package zOldies;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.lwjgl.input.Mouse;

import Controler.AbstractControler;
import Observer.MessageCenterObserver;

public class EcranPartieOld extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	public AbstractControler cont;
	private JPanel centerMsg;
	
	public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new byte[]{0}), new Point(1, 1), "Blank cursor");
	
	public EcranPartieOld(AbstractControler cont){
		super();
		this.cont = cont;
		//main.setLayout(new BorderLayout());
		
		this.setCursor(BLANK_CURSOR);
		
//		useCenterMessage();
		
//		cont.getIhm().add(new MessageCenterObserver());
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

//	private void useCenterMessage(){
//		centerMsg = new JPanel(){
//			public void paintComponent(Graphics g){               
//			    Font font = new Font("Courier", Font.BOLD, 20);
//			    g.setFont(font);
//			    g.setColor(Color.red);         
//			    g.drawString("Tiens ! Le Site du Zéro !", 10, 20);               
//			  }     
//		};
//		centerMsg.setBackground(Color.black);
//		centerMsg.setSize(484, 100);
//		centerMsg.setLocation(0, 253-50);
//		
//		this.add(centerMsg);
//	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :
			cont.setMouv(-1);
			break;
		case KeyEvent.VK_RIGHT:
			cont.setMouv(1);
			break;
		case KeyEvent.VK_PAUSE:
			cont.setPause();
			break;
		case KeyEvent.VK_SPACE:
			cont.setPause();
			break;
		}
	}


	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_LEFT||key==KeyEvent.VK_RIGHT)
			cont.setMouv(0);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		Point pos = e.getPoint();
		cont.setPos(pos.getX());
	}

	public void mouseClicked(MouseEvent e) {
		cont.setStart(true);
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}