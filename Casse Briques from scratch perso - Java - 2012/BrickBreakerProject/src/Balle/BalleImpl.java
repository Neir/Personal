package Balle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import Jeu.Jeu;
import Model.IHMSwing;
import ObjetSim.Brick;
import Plateau.Plateau;
import Sons.Sound;
import Tools.Tools;
import li260.geometrie.Vecteur;

public class BalleImpl implements Balle{
	private double vitesse;
	private Vecteur position;
	private Vecteur direction;
	private int taille;
	private boolean perdu = false;
	
	public BalleImpl(double vitesse, Vecteur position, Vecteur direction, int taille) {
		super();
		this.vitesse = vitesse;
		this.position = position;
		this.direction = direction;
		this.taille = taille;
	}

	public double getVitesse() {
		return vitesse;
	}

	public Vecteur getPosition() {
		return position;
	}
	
	public void setPosX(double x) {
		this.position = new Vecteur(x, position.getY());
	}

	public Vecteur getDirection() {
		return direction;
	}

	public int getTaille() {
		return taille;
	}
	
	public String toString(){
		String str = "";
		str += "t="+taille;
		str += "/vitesse="+vitesse;
		str += "/position="+position;
		str += "/direction="+direction;
		return str;
	}
	
	public void rebond(Plateau p, Jeu j) {
		double xb = position.getX();
		double yb = position.getY();
		//System.out.println(xb+taille);
		
		Vecteur posP = p.getPosition();
		//Interraction avec les bords
		if(xb+taille>=468||xb<=0)
			direction.setX(-direction.getX());
		if(yb<=0)
			direction.setY(-direction.getY());
		//interraction avec le plateau
		else if(yb+taille>posP.getY()&&xb+taille>posP.getX()&&xb<posP.getX()+p.getWidth())
			renvoie(p); //On pourra placer ce if (car plus besoin du else) dans Renvoie quand l'echec sera possible.

		if(yb+taille>=468)
			perdu = true;
		
		//interraction avec le jeu
		percute(j);

		
		position.add(direction.fact(vitesse));
	}

	private void renvoie(Plateau p){
		double xp = p.getPosition().getX();
		double xb = position.getX();
		double milieu = (xp+p.getWidth()/2);
		double newDirectionX = direction.getX();
		double newDirectionY = direction.getY();
		
		// Formule 1 algorithmique
		newDirectionX = newDirectionX/3+((xb+taille/2)/milieu-1)*15;
		
//		//Formule 2 alternative
//		if(xb>=xp&&xb<milieu)
//			newDirectionX--;
//		else if(xb>=milieu&&xb<=xp+p.getWidth())
//			newDirectionX++;

		if(newDirectionX>2)
			newDirectionX=2;
		else if(newDirectionX<-2)
			newDirectionX=-2;
		
		// Peu mieux faire
//		if(newDirectionY > 0)
//			newDirectionY -= Math.abs(newDirectionX);
//		else
//			newDirectionY += Math.abs(newDirectionX);
//		direction.setY(newDirectionY);
		
		Tools.PlayASound("bouchon");
		
		
		direction.setX(newDirectionX);
		direction.setY(-direction.getY());
	}
	
	private void percute(Jeu j){
		Brick[][] mat=j.getMat();
		Brick br;
		int nbL = mat.length;
		int nbC = mat[0].length;
//		// Coordonnées de la brique
//		double xbr, ybr;
//		// Taille de la brique (Hauteur/Largeur)
//		double hbr, wbr;
		// Coordonnées de la balle
		double xba = position.getX();
		double yba = position.getY();
		
		boolean brickCassee = false;
		
		Vecteur centreBalle;
		
		//(yb+taille>posP.getY()&&xb+taille>posP.getX()&&xb<posP.getX()+p.getWidth())
		for(int i = 0; i < nbL ; i++){
			
			for(int z = 0; z < nbC ; z++){
				br = mat[i][z];
//				xbr = br.getPosition().getX();
//				ybr = br.getPosition().getY();
//				hbr = j.getHeightB();
//				wbr = j.getWidthB();
				
				
				if(br.is()){
//					// la balle : "touche" à gauche ET "tape" en bas ET "touche" à droite
//					//       |___|
//					//         î
//					if(xba+taille>=xbr&&yba-1==ybr+hbr&&xba<=xbr+wbr){
//						br.setSolidite(br.getSolidite()-1);
//						direction.setY(-direction.getY());
//						System.out.println("1");
//					}
//					//        _\/_
//					//       |    |
//					if(xba+taille>=xbr&&yba+taille+1==ybr&&xba<=xbr+wbr){
//						br.setSolidite(br.getSolidite()-1);
//						direction.setY(-direction.getY());
//						System.out.println("2");
//					}
//					//        ___
//					//    -> |   |
//					if(yba<=ybr+hbr&&xba+taille==xbr&&yba+taille>=ybr){
//						br.setSolidite(br.getSolidite()-1);
//						direction.setX(-direction.getX());
//						System.out.println("3");
//					}
//					//        ___
//					//       |   | <-
//					if(yba<=ybr+hbr&&xba==xbr+wbr&&yba+taille>=ybr){
//						br.setSolidite(br.getSolidite()-1);
//						direction.setX(-direction.getX());
//						System.out.println("4");
//					}
					
					centreBalle = new Vecteur((double)xba+taille/2.0, (double)yba+taille/2.0);
					
					if(br.surBordHorizontal(centreBalle, taille+6)){
						br.setSolidite(br.getSolidite()-1);
						direction.setY(-direction.getY());
						brickCassee=true;
					}
					else if(br.surBordVertical(centreBalle, taille+6)){
						br.setSolidite(br.getSolidite()-1);
						direction.setX(-direction.getX());
						brickCassee=true;
					}
					
					if(brickCassee==true)
						return;
				}
			}
		}
	}

	public boolean perdu() {
		return perdu;
	}
}