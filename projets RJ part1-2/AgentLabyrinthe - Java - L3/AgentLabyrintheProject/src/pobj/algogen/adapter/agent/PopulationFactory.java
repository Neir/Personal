package pobj.algogen.adapter.agent;

import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.strategy.IEvolution;

public class PopulationFactory {

	public static Environnement createEnvironnement(Labyrinthe lab, int nbCoups) {
		return new LabyEnvironnementAdapter(lab, nbCoups);
	}

	public static Population createRandomPopulation(int taille, int nbRules, IEvolution evol) {
		int i;
		Individu ind;
		Population pop = new Population(evol);
		for (i=0;i<taille;i++){
			ind = createRandomIndividu(nbRules);
			pop.add(ind);
		}
		return pop;
	}
	
	public static Individu createRandomIndividu(int nbRules) {
		return new ControleurIndividuAdapter(nbRules);
	}
}