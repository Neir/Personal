package isketch.objets;

import java.util.ArrayList;

public class Commande {
	private String com;
	private ArrayList<String> attributs;
	
	public Commande(String com) {
		super();
		this.com = com;
		attributs = new ArrayList<String>();
	}
	
	public Commande(String com, String attribut) {
		super();
		this.com = com;
		attributs = new ArrayList<String>();
		attributs.add(attribut);
	}
	
	public Commande(String com, ArrayList<String> attributs) {
		super();
		this.com = com;
		this.attributs = attributs;
	}
	
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public ArrayList<String> getAttributs() {
		return attributs;
	}
	public void setAttributs(ArrayList<String> attributs) {
		this.attributs = attributs;
	}
	public void addAttribut(String attribut){
		this.attributs.add(attribut);
	}
	
	public String getAttIndex(int i) {
		return attributs.get(i);
	}
	public void setAttIndex(String attribut, int i) {
		this.attributs.set(i, attribut);
	}

	@Override
	public String toString() {
		return "Commande [com=" + com + ", attributs=" + attributs + "]";
	}
	
}
