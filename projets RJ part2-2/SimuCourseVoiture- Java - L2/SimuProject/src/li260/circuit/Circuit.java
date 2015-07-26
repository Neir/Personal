package li260.circuit;

import java.util.ArrayList;

import li260.geometrie.Vecteur;

/* +getTerrain(int i, int j): Terrain
+getTerrain(Vecteur p): Terrain
+getPointDepart(): Vecteur
+getDirectionDepart(): Vecteur
+getDirectionArrivee(): Vecteur
+ getWidth(): int
+ getHeight(): int
+ getArrivees(): ArrayList<Vecteur>
*/

public interface Circuit {

	public Terrain getTerrain(int i, int j);
	public Terrain getTerrain(Vecteur p);
	public Vecteur getPointDepart();
	public Vecteur getDirectionDepart();
	public Vecteur getDirectionArrivee();
	public int getWidth();
	public int getHeight();
	public ArrayList<Vecteur> getArrivees();
}
