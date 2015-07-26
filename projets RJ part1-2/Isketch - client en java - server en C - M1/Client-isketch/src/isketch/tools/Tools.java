package isketch.tools;

import java.util.ArrayList;

import isketch.objets.Commande;

public class Tools {
	
	
	// Une commande ressemble à
	// "/<commande>(/<attribut>)*"
	public static Commande interpretation(String commande){
		int i, offset = 0;
		String com = null;
		ArrayList<String> attributs = new ArrayList<String>();
		Commande result;
		if(commande.charAt(offset)=='/'){
			com = lireJusquaSlash(commande, ++offset);
			offset += com.length();
		}
		i=0;
		while(offset<commande.length()-1) {
			if(commande.charAt(offset)=='/'){
				attributs.add(lireJusquaSlash(commande, ++offset));
				offset += attributs.get(i).length();
				i++;
			}
		}
		return new Commande(com, attributs);
	}
	
	private static String lireJusquaSlash(String commande, int offset) {
		int cpt = offset;
		while(/*cpt<commande.length() &&*/ commande.charAt(cpt)!='/') cpt++;
		char[] result = new char[cpt-offset];
		commande.getChars(offset, cpt, result , 0);
		
		return new String(result);
	}
	
	public static EnumCommandes enumFromCommande(Commande com){
		if(com.getCom().equals("CONNECT")) return EnumCommandes.CONNECT;
		if(com.getCom().equals("CONNECTED")) return EnumCommandes.CONNECTED;
		if(com.getCom().equals("END_ROUND")) return EnumCommandes.END_ROUND;
		if(com.getCom().equals("EXIT")) return EnumCommandes.EXIT;
		if(com.getCom().equals("EXITED")) return EnumCommandes.EXITED;
		if(com.getCom().equals("GUESS")) return EnumCommandes.GUESS;
		if(com.getCom().equals("GUESSED")) return EnumCommandes.GUESSED;
		if(com.getCom().equals("LINE")) return EnumCommandes.LINE;
		if(com.getCom().equals("NEW_ROUND")) return EnumCommandes.NEW_ROUND;
		if(com.getCom().equals("SCORE_ROUND")) return EnumCommandes.SCORE_ROUND;
		if(com.getCom().equals("SET_COLOR")) return EnumCommandes.SET_COLOR;
		if(com.getCom().equals("SET_LINE")) return EnumCommandes.SET_LINE;
		if(com.getCom().equals("SET_SIZE")) return EnumCommandes.SET_SIZE;
		if(com.getCom().equals("WORD_FOUND")) return EnumCommandes.WORD_FOUND;
		if(com.getCom().equals("WORD_FOUND_TIMEOUT")) return EnumCommandes.WORD_FOUND_TIMEOUT;
		return null;
	}
	
	public static String stringFromCommande(Commande com){
		String str = "";
		
		str += "/" + com.getCom() + "/";
		for(String att : com.getAttributs())
			str += att + "/";
		str += "\n";
		return str;
	}
}
