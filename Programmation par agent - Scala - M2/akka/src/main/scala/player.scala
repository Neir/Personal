
package upmc.akka.culto

import akka.util.Timeout
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const
import com.typesafe.config.ConfigFactory

import math._
import scala.concurrent.Await
import scala.concurrent.Await._
import java.util.concurrent.TimeoutException
import akka.pattern.ask
import scala.concurrent.duration._
//import scala.concurrent.ExecutionContext
//import ExecutionContext.Implicits.global

import akka.actor.{Props, Actor, ActorRef, ActorSystem, ActorSelection}

abstract class ObjectBeat
case class Beat(id:Int) extends ObjectBeat
case class LeaderBeat(id:Int) extends ObjectBeat
case class OutGoingMessage(obj:ObjectBeat)
case class InGoingMessage(obj:ObjectBeat)
case class LeaderChanged(id:Int)
case class Dead(id:Int)
case class Live(id:Int)
case class ALG(id:Int)
case class AVS(id:Int)
case class AVSRSP(id:Int)
case class inALG(id:Int,to:Int)
case class inAVS(id:Int,to:Int)
case class inAVSRSP(id:Int,to:Int)

object player {
  implicit val HEART_BEAT_PERIOD = 300

  val ticks = 300 milliseconds
  val NEIGH = -1

  case object isLeader

  class NodeActor (m_id:Int, nodes:Array[ActorSelection]) extends Actor {
    val beatActor = context.actorOf(Props(new BeatActor(m_id)), name ="beat")
    val checkerActor = context.actorOf(Props(new CheckerActor(nodes.size)), name ="checker")
    val electionActor = context.actorOf(Props(new ElectionActor(m_id)), name ="election")
    val displayActor = context.actorOf(Props(new DisplayActor(nodes.size)), name ="display")

    var alives = Array(false, false, false, false)
    var leader_id = -1

    def receive = {
      case "start" =>
        initAlives()
        checkerActor ! Tick
        println("node "+(m_id+1)+" started")
        if(nextNode() == -1) {
          leader_changed(m_id)
        }
        beatActor ! Tick
        displayActor ! Live(m_id)
      case InGoingMessage(Beat(id:Int)) =>
        broadcast(OutGoingMessage(Beat(id)))
        displayActor ! Beat(id)
      case InGoingMessage(LeaderBeat(id:Int)) =>
        broadcast(OutGoingMessage(LeaderBeat(id)))
        displayActor ! LeaderBeat(id)
      case OutGoingMessage(LeaderBeat(id:Int)) =>
        if(id != leader_id) {
          leader_id = id
        }
        checkerActor ! LeaderBeat(id)
        displayActor ! LeaderBeat(id)
      case OutGoingMessage(Beat(id:Int)) =>
        if(!alives(id)) {
          alives(id) = true
        }
        checkerActor ! Beat(id)
        displayActor ! Beat(id)
      case Dead(id:Int) =>
        if(alives(id)) {
          alives(id) = false
          if(id == leader_id) {
            if(nextNode() == -1) {
              leader_changed(m_id)
            }
            else {
              if(isMinimal()) {
                leader_changed(m_id)
              }
              //electionActor ! "start"
            }
          }
          displayActor ! Dead(id)
        }
      case Live(id:Int) =>
        alives(id) = true
        displayActor ! Live(id)
      case inALG(i, to) =>
        if(to == NEIGH) {
          val prev = nextNode()
          if(prev != -1) {
            nodes(prev) ! ALG(i)
          }
        }
        else {
          nodes(to) ! ALG(i)
        }
      case ALG(i) =>
        electionActor ! ALG(i)
      case inAVS(i, to) =>
        if(to == NEIGH) {
          val prev = prevNode()
          if(prev != -1) {
            nodes(prev) ! AVS(i)
          }
        }
        else {
          nodes(to) ! AVS(i)
        }
      case AVS(i) =>
        electionActor ! AVS(i)
      case inAVSRSP(i, to) =>
        if(to == NEIGH) {
          val prev = prevNode()
          if(prev != -1) {
            nodes(prev) ! AVSRSP(i)
          }
        }
        else {
          nodes(to) ! AVSRSP(i)
        }
      case AVSRSP(i) =>
        electionActor ! AVSRSP(i)
      case isLeader =>
        sender ! (leader_id == m_id)
      case _ => println("NodeActor : Me not understand you :p")
    }

