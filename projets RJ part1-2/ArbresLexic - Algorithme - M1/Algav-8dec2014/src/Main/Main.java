package Main;

import javax.swing.SwingUtilities;

import AffichageGeneral.MainFrame;

public class Main {

	public static void main(String arg[]){
	//	AffichageGeneral.MainFrame f = new MainFrame();
	//
	
	SwingUtilities.invokeLater(
		    new Runnable() {
		      public void run() {
		        new MainFrame();
		      }
		    });
		  }
		}
