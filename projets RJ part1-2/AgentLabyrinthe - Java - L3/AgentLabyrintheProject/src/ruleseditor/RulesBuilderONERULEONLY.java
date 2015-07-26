package ruleseditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

import agent.control.Regle;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.ContenuCase;

public class RulesBuilderONERULEONLY extends JFrame{

	private static final long serialVersionUID = 1L;
	private Regle r;
	private JPanel sidePanel;
	private RulePanel centerPanel;
	
	// Des constantes pour mettre sur les textes des menus
	// Il est recommandé (best practice) au maximum d'utiliser
	// des constantes plutôt que des String en litéral "Toto" enfouies dans le code.
	// private static final String XML_EXPORT_MENU = "Export Maze To XML";
	private static final String SER_EXPORT_MENU = "Sauver regle";
	private static final String SER_IMPORT_MENU = "Charger regle";
	
	// Pour les tests getSource dans le Menu Listener : les sources d'événements menu
	private JMenuItem mi_export;
	private JMenuItem mi_import;
	
	public RulesBuilderONERULEONLY(Regle r){
		super("Rules Builder");
		
		this.r = r;
		
		createCenterPanel();
		createSidePanel();
		createMenus();
		// Positionner la taille de la fenetre
		setSize(1200, 600);
		setResizable(false);
		// traiter le clic sur la croix
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		// rendre visible 
		setVisible(true);
	}
	
	private void createSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		JTextArea instructions = new JTextArea();
		instructions
				.setText("Cliquez sur un bouton puis\n sur des cases pour\n modifier la regle!\n\nRegle de d�part :\n"+r.toString());
		instructions.setEditable(false);
		sidePanel.add(instructions);

		JButton anyButton = new JButton("any");
		anyButton.setIcon(new ImageIcon("icones/any.png"));
		sidePanel.add(anyButton);
		
		JButton videButton = new JButton("vide");
		videButton.setIcon(new ImageIcon("icones/vide.png"));
		sidePanel.add(videButton);
		
		JButton pointButton = new JButton("point");
		pointButton.setIcon(new ImageIcon("icones/dot.jpg"));
		sidePanel.add(pointButton);
		
		JButton murButton = new JButton("mur");
		murButton.setIcon(new ImageIcon("icones/wall.jpg"));
		sidePanel.add(murButton);

		// Les actions sur les boutons, cette forme anonyme évite les
		// getSource() dans actionPerformed
		anyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// L'action consiste simplement à positionner l'effet d'un
				// clic dans l'interface MazePanel
				centerPanel.setActionSelected(ContenuCase.ANY);
			}
		});

		videButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.setActionSelected(ContenuCase.VIDE);
			}
		});

		pointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.setActionSelected(ContenuCase.POINT);
			}
		});

		murButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.setActionSelected(ContenuCase.MUR);
			}
		});
		getContentPane().add(sidePanel, BorderLayout.EAST);
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
					exportRulesData();
				} else if (item == mi_import) {
					chargerRegles();
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
	
	private JFrame getFrame() {
		return this;
	}
	
	private void createCenterPanel() {
		centerPanel = new RulePanel(r);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
	}
	
	public void exportRulesData() throws IOException {
		// Force la remise à jour de l'état du labyrinthe en fonction des boutons
		// affichés dans l'interface graphique de dessin
		// What You See Is What You Get
		centerPanel.modifRule();

		String fileName = JOptionPane
				.showInputDialog("Please enter a file name to save this rules (extension .rls).");
		ArrayList<Regle> rl = new ArrayList<Regle>();
		rl.add(r);
		ChargeurRegles.sauverRegles(fileName, rl);
	}

	/**
	 * Import du labyrinthe sauvé par la sérialisation
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void chargerRegles() throws IOException {

		// / Code pris directement dans la doc de JFileChooser.
		// L'argument "./" permet de démarrer directement dans le repertoire
		// courant
		// La version par défaut JFilechooser() démarre dans le $home.
		JFileChooser chooser = new JFileChooser(new File("./"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Rules files", "rls");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Entrez un nom de fichier .rls (avec l'extension");
		int returnVal = chooser.showOpenDialog(this);
		String fileName;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileName = chooser.getSelectedFile().getName();
		} else {
			// Un cancel, click sur la croix...
			return;
		}

		r = ChargeurRegles.chargerRegles(fileName).get(0);
		centerPanel.setRule(r);
	}
	
}
