package AffichageGeneral;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AffichageBriandais.panelBriandais;
import AffichageHybride.panelHybride;
import BriandaisTree.AjoutTexteLong;
import BriandaisTree.TreeBriandais;
import HybridTrie.AjoutTexteLongH;
import HybridTrie.TrieHybride;


public class MainFrame extends JFrame implements ActionListener, ComponentListener{
	int hauteur, largeur ;
	private GridBagConstraints gbc ;
	private JPanel Options, Rendu ;
	private JTabbedPane Arbres ;
	private ArrayList<TreeBriandais> Briandais ;
	private ArrayList<TrieHybride> Hybride ;
	private PanelChoixArbre Choix ;
	private PanelAjout Ajout ;
	private PanelOption Stat ;
	private PanelText Console ;
	Dimension n ;

	private JButton Launch, Importer, image; 
	int Mode ;
	String text ;

	public MainFrame(){

		MiseEnPlace();
		Briandais = new ArrayList<TreeBriandais>();
		Hybride = new ArrayList<TrieHybride>();
		Listener();


	}

	private void Listener() {
		Launch = Ajout.getGo();
		Launch.addActionListener(this);
		Importer = Choix.GetImort();
		Importer.addActionListener(this);
		addComponentListener(this);
	}

	private void MiseEnPlace(){
		setTitle("Algav 2014");
		setLayout(new BorderLayout());


		GetEnvironementGraphique();
		TreeBriandais th = new TreeBriandais();
		TrieHybride t = new TrieHybride();
		setVisible(true);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int reponse = JOptionPane.showConfirmDialog(new JFrame(),
						"Voulez-vous quitter l'application ?",
						"Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (reponse==JOptionPane.YES_OPTION){
					quit();								
				}
			}
		});
		
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged (WindowEvent e){
						Arbres.setPreferredSize(new Dimension(Frame.MAXIMIZED_HORIZ-Options.getWidth(),Frame.MAXIMIZED_VERT));
				}
			});
		
		JScrollPane scroll = new JScrollPane(AjoutOption());
		add(scroll, BorderLayout.WEST);
		add(PanelArbre(), BorderLayout.CENTER);
		th.AjouterMot("test");
		
		Ajout.getField().addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					Launch.doClick();}}
			public void keyReleased(KeyEvent e) {}});
		
		invalidate();
		validate();
		repaint();
		pack();
		n=Arbres.getSize();
	}


	public JPanel AjoutOption(){
		Options = new JPanel();
		Options.setLayout(new BoxLayout(Options,BoxLayout.Y_AXIS)) ;
		Options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLACK), "Options" , 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
		Stat = new PanelOption(500, 200);
		Console = new PanelText();
		Choix = new PanelChoixArbre();
		Ajout=new PanelAjout();
		Options.add(Choix);
		Options.add(Stat);
		Options.add(Ajout);
		Options.add(Console);
	
		return Options ;
	}

	public JPanel PanelArbre(){

		Arbres = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

		ChangeListener changeListener = new ChangeListener() {

			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();

				if(sourceTabbedPane.getTitleAt(index).equals("Acceuil")){
					Stat.SetStat(0, 0, 0, 0,0,0,0);
					Choix.SetValueBrian(0);
					Choix.SetValueHyb(0);
				}
				else{
					int numBri =0 ;
					int numHy =0 ;

					for(int i=1 ; i<=Arbres.getSelectedIndex();i++){
						if(Arbres.getTitleAt(i).charAt(0) == 'B'){
							numBri++;
						}
						else{
							numHy++;
						}
					}
					if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'B'){
						TreeBriandais th = Briandais.get(numBri-1);							
						MiseAJourStat(th);						
					}

					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'H'){
						TrieHybride th = Hybride.get(numHy-1);							
						MiseAJourStatH(th);
					}
				}
			}
		};
		Arbres.addChangeListener(changeListener);

		Rendu = new JPanel();
		image = new JButton("");
		image.setPreferredSize(new Dimension(850,850));

		image.setMargin(new Insets(0,0,0,0));
		image.setBorderPainted(false);
		image.setIcon(new ImageIcon(MainFrame.class.getResource("Algav.jpg" )));
		Arbres.add("Acceuil",image);
		Rendu.add(Arbres);
		Arbres.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK , Color.BLUE), "Affichage Arbre" , 2 ,0, new Font("Dialog", 3, 13), Color.BLACK));
		
		return Rendu;
	}

	private void GetEnvironementGraphique(){
		String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();
		if(!lookAndFeelName.equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")){
		try {
			UIManager.setLookAndFeel(lookAndFeelName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		}
	}

	public void quit(){
		System.exit(0);
	}

	public PanelText getConsole(){
		return Console;
	}

	public void MiseAJourStat(TreeBriandais th){
		Stat.SetStat(th.NombreMot(), th.comptageNil(), th.hauteur(), th.profondeurMoyenne(),th.getTemps(),th.getTempslast(), th.getTempsSuppr());
	}

	public void MiseAJourStatH(TrieHybride th){
		Stat.SetStat(th.comptageMots(), th.comptageNil(), th.hauteur(), th.profondeurMoyenne(),th.getTemps(),th.getTempslast(), 0);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==Launch){
			int numBri =0 ;
			int numHy =0 ;

			for(int i=1 ; i<=Arbres.getSelectedIndex();i++){
				if(Arbres.getTitleAt(i).charAt(0) == 'B'){
					numBri++;
				}
				else{
					numHy++;
				}
			}

			if(!Ajout.getMot().equals("")){   // mode 1,2,3


				if( (Briandais.isEmpty() && Hybride.isEmpty() && Ajout.getMode()==1)  || (Arbres.getSelectedIndex()==0 && Ajout.getMode()==1)){


					Console.setText("\n***** Ajout de mot sur un arbre nouveau *****");
					switch (Choix.GetMode()){
					case 1:{ //DLB
						TreeBriandais th = new TreeBriandais();
						Briandais.add(th);
						th.setConsole(Console);
						String mot = Ajout.getMot()+" ";
						th.ajoutPhrase(mot,false);
						MiseAJourStat(th);
						panelBriandais test = new panelBriandais(th,th.NombreMot() );
						Arbres.add("Briandais n°"+Briandais.size(),test.getArbre());
						Ajout.AjouterArbre("Briandais n°"+Briandais.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);
						break;
					}
					case 2:{ //Hyb
						TrieHybride th = new TrieHybride();
						Hybride.add(th);
						th.setConsole(Console);
						th.setChoix(Choix);
						String mot = Ajout.getMot()+" ";
						th.ajoutPhrase(mot);
						MiseAJourStatH(th);
						panelHybride test = new panelHybride(th,th.comptageMots() );
						Arbres.add("Hybrides n°"+Hybride.size(),test.getArbre());
						Ajout.AjouterArbre("Hybride n°"+Hybride.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);
						Console.setText("Mot(s) ajoutés avec succès ");
						break;
					}
					case 3:{ //Les Deux
						TreeBriandais tb = new TreeBriandais();
						Briandais.add(tb);
						tb.setConsole(Console);
						String mot = Ajout.getMot()+" ";
						tb.ajoutPhrase(mot,false);
						MiseAJourStat(tb);
						panelBriandais test = new panelBriandais(tb,tb.NombreMot() );
						Arbres.add("Briandais n°"+Briandais.size(),test.getArbre());
						Ajout.AjouterArbre("Briandais n°"+Briandais.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);

						TrieHybride th = new TrieHybride();
						Hybride.add(th);
						th.setConsole(Console);
						th.setChoix(Choix);
						th.ajoutPhrase(mot);
						MiseAJourStatH(th);
						panelHybride testh = new panelHybride(th,th.comptageMots() );
						Arbres.add("Hybrides n°"+Hybride.size(),testh.getArbre());
						Ajout.AjouterArbre("Hybride n°"+Hybride.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);
						break;
					}
					default:{
						break;
					}
					}





				}
				else{

					if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'B'){ // DE LA BRIANDAIS

						if(Ajout.getMode()==1){ // mode ajout mot
							Console.setText("\n***** Ajout de mot *****");
							TreeBriandais th = Briandais.get(numBri-1);
							String mot = Ajout.getMot()+" ";
							th.ajoutPhrase(mot,false);
							MiseAJourStat(th);
							panelBriandais test = new panelBriandais(th,th.NombreMot() );
							Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
						}
						else if (Ajout.getMode()==2){ //mode rechercher mot
							TreeBriandais th = Briandais.get(numBri-1);
							Console.setText("\n***** Recherche de mot *****");
							int a = th.findString(Ajout.getMot() , false);
							if(a == -1){
								Console.setText(Ajout.getMot() +" n'est pas présent ");
							}
						}
						else { //mode supprimer mot
							TreeBriandais th = Briandais.get(numBri-1);
							Console.setText("\n***** Suppression de mot *****");
							if(th.RechercheMot(Ajout.getMot(), 0, th.racine, false)==1){
								th.supprimer(Ajout.getMot());
								MiseAJourStat(th);
								panelBriandais test = new panelBriandais(th,th.NombreMot() );
								Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
								Console.setText(Ajout.getMot()+" a été supprimé");
							} else{
								Console.setText("Impossible de supprimer "+Ajout.getMot()+" car non présent dans l'arbre");
							}
						}
					}

					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'H'){ // HYBRIDES


						if(Ajout.getMode()==1){ // mode ajout mot
							Console.setText("\n***** Ajout de mot *****");
							TrieHybride th = Hybride.get(numHy-1);
							String mot = Ajout.getMot()+" ";
							th.ajoutPhrase(mot);
							th.setChoix(Choix);
							MiseAJourStatH(th);
							panelHybride test = new panelHybride(th,th.comptageMots() );
							Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
							Console.setText("Mot(s) ajoutés avec succès ");
						}
						else if (Ajout.getMode()==2){ //mode rechercher mot
							TrieHybride th = Hybride.get(numHy-1);
							Console.setText("\n***** Recherche de mot *****");
							if(th.recherche(Ajout.getMot())){
								if(th.prefixe(Ajout.getMot())==0){
									Console.setText(Ajout.getMot() +" est présent mais pas préfixe");
								}
								else{
									Console.setText(Ajout.getMot() +" est présent et est préfixe de "+th.prefixe(Ajout.getMot())+" mot(s)");
								}
							}
							else if(th.prefixe(Ajout.getMot())==0){
									Console.setText(Ajout.getMot() +" n'est pas présent et n'est pas préfixe ");
								}
								else{
									Console.setText(Ajout.getMot() +" n'est pas présent mais est préfixe de "+th.prefixe(Ajout.getMot())+" mot(s)");
								}

						}
						else { //mode supprimer mot
							TrieHybride th = Hybride.get(numHy-1);
							Console.setText("\n***** Suppression de mot *****");
							if(th.recherche(Ajout.getMot())){
								th.suppression(Ajout.getMot());
								MiseAJourStatH(th);
								panelHybride test = new panelHybride(th,th.comptageMots() );
								Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
								Console.setText(Ajout.getMot()+" a été supprimé");
							}
							else{
								Console.setText("Impossible de supprimer "+Ajout.getMot()+" car non présent dans l'arbre");
							}
						}
					}

					else{

						Console.setText("Aucun arbre selectionné");

					}



				}





				Ajout.ResetMot();
				Rendu.updateUI();
				pack();
//				setResizable(false);

			}else
				if(Ajout.getMode()==4){ //Lister les mots

					if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'B'){ //liste Mot Briandais
						TreeBriandais th = Briandais.get(numBri-1);
						Console.setText("\n***** Liste des mots par ordre alphabetique *****");
						for(String mot : th.listeMots()  ){
							Console.setText(mot);
						}
					}
					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'H'){ //liste mot Hybrid
						TrieHybride th = Hybride.get(numHy-1);
						Console.setText("\n***** Liste des mots par ordre alphabetique *****");
						for(String mot : th.listeMots()  ){
							Console.setText(mot);
						}
					}
					else{   //liste Mot Hybrid
						Console.setText("Aucun arbre selectionné");
					}

				}

				else if(Ajout.getMode()==6){ // Convertir un arbre

					if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'B'){ //Convertit Briandais to hybrid
						TrieHybride th = Briandais.get(numBri-1).conversion();
						Console.setText("\n***** Convertir un arbre de la Briandais *****");
						MiseAJourStatH(th);
						Hybride.add(th);
						panelHybride test = new panelHybride(th,th.comptageMots() );
						Arbres.add("Hybride n°"+Hybride.size(),test.getArbre());
						Ajout.AjouterArbre("Hybride Converti n°"+Hybride.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);
					}
					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'H'){ //Convertir Hybrid to briandais
						TreeBriandais th = Hybride.get(numHy-1).conversion();
						Console.setText("\n***** Convertir un arbre de la Briandais *****");
						MiseAJourStat(th);
						Briandais.add(th);
						panelBriandais test = new panelBriandais(th,th.NombreMot() );
						Arbres.add("Briandais n°"+Briandais.size(),test.getArbre());
						Ajout.AjouterArbre("Briandais n°"+Briandais.size());
						Arbres.setSelectedIndex(Arbres.getTabCount()-1);
					}
					else{   //Convertir Hybrid to briandais
						Console.setText("Aucun arbre selectionné");
					}

				}

				else if(Ajout.getMode()==5){ // fusion arbre de la briandais

					if(Ajout.getID()==0){
						Console.setText("\n***** invalide, selectionner un arbre pour la fusion *****");
					}
					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0)!= 'B'){
						Console.setText("\n***** invalide, pas de fusion avec un Hybride *****");
					}
					else{
						Console.setText("\n***** Fusion de deux arbres *****");
						TreeBriandais th = Briandais.get(numBri-1); //Arbre affiché
						TreeBriandais th2 = Briandais.get(Ajout.getID()-1);
						
						th.fusion(th2);
												
						MiseAJourStat(th);
						panelBriandais test = new panelBriandais(th,th.NombreMot() );
						Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
						String a = Arbres.getTitleAt(Arbres.getSelectedIndex());
						Arbres.setTitleAt(Arbres.getSelectedIndex(), a+" fusionné avec n°"+Ajout.getID());
						Console.setText("Fusion de l'arbre De La Briandais n°"+numBri+" avec le n°"+Ajout.getID()+" OK");
					}
				}
			
				else if(Ajout.getMode()==7){ // equilibrage arbre Hybride

					
						
					if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0)== 'B'){
						Console.setText("\n***** invalide, pas d'equilibrage sur un arbre De La Briandais *****");
					}
					else if(Arbres.getTitleAt(Arbres.getSelectedIndex()).charAt(0) == 'H'){
						Console.setText("\n***** Fusion de deux arbres *****");
						
						TrieHybride th = Hybride.get(numHy-1);
						th.equilibrer();
						MiseAJourStatH(th);
						panelHybride test = new panelHybride(th,th.comptageMots() );
						Arbres.setComponentAt(Arbres.getSelectedIndex(), test.getArbre());
						Console.setText("Arbre équilibré avec succès ");
					}
					else{
						Console.setText("\n***** invalide, selectionner un arbre pour l'équilibrage *****");
					}
				}

				else
					Console.setText("commande ou mot invalide");

		}

		if(e.getSource()==Importer){

			JFileChooser choixText = new JFileChooser();

			int openAction = choixText.showOpenDialog(Choix);

			if (openAction == JFileChooser.APPROVE_OPTION) {
				ChargeText(choixText.getSelectedFile());
				repaint();
			}		
			Rendu.updateUI();
			pack();
//			setResizable(false);
		}
	}//fin listener

	public void AjoutText(String mot){
		mot = mot+" ";

		if(Choix.GetMode()==1){ // creer arbre Briandais
			TreeBriandais th = new TreeBriandais();
			Briandais.add(th);
			th.setConsole(Console);
			th.setChoix(Choix);
			AjoutTexteLong test1 = new AjoutTexteLong(mot, th, Arbres,Briandais.size(),Ajout);
			test1.start();
		}

		else if(Choix.GetMode()==2){ //creer arbre Hybrid
			TrieHybride th = new TrieHybride();
			Hybride.add(th);
			th.setConsole(Console);
			th.setChoix(Choix);
			AjoutTexteLongH test1 = new AjoutTexteLongH(mot, th, Arbres, Hybride.size(),Ajout);
			test1.start();
		}
		else{ // creer 2 arbres
			TreeBriandais th1 = new TreeBriandais();
			Briandais.add(th1);
			th1.setConsole(Console);
			th1.setChoix(Choix);
			AjoutTexteLong test2 = new AjoutTexteLong(mot, th1, Arbres,Briandais.size(),Ajout);
			test2.start();

			TrieHybride th = new TrieHybride();
			Hybride.add(th);
			th.setConsole(Console);
			th.setChoix(Choix);
			AjoutTexteLongH test1 = new AjoutTexteLongH(mot, th, Arbres, Hybride.size(),Ajout);
			test1.start();


		}

	}

	public void ChargeText(File TextFile) {
		String Texte="";
		try{
			InputStream ips=new FileInputStream(TextFile); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;

			while ((ligne=br.readLine())!=null){
				Texte+=ligne+" ";
			}
			br.close();
			AjoutText(Texte);
			System.out.println("texte chargé");
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		Arbres.setPreferredSize(new Dimension(Rendu.getWidth(),Rendu.getHeight()));
		n = new Dimension(Rendu.getWidth(),Rendu.getHeight());
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
