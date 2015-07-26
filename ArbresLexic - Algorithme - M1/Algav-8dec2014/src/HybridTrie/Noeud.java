package HybridTrie;

public class Noeud {
	private char etiquette;
	private int id;
	private Noeud inf;
	private Noeud eq;
	private Noeud sup;
	
	public Noeud(){
		etiquette = (char) -1;
		id = -1;
		inf = null;
		eq = null;
		sup = null;
	}
	
	public Noeud filsCorrespondant(char c){
		if(c < getEtiquette())	return inf;
		if(c > getEtiquette())	return sup;
		return eq;
	}
	
	public boolean estNil(){
		return (getEtiquette()==((char)-1));
	}
	
	public boolean estFeuille(){
		return inf.estNil() && eq.estNil() && sup.estNil();
	}
	
	public char getEtiquette() {
		return etiquette;
	}
	public void setEtiquette(char etiquette) {
		this.etiquette = etiquette;
		inf = new Noeud();
		eq = new Noeud();
		sup = new Noeud();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Noeud getInf() {
		return inf;
	}
	public void setInf(Noeud inf) {
		this.inf = inf;
	}
	public Noeud getEq() {
		return eq;
	}
	public void setEq(Noeud eq) {
		this.eq = eq;
	}
	public Noeud getSup() {
		return sup;
	}
	public void setSup(Noeud sup) {
		this.sup = sup;
	}
	
	public String toString(){
		String result;
		result = "(" + this.getEtiquette() +","+ this.getId() +")\n";
		result +=" / | \\ \n";
		
		return result;
	}

	public void setNil() {
		etiquette = (char) -1;
		id = -1;
		inf = null;
		eq = null;
		sup = null;
	}

	public void supprimerRec() {
		if(!this.estNil()){
			this.getEq().supprimerRec();
			this.setNil();
		}
	}
	
	public Noeud clone(){
		Noeud clone = new Noeud();
		clone.setEtiquette(etiquette);
		clone.setId(id);
		clone.setInf(inf);
		clone.setEq(eq);
		clone.setSup(sup);
		
		return clone;
	}

	public int hauteur() {
		if(this.estNil()){
			return 0;
		} else
			return 1 + Math.max(Math.max(this.getInf().hauteur(),
					this.getEq().hauteur()),
					this.getSup().hauteur());
	}
	/*
	public int getLargeurGauche(){
		if(this.estNil())
			return 0;
		return Math.max(1+this.getInf().getLargeurGauche(), this.getEq().getLargeurGauche());
	}
	
	public int getLargeurDroite(){
		if(this.estNil())
			return 0;
		return Math.max(1+this.getSup().getLargeurDroite(), this.getEq().getLargeurDroite());
	}
	//*/

	public int getLargeurGauche(){
		if(this.estNil()) return 0;
		return this.getEq().parcoursG() + this.getInf().parcoursD();
	}

	public int getLargeurDroite(){
		if(this.estNil()) return 0;
		return this.getEq().parcoursD() + this.getSup().parcoursG();
	}

	private int parcoursG(){
		if(this.estNil())
			return 0;
		else if(!this.getInf().estNil())
			return 1 + this.getInf().parcoursG() + this.getEq().parcoursG();
		else
			return this.getEq().parcoursG();
	}

	private int parcoursD(){
		if(this.estNil())
			return 0;
		else if(!this.getSup().estNil())
			return 1 + this.getSup().parcoursD() + this.getEq().parcoursD();
		else
			return this.getEq().parcoursD();
	}

	public int getLargeurTotal(){
		return this.estNil()? 0 : 1 + parcoursLargeurTotal();
	}

	private int parcoursLargeurTotal(){
		int largeur = 0;
		if(this.estNil())
			return 0;

		if(!this.getInf().estNil())
			largeur+=1+this.getInf().parcoursLargeurTotal();
		if(!this.getEq().estNil())
			largeur += this.getEq().parcoursLargeurTotal();
		if(!this.getSup().estNil())
			largeur+=1+this.getSup().parcoursLargeurTotal();

		return largeur;
	}
}
