package li260.circuit;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import li260.geometrie.Vecteur;
import li260.tools.Tools;

public class CircuitFactory {
	private String filename;


	public CircuitFactory(String filename){
		this.filename=filename;
	}

	public Circuit build() {
		CircuitImpl circuit;
		int nbLigne;
		int nbCol;
		int numLigne = 0;
		Terrain[][] matrice=null;
		final Vecteur dirDepart = new Vecteur(0,1);
		final Vecteur dirArrivee = new Vecteur(0,1);
		Vecteur ptDepart = null;

		FileReader fr;
		try {
			fr = new FileReader(filename);

			BufferedReader in = new BufferedReader(fr);

			String buf = in.readLine();
			nbCol=Integer.parseInt(buf);
			buf = in.readLine();
			nbLigne=Integer.parseInt(buf);

			matrice=new Terrain[nbLigne][nbCol];

			while((buf=in.readLine()) != null){
				//if (buf.length()!=nbCol) throw IOException;
				for(int i=0;i<buf.length();i++){
					matrice[numLigne][i]=Tools.terrainFromChar(buf.charAt(i));
					if(matrice[numLigne][i]==Terrain.StartPoint){
						ptDepart= new Vecteur(numLigne, i);
					}
				}	
				numLigne++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circuit = new CircuitImpl(matrice, ptDepart, dirDepart, dirArrivee);
		return circuit;
	}

}
