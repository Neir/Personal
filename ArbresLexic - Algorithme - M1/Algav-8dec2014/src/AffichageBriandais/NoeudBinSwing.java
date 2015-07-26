package AffichageBriandais;

import java.awt.Color;
import java.awt.Graphics;
import BriandaisTree.Noeud;

public class NoeudBinSwing {
	private Noeud nd;

	public NoeudBinSwing(Noeud nd) {
		super();
		this.nd = nd;
	}

	public void print(int x, int y, Graphics g , Largeur L ){

		AjoutValeur(x , y, nd.value, g);
		L.Occupe(x);		
		NoeudBinSwing filsSwing;

		
		if(!(nd.getFils() ==null)){
			AjoutUnionFils( x, y, g) ;
			filsSwing = new NoeudBinSwing(nd.getFils());
			filsSwing.print(x, y+40 , g ,L );
		}		

		if(!(nd.getFrere() ==null)){
			AjoutUnionFrere(x, y,  g, L);

		}
		


	}
	public void AjoutValeur(int x, int y, char a, Graphics g ){

		g.setColor(Color.BLACK);
		g.drawOval(x, y, 20, 20);
		g.drawString(Character.toString(a), x+7, y+14);
	}

	public void AjoutUnionFils(int x, int y, Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(x+10, y+20, x+10, y+40);
	}

	public void AjoutUnionFrere(int x, int y, Graphics g, Largeur L){

		
		NoeudBinSwing filsSwing = new NoeudBinSwing(nd.getFrere());
		int hauteur = 40 ;
		boolean modif = false;
		int x1 = x ;


		while(true){
			if(L.EstOcuppe(x+40)){
				x = x+40;
				modif=true;
			}
			else {
				break;
			}
		}


		if(modif){ // si ligne occupée
			g.setColor(Color.BLACK);
			g.drawLine(x1+20, y+10, x+hauteur, y+10);
			filsSwing.print(x+hauteur, y, g  ,L);

		}
		else{
			g.setColor(Color.BLACK);
			g.drawLine(x+20, y+10, x+40, y+10);
			filsSwing.print(x+40 , y , g  ,L);

		}	

	}
}
