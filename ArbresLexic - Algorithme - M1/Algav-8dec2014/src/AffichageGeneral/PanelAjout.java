package AffichageGeneral;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tools.SteppedComboBox;



public class PanelAjout extends JPanel {

	private JCheckBox ajouter , rechercher , supprimer, ListeMot, fusionner, convertir, equilibre ;
	private SteppedComboBox Liste ;
	private JTextField Mot ;
	JPanel Mode;
	private JButton go ;
	private ArrayList<String>LesArbres;
	private GridBagConstraints gbc ;
	int mode=1 ;
	public PanelAjout(){
		MiseEnPlace();
	}
	private void MiseEnPlace() {
		Mode = new JPanel();
		Mode.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		ajouter = new JCheckBox("Ajouter");
		rechercher = new JCheckBox("Rechercher/Prefix");
		supprimer = new JCheckBox("Supprimer");
		ListeMot = new JCheckBox("Lister les mots");
		convertir = new JCheckBox("Convertir");
		equilibre = new JCheckBox("Equilibrer");
		

		fusionner = new JCheckBox("Fusionner avec");
		LesArbres= new ArrayList<String>();
		LesArbres.add("Selectionner arbre");
		Liste = new SteppedComboBox(LesArbres.toArray());
		Dimension d = Liste.getPreferredSize();
		Liste.setPreferredSize(new Dimension(150, d.height));
		Liste.setEnabled(false);


		convertir.addActionListener(new CheckBoxAction());
		ajouter.addActionListener(new CheckBoxAction());
		rechercher.addActionListener(new CheckBoxAction());
		supprimer.addActionListener(new CheckBoxAction());
		ListeMot.addActionListener(new CheckBoxAction());
		equilibre.addActionListener(new CheckBoxAction());
		fusionner.addActionListener(new CheckBoxAction());

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,30,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;

		Mode.add(ajouter,gbc);
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		Mode.add(rechercher,gbc);
		gbc.gridx = 2;
		Mode.add(ListeMot,gbc);
		gbc.gridx = 3;
		gbc.insets = new Insets(0,0,0,30);
		Mode.add(supprimer,gbc);
		ajouter.setSelected(true);


		Mot=new JTextField();
		Mot.setPreferredSize(new Dimension(200, 20));
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		Mode.add(Mot,gbc);

		gbc.gridwidth=1 ;
		gbc.gridheight=1;

		gbc.gridx=1;
		gbc.gridy=2;
		gbc.insets = new Insets(10,0,0,0);
		Mode.add(fusionner, gbc);
		gbc.gridwidth=1 ;
		gbc.gridx=2;

		Mode.add(Liste,gbc);

		gbc.insets = new Insets(10,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		Mode.add(convertir,gbc);
		
		gbc.gridx = 2;
		Mode.add(equilibre, gbc);

		gbc.gridwidth = 2;
		gbc.insets = new Insets(10,0,0,0);
		go = new JButton("lancer");
		go.setPreferredSize(new Dimension(120, 25));

		gbc.gridx = 1;
		gbc.gridy = 4;
		Mode.add(go,gbc);
		add(Mode);

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLUE), "Modification de l'arbre affiché" , 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
	}
	public String getMot() {
		return Mot.getText();
	}
	public void ResetMot() {
		Mot.setText("");
	}
	public int getMode() {
		return mode;
	}

	public JButton getGo() {
		return go;
	}

	public JTextField getField(){
        return Mot;
    }
	
	public void AjouterArbre(String a){
		LesArbres.add(a);
		Liste.addItem(a);
	}





	class CheckBoxAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			JCheckBox p = (JCheckBox) e.getSource() ;
			if(p.getLabel() == "Ajouter" ){
				mode=1;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				Mot.setEnabled(true);
				Liste.setEnabled(false);


			}
			
			if(p.getLabel() == "Equilibrer" ){
				mode=7;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				Mot.setEnabled(false);
				Liste.setEnabled(false);
			


			}

			if(p.getLabel() =="Convertir") {
				mode=6;
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				Mot.setText("");
				Mot.setEnabled(false);
				Liste.setEnabled(false);	


			}

			if(p.getLabel() =="Fusionner avec") {
				mode = 5 ;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				Mot.setText("");
				Mot.setEnabled(false);
				Liste.setEnabled(true);
			}

			if(p.getLabel() == "Lister les mots" ){
				mode=4;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				Liste.setEnabled(false);
				Mot.setText("");
				Mot.setEnabled(false);
			}

			if(p.getLabel() == "Rechercher/Prefix" ){
				mode=2;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				if(supprimer.isSelected()){supprimer.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				Liste.setEnabled(false);
				Mot.setEnabled(true);
			}
			if(p.getLabel() == "Supprimer" ){
				mode=3;
				if(convertir.isSelected()){convertir.setSelected(false);}
				if(ajouter.isSelected()){ajouter.setSelected(false);}
				if(rechercher.isSelected()){rechercher.setSelected(false);}
				if(ListeMot.isSelected()){ListeMot.setSelected(false);}
				if(fusionner.isSelected()){fusionner.setSelected(false);}
				if(equilibre.isSelected()){equilibre.setSelected(false);}
				Liste.setEnabled(false);
				Mot.setEnabled(true);
			}
		}
	}

	public int getID() {
		System.out.println(Liste.getSelectedIndex());
		return Liste.getSelectedIndex();
	}
}

