package bloc;

public class BlocDecorator implements BlocService {
	
	private final BlocService delegate;
	
	public BlocDecorator(BlocService delegate){
		this.delegate = delegate;
	}
	
	@Override
	public Type getType() {
		return this.delegate.getType();
	}

	@Override
	public Tresor getTresor() {
		return this.delegate.getTresor();
	}

	@Override
	public void init(Type type, Tresor tresor) {
		this.delegate.init(type, tresor);
	}

	@Override
	public void setTYPE(Type type) {
		this.delegate.setTYPE(type);
	}

	@Override
	public void setTresor(Tresor tresor) {
		this.delegate.setTresor(tresor);
	}
	
	@Override
	public boolean equals(Object o) {
		return delegate.equals(o);
	}
	
	@Override
	public String toString() {
		return delegate.toString();
	}
}
