package test.testJUnit;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;
import agent.laby.exception.VerificationLaby;
import junit.framework.TestCase;

public class SimuSmartTest extends TestCase {
	private Simulation simuTest;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe("testSansMur.mze");
		VerificationLaby.corrigerLabyrinthe(laby);
		simuTest = new Simulation(laby, ControlFactory.createSmartController());
		
	}

	public void testSimulation() {
		//System.out.println(simuTest.getScore());
	}

	public void testMesurePerf() throws Exception {
		int score;
		int cpt = 0;
		
		for (int j = 0 ; j<simuTest.getLaby().Ysize() ; j++){
			for (int i = 0 ; i<simuTest.getLaby().Xsize() ; i++){
				if(simuTest.getLaby().getContenuCase(i, j)==ContenuCase.POINT)
					cpt++;
				//*
				System.out.print(simuTest.getLaby().getContenuCase(i, j));
				//*/
			}
			//*
			System.out.println("");
			//*/
		}
		
		score = simuTest.mesurePerf(100);
		if(score==cpt)
			System.out.println("Tous les points sont trouvés !");
		else
			System.out.println("L'agent n'a pas trouvé tous les points !");
		System.out.println("Score = " + simuTest.getScore() + "/" + cpt);
	}
}

