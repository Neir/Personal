package li260.selector;

import li260.tools.Initialisation;

public class SelectorManuGauche implements Selector {

	@Override
	public boolean isSelected() {
		if(Initialisation.comManu.equals("gauche"))
			return true;
		return false;
	}

}
