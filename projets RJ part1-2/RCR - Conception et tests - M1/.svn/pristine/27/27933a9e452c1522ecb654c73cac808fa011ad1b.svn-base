package bloc;

public interface BlocService {

	/* Observateur */
	public Type getType();
	public Tresor getTresor();
	
	/* Initializers */
	/**
	 *pre : init(type,tresor) require type !=null et tresors!=null
	 *post :getType(init(type,tresor)) = type
	 *post :getType(init(type,tresor)) = tresor
	 */
	public void init(Type type, Tresor tresor);
	
	/* Operateur */
	
	/**
	 * pre : setType(B,type) require type!= null
	 * post : getType(setType(B,type)) = type
	 * post : getTresor(setType(B,type))@pre = getTresor(setType(B,type)) 
	 */
	public void setTYPE(Type type);
	
	/**
	 * pre : setTresor(B, tresor) require tresor!= null
	 * post : getTresor(setTresor(B, tresor)) = tresor
	 * post : getType(setTresor(B,tresor))@pre = getType(setTresor(B,tresor))
	 */
	public void setTresor(Tresor tresor);
	
}
