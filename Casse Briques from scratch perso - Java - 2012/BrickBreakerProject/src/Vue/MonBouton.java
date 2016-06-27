package Vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Tools.LabelMBTools;


public class MonBouton extends JButton {
	private String txt;
	private LabelMonBouton labelMB;
	private final float border = (float) 1.4;

	public MonBouton(String txt){
		this.txt = txt;
		labelMB = LabelMBTools.labelFromText(txt);
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
		this.setFont(f);
		this.setForeground(Color.BLUE);
		
		this.setText(txt);
		
		makeBorderedFont(border, Color.GREEN);
		
		this.setFocusPainted(false);
		//	  this.setVisible(false);
		this.setEnabled(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(getTxt()+" : Oh merci merci merci ! ! !");
			}
		});

		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}

			public void mouseEntered(MouseEvent arg0) {
				setForeground(Color.GREEN);
				makeBorderedFont(border, Color.WHITE);
			}

			public void mouseExited(MouseEvent arg0) {
				setForeground(Color.BLUE);
				makeBorderedFont(border, Color.GREEN);
			}

			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
		labelMB = LabelMBTools.labelFromText(txt);
	}
	
	private void makeBorderedFont(float size, Color color) {
		String txt = this.getTxt();
		Font f = this.getFont();
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		int width = img.getGraphics().getFontMetrics(f).stringWidth(txt);
		int height = img.getGraphics().getFontMetrics(f).getHeight();
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)img.getGraphics();
		g2d.setFont(f);
		g2d.setColor(Color.BLACK);
		g2d.drawString(txt,size,height/2F+size);
		g2d.drawString(txt,-size,height/2F-size);
		
		g2d.drawString(txt,-size,height/2F+size);
		g2d.drawString(txt,size,height/2F-size);
		
		g2d.setColor(color);
		g2d.drawString(txt,0,height/2);
		g2d.dispose();
		this.setText(null);
		this.setIcon(new ImageIcon(img));
	}

	public LabelMonBouton getlabelMB() {
		return labelMB;
	}
}
