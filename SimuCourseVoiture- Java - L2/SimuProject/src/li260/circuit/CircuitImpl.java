package li260.circuit;

import java.util.ArrayList;

import li260.geometrie.Vecteur;

/*-Terrain[][] matrice
-Vecteur ptDepart
-Vecteur sensDepart
-Vecteur sensArrivee
*/

public class CircuitImpl implements Circuit {
	private Terrain[][] matrice;
	private Vecteur ptDepart;
	private Vecteur sensDepart;
	private Vecteur sensArrivee;
	
	public CircuitImpl(Terrain[][] matrice, Vecteur ptDepart, Vecteur sensDepart, Vecteur sensArrivee){
		this.matrice=matrice;
		this.ptDepart=ptDepart;
		this.sensDepart=sensDepart;
		this.sensArrivee=sensArrivee;
	}
	
	@Override
	public Terrain getTerrain(int i, int j) {
		return matrice[i][j];
	}

	@Override
	public Terrain getTerrain(Vecteur p) {
		//if(matrice[(int) p.getX()][(int) p.getY()] == Terrain.EndLine)
			//System.out.println("CIRCUITIMPL. endline"+p);
		return matrice[(int) p.getX()][(int) p.getY()];
	}

	@Override
	public Vecteur getPointDepart() {
		return ptDepart;
	}

	@Override
	public Vecteur getDirectionDepart() {
		return sensDepart;
	}

	@Override
	public Vecteur getDirectionArrivee() {
		return sensArrivee;
	}

	@Override
	public int getWidth() {
		return matrice[0].length;
	}

	@Override
	public int getHeight() {
		return matrice.length;
	}

	@Override
	public ArrayList<Vecteur> getArrivees() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
