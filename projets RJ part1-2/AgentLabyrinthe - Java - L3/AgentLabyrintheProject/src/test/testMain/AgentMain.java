package test.testMain;

import java.io.IOException;

import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.config.ChargeurConfig;
import pobj.algogen.config.Configuration;
import pobj.algogen.config.Generateur;


//public class AgentMain {
//	public static void main(String []args){
//		String labyFile = "test60Points.mze";
//		Configuration config;
//		try {
//			config = ChargeurConfig.ChargerConfig("testConfig.txt");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			return;
//		}
//		int nbCoups = config.getParameterValue(AlgoGenParameter.NB_GEN);
//		int taille = config.getParameterValue(AlgoGenParameter.TAILLE_POP);
//		int nbRules = config.getParameterValue(AlgoGenParameter.NB_RULES);
//		int nbGen = config.getParameterValue(AlgoGenParameter.NB_STEPS);
//		
//		Labyrinthe laby;
//		try {
//			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		}
//		
//		Environnement envEv = PopulationFactory.createEnvironnement(laby, nbCoups);
//		Population popAlea;
//
//		/** Construit la Population popAlea de nombre d'Individu aléatoire "param" */
//		popAlea = PopulationFactory.createRandomPopulation(taille, nbRules);
//		//popAlea.add(new ControleurIndividuAdapter(ControlFactory.createSmartController()));
//
//		popAlea.evaluer(envEv);
//
//		/** Affiche la description des Individus de la Population popAlea */
//		System.out.println(popAlea.affichage(envEv));
//
//		Population popGenSuiv = popAlea.evoluer(envEv);
//		//*
//		for(int i = 0 ; i < nbGen ; i++){
//			System.out.println(popGenSuiv.affichage(envEv));
//			popGenSuiv = popGenSuiv.evoluer(envEv);
//		}
//		//*/
//		System.out.println(popGenSuiv.affichage(envEv));
//	}

public class AgentMain {
	public static void main(String []args){
		try {
			ChargeurConfig.chargerConfigPerso("config.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Generateur.generation(Configuration.getInstance());
	}
}
