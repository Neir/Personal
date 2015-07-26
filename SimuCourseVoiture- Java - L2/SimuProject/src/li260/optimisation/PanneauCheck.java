package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauCheck extends JPanel{
	private JLabel label;
	private JCheckBox strDijk, strCla, strEnd, strBoue, strObs;
	private JPanel panBout;
	
	public PanneauCheck(){
		label = new JLabel("Strategies implementees:");
		strDijk = new JCheckBox("Strategie Dijkstra", true);
		strCla = new JCheckBox("Strategie Classique");
		strEnd = new JCheckBox("Strategie EndLine", true);
		strBoue = new JCheckBox("Strategie Boue");
		strObs = new JCheckBox("Strategie Obstacle", true);
		panBout = new JPanel();

		//Couleurs et polices
		strDijk.setForeground(Color.white);
		strEnd.setForeground(Color.white);
		strBoue.setForeground(Color.white);
		strObs.setForeground(Color.white);	
		strCla.setForeground(Color.white);		
		
		strDijk.setBackground(new Color(72, 60, 50));
		strEnd.setBackground(new Color(72, 60, 50));
		strBoue.setBackground(new Color(72, 60, 50));
		strObs.setBackground(new Color(72, 60, 50));
		strCla.setBackground(new Color(72, 60, 50));
		panBout.setBackground(new Color(72, 60, 50));
		this.setBackground(new Color(72, 60, 50));
		
		label.setFont(new Font(null, Font.PLAIN, 30));
		label.setForeground(Color.white);
		
		this.setLayout(new BorderLayout());
		panBout.setLayout(new FlowLayout());
		panBout.add(strCla);
		panBout.add(strDijk);
		panBout.add(strEnd);
		panBout.add(strBoue);
		panBout.add(strObs);
		this.add(label, BorderLayout.NORTH);
		this.add(panBout, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(450, 100));
		
	}
	
	public void setActionCommand(){
		strDijk.setActionCommand("dijk");
		strCla.setActionCommand("classique");
 		strEnd.setActionCommand("endLine");
 		strBoue.setActionCommand("boue");
 		strObs.setActionCommand("obstacle");
	}
	
	public void addActionListener(Controleur cont) {
		strDijk.addActionListener(cont);
		strCla.addActionListener(cont);
		strEnd.addActionListener(cont);
		strBoue.addActionListener(cont);
		strObs.addActionListener(cont);
	}


	public void enable(boolean b){
		strDijk.setEnabled(b);
		strCla.setEnabled(b);
		strEnd.setEnabled(b);
		strBoue.setEnabled(b);
		strObs.setEnabled(b);
	}

}
