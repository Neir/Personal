package pobj.arith;

import pobj.algogen.Environnement;
import pobj.algogen.adapter.arith.ExpressionFactory;

public class Constante implements Expression{
	private final double value;
	
	public Constante(double value){
		this.value=value;
	}
	
	@Override
	public double eval(EnvEval e) {
		// TODO Auto-generated method stub
		
		return value;
	}

	public double getValue(){
		return value;
	}

	@Override
	public String toString() {
		return ""+value;
	}

	@Override
	public String affichage(Environnement env2) {
		return toString();
	}

	@Override
	public Expression simplifier() {
		// TODO Auto-generated method stub
		//return this;
		return ExpressionFactory.createConstante(value);
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
