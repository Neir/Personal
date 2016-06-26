package li260.voiture;

import java.util.ArrayList;

import li260.exception.VoitureException;
import li260.geometrie.Vecteur;
import li260.tools.Commande;


public interface Voiture {
	// pour le pilotage
	public void drive(Commande c) throws VoitureException;
	public double getMaxTurnSansDerapage();

	// pour l'observation
	public double getVitesse();
	public Vecteur getPosition();
	public Vecteur getDirection();
	public boolean getDerapage();
	public double getBraquage();
	//public ArrayList<Vecteur> getListPos();
}



