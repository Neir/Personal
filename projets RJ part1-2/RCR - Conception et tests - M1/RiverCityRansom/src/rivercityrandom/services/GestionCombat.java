/**
 * 
 */
package rivercityrandom.services;

import java.util.ArrayList;

import rivercityrandom.enumerations.COMMANDE;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface GestionCombat {
	
	
	public Joueur		getAlex();
	public Joueur		getRyan();
	public Personnage		getSlick();
	public ArrayList<Gangster>	getListGangster();
	public ArrayList<Personnage> getListPersonnages();
	
	public Terrain		getTerrain();
	
	public void			gerer(COMMANDE cR, COMMANDE cA);
	public void 		gerer1Joueur(Joueur j, COMMANDE c);
	public void 		tomber(Personnage p);
	public void 		frappe(Personnage a);

	public void			init(int largeur, int hauteur, int profondeur);

}
