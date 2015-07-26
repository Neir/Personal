package test.testMain;

import java.io.IOException;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.algogen.config.ChargeurConfig;
import pobj.algogen.config.Configuration;
import pobj.algogen.config.Generateur;
import agent.control.ControlFactory;
import agent.control.Controleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import agent.laby.interf.LabyViewer;

public class testInterf {
	public static void main(String[] args){

		String labyFile = "Smiley86Pts";
		int nbCoups = 100, taille = 30, nbRules = 10, nbGen = 5;
		
		try {
			ChargeurConfig.chargerConfigPerso("configSmiley.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Labyrinthe laby = null;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Environnement envEv = PopulationFactory.createEnvironnement(laby, nbCoups);
		Population popAlea;

		popAlea = PopulationFactory.createRandomPopulation(taille, nbRules, Generateur.typeEvolution(Configuration.getInstance()));
		popAlea.add(new ControleurIndividuAdapter(ControlFactory.createSmartController()));

		popAlea.evaluer(envEv);

		Population popGenSuiv = popAlea.evoluer(envEv);
		//*
		for(int i = 0 ; i < nbGen ; i++){
			popGenSuiv = popGenSuiv.evoluer(envEv);
		}
		//*/
		//System.out.println(popGenSuiv.affichage(envEv));
		Controleur c = (Controleur) popGenSuiv.getIndividus().get(0).getValeurPropre();
		System.out.println(popGenSuiv.getIndividus().get(0).fitness());
		
		new LabyViewer(c,Configuration.getInstance());
	}
}
