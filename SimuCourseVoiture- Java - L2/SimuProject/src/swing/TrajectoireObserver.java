package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import li260.geometrie.Vecteur;
import li260.voiture.Voiture;

public class TrajectoireObserver implements ObserveurSWING {
	private Voiture v;
	private ArrayList<Vecteur> pos;
	private ArrayList<Color> color;
	
	public TrajectoireObserver(Voiture v) {
		super();
		pos = new ArrayList<Vecteur>();
		color = new ArrayList<Color>();
		this.v = v;
	}
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		
		
		pos.add(v.getPosition().clonage());
		color.add(new Color(0, 0,(int) Math.abs((v.getVitesse()/0.9*255))));
		
		for(int i=0; i<pos.size(); i++){
			g.setColor(color.get(i));
			g.drawRect((int) pos.get(i).getX(), (int) pos.get(i).getY(), 1,1);
		}
		
	}

}
