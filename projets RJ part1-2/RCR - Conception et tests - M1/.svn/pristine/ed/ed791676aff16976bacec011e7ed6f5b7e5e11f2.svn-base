/**
 * 
 */
package rivercityrandom.decorators;

import rivercityrandom.services.Bloc;
import rivercityrandom.services.Terrain;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class TerrainDecorateur implements Terrain {
	private final Terrain delegate;
	
	public TerrainDecorateur(Terrain delegate) {
		this.delegate = delegate;
	}
	
	protected Terrain getDelegate() {
		return delegate;
	}
	
	
	/**
	 * @see rivercityrandom.services.Terrain#getLargeur()
	 */
	@Override
	public int getLargeur() {
		return delegate.getLargeur();
	}

	/**
	 * @see rivercityrandom.services.Terrain#getHauteur()
	 */
	@Override
	public int getHauteur() {
		return delegate.getHauteur();
	}

	/**
	 * @see rivercityrandom.services.Terrain#getProfondeur()
	 */
	@Override
	public int getProfondeur() {
		return delegate.getProfondeur();
	}

	/**
	 * @see rivercityrandom.services.Terrain#getBloc(int, int, int)
	 */
	@Override
	public Bloc getBloc(int x, int y, int z) {
		return delegate.getBloc(x, y, z);
	}

	/**
	 * @see rivercityrandom.services.Terrain#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		delegate.init(largeur, hauteur, profondeur);
	}

}
