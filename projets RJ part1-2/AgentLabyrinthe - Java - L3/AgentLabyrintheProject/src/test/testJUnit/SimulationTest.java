package test.testJUnit;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;
import agent.laby.exception.VerificationLaby;
import junit.framework.TestCase;

public class SimulationTest extends TestCase {
	private Simulation simuTest;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe("test5Droite.mze");
		VerificationLaby.corrigerLabyrinthe(laby);
		simuTest = new Simulation(laby, ControlFactory.createControleurDroitier());
		
	}

	public void testMesurePerf() throws Exception {	
		int score;
		int cpt = 0;

		for (int i = 0 ; i<simuTest.getLaby().Xsize();i++){
			//*
			System.out.print(simuTest.getLaby().getContenuCase(i, (int) simuTest.getLaby().getPositionInitiale().getY()));
			//*/
			if(simuTest.getLaby().getContenuCase(i, (int) simuTest.getLaby().getPositionInitiale().getY())==ContenuCase.POINT)
				cpt++;
		}
		score = simuTest.mesurePerf(20);
		if(score==cpt)
			System.out.println("\nTous les points sont trouv�s !");
		else
			System.out.println("\nL'agent n'a pas trouv� tous les points !");
		System.out.println("Score = " + simuTest.getScore() + "/" + cpt);
	}
	
	public void testSimulation() {
		//System.out.println(simuTest.getScore());
	}
}
