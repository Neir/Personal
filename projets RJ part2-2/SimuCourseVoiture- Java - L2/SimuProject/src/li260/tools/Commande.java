package li260.tools;

public class Commande {
	private double acceleration;
	private double rotation;
	
	public Commande(double acceleration, double rotation) {
		super();
		this.acceleration = acceleration;
		this.rotation = rotation;
	}
	
	public double getAcc() {
		return acceleration;
	}

	public double getTurn() {
		return rotation;
	}
	
	public void controlCommand() {
		// TODO Auto-generated method stub
		if (acceleration > 1)
			acceleration = 1;
		else if (acceleration < -1)
			acceleration = -1;
		if (rotation < -1)
			rotation = -1;
		else if (rotation > 1)
			rotation = 1;
	}
	
	public void setAcc(double acceleration) {
		this.acceleration = acceleration;
	}

	public void setTurn(double rotation) {
		this.rotation = rotation;
	}

	@Override
	public String toString() {
		return "Commande [acceleration=" + acceleration + ", rotation="
				+ rotation + "]";
	}
	

}
