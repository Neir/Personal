package isketch.objets;

public class User implements Comparable{
	private int nbPoints;
	private String name;
	
	public User(String name, int nbPoints) {
		super();
		this.nbPoints = nbPoints;
		this.name = name;
	}
	public int getNbPoints() {
		return nbPoints;
	}
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Permet de classer les joueurs dans le chat (en fonction de leurs points)
	//J'ai inversé la comparateur volontairement pour que le User ayant le plus de point se retrouve en début de liste
	// Il sera alors affiché en premier dans la
	public int compareTo(Object u2) {
		if (this.getNbPoints() > ((User) u2).getNbPoints()) {
			return 1;
		} else if (this.getNbPoints() < ((User) u2).getNbPoints()) {
			return -1;        	
		} else {
			return 0;
		}
	}
}
