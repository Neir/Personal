package li260.selector;

import li260.tools.Initialisation;

public class SelectorAttendre implements Selector {

	@Override
	public boolean isSelected() {
		if(Initialisation.comManu.equals(""))
			return true;
		return false;
	}

}
