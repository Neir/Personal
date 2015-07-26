/**
 * 
 */
package rivercityrandom.decorators;

import rivercityrandom.enumerations.COMMANDE;
import rivercityrandom.enumerations.RESULTAT;
import rivercityrandom.services.GestionCombat;
import rivercityrandom.services.MoteurJeu;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class MoteurJeuDecorator implements MoteurJeu {
	protected final MoteurJeu delegate;
	
	public MoteurJeuDecorator(MoteurJeu delegate) {
		this.delegate = delegate;
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#estFini()
	 */
	@Override
	public boolean estFini() {
		return delegate.estFini();
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#getResultatFinal()
	 */
	@Override
	public RESULTAT getResultatFinal() {
		return delegate.getResultatFinal();
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#getCombat()
	 */
	@Override
	public GestionCombat getCombat() {
		return delegate.getCombat();
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		delegate.init(largeur, hauteur, profondeur);
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#pasJeu(rivercityrandom.enumerations.COMMANDE, rivercityrandom.enumerations.COMMANDE)
	 */
	@Override
	public void pasJeu(COMMANDE cR, COMMANDE cA) {
		 delegate.pasJeu(cR, cA);
	}

}
