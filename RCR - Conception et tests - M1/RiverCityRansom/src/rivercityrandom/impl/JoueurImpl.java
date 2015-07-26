/**
 * 
 */
package rivercityrandom.impl;

import java.util.ArrayList;
import java.util.List;

import rivercityrandom.services.Joueur;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class JoueurImpl extends PersonnageImpl implements Joueur {

	private int somme_d_argent;
	private List<Objet> inventaire;
	
	/**
	 * @see rivercityrandom.services.Joueur#getSomme_d_argent()
	 */
	@Override
	public int getSomme_d_argent() {
		return somme_d_argent;
	}

	/**
	 * @see rivercityrandom.services.Joueur#getInventaire()
	 */
	@Override
	public List<Objet> getInventaire() {
		return inventaire;
	}

	/**
	 * @see rivercityrandom.services.Joueur#init(java.lang.String)
	 */
	@Override
	public void init(String nom) {
		int largeur = 30;
		int hauteur = 50;
		int profondeur = 10;
		int force = 10;
		int pvMax = 100;
		int x = 1;
		int y = 5;
		int z = 0;
		super.init(nom, largeur, hauteur, profondeur, force, pvMax, x, y, z);
		
		this.somme_d_argent = 20;
		this.inventaire = new ArrayList<Objet>();
		
	}

	/**
	 * @see rivercityrandom.services.Joueur#retrait_de_l_argent(int)
	 */
	@Override
	public void retrait_de_l_argent(int argent) {
		somme_d_argent -= argent;
	}

	/**
	 * @see rivercityrandom.services.Joueur#depot_de_l_argent(int)
	 */
	@Override
	public void depot_de_l_argent(int argent) {
		somme_d_argent += argent;
	}

	/**
	 * @see rivercityrandom.services.Joueur#retrait_objet_de_l_inventaire(rivercityrandom.services.Objet)
	 */
	@Override
	public void retrait_objet_de_l_inventaire(Objet objet) {
		inventaire.remove(objet);
	}

	/**
	 * @see rivercityrandom.services.Joueur#depot_objet_de_l_inventaire(rivercityrandom.services.Objet)
	 */
	@Override
	public void depot_objet_de_l_inventaire(Objet objet) {
		inventaire.add(objet);
	}

}
