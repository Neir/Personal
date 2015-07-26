package agent.laby;

/**
 * Le contenu d'une cellule, matérialisé par un enum.
 * @author Yann
 *
 */
public enum ContenuCase {
	/** Valeur joker.
	 *  Utilisée dans les règles pour désigner n'importe quel contenu.
	 */
	ANY ("?"), 
	/**
	 * Cette case est vide.
	 */
	VIDE (" "), 
	/**
	 * Cette case contient un point.
	 * Les points sont des bonus consommés par les agents.
	 */
	POINT ("."), 
	/**
	 * Cette case contient un mur infranchissable.
	 */
	MUR ("#"), 
	/**
	 * Cette case contient un agent.
	 */
	AGENT ("A");
	
	private ContenuCase (String name) {
		this.name = name;
	}

	private final String name;
	
	public String toString() {
		return name;
	}	
}
