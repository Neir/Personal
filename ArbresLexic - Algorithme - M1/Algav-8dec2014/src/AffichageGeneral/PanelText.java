package AffichageGeneral;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PanelText extends JPanel {

	private JPanel jp1;
	private JTextArea jtAffichage;
	private JScrollPane jtaScrollPane;
	
	String Texte;
	
	
	public PanelText(){
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLUE),"Console", 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
		MiseEnPlace();
		add(jp1);
	}
	
	public void MiseEnPlace(){
		jp1=new JPanel();
		jtAffichage=new JTextArea(18,55);
		jtAffichage.setLineWrap(true);
		jtAffichage.setText("*****Algav 2014*****");
		jtAffichage.setEditable(false);
		jtaScrollPane=new JScrollPane(jtAffichage);
		jtaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jtaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtAffichage.setForeground(Color.black);

		JPanel a = new JPanel();
		GroupLayout layout = new GroupLayout(jp1);
		jp1.setLayout(layout);
		jp1.setOpaque(true);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(jtaScrollPane)
						.addComponent(a))
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(jtaScrollPane)
				.addComponent(a)
				);
	}
	
	public void setText(String n){
		jtAffichage.setText(jtAffichage.getText()+"\n"+n);
		jtAffichage.setCaretPosition(jtAffichage.getDocument().getLength ());
	}
	
	
}
