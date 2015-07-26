package pobj.algogen.adapter.arith;

import pobj.algogen.AbstractIndividu;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.arith.EnvEval;
import pobj.arith.Expression;
import pobj.arith.Operator;

/**
 * Classe de création d'un Individu
 */
public class IndividuExpression extends AbstractIndividu{
	/** Valeur propre de l'invividu */
	private Expression valeurPropre;

	/**
	 * Construit un individu de valeur propre aléatoire
	 */
	public IndividuExpression(int prof, EnvEval env){
		valeurPropre = ExpressionFactory.createRandomExpression(prof);
	}

	/**
	 * Construit un individu de valeur propre spécifiée
	 * @param valeur propre initiale de l'individu
	 */
	public IndividuExpression(Expression valeurPropre){
		this.valeurPropre=valeurPropre;
	}

	/**
	 * Accède à la valeur propre de l'individu
	 * @return valeur propre de l'individu
	 */
	public Object getValeurPropre(){
		return valeurPropre;
	}

	/**
	 * Redéfinit la valeur propre de l'individu
	 * @param nouvelle valeur propre
	 */
	public void setValeurPropre(Object valeurPropre){
		this.valeurPropre = (Expression) valeurPropre;
	}

	/**
	 * Décrit l'individu
	 * @return la chaine de caractère décrivant l'individu
	 */
	public String toString(){
			return "\nIndividu : valeurPropre = "+valeurPropre+" / fitness : "+fitness;
	}
	
	public String affichage(Environnement env){
		return "\nIndividu : valeurPropre = "+valeurPropre.affichage(env)+" / fitness : "+fitness;
	}
	
	public void muter(double ratio){
		valeurPropre = ExpressionFactory.createRandomExpression(3);
	}
	
	public Individu croiser(Individu autre){
		Expression vPInd = (Expression) getValeurPropre();
		Expression vPAutre = (Expression) autre.getValeurPropre();
		Expression nouvVP;
		
		if(vPInd.equals(vPAutre)){
			nouvVP = vPInd;
		} else {
			nouvVP= ExpressionFactory.createOperateurBinaire(Operator.DIV, 
					ExpressionFactory.createOperateurBinaire(Operator.PLUS, vPInd, vPAutre), 
					ExpressionFactory.createConstante(2));
		}
	
		return new IndividuExpression(nouvVP);
	}
	
	public Individu clone(){
		return new IndividuExpression(valeurPropre.clone());
		
	}
	
	@Override
	public void getIdentifier() {
		// TODO Auto-generated method stub
		System.out.println("C'est une expression");
	}
}
