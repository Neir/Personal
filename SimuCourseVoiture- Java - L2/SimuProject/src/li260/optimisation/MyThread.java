package li260.optimisation;

import java.io.IOException;

import li260.exception.VoitureException;

public class MyThread extends Thread{
	
	Controleur c;
	
	public MyThread(Controleur c, String str){
		super(str);
		this.c = c;
	}
	
	public void run(){
			c.jouer();
	}
	
	public void lancement(String str){
		try {
			c.lancement(str);
		} catch (VoitureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
