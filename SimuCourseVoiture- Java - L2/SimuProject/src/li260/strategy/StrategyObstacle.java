package li260.strategy;

import li260.radar.Radar;
import li260.tools.Commande;
import li260.voiture.Voiture;

public class StrategyObstacle extends StrategyRadar implements Strategy {


	
	public StrategyObstacle(Voiture car, Radar radar) {
		super(car, radar);
	}

	public Commande getCommande() {
		radar.scores();
		Commande c = allCom[radar.getBestIndex()];
		int orientation;
		double [] tab;
		double turnAbs = Math.min( Math.abs(c.getTurn()), car.getMaxTurnSansDerapage());
		double acc = c.getAcc();
		acc = -1+radar.getToutDroit()/2500;
		orientation=radar.getScores().length-1;
		tab = radar.getScores();
		if(c.getTurn()<0)
			for(int i=0; i<orientation/2; i++){
				if(tab[i] >500)
					c.setTurn(c.getTurn()*2);
			}
		else
			for(int i=orientation/2+1; i<orientation+1; i++){
				if(tab[i] >500)
					c.setTurn(c.getTurn()*2);
			}
		if(car.getVitesse() < 0.1){
			acc = 0.2;
		}
		return new Commande(acc, turnAbs * Math.signum(c.getTurn()));
	}
	
}

