package ObjetSim;

import Bonus.Bonus;
import li260.geometrie.Vecteur;

public interface Brick {
	public int getSolidite();
	public void setSolidite(int solidite);
	public Vecteur getPosition();
	public boolean surBordVertical(Vecteur v, int taille);
	public boolean surBordHorizontal(Vecteur v, int taille);
	public boolean is();
	public boolean isBonus();
	public boolean isIncassable();
	public void setABonus();
	public void setBonus(Bonus bonus);
	public void setPosEtDim(Vecteur vecteur, int widthB, int heightB);
}
