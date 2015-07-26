/**
 * 
 */
package rivercityrandom.impl;

import rivercityrandom.enumerations.COMMANDE;
import rivercityrandom.enumerations.RESULTAT;
import rivercityrandom.services.GestionCombat;
import rivercityrandom.services.MoteurJeu;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class MoteurJeuImpl implements MoteurJeu{
	public GestionCombat 	combat = new GestionCombatImpl();

	/**
	 * @see rivercityrandom.services.MoteurJeu#estFini()
	 */
	@Override
	public boolean estFini() {
		return ((getCombat().getRyan().estVaincu()
				&& getCombat().getAlex().estVaincu())
				|| getCombat().getSlick().estVaincu());
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#getResultatFinal()
	 */
	@Override
	public RESULTAT getResultatFinal() {
		if (getCombat().getSlick().estVaincu()
				&& !getCombat().getRyan().estVaincu())
			return RESULTAT.SLICK_M_RYAN_V;
		else if (getCombat().getSlick().estVaincu()
				&& getCombat().getRyan().estVaincu()
				&& !getCombat().getAlex().estVaincu())
			return RESULTAT.SLICK_M_RYAN_M_ALEX_V;
		else if (!getCombat().getSlick().estVaincu()
				&& getCombat().getRyan().estVaincu()
				&& getCombat().getAlex().estVaincu())
			return RESULTAT.SLICK_V_RYAN_M_ALEX_M;
		else if (getCombat().getSlick().estVaincu()
				&& getCombat().getRyan().estVaincu()
				&& getCombat().getAlex().estVaincu())
			return RESULTAT.TOUS_M;
		else
			return RESULTAT.NULLE;
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#getCombat()
	 */
	@Override
	public GestionCombat getCombat() {
		return combat;
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		combat.init(largeur, hauteur, profondeur);
	}

	/**
	 * @see rivercityrandom.services.MoteurJeu#pasJeu(rivercityrandom.enumerations.COMMANDE, rivercityrandom.enumerations.COMMANDE)
	 */
	@Override
	public void pasJeu(COMMANDE cR, COMMANDE cA) {
		if(getResultatFinal()==RESULTAT.NULLE)
			combat.gerer(cR, cA);
	}

}
