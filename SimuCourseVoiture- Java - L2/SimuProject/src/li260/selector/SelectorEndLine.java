package li260.selector;

import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.geometrie.Vecteur;
import li260.radar.Radar;
import li260.tools.Tools;
import li260.voiture.Voiture;

public class SelectorEndLine implements Selector {
	private Circuit c;
	private Voiture v;
	private Radar r;
	private double[] tab;
	
	public SelectorEndLine(Circuit c, Voiture v, Radar r) {
		super();
		this.c = c;
		this.v = v;
		this.r = r;
	}

	public boolean isSelected() {
		/*
		Vecteur pos = v.getPosition().clonage();
		pos.normalise();
		tab = r.getThetas();
		for(int i=0; i<tab.length; i++){
			Vecteur dir = v.getDirection().clonage();
			dir.rotation(tab[i]);
			//System.out.println("SELECTORENDLINE.test endline"+tab[i]);
			while(Tools.isRunnable(c.getTerrain(pos))){
				//System.out.println("SELECTORENDLINE."+c.getTerrain(pos));
				if(c.getTerrain(pos) == Terrain.EndLine){
					System.out.println("SELECTORENDLINE.select ENDLINE");
					return true;
				}
				pos.add(dir.fact(0.01));
			}
			//System.out.println("SELECTORENDLINE."+i+"  "+tab[i]+"  "+c.getTerrain(pos));
		}
		
		
		
		return false;
		*/
		
		
		
		//System.out.println("SELECTORENDLINE."+r.isVoitEndLine());
		//return r.isVoitEndLine();
		
		Vecteur pos = v.getPosition().clonage();
		Vecteur dir = v.getDirection().clonage();
		while(Tools.isRunnable(c.getTerrain(pos))){
			pos.add(dir.fact(0.01));
			if(c.getTerrain(pos) == Terrain.EndLine)
				return true;
		}
		
		
		return false;
	}

}
