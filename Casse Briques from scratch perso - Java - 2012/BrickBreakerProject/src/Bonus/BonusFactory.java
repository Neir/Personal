package Bonus;

import ObjetSim.Brick;
import Plateau.Plateau;

public class BonusFactory {
	private double vitesse = 2;
	private int height = 20;
	private int width = 20;
	
	public Bonus buildWidth(Brick br, Plateau p){
		return new WidthBonus(br.getPosition(), vitesse, width, height, p);
	}
	
//	public Bonus buildMulti(Plateau p){
//		return new MultiBall(vitesse, width, height, p);
//	}
//	
//	public Bonus buildExtra(Plateau p){
//		return new ExtraLife(vitesse, width, height, p);
//	}
}
