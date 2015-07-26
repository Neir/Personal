package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class PanneauChoix extends JPanel{
	private JLabel label;
	private JRadioButton algo;
	private JRadioButton manu;
	private ButtonGroup group;
	//private JRadioButtonMenuItem menu;
	private JPanel radio;
	private JPanel espace[];
	public PanneauChoix(){
		label = new JLabel("Choix de la conduite :");
		algo = new JRadioButton("ordinateur", true);
		manu = new JRadioButton("utilisateur");
		group = new ButtonGroup();
		radio = new JPanel();	

		espace = new JPanel[2];
		for(int i=0; i<2; i++){
			espace[i] = new JPanel();
			espace[i].setPreferredSize(new Dimension(105, 60));
			espace[i].setBackground(new Color(75, 60, 50));
		}

		label.setFont(new Font(null, Font.PLAIN, 30));
		label.setForeground(Color.white);

		algo.setMnemonic(KeyEvent.VK_O);
		manu.setMnemonic(KeyEvent.VK_U);
		
		manu.setFont(new Font(null, Font.PLAIN, 20));
		algo.setFont(new Font(null, Font.PLAIN, 20));

		group.add(algo);
		group.add(manu);
		
		this.setLayout(new BorderLayout());
		radio.setLayout(new BorderLayout());
		radio.add(algo, BorderLayout.NORTH);
		radio.add(manu, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(450, 100));
		this.add(label, BorderLayout.NORTH);
		this.add(radio, BorderLayout.CENTER);
		this.add(espace[0], BorderLayout.EAST);
		this.add(espace[1], BorderLayout.WEST);
		this.setBackground(new Color(72, 60, 50));
		radio.setBackground(new Color(72, 60, 50));
		algo.setBackground(new Color(72, 60, 50));
		manu.setBackground(new Color(72, 60, 50));
		algo.setForeground(Color.white);
		manu.setForeground(Color.white);
	}
	
	public void setActionCommand(){
		algo.setActionCommand("algo");
		manu.setActionCommand("manu");
	}
	
	public void addActionListener(Controleur cont){
		algo.addActionListener(cont);
		manu.addActionListener(cont);
	}
	

	public void enable(boolean b){
		algo.setEnabled(b);
		manu.setEnabled(b);
	}

}
