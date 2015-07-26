package pobj.algogen.adapter.agent;

import agent.control.ControlFactory;
import agent.control.Controleur;
import agent.control.IControleur;
import pobj.algogen.AbstractIndividu;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.arith.EnvEval;

public class ControleurIndividuAdapter extends AbstractIndividu{
	private IControleur valeurPropre;
	
	public ControleurIndividuAdapter(IControleur valeurPropre) {
		super();
		this.valeurPropre = valeurPropre;
	}

	public ControleurIndividuAdapter(int nbRules){
		valeurPropre = ControlFactory.createControleur(nbRules);
	}
	
	@Override
	public Object getValeurPropre() {
		return valeurPropre;
	}

	@Override
	public void setValeurPropre(Object valeurPropre) {
		this.valeurPropre = (IControleur) valeurPropre;
	}

	@Override
	public void muter(double ratio) {
		// TODO Auto-generated method stub
		valeurPropre.muter(1);
	}

	@Override
	public Individu croiser(Individu autre) {
		// TODO Auto-generated method stub
		return new ControleurIndividuAdapter(((Controleur) valeurPropre).copieCroisee((IControleur) autre.getValeurPropre()));
	}

	@Override
	public String affichage(Environnement env) {
		// TODO Auto-generated method stub
		return "\nIndividu : valeurPropre = "+valeurPropre.affiche()+" / fitness : "+fitness;
	}

	public Individu clone(){
		return new ControleurIndividuAdapter(valeurPropre.clone());
	}

	@Override
	public void getIdentifier() {
		// TODO Auto-generated method stub
		System.out.println("C'est un agent");
	}
}
