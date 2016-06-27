package Observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Balle.Balle;

public class BalleObserver implements ObserveurSWING{
	private Balle b;
	
	public BalleObserver(Balle b) {
		super();
		this.b = b;
	}

	public void print2(Graphics g) {
		g.setColor(Color.blue);
		g.drawOval((int)b.getPosition().getX(), (int)b.getPosition().getY(), b.getTaille(), b.getTaille()); // posX, posY, larg, long
	}
	
	public void print(Graphics g){ // NOM A CHANGER !
		
//		try {
//			BufferedImage img = ImageIO.read(new File("F:/img2.gif"));
//			JFrame frame = new JFrame("Demo load image");
//			frame.getContentPane().add(new JLabel(new ImageIcon(img)));
//			frame.pack();
//			frame.setVisible(true);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			Image img = ImageIO.read(new File("ball2.png"));
			g.drawImage(img, (int)b.getPosition().getX(), (int)b.getPosition().getY(), b.getTaille()*2, b.getTaille()*2, null);
			//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  

}
