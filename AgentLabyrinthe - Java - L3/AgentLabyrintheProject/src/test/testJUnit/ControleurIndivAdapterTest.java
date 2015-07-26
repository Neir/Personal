package test.testJUnit;

import agent.control.ControlFactory;
import agent.control.IControleur;
import pobj.algogen.Individu;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import junit.framework.TestCase;

public class ControleurIndivAdapterTest extends TestCase {
	ControleurIndividuAdapter cia;
	IControleur controleur;
	Individu autre;

	public ControleurIndivAdapterTest(String arg0) {
		super(arg0);
		controleur = ControlFactory.createControleur(20);
		IControleur controleur2 = ControlFactory.createControleur(20);
		cia = new ControleurIndividuAdapter(controleur);
		autre = new ControleurIndividuAdapter(controleur2);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
/*
 * on vérifie que la valeur propre stockée est bien celle qui a été créée initialement
 */
	public void testGetValeurPropre() {
		IControleur cont = (IControleur) cia.getValeurPropre();
		assertTrue(cont.equals(controleur));
		assertTrue(cont==controleur);
	}
/*
 * On vérifie à l'issue du croisement que le nouvel individu n'est pas égal à l'original
 */
	public void testCroiser() {
		cia.croiser(autre);
		assertFalse(cia.equals(autre));
	}
/*
 * On vérifie que la mutation introduit bien un changement
 */
	public void testMuter() {
		autre = cia.clone();
		cia.muter(0.2);
		assertFalse(cia.equals(autre));
	}

}
