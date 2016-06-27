package Observer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import li260.geometrie.Rectangle;
import li260.geometrie.Vecteur;

public class MyButtonObserver implements ObserveurSWING {
	private String str;
	private Vecteur position;
	private Color color;
	private Rectangle rec;
	
	public MyButtonObserver(String str, Vecteur position, Rectangle rec, Color color){
		this.str = str;
		this.position = position;
		this.color = color;
		
		this.rec = rec;
	}
	
	public void print(Graphics g){
		g.setColor(Color.pink);
		g.drawRect((int)rec.getV1().getX(), (int)rec.getV1().getY(), rec.getWidth(), rec.getHeight());
		Font font = new Font("Courier", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(color);
	    g.drawString(str, (int)(position.getX() - font.getSize()/10*str.length()), (int) (position.getY() - font.getSize()));
	}

	public void setColor(Color color){
		this.color = color;
	}
}
