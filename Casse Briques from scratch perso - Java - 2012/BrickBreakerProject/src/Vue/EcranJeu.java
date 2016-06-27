package Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;





import zOldies.EcranPartieOld;
import Controler.AbstractControler;
import Observer.MessageCenterObserver;

public class EcranJeu extends JFrame implements KeyListener, MouseMotionListener, MouseListener{
	//private JLabel title;
	private JPanel PPartie;
	private JPanel PMenu;
	public AbstractControler cont;
	
	public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new byte[]{0}), new Point(1, 1), "Blank cursor");
	
	public EcranJeu(AbstractControler cont){
		super();
		this.cont = cont;
		//main.setLayout(new BorderLayout());
		PPartie = new EcranPartieOld(cont);
		this.add(PPartie);
		this.setPreferredSize(new Dimension(484, 507));
		
		//this.setLocationRelativeTo(null);
		this.setLocation(new Point(600,100));
		
		this.setCursor(BLANK_CURSOR);
		
		/*Affiche la fenetre*/
		this.setTitle("Simulation");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.add(main);
		this.setVisible(true);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

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
			//cont.getIhm().add(new MessageCenterObserver());
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
