package li260.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

import li260.circuit.Circuit;
import li260.circuit.Terrain;

/*Route, Herbe, Eau, Obstacle, BandeRouge, BandeBlanche,
	StartPoint, EndLine*/
public class Tools {
	
	public static boolean isRunnable(Terrain t){
	return !(t==Terrain.Eau || t==Terrain.Herbe);
	}
	
	public static Terrain terrainFromChar(char c) {
		/*’g’ →herbe, ’b’ → eau, ’.’ → gaudron, ’w/r’ → bande de goudron rouge et blanche, ’*’ → point de depart,’!’ → ligne d’arrivee.*/
		switch(c){
		case '.' : return Terrain.Route;
		case 'g' : return Terrain.Herbe;
		case 'b' : return Terrain.Eau;
		case 'w' : return Terrain.BandeBlanche;
		case 'r' : return Terrain.BandeRouge;
		case '*' : return Terrain.StartPoint;
		case '!' : return Terrain.EndLine;
		case 'm' : return Terrain.Boue;
		default : return null;
		}		
	}
	
	public static int colorFromTerrain(Terrain t){
		switch(t){
		case Route : return Color.gray.getRGB();
		case Herbe : return Color.green.getRGB();
		case Eau : return Color.blue.getRGB();
		case BandeBlanche : return Color.white.getRGB();
		case BandeRouge : return Color.red.getRGB();
		case StartPoint : return Color.black.getRGB();
		case EndLine : return Color.pink.getRGB();
		case Boue : return Color.yellow.getRGB();
		default : return -1;
		}
	}
	
	public static BufferedImage imageFromCircuit(Circuit circuit) {
		int width= circuit.getWidth();
		int height= circuit.getHeight();
//		Terrain[][] mat = null;
		BufferedImage bi = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
//				mat[i][j] = circuit.getTerrain(i, j);
				bi.setRGB(i, j,Tools.colorFromTerrain(circuit.getTerrain(i, j)));
			}
		}
		
		return bi;
//		ImageIO.write(bi,"PNG",new File(filename));
		
	}
		
}

