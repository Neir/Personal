/**
 * 
 */
package rivercityrandom.contracts;

import java.util.List;

import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.services.Joueur;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class JoueurContract extends PersonnageContract implements Joueur {

	public JoueurContract(Joueur delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// pas d'invariant
	}
	
	
	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------

	/**
	 * @see rivercityrandom.services.Joueur#getSomme_d_argent()
	 */
	@Override
	public int getSomme_d_argent() {
		return ((Joueur)delegate).getSomme_d_argent();
	}

	/**
	 * @see rivercityrandom.services.Joueur#getInventaire()
	 */
	@Override
	public List<Objet> getInventaire() {
		return ((Joueur)delegate).getInventaire();
	}
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	@Override
	public void init(String nom) {
		
		// Préconditions
		
		// pre: nom != ""
		if (!(nom != null && nom.trim() != "")) {
			throw new PreconditionError("Aucun nom renseigné.");
		}
		
		// Invariants
		checkInvariant();
		
		// run
		((Joueur)delegate).init(nom);
		
		// Invariants
		checkInvariant();
		
		// Postconditions
		
		// post: getNom() == nom
		if (!(getNom() == nom)) {
			throw new PostconditionError(
					"Le nom n'a pas été correctement initialisé.");
		}
		
		// post: getLargeur() == 30
		if (!(getLargeur() == 30)) {
			throw new PostconditionError(
					"La largeur n'a pas été correctement initialisée.");
		}
		
		// post: getHauteur() == 50
		if (!(getHauteur() == 50)) {
			throw new PostconditionError(
					"La hauteur n'a pas été correctement initialisée.");
		}
		
		// post: getProfondeur() == 10
		if (!(getProfondeur() == 10)) {
			throw new PostconditionError(
					"La profondeur n'a pas été correctement initialisée.");
		}
		
		// post: getForce() == 10
		if (!(getForce() == 10)) {
			throw new PostconditionError(
					"La force n'a pas été correctement initialisée.");
		}
		
		// post: getPvMax() == 100
		if (!(getPvMax() == 100)) {
			throw new PostconditionError(
					"Les pv n'ont pas été correctement initialisés.");
		}
		
		// post: getSomme_d_argent() == 20
		if (!(getSomme_d_argent() == 20)) {
			throw new PostconditionError(
					"La somme d'argent n'a pas été correctement initialisée.");
		}
		
		// post: getInventaire() == EMPTY
		if (!(getInventaire().isEmpty())) {
			throw new PostconditionError(
					"L'inventaire n'a pas été correctement initialisé.");
		}
	}

	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	public void retrait_de_l_argent(int argent) {
		
		// @pre
		int getSomme_d_argentPre;
		List<Objet>	getInventairePre;
		
		// Préconditions
		
		// pre: argent <= getSomme_d_argent()
		if (!(argent <= getSomme_d_argent())) {
			throw new PreconditionError(
					"Le personnage n'a pas assez d'argent.");
		}
		
		// pre: argent > 0
		if (!(argent > 0)) {
			throw new PreconditionError(
					"Impossible d'utiliser une somme négative.");
		}
		
		// Invariants
		checkInvariant();
		
		// capture
		getSomme_d_argentPre = getSomme_d_argent();
		getInventairePre = getInventaire();
		
		// run
		((Joueur)delegate).retrait_de_l_argent(argent);
		
		// Invariants
		checkInvariant();
		
		// Postconditions
		
		// post: getSomme_d_argent() == getSomme_d_argent()@pre - argent
		if (!(getSomme_d_argent() == getSomme_d_argentPre - argent)) {
			throw new PostconditionError(
					"L'argent n'a pas été correctement modifié.");
		}
		
		// post: getInventaire() == getInventaire()@pre
		if (!(getInventaire() == getInventairePre)) {
			throw new PostconditionError("L'inventaire a été modifié");
		}
	}
	
	public void depot_de_l_argent(int argent) {
		
		// @pre
		int getSomme_d_argentPre;
		List<Objet>	getInventairePre;
		
		// Préconditions
		
		// pre: argent > 0
		if (!(argent > 0)) {
			throw new PreconditionError(
					"Impossible d'utiliser une somme négative.");
		}
		
		// Invariants
		checkInvariant();
		
		// capture
		getSomme_d_argentPre = getSomme_d_argent();
		getInventairePre = getInventaire();
		
		// run
		((Joueur)delegate).depot_de_l_argent(argent);
		
		// Invariants
		checkInvariant();
		
		// Postconditions
		
		// post: getSomme_d_argent() == getSomme_d_argent()@pre + argent
		if (!(getSomme_d_argent() == getSomme_d_argentPre + argent)) {
			throw new PostconditionError(
					"L'argent n'a pas été modifié correctement.");
		}
		
		// post: getInventaire() == getInventaire()@pre
		if (!(getInventaire() == getInventairePre)) {
			throw new PostconditionError("L'inventaire a été modifié");
		}
	}
	
	public void	retrait_objet_de_l_inventaire(Objet objet) {
		
		// @pre
		int getSomme_d_argentPre;
		//List<Objet>	getInventairePre;
		
		// Préconditions
		
		
		// Invariants
		checkInvariant();
		
		// capture
		getSomme_d_argentPre = getSomme_d_argent();
		//getInventairePre = getInventaire();

		// run
		((Joueur)delegate).retrait_objet_de_l_inventaire(objet);
		
		// Invariants
		checkInvariant();
		
		// Postconditions
		
		// post: getSomme_d_argent() == getSomme_d_argent()@pre
		if (!(getSomme_d_argent() == getSomme_d_argentPre)) {
			throw new PostconditionError("L'argent a été modifié.");
		}
		
		// post: getInventaire() == getInventaire()@pre - objet
		if ( ! (!getInventaire().contains(objet)) ) {
			throw new PostconditionError(
					"L'inventaire n'a pas été correctement modifié.");
		}
	}
	
	public void	depot_objet_de_l_inventaire(Objet objet) {
		
		// @pre
		int getSomme_d_argentPre;
		//List<Objet>	getInventairePre;
		
		// Préconditions
		
		
		// Invariants
		checkInvariant();
		
		// capture
		getSomme_d_argentPre = getSomme_d_argent();
		//getInventairePre = getInventaire();
		
		// run
		((Joueur)delegate).depot_objet_de_l_inventaire(objet);
		
		// Invariants
		checkInvariant();
		
		// Postconditions
		
		// post: getSomme_d_argent() == getSomme_d_argent()@pre
		if (!(getSomme_d_argent() == getSomme_d_argentPre)) {
			throw new PostconditionError("L'argent a été modifié.");
		}
		
		// post: getInventaire() == getInventaire()@pre + objet
		if (!(getInventaire().contains(objet))) {
			throw new PostconditionError(
					"L'inventaire n'a pas été correctement modifié");
		}
	}
	
}
