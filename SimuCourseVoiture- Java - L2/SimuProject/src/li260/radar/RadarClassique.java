package li260.radar;


import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.geometrie.Vecteur;

import li260.tools.Tools;
import li260.voiture.Voiture;

public class RadarClassique implements Radar{

	protected double[] thetas;
	protected Voiture car;
	protected Circuit track;
	protected double[] scores;
	protected int bestIndex;	
	protected double toutDroit;
	private boolean voitEndLine=false;
	public final static double eps = 0.1;
	
	public RadarClassique(double[] thetas, Voiture car, Circuit track) {
		super();
		this.thetas = thetas;
		this.car = car;
		this.track = track;
	}

	public double[] scores(){	
		scores = new double[thetas.length]; // Attribut de la classe	

		bestIndex = 0; // attribut de la classe	
		for(int i=0; i<thetas.length; i++){	
			scores[i] = calcScore(thetas[i]);	
			if(scores[i]>scores[bestIndex])	
				bestIndex=i;	
		}	
		return scores;	
	}
	@Override
	public double[] distancesInPixels() {
		// TODO Auto-generated method stub
		double[] dist = new double[scores.length];
		for(int i=0; i<dist.length; i++){
			dist[i] = scores[i] * 0.1;
		}
		
		return dist;
	}
	@Override
	public int getBestIndex() {
		// TODO Auto-generated method stub
		return bestIndex;
	}
	@Override
	public double[] getThetas() {
		// TODO Auto-generated method stub
		return thetas;
	}	
	
	public double[] getScores() {
		return scores;
	}
	
	public double getToutDroit() {
		return toutDroit;
	}
	public boolean isVoitEndLine() {
		return voitEndLine;
	}

	public double calcScore(double theta){
		
		double cpt = 0;		
		Vecteur pos = car.getPosition().clonage();
		Vecteur dir = car.getDirection().clonage();
		pos.normalise();
		dir.rotation(theta);
		while(Tools.isRunnable(track.getTerrain(pos)) && (track.getTerrain(pos)!=Terrain.EndLine)){
			cpt++;
			pos.add(dir.fact(eps));
		}
		if(track.getTerrain(pos)==Terrain.EndLine && dir.prodScal(track.getDirectionArrivee())>0) 
			voitEndLine=true;
		if(theta == 0) toutDroit=cpt;
		return cpt;
	}
	
}
