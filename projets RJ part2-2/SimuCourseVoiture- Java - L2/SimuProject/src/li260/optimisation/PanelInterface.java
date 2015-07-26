package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import swing.IHMSwing;


import li260.exception.VoitureException;
import li260.strategy.Strategy;

public class PanelInterface extends JPanel{
	private PanneauCom commande;
	private JPanel ihmPanel;
	private String nom;
	private ArrayList<Strategy> listStrategy;
	private Dimension dim;
	//private IHMSwing ihm;
	
	public PanelInterface(Controleur cont, IHMSwing ihm, int width, int height){
		super();
		listStrategy = new ArrayList<Strategy>();
		//this.ihm = ihm;
		commande = new PanneauCom();
		ihmPanel = ihm;
		if(height<800){
			this.setBackground(new Color(72, 60, 50));
			dim = new Dimension(width+500, 800);
		}
		else{
			dim = new Dimension(width+500, height);
		}
		ihmPanel.setPreferredSize(new Dimension(width, height));
		ihmPanel.setBackground(new Color(72, 60, 50));
		this.setLayout(new BorderLayout());
		this.add(commande, BorderLayout.EAST);
		this.add(ihmPanel, BorderLayout.CENTER);
		this.setPreferredSize(dim);
		commande.setActionCommand();
		commande.addActionListener(cont);
		//System.out.println(commande);
		//System.out.println(ihmPanel);
		
	}
	public void update(IHMSwing ihm){
		
	}
	public JPanel getIHMPanel() {
		return ihmPanel;
	}
	
	public PanneauCom getCommande(){
		return commande;
	}

}
