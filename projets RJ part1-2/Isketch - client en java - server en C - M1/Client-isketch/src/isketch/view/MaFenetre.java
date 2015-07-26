package isketch.view;


import isketch.clientThread.ClientThread;
import isketch.interfaces.Observable;
import isketch.interfaces.Observateur;
import isketch.objets.Commande;
import isketch.objets.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public  class MaFenetre extends JFrame implements Observable {
	protected ClientThread cliThread;

	private JMenuBar menuBar = new JMenuBar();
	JMenu   fichier = new JMenu("Fichier"),
			connexion = new JMenu("Connexion");
	
	JMenuItem   nouveau = new JMenuItem("Effacer"),
			quitter = new JMenuItem("Quitter"),
			connect = new JMenuItem("se connecter"),
			disconnect = new JMenuItem("se deconnecter");

	private ButtonGroup choixCouleur;
	private JRadioButton rouge, rose, jaune, vert, bleu, gris, noir, blanc;
	private Font f;
	private JPanel choixCouleurPanel = new JPanel();
	private GridLayout layout;
	
	private CouleurListener cListener = new CouleurListener();
	
	// La zone de dessin
	private JPanel drawerPanel = new JPanel();
	private DrawPanel drawPanel = new DrawPanel();
	
	// La zone de chat
	private ChatPanel chatPanel;
	
	private JSlider epaisseurSlider;
	
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public MaFenetre(ClientThread cliThread){
		this.cliThread = cliThread;
		
		f = new Font("Comic Sans MS", Font.BOLD, 20);
		
		init_JFrame();
		drawerPanel.setLayout(new BorderLayout());
		drawerPanel.add(drawPanel, BorderLayout.CENTER);
	    this.add(drawerPanel, BorderLayout.CENTER);
	    initMenu();
		init_colorPanel();
		init_epaisseurSlider();

		layout = new GridLayout(2,1);
		choixCouleurPanel.setLayout(layout);
		
		epaisseurSlider.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				drawPanel.setPointerSize(((JSlider) e.getSource()).getValue());
			}
		});
		
		JPanel panSliderAndColor = new JPanel();
		
		panSliderAndColor.setLayout(new BorderLayout());
		panSliderAndColor.add(choixCouleurPanel, BorderLayout.CENTER);
		panSliderAndColor.add(epaisseurSlider, BorderLayout.EAST);

