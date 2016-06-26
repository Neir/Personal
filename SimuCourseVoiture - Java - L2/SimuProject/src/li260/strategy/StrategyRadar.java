package li260.strategy;

import li260.radar.Radar;
import li260.tools.Commande;
import li260.tools.Generateur;
import li260.voiture.Voiture;

public class StrategyRadar implements Strategy{	
	protected Radar radar;	
	protected Voiture car;
	private final double fact = 1;
	
	protected Commande[] allCom;
	
	/*
		protected Commande[] allCom = {
	 	new Commande(1,0.), 		//0
		new Commande(-0.5,-1), 		// -PI/3
		new Commande(-0.3,-0.5), 	// -PI/4
		new Commande(0.,-0.25), 	// -PI/6
		new Commande(0.5, -0.15), 	// -PI/8
		new Commande(0.75, -0.05), 	// -PI/16
		new Commande(-0.5, 1), 		// PI/3
		new Commande(-0.3,0.5), 	// PI/4
		new Commande(0.,0.25), 		// PI/6
		new Commande(0.5, 0.15), 	// PI/8
		new Commande(0.75, 0.05)};	// PI/16
//*/
/*
	protected Commande[] allCom = {
			new Commande(1,0.), 			// 0
			new Commande(-0.6*fact%1, -1),   	// -PI/2
			new Commande(-0.4*fact%1,-0.75),	// -PI/3
			new Commande(-0.2*fact%1,-0.5),	// -PI/4
			new Commande(-0.1*fact%1,-0.25),	// -PI/6
			new Commande(0.4*fact%1, -0.15),	// -PI/8
			new Commande(0.75*fact%1, -0.05),	// -PI/16
			new Commande(-0.6*fact%1, 1),   	// -PI/2
			new Commande(-0.4*fact%1, 0.75),	// PI/3
			new Commande(-0.2*fact%1,0.5),	// PI/4
			new Commande(-0.1*fact%1,0.25),	// PI/6
			new Commande(0.4*fact%1, 0.15),	// PI/8
			new Commande(0.75*fact%1, 0.05)	// PI/16
			};

//*/	
	public StrategyRadar(Voiture car, Radar radar) {	
		super();	
		this.car = car; 
		this.radar = radar;	
		allCom = Generateur.calcComm(radar.getThetas(), car.getVitesse());
	}
	@Override
	public Commande getCommande() {
		// TODO Auto-generated method stub
		radar.scores();
		Commande c = allCom[radar.getBestIndex()];
		double turnAbs = Math.min( Math.abs(c.getTurn()), car.getMaxTurnSansDerapage());

		return new Commande(c.getAcc(), turnAbs * Math.signum(c.getTurn()));
		}
	
	public Radar getRadar() {
		return radar;
	}
	
	public Voiture getCar() {
		return car;
	}	
}
