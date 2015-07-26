/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.JoueurContract;
import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.JoueurImpl;
import rivercityrandom.impl.ObjetImpl;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class JoueurTest {

	private JoueurContract joueur;
	
	public JoueurTest() {
		joueur = null;
	}

	@Before
	public void initialisation() {
		joueur = new JoueurContract(new JoueurImpl());
	}
	
	@After
	public void afterTests() {
		joueur = null;
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation d'un joueur
	// ------------------------------------------------------------------------
	
	@Test
	public void initOK1() {
		
		try {
			
			String nom = "Toto";
			
			joueur.init(nom);
			
			assertTrue("Joueur::initOK1 :\n" +
					"le nom du joueur est erroné",
					joueur.getNom() == nom);
			
			assertTrue("Joueur::initOK1 :\n" +
					"la largeur du joueur est erronée",
					joueur.getLargeur() == 30);
			
			assertTrue("Joueur::initOK1 :\n" +
					"la hauteur du joueur est erronée",
					joueur.getHauteur() == 50);
			
			assertTrue("Joueur::initOK1 :\n" +
					"la profondeur du joueur est erronée",
					joueur.getProfondeur() == 10);
			
			assertTrue("Joueur::initOK1 :\n" +
					"la force du joueur est erronée",
					joueur.getForce() == 10);
			
			assertTrue("Joueur::initOK1 :\n" +
					"les pv du joueur sont erronés",
					joueur.getPvMax() == 100);
			
			assertTrue("Joueur::initOK1 :\n" +
					"la somme d'argent du joueur est erronée",
					joueur.getSomme_d_argent() == 20);
			
			assertTrue("Joueur::initOK1 :\n" +
					"l'inventaire du joueur n'est pas vide",
					joueur.getInventaire().isEmpty());
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::initOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			String nom = "";
			
			joueur.init(nom);
			
			assertTrue("Joueur::initKO1 :\n" +
					"aucune exception levée alors que le nom est null ou vide",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Le retrait d'argent retire la bonne somme d'argent
	// ------------------------------------------------------------------------
	
	@Test
	public void retrait_de_l_argentOK1() {
		
		try {
			
			String nom = "Toto";
			int argent = 10;
			
			joueur.init(nom);
			joueur.retrait_de_l_argent(argent);
			
			assertTrue("Joueur::retrait_de_l_argentOK1 :\n" +
					"la somme d'argent du joueur est erronée",
					joueur.getSomme_d_argent() == 20 - argent);

			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::retrait_de_l_argentOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void retrait_de_l_argentKO1() {
		
		try {
			
			String nom = "Toto";
			int argent = 0;
			
			joueur.init(nom);
			joueur.retrait_de_l_argent(argent);
			
			assertTrue("Joueur::retrait_de_l_argentKO1 :\n" +
					"aucune exception levée alors que l'argent à retirer " +
					"est négatif ou à 0",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::retrait_de_l_argentKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Le depot d'argent retire la bonne somme d'argent
	// ------------------------------------------------------------------------
	
	@Test
	public void depot_de_l_argentOK1() {
		
		try {
			
			String nom = "Toto";
			int argent = 10;
			
			joueur.init(nom);
			joueur.depot_de_l_argent(argent);
			
			assertTrue("Joueur::retrait_de_l_argentOK1 :\n" +
					"la somme d'argent du joueur est erronée",
					joueur.getSomme_d_argent() == 20 + argent);

			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::retrait_de_l_argentOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void depot_de_l_argentKO1() {
		
		try {
			
			String nom = "Toto";
			int argent = 0;
			
			joueur.init(nom);
			joueur.retrait_de_l_argent(argent);
			
			assertTrue("Joueur::retrait_de_l_argentKO1 :\n" +
					"aucune exception levée alors que l'argent à déposer " +
					"est négatif ou à 0",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::retrait_de_l_argentKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Le retrait d'objet retire bien l'objet de l'inventaire
	// ------------------------------------------------------------------------
	
	@Test
	public void retrait_objet_de_l_inventaireOK1() {
		
		try {
			
			String nom = "Toto";
			
			Objet objet = new ObjetImpl();
			objet.init(CONTENU_OBJET.POTIONVIE, 10, true, 10);
			
			joueur.init(nom);
			joueur.retrait_objet_de_l_inventaire(objet);
			
			assertTrue("Joueur::retrait_objet_de_l_inventaireOK1 :\n" +
					"l'inventaire du joueur contient encore l'objet à retirer",
					!joueur.getInventaire().contains(objet));
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::retrait_objet_de_l_inventaireOK1 :\n" + 
					e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Le depot d'objet ajoute bien l'objet à l'inventaire
	// ------------------------------------------------------------------------
	
	@Test
	public void depot_objet_de_l_inventaireOK1() {
		
		try {
			
			String nom = "Toto";
			
			Objet objet = new ObjetImpl();
			objet.init(CONTENU_OBJET.POTIONVIE, 10, true, 10);
			
			joueur.init(nom);
			joueur.depot_objet_de_l_inventaire(objet);
			
			assertTrue("Joueur::depot_objet_de_l_inventaireOK1 :\n" +
					"l'inventaire du joueur ne contient pas l'objet à ajouter",
					joueur.getInventaire().contains(objet));
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Joueur::depot_objet_de_l_inventaireOK1 :\n" + 
					e.getMessage(),
					false);
		}
	}
}
