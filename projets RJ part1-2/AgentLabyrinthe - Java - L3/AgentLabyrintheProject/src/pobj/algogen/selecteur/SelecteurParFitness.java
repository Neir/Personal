package pobj.algogen.selecteur;

import java.util.Random;

import pobj.algogen.Individu;
import pobj.algogen.Population;

public class SelecteurParFitness implements IndivSelecteur {

	public Individu getRandom(Population pop) {
		double somme = pop.getSommeFitnesses();
		double r = new Random().nextDouble();
		double prop = 0;
		for(Individu ind : pop.getIndividus()){
			if(prop<=r&&r<=prop + ind.fitness() / somme){
				return ind;
			}
			prop+=ind.fitness()/somme;
		}
		System.out.println("Et merde...");
		return null; 
	}

}
