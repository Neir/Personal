package Controler;

import java.awt.Color;
import java.util.ArrayList;

import Model.IHMSwing;
import Observer.ObserveurSWING;

public class ControlerMenu {
	protected IHMSwing ihm;
	protected ArrayList<ObserveurSWING> listObs =  new ArrayList<ObserveurSWING>();
	protected Color color;
	
	public ControlerMenu(IHMSwing ihm){
		this.ihm = ihm;
		//On définit la liste des observateur afin d'être sûr qu'ils soient bons
		this.listObs = ihm.getList();
	}
	
	public IHMSwing getIHM(){
		return ihm;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
}
