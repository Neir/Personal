package li260.ihm.observer;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface ObserveurImage extends Observeur {
	public int getX();	
	public int getY();
	public Color getColor();
	public void print(BufferedImage im);
}
