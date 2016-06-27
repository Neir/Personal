package Jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Tools.Tools;

public class JeuFactory {
	private String filename;


	public JeuFactory(String filename){
		this.filename=filename;
	}

	public Jeu build() {
		JeuImpl jeu;
		int nbLigne = 0;
		int nbCol = 0;
		int numLigne = 0;
		Brique[][] matrice=null;

		FileReader fr;
		try {
			fr = new FileReader(filename);

			BufferedReader in = new BufferedReader(fr);
			String buf = in.readLine();
			nbLigne=Integer.parseInt(buf);
			buf = in.readLine();
			nbCol=Integer.parseInt(buf);

			matrice=new Brique[nbLigne][nbCol];

			while((buf=in.readLine()) != null){
				//if (buf.length()!=nbCol) throw IOException;
				for(int i=0;i<buf.length();i++){
					matrice[numLigne][i]=Tools.briqueFromChar(buf.charAt(i));
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
		
		jeu = new JeuImpl(matrice, 46, 23);
		return jeu;
	}
}