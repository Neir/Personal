package li260.selector;

import li260.tools.Initialisation;

public class SelectorManuArriere implements Selector {

	@Override
	public boolean isSelected() {
		if(Initialisation.comManu.equals("arriere"))
			return true;
		return false;
	}

}
