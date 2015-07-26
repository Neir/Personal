package isketch.view;

import isketch.interfaces.Observateur;
import isketch.objets.Commande;
import isketch.objets.Segment;
import isketch.objets.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class ChatPanel extends JPanel {
	private JTextArea zoneChat = new JTextArea();
	
	private JPanel ecritPanel;
	private JTextArea zoneEcriture = new JTextArea(3,10);
	private JButton envoyerButton;
	
	private JScrollPane chatScroll, ecritScroll, userScroll;
	
	private JTextArea userConnected = new JTextArea();
	private ArrayList<User> users;
	private String userName = "Pseudo";
	
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
		
	public ChatPanel(String username, ArrayList<User> users) {
		setLayout(new BorderLayout());
		this.users = users;
		users.add(new User(userName, 0)); //System.out.println(userName);
		this.userName = username;

		chatScroll = new JScrollPane(zoneChat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScroll.setBounds(0,0,300,200);
		zoneChat.setLineWrap(true); 
		zoneChat.setEditable(false);
		//zoneChat.setPreferredSize(new Dimension(250, zoneChat.getHeight()));
		add(chatScroll,BorderLayout.CENTER);
		
		ecritPanel = new JPanel();
		ecritPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
		ecritPanel.setLayout(new BorderLayout());
		zoneEcriture.setLineWrap(true);
		ecritScroll = new JScrollPane(zoneEcriture, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ecritPanel.add(ecritScroll, BorderLayout.CENTER);

		envoyerButton = new JButton("Envoyer");
		envoyerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!zoneEcriture.getText().isEmpty()){
					zoneChat.append(userName+" : "+zoneEcriture.getText()+"\n");
					envoyerTexteAuServeur(zoneEcriture.getText());
					zoneEcriture.setText("");
				}
			}
		});
		ecritPanel.add(envoyerButton, BorderLayout.EAST);
		ecritPanel.setFocusable(true);
		ecritPanel.requestFocus();
		zoneEcriture.addKeyListener((new KeyListener(){
			String newText ="";
			Boolean editable = true;
			public void keyPressed(KeyEvent e) {
				if(editable)
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						
						zoneEcriture.setEditable(editable=false);
						if(!zoneEcriture.getText().isEmpty())
							newText = userName+" : "+zoneEcriture.getText()+"\n";
						envoyerTexteAuServeur(zoneEcriture.getText());
						zoneEcriture.setText("");
						
						zoneChat.append(newText);
						newText="";
					}
				if(zoneEcriture.getText().length()>150){
					try {
						zoneEcriture.setText(zoneEcriture.getText(0,150));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
//					zoneChat.setText(newText);
//					zoneEcriture.setText("");
					zoneEcriture.setEditable(editable=true);
				}
			}
			public void keyTyped(KeyEvent e) {}
		}));
		
		add(ecritPanel,BorderLayout.SOUTH);
		
		userScroll = new JScrollPane(userConnected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		userScroll.setBounds(0,0,300,200);
		userConnected.setEditable(false);
		userConnected.setPreferredSize(new Dimension(150,(int) zoneChat.getSize().getHeight()));
		
		// Classe et affiche la liste des joueurs
		Collections.sort(users);
		String userContains = "";
		for(User user : users){
			userConnected.setText(userContains=user.getNbPoints()+" : "+user.getName()+"\n"+userContains);
		}
		
		add(userScroll, BorderLayout.EAST);
	}
	
	public void setUsername(String name) {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(userName)){
				users.get(i).setName(name);
			}
		}
		this.userName = name;
		actualiserScore(users);
	}
	
	protected void envoyerTexteAuServeur(String text) {
		Commande com;
		
		if(!commandes.isEmpty()) commandes = new ArrayList<Commande>();
		
		com = new Commande("GUESS");
		com.addAttribut(""+text);
		commandes.add(com);
		updateObservateur();
	}

	public void newUserConnected(String name) {
		users.add(new User(name, 0));
		actualiserScore(users);
	}

	public void userDisconnected(String name) {
		for(User u : users){
			if(u.getName().equals(name)) { users.remove(u); return; }
		}
		actualiserScore(users);
	}

	public void ecrireDansChat(String texte, String name) {
		zoneChat.append(name+" : "+texte+"\n");
	}
	
	public void afficherDansChat(String texte) {
		zoneChat.append("***********************************************"
				+ "\n"+texte+"\n"
				+ "***********************************************\n");
	}

	public void actualiserScore(HashMap<String, Integer> joueurEtScore) {
		
		for(User user : users){
			user.setNbPoints(joueurEtScore.get(user.getName()));
		}
		Collections.sort(users);
		
		String userContains = "";
		for(User user : users){
			userConnected.setText(userContains=user.getNbPoints()+" : "+user.getName()+"\n"+userContains);
		}
	}

	public void actualiserScore(ArrayList<User> users) {
		this.users = users;
		
		Collections.sort(users);

		String userContains = "";
		for(User user : users){
			userConnected.setText(userContains=user.getNbPoints()+" : "+user.getName()+"\n"+userContains);
		}
	}

	public void addObservateur(Observateur obs) {
		listObservateur.add(obs);
	}

	public void updateObservateur() {
		for(Observateur obs : listObservateur )
			obs.update(commandes);
	}
	
	public void setEnablePerso(Boolean b){
		zoneEcriture.setEnabled(b);
		envoyerButton.setEnabled(b);
	}

	public static void main(String[] args) {
		JFrame fen = new JFrame();
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setLayout(new BorderLayout());
		fen.setPreferredSize(new Dimension(500,300));

		fen.setVisible(true);
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Rival", 2)); users.add(new User("Connard", 13)); users.add(new User("Gagne tout -_-\"", 999));
		fen.add(new ChatPanel("Neir", users));
		fen.pack();
	}


}

