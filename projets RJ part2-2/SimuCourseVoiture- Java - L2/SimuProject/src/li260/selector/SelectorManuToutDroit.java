package li260.selector;

import li260.tools.Initialisation;

public class SelectorManuToutDroit implements Selector {

	@Override
	public boolean isSelected() {
		if(Initialisation.comManu.equals("avant"))
			return true;
		return false;
	}

}
