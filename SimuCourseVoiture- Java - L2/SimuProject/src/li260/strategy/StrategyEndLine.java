package li260.strategy;

import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.geometrie.Vecteur;
import li260.radar.Radar;
import li260.radar.RadarClassique;
import li260.tools.Commande;
import li260.tools.Tools;
import li260.voiture.Voiture;

public class StrategyEndLine implements Strategy {
	
	private Radar r, rDijk;
	private double[] tabTheta;
	private Voiture car;
	private Circuit track;
	private int bestIndex=Integer.MAX_VALUE;
	private double bestTheta;
	
	public StrategyEndLine(Radar r, Radar rDijk, Voiture car, Circuit track) {
		super();
		this.r = r;
		this.rDijk = rDijk;
		this.car = car;
		this.track=track;
	}

	@Override
	public Commande getCommande() {
		scores();
		double angle = bestTheta;
		Commande com = new Commande(1, angle);
		double turnAbs = Math.min( Math.abs(com.getTurn()), car.getMaxTurnSansDerapage());
		//return new Commande(com.getAcc(), turnAbs*Math.signum(com.getTurn()) );
		return new Commande(1,0);
	}
	
	public int calcEndLine(double theta){
		int cpt=0;
		Vecteur pos = car.getPosition().clonage();
		Vecteur dir = car.getDirection().clonage();
		pos.normalise();
		dir.rotation(theta);
		while(Tools.isRunnable(track.getTerrain(pos)) && (track.getTerrain(pos)!=Terrain.EndLine)){
			cpt++;
			pos.add(dir.fact(RadarClassique.eps));
		}
		return cpt;
	}
	
	public void scores(){
		tabTheta=r.getThetas();
		double[] scoreDijk=rDijk.getScores();
		for(int i=0; i<tabTheta.length; i++){
			if(bestIndex>calcEndLine(tabTheta[i])&&(scoreDijk[i]==0)){
				bestIndex = calcEndLine(tabTheta[i]);
				bestTheta = tabTheta[i];//System.out.println(scoreDijk[i]);
			}
		
		}
	}

}
