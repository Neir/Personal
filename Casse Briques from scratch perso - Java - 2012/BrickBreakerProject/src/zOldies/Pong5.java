package zOldies;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.WindowAdapter;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * Pong5.java
 *
 * D�monstration d'animation rapide pour un jeu en java.
 * 
 * Largement inspir� d'informations sur le site 
 * <a href="http://fivedots.coe.psu.ac.th/~ad/jg/">http://fivedots.coe.psu.ac.th/~ad/jg/</a>
 *
 * Normalement, un objet graphique java fonctionne de la mani�re suivante :
 * <ul>
 * <li> il sert � afficher graphiquement des donn�es. Ces donn�es constituent le <em>mod�le</em> ;
 * <li> le mod�le est repr�sent� par au moins une classe ;
 * <li> quand le mod�le est modifi�, on appelle la m�thode repaint() de l'objet graphique,
 *      qui le marque comme n'�tant pas � jour par rapport aux donn�es qu'il affiche ;
 * <li> quand la boucle graphique de java a le temps, elle redessine les objets qui ne sont 
 *      pas � jour, ou ceux qui n'�taient pas visibles et le sont devenus, etc... en utilisant 
 *      la m�thode paint (en AWT) ou paintComponent (Swing) � laquelle elle passe un objet Graphics
 *      en param�tre, cet objet repr�sentant la surface de dessin � utiliser.
 * </ul> 
 * 
 * <p> Ce syst�me est parfaitement adapt� pour la plupart des
 * applications, y compris certains jeux (par exemple, les jeux de
 * plateau). Par contre, pour un jeu d'action rapide, un probl�me va
 * se poser : le programme fera tourner en parall�le la boucle
 * graphique et un thread pour g�rer l'animation du jeu, les
 * d�placement, etc. Ce dernier aura probablement � sa disposition
 * beaucoup de temps graphique, et l'affichage, normalement effectu�
 * par la boucle graphique, ne se fera que de temps en temps.
 *
 * <p>En fait, dans un jeu d'action, on souhaite contr�ler plus ou
 * moins le taux de raffraichissement de l'�cran (le nombre de fois
 * par seconde o� le jeu est redessin�). Pour cela, nous allons
 * court-circuiter les m�canismes normaux de java, et dessiner
 * directement, sans attendre que la boucle graphique le demande. La
 * m�thode render() de notre code essaie plus ou moins de faire cela.
 * Avec le jdk 1.4.2_04, Sur un athlon 900, carte ATI radeon 7000 avec
 * accell�ration g�r�e par X11, il tient 58 images/s (�a tombe bien,
 * la fr�quence du moniteur est de 60 images/s). Une version
 * l�g�rement modifi�e, utilisant les fonctionnalit�s plein �cran du
 * jdk 1.4, permet d'avoir 60 images/s.
 * 
 *
 * <p> Le double buffering : normalement, pour dessiner quelque chose,
 * on efface la surface de dessin, et on dessine les divers �l�ments
 * dessus. Le probl�me est que, si c'est fait directement, on "voit"
 * le dessin se r�aliser ; en pratique, le temps de dessiner les
 * divers �l�ments, l'utilisateur per�oit une sorte de scintillement
 * de l'image, assez d�sagr�able. Pour �viter cela, on dessine d'abord
 * tout sur une image non affich�e, puis on copie cette image sur
 * l'�cran. Comme la copie est une op�ration tr�s rapide, l'impression
 * de scintillement dispara�t. Essayez de mettre utiliseDoubleBuffer �
 * false, pour voir la diff�rence.
 * 
 * @author <a href="mailto:rosmord@iut.univ-paris8.fr">Serge ROSMORDUC</a>
 * @version
 */

public class Pong5 extends JFrame{

    Pong5Model model;
    private Image doubleBuffer= null; 
    private boolean utiliseDoubleBuffer= true; // Mettre � false pour voir le programme fonctionner sans double buffering.

    public Pong5(int width, int height) {
	setSize(width, height);

	setIgnoreRepaint(true); // Ne tient pas compte du syst�me normal de raffraichissement des fen�tres java
	setResizable(false); // emp�che les changements de taille
	setUndecorated(true); // supprime les bordures, etc...

	setBackground(java.awt.Color.WHITE);

	model= new Pong5Model(getWidth(), getHeight());
	Thread t= new Thread(new PongAnimator(this));
	
	// Arr�t et manipulation des objets. Ne devrait bien �videmement pas �tre dans cette classe.
	addKeyListener( new KeyAdapter() {
		// listen for esc, q, end, ctrl-c on the canvas to
		// allow a convenient exit from the full screen configuration
		public void keyPressed(KeyEvent e)
		{ int keyCode = e.getKeyCode();
		if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
		    (keyCode == KeyEvent.VK_END) ||
		    ((keyCode == KeyEvent.VK_C) && e.isControlDown()) ) {
		    System.exit(0); // Beaucoup trop brutal .
		} else if (e.getKeyChar() == '-') {
		      model.plusLentement();
		} else if (e.getKeyChar() == '+') {
		      model.plusVite();
		}
		//System.out.println(e);
		}
	    });
	setVisible(true);

