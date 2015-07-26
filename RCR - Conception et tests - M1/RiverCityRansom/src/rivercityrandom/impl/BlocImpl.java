/**
 * 
 */
package rivercityrandom.impl;

import rivercityrandom.enumerations.TYPE_BLOC;
import rivercityrandom.services.Bloc;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class BlocImpl implements Bloc {

	private TYPE_BLOC typeBloc;
	private Objet tresor;
	
	public BlocImpl() {}

	/**
	 * @see rivercityrandom.services.Bloc#getTypeBloc()
	 */
	@Override
	public TYPE_BLOC getTypeBloc() {
		return typeBloc;
	}

	/**
	 * @see rivercityrandom.services.Bloc#getTresor()
	 */
	@Override
	public Objet getTresor() {
		return tresor;
	}

	/**
	 * @see rivercityrandom.services.Bloc#init(rivercityrandom.enumerations.TYPE_BLOC, rivercityrandom.services.Objet)
	 */
	@Override
	public void init(TYPE_BLOC type, Objet tresor) {
		typeBloc = type;
		this.tresor = tresor;
	}

	/**
	 * @see rivercityrandom.services.Bloc#changerTresor(rivercityrandom.services.Objet)
	 */
	@Override
	public void changerTresor(Objet tresor) {
		this.tresor = tresor;
	}

}
