package bloc;

import error.PostconditionError;
import error.PreconditionError;

public class BlocContract extends BlocDecorator{

	public BlocContract(BlocService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		//N'a aucun Invariant
	}

	/* Initializers */
	public void init(Type type, Tresor tresor){
		//\pre : init(type,tresor) require type !=null et tresors!=null
		if(tresor ==null || type == null)
			throw new PreconditionError("Erreur paramètre null");

		super.init(type, tresor);

		this.checkInvariant();
		//\post :getType(init(type,tresor)) = type
		if(getType() != type)
			throw new PostconditionError("La modification du type de bloc n'a pas été éffectuer");
		//\post :getType(init(type,tresor)) = tresor
		if(getTresor() != tresor)
			throw new PostconditionError("La modification du du comptenue du bloc n'a pas été éffectuer");
	}

	/* Operateur */

	/**
	 * 
	 */
	public void setTYPE(Type type){
		//\pre : setType(B,type) require type!= null
		if(type == null)
			throw new PreconditionError("Erreur paramètre null");
		//aucune précondition
		//capture
		Tresor t = getTresor();

		super.setTYPE(type);

		checkInvariant();
		//\post : setType(B,type) = type 
		if(!(type == getType()))
			throw new PostconditionError("le type de bloc n'a pas été modifier");
		//\post : getTresor(setType(B,type))@pre = getTresor(setType(B,type)) 
		if(!(t == getTresor()))
			throw new PostconditionError("le tresor de bloc a pas été modifier");

	}

	public void setTresor(Tresor tresor){
		//\pre : setTresor(B, tresor) require tresor!= null
		if(tresor == null)
			throw new PreconditionError("Erreur paramètre null");
		//aucune précondition
		//capture
		Type t = getType();

		super.setTresor(tresor);

		checkInvariant();
		
		//\post : setTresor(B, tresor) = tresor
		if(!(tresor == getTresor()))
			throw new PostconditionError("le trésor de bloc n'a pas été modifier");
		//\post : getType(setTresor(B,tresor))@pre = getType(setTresor(B,tresor))
		if(t != getType())
			throw new PostconditionError("le type de bloc a pas été modifier");
	}
}
