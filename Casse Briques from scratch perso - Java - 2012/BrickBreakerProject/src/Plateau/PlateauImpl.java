package Plateau;

import Model.IHMSwing;
import li260.geometrie.Vecteur;


public class PlateauImpl implements Plateau {
	private Vecteur position;
	private double vitesse;
	private static final int WIDTH_MAX = 180;
	private int width;
	private int height;
	
	public PlateauImpl(Vecteur position, int width, int height, double vitesse) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
		this.vitesse = vitesse;
	}

	public Vecteur getPosition() {
		return position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void bouge(int mouv){
		double xp = position.getX();
		//if(xp+width<468&&xp>0)
			
		if(xp+width>=468)
			position.setX(position.getX()-1);
		else if(xp<=0)
			position.setX(position.getX()+1);
		else
			position.setX(position.getX()+mouv*vitesse);
	}
	
	public void bouge(double pos){
			
			if(pos<=width/2+10)
				position.setX(1);
			else if(pos>=IHMSwing.fenWidth-10)
				position.setX(IHMSwing.fenWidth-width-1);
			else
				position.setX(pos-width/2-10);

	}

	public void setWidth(int width) {
		if(width>WIDTH_MAX)
			width = WIDTH_MAX;
		else
			this.width = width;
	}

}
