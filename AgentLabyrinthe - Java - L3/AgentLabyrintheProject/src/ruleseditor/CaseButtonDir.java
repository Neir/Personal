package ruleseditor;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import agent.control.Direction;

public class CaseButtonDir extends JButton{

	private static final long serialVersionUID = 1L;
	
	private Direction dir;
	
	public CaseButtonDir(Direction dir){
		super();
		this.dir = dir;
	}
	
	public Direction getDir() {
		return dir;
	}
	
	public void setAspect(Direction dir) {
		this.dir = dir;
		updateGraphics();
	}

	/**
	 * Mise à jour des caractéristiques graphiques en fonction de "aspect".
	 */
	private void updateGraphics() {
		setBackground(Color.green);
		
		switch (dir) {
		case HAUT:
			setIcon(new ImageIcon("icones/haut.png"));
			break;
		case BAS:
			setIcon(new ImageIcon("icones/bas.png"));
			break;
		case GAUCHE:
			setIcon(new ImageIcon("icones/gauche.png"));
			break;
		case DROITE:
			setIcon(new ImageIcon("icones/droite.png"));
			break;
		}

		// inciter à repeindre cet objet à la prochaine occasion
		repaint();
	}
	
}
