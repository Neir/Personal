package Effet;

import java.util.ArrayList;

import Bonus.Bonus;
import Bonus.TypeBonus;
import Bonus.WidthBonus;

public class EffectsImpl {
	private ArrayList<Bonus> bonList;
	
	public EffectsImpl(){
		bonList = new ArrayList<Bonus>();
	}
	
	public void isActivate(TypeBonus type){
		switch(type){
//		case MULTI_BALL : bonList.add(new MultiBall());
//		case WIDTH_BONUS : bonList.add(new WidthBonus());
//		case EXTRA_LIFE : bonList.add(new ExtraLife());
		}
		
	}
	
	public void add(Bonus bonus){
		bonList.add(bonus);
	}
	
	public void remove(Bonus bonus){
		bonList.remove(bonus);
	}

	public ArrayList<Bonus> getBonList() {
		return bonList;
	}

	public void setBonList(ArrayList<Bonus> bonList) {
		this.bonList = bonList;
	}
}
