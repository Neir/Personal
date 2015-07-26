package agent.laby.interf;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import agent.laby.ContenuCase;

/**
 * Un bouton graphique pour représenter l'état d'une cellule du labyrinthe.
 * 
 * Son état interne "aspect" est censé refléter l'état du labyrinthe. Pour
 * intercepter les clicks sur le bouton et les traiter, s'abonner au bouton via
 * addActionListener().
 * 
 * C'est un bouton, on étends {@link JButton}.
 * 
 * @author Yann
 * 
 */
class CaseButton extends JButton {

	// trivia de serializable
	private static final long serialVersionUID = 1L;

	// L'aspect de ce bouton
	private ContenuCase aspect = ContenuCase.VIDE;

	// La position de cette cellule, utile aux clients pour savoir où s'est
	// passé le clic.
	private final Point position;

	// vrai si cette case est la position initiale.
	private boolean isInit;

	/**
	 * Fixe la position
	 * 
	 * @param position
	 *            la position de ce bouton dans le Maze
	 */
	public CaseButton(Point position) {
		this.position = position;
	}

	/**
	 * Modifie l'aspect de ce bouton
	 * 
	 * @param content
	 *            le nouvel aspect
	 */
	public void setAspect(ContenuCase content) {
		this.aspect = content;
		updateGraphics();
	}

	/**
	 * Rend l'aspect graphique actuel de ce bouton.
	 * 
	 * @return l'aspect du bouton
	 */
	public ContenuCase getAspect() {
		return aspect;
	}

	/**
	 * Mise à jour des caractéristiques graphiques en fonction de "aspect".
	 */
	private void updateGraphics() {
		if (isInit) {
			setBorder(BorderFactory.createTitledBorder("init"));
		} else {
			setBorder(null);
		}

		
		switch (aspect) {
		case POINT:
			setBackground(Color.yellow);
			setIcon(new ImageIcon("icones/dot.jpg"));
			break;
		case VIDE:
			setBackground(Color.white);
			setIcon(new ImageIcon("icones/vide.png"));
			break;
		case MUR:
			setBackground(Color.black);
			setIcon(new ImageIcon("icones/wall.jpg"));
			break;
		case ANY:
		case AGENT:
			setBackground(Color.blue);
			setIcon(new ImageIcon("icones/agent.jpg"));
			setIcon(null);
			break;

		}
		
		// inciter à repeindre cet objet à la prochaine occasion
		repaint();
	}

	/**
	 * Rend la position de ce bouton. Utile aux clients qui implémentent
	 * {@link ActionListener} pour savoir où s'est passé le clic.
	 * 
	 * @return la position dans le labyrinthe
	 */
	public Point getPosition() {
		return position;
	}

	public void setInitial(boolean b) {
		this.isInit = b;
		updateGraphics();
	}

	public boolean isInitial() {
		return isInit;
	}
}
