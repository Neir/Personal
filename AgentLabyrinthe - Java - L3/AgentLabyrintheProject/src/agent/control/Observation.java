package agent.control;

import java.awt.Point;
import java.util.Arrays;

import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;


/**
 * Cette classe donne l'état des cases qui entourent une position.
 *  
 * @author Yann
 *
 */
public class Observation {

	/**
	 * Etat des cases environnantes.
	 */
	private ContenuCase[] capteurs = new ContenuCase[8];

	/**
	 * Crée une Observation, c'est à dire l'état des cases environnant la
	 * position fournie dans le labyrinthe. Remplit le tableau de capteurs avec
	 * les cases perçues dans le labyrinthe. Les cases qui débordent du labyrinthe sont remplies de ANY.
	 * 
	 * Sensors porte l'état des capteurs, dans la convention horaire: 0 = Nord,
	 * 1 = NE, 2 = E, 3 = SE, 4 = S, 5 = SO, 6 = O, 7 = NO
	 */
	public Observation (Point position, Labyrinthe laby) {
		capteurs[0] = laby.getContenuCase(position.x,
				position.y - 1);
		capteurs[1] = laby.getContenuCase(position.x + 1,
				position.y - 1);
		capteurs[2] = laby.getContenuCase(position.x + 1,
				position.y);
		capteurs[3] = laby.getContenuCase(position.x + 1,
				position.y + 1);
		capteurs[4] = laby.getContenuCase(position.x,
				position.y + 1);
		capteurs[5] = laby.getContenuCase(position.x - 1,
				position.y + 1);
		capteurs[6] = laby.getContenuCase(position.x - 1,
				position.y);
		capteurs[7] = laby.getContenuCase(position.x - 1,
				position.y - 1);
	}

	/**
	 * Crée une Observation basée sur une chaine de caractères composée de 8 caractère :
	 * # : mur, ? : ANY, . : point, ' ' : vide, A : Agent
	 * 
	 * Les caractères sont interprétés dans la convention horaire: 0 = Nord,
	 * 1 = NE, 2 = E, 3 = SE, 4 = S, 5 = SO, 6 = O, 7 = NO
	 * @param contenu
	 */
	public Observation(String contenu) {
		for (int i=0;i<contenu.length();i++){
			char symbole = contenu.charAt(i);
			switch (symbole) {
			case '?':
				capteurs[i]= ContenuCase.ANY;
				break;
			case '.':
				capteurs[i]= ContenuCase.POINT;
				break;
			case ' ':
				capteurs[i]= ContenuCase.VIDE;
				break;
			case '#':
				capteurs[i]= ContenuCase.MUR;
				break;
			default:
				capteurs[i]= ContenuCase.AGENT;
			}
		}
	}
	
	public ContenuCase getContenu(int position){
		return capteurs[position];
	}
	
	public void setContenu(int position, ContenuCase contenu){
		capteurs[position] = contenu;
	}

	/** Utilisé par clone() pour copier une Observation.
	 * 
	 * @param capteurs tableau de capteurs à copier dans l'objet.
	 */
	private Observation(ContenuCase[] capteurs) {
		this.capteurs = Arrays.copyOf(capteurs, capteurs.length);			
	}

	/**
	 * Rends vrai si la situation passée en argument est compatible avec la
	 * situation décrite dans this.
	 * 
	 * En d'autres termes, on doit avoir égalité de this et de situation sur
	 * toutes les cases ou this n'a pas de ANY. Ce test est donc asymétrique.
	 * 
	 * @param situation la situation à tester
	 * @return vrai si la situation est compatible avec this.
	 */
	public boolean matches(Observation situation) {
//		System.err.println("Sensors :");
//		System.err.println(this);
//		
//		System.err.println("capteurs :");
//		System.err.println(situation);
		ContenuCase [] conditions = capteurs;
		ContenuCase [] sensors = situation.capteurs;
		
		
		for (int i = 0; i < 8; i++) {
			if (conditions[i] != ContenuCase.ANY) {
				if (conditions[i] != sensors[i]) {
//					System.err.println("no match !");
					return false;
				}
			}
		}
//		System.err.println("match !");
		return true;
	}

	
	/**
	 * Copie cette observation.
	 */
	public Observation clone () {
		return new Observation(capteurs);
	}
	
	/**
	 * Pretty print.
	 */
	@Override
	public String toString() {
		StringBuilder retour = new StringBuilder();
		retour.append(capteurs[7]);
		retour.append(capteurs[0]);
		retour.append(capteurs[1]);				
		retour.append("\n");

		retour.append(capteurs[6]);
		retour.append("X");
		retour.append(capteurs[2]);
		retour.append("\n");

		retour.append(capteurs[5]);
		retour.append(capteurs[4]);
		retour.append(capteurs[3]);				
		retour.append("\n");
		return retour.toString();
	}
	
	public String getContenuString() {
		String s = "";
		for(int i = 0 ; i < 8 ; i++)
			s += capteurs[i];
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(capteurs);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Observation other = (Observation) obj;
		if (!Arrays.equals(capteurs, other.capteurs))
			return false;
		return true;
	}
	
	
	
}
