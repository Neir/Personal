package isketch.view;
import isketch.interfaces.Observable;
import isketch.interfaces.Observateur;
import isketch.objets.Commande;
import isketch.objets.Segment;
import isketch.tools.EnumCommandes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements Observable{

	//Taille du pointeur
	private int pointerSize = 10;
	//Couleur du pointeur
	private Color pointerColor = Color.black;
	
	//Pour savoir si on doit dessiner ou non
	private boolean erasing = true;
	
	//Liste des segments déjà dessinés
	private ArrayList<Segment> segments = new ArrayList<Segment>(); 
	
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	//Segment en cour de dessin
	private Segment segmentCourant;
	private Point p1=null, p2=null;
	
	private Boolean enable;
	
	public DrawPanel(){
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(enable){
					p1 = new Point(e.getX(), e.getY());
					segmentCourant = new Segment(p1,p1,pointerColor,pointerSize);
					repaint();
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if(enable){
					segments.add(segmentCourant);
					repaint();
					envoyerLigneAuServeur(segmentCourant);
				}
			}
		});

		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				//On récupère les coordonnées de la souris et on enlève la moitié de la taille du pointeur pour centrer le tracé
				if(enable){
					p2 = new Point(e.getX(), e.getY());
					segmentCourant = new Segment(p1,p2,pointerColor,pointerSize);
					repaint();
				}
			}

			public void mouseMoved(MouseEvent e) {}
		});

	}
	


	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(this.erasing){
			this.erasing = false;
		}
		else{
			for(Segment s : segments)
			{
				g.setColor(s.getC());
				drawSegment(g, s);
			}
			
			if(p1!=null&&p2!=null){
				g.setColor(pointerColor);
				drawSegment(g, segmentCourant);
			}
		}
	}
	
	private void drawSegment(Graphics g, Segment s){
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setStroke(new BasicStroke(s.getSize(), BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f));
		
		g2d.drawLine(
				(int)s.getP1().getX(),
				(int)s.getP1().getY(), 
				(int)s.getP2().getX(),
				(int)s.getP2().getY());

	}
	
	

	//Efface le contenu
	public void erase(){
		this.erasing = true;
		segments.removeAll(segments);
		repaint();
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}
	
	public void addSegment(int x1, int y1, int x2, int y2, int r, int g, int b, int taille){
		segments.add(new Segment(new Point(x1,y1), new Point(x2,y2), new Color(r, g, b, 255), taille));
		repaint();
	}

	//Définit la couleur du pointeur
	public void setPointerColor(Color c){
		this.pointerColor = c;
	}

	//Définit la taille du pointeur
	public void setPointerSize(int pointerSize) {
		this.pointerSize = pointerSize;
	}
	
	
	protected void envoyerLigneAuServeur(Segment segment) {
		Commande com;
		
		if(!commandes.isEmpty()) commandes = new ArrayList<Commande>();
		
		com = new Commande("SET_COLOR");
		com.addAttribut(""+segment.getC().getRed());
		com.addAttribut(""+segment.getC().getGreen());
		com.addAttribut(""+segment.getC().getBlue());
		commandes.add(com);
		
		updateObservateur();
		commandes = new ArrayList<Commande>();
		
		commandes.add(new Commande("SET_SIZE", ""+segment.getSize()));
		
		updateObservateur();
		commandes = new ArrayList<Commande>();
		
		com = new Commande("SET_LINE");
		com.addAttribut(""+segment.getP1().getX());
		com.addAttribut(""+segment.getP1().getY());
		com.addAttribut(""+segment.getP2().getX());
		com.addAttribut(""+segment.getP2().getY());
		commandes.add(com);

		updateObservateur();
	}

	public void setEnablePerso(Boolean b){
		enable = b;
	}

	public void addObservateur(Observateur obs) {
		listObservateur.add(obs);
	}

	public void updateObservateur() {
		for(Observateur obs : listObservateur )
			obs.update(commandes);
	}

}