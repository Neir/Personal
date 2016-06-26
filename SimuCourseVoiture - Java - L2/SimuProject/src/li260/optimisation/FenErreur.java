package li260.optimisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenErreur extends JFrame{

	private JLabel lab;
	public FenErreur(int pb){
		super();

		lab = new JLabel();
		
		switch(pb){
		case 1: lab.setText("ERREUR: Le circuit n'a pas ete choisi.");
			break;
		case 2: lab.setText("ERREUR:");
			break;
		default: lab.setText("Erreur inconnue");
			break;
		}

		lab.setFont(new Font(null, Font.PLAIN, 20));
		lab.setForeground(Color.red);
		this.add(lab);
		this.setTitle("ERREUR");
		this.setPreferredSize(new Dimension(370, 100));
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
