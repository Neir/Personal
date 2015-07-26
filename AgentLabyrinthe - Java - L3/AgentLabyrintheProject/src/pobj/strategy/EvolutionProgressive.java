package pobj.strategy;

import java.util.Random;

import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.selecteur.IndivSelecteur;
import pobj.util.MyArrayList;

public class EvolutionProgressive implements IEvolution{
	
	private IndivSelecteur indSelecteur;
	
	public EvolutionProgressive(IndivSelecteur iS){
		indSelecteur=iS;
	}
	
	public Population reproduire(Population pop, double ratio) {
		int taillePop = pop.size();
		// creation de la liste contenant les 20 premiers % de individus
		// nb d'individus representant les 20%   
		int nbMeilleursInd = (20 * taillePop) / 100;			
		//liste contenant les meilleurs individus de individus (les premiers 20%)
		MyArrayList<Individu> meilleursInd = new MyArrayList<Individu>();	

		//Selection des 20% meilleurs (les premiers vu que la liste est triée)
		for (int i = 0 ; i < nbMeilleursInd ; i++){
			meilleursInd.add(pop.get(i));
		}

		Random alea = new Random();	
		// liste finale que l'on va remplir  avec les croisements et les mutations
		Population nvlePop = new Population(this);		
		nvlePop.setIndividus(meilleursInd);

		//croisement
		//*
		Individu pere;
		Individu mere;
		for (int i = 0; i < (80*taillePop)/100; i++){
			pere = indSelecteur.getRandom(pop);
			mere = indSelecteur.getRandom(pop);
			
			// Regle les problèmes de consanguinité qui entrainent une évolution attardée (trop uniforme)
			while(pere.equals(mere)){
				mere = indSelecteur.getRandom(pop);
			}
			nvlePop.add(pere.croiser(mere));
		}
		//*/

		//* 		//mutation
		for (int i = 0; i < nvlePop.size(); i++){
			if(alea.nextInt(100)<10){ //passe ici dans 10% des cas.
				nvlePop.getIndividus().get(i).muter(ratio);
				//System.out.println("L'individu "+i+" a muté");
			}
		}
		//*/
		return nvlePop;
	}
}