package li260.ihm.observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import swing.ObserveurSWING;

import li260.voiture.Voiture;

public class VoitureObserver implements ObserveurImage, ObserveurSWING{	
	private Voiture voiture;	
	private Color color = Color.yellow; 	
	private Color colorDerape = Color.red;
	private BufferedImage carIM;

	public VoitureObserver(Voiture voiture){
		this.voiture = voiture;
	}

	public int getX(){ 
		return (int) voiture.getPosition().getX();
	}	
	public int getY(){ 
		return (int) voiture.getPosition().getY();
	}	

	public Color getColor() {	
		if(voiture.getDerapage()) 
			return colorDerape;	
		return color;	
	}	

	public void print(BufferedImage im) {	
		
		im.setRGB(getX(), getY(), getColor().getRGB());	
	}

	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawRect(getX(), getY(), 2,2);
	}
}
