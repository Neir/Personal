package BriandaisTree;

import javax.swing.JTabbedPane;

import AffichageBriandais.panelBriandais;
import AffichageGeneral.PanelAjout;
import Tools.SwingWorker;

public class AjoutTexteLong extends SwingWorker {

	String mot ;
	TreeBriandais th;
	JTabbedPane Arbres;
	int size;
	PanelAjout Ajout;

	public AjoutTexteLong(String mot, TreeBriandais th, JTabbedPane arbres, int a, PanelAjout Ajout){
		this.mot=mot;
		this.th = th ;
		this.Arbres=arbres;
		this.size=a;
		this.Ajout=Ajout ;
		
		
	}
	public Object construct() {
		
		th.ajoutPhrase(mot,true);
		panelBriandais test = new panelBriandais(th,th.NombreMot() );
		Arbres.add("Briandais n°"+size,test.getArbre());
		Ajout.AjouterArbre("Briandais n°"+size);
		Arbres.setSelectedIndex(Arbres.getTabCount()-1);
		
		return th;
	}

}
