package agent;

import pobj.obs.SimpleObservable;
import agent.control.IControleur;
import agent.laby.Labyrinthe;

/**
 *  Cette classe représente un agent qui se déplace dans un
 *         labyrinthe.
 *  @author sigaud
 */
public class Simulation extends SimpleObservable{

	/** L'agent qu'on simule */
	private Agent agent;
	
	/**
	 * Le labyrinthe dans lequel la simulation se déroule.
	 * Attention, ce labyrinthe est modifié au cours de la simulation.
	 */
	private Labyrinthe laby;

	/**
	 * Constructeur : initialise une simulation d'un contrôleur donné dans un labyrinthe donné.
	 * @param m : le labyrinthe, sera modifié par la simulation
	 * @param c : le controleur
	 */
	public Simulation(Labyrinthe m, IControleur c) {
		laby = m;
		agent = new Agent(c, m.getPositionInitiale());
	}


	/**
	 * Rend le labyrinthe dans lequel la simulation évolue.
	 * @return mon labyrinthe.
	 */
	public Labyrinthe getLaby() {
		return laby;
	}

	/**
	 * Renvoie le score de l'agent
	 */
	public int getScore() {
		return agent.getScore();
	}

	/**
	 * Effectue nbPas pas de l'agent dans le labyrinthe
	 * @param nbPas : le nombre de pas
	 * @return : le score de l'agent
	 */
	public int mesurePerf (int nbPas) {
		for (int i=0;i<nbPas;i++){
			agent.faitUnPas(getLaby());
			notifyObservers();
		}
		return agent.getScore();
	}
	
}
