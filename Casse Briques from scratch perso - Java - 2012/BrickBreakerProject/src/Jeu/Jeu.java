package Jeu;

import ObjetSim.Brick;

public interface Jeu {
	public Brick getBrick(int x, int y);
	public Brick[][] getMat();
	public int getWidthB();
	public int getHeightB();
	public boolean gagne();
}
