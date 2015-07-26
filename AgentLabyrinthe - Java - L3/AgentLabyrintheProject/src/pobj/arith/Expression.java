package pobj.arith;

import pobj.algogen.Environnement;

public interface Expression {
	public double eval(EnvEval e);
	public Expression clone();
	public Expression simplifier();
	public String affichage(Environnement env);
}
