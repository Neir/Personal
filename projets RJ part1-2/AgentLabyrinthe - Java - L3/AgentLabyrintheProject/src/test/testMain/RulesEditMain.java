package test.testMain;

import ruleseditor.RulesBuilderONERULEONLY;
import ruleseditor.Liste.RulesBuilder;
import agent.control.ControlFactory;
import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;

/** CLASSE COMPLETEMENT DEPASSE, A SUPPRIMER **/
public class RulesEditMain {
	public static void main(String []args){
		Regle r = ControlFactory.createControleur(1).getRuleset().get(0);
				//new Regle(new Observation("? .#? .#"), Direction.BAS);

		new RulesBuilderONERULEONLY(r);
	}
}
