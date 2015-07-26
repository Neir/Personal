package pobj.arith;

import pobj.algogen.Environnement;
import pobj.algogen.adapter.arith.ExpressionFactory;

public class OperateurBinaire implements Expression {
	private Operator op;
	private final Expression e1;
	private final Expression e2;
	
	public OperateurBinaire(Operator op, Expression e1, Expression e2){
		this.op=op;
		this.e1=e1;
		this.e2=e2;
	}

	public Operator getOp() {
		return op;
	}

	public Expression getE1() {
		return e1;
	}

	public Expression getE2() {
		return e2;
	}

	@Override
	public double eval(EnvEval e) {
		switch(op){
		case DIV :
			return e1.eval(e)/e2.eval(e);
		case MINUS :
			return Math.abs(e1.eval(e)-e2.eval(e));
		case MULT :
			return e1.eval(e)*e2.eval(e);
		case PLUS :
			return e1.eval(e)+e2.eval(e);
		default : 
			System.out.println("Mauvaise op�ration");
			return -1;
		}
	}

	@Override
	public String toString() {
		return "left : " + e1 + ", op : " + op + ", right : " + e2;
	}
	
	public String affichage(Environnement e){
		return " " + e1.affichage(e) + " " + op + " " + e2.affichage(e) + " ( = " + eval((EnvEval) e) + " ) ";
		//return ""+eval(e);
	}

	@Override
	public Expression simplifier() {
		if(e1 instanceof Constante && e2 instanceof Constante)
			switch(op){
			case DIV :
				return ExpressionFactory.createConstante(((Constante) e1).getValue()/((Constante) e2).getValue());
			case MINUS :
				return ExpressionFactory.createConstante(((Constante) e1).getValue()-((Constante) e2).getValue());
			case MULT :
				return ExpressionFactory.createConstante(((Constante) e1).getValue()*((Constante) e2).getValue());
			case PLUS :
				return ExpressionFactory.createConstante(((Constante) e1).getValue()+((Constante) e2).getValue());
			default : 
				System.out.println("Mauvaise op�ration");
				return null;
			}
		else
			return this;
			//return ExpressionFactory.createOperateurBinaire(getOp(), e1.simplifier(), e2.simplifier());
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
