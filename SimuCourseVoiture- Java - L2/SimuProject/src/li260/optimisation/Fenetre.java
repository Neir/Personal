package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import li260.exception.VoitureException;
import li260.mains.TestCircuit;
import li260.simulation.Simulation;
import li260.tools.Initialisation;

import swing.IHMSwing;

public class Fenetre extends JFrame{
	private JPanel main;
	private PanneauCom commande;
	private JPanel ihmPanel;

	public Fenetre () throws VoitureException{
		super();
		//this.init = init;
		main = new JPanel();
		commande = new PanneauCom();
		ihmPanel = new JPanel();
		
		main.setLayout(new BorderLayout());
		main.add(commande, BorderLayout.EAST);
	
		JLabel label = new JLabel("Choisir un Circuit");
		label.setFont(new Font(null, Font.BOLD, 30));
		//label.setHorizontalAlignment(5);
		ihmPanel.setLayout(new BorderLayout());
		ihmPanel.add(label);
		main.add(ihmPanel, BorderLayout.CENTER);
		//String name = TestCircuit.nom;//
		
		/*Affiche la fenetre*/
		this.setTitle("Simulation");
		//this.setPreferredSize(new Dimension(900, 850));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(main);
		this.setVisible(true);
		
	}

	public void setActionCommand() {
		commande.setActionCommand();
	}
	public void addActionListener(Controleur cont){
		commande.addActionListener(cont);
	}

	public void maj(PanelInterface pan) {
		this.remove(main);
		main=pan;
		commande=pan.getCommande();
		this.add(main);		
		this.pack();
	}

	public void enable(String com) {
		//System.out.println("fenetre Enable");
		commande.enable(com);
	}

}
