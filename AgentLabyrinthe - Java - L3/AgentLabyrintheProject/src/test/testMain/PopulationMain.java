package test.testMain;

import java.util.Random;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.arith.ExpressionFactory;
import pobj.algogen.adapter.arith.ValeurCible;
import pobj.arith.EnvEval;

/**
 * Classe de test de Population
 */
class PopulationMain{

	public static void main(String []args){
		Random r = new Random();
		ExpressionFactory fact = new ExpressionFactory();
		EnvEval envEv = ExpressionFactory.createRandomEnvironment();
		Environnement env = new ValeurCible(r.nextDouble(), envEv);
		
		/** Convertit l'argument de l'exécution en entier */
		int param = Integer.parseInt(args[0]);
		Population popAlea;
		
		/** Construit la Population popAlea de nombre d'Individu aléatoire "param" */
		popAlea = fact.createPopulation(param, envEv, null);
		
		popAlea.evaluer(env);
		
		/** Affiche la description des Individus de la Population popAlea */
		System.out.println(popAlea.affichage((Environnement)envEv));
		
		Population popGenSuiv = popAlea.evoluer(env);
		//*
		for(int i = 0 ; i < 5 ; i++){
			System.out.println(popGenSuiv.affichage((Environnement) envEv));
			popGenSuiv = popGenSuiv.evoluer(env);
		}
		//*/
		System.out.println(popGenSuiv.affichage((Environnement) envEv));
	}
}
