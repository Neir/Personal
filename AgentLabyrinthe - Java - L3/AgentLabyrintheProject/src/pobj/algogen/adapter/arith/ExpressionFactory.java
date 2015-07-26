package pobj.algogen.adapter.arith;

import java.util.Random;

import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.arith.Constante;
import pobj.arith.EnvEval;
import pobj.arith.Expression;
import pobj.arith.OperateurBinaire;
import pobj.arith.Operator;
import pobj.arith.Variable;
import pobj.strategy.IEvolution;

public class ExpressionFactory {
	private final static int MAXVARIABLE = 2;
	private static Random generateur = new Random();
	public static int cpt = 0, cptVar = 0, cptConst = 0;
	
	public static Expression createOperateurBinaire(Operator op, Expression left, Expression right){
		Expression ob = new OperateurBinaire(op, left, right);
		return ob.simplifier();
	}
	
	public static Expression createConstante(double value){
		return new Constante(value);
	}
	
	public static Expression createVariable(int id){
		return new Variable(id);
	}
	
	public static EnvEval createRandomEnvironment(){
		EnvEval env = new EnvEval(MAXVARIABLE);
		for(int i = 0; i<MAXVARIABLE; i++)
			env.setVariables(i, generateur.nextDouble());
		return env;
	}
	
	public static Expression createRandomExpression(int prof){
		int typeExpr = generateur.nextInt(3);
		Random r = new Random();
		int choix = r.nextInt(3);
		Operator op;
		if(typeExpr==0)
			return createVariable(generateur.nextInt(MAXVARIABLE));
		else if(typeExpr==1)
			return createConstante(generateur.nextDouble());
		else {
			if(prof>0){
				switch (choix) {
				case 0:
					op = Operator.MINUS;
					break;
				case 1:
					op = Operator.PLUS;
					break;
				case 2:
					op = Operator.MULT;
					break;
				default:
					op = Operator.MULT;
				}
			return createOperateurBinaire(op, createRandomExpression(prof-1).simplifier(), createRandomExpression(prof-1).simplifier());
			} else
				return createVariable(generateur.nextInt(MAXVARIABLE));
		}
	}

	protected Environnement createEnvironnement(Object o, EnvEval env) {
		return new ValeurCible(o,env);
	}

	public Population createPopulation(int size, EnvEval env, IEvolution evol){
		int i;
		Individu ind;
		Population pop = new Population(evol);
		for (i=0;i<size;i++){
			ind = new IndividuExpression(3, env);
			pop.add(ind);
		}
		return pop;
	}
}

