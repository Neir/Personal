package AffichageGeneral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class PanelChoixArbre extends JPanel {

	private JCheckBox Briandais , Hybrides , LesDeux ;
	private int mode ;
	private GridBagConstraints gbc ;
	private JButton jbImporter;
	private JLabel Brian, Hybri ;
	private JProgressBar hy, bi;
	public PanelChoixArbre(){
		miseEnPlace();
	}

	private void miseEnPlace() {
		mode = 1 ;
		JPanel Mode = new JPanel();
		Mode.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		Briandais = new JCheckBox("Briandais");
		Hybrides = new JCheckBox("Hybrides");
		Brian = new JLabel("Briandais");
		Hybri = new JLabel("Hybrides");
		hy = new JProgressBar(0,100);
		bi = new JProgressBar(0,100);
		bi.setStringPainted(true);
		hy.setStringPainted(true);
		LesDeux = new JCheckBox("Les deux");
		Briandais.addActionListener(new CheckBoxAction());
		Hybrides.addActionListener(new CheckBoxAction());
		LesDeux.addActionListener(new CheckBoxAction());
		
		jbImporter=new JButton("Importer texte");
//		jbImporter.setFont(new Font("Elephant", Font.PLAIN, 13));
//		jbImporter.addActionListener(new ActionCharger());

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,30,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;

		Mode.add(Briandais,gbc);
		gbc.insets = new Insets(0,30,0,30);
		gbc.gridx = 1;
		Mode.add(Hybrides,gbc);
		gbc.gridx = 2;
		gbc.insets = new Insets(0,0,0,30);
		Mode.add(LesDeux,gbc);
		Briandais.setSelected(true);
		gbc.insets = new Insets(0,0,0,00);
		gbc.gridx = 1;
		gbc.gridy = 1;
		jbImporter.setPreferredSize(new Dimension(150, 25));
		Mode.add(jbImporter, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0,00,0,0);
		gbc.anchor = GridBagConstraints.LINE_START;
		Mode.add(Brian,gbc);
		gbc.gridx=1;
		gbc.anchor = GridBagConstraints.LINE_END;
		Mode.add(Hybri,gbc);
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx=0;
		Mode.add(bi,gbc);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx=1;
		Mode.add(hy,gbc);

		add(Mode);

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLUE), "Type d'arbre" , 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
	}
	
	public int GetMode(){
		return mode ;
	}
	
	public void SetValueBrian(int a){
		bi.setValue(a);
	}
	
	public void SetValueHyb(int a){
		hy.setValue(a);
	}
	
	public JButton GetImort(){
		return jbImporter;
	}


	class CheckBoxAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			JCheckBox p = (JCheckBox) e.getSource() ;
			if(p.getLabel() == "Briandais" ){
				mode=1;
				if(Hybrides.isSelected()){Hybrides.setSelected(false);}
				if(LesDeux.isSelected()){LesDeux.setSelected(false);}
			}

			if(p.getLabel() == "Hybrides" ){
				mode=2;
				if(Briandais.isSelected()){Briandais.setSelected(false);}
				if(LesDeux.isSelected()){LesDeux.setSelected(false);}
			}
			if(p.getLabel() == "Les deux" ){
				mode=3;
				if(Briandais.isSelected()){Briandais.setSelected(false);}
				if(Hybrides.isSelected()){Hybrides.setSelected(false);}

			}

		}
	}
	
	class ActionCharger implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
	
	
}
