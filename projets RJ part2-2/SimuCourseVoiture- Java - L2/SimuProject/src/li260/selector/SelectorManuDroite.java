package li260.selector;

import li260.tools.Initialisation;

public class SelectorManuDroite implements Selector {
	@Override
	public boolean isSelected() {
		if(Initialisation.comManu.equals("droite"))
			return true;
		return false;
	}

}
