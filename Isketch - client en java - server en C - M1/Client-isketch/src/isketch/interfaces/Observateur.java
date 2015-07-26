package isketch.interfaces;

import isketch.objets.Commande;

import java.util.ArrayList;

public interface Observateur {
	public void update(ArrayList<Commande> comList);
}
