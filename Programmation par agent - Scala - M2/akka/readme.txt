------------README AKKA Project------------

Authors : Jérôme RAHAULT, Mohamed Reda LARBI YOUCEF.

This project is a sbt project, to run it, you have to launch 4 instances of sbt (one instance per node) and type run X where
X represents the id of the node. A common execution would be : 

sbt -> run 1
sbt -> run 2
sbt -> run 3
sbt -> run 4

NOTE : The first executed node is the leader, however if two nodes are launched simultaneously, you may have two leaders.
To avoid that, when you launch a node, wait until the console prints "node X started".

Enjoy :)
