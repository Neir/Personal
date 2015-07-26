package agent.laby.interf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import agent.laby.ChargeurLabyrinthe;
import agent.laby.ContenuCase;
import agent.laby.Labyrinthe;

/**
 * Cette classe représente une interface graphique qui permet de dessiner un
 * labyrinthe. On étend {@link JFrame}.
 * 
 * Le constructeur permet d'instancier directement et de visualiser l'IHM.
 * 
 * @author Yann,sigaud
 */
public class LabyBuilder extends JFrame {

	private static final long serialVersionUID = 1L;

	// Des constantes pour mettre sur les textes des menus
	// Il est recommandé (best practice) au maximum d'utiliser
	// des constantes plutôt que des String en litéral "Toto" enfouies dans le code.
	// private static final String XML_EXPORT_MENU = "Export Maze To XML";
	private static final String SER_EXPORT_MENU = "Sauver labyrinthe";
	private static final String SER_IMPORT_MENU = "Charger labyrinthe";

	// zone boutons
	private JPanel sidePanel;

	// Le labyrinthe sous-jacent
	private Labyrinthe laby;

	// Pour les tests getSource dans le Menu Listener : les sources d'événements menu
	private JMenuItem mi_export;
	private JMenuItem mi_import;

	// La zone montrant le labyrinthe 
	private LabyPanel centerPanel;

	// Les tailles, constantes
	private static final int COLS = 15, LIGNES = 10;

	/**
	 * Constructeur
	 */
	public LabyBuilder() {
		// Titre de la JFrame
		super("Laby Builder");
		
		// Construire le maze
		laby = new Labyrinthe(COLS, LIGNES);

		// creer les Panel et menus
		createCenterPanel();
		createSidePanel();
		createMenus();

		// Positionner la taille de la fenetre
		setSize(800, 758);
		setResizable(false);
		// traiter le clic sur la croix
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// rendre visible 
		setVisible(true);
	}

	/**
	 * Créée les menus d'import export et y associe le traitement défini par MenuListener.
	 */
	private void createMenus() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");

		ActionListener ml = new ExportMenuListener();

		mi_export = new JMenuItem(SER_EXPORT_MENU);
		mi_export.addActionListener(ml);
		menu.add(mi_export);

		mi_import = new JMenuItem(SER_IMPORT_MENU);
		mi_import.addActionListener(ml);
		menu.add(mi_import);

		menubar.add(menu);
		menubar.setVisible(true);
		
		// positionne la barre
		this.setJMenuBar(menubar);
	}

	/**
	 * Crée le panneau latéral, ses boutons et associe les actions appropriées aux boutons.
	 */
	private void createSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		JTextArea instructions = new JTextArea();
		instructions
				.setText("Cliquez sur un bouton puis\n sur des cases pour\n modifier le labyrinthe!\n");
		instructions.setEditable(false);
		sidePanel.add(instructions);

		JButton spaceButton = new JButton("case vide");
		spaceButton.setIcon(new ImageIcon("icones/vide.png"));
		sidePanel.add(spaceButton);
		
		JButton dotButton = new JButton("point");
		dotButton.setIcon(new ImageIcon("icones/dot.jpg"));
		sidePanel.add(dotButton);
		
		JButton wallButton = new JButton("mur");
		wallButton.setIcon(new ImageIcon("icones/wall.jpg"));
		sidePanel.add(wallButton);

		// Les actions sur les boutons, cette forme anonyme évite les
		// getSource() dans actionPerformed
		spaceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// L'action consiste simplement à positionner l'effet d'un
				// clic dans l'interface MazePanel
				centerPanel.setActionSelected(ContenuCase.VIDE);
			}
		});

		dotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.setActionSelected(ContenuCase.POINT);
			}
		});

		wallButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.setActionSelected(ContenuCase.MUR);
			}
		});

		getContentPane().add(sidePanel, BorderLayout.EAST);

	}
	
	/**
	 * Crée le MazePanel responsable d'afficher le Maze courant.
	 */
	private void createCenterPanel() {
		centerPanel = new LabyPanel(laby);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Classe interne pour gérer les événements menu
	 */
	private class ExportMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			try {
				/**
				 * Attention à la sémantique des classes internes ! mi_export
				 * est membre de la classe englobante.
				 */
				if (item == mi_export) {
					exportMazeData();
				} else if (item == mi_import) {
					chargerLabyrinthe();
				}
				// else if (item == mi_other) {
				// }
			} catch (IOException e1) {
				// une erreur "jolie"
				// Attention encore aux classes interne, on veut passer la
				// JFrame
				// i.e. le parent des JDialog en premier argument.
				// C'est la classe englobante, mais on utilise "getFrame" pour
				// la retrouver.
				JOptionPane.showInternalMessageDialog(getFrame(), e1
						.getMessage(), "Error during export ",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Pour repasser this au contexte de la classe locale menuListener
	 * 
	 * @return this, typage relaxé en JFrame
	 */
	private JFrame getFrame() {
		return this;
	}

	/**
	 * Export du labyrinthe par la sérialisation
	 * 
	 * @throws IOException
	 */
	public void exportMazeData() throws IOException {
		// Force la remise à jour de l'état du labyrinthe en fonction des boutons
		// affichés dans l'interface graphique de dessin
		// What You See Is What You Get
		centerPanel.modifLaby();

		String fileName = JOptionPane
				.showInputDialog("Please enter a file name to save this maze (extension .mze).");
		ChargeurLabyrinthe.sauverLabyrinthe(fileName, laby);
	}

	/**
	 * Import du labyrinthe sauvé par la sérialisation
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void chargerLabyrinthe() throws IOException {

		// / Code pris directement dans la doc de JFileChooser.
		// L'argument "./" permet de démarrer directement dans le repertoire
		// courant
		// La version par défaut JFilechooser() démarre dans le $home.
		JFileChooser chooser = new JFileChooser(new File("./"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Maze files", "mze");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Entrez un nom de fichier .mze (avec l'extension");
		int returnVal = chooser.showOpenDialog(this);
		String fileName;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileName = chooser.getSelectedFile().getName();
		} else {
			// Un cancel, click sur la croix...
			return;
		}

		laby = ChargeurLabyrinthe.chargerLabyrinthe(fileName);
		centerPanel.setLaby(laby);
	}

	/**
	 * Export du labyrinthe au format xml
	 * 
	 * @throws IOException
	 */
	public void sauverLabyrintheEnXML() throws IOException {
		centerPanel.modifLaby();
		ChargeurLabyrinthe.sauverLabyrintheEnXML("maze_xml.txt", laby);
	}

	/**
	 * Getter
	 * 
	 * @return : le labyrinthe
	 */
	public Labyrinthe getLaby() {
		return laby;
	}

	/**
	 * Méthode principale
	 * 
	 * @param args
	 *            : non utilisé
	 */
	public static void main(String[] args) {
		new LabyBuilder();
	}

}
