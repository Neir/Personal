/**
 * 
 */
package rivercityrandom.contracts;

import rivercityrandom.decorators.TerrainDecorateur;
import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.services.Bloc;
import rivercityrandom.services.Terrain;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class TerrainContract extends TerrainDecorateur {

	public TerrainContract(Terrain delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		
		// throw new InvariantError
	}
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * @see rivercityrandom.services.Terrain#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		
		/* Préconditions */
		
		// pre: largeur > 0
		if (!(largeur > 0)) {
			throw new PreconditionError("Largeur nulle ou négative.");
		}
		
		// pre: hauteur > 0
		if (!(hauteur > 0)) {
			throw new PreconditionError("Hauteur nulle ou négative.");
		}
		
		// pre: profondeur > 0
		if (!(profondeur > 0)) {
			throw new PreconditionError("Profondeur nulle ou négative.");
		}
		
		
		
		// pre inv
		checkInvariant();
		
		// run
		super.init(largeur, hauteur, profondeur);
		
		// post inv
		checkInvariant();
		
		
		
		
		/* Postconditions */
		
		// post: getLargeur() == largeur
		if (!(getLargeur() == largeur)) {
			throw new PostconditionError(
					"La largeur n'est pas correctement initialisée.");
		}
		
		// post: getHauteur() == hauteur
		if (!(getHauteur() == hauteur)) {
			throw new PostconditionError(
					"La hauteur n'est pas correctement initialisée.");
		}
		
		// post: getProfondeur() == profondeur
		if (!(getProfondeur() == profondeur)) {
			throw new PostconditionError(
					"La profondeur n'est pas correctement initialisée.");
		}
		
		// post: forall x:Integer \in [0..largeur - 1] {
		//          forall y:Integer \in [0..profondeur - 1] {
		//				\exists z:Integer \in [0..hauteur-1] {
		//					getBloc(x, y, z) != null
		//				}
		//			}
		//      }
		for (int x = 0; x < getLargeur(); x++) {
			for (int y = 0; y < getProfondeur(); y++) {
				Bloc bloc = null;
				
				for (int z = 0; z < getHauteur(); z++) {
					if (getBloc(x, y, z) != null) {
						bloc = getBloc(x, y, z);
					}
				}
				
				if (bloc == null) {
					throw new PostconditionError(
							"Aucun bloc disponible au croisement "+x+"/"+y);
				}
			}
		}
	}
}
