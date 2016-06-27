package zOldies;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PanelButton extends JPanel implements MouseListener{
	private String str;
	private boolean surbrillance = false;
	private boolean selected = false;
	
	public PanelButton(String str){
		this.str = str;
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		Font font = new Font("Courier", Font.BOLD, 20);
		g.setFont(font);
		if(surbrillance)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
	    g.drawString(str, this.getWidth()/2 - font.getSize()/10*str.length(), this.getHeight()/2 - font.getSize());
	}

	public void mouseClicked(MouseEvent arg0) {		
	}

	public void mouseEntered(MouseEvent arg0) {
		surbrillance = true;
		this.repaint();
	}

	public void mouseExited(MouseEvent arg0) {
		surbrillance = false;
		this.repaint();
	}

	public void mousePressed(MouseEvent arg0) {		
		
	}

	public void mouseReleased(MouseEvent arg0) {
		selected = true;
	}
	
}
