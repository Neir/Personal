/**
 * 
 */
package rivercityrandom.decorators;

import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class ObjetDecorator implements Objet {
	private final Objet delegate;
	
	public ObjetDecorator(Objet delegate) {
		this.delegate = delegate;
	}
	
	protected Objet getDelegate() {
		return delegate;
	}

	/**
	 * @see rivercityrandom.services.Objet#getContenu()
	 */
	@Override
	public CONTENU_OBJET getContenu() {
		return delegate.getContenu();
	}

	/**
	 * @see rivercityrandom.services.Objet#getValeur()
	 */
	@Override
	public int getValeur() {
		return delegate.getValeur();
	}

	/**
	 * @see rivercityrandom.services.Objet#estMarchandable()
	 */
	@Override
	public boolean estMarchandable() {
		return delegate.estMarchandable();
	}

	/**
	 * @see rivercityrandom.services.Objet#getCout()
	 */
	@Override
	public int getCout() {
		return delegate.getCout();
	}

	/**
	 * @see rivercityrandom.services.Objet#init(rivercityrandom.enumerations.CONTENU_OBJET, int, boolean, int)
	 */
	@Override
	public void init(CONTENU_OBJET p_contenu, int p_valeur, boolean p_marchand,
			int p_cout) {
		
		delegate.init(p_contenu, p_valeur, p_marchand, p_cout);
	}

}
