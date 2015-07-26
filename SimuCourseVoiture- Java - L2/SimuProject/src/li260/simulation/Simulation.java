package li260.simulation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.exception.VoitureException;
import li260.ihm.observer.UpdateEventListener;
import li260.ihm.observer.UpdateEventSender;
import li260.strategy.Strategy;
import li260.tools.Commande;
import li260.tools.Tools;
import li260.voiture.Voiture;

public class Simulation implements UpdateEventSender{
	private Circuit c;
	private Voiture v;
	private Strategy s;
	private EtatSimulation state;
	private ArrayList<Commande> record;
	private ArrayList<UpdateEventListener> list;
	public static boolean pause;
	
	public Simulation(Circuit c, Voiture v, Strategy s) {
		super();
		this.c = c;
		this.v = v;
		this.s = s;
		state = EtatSimulation.Run;
		list = new ArrayList<UpdateEventListener>();
	}
	
	public Boolean isPlaying(){
		return !(updateState() == EtatSimulation.Echec);		
	}
	
	public void play() throws VoitureException{
		//BufferedImage im = Tools.imageFromCircuit(c);
		record = new ArrayList<Commande>();
		while(state == EtatSimulation.Run){
			playOneShot();
			update();
		}
	}

	public  void playOneShot() throws VoitureException{
		Commande c = s.getCommande();
		record.add(c);
		//System.out.println("SIMULATION."+pause);
		mettreEnPause();
		// application
		v.drive(c);
		// MAJ Etat
		state = updateState();
		//System.out.println("SIMULATION."+state);
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

	public ArrayList<Commande> getRecord(){
	     return record;
	  }
	
	private EtatSimulation updateState(){
		EtatSimulation sim = EtatSimulation.Run; //Cas général
		
		//Test terrain
		if(!Tools.isRunnable(c.getTerrain(v.getPosition()))){
			sim = EtatSimulation.Echec;
		}
		
		
		//Test arrivée
		if((c.getTerrain(v.getPosition())==Terrain.EndLine)){
			if((v.getPosition().prodScal(c.getDirectionArrivee()))<0){
				sim = EtatSimulation.Echec;
			}
			
			else sim = EtatSimulation.Succes;
		}
			
		
		return	sim;	
	}
	
	public static void saveListeCommande(ArrayList<Commande> liste, String filename){
		try {
			int cpt = 0;
			DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
			for(Commande c:liste){
				os.writeDouble(c.getAcc());
				os.writeDouble(c.getTurn());
				cpt++;
			}
			os.close();
			System.out.println("SIMULATION."+cpt+" commandes written succesfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Commande> loadListeCommande(  String filename) throws IOException{
		ArrayList<Commande> liste = null;

		try {
			DataInputStream os = new DataInputStream(new FileInputStream(filename));

			liste = new ArrayList<Commande>();
			double a,t;
			while(true){ // on attend la fin de fichier
				a = os.readDouble();
				t = os.readDouble();
				liste.add(new Commande(a,t));
			}

		} catch (EOFException e){
			return liste;
		} 

	}

	public void update() {
		for(UpdateEventListener listener: list)	
		   	 	listener.manageUpdate();	
		}

	@Override
	public void add(UpdateEventListener l) {
		list.add(l);
	}
}