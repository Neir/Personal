package li260.radar;

public interface Radar {	
	public double[] scores(); // score de chaque branche	
	public double[] distancesInPixels(); // pour l'observer	
	public int getBestIndex(); // meilleur indice	
	public double[] getThetas(); // angles de chaque faisceau	
	public double getToutDroit();	
	public double[] getScores();
	public boolean isVoitEndLine();
}
