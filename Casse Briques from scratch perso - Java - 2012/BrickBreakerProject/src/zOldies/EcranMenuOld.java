package zOldies;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import li260.geometrie.Rectangle;
import li260.geometrie.Vecteur;
import Controler.AbstractControler;
import Controler.ControlerMenu;
import Model.IHMSwing;
import Observer.MyButtonObserver;

// Remplacer les 2 fenetres par des panneaux.
public class EcranMenuOld extends JFrame implements MouseListener, MouseMotionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public ControlerMenu cont;
		private Rectangle[] items;
		private int nbItem = 3;
		private MyButtonObserver[] obsList;
		
//		public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new byte[]{0}), new Point(1, 1), "Blank cursor");
		
		public EcranMenuOld(){
			super();
			items = new Rectangle[nbItem];
			obsList = new MyButtonObserver[nbItem];
			IHMSwing ihm = new IHMSwing();
			for(int i=0; i<nbItem; i++){
				items[i] = new Rectangle(new Vecteur(150, 151+i*50), new Vecteur(300, 200+i*50));
			}
			obsList[0] = new MyButtonObserver("Play", new Vecteur(200, 200), items[0], Color.blue);
			obsList[1] = new MyButtonObserver("Setting", new Vecteur(200, 250),items[1], Color.blue);
			obsList[2] = new MyButtonObserver("Exit", new Vecteur(200, 300),items[2], Color.blue);
			ihm.add(obsList[0]);
			ihm.add(obsList[1]);
			ihm.add(obsList[2]);
			
			cont = new ControlerMenu(ihm);
			
			this.setContentPane(ihm);
			
			this.setPreferredSize(new Dimension(484, 507));
			
			//this.setLocationRelativeTo(null);
			this.setLocation(new Point(600,100));
			
			//this.setCursor(BLANK_CURSOR);
			
			/*Affiche la fenetre*/
			this.setTitle("Menu");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			//this.add(main);
			this.setVisible(true);
			
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
		}
		
		public static void main(String[] args){
			JFrame fen = new EcranMenuOld();
			fen.pack();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent pos) {
			for(int i=0;i<nbItem;i++){
				if(items[i].isRectangle(new Vecteur(pos.getX(), pos.getY()))){
					obsList[i].setColor(Color.green);
					System.out.println("1..2..Test !");
				}
			}
		}
}

