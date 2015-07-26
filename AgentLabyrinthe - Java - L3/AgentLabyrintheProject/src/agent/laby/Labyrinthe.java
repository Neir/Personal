package agent.laby;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author sigaud Cette classe représente un labyrinthe
 */
public class Labyrinthe implements Serializable {
	/**
	 * La version de la classe, utilisée par le mécanisme Serializable.
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * tableau des cases du labyrinthe
	 */
	private ContenuCase[][] carte;

	/**
	 * La position initiale d'un agent dans le labyrinthe.
	 */
	private Point init = new Point(1,1);

	
	/**
	 * Crée un labyrinthe cerné de murs et couverts de points, des dimensions indiquées.
	 * 
	 * @param width
	 *            : nombre de colonnes
	 * @param height
	 *            : nombre de lignes
	 */
	public Labyrinthe(int width, int height) {
		carte = new ContenuCase[width][height];
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (i == 0 || j == 0 || i == carte.length - 1
						|| j == carte[i].length - 1)
					carte[i][j] = ContenuCase.MUR;
				else
					carte[i][j] = ContenuCase.POINT;
			}
		}
		carte[init.x][init.y] = ContenuCase.VIDE;
	}

	/**
	 * Private ctor utilise pour copier l'objet courant (cf clone).
	 * 
	 * @param copy
	 */
	private Labyrinthe(ContenuCase[][] copy, Point init) {
		carte = copy;
		this.init = init;
	}

	/**
	 * Renvoie le nombre de positions en X (donc le nombre de colonnes)
	 * 
	 * @return
	 */
	public int Xsize() {
		// juste map.length, donc le nombre de lignes
		return carte.length;
	}

	/**
	 * Renvoie le nombre de positions en Y (donc le nombre de lignes)
	 * 
	 * @return
	 */
	public int Ysize() {
		// on prends la mesure de la première ligne
		return carte[0].length;
	}

	/**
	 * Renvoie ce que contient la case à la position p
	 * 
	 * @param p
	 *            : case demandée
	 * @return : ce qu'elle contient
	 */
	public ContenuCase getContenuCase(Point p) {
		return getContenuCase(p.x, p.y);
	}

	/**
	 * Renvoie ce que contient la case à la position (x,y)
	 * 
	 * @param x
	 *            : coordonnée demandée
	 * @param y
	 *            : coordonnée demandée
	 * @return : ce qu'elle contient
	 */
	public ContenuCase getContenuCase(int x, int y) {
		try {
			return carte[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return ContenuCase.ANY;
		}
	}

	/**
	 * Change le contenu d'une case
	 * 
	 * @param p
	 *            : case demandée
	 * @param element
	 *            : élément modifié
	 */
	public void setContenuCase(Point p, ContenuCase element) {
		setContenuCase(p.x, p.y, element);
	}

	/**
	 * Change le contenu d'une case
	 * 
	 * @param x abscisse
	 * @param y ordonnée de la case demandée
	 * @param element élément modifié
	 */
	public void setContenuCase(int x, int y, ContenuCase element) {
		carte[x][y] = element;
	}

	/**
	 * Renvoie le nombre de points que contient le labyrinthe
	 * 
	 * @return le score maximal d'un agent dans le labyrinthe.
	 */
	public int getNbPoints() {
		int nbPoints = 0;
		// pour chaque ligne de map
		for (ContenuCase[] row : carte) {
			// pour chaque cellule de la ligne
			for (ContenuCase cell : row) {
				if (cell == ContenuCase.POINT)
					nbPoints++;
			}
		}
		return nbPoints;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y =0; y < Ysize() ; y++) {
			for (int x = 0; x < Xsize() ; x++) {
				sb.append(getContenuCase(x,y)+ " ");
			}
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Le labyrinthe contient " + getNbPoints() + " points.");
		return sb.toString();
	}

	/**
	 * Vérifie que le labyrinthe est entouré de murs
	 * @return : true s'il est entouré de murs, false sinon
	 */
	public boolean estEntoureDeMurs() {
		int imax = Xsize()-1;
		int jmax = Ysize()-1;
		for (int i=0;i< Xsize();i++){
			if (carte[i][jmax]!=ContenuCase.MUR) return false;
			if (carte[i][0]!=ContenuCase.MUR) return false;
		}
		for (int j=0;j<Ysize();j++){
			if (carte[0][j]!=ContenuCase.MUR) return false;
			if (carte[imax][j]!=ContenuCase.MUR) return false;
		}
		return true;
	}

	/**
	 * Surcharge de la méthode clone, en serrant le type de retour.
	 * 
	 * @return : un clone du labyrinthe
	 */
	@Override
	public Labyrinthe clone() {
		ContenuCase[][] copy = new ContenuCase[Xsize()][Ysize()];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = Arrays.copyOf(carte[i], carte[i].length);
		}
		return new Labyrinthe(copy,new Point(init));
	}

	public Point getPositionInitiale() {
		return init;
	}
	
	public void setPositionInitiale(Point init) {
		if (init != null)
			this.init = init;
		else
			init = new Point(1,1);
	}

}
