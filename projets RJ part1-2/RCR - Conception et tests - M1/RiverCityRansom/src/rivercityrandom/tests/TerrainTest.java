/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.TerrainContract;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.PostconditionError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.TerrainImpl;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class TerrainTest {

	private TerrainContract terrain;
	
	public TerrainTest() {
		terrain = null;
	}

	@Before
	public void initialisation() {
		terrain = new TerrainContract(new TerrainImpl());
	}
	
	@After
	public void afterTests() {
		terrain = null;
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation du terrain
	// ------------------------------------------------------------------------
	
	@Test
	public void initOk() {
		
		try {
			
			int largeur = 500;
			int hauteur = 10;
			int profondeur = 50;
			
			terrain.init(largeur, hauteur, profondeur);
			
			assertTrue("Terrain::initOK :\n"
					+ "la largeur du terrain est erronée",
					terrain.getLargeur() == largeur);

			assertTrue("Terrain::initOK :\n"
					+ "la hauteur du terrain est erronée",
					terrain.getHauteur() == hauteur);

			assertTrue("Terrain::initOK :\n"
					+ "la profondeur du terrain est erronée",
					terrain.getProfondeur() == profondeur);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Terrain::initOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			int largeur = 0;
			int hauteur = 10;
			int profondeur = 50;
			
			terrain.init(largeur, hauteur, profondeur);
			
			assertTrue("Terrain::initKO1 :\n"
					+ "Aucune exception levée alors que la largeur est erronée",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (PostconditionError e) {
			assertTrue("Terrain::initKO1 :\n"
					+ e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO2() {
		
		try {
			
			int largeur = 500;
			int hauteur = 0;
			int profondeur = 50;
			
			terrain.init(largeur, hauteur, profondeur);
			
			assertTrue("Terrain::initKO2 :\n"
					+ "Aucune exception levée alors que la hauteur est erronée",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (PostconditionError e) {
			assertTrue("Terrain::initKO2 :\n"
					+ e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO3() {
		
		try {
			
			int largeur = 500;
			int hauteur = 10;
			int profondeur = 0;
			
			terrain.init(largeur, hauteur, profondeur);
			
			assertTrue("Terrain::initKO3 :\n"
					+ "Aucune exception levée alors que la profondeur est erronée",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (PostconditionError e) {
			assertTrue("Terrain::initKO3 :\n"
					+ e.getMessage(),
					false);
		}
	}
}
