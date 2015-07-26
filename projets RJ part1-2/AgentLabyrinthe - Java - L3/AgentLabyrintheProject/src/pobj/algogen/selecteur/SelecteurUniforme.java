package pobj.algogen.selecteur;

import java.util.Random;

import pobj.algogen.Individu;
import pobj.algogen.Population;

public class SelecteurUniforme implements IndivSelecteur {

	public Individu getRandom(Population pop) {
		
		return pop.get(new Random().nextInt(pop.size()));
	}

}
