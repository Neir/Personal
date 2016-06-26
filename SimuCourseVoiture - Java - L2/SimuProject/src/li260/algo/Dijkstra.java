package li260.algo;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import li260.circuit.Circuit;
import li260.circuit.Terrain;
import li260.dijkstra.ComparatorDijk;
import li260.geometrie.Vecteur;
import li260.ihm.observer.UpdateEventListener;
import li260.ihm.observer.UpdateEventSender;
import li260.tools.Tools;

public class Dijkstra implements UpdateEventSender{
	private Circuit circuit;
	private double[][] dist;
	private Vecteur current;
	private PriorityBlockingQueue<Vecteur> q;
	private ArrayList<UpdateEventListener> list;
	private static int cpt=0;

	public Dijkstra(Circuit c){

		circuit = c;
		list = new ArrayList<UpdateEventListener>();
		dist = new double[circuit.getHeight()][circuit.getWidth()];
		q = new PriorityBlockingQueue<Vecteur> (1000, new ComparatorDijk(dist));

		for(int i=0; i<circuit.getHeight();i++){
			for(int j=0; j<circuit.getWidth();j++){
				dist[i][j] =  Double.POSITIVE_INFINITY;
				current = new Vecteur(i,j);
				if((c.getTerrain(current)==Terrain.EndLine)){
					dist[i][j]=0;
					q.add(current);
				}
			}
		}
	}

	private double Poids(Vecteur s1, Vecteur s2){

		if(Math.abs(s2.getX()-s1.getX()) == Math.abs(s2.getY()-s1.getY())){
			return 14.0;
		}
		return 10.0;
	}
	//
	private void Maj(Vecteur s1){
		for(int i=-1; i<=1;i++){
			for(int j=-1; j<=1;j++){
				Vecteur s2 = new Vecteur(j+s1.getX(),i+s1.getY());
				if((i==0&&j==0) || !Tools.isRunnable(circuit.getTerrain(s2)))
					continue;
				Vecteur v = new Vecteur(j,i);
				if(!(((v.prodScal(circuit.getDirectionArrivee()))>=0)&& ((dist[(int)s1.getX()][(int)s1.getY()] == 0)))){ 
					//affichage ds les 2 sens à partir de la ligne d'arrivée;
					if(dist[(int)s2.getX()][(int)s2.getY()]>(dist[(int)s1.getX()][(int)s1.getY()])+Poids(s1, s2)){				
						q.remove(s2);
						dist[(int)s2.getX()][(int)s2.getY()]=(dist[(int)s1.getX()][(int)s1.getY()])+Poids(s1, s2);
						if(!s2.equals(q.peek()))
							q.add(s2);

					}
				}
			}
		}

	}

	public Vecteur getCurrent(){
		return current;
	}

	public double getDist(int x, int y){
		return dist[x][y];
	}

	public void compute(){

		while(q.size() > 0){
			//System.out.println("Dijkstra.compute");
			current = q.poll();
			Maj(current);
			update();
		}
		//System.out.println("cpt="+cpt);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for(UpdateEventListener listener: list)	
			listener.manageUpdate();	
	}


	@Override
	public void add(UpdateEventListener l) {
		// TODO Auto-generated method stub
		list.add(l);	
	}
}

