package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swing.IHMSwing;

import li260.exception.VoitureException;
import li260.mains.TestCircuit;
import li260.simulation.Simulation;
import li260.tools.Initialisation;

public class PanneauBox extends JPanel { 

	private JComboBox liste;
    private JLabel label;
    private String[] tab = { "1_safe", "2_safe", "3_safe", "4_safe", "5_safe", "6_safe", "7_safe", "8_safe", "8_safemod", "aufeu", "bond_safe", "Een2", "labymod", "labyperso", "perso", "t2009", "t260_safe"};
    //private Initialisation init;
    
	public PanneauBox(){
		super();
		label = new JLabel("Liste des circuits: ");
		liste = new JComboBox(tab);
		
		liste.setSelectedIndex(0);
		label.setFont(new Font(null, Font.PLAIN, 30));
		label.setForeground(Color.white);
		this.setBackground(new Color(72, 60, 50)); 
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(liste, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(450, 65));
	}

	
	public void setActionCommand(){
		liste.setActionCommand("circuit");
	}
	public void addActionListener(Controleur cont){
		liste.addActionListener(cont);
	}

	public void enable(boolean b){
		liste.setEnabled(b);
	}
}

