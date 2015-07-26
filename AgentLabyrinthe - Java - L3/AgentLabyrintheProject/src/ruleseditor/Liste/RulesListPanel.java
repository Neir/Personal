package ruleseditor.Liste;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import ruleseditor.RulePanel;

import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;
import agent.laby.ContenuCase;

public class RulesListPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ArrayList<Regle> rulesList;
	private ArrayList<RulePanel> rulePanelList;
	
	public RulesListPanel(ArrayList<Regle>rulesList){
		rulePanelList = new ArrayList<RulePanel>();
		
		int taille = rulesList.size();
		// / positionne le comportement de this.add pour la suite
		GridLayout gl = new GridLayout((int) Math.sqrt(taille), 0);
		setLayout(gl);
        gl.setHgap(5); // 5 pixels d'espace entre les colonnes (H comme Horizontal)
        gl.setVgap(5);
		for (int i = 0; i < taille; i++) {
			rulePanelList.add(new RulePanel(rulesList.get(i)));
			add(rulePanelList.get(i));
		}
		//setRules(rulesList);
		this.rulesList = rulesList;
	}
	
	public void updateGraphics() {
		rulePanelList = new ArrayList<RulePanel>(); 
		for(Regle r : rulesList)
			rulePanelList.add(new RulePanel(r));
	}

	public void modifRulesList() {
		for (int i = 0; i < rulesList.size(); i++)
			rulesList.set(i, rulePanelList.get(i).getRule());
	}

	public void setActionSelected(ContenuCase actionSelected) {
		for(RulePanel rp : rulePanelList)
			rp.setActionSelected(actionSelected);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Passe dans actionPerformed de RulesListPanel !");
		for (int i = 0; i < rulesList.size(); i++)
			rulePanelList.get(i).actionPerformed(e);
	}
	
	public void addRuleVierge(){
		Regle regleVierge = new Regle(new Observation("        "), Direction.DROITE);
		rulesList.add(regleVierge);
		rulePanelList.add(new RulePanel(regleVierge));
	}
	
	public ArrayList<Regle> getRulesList() {
		return rulesList;
	}

	public void supprNextRule() {
		for(RulePanel rp : rulePanelList){
			rp.aSupprimer();
			if(rp.supprimer()){
				rulePanelList.remove(rp);
				rulesList.remove(rp.getRule());
			}
		}
	}
}
