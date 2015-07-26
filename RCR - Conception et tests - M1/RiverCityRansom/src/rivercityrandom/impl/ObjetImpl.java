/**
 * 
 */
package rivercityrandom.impl;

import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class ObjetImpl implements Objet {

	CONTENU_OBJET contenu;
	int valeur;
	boolean marchand;
	int cout;
	
	public ObjetImpl() {}

	/**
	 * @see rivercityrandom.services.Objet#getContenu()
	 */
	@Override
	public CONTENU_OBJET getContenu() {
		return contenu;
	}

	/**
	 * @see rivercityrandom.services.Objet#getValeur()
	 */
	@Override
	public int getValeur() {
		return valeur;
	}

	/**
	 * @see rivercityrandom.services.Objet#estMarchandable()
	 */
	@Override
	public boolean estMarchandable() {
		return marchand;
	}

	/**
	 * @see rivercityrandom.services.Objet#getCout()
	 */
	@Override
	public int getCout() {
		return cout;
	}

	/**
	 * @see rivercityrandom.services.Objet#init(rivercityrandom.enumerations.CONTENU_OBJET, int, boolean, int)
	 */
	@Override
	public void init(CONTENU_OBJET p_contenu, int p_valeur, boolean p_marchand,
			int p_cout) {

		this.contenu = p_contenu;
		this.valeur = p_valeur;
		this.marchand = p_marchand;
		this.cout = p_cout;
	}

}
