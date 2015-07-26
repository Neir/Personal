package AffichageHybride;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import AffichageBriandais.NoeudBinSwing;
import BriandaisTree.TreeBriandais;
import HybridTrie.TrieHybride;

public class panelHybride extends JPanel {
	private static final long serialVersionUID = 1L;
	private TrieHybride th;
	private BufferedImage imageBuff;
	private JScrollPane pane ;

	public panelHybride(TrieHybride th, int nbMot){
		this.th = th;
		if(nbMot<10) nbMot=10;
		//System.out.println(nbMot);
		NoeudSwing nds = new NoeudSwing(th.getRacine());
		/*
		int largeurImage = NoeudSwing.Xmax-NoeudSwing.Xmin;
		System.out.println("largeur image : "+largeurImage);
		imageBuff = new BufferedImage(largeurImage,1000 , BufferedImage.TYPE_INT_ARGB);
		//*/
		//imageBuff = new BufferedImage(nbMot*60,1000 , BufferedImage.TYPE_INT_ARGB);
		
		imageBuff = new BufferedImage((th.getRacine().getLargeurTotal()+1)*40, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics g = imageBuff.getGraphics();
		nds.print(imageBuff.getWidth()/2, 30, g, 1);final Image image =  imageBuff ;

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
