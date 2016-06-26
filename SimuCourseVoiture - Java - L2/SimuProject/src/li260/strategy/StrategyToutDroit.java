package li260.strategy;

import li260.tools.Commande;

public class StrategyToutDroit implements Strategy{	
  	public Commande getCommande() {	
		System.out.println("STRTOUTDROIT.avant");
  	 	return new Commande(0.15,0);	
  	}	
}
