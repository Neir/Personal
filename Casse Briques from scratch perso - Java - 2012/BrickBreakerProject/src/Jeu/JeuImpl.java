package Jeu;

import li260.geometrie.Vecteur;
import ObjetSim.Brick;
import Tools.Tools;

public class JeuImpl implements Jeu {
	private Brick[][] mat;
	private int widthB;
	private int heightB;
	private int nbL;
	private int nbC;
	
	public JeuImpl(Brique[][] matB, int widthB, int heightB){
		nbL = matB.length;
		nbC = matB[0].length;
		
		this.widthB=widthB;
		this.heightB=heightB;
		mat = new Brick[nbL][nbC];
		
		for(int i = 0; i < nbL ; i++)
			for(int j = 0; j < nbC ; j++){
				//mat[i][j]=Tools.BrickFromBrique(matB[i][j]);
				mat[i][j]=Tools.BrickFromBriqueKollider(matB[i][j]);
			
				mat[i][j].setPosEtDim(new Vecteur(4+j*(widthB+1), i*(heightB+1)), widthB, heightB);
			}
	}
	
	public Brick[][] getMat(){
		return mat;
	}

	public Brick getBrick(int x, int y){
		return mat[x][y];
	}
	
	public int getWidthB(){
		return widthB;
	}

	public int getHeightB() {
		return heightB;
	}
	
	public boolean gagne(){
		for(int i = 0; i < nbL ; i++)
			for(int j = 0; j < nbC ; j++)
				if(mat[i][j].is()&&!mat[i][j].isBonus()&&!mat[i][j].isIncassable())
					return false;
		return true;
	}
}