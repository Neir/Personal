/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.ObjetContract;
import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.ObjetImpl;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class ObjetTest {

	private ObjetContract objet;
	
	public ObjetTest() {
		objet = null;
	}
	
	@Before
	public void initialisation() {
		objet = new ObjetContract(new ObjetImpl());
	}
	
	@After
	public void afterTests() {
		objet = null;
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation d'une poubelle
	// ------------------------------------------------------------------------
	
	@Test
	public void initOK() {
		
		try {
			
			CONTENU_OBJET p_contenu = CONTENU_OBJET.POUBELLEMETALLIQUE;
			int p_valeur = 10;
			boolean p_marchand = false;
			int p_cout = 0;
			
			objet.init(p_contenu, p_valeur, p_marchand, p_cout);
			
			assertTrue("Objet::initOK :\n" +
					"le contenu de l'objet est erroné",
					objet.getContenu() == p_contenu);
			
			assertTrue("Objet::initOK :\n" +
					"la valeur de l'objet est erronée",
					objet.getValeur() == p_valeur);
			
			assertTrue("Objet::initOK :\n" +
					"l'état marchandable de l'objet est erroné",
					objet.estMarchandable() == p_marchand);
			
			assertTrue("Objet::initOK :\n" +
					"le cout de l'objet est erroné",
					objet.getCout() == p_cout);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Objet::initOK :\n" + e.getMessage(),
					false);
		}
		
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			CONTENU_OBJET p_contenu = null;
			int p_valeur = 10;
			boolean p_marchand = false;
			int p_cout = 0;
			
			objet.init(p_contenu, p_valeur, p_marchand, p_cout);
			
			assertTrue("Objet::initKO1 :\n" +
					"Aucune exception levée alors que le contenu est null",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Objet::initOK :\n" + e.getMessage(),
					false);
		}
		
	}
	
	@Test
	public void initKO2() {
		
		try {
			
			CONTENU_OBJET p_contenu = CONTENU_OBJET.POUBELLEMETALLIQUE;
			int p_valeur = -1;
			boolean p_marchand = false;
			int p_cout = 0;
			
			objet.init(p_contenu, p_valeur, p_marchand, p_cout);
			
			assertTrue("Objet::initKO1 :\n" +
					"Aucune exception levée alors que la valeur est négative",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Objet::initOK :\n" + e.getMessage(),
					false);
		}
		
	}
	
	@Test
	public void initKO3() {
		
		try {
			
			CONTENU_OBJET p_contenu = CONTENU_OBJET.POUBELLEMETALLIQUE;
			int p_valeur = 10;
			boolean p_marchand = false;
			int p_cout = -1;
			
			objet.init(p_contenu, p_valeur, p_marchand, p_cout);
			
			assertTrue("Objet::initKO1 :\n" +
					"Aucune exception levée alors que le cout est négatif",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Objet::initOK :\n" + e.getMessage(),
					false);
		}
		
	}
}
