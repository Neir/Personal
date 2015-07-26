package pobj.algogen.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.xml.sax.helpers.DefaultHandler;


public class ChargeurConfig extends DefaultHandler {
	
	// Ligne écrite en format :
	// "nameParametre:valeurParametre"
	public static void chargerConfigPerso(String filename) throws IOException{

		try {
			FileReader fr;
			fr = new FileReader("config/"+filename);
			BufferedReader in = new BufferedReader(fr);
			String buf;
			char c;
			while((buf=in.readLine()) != null){
				int i = 0;
				String mot ="";
				String name ="";
				while(i<buf.length()){
					c=buf.charAt(i);
					if(c==':'){
						name = mot;
						mot ="";
					} else {
						mot+=c;
					}
					i++;
				}
				//System.out.println("param = "+name+", value = "+mot);
				if(!mot.isEmpty()&&!name.isEmpty()){
					Configuration.getInstance().setParameterValue(name, mot);
				}
			}
			fr.close();

			
		} catch (ClassCastException e) {
			throw new IOException("Le fichier n'est pas un fichier de configuration ou n'existe pas.", e);
		}
	}
	
	public static void sauverConfigPerso(String fileName, Configuration conf) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		
		for(Map.Entry<String, String> entry : conf.getParams().entrySet()){
			fw.write(entry.getKey()+":"+entry.getValue()+"\n");
		}
		
		fw.close();
	}

	public static void sauverConfigurationEnXML(String fileName, Configuration conf)
			throws IOException {
		FileWriter fw = new FileWriter(fileName);
		
		fw.write("<Configuration>\n");
		for(Map.Entry<String, String> entry : conf.getParams().entrySet()){
			fw.write("<parametre name='"+entry.getKey());
			fw.write("' value='"+entry.getValue());
			fw.write("'/>\n");
		}
		fw.write("</Configuration>\n");
		fw.close();
	}
	
	/**
	 * Import de la configuration sauvé par la sérialisation.
	 * 
	 * @param fileName
	 *            : nom du fichier sauvé par la sérialisation
	 * @throws IOException problème de lecture du fichier ou de son contenu
	 */
	public static Configuration chargerConfig(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Configuration conf = (Configuration) ois.readObject();
			return conf;
		} catch (ClassCastException e) {
			throw new IOException(
					"Le fichier ne contient pas une configuration.", e);
		} catch (ClassNotFoundException e) {
			throw new IOException(
					"JVM does not know the type Conf.", e);
		}
	}
	
	public static void sauverConfig(String filename, Configuration config) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(config);
		fos.close();
		
	}
}
