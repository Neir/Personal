package li260.selector;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;
import li260.tools.Tools;
import li260.voiture.Voiture;

public class SelectorObstacle implements Selector {
	private Circuit c;
	private Voiture v;
	
	public SelectorObstacle(Circuit c, Voiture v) {
		super();
		this.c = c;
		this.v = v;
	}

	@Override
	public boolean isSelected() {
		Vecteur pos = v.getPosition().clonage();
		Vecteur dir = v.getDirection().clonage();
		for(int i=0; i<2000; i++){
			if(!Tools.isRunnable(c.getTerrain(pos))){
				return true;
			}
			pos.add(dir.fact(0.01));
		}
		return false;
	}

}
