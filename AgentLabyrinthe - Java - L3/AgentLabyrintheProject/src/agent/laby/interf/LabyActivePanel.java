package agent.laby.interf;

import java.awt.event.ActionEvent;

import agent.laby.Labyrinthe;
import pobj.obs.ISimpleObserver;

public class LabyActivePanel extends LabyPanel implements ISimpleObserver {

	private static final long serialVersionUID = 1L;

	public LabyActivePanel(Labyrinthe laby) {
		super(laby);
		// TODO Auto-generated constructor stub
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		try{ Thread.sleep(200); }	//en millisecondes
		catch(InterruptedException e){	e.printStackTrace(); }
		updateGraphics();
	}
	
}
