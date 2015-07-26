package test.testMain;

import java.io.IOException;
import java.util.ArrayList;

import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.algogen.config.ChargeurConfig;
import pobj.algogen.config.Configuration;
import pobj.algogen.config.Generateur;
import ruleseditor.Liste.RulesBuilder;
import spiti.core.io.Chrono;
import agent.control.IControleur;
import agent.control.Regle;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import agent.laby.interf.LabyViewer;

public class TestComplet {
	public static void main(String[] args){
		String configFile = "config";
		Configuration conf = Configuration.getInstance();
		
		try {
			ChargeurConfig.chargerConfigPerso(configFile+".txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(conf.getParams());

		//Chrono time = new Chrono();
		Individu bestInd = Generateur.generation(conf);
		//time.stop();
		System.out.println(bestInd.fitness());
		new LabyViewer((IControleur) bestInd.getValeurPropre(),
				conf);
		//new RulesBuilder((ArrayList<Regle>) ((IControleur)bestInd.getValeurPropre()).getRuleset());
		
	}
}
		
//	public static void main(String []args){
//		String labyFile = "Smiley86Pts";
//		int nbCoups = 100, taille = 10000, nbRules = 20, nbGen = 10;
//
//		Labyrinthe laby = null;
//		try {
//			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Chrono time = new Chrono();
//		Environnement envEv = PopulationFactory.createEnvironnement(laby, nbCoups);
//		Population popAlea;
//System.out.println("MyArray :");
//		/** Construit la Population popAlea de nombre d'Individu aléatoire "param" */
//		popAlea = PopulationFactory.createRandomPopulation(taille, nbRules);
//		//popAlea.add(new ControleurIndividuAdapter(ControlFactory.createSmartController()));
//
//		popAlea.evaluer(envEv);
//
//		/** Affiche la description des Individus de la Population popAlea */
//		//System.out.println(popAlea.affichage(envEv));
//
//		Population popGenSuiv = popAlea.evoluer(envEv);
//		//*
//		for(int i = 0 ; i < nbGen ; i++){
//			//System.out.println(popGenSuiv.affichage(envEv));
//			popGenSuiv = popGenSuiv.evoluer(envEv);
//		}
//		time.stop();
//		//*/
//		//System.out.println(popGenSuiv.affichage(envEv));
//
//	}
//}