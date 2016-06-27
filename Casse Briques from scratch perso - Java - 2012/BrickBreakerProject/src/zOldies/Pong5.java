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
 * Démonstration d'animation rapide pour un jeu en java.
 * 
 * Largement inspiré d'informations sur le site 
 * <a href="http://fivedots.coe.psu.ac.th/~ad/jg/">http://fivedots.coe.psu.ac.th/~ad/jg/</a>
 *
 * Normalement, un objet graphique java fonctionne de la manière suivante :
 * <ul>
 * <li> il sert à afficher graphiquement des données. Ces données constituent le <em>modèle</em> ;
 * <li> le modèle est représenté par au moins une classe ;
 * <li> quand le modèle est modifié, on appelle la méthode repaint() de l'objet graphique,
 *      qui le marque comme n'étant pas à jour par rapport aux données qu'il affiche ;
 * <li> quand la boucle graphique de java a le temps, elle redessine les objets qui ne sont 
 *      pas à jour, ou ceux qui n'étaient pas visibles et le sont devenus, etc... en utilisant 
 *      la méthode paint (en AWT) ou paintComponent (Swing) à laquelle elle passe un objet Graphics
 *      en paramètre, cet objet représentant la surface de dessin à utiliser.
 * </ul> 
 * 
 * <p> Ce système est parfaitement adapté pour la plupart des
 * applications, y compris certains jeux (par exemple, les jeux de
 * plateau). Par contre, pour un jeu d'action rapide, un problème va
 * se poser : le programme fera tourner en parallèle la boucle
 * graphique et un thread pour gérer l'animation du jeu, les
 * déplacement, etc. Ce dernier aura probablement à sa disposition
 * beaucoup de temps graphique, et l'affichage, normalement effectué
 * par la boucle graphique, ne se fera que de temps en temps.
 *
 * <p>En fait, dans un jeu d'action, on souhaite contrôler plus ou
 * moins le taux de raffraichissement de l'écran (le nombre de fois
 * par seconde où le jeu est redessiné). Pour cela, nous allons
 * court-circuiter les mécanismes normaux de java, et dessiner
 * directement, sans attendre que la boucle graphique le demande. La
 * méthode render() de notre code essaie plus ou moins de faire cela.
 * Avec le jdk 1.4.2_04, Sur un athlon 900, carte ATI radeon 7000 avec
 * accellération gérée par X11, il tient 58 images/s (ça tombe bien,
 * la fréquence du moniteur est de 60 images/s). Une version
 * légèrement modifiée, utilisant les fonctionnalités plein écran du
 * jdk 1.4, permet d'avoir 60 images/s.
 * 
 *
 * <p> Le double buffering : normalement, pour dessiner quelque chose,
 * on efface la surface de dessin, et on dessine les divers éléments
 * dessus. Le problème est que, si c'est fait directement, on "voit"
 * le dessin se réaliser ; en pratique, le temps de dessiner les
 * divers éléments, l'utilisateur perçoit une sorte de scintillement
 * de l'image, assez désagréable. Pour éviter cela, on dessine d'abord
 * tout sur une image non affichée, puis on copie cette image sur
 * l'écran. Comme la copie est une opération très rapide, l'impression
 * de scintillement disparaît. Essayez de mettre utiliseDoubleBuffer à
 * false, pour voir la différence.
 * 
 * @author <a href="mailto:rosmord@iut.univ-paris8.fr">Serge ROSMORDUC</a>
 * @version
 */

public class Pong5 extends JFrame{

    Pong5Model model;
    private Image doubleBuffer= null; 
    private boolean utiliseDoubleBuffer= true; // Mettre à false pour voir le programme fonctionner sans double buffering.

    public Pong5(int width, int height) {
	setSize(width, height);

	setIgnoreRepaint(true); // Ne tient pas compte du système normal de raffraichissement des fenêtres java
	setResizable(false); // empêche les changements de taille
	setUndecorated(true); // supprime les bordures, etc...

	setBackground(java.awt.Color.WHITE);

	model= new Pong5Model(getWidth(), getHeight());
	Thread t= new Thread(new PongAnimator(this));
	
	// Arrêt et manipulation des objets. Ne devrait bien évidemement pas être dans cette classe.
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
	
	// On démarre le "thread" d'animation.
	// Cela lance sa méthode "run" en parallèle avec la boucle graphique.
	t.start();
	// version sans thread : (new PongAnimator(this)).run();
    }
    
