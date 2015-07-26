package li260.tools;

import java.io.IOException;
import java.util.ArrayList;
import li260.algo.Dijkstra;
import li260.circuit.Circuit;
import li260.circuit.CircuitFactory;
import li260.exception.VoitureException;
import li260.ihm.observer.UpdateEventListener;
import li260.ihm.observer.UpdateEventSender;
import li260.radar.Radar;
import li260.radar.RadarClassique;
import li260.radar.RadarDijkstra;
import li260.selector.Selector;
import li260.selector.SelectorAttendre;
import li260.selector.SelectorEndLine;
import li260.selector.SelectorManuArriere;
import li260.selector.SelectorManuDroite;
import li260.selector.SelectorManuGauche;
import li260.selector.SelectorManuToutDroit;
import li260.selector.SelectorObstacle;
import li260.selector.SelectorRadar;
import li260.simulation.Simulation;
import li260.strategy.Strategy;
import li260.strategy.StrategyArriere;
import li260.strategy.StrategyAttendre;
import li260.strategy.StrategyBoue;
import li260.strategy.StrategyDroite;
import li260.strategy.StrategyEndLine;
import li260.strategy.StrategyGauche;
import li260.strategy.StrategyObstacle;
import li260.strategy.StrategyRadar;
import li260.strategy.StrategySelector;
import li260.strategy.StrategyToutDroit;
import li260.voiture.FerrariFactory;
import li260.voiture.Voiture;

public class Initialisation implements UpdateEventSender{
	private String nom; 
	//private IHMSwing ihm;

	private Simulation sim;
	private StrategySelector strsel;
	private Circuit c;
	private Voiture v;
	private Radar rad, rdijk;
	private Dijkstra dijk;
	private CircuitFactory cf;
	private FerrariFactory f;
	private double[] thetas;
	private Strategy strdijk, strrad, strend, strboue, strobs;
	private Strategy strav, strgau, strdroit, strarr, stratt;
	private Selector seldijk, selrad, selend, selboue, selobs;
	private Selector selav, selgau, seldroit, selarr, selatt;
	private ArrayList<UpdateEventListener> list;
	public static String comManu=""; 

	public Initialisation(String nom) throws VoitureException, IOException{
		if(!nom.isEmpty()){
			thetas = Generateur.calcThetas(50);

			this.nom=nom;
			cf = new CircuitFactory("tracks/"+nom+".trk");
			c = cf.build();

			f = new FerrariFactory(c);
			v =f.build();

			dijk = new Dijkstra(c);
			dijk.compute();
			rdijk = new RadarDijkstra(thetas, v, c, dijk);
			rad = new RadarClassique(thetas, v, c);

			selrad = new SelectorRadar();
			seldijk = new SelectorRadar();
			selobs = new SelectorObstacle(c,v);
			selend = new SelectorEndLine(c,v, rad);
			selboue = new SelectorRadar();
			selav = new SelectorManuToutDroit();
			selgau = new SelectorManuGauche();
			seldroit = new SelectorManuDroite();
			selarr = new SelectorManuArriere();
			selatt = new SelectorAttendre();
			
			strboue = new StrategyBoue(c, v, rdijk);
			strdijk = new StrategyRadar(v,rdijk);
			strrad = new StrategyRadar(v,rad);
			strend = new StrategyEndLine(rad, rdijk, v, c);
			strobs = new StrategyObstacle(v,rdijk);
			strsel = new StrategySelector();
			strav = new StrategyToutDroit();
			strgau = new StrategyGauche();
			strdroit = new StrategyDroite();
			strarr = new StrategyArriere();
			stratt = new StrategyAttendre();

			strsel.add(strend, selend);
			strsel.add(strobs, selobs);
			strsel.add(strdijk, seldijk);
			
			sim = new Simulation(c, v, strsel);

			//ihm = new IHMSwing();
			//ControleurObserver cont = new ControleurObserver(c);

			// Ajout des observeurs	
			/*
			ihm.add(new CircuitObserver(c));
			ihm.add(new TrajectoireObserver(v));	
			ihm.add(new VoitureObserver(v));
			ihm.add(new RadarObserver(rc, v));
			 */
			// Ajout des récepteurs dans l’émetteur	
			//sim.add(ihm);
			//dijk.add(cont);
			//dijk.compute();
		}
	}

