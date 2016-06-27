package Controler;

import Model.IHMSwing;
import Simulation.Simulation;

public class Controler extends AbstractControler {
	private Simulation sim;

	public Controler(IHMSwing ihm, Simulation sim) {
		super(ihm);
		this.sim = sim;
	}

	void control() {
		sim.setMouv(this.mouv);
		sim.setPos(this.pos);
		sim.setStart(this.start);
		sim.setPause(this.pause);
	}

}
