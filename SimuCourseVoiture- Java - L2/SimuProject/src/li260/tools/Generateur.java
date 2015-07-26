package li260.tools;

public class Generateur {

	private static double acc, rot;



	public static double[] calcThetas(int nbFaisceaux){
		double[] thetas = new double[nbFaisceaux+1];
		for(int i=0; i<=nbFaisceaux; i++){
			thetas[i] = (Math.PI/nbFaisceaux*i) - Math.PI/2;
		}
		return thetas;

	}


	public static Commande[] calcComm(double[] thetas, double vitesse) {
        double nbFaisceaux = thetas.length - 1;
        double rot;
		Commande[] allCom = new Commande[thetas.length];
		//*
		for (int i=0; i<=nbFaisceaux; i++) {
			rot = (2*i/nbFaisceaux)- 1;
			if (Math.abs(thetas[i]) >= Math.PI / 2) {
				allCom[i] = new Commande(-1 , rot);
			} else if (Math.abs(thetas[i]) > Math.PI / 4) {
				allCom[i] = new Commande(-0.8 , rot);
			} else if (Math.abs(thetas[i]) > Math.PI / 6) {
				allCom[i] = new Commande( -0.5 , rot);
			} else if (Math.abs(thetas[i]) > Math.PI / 9) {
				allCom[i] = new Commande( -0.2 , rot);
			} else {
				allCom[i] = new Commande( 1 , rot);
			}
		}
		//*/
/*
		for (int i = 0; i <=nbFaisceaux; i++) {

			if (Math.abs(thetas[i]) >= Math.PI / 2) {
				allCom[i] = new Commande(((-1/vitesse)<1) ? -1 : -1/vitesse , Math.signum(thetas[i])
						* (1-Math.cos(thetas[i])));
			} else {
				if (Math.abs(thetas[i]) > Math.PI / 4) {
					allCom[i] = new Commande(((-1/vitesse)<1) ? -1 : -1/vitesse, Math.signum(thetas[i])
							* Math.cos(thetas[i]));
				} else {
					if (Math.abs(thetas[i]) > Math.PI / 6) {
						allCom[i] = new Commande(1, Math.signum(thetas[i])
								* Math.cos(thetas[i]));
					} else {
						if (Math.abs(thetas[i]) > Math.PI / 9) {
							allCom[i] = new Commande(1, Math.signum(thetas[i]));
									//* Math.cos(thetas[i]));
						} else {
							if (Math.abs(thetas[i]) > Math.PI / 20) {
								allCom[i] = new Commande(1, Math.signum(thetas[i]));
										//* Math.cos(thetas[i]));
							} else {
								allCom[i] = new Commande(1, Math.signum(thetas[i]));
										//* Math.cos(thetas[i]));
							}
						}
					}
				}
			}
		}//*/
		allCom[(int) (nbFaisceaux/2)] = new Commande(1, 0);
		return allCom;
	}
	/*
	public static Commande[] calcCommande(double[] thetas){
		Commande[] com = new Commande[thetas.length];
		com[thetas.length/2] = new Commande(1, 0);
		for(int i=0; i<thetas.length; i++){

			rot= (1-Math.cos(thetas[i]));
			if (i<thetas.length/2)	rot = -rot;
			acc= ((Math.cos(thetas[i])) - Math.cos(Math.PI/6));
			if(acc>0) acc = ((Math.cos(thetas[i]))-thetas[i]);

			if(thetas[i]>Math.PI/6 || thetas[i]<-Math.PI/6) {acc = -1;System.out.println("acc = -1");}
			if((thetas[i]>=Math.PI/18 && thetas[i]<=Math.PI/6)||(thetas[i]<=-Math.PI/18 && thetas[i]>=-Math.PI/6)) {acc = -0.5;System.out.println("acc = -0.5");}
			else acc = 1;


			depassement();
			System.out.println(acc+"                 "+rot);
			com[i] = new Commande(acc, rot);
		}

		for(int i=thetas.length/2+1; i<thetas.length; i++){  //Faisceaux gauches
			rot= Math.signum(Math.cos(thetas[i]));
			acc= ((Math.cos(thetas[i])) - Math.cos(Math.PI/6));
			if(acc>0) acc = ((Math.cos(thetas[i]))-thetas[i]);

			if(thetas[i]>Math.PI/6 || thetas[i]<-Math.PI/6) {acc = -1;System.out.println("acc = -1");}
			if((thetas[i]>=Math.PI/20 && thetas[i]<=Math.PI/6)||(thetas[i]<=-Math.PI/20 && thetas[i]>=-Math.PI/6)) {acc = -0.5;System.out.println("acc = -0.5");}
			else acc = 1;


			depassement();
			//System.out.println(acc+"                 "+rot);
			com[i] = new Commande(acc, rot);
		}

		com[thetas.length/2] = new Commande(1, 0);
		return com;
	}*/

	private static void depassement(){
		if(rot>1){
			rot=1;
			System.out.println("Rotation trop grande");
		}
		if(rot<-1){
			rot=-1;
			System.out.println("Rotation trop petite");
		}
		if(acc>1){
			acc=1;
			System.out.println("Acceleration trop grande");
		}
		if(acc<-1){
			acc=-1;
			System.out.println("Acceleration trop petite");
		}
	}

}
