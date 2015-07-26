package li260.voiture;

import java.util.ArrayList;

import li260.exception.VoitureException;
import li260.geometrie.Vecteur;
import li260.tools.Commande;

public class VoitureImpl implements Voiture {
	private double vmax ;
	private double alpha_c ;
	private double braquage ;
	private double alpha_f;
	private double beta_f;
	private double alpha_derapage ;
	//private ArrayList<Vecteur> listPos;
	private double masse;
	private double vitesse_sortie_derapage ;
	private Vecteur position = null;
	private Vecteur direction = null;
	private double[] tabVitesse 	= {0.1,	0.2, 0.3, 0.4, 0.5,	0.6, 0.7, 0.8,	0.9, 1.}; 
	private double[] tabTurn 	= {1., 1., 0.8, 0.7, 0.6, 0.4, 0.3, 0.2, 0.1, 0.075};

	private double vitesse; // vitesse initiale

	public VoitureImpl(double vmax, double braquage, double alpha_c, double alpha_f, double beta_f, double alpha_derapage,double vitesse, Vecteur position,
			Vecteur direction,double vitesse_sortie_derapage) {
		super();
		this.vmax = vmax;
		this.alpha_c = alpha_c;
		this.braquage = braquage;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
		this.alpha_derapage = alpha_derapage;
		this.vitesse_sortie_derapage = vitesse_sortie_derapage;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		//listPos = new ArrayList<Vecteur>();
	}

	
	public double getMaxTurnSansDerapage() {
		int i = 0;
		while(vitesse>tabVitesse[i]*vmax) i++;
		return tabTurn[i];
	}

	@Override
	public double getVitesse() {
		// TODO Auto-generated method stub
		return vitesse;
	}

	@Override
	public Vecteur getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Vecteur getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	@Override
	public boolean getDerapage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBraquage() {
		// TODO Auto-generated method stub
		return braquage;
	}

//	public ArrayList<Vecteur> getListPos(){
//		return listPos;
//	}
	
	public void driveSansDerapage(Commande c) {
		// approche normale
		// 1) gestion du volant
		direction.rotation(c.getTurn() * braquage);

		// 2.1) gestion des frottements

		vitesse -= alpha_f;
		vitesse -= beta_f*vitesse;

		// 2.2) gestion de l'acceleration/freinage

		vitesse += c.getAcc() * alpha_c;

		// 2.3) garanties, bornes...
		direction=direction.normalise();

		vitesse = Math.max(0., vitesse); // pas de vitesse négative
		vitesse = Math.min(vmax, vitesse);

		// 3) mise à jour de la position

		position.add(direction.fact(vitesse));
	}

	@Override
	public void drive(Commande c) throws VoitureException {
		// TODO Auto-generated method stub
	//	listPos.add(position);
		driveSansDerapage(c);
	}


}
