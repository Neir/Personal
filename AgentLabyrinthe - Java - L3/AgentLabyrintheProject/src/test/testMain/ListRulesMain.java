package test.testMain;

import java.util.ArrayList;

import ruleseditor.Liste.RulesBuilder;

import agent.control.ControlFactory;
import agent.control.Regle;

public class ListRulesMain {

	public static void main(String []args){
		ArrayList<Regle> lr = new ArrayList<Regle>();
		lr = (ArrayList<Regle>) ControlFactory.createControleur(20).getRuleset();


		new RulesBuilder(lr);
	}
}

