package pobj.algogen;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import pobj.strategy.IEvolution;
import pobj.util.MyArrayList;

/**
 * Classe de cr�ation de Population, sans limite de nombre d'individu.
 */
public class Population implements Iterable<Individu> {
	/** tableau stokant les Individus de la Population */
	private MyArrayList<Individu> individus;
	private IEvolution evol;

	/**
	 * Construit la Population avec aucun Individu 
	 */
	public Population(IEvolution evol){
		individus = new MyArrayList<Individu>();
		this.evol = evol;
	}

	public MyArrayList<Individu> getIndividus() {
		return individus;
	}

	public void setIndividus(MyArrayList<Individu> individus) {
		this.individus = individus;
	}

	/**
	 * Accède au nombre d'Individus de la Population
	 * @return nombre d'Individus de la Population
	 */
	public int size(){
		return individus.size();
	}

	/**
	 * Ajoute un Individu à la Population
	 * @param le nouvel Individu
	 */
	public void add(Individu individu){
		individus.add(individu);
	}
	
	public Population evoluer(Environnement cible){
		Population pop = reproduire(0.2);
		pop.evaluer(cible);
		return pop;
	}

	public void evaluer(Environnement cible){
		Comparator<Individu> c = new ComparatorIndividu();
		//Impossible a faire fonctionner
//		for(Iterator<Individu> it = individus.iterator();it.hasNext();){
//			System.out.println(it.next().getClass());
//			 ((Individu) it).setFitness(cible.eval((Individu) it));
//			 it.next();
//		}
		Individu ind;
		for(int i=0; i<individus.size(); i++){
			ind = individus.get(i);
			ind.setFitness(cible.eval(ind));
		}
		Collections.sort(individus, c);
	}

	public Population reproduire(){
		
		int taillePop = individus.size();
		// creation de la liste contenant les 20 premiers % de individus
		int nbMeilleursInd= (20 * taillePop) / 100;						//nb d'individus representant les 20%   
		MyArrayList<Individu> meilleursInd = new MyArrayList<Individu>();	//liste contenant les meilleurs individus de individus (les premiers 20%)
		/*
		int nbIndivNvlListe = 0;
		for (int i = taillePop - 1; i>0; i--){
			if(nbIndivNvlListe < nbMeilleursInd){
				meilleursInd.add(individus.get(i));
				nbIndivNvlListe++;
			}
		}
		*/
		
		//Selection des 20% meilleurs (les premiers vu que la liste est tri�e)
		for (int i = 0 ; i < nbMeilleursInd ; i++){
			meilleursInd.add(individus.get(i));
		}
		
		Random alea=new Random();	
		Population nvlePop = new Population(evol);				// liste finale que l'on va remplir  avec les croisements et les mutations
		nvlePop.setIndividus(meilleursInd);
		
		//croisement
		//*
		int alea1;
		int alea2;
		for (int i = 0; i < (80*taillePop)/100; i++){
			alea1 = alea.nextInt(meilleursInd.size());
			alea2 = alea.nextInt(meilleursInd.size());
			
			//boucle pour �viter le croisement d'un individu avec lui m�me
			while (alea1 == alea2){ //tant que les 2 index al�atoire sont identiques, on donne une nouvelle valeur � alea2.
				alea2 = alea.nextInt(meilleursInd.size());
			}
			nvlePop.add(meilleursInd.get(alea1).croiser(meilleursInd.get(alea2)));
		}
		//*/
		
		//mutation
		//*
		for (int i = 0; i < nvlePop.size(); i++){
			if(alea.nextInt(100)<10) //passe ici dans 10% des cas.
				nvlePop.individus.get(i).muter(0.2);
		}
		//*/
		return nvlePop;
	}

	public Population reproduire(double ratio){
		return evol.reproduire(this, ratio);
	}

	public void removeLast(){
		individus.remove(individus.get(individus.size()-1));
	}

	/**
	 * Décrit chaque Individu de la Population
	 * @return la déscription des Individus de la Population
	 */
	public String toString(){	
		String chaine = "";
		for(Individu ind : individus)
			chaine += ind.toString();
		return chaine;
	}
	
	public String affichage(Environnement envEv){	
		String chaine = "";
		for(Individu ind : individus)
			chaine += ind.affichage(envEv);
		return chaine;
	}

	@Override
	public Iterator<Individu> iterator() {
		// TODO Auto-generated method stub
		return individus.iterator();
	}

	public Individu get(int i) {
		// TODO Auto-generated method stub
		return individus.get(i);
	}
	
	public double getSommeFitnesses(){
		double res=0;
		for(Individu ind: individus)
			res+=ind.fitness();
		return res;
	}
}