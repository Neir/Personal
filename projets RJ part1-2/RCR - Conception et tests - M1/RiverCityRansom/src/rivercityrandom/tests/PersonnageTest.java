/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.ObjetContract;
import rivercityrandom.contracts.PersonnageContract;
import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.InvariantError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.ObjetImpl;
import rivercityrandom.impl.PersonnageImpl;
import rivercityrandom.services.Objet;
import rivercityrandom.services.Personnage;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class PersonnageTest {

	private PersonnageContract personnage;
	
	public PersonnageTest() {
		personnage = null;
	}

	@Before
	public void initialisation() {
		personnage = new PersonnageContract(new PersonnageImpl());
	}
	
	@After
	public void afterTests() {
		personnage = null;
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation d'un personnage
	// ------------------------------------------------------------------------
	
	@Test
	public void initOK() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 11;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initOK :\n"
					+ "le nom est erroné",
					personnage.getNom() == nom);
			
			assertTrue("Personnage::initOK :\n"
					+ "la largeur est erronée",
					personnage.getLargeur() == largeur);
			
			assertTrue("Personnage::initOK :\n"
					+ "la hauteur est erronée",
					personnage.getHauteur() == hauteur);
			
			assertTrue("Personnage::initOK :\n"
					+ "la profondeur est erronée",
					personnage.getProfondeur() == profondeur);
			
			assertTrue("Personnage::initOK :\n"
					+ "la force est erronée",
					personnage.getForce() == force);
			
			assertTrue("Personnage::initOK :\n"
					+ "les pv max sont erronés",
					personnage.getPvMax() == pvMax);
			
			assertTrue("Personnage::initOK :\n"
					+ "les pv sont erronés",
					personnage.getPv() == pvMax);
			
			assertTrue("Personnage::initOK :\n"
					+ "la pos x est erronée",
					personnage.getPos_x() == pos_x);
			
			assertTrue("Personnage::initOK :\n"
					+ "la pos y est erronée",
					personnage.getPos_y() == pos_y);
			
			assertTrue("Personnage::initOK :\n"
					+ "la pos z est erronée",
					personnage.getPos_z() == pos_z);
			
			assertTrue("Personnage::initOK :\n"
					+ "l'orientation à droite est erronée",
					personnage.orientation_a_droite() == true);
			
			assertTrue("Personnage::initOK :\n"
					+ "l'état en saut est erroné",
					personnage.en_saut() == false);
			
			assertTrue("Personnage::initOK :\n"
					+ "l'objet équipé est erroné",
					personnage.l_objet_equipe() == null);
			
			assertTrue("Personnage::initOK :\n"
					+ "le personnage équipé est erroné",
					personnage.le_personnage_equipe() == null);
			
			assertTrue("Personnage::initOK :\n"
					+ "l'état est porté est erroné",
					personnage.estPorte() == false);
			
			assertTrue("Personnage::initOK :\n"
					+ "l'état est gelé est erroné",
					personnage.estGele() == 0);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			String nom = "";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 11;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO1 :\n" +
					"aucune exception levée alors que le nom n'est pas défini",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO2() {
		
		try {
			String nom = "Toto";
			int largeur = 0;
			int hauteur = 11;
			int profondeur = 11;
			int force = 11;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO2 :\n" +
					"aucune exception levée alors que la largeur est de 0",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO3() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 0;
			int profondeur = 11;
			int force = 11;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO3 :\n" +
					"aucune exception levée alors que la hauteur est de 0",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO4() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 0;
			int force = 11;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO4 :\n" +
					"aucune exception levée alors que la profondeur est de 0",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO4 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO5() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = -1;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO5 :\n" +
					"aucune exception levée alors que la force est négative",
					false);
		}
		catch (InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO5 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO6() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 100;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO6 :\n" +
					"aucune exception levée alors que la force "
					+ "n'est pas inférieure aux pv",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO6 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO7() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = -1;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO7 :\n" +
					"aucune exception levée alors que la pos x est négative",
					false);
		}
		catch (InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO7 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO8() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = -1;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO8 :\n" +
					"aucune exception levée alors que la pos y est négative",
					false);
		}
		catch (InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO8 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO9() {
		
		try {
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = -1;
			
			personnage.init(nom, largeur, hauteur, profondeur, 
					force, pvMax, pos_x, pos_y, pos_z);
			
			assertTrue("Personnage::initKO9 :\n" +
					"aucune exception levée alors que la pos z est négative",
					false);
		}
		catch (InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::initKO9 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST: retrait_de_pv retire les degats de la vie du personnage
	// ------------------------------------------------------------------------
	
	@Test
	public void retrait_de_pvOK() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 5;
			personnage.retrait_de_pv(pv);
			
			assertTrue("Personnage::retrait_de_pvOK : \n" +
					"Les pv du personnages n'ont pas été correctement diminués",
					personnage.getPv() == pvMax - pv);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::retrait_de_pvOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void retrait_de_pvKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 5;
			personnage.retrait_de_pv(pvMax);
			personnage.retrait_de_pv(pv);
			
			assertTrue("Personnage::retrait_de_pvKO1 : \n" +
					"aucune exception levée alors que le personnage " +
					"ne peut avoir un retrait de pv en étant mort",
					false);
			
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::retrait_de_pvKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void retrait_de_pvKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = -1;
			personnage.retrait_de_pv(pvMax);
			personnage.retrait_de_pv(pv);
			
			assertTrue("Personnage::retrait_de_pvKO1 : \n" +
					"aucune exception levée alors que les pv à retirer " +
					"sont négatifs",
					personnage.getPv() == pvMax - pv);
			
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::retrait_de_pvKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : depot_de_pv ajoute le bonus à la vie du personnage
	// ------------------------------------------------------------------------
	
	@Test
	public void depot_de_pvOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 10;
			personnage.retrait_de_pv(pv - 1);
			personnage.depot_de_pv(pv);
			
			assertTrue("Personnage::depot_de_pvOK1 : \n" +
					"le personnage n'a pas ses pv au max",
					personnage.getPv() == pvMax);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::depot_de_pvOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void depot_de_pvOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 10;
			personnage.retrait_de_pv(pv + 1);
			personnage.depot_de_pv(pv);
			
			assertTrue("Personnage::depot_de_pvOK2 : \n" +
					"le personnage n'a pas ses pv à pvMax-1",
					personnage.getPv() == pvMax-1);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::depot_de_pvOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void depot_de_pvKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 10;
			personnage.depot_de_pv(pv);
			
			assertTrue("Personnage::depot_de_pvKO1 : \n" +
					"aucune exception levée alors que le personnage" +
					" est déjà full life",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::depot_de_pvKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void depot_de_pvKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = -1;
			personnage.retrait_de_pv(10);
			personnage.depot_de_pv(pv);
			
			assertTrue("Personnage::depot_de_pvKO2 : \n" +
					"aucune exception levée alors que les pv à déposer " +
					"sont négatifs",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::depot_de_pvKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void depot_de_pvKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			int pv = 10;
			personnage.retrait_de_pv(pvMax);
			personnage.depot_de_pv(pv);
			
			assertTrue("Personnage::depot_de_pvKO3 : \n" +
					"aucune exception levée alors que le personnage est vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::depot_de_pvKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Frapper inflige bien les dégats de force du frappeur
	// 				et de son objet équipé à la cible		
	// ------------------------------------------------------------------------
	
	@Test
	public void frapperOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage target = new PersonnageContract(new PersonnageImpl());
			target.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.frapper(target);
			
			assertTrue("Personnage::frapperOK1 : \n" +
					"le personnage n'est pas gelé pour un tour " +
					"après avoir frappé",
					personnage.estGele() == 1);
			
			assertTrue("Personnage::frapperOK1 : \n" +
					"le personnage n'est pas gelé pour trois tours " +
					"après avoir été frappé",
					target.estGele() == 3);
			
			assertTrue("Personnage::frapperOK1 : \n" +
					"le personnage frappé n'a pas perdu autant de vie " +
					"que le frappeur a de force",
					target.getPv() == pvMax - force);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::frapperOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void frapperOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage target = new PersonnageContract(new PersonnageImpl());
			target.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserObjet(objet);

			personnage.frapper(target);
			
			assertTrue("Personnage::frapperOK2 : \n" +
					"le personnage n'est pas gelé pour un tour " +
					"après avoir frappé",
					personnage.estGele() == 1);
			
			assertTrue("Personnage::frapperOK2 : \n" +
					"le personnage n'est pas gelé pour trois tours " +
					"après avoir été frappé",
					target.estGele() == 3);
			
			assertTrue("Personnage::frapperOK2 : \n" +
					"le personnage frappé n'a pas perdu autant de vie " +
					"que le frappeur a de force avec son objet équipé",
					target.getPv() == pvMax - (force + objet.getValeur()));
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::frapperOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void frapperKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage target = null;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.frapper(target);
			
			assertTrue("Personnage::frapperKO1 : \n" +
					"aucune exception levée alors que la target est null",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::frapperKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void frapperKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage target = new PersonnageContract(new PersonnageImpl());
			target.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.estFrappe(1);
			
			personnage.frapper(target);
			
			assertTrue("Personnage::frapperKO2 : \n" +
					"aucune exception levée alors que le personnage " + 
					"qui doit frapper est gelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::frapperKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void frapperKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage target = new PersonnageContract(new PersonnageImpl());
			target.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.retrait_de_pv(pvMax);
			
			personnage.frapper(target);
			
			assertTrue("Personnage::frapperKO3 : \n" +
					"aucune exception levée alors que le personnage " + 
					"qui doit frapper est vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::frapperKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : ramasserPersonnage équipe bien le personnage ramassé
	//	 					et celui-ci est dans l'état porté
	// ------------------------------------------------------------------------
	
	@Test
	public void ramasserPersonnageOK() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax - 1);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageOK : \n" +
					"le personnage équipé ne correspond pas au personnage " + 
					"ramassé",
					personnage.le_personnage_equipe() == perso);
			
			assertTrue("Personnage::ramasserPersonnageOK : \n" +
					"le personnage ramassé n'est pas dans l'état porté",
					perso.estPorte() == true);
			
			assertTrue("Personnage::ramasserPersonnageOK : \n" +
					"le personnage ramassé n'a pas un tour de gel",
					perso.estGele() == 1);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserPersonnageKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax - 1);
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserObjet(objet);
			
			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageKO1 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"déjà équipé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserPersonnageKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax - 1);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.estFrappe(pvMax - 1);
			
			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageKO2 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"gelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserPersonnageKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax - 1);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.retrait_de_pv(pvMax);
			
			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageKO3 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserPersonnageKO4() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageKO4 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un personnage si celui-ci n'est " + 
					"pas gelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageKO4 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserPersonnageKO5() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserPersonnage(perso);
			
			assertTrue("Personnage::ramasserPersonnageKO5 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un personnage si celui-ci est " + 
					"vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserPersonnageKO5 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : ramasserObjet équipe bien l'objet ramassé
	// ------------------------------------------------------------------------
	
	@Test
	public void ramasserObjetOK() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserObjet(objet);
			
			assertTrue("Personnage::ramasserObjetOK : \n" +
					"l'objet équipé ne correspond pas à l'objet ramassé",
					personnage.l_objet_equipe() == objet);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserObjetOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserObjetKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserObjet(objet);
			
			personnage.ramasserObjet(objet);
			
			assertTrue("Personnage::ramasserObjetKO1 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"déjà équipé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserObjetKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserObjetKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.estFrappe(pvMax - 1);
			
			personnage.ramasserObjet(objet);
			
			assertTrue("Personnage::ramasserObjetKO2 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"gelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserObjetKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void ramasserObjetKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.retrait_de_pv(pvMax);
			
			personnage.ramasserObjet(objet);
			
			assertTrue("Personnage::ramasserObjetKO3 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas ramasser un nouvel équipement car il est " +
					"vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::ramasserObjetKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : jeter retire bien tout équipement et si l'équipement
	//	 						était un personnage, celui-ci n'est plus dans
	//	 						l'état porté
	// ------------------------------------------------------------------------
	
	@Test
	public void jeterOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserObjet(objet);
			
			personnage.jeter();
			
			assertTrue("Personnage::jeterOK1 : \n" +
					"l'objet équipé n'est pas null après un jet",
					personnage.l_objet_equipe() == null);
			
			assertTrue("Personnage::jeterOK1 : \n" +
					"le personnage équipé n'est pas null après un jet",
					personnage.le_personnage_equipe() == null);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			perso.estFrappe(pvMax - 1);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.ramasserPersonnage(perso);

			personnage.jeter();
			
			assertTrue("Personnage::jeterOK2 : \n" +
					"l'objet équipé n'est pas null après un jet",
					personnage.l_objet_equipe() == null);
			
			assertTrue("Personnage::jeterOK2 : \n" +
					"le personnage équipé n'est pas null après un jet",
					personnage.le_personnage_equipe() == null);
			
			assertTrue("Personnage::jeterOK2 : \n" +
					"le personnage jeté n'a pas un tour de gel",
					perso.estGele() == 1);
			
			assertTrue("Personnage::jeterOK2 : \n" +
					"le personnage jeté est toujours dans l'état porté",
					perso.estPorte() == false);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.jeter();
			
			assertTrue("Personnage::jeterKO1 : \n" +
					"aucune exception levée alors que le personnage n'est pas " + 
					"équipé et n'a donc rien à jeter",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserObjet(objet);
			
			personnage.estFrappe(pvMax - 1);
			
			personnage.jeter();
			
			assertTrue("Personnage::jeterKO2 : \n" +
					"aucune exception levée alors que le personnage ne " +
					"peut pas jeter un équipement car il est gelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Objet objet = new ObjetContract(new ObjetImpl());
			objet.init(CONTENU_OBJET.POUBELLEMETALLIQUE, 10, false, 0);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserObjet(objet);
			
			personnage.retrait_de_pv(pvMax);
			
			personnage.jeter();
			
			assertTrue("Personnage::jeterKO3 : \n" +
					"aucune exception levée alors que le personnage " +
					"est vaincu et ne peut donc rien jeter",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterKO4() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserPersonnage(perso);
			
			perso.retrait_de_pv(pvMax);
			
			personnage.jeter();
			
			assertTrue("Personnage::jeterKO4 : \n" +
					"aucune exception levée alors que le personnage " +
					"devant être jeté est vaincu",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterKO4 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void jeterKO5() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			Personnage perso = new PersonnageContract(new PersonnageImpl());
			perso.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.ramasserPersonnage(perso);
			
			perso.se_fait_jeter();
			
			personnage.jeter();
			
			assertTrue("Personnage::jeterKO5 : \n" +
					"aucune exception levée alors que le personnage " +
					"devant être jeté n'est déjà plus dans l'état porté",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::jeterKO5 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : degeler retire un tour de gèle si le personnage 
	//	 						est gelé et n'est pas porté
	// ------------------------------------------------------------------------
	
	@Test
	public void degelerOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);

			personnage.estFrappe(pvMax - 1);
			
			int estGelePre = personnage.estGele();
			
			personnage.degeler();
			
			assertTrue("Personnage::degelerOK1 : \n" +
					"le personnage n'a pas perdu un tour de gel",
					personnage.estGele() == estGelePre - 1);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::degelerOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void degelerOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.degeler();
			
			assertTrue("Personnage::degelerOK2 : \n" +
					"le personnage est déjà gelé alors qu'il vient d'être créé",
					personnage.estGele() == 0);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::degelerOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void degelerOK3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.estFrappe(pvMax - 1);
			
			personnage.se_fait_porter();
			
			int estGelePre = personnage.estGele();
			
			personnage.degeler();
			
			assertTrue("Personnage::degelerOK3 : \n" +
					"le gel du personnage a été modifié alors qu'il est porté",
					personnage.estGele() == estGelePre);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::degelerOK3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void degelerKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.retrait_de_pv(pvMax);
			
			personnage.degeler();
			
			assertTrue("Personnage::degelerKO1 : \n" +
					"aucune exception levée alors que le personnage " +
					"est vaincu et ne peut donc pas être dégelé",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::degelerKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : se_deplacer modifie les coordonnées du personnage et
	//	 						change son orientation en fonction de la
	//	 						position précédente
	// ------------------------------------------------------------------------
	
	@Test
	public void se_deplacerOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 6;
			int y = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerOK1 : \n" +
					"le personnage n'a pas modifié sa coordonnée x " +
					"correctement",
					personnage.getPos_x() == x);
			
			assertTrue("Personnage::se_deplacerOK1 : \n" +
					"le personnage n'a pas modifié sa coordonnée y " +
					"correctement",
					personnage.getPos_y() == y);
			
			assertTrue("Personnage::se_deplacerOK1 : \n" +
					"le personnage a modifié sa coordonnée z alors qu'il " +
					"n'a pas sauté",
					personnage.getPos_z() == pos_z);
			
			assertTrue("Personnage::se_deplacerOK1 : \n" +
					"le personnage n'est pas orienté à droite alors qu'il " +
					"s'est déplacé vers la droite",
					personnage.orientation_a_droite() == true);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void se_deplacerOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 4;
			int y = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerOK2 : \n" +
					"le personnage n'a pas modifié sa coordonnée x " +
					"correctement",
					personnage.getPos_x() == x);
			
			assertTrue("Personnage::se_deplacerOK2 : \n" +
					"le personnage n'a pas modifié sa coordonnée y " +
					"correctement",
					personnage.getPos_y() == y);
			
			assertTrue("Personnage::se_deplacerOK2 : \n" +
					"le personnage a modifié sa coordonnée z alors qu'il " +
					"n'a pas sauté",
					personnage.getPos_z() == pos_z);
			
			assertTrue("Personnage::se_deplacerOK2 : \n" +
					"le personnage est orienté à droite alors qu'il " +
					"s'est déplacé vers la gauche",
					personnage.orientation_a_droite() == false);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void se_deplacerKO1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 6;
			int y = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.estFrappe(pvMax - 1);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerKO1 : \n" +
					"aucune exception levée alors que le personnage " +
					"est gelé et ne peut donc pas se déplacer",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void se_deplacerKO2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 6;
			int y = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.retrait_de_pv(pvMax);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerKO2 : \n" +
					"aucune exception levée alors que le personnage " +
					"est vaincu et ne peut donc pas se déplacer",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void se_deplacerKO3() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = -1;
			int y = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerKO3 : \n" +
					"aucune exception levée alors que la coordonnée x " +
					"est négative",
					false);
		}
		catch (PreconditionError | InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerKO3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void se_deplacerKO4() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 6;
			int y = -1;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.se_deplacer(x, y);
			
			assertTrue("Personnage::se_deplacerKO4 : \n" +
					"aucune exception levée alors que la coordonnée y " +
					"est négative",
					false);
		}
		catch (PreconditionError | InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::se_deplacerKO4 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : sauter modifie les coordonnées du personnage et
	//	 						change son orientation en fonction de la 
	//	 						position précédente
	// ------------------------------------------------------------------------
	
	@Test
	public void sauterOK1() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 6;
			int y = 6;
			int z = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.sauter(x, y, z);
			
			assertTrue("Personnage::sauterOK1 : \n" +
					"le personnage n'a pas modifié sa coordonnée x " +
					"correctement",
					personnage.getPos_x() == x);
			
			assertTrue("Personnage::sauterOK1 : \n" +
					"le personnage n'a pas modifié sa coordonnée y " +
					"correctement",
					personnage.getPos_y() == y);
			
			assertTrue("Personnage::sauterOK1 : \n" +
					"le personnage n'a pas modifié sa coordonnée z " +
					"correctement",
					personnage.getPos_z() == z);
			
			assertTrue("Personnage::sauterOK1 : \n" +
					"le personnage n'est pas en saut après avoir sauté",
					personnage.en_saut() == true);
			
			assertTrue("Personnage::sauterOK1 : \n" +
					"le personnage n'est pas orienté à droite alors qu'il " +
					"s'est déplacé vers la droite",
					personnage.orientation_a_droite() == true);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::sauterOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void sauterOK2() {
		
		try {
			
			String nom = "Toto";
			int largeur = 11;
			int hauteur = 11;
			int profondeur = 11;
			int force = 10;
			int pvMax = 100;
			int pos_x = 5;
			int pos_y = 5;
			int pos_z = 5;
			
			int x = 4;
			int y = 6;
			int z = 6;
			
			personnage.init(nom, largeur, hauteur, profondeur,
					force, pvMax, pos_x, pos_y, pos_z);
			
			personnage.sauter(x, y, z);
			
			assertTrue("Personnage::sauterOK2 : \n" +
					"le personnage n'a pas modifié sa coordonnée x " +
					"correctement",
					personnage.getPos_x() == x);
			
			assertTrue("Personnage::sauterOK2 : \n" +
					"le personnage n'a pas modifié sa coordonnée y " +
					"correctement",
					personnage.getPos_y() == y);
			
			assertTrue("Personnage::sauterOK2 : \n" +
					"le personnage n'a pas modifié sa coordonnée z " +
					"correctement",
					personnage.getPos_z() == z);
			
			assertTrue("Personnage::sauterOK2 : \n" +
					"le personnage n'est pas en saut après avoir sauté",
					personnage.en_saut() == true);
			
			assertTrue("Personnage::sauterOK2 : \n" +
					"le personnage est orienté à droite alors qu'il " +
					"s'est déplacé vers la gauche",
					personnage.orientation_a_droite() == false);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Personnage::sauterOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	
}
