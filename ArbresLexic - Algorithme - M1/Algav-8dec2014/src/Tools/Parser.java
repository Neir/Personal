package Tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {

	public static ArrayList<String> parseBufferToWords(String buf){
		ArrayList<String> listMots = new ArrayList<String>();
		String motCourrant = "";
		
		for(int i=0 ; i<buf.length() ; i++) {
	        if(!Character.isLetter(buf.charAt(i))){
	            if(motCourrant != ""){
	                listMots.add(motCourrant.toLowerCase());
	                motCourrant = "";
	            }
	        } else {
	        	motCourrant += buf.charAt(i);
	        }
	    }
		return listMots;
	}
	
	public static ArrayList<String> parseFileToWords(String filename) throws IOException{
		ArrayList<String> listMots = new ArrayList<String>();
		InputStream ips;
		ips = new FileInputStream(filename);
		InputStreamReader ipsr = new InputStreamReader(ips);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		while ((ligne=br.readLine())!=null){
			listMots.addAll(Parser.parseBufferToWords(ligne));
		}
		
		return listMots;
	}
	
	public static void main(String[] args){
		ArrayList<String> listMots = Parser.parseBufferToWords("Salut je'(test!!?ma.fonction;(-�_��)=");
		
		System.out.println("Liste : "+listMots);
	}
}
