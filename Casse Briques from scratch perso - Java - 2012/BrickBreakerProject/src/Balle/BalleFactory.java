package Balle;

import li260.geometrie.Vecteur;


public interface BalleFactory {
	public Balle build();
	public Balle build(Vecteur position);
}
