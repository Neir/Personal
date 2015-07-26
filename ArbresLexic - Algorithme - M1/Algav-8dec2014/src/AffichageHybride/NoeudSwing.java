package AffichageHybride;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import HybridTrie.Noeud;

public class NoeudSwing{
	private Noeud nd;
	
//	public static int Xmin = Integer.MAX_VALUE;
//	public static int Xmax = 0;
	
	public NoeudSwing(Noeud nd) {
		super();
		this.nd = nd;
	}
	
	public void print(int x, int y, Graphics g, double size_coef){
        int size = (int) (20*size_coef);
        int marge = (int) (5*size_coef);
        g.setColor(Color.BLACK);
        //if(nd.getId()!=-1) g.setColor(Color.RED);
        g.drawOval(x, y, size, size);
        //char []tmp = { nd.getEtiquette()};//, Integer.toString(nd.getId()).charAt(0) };
        //g.drawChars(tmp, 0, marge*2, x+marge, y+marge);
       
        if(nd.getId()!=-1){
            g.setColor(Color.BLUE);
            //g.setFont(new Font("TimesRoman", Font.BOLD, (int) (size_coef*5)));
            g.drawString(nd.getEtiquette()+","+nd.getId(), x+marge/2, y+size*2/3);
            g.setColor(Color.BLACK);
        } else
            g.drawString(" "+nd.getEtiquette(), x+marge/2, y+size*2/3);
       
        // Branche des noeuds fils :
        int newX = x+size/2;
        int newY = y+size;
        
        //*
        int nextXG = (size)*(nd.getInf().getLargeurDroite()+1 + nd.getLargeurGauche());
        int nextXD = (size)*(nd.getSup().getLargeurGauche()+1 + nd.getLargeurDroite());
        //*/
        
        /*
        int nextXG = (size)*(nd.getLargeurGauche()+1);
        int nextXD = (size)*(nd.getLargeurDroite()+1);
        //*/
        /*
        int nextXG = (size)*(nd.().getInfgetLargeurTotal() + nd.getLargeurTotal());
        int nextXD = (size)*(nd.getSup().getLargeurTotal() + nd.getLargeurTotal());
        //*/
        Noeud fils;
        NoeudSwing filsSwing;
        //*
        if(!(fils = nd.getInf()).estNil()){
            g.drawLine(newX, newY, newX-nextXG, newY+size); // inf
            filsSwing = new NoeudSwing(fils);
            filsSwing.print(newX-nextXG-marge*2, newY+size, g, size_coef);
        }
        if(!(fils = nd.getEq()).estNil()){
            g.drawLine(newX, newY, newX, newY+size); // eq
            filsSwing = new NoeudSwing(fils);
            filsSwing.print(newX-marge*2, newY+size, g, size_coef);
        }
        if(!(fils = nd.getSup()).estNil()){
            g.drawLine(newX, newY, newX+nextXD, newY+size); // sup
            filsSwing = new NoeudSwing(fils);
            filsSwing.print(newX+nextXD-marge*2, newY+size, g, size_coef);
        }
        
        //if(newX+nextXD > Xmax) Xmax = newX+nextXD;
        //if(newX-nextXG < Xmin) Xmin = newX-nextXG;
        //*/
    }
}
