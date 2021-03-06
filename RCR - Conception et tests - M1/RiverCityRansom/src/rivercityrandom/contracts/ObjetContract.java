/**
 * 
 */
package rivercityrandom.contracts;

import rivercityrandom.decorators.ObjetDecorator;
import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class ObjetContract extends ObjetDecorator {

	public ObjetContract(Objet delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// Pas d'invariant dans ce service
	}
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------

	@Override
	public void init(CONTENU_OBJET p_contenu,
			int p_valeur,
			boolean p_marchand,
			int p_cout) {
		
		/* Préconditions */
		
		// pre: p_contenu != null
		if (!(p_contenu != null)) {
			throw new PreconditionError("Pas de contenu");
		}
		
		// pre: p_valeur >= 0
		if (!(p_valeur >= 0)) {
			throw new PreconditionError("Valeur négative");
		}
		
		// pre: p_cout >= 0
		if (!(p_cout >= 0)) {
			throw new PreconditionError("Cout négatif");
		}
		
		
		// pre inv
		checkInvariant();
		
		super.init(p_contenu, p_valeur, p_marchand, p_cout);
		
		// post inv
		checkInvariant();
		
		
		/* Postconditions */
		
		// post: getContenu() == p_contenu
		if (!(getContenu() == p_contenu)) {
			throw new PostconditionError("Contenu mal initialisé");
		}
		
		// post: getValeur() == p_valeur
		if (!(getValeur() == p_valeur)) {
			throw new PostconditionError("Valeur mal initialisée");
		}
		
		// post: getContenu() == p_contenu
		if (!(estMarchandable() == p_marchand)) {
			throw new PostconditionError("Marchandable mal initialisé");
		}
		
		// post: getCout() == p_cout
		if (!(getCout() == p_cout)) {
			throw new PostconditionError("Contenu mal initialisé");
		}
	}
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
}
