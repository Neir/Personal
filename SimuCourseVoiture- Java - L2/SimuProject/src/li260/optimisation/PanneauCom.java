package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import li260.exception.VoitureException;

public class PanneauCom extends JPanel{
	private PanneauBox listeCircuit;
	private PanneauCheck listeStrategy;
	private PanneauControle listeControle;
	private PanneauBoutons listeBoutons;
	private PanneauChoix listeChoix;
	
	private JPanel pCir, pStr, pCont, pBout, pChoix, com;
	private JPanel espace[];
	private JPanel vide[];
	public PanneauCom(){
		listeCircuit = new PanneauBox();
		listeBoutons = new PanneauBoutons();
		listeStrategy = new PanneauCheck();
		listeControle = new PanneauControle();
		listeChoix = new PanneauChoix();
		
		espace = new JPanel[2];
		for(int i=0; i<2; i++){espace[i] = new JPanel();
			espace[i].setPreferredSize(new Dimension(500, 20));	
			espace[i].setBackground(new Color(72, 60, 50));
		}
				
		vide = new JPanel[4];
		for(int i=0; i<4; i++){
			vide[i] = new JPanel();
			vide[i].setPreferredSize(new Dimension(500, 50));	
			vide[i].setBackground(new Color(72, 60, 50));		
		}
		pCir = new JPanel();
		pCir.add(listeCircuit);
		pStr = new JPanel();
		pStr.add(listeStrategy);
		pCont = new JPanel();
		pCont.add(listeControle);
		pBout = new JPanel();	
		pBout.add(listeBoutons);
		pChoix = new JPanel();
		pChoix.add(listeChoix);
				
		com = new JPanel();
		com.add(espace[0]);
		com.add(pCir);
		com.add(vide[0]);
		com.add(pChoix);
		com.add(vide[1]);
		com.add(pStr);
		com.add(vide[2]);
		com.add(pCont);
		com.add(vide[3]);
		com.add(pBout);
		com.add(espace[1]);

		this.setLayout(new BorderLayout());
		this.add(com, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(500, 800));
		
		this.setBackground(new Color(72, 60, 50));
		com.setBackground(new Color(72, 60, 50));
		pCir.setBackground(new Color(72, 60, 50));
		pStr.setBackground(new Color(72, 60, 50));
		pCont.setBackground(new Color(72, 60, 50));
		pBout.setBackground(new Color(72, 60, 50));
		pChoix.setBackground(new Color(72, 60, 50));
		
	}
	
	public void setActionCommand() {
		listeCircuit.setActionCommand();
		listeStrategy.setActionCommand();
		listeControle.setActionCommand();
		listeBoutons.setActionCommand();
		listeChoix.setActionCommand();
	}

	public void addActionListener(Controleur cont){
		System.out.println("actionListener");
		listeCircuit.addActionListener(cont);
		listeStrategy.addActionListener(cont);
		listeControle.addActionListener(cont);
		listeBoutons.addActionListener(cont);
		listeChoix.addActionListener(cont);
		
	}

	public void enable(String com) {
		if(com.equals("circuit")){
			listeBoutons.restart();
			listeBoutons.enable(true);
		}
		
		if(com.equals("arret")){
			listeCircuit.enable(true);
			listeChoix.enable(true);
			listeStrategy.enable(true);
			listeBoutons.enable(true);
			listeControle.enable(false);
		}
		
		if(com.equals("lecture")){
			listeCircuit.enable(false);
			listeChoix.enable(false);
			listeStrategy.enable(false);
		}
		
		if(com.equals("manu")){
			listeStrategy.enable(false);
			listeControle.enable(true);
		}
		
		if(com.equals("algo")){
			listeStrategy.enable(true);
			listeControle.enable(false);
		}
	}

}
