package Model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Observer.ObserveurSWING;
import Observer.Event.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener{
	private ArrayList<ObserveurSWING> list;
	public static double fenWidth;
	public static double fenHeight;

	public IHMSwing() {
		super();
		list = new ArrayList<ObserveurSWING>();
	}
	
	public ArrayList<ObserveurSWING> getList() {
		return list;
	}

	public void manageUpdate() {	
	   	paintImmediately(getBounds());	
	}
	
	public void paintComponent(Graphics g){

		try {
			Image img = ImageIO.read(new File("fond-BB.jpg"));
			fenWidth = img.getWidth(this);
			fenHeight = img.getHeight(this);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

