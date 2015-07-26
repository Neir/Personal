package li260.voiture;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;

public class FerrariFactory implements VoitureFactory{
	private double vmax = 0.9;
	private double alpha_c = 0.02;
	private double braquage = 0.2;
	private double alpha_f= 0.001;
	private double beta_f= 0.002;
	private double alpha_derapage = 0.01;
	private double masse = 1;
	private double vitesse_sortie_derapage = 0.6;
	private Vecteur position = null;
	private Vecteur direction = null;
	private double vitesse = 0.; // vitesse initiale

	public FerrariFactory(Circuit track) {	
		super();	
		position = track.getPointDepart();	
		direction = track.getDirectionDepart();	
	}	

	public Voiture build() {	
		return new VoitureImpl(vmax, braquage, alpha_c, alpha_f, beta_f, alpha_derapage, vitesse, position, direction, vitesse_sortie_derapage);	
	}	

}
