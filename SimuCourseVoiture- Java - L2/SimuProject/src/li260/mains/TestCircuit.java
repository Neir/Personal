package li260.mains;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JPanel;

import swing.IHMSwing;
import li260.exception.VoitureException;
import li260.optimisation.Controleur;
import li260.optimisation.Fenetre;
import li260.optimisation.PanelInterface;
import li260.simulation.Simulation;
import li260.tools.Initialisation;

public class TestCircuit {
	//public static String nom = "8_safe";

	public static void main(String[] args) throws VoitureException, IOException{
		
		//String nom="1_safe";
/*
		IHMSwing ihm;
		Simulation sim;
		Initialisation init=new Initialisation(nom);
		ihm = init.getIhm();

		sim = init.getSim();

		Fenetre fen = new Fenetre(init);
		//*/
		//*
		Fenetre fen = new Fenetre();
		Controleur controle = new Controleur(fen);
		//ihm.setPreferredSize(new Dimension(1024, 1024));
		//fen.setContentPane(controle.getIhm());
		fen.setVisible(true);
		fen.pack();
//*/

	}


}
