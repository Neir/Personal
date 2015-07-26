package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import li260.simulation.Simulation;

public class PanneauBoutons extends JPanel{

	private static ImageIcon iconeStop = new ImageIcon("icones/stop.png");
	private BoutonPause pause;
	private JButton arret;
	
	public PanneauBoutons(){
		pause = new BoutonPause("Lecture");
		arret = new JButton("Stop", iconeStop);
		arret.setPreferredSize(new Dimension(100, 30));
		
		this.setLayout(new BorderLayout());
		this.add(pause, BorderLayout.EAST);
		this.add(arret, BorderLayout.WEST);
		this.setPreferredSize(new Dimension(200, 30));
		this.setBackground(new Color(72, 60, 50));
	}
	
	public void setActionCommand(){
		arret.setActionCommand("arret");
		pause.setActionCommand("lecture");
	}
	
	public void addActionListener(Controleur cont) {
		pause.addActionListener(cont);
		arret.addActionListener(cont);
	}

	public void enable(boolean b){
		pause.setEnabled(b);
	}
	
	
	public void restart(){
		pause.restart();
	}
}
