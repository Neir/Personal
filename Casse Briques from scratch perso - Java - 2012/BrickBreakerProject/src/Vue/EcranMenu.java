package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Tools.FactoryMain;


@SuppressWarnings("serial")
public class EcranMenu extends JFrame{
	
	private String[] str;
	private int nb_button;
	private MonBouton[] buttons;
	
	public EcranMenu(String[] str){
		this.str = str;
		this.nb_button = str.length;
		buttons = new MonBouton[nb_button];
		
		JPanel pan = new JPanel(){
			public void paintComponent(Graphics g){

				try {
					Image img = ImageIO.read(new File("fond-BB.jpg"));
//					fenWidth = img.getWidth(this);
//					fenHeight = img.getHeight(this);
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
		};
		pan.setBackground(Color.CYAN);
		this.setContentPane(pan);
		
		this.setLayout(new GridLayout(nb_button+6, 1));
		
		this.setPreferredSize(new Dimension(484, 507));
		//this.setLocationRelativeTo(null);
		this.setLocation(new Point(600,100));
		
		//this.setCursor(BLANK_CURSOR);
		
		/*Affiche la fenetre*/
		this.setTitle("Menu");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.add(main);
		this.setVisible(true);
		
		init();
		
		addMesBoutonsActionListener();
	}

	private void init() {
		this.add(new MonBouton(" ")); this.add(new MonBouton(" ")); this.add(new MonBouton(" ")); // MOCHE ! ! ! !
		for(int i = 0 ; i < nb_button ; i++){
			buttons[i] = new MonBouton(str[i]);
			this.add(buttons[i]);
		}
	}
	
	private void addMesBoutonsActionListener() {
		for(MonBouton mb : buttons){
			switch(mb.getlabelMB()){
			case PLAY : 
				mb.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {	
						// Ouvre la fenètre de jeu, lance la simulation
						// (Méthode contenant test1)
						FactoryMain.launchEcranJeu();
					}
				});
				break;
			case MULT : 
				mb.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {	
						// Non définie pour le moment
					}
				});
				break;
			case OPT : 
				mb.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {	
						// Ouvre la fenètre des options (non définie)
					}
				});
				break;
			case EXIT : 
				mb.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {	
						dispose();
					}
				});
				break;
			default : return;
			}
		}
	}
	
}