    def broadcast (o:Object): Unit = {
      for(i <- 0 to nodes.size - 1) {
        nodes(i) ! o
      }
    }

    def leader_changed(id:Int): Unit = {
      leader_id = id
      beatActor ! LeaderChanged(id)
      displayActor ! LeaderBeat(id)
    }

    def isMinimal(): Boolean = {
      for(i <- 0 to m_id - 1) {
        if(alives(i)) {
          return false;
        }
      }
      return true
    }

    def initAlives ():Unit = {
      for (i <- 0 to nodes.size-1) {
        if(i == m_id) {
          alives(i) = true
        }
        else {
          implicit val timeout = Timeout(2 seconds)
          val future = nodes(i) ? isLeader
          try {
            val result = Await.result(future, timeout.duration).asInstanceOf[Boolean]
            alives(i) = true
            if(result) {
              leader_changed(i)
            }
            displayActor ! Live(i)
          }
          catch {
            case t: TimeoutException => alives(i) = false ;
          }
        }
      }
    }
    def nextNode ():Int = { // Useful for ElectionActor
    var i = (m_id + 1) % nodes.size
      while(i != m_id) {
        if(alives(i)) {
          return i
        }
        i = (i + 1) % nodes.size
      }
      return -1
    }

    def prevNode ():Int = { // Useful for ElectionActor
    var i = (m_id - 1)
      if(i < 0 ) {
        i = nodes.size -1
      }
      while(i != m_id) {
        if(alives(i)) {
          return i
        }
        i = (i - 1)
        if(i < 0 ) {
          i = nodes.size -1
        }
      }
      return -1
    }
  }

  case object Tick

  class ElectionActor(m_id:Int) extends Actor {
    val system = context.system
    val scheduler = context.system.scheduler
    val father = context.actorSelection("../")

    val PASSIVE = 1
    val CANDIDATE = 2
    val LEADER = 3
    val WAITING = 4
    val DUMMY = 5

    var status = PASSIVE
    var cand_succ:Int = -1
    var cand_pred:Int = -1

    def receive = {
      case "start" =>
        if(status == PASSIVE) {
          status = CANDIDATE
          cand_succ = -1
          cand_pred = -1
          father ! inALG(m_id, NEIGH)
        }
      case ALG(init) =>
        if(status == PASSIVE) {
          status = DUMMY
          inALG(init, NEIGH)
        }
        if(status == CANDIDATE) {
          cand_pred = init
          if(m_id > init) {
            if(cand_succ == -1) {
              status = WAITING
              inAVS(m_id, init)
            }
            else {
              father ! inAVSRSP(cand_pred, cand_succ)
              status = DUMMY
            }
          }
          if(m_id == init) {
            status = LEADER
            father ! OutGoingMessage(LeaderBeat(m_id))
          }
        }
      case AVS(j) =>
        if(status == CANDIDATE) {
          if(cand_pred == -1) {
            cand_succ = j
          }
          else {
            father ! inAVSRSP(cand_pred,j)
            status = DUMMY
          }
        }
        if(status == WAITING) {
          cand_succ = j
        }
      case AVSRSP(k) =>
        if(status == WAITING) {
          if(m_id == k) {
            status = LEADER
            father ! OutGoingMessage(LeaderBeat(m_id))
          }
          else {
            cand_pred = k
            if(cand_succ == -1) {
              if(k < m_id) {
                status = WAITING
                father ! inAVS(m_id,k)
              }
            }
            else {
              status = DUMMY
              father ! inAVSRSP(k, cand_succ)
            }
          }
        }

      case _ => println("ElectionActor : Me not understand you :p")
    }
  }

