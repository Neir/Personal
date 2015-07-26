package pobj.strategy;

import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.selecteur.IndivSelecteur;

public class EvolutionGenerationnelle implements IEvolution {
	
	private IndivSelecteur indSelecteur;
	
	public EvolutionGenerationnelle(IndivSelecteur iS){
		indSelecteur=iS;
	}
	
	public Population reproduire(Population pop, double ratio) {
		Individu newInd = indSelecteur.getRandom(pop);
		return pop.reproduire();
	}
}