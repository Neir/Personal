package Plateau;

import li260.geometrie.Vecteur;

public interface Plateau {
	
	public void bouge(int mouv);
	public void bouge(double pos);
	
	public Vecteur getPosition();
	public int getWidth();
	public int getHeight();
	public void setWidth(int width);
}
