/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.MoteurJeuContract;
import rivercityrandom.enumerations.RESULTAT;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.MoteurJeuImpl;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class MoteurJeuTest {

	private MoteurJeuContract moteurJeu;
	
	public MoteurJeuTest() {
		moteurJeu = null;
	}

	@Before
	public void initialisation() {
		moteurJeu = new MoteurJeuContract(new MoteurJeuImpl());
	}
	
	@After
	public void afterTests() {
		moteurJeu = null;
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation du moteur de jeu
	// ------------------------------------------------------------------------
	
	@Test
	public void initOK() {
		
		try {
			
			int largeur = 256;
			int hauteur = 240;
			int profondeur = 100;
			
			moteurJeu.init(largeur, hauteur, profondeur);
			
			assertTrue("MoteurJeu::initOK :\n" +
					"la largeur du terrain est erronée",
					moteurJeu.getCombat().getTerrain().getLargeur() == largeur);
			
			assertTrue("MoteurJeu::initOK :\n" +
					"la hauteur du terrain est erronée",
					moteurJeu.getCombat().getTerrain().getHauteur() == hauteur);
			
			assertTrue("MoteurJeu::initOK :\n" +
					"la profondeur du terrain est erronée",
					moteurJeu.getCombat().getTerrain().getProfondeur() 
					== profondeur);
			
			assertTrue("MoteurJeu::initOK :\n" +
					"l'état estFini est erronée",
					moteurJeu.estFini() == false);
			
			assertTrue("MoteurJeu::initOK :\n" +
					"le résultat de la partie est erroné",
					moteurJeu.getResultatFinal() == RESULTAT.NULLE);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"MoteurJeu::initOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			int largeur = 255;
			int hauteur = 240;
			int profondeur = 100;
			
			moteurJeu.init(largeur, hauteur, profondeur);
			
			assertTrue("MoteurJeu::initKO1 :\n" +
					"aucune exception levée alors que la largeur est " +
					"inférieure à 256",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"MoteurJeu::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO2() {
		
		try {
			
			int largeur = 256;
			int hauteur = 239;
			int profondeur = 100;
			
			moteurJeu.init(largeur, hauteur, profondeur);
			
			assertTrue("MoteurJeu::initKO2 :\n" +
					"aucune exception levée alors que la hauteur est " +
					"inférieure à 240",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"MoteurJeu::initKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO3() {
		
		try {
			
			int largeur = 256;
			int hauteur = 240;
			int profondeur = 99;
			
			moteurJeu.init(largeur, hauteur, profondeur);
			
			assertTrue("MoteurJeu::initKO3 :\n" +
					"aucune exception levée alors que la profondeur est " +
					"inférieure à 100",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"MoteurJeu::initKO3 :\n" + e.getMessage(),
					false);
		}
	}
}
