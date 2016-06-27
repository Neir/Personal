package Balle;

import li260.geometrie.Vecteur;


public class BalleClassiqueFactory implements BalleFactory{

	public Balle build() {
		return new BalleImpl(5, new Vecteur(1,1), new Vecteur(1,2), 16); //vitesse, pos, dir, taille
	}
	
	public Balle build(Vecteur position){
		return new BalleImpl(6, position, new Vecteur(1,-1), 16);
	}
}
