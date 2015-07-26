package test.testMain;

import java.awt.Point;
import java.io.IOException;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

/**
 * Classe principale pour tester le comportement des agents dans
 *         le labyrinthe.
 *  @author sigaud 
 */
public class SimuMain {

	/**
	 * @param args[0] : nom du fichier contenant le labyrinthe
	 * @param args[1] : nombre de pas d'évaluation
	 * @param args[2] : nombre de règles par controleur
	 */
	public static void main(String[] args) {
		String labyFile = "testSansMur.mze"; // args[0];
		int nbSteps = 50; // Integer.parseInt(args[1]);
		int nbRules = 10; // Integer.parseInt(args[2]);
		try {
			Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
			
			//IControleur sc = ControlFactory.createControleurDroitier();
			//IControleur sc = ControlFactory.createControleur(nbRules);
			IControleur sc = ControlFactory.createSmartController();
			
			//Agent a = new Agent(sc, new Point(1,1));
			Simulation sim = new Simulation(laby, sc);
			System.out.println(sim.mesurePerf(nbSteps));
			//System.out.println(sc);
		} catch (IOException e) {
			System.out.println("Probl�me de chargement du labyrinthe"+e);
			System.exit(1);
		}
	}
}
