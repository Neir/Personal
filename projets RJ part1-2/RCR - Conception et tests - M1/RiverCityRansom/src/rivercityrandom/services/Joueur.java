/**
 * 
 */
package rivercityrandom.services;

import java.util.List;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface Joueur extends Personnage {

	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	
	public int 		getSomme_d_argent();
	public List<Objet>	getInventaire();
	
	// inv:
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * pre: nom != ""
	 * post: getNom() == nom
	 * post: getLargeur() == 30
	 * post: getHauteur() == 50
	 * post: getProfondeur() == 10
	 * post: getForce() == 10
	 * post: getPvMax() == 100
	 * post: getSomme_d_argent() == 20
	 * post: getInventaire() == EMPTY
	 */
	public void 		init(String nom);
		
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	/**
	 * pre: argent <= getSomme_d_argent()
	 * pre: argent > 0
	 * post: getSomme_d_argent() == getSomme_d_argent()@pre - argent
	 * post: getInventaire() == getInventaire()@pre
	 */
	public void 		retrait_de_l_argent(int argent);
	
	/**
	 * pre: argent > 0
	 * post: getSomme_d_argent() == getSomme_d_argent()@pre + argent
	 * post: getInventaire() == getInventaire()@pre
	 */
	public void 		depot_de_l_argent(int argent);
	
	/**
	 * post: getSomme_d_argent() == getSomme_d_argent()@pre
	 * post: getInventaire() == getInventaire()@pre - objet
	 */
	public void			retrait_objet_de_l_inventaire(Objet objet);
	
	/**
	 * post: getSomme_d_argent() == getSomme_d_argent()@pre
	 * post: getInventaire() == getInventaire()@pre + objet
	 */
	public void			depot_objet_de_l_inventaire(Objet objet);
}
