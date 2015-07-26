package pobj.algogen.selecteur;

import pobj.algogen.Individu;
import pobj.algogen.Population;

public interface IndivSelecteur {
	public Individu getRandom(Population pop);
}
