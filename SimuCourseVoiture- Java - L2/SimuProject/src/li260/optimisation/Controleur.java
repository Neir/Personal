package li260.optimisation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;

import swing.CircuitObserver;
import swing.IHMSwing;
import swing.RadarObserver;
import swing.TrajectoireObserver;

import li260.exception.VoitureException;
import li260.ihm.observer.VoitureObserver;
import li260.simulation.Simulation;
import li260.tools.Initialisation;

public class Controleur implements ActionListener{
	private Initialisation init;
	private PanelInterface comPanel;
	private IHMSwing ihm;
	private int width, height;
	//private 
	//private MyThread thL = new MyThread(this);
	private Fenetre fen;
	private boolean avecThread=true;
	private boolean erreur=false;
	private boolean auto=true;
	private boolean dijk=true, cla=false, end=true, boue=false, obs=true;
	private int nb=0;

	public Controleur(Fenetre fen) throws VoitureException, IOException{
		super();
		this.fen = fen;
		fen.setActionCommand();
		fen.addActionListener(this);


	}

	public void lancement(String nom) throws VoitureException, IOException{
		init = new Initialisation(nom);
		creerInterface();
	}

	public void creerInterface(){
		ihm = new IHMSwing();
		// Ajout des observeurs	
		ihm.add(new CircuitObserver(init.getC()));
		ihm.add(new TrajectoireObserver(init.getV()));	
		ihm.add(new VoitureObserver(init.getV()));
		ihm.add(new RadarObserver(init.getRad(), init.getV()));
		creerIHM();
		// Ajout des récepteurs dans l’émetteur	
		init.getSim().add(ihm);
		//dijk.add(cont);
	}

	public void jouer(){
		Simulation.pause=avecThread;
		init.play();
	}

	public void creerIHM(){

		comPanel = new PanelInterface(this, ihm, width, height);
		fen.maj(comPanel);
	}


	@Override
	public void actionPerformed(ActionEvent e){
		String str;
		if (e.getActionCommand().equals("arret")){
			if(!erreur){
				System.out.println("CONTROLEUR.Avant : "+init);
				init.delete();
				init=null;
				System.gc();
				System.out.println("CONTROLEUR.Apres : "+init);
				dijk=true; cla=false; end=true; boue=false; obs=true;
				Initialisation.comManu="";
			}

		}

		if (e.getActionCommand().equals("lecture")){
			if(init==null){
				erreur=true;
				FenErreur f = new FenErreur(1);
				f.setVisible(true);
				f.pack();
			}
			else{
				//if(auto){
					MyThread thP = new MyThread(this, "simu"+nb);
					nb++;
					if(avecThread)
						thP.start();
					else
						jouer();
				//}

			}
		}

		if (e.getActionCommand().equals("dijk")){
			str="dijk";
			if(dijk){
				init.enleveSelect(str);
				dijk = false;
			}
			else{
				init.ajoutSelect(str);
				dijk = true;
			}
		}

		if (e.getActionCommand().equals("classique")){
			str="cla";
			if(cla){
				init.enleveSelect(str);
				cla = false;				
			}
			else{
				init.ajoutSelect(str);
				cla = true;
			}
		}

		if (e.getActionCommand().equals("endLine")){
			str="end";
			if(end){
				init.enleveSelect(str);
				end = false;
			}
			else{
				init.ajoutSelect(str);
				end = true;
			}
		}

		if (e.getActionCommand().equals("boue")){
			str="boue";
			if(boue){
				init.enleveSelect(str);
				boue = false;
			}
			else{
				init.ajoutSelect(str);
				boue = true;
			}
		}

		if (e.getActionCommand().equals("obstacle")){
			str="obs";
			if(obs){
				init.enleveSelect(str);
				obs = false;
			}
			else{
				init.ajoutSelect(str);
				obs = true;
			}
		}


		if (e.getActionCommand().equals("pause")){
			if(Simulation.pause)
				Simulation.pause = false;
			else
				Simulation.pause = true;
		}


		if (e.getActionCommand().equals("algo")){
			init.enleve("manu");
			auto=true;
		}

		if (e.getActionCommand().equals("manu")){
			init.enleve("algo");
			auto=false;
			dijk=false; cla=false; end=false; boue=false; obs=false;
			init.ajoutSelect("avant");
			init.ajoutSelect("arriere");
			init.ajoutSelect("gauche");
			init.ajoutSelect("droite");
			init.ajoutSelect("attendre");
		}

		if (e.getActionCommand().equals("avant")){
			Initialisation.comManu="avant";		
		}

		if (e.getActionCommand().equals("arriere")){
			Initialisation.comManu="arriere";
		}

		if (e.getActionCommand().equals("gauche")){
			Initialisation.comManu="gauche";
			init.tourner(1);
		}

		if (e.getActionCommand().equals("droite")){
			Initialisation.comManu="droite";
			init.tourner(1);
		}

		if (e.getActionCommand().equals("circuit")){
			JComboBox cb = (JComboBox)e.getSource();
			String circ = (String)cb.getSelectedItem();
			try {
				tailleCircuit(circ);
				System.out.println("CONTROLEUR.Circuit: "+circ); //+" Width: "+width+" Height: "+height+"\n");
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				lancement(circ);
			} catch (VoitureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("CONTROLEUR."+Initialisation.comManu);

		fen.enable(e.getActionCommand());
	}

	public IHMSwing getIhm() {
		return ihm;
	}

	private void tailleCircuit(String nom) throws NumberFormatException, IOException{
		BufferedReader bR = new BufferedReader(new FileReader(new File("tracks/"+nom+".trk")));
		height=Integer.parseInt(bR.readLine());
		width=Integer.parseInt(bR.readLine());
	}
}

