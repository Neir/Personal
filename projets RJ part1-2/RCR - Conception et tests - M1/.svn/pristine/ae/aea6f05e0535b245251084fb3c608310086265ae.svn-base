/**
 * 
 */
package rivercityrandom.impl;

import java.util.Random;

import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.enumerations.TYPE_BLOC;
import rivercityrandom.services.Bloc;
import rivercityrandom.services.Objet;
import rivercityrandom.services.Terrain;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class TerrainImpl implements Terrain {

	private int 	largeur;
	private int 	hauteur;
	private int 	profondeur;
	
	private Bloc 	terrain[][][];

	public TerrainImpl() {}

	/**
	 * @see rivercityrandom.services.Terrain#getLargeur()
	 */
	@Override
	public int getLargeur() {
		return largeur;
	}

	/**
	 * @see rivercityrandom.services.Terrain#getHauteur()
	 */
	@Override
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * @see rivercityrandom.services.Terrain#getProfondeur()
	 */
	@Override
	public int getProfondeur() {
		return profondeur;
	}

	/**
	 * @see rivercityrandom.services.Terrain#getBloc(int, int, int)
	 */
	@Override
	public Bloc getBloc(int x, int y, int z) {
		return terrain[x][y][z];
	}

	/**
	 * @see rivercityrandom.services.Terrain#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.profondeur = profondeur;

		generate();
	}

	/**
	 * Génère un terrain en s'appuyant sur les champs 
	 * largeur, hauteur, profondeur.
	 */
	private void generate() {
		
		terrain = new Bloc[largeur][profondeur][hauteur];
		
		for (int x = 0; x < largeur; x++) {
			for (int y = 0; y < profondeur; y++) {
				
				Objet objet = null;
				
				if ((new Random()).nextInt(30) != 0) {
					objet = new ObjetImpl();
					objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 0, false, 0);
				}
				
				Bloc bloc = new BlocImpl();
				bloc.init(TYPE_BLOC.VIDE, objet);
				terrain[x][y][0] = bloc;
				
				
				for (int z = 1; z < hauteur; z++) {
					terrain[x][y][z] = null;
				}
			}
		}
	}
}
