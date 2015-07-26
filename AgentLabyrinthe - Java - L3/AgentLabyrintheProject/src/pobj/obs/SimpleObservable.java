package pobj.obs;

import java.util.ArrayList;
import java.util.List;

public class SimpleObservable implements ISimpleObservable{
	private List<ISimpleObserver> obs;
	
	public SimpleObservable () {
		obs = new ArrayList<ISimpleObserver>();
	}
	
	public void addObserver(ISimpleObserver observer) {
		obs.add(observer);
	}
	
	public void deleteObserver(ISimpleObserver observer) {
		obs.remove(observer);
	}
	
	public void notifyObservers() {
		for (ISimpleObserver o : obs)
			o.update();
	}
}
