package pobj.arith;

import pobj.algogen.Environnement;
import pobj.algogen.adapter.arith.ExpressionFactory;

public class TestArith {
	public static void main(String[] args){
		Expression e1 = new Constante(10);
		Expression e2 = new Variable(0);
		EnvEval env = new EnvEval(2);
		env.setVariables(0, 2);
		OperateurBinaire ob = new OperateurBinaire(Operator.DIV, e1, e2);
		
		//System.out.println(ob.getE1().eval(env) + " " + ob.getOp() + " " + ob.getE2().eval(env) + " = " + ob.eval(env));
		
		EnvEval env2 = ExpressionFactory.createRandomEnvironment();
		Expression ob2 = ExpressionFactory.createRandomExpression(3);
		
		System.out.println(ob2.affichage((Environnement) env2));
		
		/*
		System.out.println(((OperateurBinaire) ob2).getE1().eval(env2) + " " 
						+ ((OperateurBinaire) ob2).getOp() + " " 
						+ ((OperateurBinaire) ob2).getE2().eval(env2) + " = " 
						+ ob2.eval(env2));
		//*/
	}
}
