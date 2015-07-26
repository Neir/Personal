package AffichageBriandais;

import java.util.ArrayList;

public class Largeur {

	ArrayList<Integer> Occupe ;
	int a = 1;
	public Largeur(){
		Occupe =  new ArrayList<Integer>();
	}
	
	public void Occupe(int a){
		if(!Occupe.contains(a))
		Occupe.add(a);
	}
	public boolean EstOcuppe(int a){
		return Occupe.contains(a);
	}
	public int size(){
		return Occupe.size();
		
	}
}
