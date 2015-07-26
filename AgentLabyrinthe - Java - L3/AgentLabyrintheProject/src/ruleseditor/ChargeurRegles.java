package ruleseditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;

public class ChargeurRegles {
	/**
	 * Import des regles sauvé par la sérialisation.
	 * 
	 * @param fileName
	 *            : nom du fichier sauvé par la sérialisation
	 * @throws IOException problème de lecture du fichier ou de son contenu
	 */
	public static ArrayList<Regle> chargerRegles(String filename) throws IOException {
		try {
			FileReader fr;
			fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);
			String buf;
			
			ArrayList<Regle> lr = new ArrayList<Regle>();
			
			while((buf=in.readLine()) != null)
				lr.add(readRegle(buf));
			
			fr.close();
			return lr;
		} catch (ClassCastException e) {
			throw new IOException("Le fichier n'est pas un fichier de regle ou n'existe pas.", e);
		}
	}
	
	private static Regle readRegle(String line){
		Observation obs = null;
		Direction dir = null;
		
		char c;
		String mot ="";
		String name ="";
		
		for(int i = 0; i<line.length(); i++){
			c=line.charAt(i);

			if(c=='/'){
				if(name.equals("observation"))
					obs = new Observation(mot);
				if(name.equals("direction")){
					if(mot.equals("haut"))
						dir = Direction.HAUT;
					if(mot.equals("bas"))
						dir = Direction.BAS;
					if(mot.equals("gauche"))
						dir = Direction.GAUCHE;
					if(mot.equals("droite"))
						dir = Direction.DROITE;
				}
				mot="";
				continue;
			}
			if(c==':'){
				name = mot;
				mot ="";
			} else {
				mot+=c;
			}
			//System.out.println("c:"+c+"  name:"+name+" val:"+mot);
		}
		if(name.equals("observation"))
			obs = new Observation(mot);
		if(name.equals("direction")){
			if(mot.equals("haut"))
				dir = Direction.HAUT;
			if(mot.equals("bas"))
				dir = Direction.BAS;
			if(mot.equals("gauche"))
				dir = Direction.GAUCHE;
			if(mot.equals("droite"))
				dir = Direction.DROITE;
		}
		
		if(dir==null||obs==null){
			System.out.println("Erreur dans la lecture d'une regle");
			return null;
		}
		return new Regle(obs, dir);
	}
	
	/**
	 * Export du Regle par la sérialisation
	 * @param le nom du fichier à charger (en convention {@link File})
	 * @throws IOException En cas de problème d'écriture dans ce fichier.
	 */
	public static void sauverRegles(String filename, ArrayList<Regle> lr){
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			for(Regle r : lr){
				fw.write("observation:"+r.getConditions().getContenuString()+"/");
				switch (r.getAction()) {
				case HAUT:
					fw.write("direction:haut\n");
					break;
				case BAS:
					fw.write("direction:bas\n");
					break;
				case GAUCHE:
					fw.write("direction:gauche\n");
					break;
				case DROITE:
					fw.write("direction:droite\n");
					break;
				}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

