package Bonus;

import li260.geometrie.Vecteur;

public interface Bonus extends Runnable{
	public double getVitesse();
	public Vecteur getPosition();
	public int getWidth();
	public int getHeight();
	public boolean isActivate();
	public boolean bonusCaught();
	public void end();

	public void run();
}
