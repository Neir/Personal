package Observer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MessageCenterObserver implements ObserveurSWING {

	public void print(Graphics g) {
		Font font = new Font("Courier", Font.BOLD, 20);
	    g.setColor(Color.black);
	    g.fillRect(0, 253-50, 484, 50);
//	    g.clearRect(0, 253-50, 484, 50);
		g.setFont(font);
	    g.setColor(Color.white);     
	    g.drawString("Pause !", 200, 253+50-70);  
	}
}
