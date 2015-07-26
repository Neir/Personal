/**
 * 
 */
package rivercityrandom.contracts;

import java.util.ArrayList;
import java.util.List;

import rivercityrandom.decorators.GestionCombatDecorator;
import rivercityrandom.enumerations.COMMANDE;
import rivercityrandom.errors.InvariantError;
import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.services.GestionCombat;
import rivercityrandom.services.Joueur;
import rivercityrandom.services.Objet;
import rivercityrandom.services.Personnage;
import rivercityrandom.services.Terrain;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class GestionCombatContract extends GestionCombatDecorator {

	//@pre
	private ArrayList<Personnage> 	getListePersonnagesPre;
	private Joueur 					getAlexPre;
	private Joueur 					getRyanPre;
	private Personnage 				getSlickPre;
	private Terrain 				getNiveau1Pre;
	
	
	/**
	 * @param delegate
	 */
	public GestionCombatContract(GestionCombat delegate) {
		super(delegate);
	}
	
	// ------------------------------------------------------------------------
	// TOOLS
	// ------------------------------------------------------------------------
	
	/**
	 * Permet de prendre une capture des observateurs de la gestion du combat
	 */
	public void captureImage() {
		getListePersonnagesPre 		= getListPersonnages();
		getAlexPre	 				= getAlex();
		getRyanPre 					= getRyan();
		getSlickPre 				= getSlick();
		getNiveau1Pre 				= getTerrain();
	}
	
	/**
	 * Permet de factoriser les tests de postconditions gÃ©nÃ©riques.
	 * La mÃ©thode captureImage doit avoir Ã©tÃ© appelÃ©e prÃ©cÃ©demment.
	 * @param cond_exclues Les postConditions Ã  exclure du test
	 */
	public void checkGenericPostcondition(List<Integer> cond_exclues) {
		
		if (cond_exclues.contains(1)) {
			// post: getListPersonnages() == getListPersonnages()@pre
			if (!(getListPersonnages() == getListePersonnagesPre)) {
				throw new PostconditionError("La liste des personnages a été modifié.");
			}
		}
		
		if (cond_exclues.contains(2)) {
			// post: getAlex() == getAlex()@pre
			if (getAlex() == getAlexPre) {
				throw new PostconditionError("Alex a été modifié.");
			}
		}
		
		if (cond_exclues.contains(3)) {
			// post: getRyan() == getRyan()@pre
			if (!(getRyan() == getRyanPre)) {
				throw new PostconditionError("Ryan a été modifié.");
			}
		}
		
		if (cond_exclues.contains(4)) {
			// post: getSlick() == getSlick()@pre
			if (!(getSlick() == getSlickPre)) {
				throw new PostconditionError("Slick a été modifié.");
			}
		}
		
		if (cond_exclues.contains(5)) {
			// post: getTerrain() == getTerrain()@pre
			if (!(getTerrain() == getNiveau1Pre)) {
				throw new PostconditionError("Le niveau 1 a été modifé.");
			}
		}
	}
	
	public void checkInvariant() {
		/*	∀ perso in personnageList
	     *	0 ≤ perso.pos_x ≤ Terrain::largeur(niveau1)
	     *	0 ≤ perso.pos_y ≤ Terrain::hauteur(niveau1)
	     *	0 ≤ perso.pos_z ≤ Terrain::profondeur(niveau1)
	     */
		for(Personnage perso : getListPersonnages()){
			if (perso.getPos_x() < 0 || perso.getPos_x() >= getTerrain().getLargeur()) {
				throw new InvariantError("Un personnage est sorti du terrain en largeur : x="+perso.getPos_x());
			}
			if (perso.getPos_y() < 0 || perso.getPos_y() >= getTerrain().getHauteur()) {
				throw new InvariantError("Un personnage est sorti du terrain en hauteur : y="+perso.getPos_y());
			}
			if (perso.getPos_z() < 0 || perso.getPos_z() >= getTerrain().getProfondeur()) {
				throw new InvariantError("Un personnage est sorti du terrain en profondeur : z="+perso.getPos_z());
			}
		}
	}
	
	// ------------------------------------------------------------------------
	// Constructors
	// ------------------------------------------------------------------------

	/**
	 * @see rivercityrandom.services.GestionCombat#init(int, int, int)
	 */
	@Override
	public void init(int largeur, int hauteur, int profondeur) {

		// Préconditions
		// vide

		// Invariants
		checkInvariant();

		// run
		super.init(largeur, hauteur, profondeur);

		// Invariants
		checkInvariant();

		// Postconditions

		// post: niveau1(init(l,h,p))=Terrain.init(l,h,p)
		if (!(getTerrain().getLargeur()==largeur && 
				getTerrain().getHauteur()==hauteur &&
				getTerrain().getProfondeur()==profondeur)) {
			throw new PostconditionError("Le terrain n'est pas bien initialisé");
		}
		/*
		 * liste_personnages(init(l,h,p)) = {
		 * 	slick(init(l,h,p)) = Personnage::init("Slick",90,150,30,50,10000, 
		 * 	Terrain::largeur(niveau1)-1,Terrain::hauteur(niveau1)/2, 0),
		 * 	ryan(init(l,h,p)) = Joueur::init("Ryan"),
		 * 	alex(init(l,h,p)) = Joueur::init("Alex"),
		 * 	Ganster::init("Asshole”,rand,rand,0)
		 * 	Ganster::init("Smurff”,rand,rand,0)
		 * 	Ganster::init("Jerk”,rand,rand,0)
		 * 	Ganster::init("MotherFucker”,rand,rand,0)
		 * }
		 */
		if (!(getListPersonnages().contains(getAlex()))) {
			throw new PostconditionError("Alex n'est pas dans la liste des personnages");
		}

		if (!(getListPersonnages().contains(getRyan()))) {
			throw new PostconditionError("Ryan n'est pas dans la liste des personnages");
		}

		if (!(getListPersonnages().contains(getSlick()))) {
			throw new PostconditionError("Slick n'est pas dans la liste des personnages");
		}
	}

	// ------------------------------------------------------------------------
	// Operators
	// ------------------------------------------------------------------------

	@Override
	public void gerer(COMMANDE cR, COMMANDE cA){
		
		// Préconditions
		// vide

		// Invariants
		checkInvariant();

		// capture
		getNiveau1Pre = getTerrain();
		getListePersonnagesPre = getListPersonnages();
		
		// run
		super.gerer(cR, cA);

		// Invariants
		checkInvariant();

		// Postconditions
	}

	public void gerer1Joueur(Joueur j, COMMANDE c){
		
		// pre gerer1Joueur(G,j,com) require Joueur::estVaincu(j)=false & Joueur::estGele(j)=0
		if (!(!j.estVaincu() && j.estGele()==0)) {
			throw new PreconditionError("On ne peux pas faire d'action sur ce joueur car il est gelé ou vaincu");
		}

		// Invariants
		checkInvariant();

		// capture
		Joueur pre = null;
		if(j.getNom().equals("Alex")){
			pre = getAlex();
		}else if(j.getNom().equals("Ryan")){
			pre = getRyan();
		}
		getNiveau1Pre = getTerrain();
		getListePersonnagesPre = getListPersonnages();
		
		// run
		super.gerer1Joueur(j, c);

		// Invariants
		checkInvariant();
		
		// Postconditions
		
// post : 	gerer1Joueur(j,com)=
//			    switch(com)
//			        cas GAUCHE :
//			            Joueur::se_deplacer(j, j.pos_x-1, j.pos_y)
//			            si ￢collisionGauche(G,j.pos_x-1)
//			        cas DROITE :
//			            Joueur::se_deplacer(j, j.pos_x+1, j.pos_y)
//			            si ￢collisionDroite(G,j.pos_x+1)
//			        cas HAUT :
//			            Joueur::se_deplacer(j, j.pos_x, j.pos_y+1)
//			            si ￢collisionHaut(G,j.pos_y+1)
//			        cas BAS :
//			            Joueur::se_deplacer(j, j.pos_x, j.pos_y-1)
//			            si ￢collisionBas(G,j.pos_y-1)
//			        cas FRAPPE :
//			            frappe(C, j)
//			        cas SAUT_SUR_PLACE :
//			            Joueur::sauter(j,j.pos_x,j.pos_y,j.pos_z+1)
//			        cas SAUT_GAUCHE :
//			            Joueur::sauter(j,j.pos_x-1,j.posy,j.pos_z+1)
//			            si ￢collisionGauche(G,j.pos_x-1)
//					cas SAUT_DROIT :
//			            Joueur::sauter(j,j.pos_x+1,j.posy,j.pos_z+1)
//			            si ￢collisionDroite(G,j.pos_x-1)
//			        cas PREND :
//			            prend(C,j)
//			            si ￢Joueur::a_equipement(j)
//			        cas JET :
//			            jet(C,j)
//			            si Joueur::a_equipement(j)

		switch(c){
		case GAUCHE :
			if(!(j.getPos_x()-1==pre.getPos_x())){
				throw new PostconditionError("Le joueur ne s'est pas déplacé vers la gauche.");
			}
			break;
		case DROITE :
			if(!(j.getPos_x()+1==pre.getPos_x())){
				throw new PostconditionError("Le joueur ne s'est pas déplacé vers la droite.");
			}
			break;
		case HAUT :
			if(!(j.getPos_y()+1==pre.getPos_y())){
				throw new PostconditionError("Le joueur ne s'est pas déplacé vers le haut.");
			}
			break;
		case BAS :
			if(!(j.getPos_y()-1==pre.getPos_y())){
				throw new PostconditionError("Le joueur ne s'est pas déplacé vers le bas.");
			}
			break;
		case SAUT_SUR_PLACE :
			if(!(j.getPos_z()==pre.getPos_z()+1)){
				throw new PostconditionError("Le joueur n'a pas sauté.");
			}
			break;
		case SAUT_GAUCHE :
			if(!(j.getPos_z()==pre.getPos_z()+1)){
				throw new PostconditionError("Le joueur n'a pas sauté.");
			}
			break;
		case SAUT_DROIT :
			if(!(j.getPos_z()==pre.getPos_z()+1)){
				throw new PostconditionError("Le joueur n'a pas sauté.");
			}
			break;
		case FRAPPE :
			int i = 0;
			for(Personnage p : getListPersonnages()){
				if(p.equals(j)) continue;
				if(collisionPersonnage(j,p)){
					if(j.orientation_a_droite()){
						if(!collisionDroite(p.getPos_x()+1))
							if(!(p.getPos_x()+1==getListePersonnagesPre.get(i).getPos_x())){
								throw new PostconditionError("Le joueur n'a pas été repoussé vers la droite.");
							}
					} else {
						if(!collisionGauche(p.getPos_x()-1))
							if(!(j.getPos_x()-1==getListePersonnagesPre.get(i).getPos_x())){
								throw new PostconditionError("Le joueur n'a pas été repoussé vers la gauche.");
							}
					}
				}

				// Le personnage frapp� perd son �quipement.
				if(p.a_equipement()){
					throw new PostconditionError("Le personnage frappé n'a pas laché son equipement");
				} else {
					if(p.l_objet_equipe()!=null){
						if(!(getTerrain().getBloc(p.getPos_x(), p.getPos_y(), p.getPos_z())==p.l_objet_equipe())){
							throw new PostconditionError("L'objet equipe n'est pas tombé sur le sol.");
						}
					} else {
						if(p.le_personnage_equipe().estPorte()){
							throw new PostconditionError("Le personnage porte n'a pas ete laché");
						}
					}
				}
				i++;
			}
			break;
		case PREND :
			Objet o;
			if(j.a_equipement()){
				throw new PostconditionError("Le personnage a déjà un equipement.");
			} else {
				if((o = (getTerrain().getBloc(j.getPos_x(), j.getPos_y(), j.getPos_z()).getTresor())) != null){
					if(o.estMarchandable()){
						if(!(j.getInventaire().contains(o)))
							throw new PostconditionError("L'objet n'a pas été ajouté à l'inventaire.");
					} else {
						if(!(j.l_objet_equipe()!=null)){
							throw new PostconditionError("L'objet n'a pas été équipé.");
						}
					}
					if(!(getTerrain().getBloc(j.getPos_x(), j.getPos_y(), j.getPos_z()).getTresor()==null)){
						throw new PostconditionError("L'objet n'a pas été ramassé.");
					}
				} else {
					for(Personnage p : getListPersonnages()){
						if(p.equals(j)) continue;
						if(p.getPos_x() == j.getPos_x() &&
								p.getPos_y() == j.getPos_y() &&
								p.getPos_z() == j.getPos_z() && 
								p.estGele()>0){
							if(!(j.le_personnage_equipe()!=null))
								throw new PostconditionError("Le joueur ne s'est pas équipé du personnage.");
							if(!(p.estPorte())){
								throw new PostconditionError("Le personnage n'est pas porté.");
							}
							break;
						}
					}
				}
			}
			break;
		case JET :
			if(j.a_equipement()){
				throw new PostconditionError("Le joueur n'a pas jeté son equipement");
			} else {
				if(j.l_objet_equipe()!=null){
					if(!(getTerrain().getBloc(j.getPos_x(), j.getPos_y(), j.getPos_z())==j.l_objet_equipe())){
						throw new PostconditionError("L'objet equipe n'est pas tombé sur le sol.");
					}
				} else {
					if(j.le_personnage_equipe().estPorte()){
						throw new PostconditionError("Le joueur n'a pas laché le personnage porté");
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	public boolean collisionPersonnage(Personnage p1, Personnage p2){
		int extGp1 = p1.getPos_x();
		int extDp1 = p1.getPos_x()+p1.getLargeur();
		int extGp2 = p2.getPos_x();
		int extDp2 = p2.getPos_x()+p2.getLargeur();
		int extHp1 = p1.getPos_y()+p1.getHauteur();
		int extBp1 = p1.getPos_y();
		int extHp2 = p2.getPos_y()+p2.getHauteur();
		int extBp2 = p2.getPos_y();			
		
		return (extGp1<=extDp2 && extDp1>=extGp2 && extBp1<=extHp2 && extHp1>=extBp2);
	}
	
	private boolean collisionGauche(int pos_x) {
		return pos_x >= 0;
	}
	
	private boolean collisionDroite(int pos_x) {
		return pos_x < getTerrain().getLargeur();
	}

	public void tomber(Personnage p, int index){
		// post : Personnage::pos_z(tomber(G,p)=p.pos_z-1 si Bloc::type(Terrain::bloc(niveau1,p.x,p.y,p.z))=null
		Personnage pre = getListePersonnagesPre.get(index);
		if(getTerrain().getBloc(p.getPos_x(), p.getPos_y(), p.getPos_z()).getTypeBloc() == null){
			if(!(p.getPos_z()-1==pre.getPos_z())){
				throw new PostconditionError("Le personnage n'est pas tombé.");
			}
		}
	}
}
