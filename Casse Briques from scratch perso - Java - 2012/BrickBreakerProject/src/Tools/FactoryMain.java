package Tools;

import javax.swing.JFrame;

import li260.geometrie.Vecteur;
import Balle.Balle;
import Balle.BalleClassiqueFactory;
import Balle.BalleFactory;
import Controler.Controler;
import Jeu.Jeu;
import Jeu.JeuFactory;
import Model.IHMSwing;
import Observer.BalleObserver;
import Observer.JeuObserver;
import Observer.PlateauObserver;
import Plateau.Plateau;
import Plateau.PlateauImpl;
import Simulation.Simulation;
import Vue.EcranJeu;

public class FactoryMain {
	public static void launchEcranJeu(){
		String repLvl = "level/";
//		String name = "level one";
//		String name = "level test";
//		String name = "level two";
		String name = "level three";
		
		Thread th;

		Balle b;
		BalleFactory bf = new BalleClassiqueFactory();
		Plateau p = new PlateauImpl(new Vecteur(468/2 - 50/2, 468 - 30), 80, 30, 5);
		b = bf.build(new Vecteur(0, 468 - 30 - 16));
		
		Jeu j;
		JeuFactory jf = new JeuFactory(repLvl+name+".lvl");
		j = jf.build();
		
		IHMSwing ihm = new IHMSwing();
		Simulation simu = new Simulation(p, b, j);
		
		ihm.add(new BalleObserver(b));
		ihm.add(new PlateauObserver(p));
		ihm.add(new JeuObserver(j));
//		ihm.add(new MessageCenterObserver());
		ihm.add(simu.getBonusObserver());
		
		JFrame fen = new EcranJeu(new Controler(ihm, simu));
		fen.setContentPane(ihm);
		fen.pack();
		simu.add(ihm); // Ajout des récepteurs dans l’émetteur
		
		th = new Thread(simu);
		th.start();
	}
}