	// On attend 50 milisecondes.
	try {
	    Thread.sleep(50);
	} catch (InterruptedException e) {}
	
	// On d�marre le "thread" d'animation.
	// Cela lance sa m�thode "run" en parall�le avec la boucle graphique.
	t.start();
	// version sans thread : (new PongAnimator(this)).run();
    }
    
    /**
     * Procedure qui affiche le jeux. 
     * On utilise le proc�d� du double buffer pour �viter un scintillement.
     */
    public void render() {
	
	if (utiliseDoubleBuffer) {
	    // Cr�ation "paresseuse" du double buffer : 
	    if (doubleBuffer== null) {
		doubleBuffer= createImage(getWidth(), getHeight());
	    }
	    // la surface de dessin du double buffer :
	    Graphics gDessin;
	    gDessin= doubleBuffer.getGraphics();
	    model.dessiner(gDessin);
	    gDessin.dispose(); // On lib�re la ressource 
	    // On r�cup�re la surface de dessin de la fen�tre
	    Graphics g= getGraphics();
	    if (g != null) {
		g.drawImage(doubleBuffer,0,0,null);
		g.dispose();
	    }
	} else {
	    Graphics g= getGraphics();
	    if (g != null) {
		model.dessiner(g);
		g.dispose();
	    }
	}
    }
    
    public static void main(String[] args) {
	//Pong5 pong = new Pong5(1024,768); // On peut essayer en plus petit.
	Pong5 pong = new Pong5(640,480); // On peut essayer en plus petit.
    }
    
} // Pong5

/**
 * Le mod�le : repr�sente les donn�es � afficher. 
 * Normalement, c'est une balle bleue qui rebondit.
 * Elle est d�finie par sa position horizontale et sa vitesse.
 * 
 * Notez que nous conservons aussi des donn�es temporelles, en partie
 * pour afficher des statistiques.  Une partie de ces valeurs est
 * dupliqu�e dans PongAnimator, ce qui n'est pas forc�ment une bonne
 * chose (3FN et tout �a). D'un autre c�t�, la plupart de ces donn�es
 * ne seraient pas dans le mod�le dans un "vrai" jeu.
 */

class Pong5Model {

    private double pos; // position de la balle
    private double vitesse; // vitesse souhait�e.

    private double width; // Largeur de la surface de jeux
    private double height; // hauteur de la surface de jeux.

    private double deplacement; // Vitesse mesur�e

    // Informations temporelles, pour information :
    private long tempsDernierDessin= 0; // date du dernier affichage
    private long tempsDerniereModification; // date de la derni�re modification du mod�le. Cette 
                                            // donn�e est vraiment utile, et ne sert pas qu'aux statistiques.
                                            // voir la fonction avance().
    
    private long tempsDepart; // d�part (date de cr�ation de l'objet)
    private double nombreDessins; // nombre de fois o� la fonction dessiner a �t� appel�e.
    private double nombreModifications; // nombre de fois o� la fonction avance a �t� appel�e.

    
    public Pong5Model (int width, int height) {
	pos= 0;
	vitesse= 1;
	this.width= width;
	this.height= height;

	// On r�cup�re la date de d�part :
	tempsDepart= System.currentTimeMillis(); 
	// Initialisation des donn�es temporelles.
	tempsDernierDessin= tempsDepart;
	tempsDerniereModification= tempsDepart;
	nombreDessins= 0;
	nombreModifications= 0;
    }

