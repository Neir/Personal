package Simulation;

import java.util.ArrayList;

import li260.geometrie.Vecteur;
import Balle.Balle;
import Bonus.Bonus;
import Bonus.BonusFactory;
import Bonus.WidthBonus;
import Effet.Effects;
import Jeu.Jeu;
import ObjetSim.Brick;
import Observer.BonusObserver;
import Observer.Event.UpdateEventListener;
import Observer.Event.UpdateEventSender;
import Plateau.Plateau;

public class Simulation implements UpdateEventSender, Runnable{
	private Jeu j;
	private Plateau p;
	private Balle b;
	private int mouv = 0;
	private double pos = 220;
	private boolean start = false;
	private boolean controlS = true;
	private EtatSimulation state;
	private ArrayList<UpdateEventListener> list;
	private boolean pause = false;
	
	private ArrayList<Bonus> bonList;
	private BonusObserver bonObs;
	
	private Effects effects;
	
	public Simulation(Plateau p, Balle b, Jeu j) {
		super();
		this.j = j;
		this.p = p;
		this.b = b;
		
		bonList = new ArrayList<Bonus>();
		bonObs = new BonusObserver();
		for(Brick[] brr : j.getMat()){
			for(Brick br : brr){
				if(br.isBonus()){
					BonusFactory bonFac = new BonusFactory();
					Bonus bon = bonFac.buildWidth(br, p);
					
					br.setBonus(bon);
					bonList.add(bon);
					bonObs.add(bon);
				}
			}
		}
		
		state = EtatSimulation.Run;
		list = new ArrayList<UpdateEventListener>();
	}
	
	public Boolean isPlaying(){
		return !(updateState() == EtatSimulation.Echec);		
	}
	
	public void play(){
		//BufferedImage im = Tools.imageFromCircuit(c);
		//System.out.println("SIMULATION."+pause);
		// application
		while(state==EtatSimulation.Run){
//			try {
//				Thread.sleep(1, 10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			mettreEnPause();
			
			if(controlS)
				p.bouge(pos);
			else
				p.bouge(mouv);
			//System.out.println(b.toString());
			if(start)
				b.rebond(p,j);
			else
				b.setPosX(pos);
			
			updateBonus();
			
			update();
			// MAJ Etat
			state = updateState();
		}

		System.out.println("SIMULATION."+state);

	}
	
	public void mettreEnPause() {
		while(pause)
			try {
				Thread.sleep(1);
				}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private EtatSimulation updateState(){
		EtatSimulation sim = EtatSimulation.Run; //Cas général

		if(j.gagne())  sim = EtatSimulation.Succes;
		else if(b.perdu()) sim = EtatSimulation.Echec;
		return	sim;	
	}
	
	private void updateBonus(){
		for(Bonus bon : bonList){
			if(bon.bonusCaught()){
				p.setWidth(p.getWidth()+30);
				bon.end();
			}
		}
	}

	public void update() {
		for(UpdateEventListener listener: list)	
		   	 	listener.manageUpdate();	
		}

	public void add(UpdateEventListener l) {
		list.add(l);
	}

	public void setMouv(int mouv) {
		this.mouv = mouv;
	}
	
	public void setPos(double pos){
		this.pos = pos;
	}
	
	public void setPause(boolean pause){
		this.pause = pause;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public BonusObserver getBonusObserver(){
		return bonObs;
	}
	
	public void run() {
		play();
	}
}