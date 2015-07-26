package ruleseditor;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import agent.control.Direction;
import agent.laby.ContenuCase;

public class CaseButtonObs extends JButton {
	private static final long serialVersionUID = 1L;

	// L'aspect de ce bouton
	private ContenuCase aspect = ContenuCase.VIDE;

	// La position de cette cellule, utile aux clients pour savoir où s'est
	// passé le clic.
	private final int position;

	/**
	 * Fixe la position
	 * 
	 * @param position
	 *            la position de ce bouton dans le Maze
	 */
	public CaseButtonObs(int position) {
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
			setBackground(new Color(102, 0, 153));
			setIcon(new ImageIcon("icones/any.png"));
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
	public int getPosition() {
		return position;
	}
}