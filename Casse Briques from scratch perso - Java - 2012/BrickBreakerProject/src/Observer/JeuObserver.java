package Observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jeu.Jeu;
import ObjetSim.Brick;
import Tools.Tools;

public class JeuObserver implements ObserveurSWING{
	private Jeu j;
	private Brick[][] mat;

	public JeuObserver(Jeu j){
		this.j = j;
		mat = j.getMat();
	}

	public void print(Graphics g) {
		Brick b;
		int nbL = mat.length;
		int nbC = mat[0].length;
		
		for(int i = 0; i < nbL ; i++)
			for(int z = 0; z < nbC ; z++){
				b = mat[i][z];
				g.setColor(Tools.colorFromBrick(b));
				if(b.is())
					//g.drawRect((int)b.getPosition().getX(), (int)b.getPosition().getY(), j.getWidthB(), j.getHeightB());
				try {
					if(b.isBonus()){
						Image img = ImageIO.read(new File("brickBonus.png"));
						g.drawImage(img, (int)b.getPosition().getX(), (int)b.getPosition().getY(), (int) (j.getWidthB()*1.3), j.getHeightB()*2, null);
					
					} else {
						Image img = ImageIO.read(new File("brickBlue.png"));
						g.drawImage(img, (int)b.getPosition().getX(), (int)b.getPosition().getY(), (int) (j.getWidthB()*1.3), j.getHeightB()*2, null);
					}
					
					//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}
