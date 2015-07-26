package pobj.algogen;


public interface Individu {
	double fitness();
	void setFitness(double fitness);
	Object getValeurPropre();
	void setValeurPropre(Object valeurPropre);
	void muter(double ratio);
	Individu croiser(Individu autre);
	int compareTo(Individu i1);
	String affichage(Environnement envEv);
	Individu clone();
	void getIdentifier();
}
