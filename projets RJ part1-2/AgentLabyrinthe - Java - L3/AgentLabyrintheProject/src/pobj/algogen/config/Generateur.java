package pobj.algogen.config;

import java.io.IOException;

import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.algogen.selecteur.IndivSelecteur;
import pobj.algogen.selecteur.SelecteurParFitness;
import pobj.algogen.selecteur.SelecteurUniforme;
import pobj.strategy.IEvolution;

public class Generateur {
	public static Individu generation(Configuration conf){
		int nbCoups = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_STEPS));
		int taille = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.TAILLE_POP));
		int nbRules = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_RULES));
		int nbGen = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_GEN));
		
		Labyrinthe laby = null;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(conf.getParameterValue(AlgoGenParameter.LABY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Environnement envEv = PopulationFactory.createEnvironnement(laby, nbCoups);
		Population popAlea;

		/** Construit la Population popAlea de nombre d'Individu aléatoire "param" */
		popAlea = PopulationFactory.createRandomPopulation(taille, nbRules, typeEvolution(conf));
		//popAlea.add(new ControleurIndividuAdapter(ControlFactory.createSmartController()));

		popAlea.evaluer(envEv);

		/** Affiche la description des Individus de la Population popAlea */
		//System.out.println(popAlea.affichage(envEv));

		Population popGenSuiv = popAlea.evoluer(envEv);
		//*
		for(int i = 0 ; i < nbGen ; i++){
			//System.out.println(popGenSuiv.affichage(envEv));
			popGenSuiv = popGenSuiv.evoluer(envEv);
		}
		//*/
		
		//System.out.println(popGenSuiv.affichage(envEv));
		
		return popGenSuiv.getIndividus().get(0);
	}
	
	public static IEvolution typeEvolution(Configuration conf){
		IndivSelecteur indSelec;
		IEvolution evol;
		if(Boolean.parseBoolean(conf.getParameterValue(AlgoGenParameter.SELECT_UNI)))
			indSelec = new SelecteurUniforme();
		else
			indSelec = new SelecteurParFitness();
		
		if(Boolean.parseBoolean(conf.getParameterValue(AlgoGenParameter.EVO_GEN)))
			evol = new pobj.strategy.EvolutionGenerationnelle(indSelec);
		else
			evol = new pobj.strategy.EvolutionProgressive(indSelec);
		return evol;
	}
}
