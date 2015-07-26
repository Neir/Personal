package agent.laby.interf;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;

/**
 * 
 * Un panel {@link JPanel} pour dessiner un maze {@link Labyrinthe}. Les cellules
 * {@link CaseButton} sont clickable, ce qui change leur aspect et modifie le
 * Maze sous-jacent. L'effet du clic sur la cellule est détérminé par
 * actionSelected, qui est un {@link ContenuCase} à appliquer à cette cellule.
 * Utilisez setActionSelected pour modifier le comportement.
 * 
 * @author Yann
 * 
 */
class LabyPanel extends JPanel implements ActionListener {

	// / Serializable trivia
	private static final long serialVersionUID = 1L;

	/**
	 * The underlying Maze instance. The graphical cell buttons reflect and
	 * impact on this maze.
	 */
	private Labyrinthe laby;
	/**
	 * The cell buttons. On en garde la trace pour permettre les mise à jour.
	 */
	private CaseButton[][] cases;
	/**
	 * L'action sélectionnée, donne l'effet d'un appui sur un bouton/cellule.
	 */
	private ContenuCase actionSelected = ContenuCase.VIDE;

	/**
	 * Constructeur, prends un maze en argument.
	 * 
	 * @param laby
	 *            un {@link Labyrinthe} non null.
	 */
	public LabyPanel(Labyrinthe laby) {
		// / positionne le comportement de this.add pour la suite
		setLayout(new GridLayout(laby.Ysize(), laby.Xsize()));

		cases = new CaseButton[laby.Xsize()][laby.Ysize()];

		for (int j = 0; j < laby.Ysize(); j++) {
			for (int i = 0; i < laby.Xsize(); i++) {
				// Creer un bouton
				CaseButton cell = new CaseButton(new Point(i, j));
				// le réferencer dans le tableau
				cases[i][j] = cell;
				// l'ajouter à ce composant graphique
				// NB: grâce au GridLayout ci dessus les positions sont bien
				add(cell);

				// positionner le comportement en cas de clic
				// NB: c'est ce MazePanel this qui joue le rôle de
				// controleur pour tous les boutons.
				cell.addActionListener(this);
			}
		}
		// positionne le contenu
		setLaby(laby);
	}

	/**
	 * Permet de changer de Maze. Atttention à conserver les dimensions, on l'on
	 * s'expose à des soucis d'affichage et des
	 * {@link ArrayIndexOutOfBoundsException}. Créer une nouvelle instance de
	 * MazePanel si cela n'est pas possible.
	 * 
	 * @param maze
	 *            le nouveau Maze, de meme dimensions que le précédent.
	 */
	public void setLaby(Labyrinthe maze) {
		this.laby = maze;
		updateGraphics();
	}

	/**
	 * Force la mise à jour de l'aspect pour refléter l'état du Maze.
	 */
	public void updateGraphics() {
		for (int i = 0; i < laby.Xsize(); i++) {
			for (int j = 0; j < laby.Ysize(); j++) {
				cases[i][j].setAspect(laby.getContenuCase(i, j));
				cases[i][j].setInitial(false);
			}
		}
		Point init = laby.getPositionInitiale();
		cases[init.x][init.y].setInitial(true);

		// Suggère (fortement) de repeindre cet objet (this) et ses fils
		// en l'occurrence les boutons, dont la valeur interne "content" a
		// changé,
		// mais qui ont besoin d'une raison avant de rafraichir leur aspect.
		repaint();
	}

	/**
	 * Force la synchronisation en écrivant l'état des boutons dans le labyrinthe
	 * sous-jacent. NB: En principe, le  labyrinthe et les boutons sont déjà
	 * synchronisés, chaque appui de bouton étant immédiatement appliqué au labyrinthe
	 * (cf. corps de actionPerformed).
	 */
	public void modifLaby() {
		for (int i = 0; i < laby.Xsize(); i++) {
			for (int j = 0; j < laby.Ysize(); j++) {
				laby.setContenuCase(i, j, cases[i][j].getAspect());
				if (cases[i][j].isInitial()) {
					laby.setPositionInitiale(new Point(i,j));
				}
			}
		}
	}

	/**
	 * Traite l'appui sur un bouton de cellule par l'utilisateur.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// requalifier le type de la source
		CaseButton c = (CaseButton) e.getSource();
		// mettre à jour l'aspect du bouton
		c.setAspect(actionSelected);
		// mettre à jour le Maze à la position concernée
		laby.setContenuCase(c.getPosition(), actionSelected);
	}

	/**
	 * Permet de modifier le type de contenu appliqué quand on clique sur une
	 * cellule.
	 * 
	 * @param actionSelected l'action donne le nouveau contenu à appliquer à la cellule
	 */
	public void setActionSelected(ContenuCase actionSelected) {
		this.actionSelected = actionSelected;
	}

}
