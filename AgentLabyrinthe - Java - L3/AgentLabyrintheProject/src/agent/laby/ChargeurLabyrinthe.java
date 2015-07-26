package agent.laby;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe pour charger ou décharger un labyrinthe depuis un fichier.
 * 
 * Une méthode s'appuie sur la sérialisation {@link Serializable}.
 * L'autre sur un format XML (sortie OK, entrée non disponible). 
 * 
 * @author sigaud, Yann 
 */
public class ChargeurLabyrinthe {

	/**
	 * Import du labyrinthe sauvé par la sérialisation.
	 * 
	 * @param fileName
	 *            : nom du fichier sauvé par la sérialisation
	 * @throws IOException problème de lecture du fichier ou de son contenu
	 */
	public static Labyrinthe chargerLabyrinthe(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream("laby/"+fileName+".mze");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Labyrinthe laby = (Labyrinthe) ois.readObject();
			return laby;
		} catch (ClassCastException e) {
			throw new IOException(
					"Le fichier ne contient pas un Labyrinthe.", e);
		} catch (ClassNotFoundException e) {
			throw new IOException(
					"JVM does not know the type Laby.", e);
		}
	}

	/**
	 * Export du labyrinthe par la sérialisation
	 * @param le nom du fichier à charger (en convention {@link File})
	 * @throws IOException En cas de problème d'écriture dans ce fichier.
	 */
	public static void sauverLabyrinthe(String fileName, Labyrinthe laby)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(laby);
		fos.close();
	}

	/**
	 * Import du labyrinthe sauvé en XML Attention : ne fonctionne pas !
	 * 
	 * Lève l'exception standard "unsupported" !
	 * Evitez de faire cela en général.
	 * 
	 * @throws IOException
	 */
	public static void chargerLabyrintheEnXML() throws IOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Export du labyrinthe au format XML.
	 * 
	 * @throws IOException si problème d'écriture.
	 */
	public static void sauverLabyrintheEnXML(String fileName, Labyrinthe laby)
			throws IOException {
		FileWriter fw = new FileWriter(fileName);
		fw.write("<Laby>\n");
		for (int i = 0; i < laby.Xsize(); i++) {
			fw.write("<Row>");
			for (int j = 0; j < laby.Ysize(); j++) {
				fw.write(" <Element>" + laby.getContenuCase(i, j)
						+ "</Element>\n");
			}
			fw.write("</Row>\n");
		}
		fw.write("</Laby>\n");
		fw.close();
	}
}
