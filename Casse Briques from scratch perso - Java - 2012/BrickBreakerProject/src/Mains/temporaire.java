package Mains;

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

import zOldies.PanelButton;
import Controler.AbstractControler;


// Remplacer les 2 fenetres par des panneaux.
public class temporaire extends JFrame {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//private JLabel title;
		//private JPanel PanCont;
		public AbstractControler cont;
		private JPanel panPlay;
		private JPanel panExit;
		
//		public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new byte[]{0}), new Point(1, 1), "Blank cursor");
		
		public temporaire(){
			super();
//			this.cont = cont;
			
			//panPlay.addMouseMotionListener(this);
			panPlay = new PanelButton("Play");
			panPlay.setVisible(false);
			panExit = new PanelButton("Exit");
			panExit.setVisible(false);
			panPlay.setBackground(Color.pink);
			
			this.setBackground(Color.green);
			this.setLayout(new GridLayout(2, 1));
			//this.getContentPane().add(new PanelButton(""));
			this.getContentPane().add(panExit); // .getContentPane() !?
			this.getContentPane().add(panPlay);
//			this.getContentPane().add(new JPanel());
			
			this.setPreferredSize(new Dimension(484, 507));
			
			//this.setLocationRelativeTo(null);
			this.setLocation(new Point(600,100));
			
			//this.setCursor(BLANK_CURSOR);
			
			/*Affiche la fenetre*/
			this.setTitle("Menu");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			//this.add(main);
			this.setVisible(true);
		}
		
		public static void main(String[] args){
			JFrame fen = new temporaire();
			fen.pack();
		}
}