    /**
     * Procedure qui affiche le jeux. 
     * On utilise le procédé du double buffer pour éviter un scintillement.
     */
    public void render() {
	
	if (utiliseDoubleBuffer) {
	    // Création "paresseuse" du double buffer : 
	    if (doubleBuffer== null) {
		doubleBuffer= createImage(getWidth(), getHeight());
	    }
	    // la surface de dessin du double buffer :
	    Graphics gDessin;
	    gDessin= doubleBuffer.getGraphics();
	    model.dessiner(gDessin);
	    gDessin.dispose(); // On libère la ressource 
	    // On récupère la surface de dessin de la fenêtre
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
 * Le modèle : représente les données à afficher. 
 * Normalement, c'est une balle bleue qui rebondit.
 * Elle est définie par sa position horizontale et sa vitesse.
 * 
 * Notez que nous conservons aussi des données temporelles, en partie
 * pour afficher des statistiques.  Une partie de ces valeurs est
 * dupliquée dans PongAnimator, ce qui n'est pas forcément une bonne
 * chose (3FN et tout ça). D'un autre côté, la plupart de ces données
 * ne seraient pas dans le modèle dans un "vrai" jeu.
 */

class Pong5Model {

    private double pos; // position de la balle
    private double vitesse; // vitesse souhaitée.

    private double width; // Largeur de la surface de jeux
    private double height; // hauteur de la surface de jeux.

    private double deplacement; // Vitesse mesurée

    // Informations temporelles, pour information :
    private long tempsDernierDessin= 0; // date du dernier affichage
    private long tempsDerniereModification; // date de la dernière modification du modèle. Cette 
                                            // donnée est vraiment utile, et ne sert pas qu'aux statistiques.
                                            // voir la fonction avance().
    
    private long tempsDepart; // départ (date de création de l'objet)
    private double nombreDessins; // nombre de fois où la fonction dessiner a été appelée.
    private double nombreModifications; // nombre de fois où la fonction avance a été appelée.

    
    public Pong5Model (int width, int height) {
	pos= 0;
	vitesse= 1;
	this.width= width;
	this.height= height;

	// On récupère la date de départ :
	tempsDepart= System.currentTimeMillis(); 
	// Initialisation des données temporelles.
	tempsDernierDessin= tempsDepart;
	tempsDerniereModification= tempsDepart;
	nombreDessins= 0;
	nombreModifications= 0;
    }

    public void dessiner(Graphics g) {
	nombreDessins++;
	
	// temps écoulé depuis le début, en secondes
	double tempsEcoule= (System.currentTimeMillis() - tempsDepart) / 1000.0;

	// calcul des statistiques : nombre de dessins par seconde,
	// et nombre de modifications du modèle par seconde.
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
	g.drawString("taux de mise à jour : "+ tauxMisesAJour, 0,300);
	
	// affichage du temps depuis le dernier dessin :
	long t= System.currentTimeMillis() - tempsDernierDessin;
	String s= ""+ t;
	if (t < 10) s= "0"+t;
	g.drawString("tempsEcoule : "+ ((System.currentTimeMillis() - tempsDernierDessin)), 0,400);
	// vitesse de l'objet.
	g.drawString("déplacement : "+ deplacement, 200,400);

	g.drawString("Démonstration d'animation en java", 100,700);
	
	// mise à jour du temps écoulé depuis le dernier dessin.
	tempsDernierDessin= System.currentTimeMillis();
	
    }

    public void plusVite() {
	vitesse+= 1;
    }

    public void plusLentement() {
	vitesse-= 1;
    }

    /**
     * La méthode "avance" est appellée régulièrement (à peu près
     * toutes les 5 ms) pour modifier la position de la balle.
     * 
     * <p> 
     */
    public void avance() {
	nombreModifications++;
	
	// La version qui suit essaie de respecter la vitesse
	// demandée.  Pour qu'elle soit vraiment fiable, il faudrait
	// par contre une horloge précise à la nanoseconde près,
	// plutôt qu'à la microseconde.  En effet, t -
	// tempsDerniereModification est de l'ordre de quelques
	// microsecondes, et la précision de l'horloge aussi (sous
	// certaines versions de windows, elle est de 10 microsecondes
	// !)  Le jdk 1.5 proposera une horloge précise à la
	// nanoseconde. Le jdk1.4 en incorpore déjà une, mais sans la
	// documenter.

	long t= System.currentTimeMillis();

	// la vitesse est donnée en pixel par 10 milisecondes
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
     * d'information temporelle : a priori, le mouvement risque d'être
     * moins régulier (c'est surtout gênant quand le mouvement est
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
 * Cette classe gère l'animation et le raffraichissement de l'écran.
 * Elle étend Runnable, c'est-à-dire qu'elle fournit une méthode run() qui 
 * est le code exécuté en parallèle avec la "boucle graphique" de java,
 * à partir du moment où on a lancé t.start() (qui démarre le thread).
 *
 * On essaie de redessiner assez régulièrement la surface de jeux, et 
 * de mettre à jour les données le plus régulièrement possible.
 */

class PongAnimator implements Runnable {

    // En passant continuer à false, on arrêterait le jeu (voir run).
    private boolean continuer = true;
    
    // Délai pour redessiner une image
    static final int REDRAW_DELAY= 10;
    // Délai de modification du modèle :
    static final int UPDATE_DELAY= 5;
    // nombre max d'updates sans dessin :
    static final int MAX_SKIPS     = 0;

    // On passe la main à d'autres threads de temps en temps :
    static final int YIELD_INTERVAL= 5;

    // la surface de dessin :
    private Pong5 p;

    // le modèle :
    private Pong5Model model;

    public PongAnimator(Pong5 p) {
	this.p= p;
	model= p.model;
    }

    
    /**
     * Méthode d'animation du jeux.
     * plusieurs techniques sont reprises de http://fivedots.coe.psu.ac.th/~ad/jg/.
     * 
     *<p> Le but de cette boucle est :
     * <ul>
     * <li> A) d'essayer de raffraîchir l'image à peu près tous les REDRAW_DELAY milisecondes ;
     * <li> B) de garantir en moyenne un raffraichissement des données toutes les UPDATE_DELAY milisecondes.
     * </ul>
     * Attention : B) est différent de A). Si on perd un raffraichissement, on ne le rattrape pas. Par
     * contre, on rattrapera les modifications perdues.
     *
     */

    public void run() {

	long tempsDepart;

	long tempsDernierDessin;
	long tempsDerniereModification;

	// La méthode sleep() n'est pas forcément précise. Dans certaines configurations de java + linux, 
	// sa précision est de l'ordre de 10ms, ce qui est très limite pour nous.
	// On essaie donc d'avoir un comportement correct en moyenne.

	long tempsDepassementSommeil=0; // Le temps que le dernier sleep a duré en trop.
	int pasDeSommeil= 0; // nombre de fois où un sleep a été ignoré.
	
	tempsDepart = System.currentTimeMillis();
	tempsDernierDessin= tempsDepart- REDRAW_DELAY;
	tempsDerniereModification= tempsDepart -UPDATE_DELAY;

	while (continuer) {
	    // temps depuis le dernier dessin.
	    long intervalle= System.currentTimeMillis() - tempsDernierDessin;

	    // Si ce temps dépasse le temps de rafraichissement, on redessine.
	    if (intervalle >= REDRAW_DELAY) {
		tempsDernierDessin= System.currentTimeMillis();
		p.render();
	    }

	    // Même idée pour le raffraichissement, mais 
	    // on raffraichit plusieurs fois si nécessaire, pour se rapprocher de 
	    // la moyenne souhaitée.
	    intervalle= System.currentTimeMillis() - tempsDerniereModification;

	    if (intervalle >= UPDATE_DELAY) {
		// Il faut mettre à jour le modèle.

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

	    // On essaie maintenant d'attendre le moment de la prochaine mise à jour.
	    // Dans l'idéal, il reste du temps et nous calculons combien :

	    // temps avant le prochain dessin souhaité :
	    intervalle= REDRAW_DELAY - (System.currentTimeMillis() - tempsDernierDessin);
	    
	    // temps avant la prochaine mise à jour souhaitée :
	    long intervalle2= UPDATE_DELAY - (System.currentTimeMillis() - tempsDerniereModification);
	    
	    // On va attendre le plus petit des deux :
	    if (intervalle2 < intervalle) intervalle= intervalle2;

	    // Si on a "trop dormi" la dernière fois, on retranche le temps de dépassement au 
	    // temps de sommeil souhaité :
	    intervalle= intervalle- tempsDepassementSommeil; 

	    // si le temps de sommeil souhaité est positif, on va effectivement dormir :

	    if (intervalle > 0) {
		// On va calculer le temps de sommeil réel, pour
		// tenir compte de l'imprécision de la méthode sleep().
		// Attention : ici aussi, si l'horloge est imprécise, nous avons un problème.
		// Il serait donc souhaitable d'utiliser les horloges précises à la nanoseconde.
		
		long startSleep= System.currentTimeMillis();
		try {
		    Thread.sleep(intervalle);
		} catch (InterruptedException e) {}
		long realSleep= System.currentTimeMillis() - startSleep;

		tempsDepassementSommeil= realSleep - intervalle;
		if (tempsDepassementSommeil < 0) tempsDepassementSommeil= 0;		

	    } else {
		// Si on a décidé de ne pas dormir pour rattraper un sommeil en trop,
		// on va quand même, de temps à autre, laisser la main à d'autres threads 
		// (le thread graphique, le garbage collector), pour 
		// assurer un meilleur fonctionnement de la jvm.
		pasDeSommeil++;

		// On a sauté un sleep(). On supprime donc le temps de sommeil souhaité à tempsDepassementSommeil :
		
		tempsDepassementSommeil= tempsDepassementSommeil - intervalle;
		if (tempsDepassementSommeil < 0) tempsDepassementSommeil= 0;		

		if (pasDeSommeil > YIELD_INTERVAL) {
		    // On a sauté beaucoup de sleep(), et on va donc laisser la main à d'autres threads.
		    Thread.yield();
		    pasDeSommeil=0;
		}
	    }
	}
    }
}
