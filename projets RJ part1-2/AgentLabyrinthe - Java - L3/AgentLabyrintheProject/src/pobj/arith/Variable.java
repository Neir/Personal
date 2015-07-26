package pobj.arith;

import pobj.algogen.Environnement;
import pobj.algogen.adapter.arith.ExpressionFactory;

public class Variable implements Expression {
	private final int rang;
	
	public Variable(int rang) {
		super();
		this.rang = rang;
	}

	@Override
	public double eval(EnvEval e) {
		// TODO Auto-generated method stub
		return e.getVariables(rang);
	}

	@Override
	public String toString() {
		return "rang :"+rang;
	}
	
	public String affichage(Environnement e) {
		return ""+((EnvEval) e).getVariables(rang);
	}

	@Override
	public Expression simplifier() {
		// TODO Auto-generated method stub
		//return this;
		return ExpressionFactory.createVariable(rang);
	}
	
	public Expression clone(){
		
		try {
			return (Expression) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
