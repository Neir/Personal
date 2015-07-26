package Tools;

import javax.swing.JFrame;

import HybridTrie.TrieHybride;

public class Affichage {
	public static void printTrieHybride(TrieHybride th){
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);
		fenetre.setSize(1200, 800);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	//	fenetre.setContentPane(new TreePanel(th));
	}
}
