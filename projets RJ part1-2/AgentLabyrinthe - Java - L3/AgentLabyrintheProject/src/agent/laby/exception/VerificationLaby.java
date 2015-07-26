package agent.laby.exception;

import java.awt.Point;

import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;

public class VerificationLaby {
	
	public static void verifierConditions(Labyrinthe l) throws LabyErroneException{
		estCaseInitialeVide(l);
		estEntoureDeMurs(l);
	}
	
	private static void estCaseInitialeVide(Labyrinthe l) throws CaseDepartNonVideException{
		if(l.getContenuCase(l.getPositionInitiale())!= ContenuCase.VIDE)
			throw new CaseDepartNonVideException(l.getPositionInitiale());
	}
	
	private static void estEntoureDeMurs(Labyrinthe l) throws LabyMalEntoureException{
		for(int i=0; i<l.Xsize();i++){
			if(l.getContenuCase(i, 0) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(i, 0));
			if(l.getContenuCase(i, l.Ysize()-1) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(i, l.Ysize()-1));
		}
		for(int i=1; i<l.Ysize()-1;i++){
			if(l.getContenuCase(0, i) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(0, i));
			if(l.getContenuCase(l.Xsize()-1, i) != ContenuCase.MUR)
				throw new LabyMalEntoureException(new Point(l.Xsize()-1, i));
		}
	}
	
	public static int corrigerLabyrinthe(Labyrinthe l){
		int cpt=0;
		try {
			verifierConditions(l);
		} catch (CaseDepartNonVideException e1) {
			l.setContenuCase(e1.getPoint(), ContenuCase.VIDE);
		} catch (LabyMalEntoureException e2){
			l.setContenuCase(e2.getPoint(), ContenuCase.MUR);
		} catch (LabyErroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpt;
	}
}
