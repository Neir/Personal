package li260.strategy;

import li260.tools.Commande;

public class StrategyArriere implements Strategy{

	@Override
	public Commande getCommande() {
		// TODO Auto-generated method stub
		return new Commande(-0.15, 0);
	}

}
