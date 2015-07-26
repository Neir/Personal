package li260.radar;

import java.util.Comparator;

import li260.algo.Dijkstra;
import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.geometrie.Vecteur;
import li260.tools.Tools;
import li260.voiture.Voiture;

public class RadarDijkstra extends RadarClassique {
	private Dijkstra d;
	
	public RadarDijkstra(double[] thetas, Voiture car, Circuit track, Dijkstra d) {
		super(thetas, car, track);
		this.d = d;
	}


	public double calcScore(double theta){
		double min;	
		double cpt = 0;
		Vecteur pos = car.getPosition().clonage();
		Vecteur dir = car.getDirection().clonage();
		double p;
		pos.normalise();
		dir.rotation(theta);
		min = Double.POSITIVE_INFINITY;
		while(Tools.isRunnable(track.getTerrain(pos))){
			if(track.getTerrain(pos) == Terrain.EndLine){
				p = dir.prodScal(track.getDirectionArrivee(), dir);
				if(p<0){
					min = Double.POSITIVE_INFINITY;
					break;
				}
			}
			
			cpt++;
			
			pos.add(dir.fact(eps));	
			
			if(d.getDist((int)pos.getX(),(int)pos.getY()) < min){				
				min = d.getDist((int)pos.getX(),(int)pos.getY());
			}
			if(track.getTerrain(pos)==Terrain.Boue)
				min *= 2;
		}

		if(theta == 0) toutDroit=cpt;
		return -min;
	}

}
