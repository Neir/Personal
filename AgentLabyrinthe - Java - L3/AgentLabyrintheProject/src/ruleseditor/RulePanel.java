package ruleseditor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;
import agent.laby.ContenuCase;

public class RulePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Regle rule;
	/**
	 * The cell buttons. On en garde la trace pour permettre les mise à jour.
	 */
	private CaseButtonObs[] cases;
	/**
	 * L'action sélectionnée, donne l'effet d'un appui sur un bouton/cellule.
	 */
	private ContenuCase actionSelected = ContenuCase.ANY;
	private CaseButtonDir caseDir;
	private boolean aSuppr = false;
	private boolean suppr = false;
	
	public RulePanel(Regle rule){
		// / positionne le comportement de this.add pour la suite
		setLayout(new GridLayout(3, 3));
		
		cases = new CaseButtonObs[8];
		caseDir = new CaseButtonDir(rule.getAction());

		for (int i = 0; i < 8; i++) {
			cases[i] = new CaseButtonObs(i);
			
			//add(cases[i]);
			
			// positionner le comportement en cas de clic
			// NB: c'est ce MazePanel this qui joue le rôle de
			// controleur pour tous les boutons.
			cases[i].addActionListener(this);
		}
		
		for (int i = 0; i < 8; i++) {
			if(i==4){
				add(caseDir);
				caseDir.addActionListener(this);
			}
			add(cases[correspondance(i)]);
		}
		setRule(rule);
		//Border bordure = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		Border bordure = new BevelBorder(1,Color.BLUE,Color.BLACK,Color.BLUE,Color.BLACK);
		
		setBorder(bordure);
	}

	public Regle getRule(){
		return rule;
	}
	
	public void setRule(Regle rule){
		this.rule = rule;
		updateGraphics();
	}
	
	public void modifRule() {
		for (int i = 0; i < 8; i++)
			rule.getConditions().setContenu(i, cases[i].getAspect());
		rule.setAction(caseDir.getDir());
	}
	
	private int correspondance(int i){
		switch(i){
		case 0 :
			return 7;
		case 1 :
			return 0;
		case 2 :
			return 1;
		case 3 :
			return 6;
		case 4 :
			return 2;
		case 5 :
			return 5;
		case 6 :
			return 4;
		case 7 :
			return 3;
		default :
			return -1;
		}
	}
	
	/**
	 * Force la mise à jour de l'aspect pour refléter l'état du Maze.
	 */
	public void updateGraphics() {
		for (int i = 0; i < 8; i++) {
			cases[i].setAspect(rule.getConditions().getContenu(i));
		}
		caseDir.setAspect(rule.getAction());
		// Suggère (fortement) de repeindre cet objet (this) et ses fils
		// en l'occurrence les boutons, dont la valeur interne "content" a
		// changé,
		// mais qui ont besoin d'une raison avant de rafraichir leur aspect.
		repaint();
	}
	
	
	/**
	 * Traite l'appui sur un bouton de cellule par l'utilisateur.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//if(aSuppr)
			suppr = true;
		if(e.getSource() instanceof CaseButtonObs){
			// requalifier le type de la source
			CaseButtonObs c = (CaseButtonObs) e.getSource();
			// mettre à jour l'aspect du bouton
			c.setAspect(actionSelected);
			// mettre à jour le Maze à la position concernée
			Observation obs = rule.getConditions();
			obs.setContenu(c.getPosition(), c.getAspect());
			rule.setConditions(obs);
		} else {
			CaseButtonDir c = (CaseButtonDir) e.getSource();
			Direction dir = rule.getAction();
			switch (dir) {
			case HAUT:
				dir = Direction.DROITE;
				break;
			case DROITE:
				dir = Direction.BAS;
				break;
			case BAS:
				dir = Direction.GAUCHE;
				break;
			default :
				dir = Direction.HAUT;
				break;
			}
			
			c.setAspect(dir);
			rule.setAction(dir);
		}
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

	public void aSupprimer() {
		aSuppr = true;
	}

	public boolean supprimer() {
		return suppr;
	}
	
}