//		choixCouleurPanel.setVisible(true);
		drawerPanel.add(panSliderAndColor, BorderLayout.SOUTH);
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Rival", 2)); users.add(new User("Connard", 13)); users.add(new User("Gagne tout -_-\"", 999));
		chatPanel = new ChatPanel("Pseudo", users);
		chatPanel.setPreferredSize(new Dimension(400, chatPanel.getHeight()));
		this.add(chatPanel, BorderLayout.WEST);
		
		// Ajout de l'observateur pour que chaque nouvelle action soit envoyée instantanément au serveur
		getDrawPanel().addObservateur(cliThread);
		getChatPanel().addObservateur(cliThread);
		
		this.setRoleDessinateur(false);
		this.addObservateur(cliThread);
	}

	public MaFenetre(){
		new MaFenetre(new ClientThread());
	}
	
	
	private void init_epaisseurSlider() {
		epaisseurSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		epaisseurSlider.setMajorTickSpacing(10);
		epaisseurSlider.setPaintTicks(true);
		epaisseurSlider.setPaintLabels(true);
	}


	
	
	private void init_JFrame() {
		Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		Dimension dim = new Dimension(1000, 600);
		centrer(dim);
		this.setPreferredSize(dim);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	private void centrer(Dimension dim) {
		int sysWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int sysHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
	    this.setLocation((int)(sysWidth-dim.getWidth())/2,
	    		(int)(sysHeight-dim.getHeight())/2);
	}




	private void init_colorPanel() {
		
		rouge = new JRadioButton("rouge");
		rose = new JRadioButton("rose");
		jaune = new JRadioButton("jaune");
		vert = new JRadioButton("vert");
		bleu = new JRadioButton("bleu");
		gris = new JRadioButton("gris");
		noir = new JRadioButton("noir");
		blanc = new JRadioButton("blanc");
		
		rouge.setFont(f);
		rose.setFont(f);
		jaune.setFont(f);
		vert.setFont(f);
		bleu.setFont(f);
		gris.setFont(f);
		noir.setFont(f);
		blanc.setFont(f);
		
		rouge.setBackground(Color.red);
		rose.setBackground(Color.pink);
		jaune.setBackground(Color.yellow);
		vert.setBackground(Color.green);
		bleu.setBackground(Color.blue);
		gris.setBackground(Color.gray);
		noir.setBackground(Color.DARK_GRAY);
		blanc.setBackground(Color.white);

		choixCouleur = new ButtonGroup();
		choixCouleur.add(rouge);
		choixCouleur.add(rose);
		choixCouleur.add(jaune);
		choixCouleur.add(vert);
		choixCouleur.add(bleu);
		choixCouleur.add(gris);
		choixCouleur.add(noir);
		choixCouleur.add(blanc);
		
		choixCouleurPanel.add(rouge);
		choixCouleurPanel.add(rose);
		choixCouleurPanel.add(jaune);
		choixCouleurPanel.add(vert);
		choixCouleurPanel.add(bleu);
		choixCouleurPanel.add(gris);
		choixCouleurPanel.add(noir);
		choixCouleurPanel.add(blanc);
		
		rouge.addActionListener(cListener);
		rose.addActionListener(cListener);
		jaune.addActionListener(cListener);
		vert.addActionListener(cListener);
		bleu.addActionListener(cListener);
		gris.addActionListener(cListener);
		noir.addActionListener(cListener);
		blanc.addActionListener(cListener);

		
//		choixCouleurPanel.setVisible(true);
//		this.add(choixCouleurPanel, BorderLayout.SOUTH);
	}
	
	

	//Initialise le menu
	private void initMenu(){
		nouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				drawPanel.erase();
			}      
		});

		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}      
		});
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		fichier.setMnemonic('F');

		connexion.setMnemonic('E');
		if(!cliThread.isConnected()){
			connexion.add(connect);
		}
		
		connect.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				final JTextField tf = new JTextField(10);
				

				JButton go = new JButton("go");

				//f.setPreferredSize(dim);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
				f.setLocation(getLocation());


				go.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						String username = tf.getText();
						cliThread.etablissementDeConnexion();
						commandes.add(new Commande("CONNECT", username));
						chatPanel.setUsername(username);
						connexion.remove(connect);
						connexion.add(disconnect);
						f.dispose();
						updateObservateur();
					}
				});

				f.add(tf, BorderLayout.NORTH);
				f.add(go, BorderLayout.SOUTH);
				f.pack();
			}
		});

		disconnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				cliThread.deconnection();
			}
		});
		
		menuBar.add(fichier);
		menuBar.add(connexion);

		this.setJMenuBar(menuBar);
	}

	public DrawPanel getDrawPanel(){
		return drawPanel;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}    


	public void addObservateur(Observateur obs) {
		listObservateur.add(obs);
	}

	public void updateObservateur() {
		for(Observateur obs : listObservateur )
			obs.update(commandes);
	}

	public void setRoleDessinateur(Boolean b) {
		
		chatPanel.setEnablePerso(!b);
		drawPanel.setEnablePerso(b);
		
		rouge.setEnabled(b);
		rose.setEnabled(b); 
		jaune.setEnabled(b);
		vert.setEnabled(b);
		bleu.setEnabled(b);
		gris.setEnabled(b);
		noir.setEnabled(b);
		blanc.setEnabled(b);
		epaisseurSlider.setEnabled(b);
		
		nouveau.setEnabled(b);
	}


	class CouleurListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().getClass().getName().equals("javax.swing.JRadioButton")){
				if(e.getSource()==rouge) drawPanel.setPointerColor(Color.red);
				else if(e.getSource()==rose)drawPanel.setPointerColor(Color.pink);
				else if(e.getSource()==jaune)drawPanel.setPointerColor(Color.yellow);
				else if(e.getSource()==vert)drawPanel.setPointerColor(Color.green);
				else if(e.getSource()==bleu)drawPanel.setPointerColor(Color.blue);
				else if(e.getSource()==gris)drawPanel.setPointerColor(Color.gray);
				else if(e.getSource()==noir)drawPanel.setPointerColor(Color.black);
				else if(e.getSource()==blanc)drawPanel.setPointerColor(Color.white);
			}
			else{
				drawPanel.setPointerColor(Color.black);        
			}
		}    
	}



	public static void main(String[] args){
		MaFenetre fen = new MaFenetre();
		fen.pack();
	}

}
