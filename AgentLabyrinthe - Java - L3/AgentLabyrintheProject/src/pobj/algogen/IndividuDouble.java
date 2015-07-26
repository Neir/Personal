package pobj.algogen;

import java.util.Random;



/**
 * Classe de création d'un Individu
 */
public class IndividuDouble extends AbstractIndividu{
	/** Valeur propre de l'invividu */
	private double valeurPropre;
	/** fitness de l'individu */
	private double fitness = 0;

	/**
	 * Construit un individu de valeur propre aléatoire
	 */
	public IndividuDouble(){
		Random r = new Random();
		valeurPropre=r.nextDouble();
	}

	/**
	 * Construit un individu de valeur propre spécifiée
	 * @param valeur propre initiale de l'individu
	 */
	public IndividuDouble(double valeurPropre){
		this.valeurPropre=valeurPropre;
	}

	/**
	 * Accède à la fitness de l'individu
	 * @return fitness de l'individu
	 */
	public double fitness(){
		return fitness;
	}

	/**
	 * Redéfinit la fitness de l'individu
	 * @param nouvelle fitness
	 */
	public void setFitness(double fitness){
		this.fitness = fitness;
	}

	/**
	 * Accède à la valeur propre de l'individu
	 * @return valeur propre de l'individu
	 */
	public Object getValeurPropre(){
		return valeurPropre;
	}

	/**
	 * Redéfinit la valeur propre de l'individu
	 * @param nouvelle valeur propre
	 */
	public void setValeurPropre(Object valeurPropre){
		this.valeurPropre =  (Double) valeurPropre;
	}

	/**
	 * Décrit l'individu
	 * @return la chaine de caractère décrivant l'individu
	 */
	public String toString(){
			return "\nIndividu : valeurPropre = "+valeurPropre+" / fitness : "+fitness;
	}
	
	public void muter(double ratio){
		Random r = new Random();
		/** val-10% + 20%*alea */
		double valPtmp = valeurPropre;
		valeurPropre = valeurPropre - valeurPropre*ratio/2 + valeurPropre*ratio*r.nextDouble();
		if(valeurPropre>1)
			valeurPropre = valPtmp - valPtmp/ratio*r.nextDouble();
	}
	
	public Individu croiser(Individu autre){
		//* Version 2
		Random r = new Random();
		double vPInd = (Double) getValeurPropre();
		double vPAutre = (Double) autre.getValeurPropre();
		double nouvVP;
		
		if(vPInd < vPAutre)
			nouvVP = r.nextDouble()*(vPAutre-vPInd)+vPInd;
		else
			nouvVP = r.nextDouble()*(vPInd-vPAutre)+vPAutre;
		
		return new IndividuDouble(nouvVP);
		//*/
		
		/* Version 1
		double moy = (getValeurPropre()+autre.getValeurPropre())/2;
		return new Individu(moy);
		//*/
	}

	@Override
	public String affichage(Environnement env) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individu clone() {
		// TODO Auto-generated method stub
		return new IndividuDouble(valeurPropre);
	}
	
	public void getIdentifier(){
		System.out.println("C'est un double");
	}
}
