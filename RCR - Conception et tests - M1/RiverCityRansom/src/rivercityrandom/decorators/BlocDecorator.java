/**
 * 
 */
package rivercityrandom.decorators;

import rivercityrandom.enumerations.TYPE_BLOC;
import rivercityrandom.services.Bloc;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class BlocDecorator implements Bloc {
	private final Bloc delegate;
	
	public BlocDecorator(Bloc delegate) {
		this.delegate = delegate;
	}

	/**
	 * @see rivercityrandom.services.Bloc#getTypeBloc()
	 */
	@Override
	public TYPE_BLOC getTypeBloc() {
		return delegate.getTypeBloc();
	}

	/**
	 * @see rivercityrandom.services.Bloc#getTresor()
	 */
	@Override
	public Objet getTresor() {
		return delegate.getTresor();
	}

	/**
	 * @see rivercityrandom.services.Bloc#init(rivercityrandom.enumerations.TYPE_BLOC, rivercityrandom.services.Objet)
	 */
	@Override
	public void init(TYPE_BLOC type, Objet tresor) {
		delegate.init(type, tresor);
	}

	/**
	 * @see rivercityrandom.services.Bloc#changerTresor(rivercityrandom.services.Objet)
	 */
	@Override
	public void changerTresor(Objet tresor) {
		delegate.changerTresor(tresor);
	}
	
	
}
