package pobj.algogen.adapter.agent;

import agent.Simulation;
import agent.control.IControleur;
import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;

public class LabyEnvironnementAdapter implements Environnement{
	private Labyrinthe laby;
	private int nbSteps;
	
	public LabyEnvironnementAdapter(Labyrinthe laby, int nbSteps) {
		super();
		this.laby = laby;
		this.nbSteps = nbSteps;
	}

	@Override
	public double eval(Individu i) {
		// TODO Auto-generated method stub
		Simulation s = new Simulation(laby.clone(), (IControleur) i.getValeurPropre());
		return s.mesurePerf(nbSteps);
	}

}
