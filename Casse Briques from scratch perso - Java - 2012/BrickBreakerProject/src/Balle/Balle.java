package Balle;

import Jeu.Jeu;
import Plateau.Plateau;
import li260.geometrie.Vecteur;


public interface Balle {
	// pour le pilotage
	public void rebond(Plateau p, Jeu j);

	// pour l'observation
	public double getVitesse();
	public Vecteur getPosition();
	public void setPosX(double x);
	public Vecteur getDirection();
	public int getTaille();
	
	//pour autre chose
	public boolean perdu();
}
