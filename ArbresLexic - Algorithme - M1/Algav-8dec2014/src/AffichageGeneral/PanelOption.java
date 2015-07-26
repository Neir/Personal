package AffichageGeneral;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelOption extends JPanel {
	
	private JLabel NbMotRes, TempsSuppr, NbNilRes, HauteurRes, ProfondeurMoyenneRes,TempsRes, TempsLastRes ; 
	private JLabel NbMot, TempsSupprRes, NbNil, Hauteur, ProfondeurMoyenne,Temps, TempsLast ; 
	private GridBagConstraints gbc ;
	
	public PanelOption(int lar, int haut){
		init();
		placement();
	}
	
	public void init(){
		NbMot = new JLabel("Nombre de mot");

		NbNil = new JLabel("Nombre de nil");
		
		Hauteur = new JLabel("Hauteur de l'arbre");
		
		Temps = new JLabel("Temps construction de l'arbre") ;
		
		TempsLast = new JLabel("Temps dernier ajout de l'arbre") ;
		
		ProfondeurMoyenne = new JLabel("Profondeur moyenne de l'arbre") ;
		
		TempsSuppr = new JLabel("Temps de suppression du dernier mot");
		
		
		NbMotRes = new JLabel("0");
		TempsSupprRes = new JLabel("0");
		NbNilRes = new JLabel("0");
		HauteurRes = new JLabel("0");
		ProfondeurMoyenneRes = new JLabel("0");
		TempsRes = new JLabel("0");
		TempsLastRes = new JLabel("0");
		
	}
	
	
	public void placement(){
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
			
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLUE),"Statistiques de l'arbre affiché", 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
		setLayout(new GridBagLayout());
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START ;
		gbc.insets = new Insets(10,00,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(NbMot, gbc);
	
		gbc.gridy = 1;
		add(NbNil, gbc);

		gbc.gridy = 2;
		add(Hauteur, gbc);	
		
		gbc.gridy = 3;
		add(ProfondeurMoyenne, gbc);
		
		gbc.gridy = 4;
		add(Temps, gbc);
		
		gbc.gridy = 5;
		add(TempsLast, gbc);
		
		gbc.gridy = 6;
		add(TempsSuppr, gbc);
		
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START ;
		
		gbc.insets = new Insets(10,50,0,0);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(NbMotRes, gbc);

		
		gbc.gridy = 1;
		add(NbNilRes, gbc);

		
		gbc.gridy = 2;
		add(HauteurRes, gbc);	
		
		
		gbc.gridy = 3;
		add(ProfondeurMoyenneRes, gbc);
		
		gbc.gridy = 4;
		add(TempsRes, gbc);
		
		gbc.gridy = 5;
		add(TempsLastRes, gbc);
		
		gbc.gridy = 6;
		add(TempsSupprRes, gbc);
	}
	
	public void SetStat(int NBM, int NBN, int H, int PM, long temps, long tempslast, long tempsSuppr){
		NbMotRes.setText(Integer.toString(NBM));
		NbNilRes.setText(Integer.toString(NBN));
		HauteurRes.setText(Integer.toString(H));
		ProfondeurMoyenneRes.setText(Integer.toString(PM));
		if(temps>1000000) TempsRes.setText(Long.toString(temps/1000000)+"ms");
		else TempsRes.setText(Long.toString(temps)+"ns");
		TempsLastRes.setText(Long.toString(tempslast)+"ns");
		TempsSupprRes.setText(Long.toString(tempsSuppr)+"ns");
	}
}
