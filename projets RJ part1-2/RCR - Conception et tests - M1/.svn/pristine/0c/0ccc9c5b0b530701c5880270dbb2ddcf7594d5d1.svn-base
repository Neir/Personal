/**
 * 
 */
package rivercityrandom.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rivercityrandom.contracts.BlocContract;
import rivercityrandom.enumerations.CONTENU_OBJET;
import rivercityrandom.enumerations.TYPE_BLOC;
import rivercityrandom.errors.ContractError;
import rivercityrandom.errors.PreconditionError;
import rivercityrandom.impl.BlocImpl;
import rivercityrandom.impl.ObjetImpl;
import rivercityrandom.services.Objet;

/**
 * @author Thibaut FLEURY & Jerome RAHAULT
 *
 */
public class BlocTest {

	private BlocContract bloc;
	
	public BlocTest() {
		bloc = null;
	}
	
	@Before
	public void initialisation() {
		bloc = new BlocContract(new BlocImpl());
	}
	
	@After
	public void afterTests() {
		bloc = null;
	}
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Initialisation d'un bloc
	// ------------------------------------------------------------------------
	
	@Test
	public void initOk() {
		
		try {
			
			TYPE_BLOC p_type = TYPE_BLOC.VIDE;
			Objet p_tresor = null;
			
			bloc.init(p_type, p_tresor);
			
			assertTrue("Bloc::initOK :\n"
					+ "le type du bloc est erroné",
					bloc.getTypeBloc() == p_type);
			
			assertTrue("Bloc::initOK :\n"
					+ "le tresor du bloc est erroné",
					bloc.getTresor() == p_tresor);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Bloc::initOK :\n" + e.getMessage(),
					false);
		}
	}
	
	@Test
	public void initKO1() {
		
		try {
			
			TYPE_BLOC p_type = null;
			Objet p_tresor = null;
			
			bloc.init(p_type, p_tresor);
			
			assertTrue("Bloc::initKO1 :\n"
					+ "Aucune exception levée alors que le type est erroné",
					false);
		}
		catch (PreconditionError e) {
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Bloc::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
	
	
	
	// ------------------------------------------------------------------------
	// OBJECTIF DE TEST : Changer tresor modifie bien le tresor du bloc
	// ------------------------------------------------------------------------
	
	@Test
	public void changerTresorOK() {
		
		try {
			
			Objet p_tresor = new ObjetImpl();
			p_tresor.init(CONTENU_OBJET.POUBELLEMETALLIQUE,
					10,
					false,
					0);
			
			bloc.changerTresor(p_tresor);
			
			assertTrue("Bloc::changerTresorOK :\n"
					+ "Le tresor du bloc n'a pas été correctement modifié",
					bloc.getTresor() == p_tresor);
			
			// Tout est ok
		}
		catch (ContractError e) {
			assertTrue(
					"Bloc::initKO1 :\n" + e.getMessage(),
					false);
		}
	}
}
