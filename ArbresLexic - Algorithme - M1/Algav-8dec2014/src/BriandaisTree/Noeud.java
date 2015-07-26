package BriandaisTree;

public class Noeud {
	public char value;
	private Noeud frere;
	private Noeud fils;
	
	public Noeud(char Etiquette, Noeud frere, Noeud fils) {
		this.value = Etiquette;
		this.frere = frere;
		this.fils = fils;
	}
	
	public Noeud(){
		value = (char) -1;
		frere = null;
		fils = null;
		
	}	

	public Noeud getFrere() {
		return frere;
	}

	public void setFrere(Noeud frere) {
		this.frere = frere;
	}

	public Noeud getFils() {
		return fils;
	}

	public void setFils(Noeud fils) {
		this.fils = fils;
	}

}