    public void dessiner(Graphics g) {
	nombreDessins++;
	
	// temps �coul� depuis le d�but, en secondes
	double tempsEcoule= (System.currentTimeMillis() - tempsDepart) / 1000.0;

	// calcul des statistiques : nombre de dessins par seconde,
	// et nombre de modifications du mod�le par seconde.
	double tauxRafraichissement= nombreDessins/tempsEcoule;
	double tauxMisesAJour= nombreModifications/tempsEcoule;

	// Le dessin proprement dit :
	// On efface :
	g.setColor(java.awt.Color.WHITE);
	g.fillRect(0, 0, (int)width, (int)height);
	// On dessine le disque bleu :
	g.setColor(java.awt.Color.BLUE);
	g.fillOval((int)pos, 100, 40, 40);
	//g.drawString("Hello", (int)pos, 100);

	// On affiche les informations :
	g.setColor(java.awt.Color.BLACK);
	g.drawString( "taux d'affichage : "+ tauxRafraichissement, 0,200);
	g.drawString( "temps moyen entre deux affichages " + 1.0/tauxRafraichissement, 500 ,200);
	g.drawString("taux de mise � jour : "+ tauxMisesAJour, 0,300);
	
	// affichage du temps depuis le dernier dessin :
	long t= System.currentTimeMillis() - tempsDernierDessin;
	String s= ""+ t;
	if (t < 10) s= "0"+t;
	g.drawString("tempsEcoule : "+ ((System.currentTimeMillis() - tempsDernierDessin)), 0,400);
	// vitesse de l'objet.
	g.drawString("d�placement : "+ deplacement, 200,400);

	g.drawString("D�monstration d'animation en java", 100,700);
	
	// mise � jour du temps �coul� depuis le dernier dessin.
	tempsDernierDessin= System.currentTimeMillis();
	
    }

    public void plusVite() {
	vitesse+= 1;
    }

    public void plusLentement() {
	vitesse-= 1;
    }

    /**
     * La m�thode "avance" est appell�e r�guli�rement (� peu pr�s
     * toutes les 5 ms) pour modifier la position de la balle.
     * 
     * <p> 
     */
    public void avance() {
	nombreModifications++;
	
	// La version qui suit essaie de respecter la vitesse
	// demand�e.  Pour qu'elle soit vraiment fiable, il faudrait
	// par contre une horloge pr�cise � la nanoseconde pr�s,
	// plut�t qu'� la microseconde.  En effet, t -
	// tempsDerniereModification est de l'ordre de quelques
	// microsecondes, et la pr�cision de l'horloge aussi (sous
	// certaines versions de windows, elle est de 10 microsecondes
	// !)  Le jdk 1.5 proposera une horloge pr�cise � la
	// nanoseconde. Le jdk1.4 en incorpore d�j� une, mais sans la
	// documenter.

	long t= System.currentTimeMillis();

	// la vitesse est donn�e en pixel par 10 milisecondes
	deplacement= (vitesse * ((t - tempsDerniereModification) / 10.0)); 
	pos+= deplacement;
	// Gestion sommaire du rebond.
	if (pos >= width) {
	    if (vitesse > 0) vitesse= -vitesse;
	} else if (pos <= 0) {
	    if (vitesse <0) vitesse= -vitesse;
	}
	
	tempsDerniereModification= t;
    }

    
    /**
     * Version plus simple de avance(), qui n'utilise pas
     * d'information temporelle : a priori, le mouvement risque d'�tre
     * moins r�gulier (c'est surtout g�nant quand le mouvement est
     * lent).
     */
    
    public void avancePlusSimple() {
	nombreModifications++;
	pos+= vitesse;
	if (pos >= width) {
	    if (vitesse > 0) vitesse= -vitesse;
	} else if (pos <= 0) {
	    if (vitesse <0) vitesse= -vitesse;
	}
    }

    
    
}

/**
 * Cette classe g�re l'animation et le raffraichissement de l'�cran.
 * Elle �tend Runnable, c'est-�-dire qu'elle fournit une m�thode run() qui 
 * est le code ex�cut� en parall�le avec la "boucle graphique" de java,
 * � partir du moment o� on a lanc� t.start() (qui d�marre le thread).
 *
 * On essaie de redessiner assez r�guli�rement la surface de jeux, et 
 * de mettre � jour les donn�es le plus r�guli�rement possible.
 */

class PongAnimator implements Runnable {

    // En passant continuer � false, on arr�terait le jeu (voir run).
    private boolean continuer = true;
    
    // D�lai pour redessiner une image
    static final int REDRAW_DELAY= 10;
    // D�lai de modification du mod�le :
    static final int UPDATE_DELAY= 5;
    // nombre max d'updates sans dessin :
    static final int MAX_SKIPS     = 0;

    // On passe la main � d'autres threads de temps en temps :
    static final int YIELD_INTERVAL= 5;

    // la surface de dessin :
    private Pong5 p;

    // le mod�le :
    private Pong5Model model;

    public PongAnimator(Pong5 p) {
	this.p= p;
	model= p.model;
    }

    
    /**
     * M�thode d'animation du jeux.
     * plusieurs techniques sont reprises de http://fivedots.coe.psu.ac.th/~ad/jg/.
     * 
     *<p> Le but de cette boucle est :
     * <ul>
     * <li> A) d'essayer de raffra�chir l'image � peu pr�s tous les REDRAW_DELAY milisecondes ;
     * <li> B) de garantir en moyenne un raffraichissement des donn�es toutes les UPDATE_DELAY milisecondes.
     * </ul>
     * Attention : B) est diff�rent de A). Si on perd un raffraichissement, on ne le rattrape pas. Par
     * contre, on rattrapera les modifications perdues.
     *
     */

