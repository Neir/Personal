package Bonus;

import Plateau.Plateau;
import li260.geometrie.Vecteur;

public class WidthBonus implements Bonus {
	private double vitesse;
	private Vecteur position;
	private int width;
	private int height;
	private boolean active = false;
	private Plateau plateau;
	private boolean bonusCaught = false;
	
	public WidthBonus(Vecteur position, double vitesse, int width, int height, Plateau plateau){
		this.position = position;
		this.vitesse = vitesse;
		this.width = width;
		this.height = height;
		this.plateau = plateau;
	}

	@Override
	public double getVitesse() {
		// TODO Auto-generated method stub
		return vitesse;
	}

	@Override
	public Vecteur getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Vecteur position) {
		this.position = position;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	
	public boolean bonusCaught(){
		return bonusCaught;
	}

	public void run() {
		while(position.getY()+height<507){
			active = true;
			position.setY(position.getY()+1);
			if(position.getX()+width>plateau.getPosition().getX()&&
					position.getX()<plateau.getPosition().getX()+plateau.getWidth()&&
					position.getY()+height==plateau.getPosition().getY()){
				bonusCaught = true;
				break;
			}
			else
				try {
					Thread.sleep((long) (20/vitesse));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		active = false;
	}

	public boolean isActivate() {
		return active;
	}

	public void end() {
		active = false;
		bonusCaught = false;
	}
	
}
