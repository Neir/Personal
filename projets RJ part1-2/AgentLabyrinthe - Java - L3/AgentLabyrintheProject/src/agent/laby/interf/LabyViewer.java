package agent.laby.interf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationFactory;
import pobj.algogen.config.AlgoGenParameter;
import pobj.algogen.config.Configuration;
import pobj.algogen.config.Generateur;
import pobj.algogen.selecteur.SelecteurParFitness;
import pobj.algogen.selecteur.SelecteurUniforme;
import pobj.strategy.EvolutionGenerationnelle;
import pobj.strategy.EvolutionProgressive;
import pobj.strategy.IEvolution;
import ruleseditor.Liste.RulesBuilder;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.control.IControleur;
import agent.control.Regle;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

public class LabyViewer extends JFrame{

	private static final long serialVersionUID = 1L;
	private int nbPas = 100, taille = 30, nbRules = 10, nbGen = 5;
	private IEvolution evol;
	private JPanel sidePanel;
	private IControleur controleur;
	private Labyrinthe laby;
	private LabyActivePanel centerPanel;
	private boolean effectuerNewEvo = false;
	private boolean smartSelected = false;
	
	private static final int COLS = 15, LIGNES = 10;

	/**
	 * Constructeur
	 */
	
	public LabyViewer(Labyrinthe lab){
		// Titre de la JFrame
		super("Laby Viewer");
		laby = lab;
		createCenterPanel();
		// Positionner la taille de la fenetre
		setSize(800, 758);
		setResizable(false);
		// traiter le clic sur la croix
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		// rendre visible 
		setVisible(true);
	}
	
	public LabyViewer(IControleur iC, Configuration conf) {
		// Titre de la JFrame
		super("Laby Viewer");
		
		// Construire le maze
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(conf.getParameterValue(AlgoGenParameter.LABY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controleur = iC;
		nbPas = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_STEPS));
		taille = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.TAILLE_POP));
		nbRules = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_RULES));
		nbGen = Integer.parseInt(conf.getParameterValue(AlgoGenParameter.NB_GEN));
		
		evol = Generateur.typeEvolution(conf);
		
		// creer les Panel et menus
		createCenterPanel();
		createSidePanel();
		// Positionner la taille de la fenetre
		setSize(800, 758);
		setResizable(false);
		// traiter le clic sur la croix
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		// rendre visible 
		setVisible(true);
	}

	private void createSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		
		JTextArea instructions = new JTextArea();
		instructions
				.setText("Choisissez les options puis cliquez\n" +
						"sur PLAY pour lancer la simulation !\n");

		instructions.setEditable(false);
		sidePanel.add(instructions);
