package li260.optimisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauControle extends JPanel implements KeyListener{
	private JLabel label;
	private JButton avant, arriere, gauche, droite;
	private JPanel haut, bas, fleches;
	private JPanel espace[];
	
	public PanneauControle(){
		label = new JLabel("Commandes:");
		haut = new JPanel();
		bas = new JPanel();
		
		espace = new JPanel[2];
		for(int i=0; i<2; i++){
			espace[i] = new JPanel();
			espace[i].setPreferredSize(new Dimension(120, 60));
			espace[i].setBackground(new Color(72, 60, 50));
		}
		fleches = new JPanel();
		avant = new JButton(new ImageIcon("icones/fHaut.png"));
		arriere = new JButton(new ImageIcon("icones/fBas.png"));
		gauche = new JButton(new ImageIcon("icones/fGauche.png"));
		droite = new JButton(new ImageIcon("icones/fDroite.png"));	
		
		avant.setMnemonic(KeyEvent.VK_UP);
		arriere.setMnemonic(KeyEvent.VK_DOWN);
		gauche.setMnemonic(KeyEvent.VK_LEFT);
		droite.setMnemonic(KeyEvent.VK_RIGHT);
		
		label.setFont(new Font(null, Font.PLAIN, 30));
		label.setForeground(Color.white);
		
		fleches.setLayout(new BorderLayout());
		bas.setLayout(new BorderLayout());
		
		haut.add(avant);
		bas.add(gauche, BorderLayout.WEST);
		bas.add(arriere, BorderLayout.CENTER);
		bas.add(droite, BorderLayout.EAST);
		
		fleches.add(haut, BorderLayout.NORTH);
		fleches.add(bas, BorderLayout.SOUTH);
		haut.setBackground(new Color(72, 60, 50));
		bas.setBackground(new Color(72, 60, 50));
		fleches.setBackground(new Color(72, 60, 50));
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(fleches, BorderLayout.CENTER);
		this.add(espace[0], BorderLayout.EAST);
		this.add(espace[1], BorderLayout.WEST);
		this.setBackground(new Color(72, 60, 50));
		this.setPreferredSize(new Dimension(450, 140));
		enable(false);
	}
	
	public void enable(boolean b){
		avant.setEnabled(b);
		arriere.setEnabled(b);
		gauche.setEnabled(b);
		droite.setEnabled(b);
	}

	public void setActionCommand(){
		avant.setActionCommand("avant");
		arriere.setActionCommand("arriere");
		gauche.setActionCommand("gauche");
		droite.setActionCommand("droite");
	}

	public void addActionListener(Controleur cont) {
		avant.addActionListener(cont);
		arriere.addActionListener(cont);
		gauche.addActionListener(cont);
		droite.addActionListener(cont);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	
}
