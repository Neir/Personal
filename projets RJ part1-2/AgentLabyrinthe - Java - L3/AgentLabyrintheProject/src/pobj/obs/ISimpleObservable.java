package pobj.obs;

public interface ISimpleObservable {
	public void addObserver(ISimpleObserver so);
	public void deleteObserver(ISimpleObserver so);
}
