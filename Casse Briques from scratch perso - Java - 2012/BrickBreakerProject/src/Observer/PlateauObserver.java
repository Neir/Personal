package Observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Plateau.Plateau;

public class PlateauObserver implements ObserveurSWING{
	private Plateau p;
	
	public PlateauObserver(Plateau p){
		this.p = p;
	}
	
	public void print2(Graphics g) {
		g.setColor(Color.RED);
		g.drawRoundRect((int)p.getPosition().getX(), (int)p.getPosition().getY(), p.getWidth(), p.getHeight(), 10, 10);
	}
	
    public void print(Graphics g) {
		try {
			Image img = ImageIO.read(new File("pcenter.png"));
			g.drawImage(img, (int)p.getPosition().getX()+10, (int)p.getPosition().getY(), p.getWidth()-20, p.getHeight(), null);
			img = ImageIO.read(new File("pleft.png"));
			g.drawImage(img, (int)p.getPosition().getX(), (int)p.getPosition().getY(), 10, p.getHeight(), null);
			img = ImageIO.read(new File("pright.png"));
			g.drawImage(img, (int)p.getPosition().getX()+p.getWidth()-10, (int)p.getPosition().getY(), 10, p.getHeight(), null);
			
			//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