//*
		ButtonGroup bg = new ButtonGroup();
		JRadioButton evolP = new JRadioButton("Evol Progressive");
		JRadioButton evolG = new JRadioButton("Evol Generationnelle", true);
		bg.add(evolP);
		bg.add(evolG);
		sidePanel.add(evolP);
		sidePanel.add(evolG);
		evolP.addItemListener(new ItemListener(){	public void itemStateChanged(ItemEvent arg0) {
			evol = new EvolutionProgressive(new SelecteurParFitness());
		    effectuerNewEvo = true;
		}});
		evolG.addItemListener(new ItemListener(){	public void itemStateChanged(ItemEvent arg0) {
			evol = new EvolutionGenerationnelle(new SelecteurUniforme());
		    effectuerNewEvo = true;
		}});

		JCheckBox smartCtlrBox = new JCheckBox("smartCtlr");
	    smartCtlrBox.setSelected(smartSelected);
	    //smartCtlrButton.addItemListener(this);
	    sidePanel.add(smartCtlrBox);
	    smartCtlrBox.addActionListener(new StateListenerSmart());
		
		JSlider nbPasSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, nbPas);
		sidePanel.add(nbPasSlider);
		nbPasSlider.setMajorTickSpacing(50);
		nbPasSlider.setMinorTickSpacing(10);
		nbPasSlider.setPaintTicks(true);
		nbPasSlider.setPaintLabels(true);
		nbPasSlider.setBorder(new TitledBorder("Nombre de pas"));
		nbPasSlider.addChangeListener(new ChangeListener (){	public void stateChanged(ChangeEvent e) {
		    nbPas = (int)((JSlider) e.getSource()).getValue();
		}});

		JSlider taillePopSlider = new JSlider(JSlider.HORIZONTAL, 20, 100, taille);
		sidePanel.add(taillePopSlider);
		taillePopSlider.setMajorTickSpacing(20);
		taillePopSlider.setMinorTickSpacing(5);
		taillePopSlider.setPaintTicks(true);
		taillePopSlider.setPaintLabels(true);
		taillePopSlider.setBorder(new TitledBorder("Taille d'une population"));
		taillePopSlider.addChangeListener(new ChangeListener (){	public void stateChanged(ChangeEvent e) {
		    taille = (int)((JSlider) e.getSource()).getValue();
		    effectuerNewEvo = true;
		}});
		
		JSlider nbRulesSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, nbRules);
		sidePanel.add(nbRulesSlider);
		nbRulesSlider.setMajorTickSpacing(10);
		nbRulesSlider.setMinorTickSpacing(5);
		nbRulesSlider.setPaintTicks(true);
		nbRulesSlider.setPaintLabels(true);
		nbRulesSlider.setBorder(new TitledBorder("Nombre de regles par controleur"));
		nbRulesSlider.addChangeListener(new ChangeListener (){	public void stateChanged(ChangeEvent e) {
			nbRules = (int)((JSlider) e.getSource()).getValue();
		    effectuerNewEvo = true;
		}});
		
		JSlider nbGenSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, nbGen);
		sidePanel.add(nbGenSlider);
		nbGenSlider.setMajorTickSpacing(20);
		nbGenSlider.setMinorTickSpacing(5);
		nbGenSlider.setPaintTicks(true);
		nbGenSlider.setPaintLabels(true);
		nbGenSlider.setBorder(new TitledBorder("Nombre de génération"));
		nbGenSlider.addChangeListener(new ChangeListener (){	public void stateChanged(ChangeEvent e) {
		    nbGen = (int)((JSlider) e.getSource()).getValue();
		    effectuerNewEvo = true;
		}});
		//*/
		JButton playButton = new JButton("PLAY");
		sidePanel.add(playButton);
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(effectuerNewEvo){
					evolution();
					effectuerNewEvo = false;
				}
				Labyrinthe labyCopie = laby.clone();
				final Simulation sim = new Simulation(labyCopie, controleur);
				centerPanel.setLaby(labyCopie);
				sim.addObserver(centerPanel);
				new Thread(new Runnable(){
					public void run(){
						sim.mesurePerf(nbPas);
					}
				}).start();
			}
		});
		
		JTextArea instructions2 = new JTextArea();
		instructions2
				.setText("\n\n\n\nPour visualiser, modifier et/ou\nsauvegarder les regles,\ncliquez ici :\n");
		instructions2.setEditable(false);
		sidePanel.add(instructions2);
		
		JButton rulesButton = new JButton("");
		rulesButton.setIcon(new ImageIcon("icones/livre.gif"));
		sidePanel.add(rulesButton);
		
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(effectuerNewEvo){
					evolution();
					effectuerNewEvo = false;
				}
				Labyrinthe labyCopie = laby.clone();
				final Simulation sim = new Simulation(labyCopie, controleur);
				
				centerPanel.setLaby(labyCopie);
				sim.addObserver(centerPanel);
				new Thread(new Runnable(){
					public void run(){
						new RulesBuilder((ArrayList<Regle>) controleur.getRuleset());
					}
				}).start();

			}
		});
		
		getContentPane().add(sidePanel, BorderLayout.EAST);
	}

	private void createCenterPanel() {
		centerPanel= new LabyActivePanel(laby);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
	}
	
	private void evolution(){
		Environnement envEv = PopulationFactory.createEnvironnement(laby, nbPas);
		Population popAlea;

		popAlea = PopulationFactory.createRandomPopulation(taille, nbRules, evol);
		if(smartSelected)
			popAlea.add(new ControleurIndividuAdapter(ControlFactory.createSmartController()));
		popAlea.evaluer(envEv);

		Population popGenSuiv = popAlea.evoluer(envEv);
		//*
		for(int i = 0 ; i < nbGen ; i++){
			popGenSuiv = popGenSuiv.evoluer(envEv);
		}
		//*/
		controleur = (IControleur) popGenSuiv.getIndividus().get(0).getValeurPropre();
		System.out.println(popGenSuiv.getIndividus().get(0).fitness());
	}
	
	class StateListenerSmart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    effectuerNewEvo = true;
			smartSelected = !smartSelected;
		}
	}
}


