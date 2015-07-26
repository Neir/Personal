package pobj.strategy;

import pobj.algogen.Population;

public interface IEvolution {
	public Population reproduire(Population pop, double ratio);
}