package Tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import Jeu.Brique;
import ObjetSim.Brick;
import ObjetSim.BrickImpl;
import ObjetSim.BrickKollider;
import Sons.Sound;

// vide, basic, dure, TDure, XTDure, ghost, incassable, bonus
public class Tools {
	public static Brique briqueFromChar(char c) {
		switch(c){
		case '0' : return Brique.vide;
		case '*' : return Brique.basic;
		case '2' : return Brique.dure;
		case '3' : return Brique.TDure;
		case '4' : return Brique.XTDure;
		case 'g' : return Brique.ghost;
		case 'X' : return Brique.incassable;
		case '?' : return Brique.bonus;
		default : return null;
		}		
	}
	
	public static Color colorFromBrick(Brick b){
		switch(b.getSolidite()){
		case 0 : return null;
		case 1 : return Color.CYAN;
		case 2 : return Color.BLUE;
		case 3 : return Color.ORANGE;
		case 4 : return Color.RED;
		case 100 : return Color.BLACK;
		case 999 : return Color.GREEN;
		default : return null;
		}	
	}
	
	public static Brick BrickFromBrique(Brique b){
		switch(b){
		case vide : return new BrickImpl(0);
		case basic : return new BrickImpl(1);
		case dure : return new BrickImpl(2);
		case TDure : return new BrickImpl(3);
		case XTDure : return new BrickImpl(4);
		case ghost : return new BrickImpl(0);
		case incassable : return new BrickImpl(100);
		case bonus : return new BrickImpl(999);
		default : return null;
		}
	}
	
	public static Brick BrickFromBriqueKollider(Brique b){
		switch(b){
		case vide : return new BrickKollider(0);
		case basic : return new BrickKollider(1);
		case dure : return new BrickKollider(2);
		case TDure : return new BrickKollider(3);
		case XTDure : return new BrickKollider(4);
		case ghost : return new BrickKollider(0);
		case incassable : return new BrickKollider(100);
		case bonus : 
			BrickKollider bk = new BrickKollider(1);
			bk.setABonus();
			return bk;
		default : return null;
		}
	}
	
	public static void PlayASound(String filename){
		Sound player = new Sound("son/"+filename+".wav");
		InputStream stream = new ByteArrayInputStream(player.getSamples());
//		player.play(stream);
		player.setSrc(stream);

		Thread th = new Thread(player);
		th.start();
	}
	
}
