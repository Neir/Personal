/**
 * 
 */
package rivercityrandom.services;

import rivercityrandom.enumerations.COMMANDE;
import rivercityrandom.enumerations.RESULTAT;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface MoteurJeu {

	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	
	public boolean 			estFini();
	public RESULTAT			getResultatFinal();
	public GestionCombat 	getCombat();
	
	// inv: estFini() [min]== (getCombat().getRyan().estVaincu()
	//      					&& getCombat().getAlex().estVaincu())
	//      					|| getCombat().getSlick().estVaincu()
	//
	// inv: if getCombat().getSlick().estVaincu() 
	// 				&& !getCombat().getRyan().estVaincu()
	// 		then
	// 			getResultatFinal() == SLICK_M_RYAN_V
	// 		else if getCombat().getSlick().estVaincu()
	// 				&& getCombat().getRyan().estVaincu()
	//				&& !getCombat().getAlex().estVaincu()
	//		then
	//			getResultatFinal() == SLICK_M_RYAN_M_ALEX_V
	// 		else if !getCombat().getSlick().estVaincu()
	// 				&& getCombat().getRyan().estVaincu()
	//				&& getCombat().getAlex().estVaincu()
	//		then
	//			getResultatFinal() == SLICK_V_RYAN_M_ALEX_M
	// 		else if getCombat().getSlick().estVaincu()
	// 				&& getCombat().getRyan().estVaincu()
	//				&& getCombat().getAlex().estVaincu()
	//		then
	//			getResultatFinal() == TOUS_M
	//		else
	//			getResultatFinal() == null
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * pre: largeur >= 256
	 * pre: hauteur >= 240
	 * pre: profondeur >= 100
	 * post: estFini() == false
	 * post: getCombat().getTerrain().getLargeur() == largeur
	 * post: getCombat().getTerrain().getHauteur() == hauteur
	 * post: getCombat().getTerrain().getProfondeur() == profondeur
	 */
	public void 			init(int largeur, int hauteur, int profondeur);
		
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	/**
	 * post: getCombat() == GestionCombat::gerer(COMMANDE cR, COMMANDE cA)
	 */
	public void				pasJeu(COMMANDE cR, COMMANDE cA);
}
