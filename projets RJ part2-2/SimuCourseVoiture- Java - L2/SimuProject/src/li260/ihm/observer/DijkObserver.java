package li260.ihm.observer;

import java.awt.Color;
import java.awt.image.BufferedImage;

import li260.algo.Dijkstra;

public class DijkObserver implements ObserveurImage {

	private Dijkstra dijk;
	
	public DijkObserver(Dijkstra d){
		dijk = d;
	}
	
	@Override
	public void print(BufferedImage im) {
		im.setRGB(getX(), getY(), getColor().getRGB());
	}
	
	public int getX(){
		return (int)dijk.getCurrent().getX();
	}
	
	public int getY(){
		return (int)dijk.getCurrent().getY();
	}
		
	public Color getColor(){
		return new Color((int)(dijk.getDist(getX(), getY()) %255.), 0, 0);
	}
}
