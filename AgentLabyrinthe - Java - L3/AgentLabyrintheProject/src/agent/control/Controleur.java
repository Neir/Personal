/**
 * 
 */
package agent.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cette classe représente le contrôleur d'un agent, sous forme
 *         d'une base de règles [condition sur les capteurs], [action]
 *         
 *  @author sigaud 
 */
public class Controleur implements IControleur {

	private static Random generator = new Random();

	/**
	 * base de règles du contrôleur
	 */
	private List<Regle> ruleset = new ArrayList<Regle>();

	public Controleur(){
		super();
	}

	public Controleur(List<Regle> ruleset) {
		super();
		this.ruleset = ruleset;
	}

	/**
	 * {@inheritDoc}
	 */
	public Direction choisitAction(Observation sensors) {
		for (Regle r : ruleset) {
			if (r.matches(sensors))
				return r.getAction();
		}
		return Direction.STOP;
	}

	public List<Regle> getRuleset() {
		return ruleset;
	}
	
	public void appendRules(List<Regle> rules){
		ruleset.addAll(rules);
		//for (int i = 0; i<rules.size(); i++)
		//	ruleset.set(ruleset.size(), rules.get(i));
	}

	/**
	 * Fait muter les règles
	 * 
	 * @param proba
	 *            : probabilité de mutation pour chaque règle
	 */
	public void muter(double proba) {
		for (int i = 0; i < size(); i++) {
			if (Math.random() < proba) {
				ruleset.set(i, ControlFactory.generateRandomRule());
			}
		}
	}

	/**
	 * @param pere
	 * @return
	 */
	public Controleur copieCroisee(IControleur parent) {
		Controleur pere = (Controleur) parent;
		Controleur retour = clone();
		int cr1 = generator.nextInt(size());
		int cr2 = generator.nextInt(pere.size());
		retour.ruleset.set(cr1, pere.ruleset.get(cr2));
		return retour;
	}

	/**
	 * Pour créer un descendant de deux Controleurs, on commence par réaliser
	 * le croisement, puis la mutation
	 * 
	 * @param expr
	 *            : le second parent
	 * @return : le descendant créé
	 */
	public Controleur creeFils(IControleur expr, double proba) {
		Controleur fils = copieCroisee(expr);
		fils.muter(proba);
		return fils;
	}

	/**
	 * Renvoie un clone du controleur
	 * 
	 * @return : le clone
	 */
	@Override
	public Controleur clone() {
		Controleur retour = new Controleur();
		for (Regle r : ruleset) {
			retour.add(r.clone());
		}
		return retour;
	}
	
	
	/**
	 * Ajoute la règle à l'ensemble de règles.
	 * 
	 * @param rule la règle à ajouter
	 * @return true si l'insertion s'est bien passée
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Regle rule) {
		return ruleset.add(rule);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ruleset.toString();
	}

	/**
	 * Getter
	 * 
	 * @return : le nombre de règles
	 */
	public int size() {
		return ruleset.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruleset == null) ? 0 : ruleset.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Controleur other = (Controleur) obj;
		if (ruleset == null) {
			if (other.ruleset != null)
				return false;
		} else if (!ruleset.equals(other.ruleset))
			return false;
		return true;
	}

	@Override
	public String affiche() {
		// TODO Auto-generated method stub
		return "" + size();
	}


}
