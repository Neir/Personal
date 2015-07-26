package isketch.clientThread;

import isketch.interfaces.Observateur;
import isketch.objets.Commande;
import isketch.objets.User;
import isketch.tools.Tools;
import isketch.view.MaFenetre;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientThread extends Thread implements Observateur{
	private static final int PORT=2013;
	private Socket s = null;
	private BufferedReader canalLecture;
	private BufferedReader console;
	private PrintStream canalEcriture;
	
	private MaFenetre fen;
	
	private String comStr;
	private Commande commande;
	
	private Boolean connected = false;

	public ClientThread(){

		fen = new MaFenetre(this);
		
		//etablissementDeConnexion();
		
		fen.setSize(fen.getPreferredSize());
	}
	
	public void etablissementDeConnexion(){
	    try{
	    	
	        // 1. Créer la socket
//	    	System.out.println("Demande de connexion "+InetAddress.getLocalHost());
	    	System.out.println("Demande de connexion "+InetAddress.getByName("localhost"));
	    	//"127.0.0.1"
	        s = new Socket(InetAddress.getByName("localhost"),PORT);
	        System.out.println("Socket Créée");
	 
	        canalLecture = new BufferedReader (new InputStreamReader(s.getInputStream()));
	        canalEcriture = new PrintStream (s.getOutputStream());
	        console = new BufferedReader(new InputStreamReader(System.in));
	        connected = true;
	        start();
	 
	    }catch(IOException e){
	        System.out.println("Erreur de serilisation "+e);
	    }
	}
	
	public void lectureCanal(){
        try {
        	while ((comStr = canalLecture.readLine()) != null){
        	commande = Tools.interpretation(comStr); System.out.println(comStr);
        	recevoirCommande(commande);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void recevoirCommande(Commande com) {
		System.out.println("J'ai reçu la commande : "+com);
		
		switch(Tools.enumFromCommande(com)){
		case CONNECTED :
			fen.getChatPanel().newUserConnected(com.getAttIndex(0));
			break;
		case EXITED :
			fen.getChatPanel().userDisconnected(com.getAttIndex(0));
			break;
		case NEW_ROUND :
			// On suppose qu'on envoie la valeur 0 au dessinateur et 1 aux devineurs
			if(Integer.parseInt(com.getAttIndex(0))==0){
				fen.getChatPanel().afficherDansChat("Vous étes le déssinateur !\n"
						+ "Tentez de faire deviner le mot " +com.getAttIndex(1)
						+ " le plus rapidement possible.");
				fen.setRoleDessinateur(true);	
			} else {
				fen.getChatPanel().afficherDansChat("Début du round, bonne chance à tous !");
				fen.setRoleDessinateur(false);
			}
			break;
		case GUESSED :
			fen.getChatPanel().ecrireDansChat(com.getAttIndex(0), com.getAttIndex(1));
			break;
		case WORD_FOUND :
			fen.getChatPanel().afficherDansChat("Vous avez trouvé le mot, bravo");
//			fen.getChatPanel().gererTrouve();
			break;
		case WORD_FOUND_TIMEOUT :
			fen.getChatPanel().afficherDansChat("Un joueur a trouvé le mot, "
					+ "il reste "+com.getAttIndex(0)+" secondes avant la fin du round");
			break;
		case END_ROUND :
			fen.getChatPanel().afficherDansChat("Le round est remporté par "+com.getAttIndex(0)
					+" !\nLe mot à deviner était : "+com.getAttIndex(1));
//			fen.getChatPanel().gererFinRound();
			break;
		case SCORE_ROUND :
			HashMap<String,Integer> joueurEtScore = new HashMap<String,Integer>();
			for(int i = 0 ; i < com.getAttributs().size() ; i += 2){
				joueurEtScore.put(com.getAttIndex(i), Integer.parseInt(com.getAttIndex(i+1)));
			}
			
			ArrayList<User> users = new ArrayList<User>();
			for(int i = 0 ; i < com.getAttributs().size() ; i += 2){
				users.add(new User(com.getAttIndex(i), Integer.parseInt(com.getAttIndex(i+1))));
			}
			
//			fen.getChatPanel().actualiserScore(joueurEtScore);
			fen.getChatPanel().actualiserScore(users);
			break;
		case LINE :
			fen.getDrawPanel().addSegment(
					(int)Double.parseDouble(com.getAttIndex(0)), 
					(int)Double.parseDouble(com.getAttIndex(1)), // x1 et y1 : coordonnées du premier point
					(int)Double.parseDouble(com.getAttIndex(2)), 
					(int)Double.parseDouble(com.getAttIndex(3)), // x2 et y2 : coordonnées du second point
					Integer.parseInt(com.getAttIndex(4)), 
					Integer.parseInt(com.getAttIndex(5)), 
					Integer.parseInt(com.getAttIndex(6)), // r, g et b : La couleur (décomposée) de la ligne
					Integer.parseInt(com.getAttIndex(7))); // taille : L'epaisseur de la ligne
			break;
		default:
			System.out.println("Commande non reconnue !");
			break;
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public void deconnection() {
		try {
			//try {join();} catch (InterruptedException e) { e.printStackTrace(); }
			s.close();
			connected = false;
			//this.interrupt();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(ArrayList<Commande> comList) {
		for(Commande com : comList){
			canalEcriture.print(Tools.stringFromCommande(com));
			System.out.println(Tools.stringFromCommande(com));
		}
		
		canalEcriture.flush();
	}
	
	public void run(){
		while(true)
			lectureCanal();
	}
	
	public void setDessinateur(Boolean b){
		fen.setRoleDessinateur(b);System.out.println("Il est dessinateur");
	}
	
	public static void main(String []args){
		new ClientThread();
		ClientThread ca = new ClientThread();
		ca.setDessinateur(true);
		
	}
}
