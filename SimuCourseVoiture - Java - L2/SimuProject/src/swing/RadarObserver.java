package swing;

import java.awt.Color;
import java.awt.Graphics;

import li260.geometrie.Vecteur;
import li260.radar.Radar;
import li260.voiture.Voiture;

public class RadarObserver implements ObserveurSWING {
	
	private Voiture v;
	private Radar r;
	
	
	public RadarObserver(Radar r, Voiture v){
		super();
		this.v = v;
		this.r = r;
	}


	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		r.scores();
		double[] thetas = r.getThetas();
		double[] dist = r.distancesInPixels();
		g.setColor(Color.cyan);
		
		for(int i=0; i<thetas.length; i++){
			//System.out.println(dist[i]);
			Vecteur vect =  v.getDirection().clonage();
			vect.rotation(thetas[i]);
			vect.multScalCourant(dist[i]);
			vect.add(v.getPosition());
			g.drawLine((int) vect.getX(), (int) vect.getY(), (int) v.getPosition().getX(),(int)  v.getPosition().getY());
		}
	}

}
