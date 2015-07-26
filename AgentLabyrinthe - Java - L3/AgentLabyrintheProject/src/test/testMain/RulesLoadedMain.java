package test.testMain;

import java.io.IOException;
import java.util.ArrayList;

import agent.control.Controleur;
import agent.control.Regle;
import agent.laby.interf.LabyViewer;

import pobj.algogen.config.ChargeurConfig;
import pobj.algogen.config.Configuration;
import ruleseditor.ChargeurRegles;

public class RulesLoadedMain {
		public static void main(String[] args){
			String configFile = "configSmiley";
			Configuration conf = Configuration.getInstance();
			
			try {
				ChargeurConfig.chargerConfigPerso(configFile+".txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<Regle> rl = null;
			try {
				rl = ChargeurRegles.chargerRegles("perfectOnSmiley.rls");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new LabyViewer(new Controleur(rl),
					conf);
			//new RulesBuilder((ArrayList<Regle>) ((IControleur)bestInd.getValeurPropre()).getRuleset());
			
		}
	}