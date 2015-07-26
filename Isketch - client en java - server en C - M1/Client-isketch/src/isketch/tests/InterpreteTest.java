package isketch.tests;

import isketch.objets.Commande;
import isketch.tools.Tools;

public class InterpreteTest {
	public static void main (String args[]){
		Commande com = Tools.interpretation("/MACOMMANDE/monPremierAttribut/monSecondAttribut/");
		System.out.println(com);
		
		String reStr = Tools.stringFromCommande(com);
		System.out.println(reStr);
		
	}
}
