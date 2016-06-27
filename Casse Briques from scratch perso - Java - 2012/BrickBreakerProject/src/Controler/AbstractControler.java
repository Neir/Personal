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
		//On d�finit la liste des observateur afin d'�tre s�r qu'ils soient bons
		this.listObs = ihm.getList();
	}
	
	/**
	 * D�finit l'op�rateur
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
	 * D�finit le nombre
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
	 * M�thode de contr�le
	 */
	abstract void control();
}
