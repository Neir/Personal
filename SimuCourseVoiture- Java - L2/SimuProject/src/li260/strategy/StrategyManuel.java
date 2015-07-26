package li260.strategy;

import li260.circuit.Circuit;
import li260.radar.Radar;
import li260.tools.Commande;
import li260.voiture.Voiture;

public class StrategyManuel implements Strategy {
	private StrategyRadar stratStd;
	private Circuit c;
	private Voiture v;
	private static int cpt = 0;
	
	public StrategyManuel(Voiture v, Radar r) {		
	super();
	stratStd = new StrategyRadar(v,r);
	this.v = v;
	}

	public Commande getCommande() {
		cpt++;
		if (cpt <= 3)
			return new Commande(0, 1);
		else if(cpt == 4)
			return new Commande(0, 0.9270);
		else if(cpt <= 40)
			return new Commande(1, 0);
		else
			return stratStd.getCommande();
	}

}
