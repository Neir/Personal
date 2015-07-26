package AffichageBriandais;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import AffichageBriandais.NoeudBinSwing;
import BriandaisTree.TreeBriandais;

public class panelBriandais {
	private BufferedImage imageBuff;
	private Largeur test ;
	private JScrollPane pane ;

	public panelBriandais(TreeBriandais th, int nbMot){
		if(nbMot<10) nbMot=10;
				
		imageBuff = new BufferedImage(nbMot*41, 1000, BufferedImage.TYPE_INT_ARGB);
		test = new Largeur() ;
		Graphics g = imageBuff.getGraphics();
		NoeudBinSwing nds = new NoeudBinSwing(th.racine);
		nds.print(30 , 30, g,  test );
		
		
		final Image image =  imageBuff ;
		
		JPanel Arbre = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, this);
			}
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(image.getWidth(this),
						image.getHeight(this));
			}
		};
		pane = new JScrollPane(Arbre); 
		pane.setPreferredSize(new Dimension(850,800));
		//JOptionPane.showMessageDialog(null, pane);

	}




public JScrollPane getArbre() {
	return pane ;
}

public ImageIcon retour(){
	return new ImageIcon(imageBuff);
}

}

