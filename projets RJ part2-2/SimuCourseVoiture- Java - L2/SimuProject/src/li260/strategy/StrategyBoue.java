package li260.strategy;

import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.radar.Radar;
import li260.tools.Commande;
import li260.voiture.Voiture;

public class StrategyBoue implements Strategy {
	private StrategyRadar stratStd;
	private Circuit c;
	private Voiture v;
	
	public StrategyBoue(Circuit c, Voiture v, Radar r){
		super();
		stratStd = new StrategyRadar(v,r);
		this.v = v;
		this.c = c;
	}
	
	public Commande getCommande() {
		double rot = stratStd.getCommande().getTurn();
		if(c.getTerrain(v.getPosition())==Terrain.Boue && v.getVitesse()> 0.4)
			return new Commande(-1, rot);
		else
			return stratStd.getCommande();
	}

}
