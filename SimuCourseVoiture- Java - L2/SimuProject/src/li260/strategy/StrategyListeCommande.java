package li260.strategy;

import java.util.ArrayList;
import li260.tools.Commande;


public class StrategyListeCommande implements Strategy {	
private ArrayList<Commande> liste;	
private int index;	
  	public StrategyListeCommande(ArrayList<Commande> liste) {	
  	 	this.liste = liste;	
  	 	index =0;	
  	}	
  	public Commande getCommande() {	
  	if(liste.size()>index)	
  	 	return liste.get(index++);	
  	return null;	
  	}	
}

