package pobj.algogen;

import java.util.Comparator;

public class ComparatorIndividu implements Comparator<Individu>{

	@Override
	public int compare(Individu i1, Individu i2) {
		return i2.compareTo(i1);
	}

}
