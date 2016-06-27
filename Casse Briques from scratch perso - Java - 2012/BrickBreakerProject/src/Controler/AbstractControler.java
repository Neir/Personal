package Controler;

import java.util.ArrayList;

import li260.geometrie.Vecteur;

import Model.IHMSwing;
import Observer.ObserveurSWING;
import Simulation.Simulation;

public abstract class AbstractControler {
	
	protected IHMSwing ihm;
	protected int mouv;
	protected double pos;
	protected boolean start = false;
	protected boolean pause = false;
	protected ArrayList<ObserveurSWING> listObs =  new ArrayList<ObserveurSWING>();
	
	public AbstractControler(IHMSwing ihm){
		this.ihm = ihm;
		//On définit la liste des observateur afin d'être sûr qu'ils soient bons
		this.listObs = ihm.getList();
	}
	
	/**
	 * Définit l'opérateur
	 * @param ope
	 */
	
	public IHMSwing getIhm(){
		return ihm;
	}
	
	public void setMouv(int mouv){
		this.mouv = mouv;
		control();
	}
	
	public void setPos(double pos){
		this.pos = pos;
		control();
	}
	
	public void setStart(boolean start){
		this.start = start;
		control();
	}
	
	public void setPause(){
		this.pause = !pause;
		control();
	}
	
	/**
	 * Définit le nombre
	 * @param nombre
	 */
	//public void setNombre(String nombre){
	
	/**
	 * Efface
	 */
	/*public void reset(){
		this.ihm.reset();
	}*/
	
	/**
	 * Méthode de contrôle
	 */
	abstract void control();
}