  class BeatActor (id:Int) extends Actor {
    //implicit val timeout = Timeout(100 milliseconds)
    val system = context.system
    import system.dispatcher

    def receive = {
      case Tick =>
        scheduler.scheduleOnce(ticks, self, Tick)
        if(id != leader_id) {
          father ! InGoingMessage(Beat(id))
        } else {
          father ! InGoingMessage(LeaderBeat(id))
        }
      case LeaderChanged(id) =>
        leader_id = id
      case _ => println("BeatActor : Me not understand you :p")
    }

    val scheduler = context.system.scheduler
    var leader_id = -1
    val father = context.actorSelection("../")
  }

  class CheckerActor(nbNodes:Int) extends Actor {
    var max_time = 5
    var timeouts = new Array[Int](nbNodes) // increase all ticks, if greater than max_time, node is dead
    val system = context.system
    val scheduler = context.system.scheduler
    val father = context.actorSelection("../")
    import system.dispatcher

    for (i <- 0 to nbNodes -1 ) {
      timeouts(i) = max_time
    }

    def receive = {
      case Beat(id:Int) =>
        if(timeouts(id) >= max_time) {
          father ! Live(id)
        }
        timeouts(id) = 0
      case LeaderBeat(id:Int) =>
        if(timeouts(id) >= max_time) {
          father ! Live(id)
        }
        timeouts(id) = 0
      case Tick =>
        scheduler.scheduleOnce(ticks, self, Tick)
        for(i <- 0 to nbNodes-1) {
          if(timeouts(i) < max_time) {
            timeouts(i) = timeouts(i) + 1
            if(timeouts(i) == max_time) {
              father ! Dead(i)
            }
          }
        }
      case _ => println("CheckerActor : Me not understand you :p")
    }
  }

  class DisplayActor(nbNodes:Int) extends Actor {
    var alives = new Array[Boolean](nbNodes)
    var leaderId = -1

    for(i <- 0 to nbNodes-1){
      alives(i) = false
    }

    def receive = {
      case Beat(id:Int) =>
      //println("BEAT ! "+(id+1))
      case LeaderBeat(id:Int) =>
        //println("LBEAT ! "+(id+1))
        if(leaderId != id+1) {
          println("New Leader = " + (id + 1))
          leaderId = id+1
          self ! "print"
        }
      case Live(id:Int) =>
        //println("LIVE ! "+(id+1))
        if(!alives(id)) {
          alives(id) = true
          self ! "print"
        }
      case Dead(id:Int) =>
        println("DEAD ! "+(id+1))
        if(alives(id)) {
          alives(id) = false
          self ! "print"
        }
      case "print" =>
        var str = ""
        for( i <- 0 to nbNodes-1){
          if(alives(i))
            str += (i+1)+","
        }
        println("Nodes : ("+str+")")
        println("Leader -> "+leaderId)
      case _ => println("DisplayActor : Me not understand you :p")
    }
  }

  def main(args: Array[String]): Unit = {
    val id = args(0).toInt
    val nbNodes = 4
    val nodes = new Array[ActorSelection](nbNodes)
    val system = ActorSystem.create("Player"+id,ConfigFactory.load().getConfig("system"+id))

    for (i <- 0 to nbNodes-1){
      val path = "akka.tcp://Player" + (i+1) + "@127.0.0.1:600" + (i+1) + "/user/NodeActor"
      println(path)
      nodes(i) = system.actorSelection(path)
    }

    val nodeActor = system.actorOf(Props(new NodeActor(id-1, nodes)), name = "NodeActor")

    nodeActor ! "start"
    println ("control c pour quitter, ou pas")
  }
}

