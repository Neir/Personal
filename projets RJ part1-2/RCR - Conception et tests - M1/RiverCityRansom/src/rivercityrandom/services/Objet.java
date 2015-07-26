/**
 * 
 */
package rivercityrandom.services;

import rivercityrandom.enumerations.CONTENU_OBJET;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface Objet {

	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	public CONTENU_OBJET 		getContenu(); // const
	/* Valeur : Dégat de l'arme ou effet du marchandable */
	public int 					getValeur(); // const
	public boolean				estMarchandable(); // const
	public int	 				getCout(); // const
	
	/* Invariants */
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------	
	
	/**
	 * pre: p_contenu != null
	 * pre: p_valeur >= 0
	 * pre: p_cout >= 0
	 * post: getContenu() == p_contenu
	 * post: getValeur() == p_valeur
	 * post: estMarchandable() == p_marchand
	 * post: getCout() == p_cout
	 */
	public void 	init(
				CONTENU_OBJET p_contenu,
				int p_valeur,
				boolean p_marchand,
				int p_cout);
	
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
}
