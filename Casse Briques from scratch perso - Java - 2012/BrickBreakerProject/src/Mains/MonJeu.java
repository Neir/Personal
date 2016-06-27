package Mains;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import Vue.EcranMenu;

public class MonJeu {

	@SuppressWarnings("deprecation")
	public static void main(String[] args){

//		Fenetre fen = new Fenetre();
//		JPanel pan = new JPanel();
//		//	  fen.setLayout(null); 
//		pan.setBackground(Color.PINK);
//
//		Font f = new Font("Courier", Font.BOLD, 20);
//
//		final JButton but = new MonBouton("CLIQUE MOI !");
//
//		JTextPane txt = new JTextPane();
//
//		txt.setFont(f);
//		//	  fen.add(txt);
//		fen.setContentPane(pan);
//		fen.add(but);
//		fen.pack();
//		//	  fen.add(txt);
		

		String[] str = { "Play", "Multi Player", "Setting", "Exit"};
		
		JFrame fen = new EcranMenu(str);
		
		fen.pack();
	}      
}