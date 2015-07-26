package agent.control;

import java.util.List;

/**
 * Le controleur d'un agent prend les décisions.
 * 
 * Les décisions sont prises en fonction de l'environnement de l'agent,
 *  passé sous la forme d'un tableau de CellContent. 
 *  
 * @author Yann 
 *
 */
public interface IControleur {

	/**
	 * Détermine l'action choisie par la base de règles.
	 * Sensors porte l'état des capteurs, dans la convention horaire:
	 * 0 = Nord, 1 = NE, 2 = E, 3 = SE, 4 = S, 5 = SO, 6 = O, 7 = NO
	 * @param capteurs : état des capteurs de l'agent
	 * @return : l'action choisie = Direction à appliquer
	 */
	Direction choisitAction(Observation capteurs);

	/**
	 * Copie.
	 * @return une copie profonde de cet objet.
	 */
	IControleur clone();

	/**
	 * Fait muter aléatoirement cette règle, avec la probabilité d.
	 * @param d la probabilité de mutation d'une règle du Controleur
	 */
	void muter(double d);

	/**
	 * Permet d'engendrer de nouveaux Controleurs.
	 * Croise le contrôleur "this" avec le controleur passé en paramètre,
	 * puis fait muter le résultat avec la probabilité fournie.
	 * @param controleur le controleur avec lequel on croise. 
	 * @param d la probabilité de mutation de chaque règle du fils
	 * @return un fils de this et de controleur 
	 */
	IControleur creeFils(IControleur controleur, double d);
	
	List<Regle> getRuleset();
	void appendRules(List<Regle> rules);

	String affiche();

}