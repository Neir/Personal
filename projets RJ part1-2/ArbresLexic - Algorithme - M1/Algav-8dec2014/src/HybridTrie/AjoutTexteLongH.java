package HybridTrie;

import javax.swing.JTabbedPane;

import AffichageGeneral.PanelAjout;
import AffichageHybride.panelHybride;
import Tools.SwingWorker;

public class AjoutTexteLongH extends SwingWorker {

	String mot ;
	TrieHybride th;
	JTabbedPane Arbres;
	int size;
	PanelAjout Ajout;

	public AjoutTexteLongH(String mot, TrieHybride th, JTabbedPane arbres, int a, PanelAjout Ajout){
		this.mot=mot;
		this.th = th ;
		this.Arbres=arbres;
		this.size=a;
		this.Ajout=Ajout ;
		
		
	}
	public Object construct() {
		
		th.ajoutPhrase(mot);
		panelHybride test = new panelHybride(th,th.comptageMots() );
		Arbres.add("Hybride n°"+size,test.getArbre());
		Ajout.AjouterArbre("Hybride n°"+size);
		Arbres.setSelectedIndex(Arbres.getTabCount()-1);
		
		return th;
	}

}
