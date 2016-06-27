package ObjetSim;

import Bonus.Bonus;
import li260.geometrie.Vecteur;

public class BrickImpl implements Brick {
	private int solidite;
	private Vecteur position;
	private int largeur, hauteur;
	private boolean bonus = false;
	
	public BrickImpl(int solidite){
		this.solidite = solidite;
	}
	
	public Vecteur getPosition() {
		return position;
	}

	public void setPosEtDim(Vecteur position, int largeur, int hauteur) {
		this.position = position;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public int getSolidite() {
		return solidite;
	}

	public void setSolidite(int solidite) {
		this.solidite = solidite;
	}
	
	public boolean surBordVertical(Vecteur v, int taille){
		if (v.getX()+taille+1==position.getX()&&
				v.getY()<=position.getY()+hauteur&&
				v.getY()+taille>=position.getY()){
			return true;}
		if (v.getX()-1==position.getX()+largeur&&
				v.getY()<=position.getY()+hauteur&&
				v.getY()+taille>=position.getY()){
			return true;}
		return false;
	}
	
	public boolean surBordHorizontal(Vecteur v, int taille){
		if (v.getY()+1==position.getY()+hauteur&&
				v.getX()+taille>=position.getX()&&
				v.getX()<=position.getX()+largeur) {
			return true;}
		if (v.getY()+taille-1==position.getY()&&
				v.getX()+taille>=position.getX()&&
				v.getX()<=position.getX()+largeur){
			return true;}
		return false;
	}

	public boolean is() {
		return solidite>0;
	}
	
	public void setBonus(){
		bonus = true;
	}
	
	public boolean isBonus(){
		return bonus;
	}
	
	public boolean isIncassable(){
		return solidite>10;
	}

	@Override
	public void setABonus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBonus(Bonus bonus) {
		// TODO Auto-generated method stub
		
	}
}
