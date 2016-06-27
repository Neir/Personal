package ObjetSim;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import Sons.Sound;
import Tools.Tools;
import Bonus.Bonus;
import Bonus.TypeBonus;
import collision.Algorithms;
import geometry.Point;
import geometry.Segment;
import li260.geometrie.Vecteur;

public class BrickKollider implements Brick {
	private int solidite;
	private Vecteur position;
	private int largeur, hauteur;
	private Segment bordD, bordG, bordH, bordB;
	private boolean aBonus = false;
	private Bonus bonus;
	
	public BrickKollider(int solidite) {
		super();
		this.solidite = solidite;
	}

	public int getSolidite() {
		return solidite;
	}

	public void setSolidite(int solidite) {
		this.solidite = solidite;

		if(solidite<=0)
			Tools.PlayASound("petit_bruit");
		if(aBonus)
			lootBonus();
	}

	private void lootBonus() {
		Thread th = new Thread(bonus);
		th.start();
	}

	public Vecteur getPosition() {
		return position;
	}

	public void setPosEtDim(Vecteur position, int largeur, int hauteur) {
		this.position = position;
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		bordG = new Segment((float)position.getX(), (float)position.getY(), (float)position.getX(), (float)(position.getY()+hauteur));
		bordD = new Segment((float)position.getX()+largeur, (float)position.getY(), (float)position.getX()+largeur, (float)(position.getY()+hauteur));
		bordB = new Segment((float)position.getX(), (float)position.getY()+hauteur, (float)position.getX()+largeur, (float)(position.getY()+hauteur));
		bordH = new Segment((float)position.getX(), (float)position.getY(), (float)position.getX()+largeur, (float)(position.getY()));
	}

	public boolean surBordVertical(Vecteur v, int taille) {
		if(Algorithms.circle2segment(bordG, Point.getInstance((float)v.getX(), (float)v.getY()), (float)taille/2))
			// La balle touche le bord droit !
			return true;
		if(Algorithms.circle2segment(bordD, Point.getInstance((float)v.getX(), (float)v.getY()), (float)taille/2))
			return true;	
		return false;
	}

	public boolean surBordHorizontal(Vecteur v, int taille) {
		if(Algorithms.circle2segment(bordB, Point.getInstance((float)v.getX(), (float)v.getY()), (float)taille/2))
			// La balle touche le bord droit !
			return true;
		if(Algorithms.circle2segment(bordH, Point.getInstance((float)v.getX(), (float)v.getY()), (float)taille/2))
			return true;
		return false;
	}

	public boolean is() {
		return solidite>0;
	}
	
	public void setABonus(){
		aBonus = true;
	}
	
	public void setBonus(Bonus bonus){
		this.bonus = bonus;
	}
	
	public boolean isBonus(){
		return aBonus;
	}
	
	public boolean isIncassable(){
		return solidite>10;
	}
}
