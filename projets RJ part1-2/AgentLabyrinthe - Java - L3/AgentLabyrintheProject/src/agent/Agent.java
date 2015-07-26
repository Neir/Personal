package agent;

import java.awt.Point;

import agent.control.Direction;
import agent.control.IControleur;
import agent.control.Observation;
import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;

/** Un Agent, dans un labyrinthe. */
public class Agent {

	/**
	 * Le contrôleur dont on évalue le comportement.
	 */
	private IControleur controleur;

	/**
	 * La position courante de l'agent
	 */
	private Point position;
	
	/**
	 * Le score obtenu à ce stade de la simulation.
	 */
	private int score;

	public Agent(IControleur controleur, Point position) {
		this.position = position;
		this.controleur = controleur;
		this.score = 0;
	}

	/**
	 * Met à jour la position suite à un déplacement
	 * 
	 * @param direction
	 *            : la direction de déplacement
	 */
	private void bouge(Direction direction, Labyrinthe laby) {
		int x = position.x;
		int y = position.y;
		
		switch (direction) {
		case HAUT:
				deplaceSiPasDeMur(x,y-1,laby);
				break;
		case BAS:
				deplaceSiPasDeMur(x,y+1,laby);
				break;
		case GAUCHE:
				deplaceSiPasDeMur(x-1,y,laby);
				break;
		case DROITE:
				deplaceSiPasDeMur(x+1,y,laby);
				break;
		case STOP: // do nothing
		default:
			break;
		}
	}
	
	/**
	 * Teste si la case (x,y) est légale (!= MUR), si oui, déplace l'agent.
	 * Positions en coordonées graphiques standard, y vertical vers le bas, 
	 * (0,0) = coin haut gauche.
	 * @param x abscisse
	 * @param y ordonnée
	 */
	private void deplaceSiPasDeMur(int x, int y, Labyrinthe laby) {
		if (laby.getContenuCase(new Point(x, y)) != ContenuCase.MUR) {
			this.position = new Point(x, y);
		}
	}

	/**
	 * Effectue un pas de simulation : capte l'environnement, choisit l'action,
	 * se déplace, met à jour le score puis la position
	 */
	public void faitUnPas(Labyrinthe laby) {
		Observation capteurs = new Observation(position, laby);
		Direction action = controleur.choisitAction(capteurs);
		laby.setContenuCase(position, ContenuCase.VIDE);

		bouge(action, laby);
		if (laby.getContenuCase(position).equals(ContenuCase.POINT)) {
			score++;
		}
		laby.setContenuCase(position, ContenuCase.AGENT);
	}

	
	public IControleur getControleur() {
		return controleur;
	}
	
	public int getScore() {
		return score;
	}
	
}
