package li260.strategy;

import java.util.ArrayList;
import li260.selector.Selector;
import li260.tools.Commande;

public class StrategySelector implements Strategy{// C’est une stratégie!	
	private ArrayList<Strategy> liStrategy; // Tout abstrait !	
	private ArrayList<Selector> liSelect;
	public StrategySelector(){	
		liStrategy = new ArrayList<Strategy>();	
		liSelect = new ArrayList<Selector>();	
	}	
	// Plutot sécurisé: on est sur qu'il y a concordance	
	public void add(Strategy str, Selector select){	
		liStrategy.add(str); liSelect.add(select);	
	}
	
	public void remove(Strategy str, Selector select){
		liStrategy.remove(str); liSelect.remove(select);
	}
	
	/*
	public Radar getRadar(){
		for(int i=0; i<liStrategy.size(); i++){	
			if(liSelect.get(i).isSelected())	
				return liStrategy.get(i).getRadar();	
			
		}
		
	}
	*/
	
	public void setPriority(Strategy str, Selector select, int priority){
		if(contains(str, select)){
			if(priority==0){ // Radar
				remove(str, select);
				add(str, select);
			}
			if(priority==1){ // Endline
				int indStr=liStrategy.indexOf(str);
				int indSel=liSelect.indexOf(select);
				Strategy sy=liStrategy.remove(indStr);	
				Selector sr=liSelect.remove(indSel);
				liStrategy.add(0, str);
				liSelect.add(0, select);
			}
		}
	}
	
	public Commande getCommande() {	
		for(int i=0; i<liStrategy.size(); i++){	
			if(liSelect.get(i).isSelected()){	
				//System.out.println("STRATEGYSELECTOR."+liStrategy.get(i)+"  "+i);
				return liStrategy.get(i).getCommande();	
			}
		}	
		// normalement on ne passe pas ici...	
		return liStrategy.get(liStrategy.size()-1).getCommande();	
	}
	public boolean contains(Strategy str, Selector select) {
		if((liStrategy.contains(str)) && liSelect.contains(select))
			return true;
		return false;
	}	
	
	public String toString(){
		String str="Strategy :";
		String str1="Selector :";
		for(int i=0; i<liStrategy.size(); i++){
			str += liStrategy.get(i)+" ";
		}	
		for(int i=0; i<liSelect.size(); i++){
			str1 += liSelect.get(i)+" ";
		}
		return (str+"\n"+str1+"\n");
	}
	
	public void clear(){
		liStrategy.clear();
		liSelect.clear();
	}
}