    public void run() {

	long tempsDepart;

	long tempsDernierDessin;
	long tempsDerniereModification;

	// La m�thode sleep() n'est pas forc�ment pr�cise. Dans certaines configurations de java + linux, 
	// sa pr�cision est de l'ordre de 10ms, ce qui est tr�s limite pour nous.
	// On essaie donc d'avoir un comportement correct en moyenne.

	long tempsDepassementSommeil=0; // Le temps que le dernier sleep a dur� en trop.
	int pasDeSommeil= 0; // nombre de fois o� un sleep a �t� ignor�.
	
	tempsDepart = System.currentTimeMillis();
	tempsDernierDessin= tempsDepart- REDRAW_DELAY;
	tempsDerniereModification= tempsDepart -UPDATE_DELAY;

	while (continuer) {
	    // temps depuis le dernier dessin.
	    long intervalle= System.currentTimeMillis() - tempsDernierDessin;

	    // Si ce temps d�passe le temps de rafraichissement, on redessine.
	    if (intervalle >= REDRAW_DELAY) {
		tempsDernierDessin= System.currentTimeMillis();
		p.render();
	    }

	    // M�me id�e pour le raffraichissement, mais 
	    // on raffraichit plusieurs fois si n�cessaire, pour se rapprocher de 
	    // la moyenne souhait�e.
	    intervalle= System.currentTimeMillis() - tempsDerniereModification;

	    if (intervalle >= UPDATE_DELAY) {
		// Il faut mettre � jour le mod�le.

		// On le fait soit MAX_SKIPS fois, 
		// soit intervalle / UPDATE_DELAY fois, 
		// selon celui qui est le plus petit :
		int nbrskips= 0;		
		while (intervalle >= UPDATE_DELAY && nbrskips++ <= MAX_SKIPS ) {
		    tempsDerniereModification= System.currentTimeMillis();
		    model.avance();
		    intervalle-= UPDATE_DELAY;
		}
	    }

	    // On essaie maintenant d'attendre le moment de la prochaine mise � jour.
	    // Dans l'id�al, il reste du temps et nous calculons combien :

	    // temps avant le prochain dessin souhait� :
	    intervalle= REDRAW_DELAY - (System.currentTimeMillis() - tempsDernierDessin);
	    
	    // temps avant la prochaine mise � jour souhait�e :
	    long intervalle2= UPDATE_DELAY - (System.currentTimeMillis() - tempsDerniereModification);
	    
	    // On va attendre le plus petit des deux :
	    if (intervalle2 < intervalle) intervalle= intervalle2;

	    // Si on a "trop dormi" la derni�re fois, on retranche le temps de d�passement au 
	    // temps de sommeil souhait� :
	    intervalle= intervalle- tempsDepassementSommeil; 

	    // si le temps de sommeil souhait� est positif, on va effectivement dormir :

	    if (intervalle > 0) {
		// On va calculer le temps de sommeil r�el, pour
		// tenir compte de l'impr�cision de la m�thode sleep().
		// Attention : ici aussi, si l'horloge est impr�cise, nous avons un probl�me.
		// Il serait donc souhaitable d'utiliser les horloges pr�cises � la nanoseconde.
		
		long startSleep= System.currentTimeMillis();
		try {
		    Thread.sleep(intervalle);
		} catch (InterruptedException e) {}
		long realSleep= System.currentTimeMillis() - startSleep;

		tempsDepassementSommeil= realSleep - intervalle;
		if (tempsDepassementSommeil < 0) tempsDepassementSommeil= 0;		

	    } else {
		// Si on a d�cid� de ne pas dormir pour rattraper un sommeil en trop,
		// on va quand m�me, de temps � autre, laisser la main � d'autres threads 
		// (le thread graphique, le garbage collector), pour 
		// assurer un meilleur fonctionnement de la jvm.
		pasDeSommeil++;

		// On a saut� un sleep(). On supprime donc le temps de sommeil souhait� � tempsDepassementSommeil :
		
		tempsDepassementSommeil= tempsDepassementSommeil - intervalle;
		if (tempsDepassementSommeil < 0) tempsDepassementSommeil= 0;		

		if (pasDeSommeil > YIELD_INTERVAL) {
		    // On a saut� beaucoup de sleep(), et on va donc laisser la main � d'autres threads.
		    Thread.yield();
		    pasDeSommeil=0;
		}
	    }
	}
    }
}
