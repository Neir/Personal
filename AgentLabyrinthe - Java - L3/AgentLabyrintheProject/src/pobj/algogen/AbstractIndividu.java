package pobj.algogen;

public abstract class AbstractIndividu implements Individu, Comparable<Individu> {
	/** fitness de l'individu */
	protected double fitness = 0;
	
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
	public abstract Object getValeurPropre();

	/**
	 * Redéfinit la valeur propre de l'individu
	 * @param nouvelle valeur propre
	 */
	public abstract void setValeurPropre(Object valeurPropre);

	@Override
	public abstract void muter(double ratio);

	@Override
	public abstract Individu croiser(Individu autre);

	@Override
	public int compareTo(Individu i) {
		return Double.compare(this.fitness, i.fitness());
	}
	
	@Override
	public abstract String affichage(Environnement env);
	
	public abstract Individu clone();

}
