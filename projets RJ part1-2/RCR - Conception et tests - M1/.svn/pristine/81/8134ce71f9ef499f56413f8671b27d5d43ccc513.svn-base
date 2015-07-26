/**
 * 
 */
package rivercityrandom.services;

import rivercityrandom.enumerations.TYPE_BLOC;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface Bloc {
	
	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	public TYPE_BLOC 		getTypeBloc(); // const
	public Objet 			getTresor();
	
	/* Invariants */
	
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------	
	
	/**
	 * pre: type != null
	 * post: getTypeBloc() == type
	 * post: getTresor() == tresor
	 */
	public void 			init(TYPE_BLOC type, Objet tresor);
	
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	/**
	 * post: getTresor() == tresor
	 */
	public void 			changerTresor(Objet tresor);
}
