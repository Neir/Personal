/**
 * 
 */
package agent.control;

/**
 * @author sigaud Cette classe représente une règle [condition sur les
 *         capteurs], [action] Si les capteurs vérifient les conditions
 *         exprimées dans les règles l'action peut Ãªtre déclenchée
 */
public class Regle {
	/**
	 * tableau des conditions sur les capteurs
	 */
	private Observation conditions;

	/**
	 * action déclenchable
	 */
	private Direction action;

	/**
	 * Constructeur
	 */
	public Regle(Observation cond, Direction dir) {
		conditions = cond;
		action = dir;
	}

	/**
	 * Regarde si une règle est déclenchable en fonction des capteurs
	 * 
	 * @param sensors
	 *            : l'état des capteurs
	 * @return : true si déclenchable, false sinon
	 */
	public boolean matches(Observation sensors) {
		return conditions.matches(sensors);
	}

	/**
	 * Getter
	 * 
	 * @return : l'action de la règle
	 */
	public Direction getAction() {
		return action;
	}
	
	public Observation getConditions() {
		return conditions;
	}

	/**
	 * Setter utilisé par le contrÃ´leur
	 * 
	 * @param action
	 */
	public void setAction(Direction action) {
		this.action = action;
	}


	public void setConditions(Observation conditions) {
		this.conditions = conditions;
	}
	
	/**
	 * 
	 * Copie profonde de cette règle.
	 * 
	 */
	@Override
	public Regle clone() {
		return new Regle (conditions.clone(), getAction());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder retour = new StringBuilder("\n");
		retour.append(conditions.toString());

		return retour + " -> " + action;
	}


	/**
	 * Méthode standard hashCode.
	 * Implémentation générée par eclipse.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + conditions.hashCode();
		return result;
	}

	/**
	 * Méthode standard equals.
	 * Implémentation générée par eclipse.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regle other = (Regle) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (conditions.equals(other.conditions))
			return false;
		return true;
	}
	
}
