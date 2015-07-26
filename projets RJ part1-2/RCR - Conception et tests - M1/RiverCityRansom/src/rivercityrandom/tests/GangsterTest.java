/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.GangsterContract;
import rivercityrandom.enumerations.TYPE_GANGSTER;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.InvariantError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.GangsterImpl;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class GangsterTest {

	GangsterContract gangster;
	
	public GangsterTest() {
		gangster = null;
	}
	
	@Before
	public void initialisation() {
		gangster = new GangsterContract(new GangsterImpl());
	}

	@After
	public void afterTests() {
		gangster = null;
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation d'un gangster
	// ------------------------------------------------------------------------
	
	@Test
	public void initOK1() {
		
		try {
			
			String nom = "Toto";
			TYPE_GANGSTER type = TYPE_GANGSTER.GENERIC_DUDES;
			Objet drop = null;
			int z = 0;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initOK1 :\n" +
					"le nom du gangster est erroné",
					gangster.getNom() == nom);
			
			assertTrue("Gangster::initOK1 :\n" +
					"le type du gangster est erroné",
					gangster.getType() == type);
			
			assertTrue("Gangster::initOK1 :\n" +
					"le drop du gangster est erroné",
					gangster.getDrop() == drop);
			
			assertTrue("Gangster::initOK1 :\n" +
					"la coordonnée z du gangster est erronée",
					gangster.getPos_z() == z);
			
			assertTrue("Gangster::initOK1 :\n" +
					"la largeur du gangster est erronée",
					gangster.getLargeur() == 30);
			
			assertTrue("Gangster::initOK1 :\n" +
					"la hauteur du gangster est erronée",
					gangster.getHauteur() == 50);
			
			assertTrue("Gangster::initOK1 :\n" +
					"la profondeur du gangster est erronée",
					gangster.getProfondeur() == 10);
			
			assertTrue("Gangster::initOK1 :\n" +
					"la force du gangster est erronée",
					gangster.getForce() == 5);
			
			assertTrue("Gangster::initOK1 :\n" +
					"les pv du gangster sont erronés",
					gangster.getPvMax() == 20);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initOK1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initOK2() {
		
		try {
			
			String nom = "Toto";
			TYPE_GANGSTER type = TYPE_GANGSTER.HOME_BOYS;
			Objet drop = null;
			int z = 0;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initOK2 :\n" +
					"le nom du gangster est erroné",
					gangster.getNom() == nom);
			
			assertTrue("Gangster::initOK2 :\n" +
					"le type du gangster est erroné",
					gangster.getType() == type);
			
			assertTrue("Gangster::initOK2 :\n" +
					"le drop du gangster est erroné",
					gangster.getDrop() == drop);
			
			assertTrue("Gangster::initOK2 :\n" +
					"la coordonnée z du gangster est erronée",
					gangster.getPos_z() == z);
			
			assertTrue("Gangster::initOK2 :\n" +
					"la largeur du gangster est erronée",
					gangster.getLargeur() == 30);
			
			assertTrue("Gangster::initOK2 :\n" +
					"la hauteur du gangster est erronée",
					gangster.getHauteur() == 50);
			
			assertTrue("Gangster::initOK2 :\n" +
					"la profondeur du gangster est erronée",
					gangster.getProfondeur() == 10);
			
			assertTrue("Gangster::initOK2 :\n" +
					"la force du gangster est erronée",
					gangster.getForce() == 10);
			
			assertTrue("Gangster::initOK2 :\n" +
					"les pv du gangster sont erronés",
					gangster.getPvMax() == 30);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initOK2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initOK3() {
		
		try {
			
			String nom = "Toto";
			TYPE_GANGSTER type = TYPE_GANGSTER.SQUIDS;
			Objet drop = null;
			int z = 0;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initOK3 :\n" +
					"le nom du gangster est erroné",
					gangster.getNom() == nom);
			
			assertTrue("Gangster::initOK3 :\n" +
					"le type du gangster est erroné",
					gangster.getType() == type);
			
			assertTrue("Gangster::initOK3 :\n" +
					"le drop du gangster est erroné",
					gangster.getDrop() == drop);
			
			assertTrue("Gangster::initOK3 :\n" +
					"la coordonnée z du gangster est erronée",
					gangster.getPos_z() == z);
			
			assertTrue("Gangster::initOK3 :\n" +
					"la largeur du gangster est erronée",
					gangster.getLargeur() == 30);
			
			assertTrue("Gangster::initOK3 :\n" +
					"la hauteur du gangster est erronée",
					gangster.getHauteur() == 50);
			
			assertTrue("Gangster::initOK3 :\n" +
					"la profondeur du gangster est erronée",
					gangster.getProfondeur() == 10);
			
			assertTrue("Gangster::initOK3 :\n" +
					"la force du gangster est erronée",
					gangster.getForce() == 15);
			
			assertTrue("Gangster::initOK3 :\n" +
					"les pv du gangster sont erronés",
					gangster.getPvMax() == 50);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initOK3 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			String nom = "";
			TYPE_GANGSTER type = TYPE_GANGSTER.SQUIDS;
			Objet drop = null;
			int z = 0;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initKO1 :\n" +
					"aucune exception levée alors que le nom est null ou vide",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO2() {
		
		try {
			
			String nom = "Toto";
			TYPE_GANGSTER type = null;
			Objet drop = null;
			int z = 0;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initKO2 :\n" +
					"aucune exception levée alors que le type est null",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initKO2 :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO3() {
		
		try {
			
			String nom = "Toto";
			TYPE_GANGSTER type = TYPE_GANGSTER.SQUIDS;
			Objet drop = null;
			int z = -1;
			
			gangster.init(nom, type, drop, z);
			
			assertTrue("Gangster::initKO3 :\n" +
					"aucune exception levée alors que la coordonnée z " +
					"est négative",
					false);
		}
		catch (PreconditionError | InvariantError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Gangster::initKO3 :\n" + e.getMessage(),
					false);
		}
	}
}
