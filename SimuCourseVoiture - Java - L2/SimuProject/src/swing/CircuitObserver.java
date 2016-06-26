package swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import li260.circuit.Circuit;
import li260.tools.Tools;


public class CircuitObserver implements ObserveurSWING {
	private BufferedImage image;
	
	public CircuitObserver(Circuit track) {
		super();
		 image = Tools.imageFromCircuit(track);
	}
	
	public void print(Graphics g) {
		
		g.drawImage(image, 0, 0, null);
		
	}	


}
