package li260.ihm.observer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import li260.circuit.Circuit;
import li260.tools.Tools;

public class ControleurObserver implements UpdateEventListener{	
	private ArrayList<ObserveurImage> list;	
	private BufferedImage image;

	public ControleurObserver(Circuit track){ // INITIALISATION	IHMImage
		list = new ArrayList<ObserveurImage>();	
		image = Tools.imageFromCircuit(track);	
	}

	public void manageUpdate() { // MISE A JOUR	
		for(ObserveurImage o:list)	
			o.print(image);	
	}	
	public BufferedImage getImage(){
		return image;
	}	
	public void add(ObserveurImage obj){
		list.add(obj);
	}
}

