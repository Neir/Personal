package Tools;

import Vue.LabelMonBouton;

public class LabelMBTools {
	public static LabelMonBouton labelFromText(String txt){
		if(txt=="Play") return LabelMonBouton.PLAY;
		if(txt=="Multi Player") return LabelMonBouton.MULT;
		if(txt=="Setting") return LabelMonBouton.OPT;
		if(txt=="Exit") return LabelMonBouton.EXIT;
		return null;
	}
	
	public static String textFromLabel(LabelMonBouton lmb){
		switch(lmb){
		case PLAY : return "Play";
		case MULT : return "Multi Player";
		case OPT : return "Setting";
		case EXIT : return "Exit";
		default : return null;
		}
	}
	
}
