/**
 * 
 */
package rivercityrandom.contracts;

import rivercityrandom.decorators.BlocDecorator;
import rivercityrandom.enumerations.TYPE_BLOC;
import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.services.Bloc;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class BlocContract extends BlocDecorator {

	public BlocContract(Bloc delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// Pas d'invariant dans ce service
	}
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * @see rivercityrandom.services.Bloc#init(rivercityrandom.enumerations.TYPE_BLOC, rivercityrandom.services.Objet)
	 */
	@Override
	public void init(TYPE_BLOC type, Objet tresor) {
		
		/* Préconditions */
		
		// pre: type != null
		if (!(type != null)) {
			throw new PreconditionError("Le type de bloc est null");
		}
		
		
		
		// pre inv
		checkInvariant();
		
		super.init(type, tresor);
		
		// post inv
		checkInvariant();
		
		
		
		/* Postconditions */
		
		// post: getTresor() == tresor
		if (!(getTresor() == tresor)) {
			throw new PostconditionError(
					"Le trésor du bloc n'est pas correctement initialisé");
		}
		
		// post: getTypeBloc() == type
		if (!(getTypeBloc() == type)) {
			throw new PostconditionError(
					"Le type du bloc n'est pas correctement initialisé");
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	/**
	 * @see rivercityrandom.services.Bloc#changerTresor(rivercityrandom.services.Objet)
	 */
	@Override
	public void changerTresor(Objet tresor) {
		
		/* Préconditions */
		
		
		
		// pre inv
		checkInvariant();
		
		super.changerTresor(tresor);
		
		// post inv
		checkInvariant();
		
		
		
		/* Postconditions */
		
		// post: getTresor() == tresor
		if (!(getTresor() == tresor)) {
			throw new PostconditionError(
					"Le trésor du bloc n'a pas été correctement modifié");
		}
	}
}
