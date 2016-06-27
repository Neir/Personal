package Observer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Bonus.Bonus;

public class BonusObserver implements ObserveurSWING {
	private ArrayList<Bonus> bonusList;
	
	public BonusObserver(){
		bonusList = new ArrayList<Bonus>();
	}
	
	public void add(Bonus b){
		bonusList.add(b);
	}
	
	public void remove(Bonus b){
		bonusList.remove(b);
	}

	public void print(Graphics g) {
		for(Bonus b : bonusList){
			if(b.isActivate()){
				try {
					Image img = ImageIO.read(new File("ball2.png"));
					g.drawImage(img, (int)b.getPosition().getX(), (int)b.getPosition().getY(), b.getWidth(), b.getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
}

}
