package li260.strategy;

import li260.tools.Commande;

public class StrategyGauche implements Strategy{
	public Commande getCommande(){
		return new Commande(0, -1);
	}
}
