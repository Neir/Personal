/**
 * 
 */
package rivercityrandom.services;

import rivercityrandom.enumerations.TYPE_GANGSTER;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface Gangster extends Personnage {

	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	
	public TYPE_GANGSTER	getType(); // const
	public Objet			getDrop(); // const
	
	// inv:
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * pre: nom != ""
	 * pre: type != null
	 * pre: pos_z != >= 0
	 * post: getNom() == nom
	 * post: getPos_z() == pos_z
	 * post: getType() == type
	 * post: getDrop() == drop
	 * post: if type == TYPE_GANGSTER.GENERIC_DUDES
	 * 		 then
	 * 			getLargeur() == 30
	 * 			getHauteur() == 50
	 * 			getProfondeur() == 10
	 * 			getForce() == 5
	 * 			getPvMax() == 20
	 * 		 else if type == TYPE_GANGSTER.HOME_BOYS
	 * 		 then
	 * 			getLargeur() == 30
	 * 			getHauteur() == 50
	 * 			getProfondeur() == 10
	 * 			getForce() == 10
	 * 			getPvMax() == 30
	 * 		 else if type == TYPE_GANGSTER.SQUIDS
	 * 		 then
	 * 			getLargeur() == 30
	 * 			getHauteur() == 50
	 * 			getProfondeur() == 10
	 * 			getForce() == 15
	 * 			getPvMax() == 50
	 */
	public void 	init(String nom, TYPE_GANGSTER type, Objet drop, int pos_z);
		
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
}
