package swing;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import li260.ihm.observer.ObserveurImage;
import li260.ihm.observer.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener{
private ArrayList<ObserveurSWING> list;
	public void manageUpdate() {	
	   	paintImmediately(getBounds());	
	}	
			
	public IHMSwing() {
		super();
		list = new ArrayList<ObserveurSWING>();	
		
	}

	public void paint(Graphics g){	
	   	super.paint(g);	
	   	for(ObserveurSWING o:list)	
	   	 	o.print(g);	
	}
	
	public void add(ObserveurSWING o){
		list.add(o);
	}
	
}

