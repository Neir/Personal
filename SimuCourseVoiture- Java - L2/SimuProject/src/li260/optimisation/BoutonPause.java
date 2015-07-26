package li260.optimisation;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import li260.simulation.Simulation;

@SuppressWarnings("serial")
public class BoutonPause extends JButton implements MouseListener{

	private static ImageIcon iconePlay = new ImageIcon("icones/play.png");
	private static ImageIcon iconePause = new ImageIcon("icones/pause.png");
	public BoutonPause(String nom){
		super(nom, iconePlay);
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(100, 30));

	}

	public void restart(){
		Simulation.pause=true;
		this.setIcon(iconePlay);
		this.setText("Lecture");
	}
	
	public void mouseClicked(MouseEvent e) {
		if(Simulation.pause){
			Simulation.pause = false;
			this.setIcon(iconePause);
			this.setText("Pause");
		}
		else {
			Simulation.pause = true;
			this.setIcon(iconePlay);
			this.setText("Lecture");
		}
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