	public void ajoutSelect(String str) {
		if(str.equals("dijk"))
			strsel.add(strdijk, seldijk);
		
		if(str.equals("cla"))
			strsel.add(strrad, selrad);

		if(str.equals("end"))
			strsel.add(strend, selend);

		if(str.equals("boue"))
			strsel.add(strboue, selboue);

		if(str.equals("obs"))
			strsel.add(strobs, selobs);
		
		if(str.equals("avant"))
			strsel.add(strav, selav);

		if(str.equals("arriere"))
			strsel.add(strarr, selarr);

		if(str.equals("gauche"))
			strsel.add(strgau, selgau);

		if(str.equals("droite"))
			strsel.add(strdroit, seldroit);

		if(str.equals("attendre"))
			strsel.add(stratt, selatt);
		/*if(strsel.contains(strend, selend)){
			strsel.setPriority(strend, selend, 1);
		}
		if(strsel.contains(strdijk, seldijk)){
			strsel.setPriority(strdijk, seldijk, 0);
			if(strsel.contains(strrad, selrad)){
				strsel.setPriority(strrad, selrad, 0);
			}
		}*/
	}


	public void enleveSelect(String str) {
		if(str.equals("dijk"))
			strsel.remove(strdijk, seldijk);

		if(str.equals("cla"))
			strsel.remove(strrad, selrad);

		if(str.equals("end"))
			strsel.remove(strend, selend);

		if(str.equals("boue"))
			strsel.remove(strboue, selboue);

		if(str.equals("obs"))
			strsel.remove(strobs, selobs);
		
		if(str.equals("avant"))
			strsel.remove(strav, selav);

		if(str.equals("arriere"))
			strsel.remove(strarr, selarr);

		if(str.equals("gauche"))
			strsel.remove(strgau, selgau);

		if(str.equals("droite"))
			strsel.remove(strdroit, seldroit);

		if(str.equals("attendre"))
			strsel.remove(stratt, selatt);
	}

	public void enleve(String str){
		if(str.equals("manu")){
			if(strsel.contains(strav, selav))
				enleveSelect("avant");
			if(strsel.contains(strarr, selarr))
				enleveSelect("arriere");
			if(strsel.contains(strgau, selgau))
				enleveSelect("gauche");
			if(strsel.contains(strdroit, seldroit))
				enleveSelect("droite");
			if(strsel.contains(stratt, selatt))
				enleveSelect("attendre");
		}
		if(str.equals("algo")){
			if(strsel.contains(strdijk, seldijk))
				enleveSelect("dijk");
			if(strsel.contains(strrad, selrad))
				enleveSelect("cla");
			if(strsel.contains(strend, selend))
				enleveSelect("end");
			if(strsel.contains(strboue, selboue))
				enleveSelect("boue");
			if(strsel.contains(strobs, selobs))
				enleveSelect("obs");
		}
	}

	public void play() {
		//System.out.println(INITIALISATION.strsel);
		try {
			sim.play();
		} catch (VoitureException e) {
			e.printStackTrace();
		}
		//System.out.println("INITIALISATION."+v.getPosition());
		Simulation.saveListeCommande(sim.getRecord(), "sauv/"+nom+".com");
	}

	public void add(UpdateEventListener event){
		sim.add(event);		
	}


	public Circuit getC() {
		return c;
	}

	public Voiture getV() {
		return v;
	}

	public String getNom() {
		return nom;
	}

	public Simulation getSim(){
		return sim;
	}

	public Radar getRad() {
		return rad;
	}


	public void update() {
		for(UpdateEventListener listener: list)	
			listener.manageUpdate();	
	}

	public void delete() {
		nom=null;
		c=null; cf=null;
		v=null; f=null;
		strdijk=null; strrad=null; strend=null; strboue=null; strobs=null;
		seldijk=null; selrad=null; selend=null; selboue=null; selobs=null;
		thetas=null;
		rad=null; rdijk=null;
		sim=null;
		strsel.clear();

	}

	public void tourner(int cpt) {
		try {
			Thread.sleep(cpt*10);
			}
		catch (InterruptedException e) {
			e.printStackTrace();
		}		
		comManu="avant";
	}


}
