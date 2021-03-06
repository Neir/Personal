/**
 * 
 */
package rivercityrandom.services;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public interface Personnage {

	// ------------------------------------------------------------------------
	// Observators
	// ------------------------------------------------------------------------
	
	public String 		getNom(); // const
	public int 			getLargeur(); // const
	public int 			getHauteur(); // const
	public int 			getProfondeur(); // const
	public int 			getPvMax(); // const
	
	public int 			getForce();
	public int 			getPv();
	public int 			getPos_x();
	public int 			getPos_y();
	public int 			getPos_z();
	public boolean		orientation_a_droite();
	public boolean		en_saut();
	
	public boolean		a_equipement();
	public Objet		l_objet_equipe();
	public Personnage	le_personnage_equipe();
	
	// Incrémente le gèle de 1 à chaque pas de jeu.
	public boolean		estPorte();
	// Renvoie le nombre de pas où le personnage est gelé.
	public int			estGele();
	public boolean 		estVaincu();
	
	// inv: estVaincu() [min]== getPv() <= 0
	// inv: a_equipement() [min]== l_objet_equipe() != null 
	// 									|| le_personnage_equipe() != null
	// inv: if estPorte() == true
	//		then
	// 			estGele() > 0
	// inv: getPos_x() >= 0
	// inv: getPos_y() >= 0
	// inv: getPos_z() >= 0
	// inv: getForce() >= 0
	
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------
	
	/**
	 * pre: nom != ""
	 * pre: largeur > 0
	 * pre: hauteur > 0
	 * pre: profondeur > 0
	 * pre: pvMax > force
	 * post: getNom() == nom
	 * post: getLargeur() == largeur
	 * post: getHauteur() == hauteur
	 * post: getProfondeur() == profondeur
	 * post: getPvMax() == pvMax
	 * post: getForce() == force
	 * post: getPv() == pvMax
	 * post: getPos_x() == pos_x
	 * post: getPos_y() == pos_y
	 * post: getPos_z() == pos_z
	 * post: orientation_a_droite() == true
	 * post: en_saut() == false
	 * post: l_objet_equipe() == null
	 * post: le_personnage_equipe() == null
	 * post: estPorte() == false
	 * post: estGele() == 0
	 */
	public void 		init(String nom, int largeur, int hauteur, 
					int profondeur, int force, int pvMax,
					int pos_x, int pos_y, int pos_z);
		
	
	
	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------
	
	/**
	 * pre: pv >= 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre - pv
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void retrait_de_pv(int pv);
	
	/**
	 * pre: pv >= 0
	 * pre: getPv() < getPvMax()
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: if  getPv()@pre + pv  > getPvMax()
	 *       then
	 *			getPv() == getPvMax()
	 *       else
	 *       	getPv() == getPv()@pre + pv
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void depot_de_pv(int pv);
	
	/**
	 * pre: a_equipement() == false
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == perso
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void ramasserPersonnage(Personnage perso);
	
	/**
	 * pre: a_equipement() == false
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == objet
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void ramasserObjet(Objet objet);
	
	/**
	 * pre: a_equipement() == true
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == null
	 * post: le_personnage_equipe() == null
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void jeter();
	
	/**
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == x
	 * post: getPos_y() == y
	 * post: getPos_z() == getPos_z()@pre
	 * post: if getPos_x() > getPos_x()@pre
	 * 		 then
	 * 			orientation_a_droite() == true
	 * 		 else if getPos_x() < getPos_x()@pre
	 * 		 then
	 * 			orientation_a_droite() == false
	 * 		 else
	 * 			orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void se_deplacer(int x, int y);
	
	/**
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == x
	 * post: getPos_y() == y
	 * post: getPos_z() == z
	 * post: if getPos_x() > getPos_x()@pre
	 * 		 then
	 * 			orientation_a_droite() == true
	 * 		 else if getPos_x() < getPos_x()@pre
	 * 		 then
	 * 			orientation_a_droite() == false
	 * 		 else
	 * 			orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == true
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == estGele()@pre
	 */
	public void sauter(int x, int y, int z);
	
	/**
	 * pre: estPorte() == false
	 * pre: estGele() > 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == true
	 * post: estGele() == 1
	 */
	public void se_fait_porter();
	
	/**
	 * pre: estPorte() == true
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == false
	 * post: estGele() == 1
	 */
	public void se_fait_jeter();
	
	/**
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: if estPorte()@pre == false && estGele()@pre - 1 >= 0
	 * 		 then
	 * 			estGele() == estGele()@pre - 1
	 * 		 else
	 * 			estGele() == estGele()@pre
	 */
	public void degeler();
	
	/**
	 * pre: target != null
	 * pre: estGele() == 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == 1
	 */
	public void frapper(Personnage target);
	
	/**
	 * pre: degats >= 0
	 * pre: estVaincu() == false
	 * post: getForce() == getForce()@pre
	 * post: getPv() == getPv()@pre - degats
	 * post: getPos_x() == getPos_x()@pre
	 * post: getPos_y() == getPos_y()@pre
	 * post: getPos_z() == getPos_z()@pre
	 * post: orientation_a_droite() == orientation_a_droite()@pre
	 * post: en_saut() == en_saut()@pre
	 * post: l_objet_equipe() == l_objet_equipe()@pre
	 * post: le_personnage_equipe() == le_personnage_equipe()@pre
	 * post: estPorte() == estPorte()@pre
	 * post: estGele() == 3
	 */
	public void estFrappe(int degats);
}
