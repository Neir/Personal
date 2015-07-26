package BriandaisTree;

import java.util.ArrayList;

import Tools.Parser;
import AffichageGeneral.PanelChoixArbre;
import AffichageGeneral.PanelText;
import BriandaisTree.Noeud;
import HybridTrie.TrieHybride;

public class TreeBriandais {

	public Noeud racine;
	private int Nbmot =0 ;
	protected char FinMot = '*';
	private PanelText console ;
	private PanelChoixArbre Progress ;
	private Noeud Pere ;
	private long temps=0, tempslast, tempsSuppr ;



	public void setTemps(long t) {
		temps = t ;
	}
	public void setTempsLast(long t){
		tempslast = t;
	}
	public long getTemps() {
		return temps;
	}
	public long getTempslast() {
		return tempslast;
	}
	public long getTempsSuppr(){
		return tempsSuppr;
	}
	public void setTempsSuppr(long tempsSuppr){
		this.tempsSuppr=tempsSuppr;
	}
	public void setChoix(PanelChoixArbre Progress ){
		this.Progress=Progress;
	}
	public void setConsole(PanelText console){
		this.console=console;
	}
	public void ajoutPhrase(String phrase,boolean lon){
		if(lon)Progress.SetValueBrian(1);
		ArrayList<String> mots = Parser.parseBufferToWords(phrase);
		console.setText(mots.size()+" : mots a ajouter");
		int NBMots = mots.size();

		int i=0;
		for(String mot : Parser.parseBufferToWords(phrase)){
			if(findString(mot,true)!=1){
				this.AjouterMot(mot);
				if(!lon){
					console.setText("\""+mot+"\" ajouté.");
				}
				else{
					i++;
					Progress.SetValueBrian((i*100)/NBMots);
				}
			}

			else{
				if(!lon)console.setText("\""+mot+"\" non ajouté car deja present.");
				else{
					i++;
					Progress.SetValueBrian((i*100)/NBMots);

				}
			}
		}
	}

	public void AjouterMot(String Mot){
		long start = System.nanoTime();
		Pere = racine ;
		Noeud NoeudCourant = racine ;
		char letters[] = Mot.toCharArray();
		for (char letter : letters) {

			if (racine == null) {
				racine = new Noeud( letter, null, null );
				NoeudCourant = racine;
			}
			else if(NoeudCourant.getFils()==null ){
				Pere = NoeudCourant ;
				NoeudCourant = AjouterFils(letter, NoeudCourant) ;
			}
			else if(NoeudCourant.value == letter){
				Pere = NoeudCourant ;
				NoeudCourant = NoeudCourant.getFils() ;

			}
			else{
				NoeudCourant = AjouterFrere(letter , NoeudCourant) ;
			}

		}
		if(NoeudCourant.getFils() == null){
			Nbmot++;
			NoeudCourant=AjouterFils(FinMot, NoeudCourant) ;
		}
		else{
			Nbmot++;
			NoeudCourant=AjouterFrere(FinMot, NoeudCourant) ;
		}
		long duree = System.nanoTime() - start;
		setTemps(temps+duree) ;
		setTempsLast(duree) ;
	}

	public Noeud AjouterFrere(char a, Noeud NoeudCourant){

		Noeud frere = new Noeud(a, null,null);


		//Si racine en jeu
		if(NoeudCourant == racine && a < racine.value){
			Noeud NewRacine = new Noeud(a, racine ,null);
			racine = NewRacine ;
			return racine ;

		}
		else if(NoeudCourant.getFrere() == null){ 
			if(NoeudCourant.value>a){
				Noeud temps = Pere.getFils() ;
				frere.setFrere(temps) ;
				Pere.setFils(frere) ;

			}
			else{

				NoeudCourant.setFrere(frere);
			}

			return frere ;

		}
		else{

			// premiere place ? Noeud winner ?
			if(NoeudCourant.value>a){
				Noeud temps = Pere.getFils() ;
				frere.setFrere(temps)   ;
				Pere.setFils(frere) ;
			}

			else {
				Noeud NoeudParent = NoeudCourant ;
				Noeud NoeudEnCours = NoeudCourant.getFrere() ;
				while(NoeudEnCours != null){

					if(a > NoeudEnCours.value ){
						NoeudParent = NoeudEnCours ;
						NoeudEnCours =NoeudEnCours.getFrere() ;
					}

					else if(a == NoeudEnCours.value){
						if(NoeudEnCours.getFils()==null){
							return NoeudCourant.getFrere();

						}
						else{ 
							Pere = NoeudEnCours;
							return NoeudEnCours.getFils();
						}
					}

					else{ //(a<NoeudEnCours.value)
						NoeudParent.setFrere(frere)  ;
						frere.setFrere(NoeudEnCours)  ;
						return frere ;
					}
				}
				NoeudParent.setFrere(frere)  ;

			}
		}
		return frere;
	}

	public Noeud AjouterFils(char a, Noeud NoeudCourant){
		Noeud fils = new Noeud(a, null,null);
		if(NoeudCourant.value == FinMot){
			NoeudCourant = AjouterFrere( a, NoeudCourant) ;
			return NoeudCourant ;
		}
		NoeudCourant.setFils(fils);
		return fils ;
	}


	public int findString(String word, boolean Ajout) {
		if(racine==null) return -1 ;
		else return( RechercheMot(word,0,racine,Ajout) );
	}



	// RechercheMot retourne 1 si existe, 0 si prefix, -1 sinon
	public int RechercheMot(String word, int PositionLettre, Noeud NoeudCourant, boolean Ajout) {
		int current;
		if( PositionLettre < word.length() ) {


			while(NoeudCourant.getFrere() != null) {
				if( NoeudCourant.value == word.charAt(PositionLettre) ) {
					current = RechercheMot(word,PositionLettre+1,NoeudCourant.getFils(),Ajout);

					if( current == 0 ) {
						return( 0 );
					}
					else if( current == 1 ) {
						return( 1 );
					}
					else {
						return( -1 );
					}
				}
				NoeudCourant = NoeudCourant.getFrere();
			}


			if( NoeudCourant.getFrere() == null && NoeudCourant.value == word.charAt(PositionLettre) ) {
				current = RechercheMot(word,PositionLettre+1,NoeudCourant.getFils(),Ajout);
				if( current == 0 ) {
					return( 0 );
				}
				else if( current == 1 ) {
					return( 1 );
				}
				else {
					return( -1 );
				}
			}
			else {
				return( -1 );
			}
		}
		else {
			if( NoeudCourant.value == FinMot ){
				if(!Ajout){listeMotsPrefix(NoeudCourant, word, 1);}
				return( 1 );
			}
			else{
				if(!Ajout){listeMotsPrefix(NoeudCourant, word, 0);}
				return( 0 );
			}
		}
	}

	public int NombreMot(){
		return listeMots().size();
	}

	public int comptageNil(){
		return navigueNil(racine);
	}

	private int navigueNil(Noeud currentNode) {
		if(currentNode == null){
			return 1;
		} else
			//if(mot[i]<currentNode.getEtiquette()){
			return navigueNil(currentNode.getFils()) +
					navigueNil(currentNode.getFrere());
	}

	public int profondeurMoyenne(){
		int somme = 0;
		ArrayList<Integer> profondeurs = new ArrayList<Integer>();

		navigueProfondeur(racine.getFils(), profondeurs, 2);
		navigueProfondeur(racine.getFrere(), profondeurs, 2); 

		for(Integer prof : profondeurs){
			//System.out.println("    Profondeur : "+prof);
			somme += (int) prof;
		}
		return  somme/profondeurs.size();

	}

	private void navigueProfondeur(Noeud currentNode, ArrayList<Integer> listProfs, int currentp){
		if((currentNode == null) ){
			return ;
		} else if(currentNode.getFils()== null && currentNode.getFrere()==null){
			listProfs.add(currentp);			
		} else {
			navigueProfondeur(currentNode.getFils(), listProfs, currentp+1);

			navigueProfondeur(currentNode.getFrere(), listProfs, currentp+1);			

		}
	}

	public ArrayList<String> listeMots(){
		return navigueListeMots(racine, "");
	}

	public void listeMotsPrefix(Noeud N, String prefix, int i){
		ArrayList<String> l = navigueListeMots(N, prefix);
		if(i==0){
			console.setText(prefix+" n'existe pas mais est préfixe des "+l.size()+" mot(s) suivant(s) :");
			for(String mot : l  ){
				console.setText(mot);
			}
		}
		else{
			if(l.size()==1){
				console.setText(prefix+" existe et n'est pas prefixe d'autres mots.");
			}
			else{
				console.setText(prefix+" existe et est préfixe des "+l.size()+" mot(s) suivant(s) :");
				for(String mot : l  ){
					console.setText(mot);
				}
			}
		}
	}

	public void fusion(TreeBriandais otherTree) {
		TreeBriandais newTree = new TreeBriandais();
		newTree.racine = sousFusion(this.racine, otherTree.racine);
		racine = newTree.racine;
	}

	private Noeud sousFusion(Noeud nd1, Noeud nd2){
		Noeud newRacine = new Noeud();
		if(nd1 == null) return nd2;
		if(nd2 == null) return nd1;
		if(nd2.value < nd1.value){
			newRacine.setFils(nd2.getFils());
			newRacine.setFrere(sousFusion(nd1, nd2.getFrere()));
			newRacine.value = nd2.value;
		} else if(nd2.value > nd1.value){
			newRacine.setFils(nd1.getFils());
			newRacine.setFrere(sousFusion(nd1.getFrere(), nd2));
			newRacine.value = nd1.value;
		} else {
			newRacine.setFils(sousFusion(nd1.getFils(), nd2.getFils()));
			newRacine.setFrere(sousFusion(nd1.getFrere(), nd2.getFrere()));
			newRacine.value = nd1.value;
		}
		return newRacine;
	}

	private ArrayList<String> navigueListeMots(Noeud currentNode, String prefixeMot){
		String nouveauMot = prefixeMot;
		nouveauMot += currentNode.value;

		ArrayList<String> result = new ArrayList<String>();

		if(currentNode == null) return null;

		if(currentNode.value == FinMot){		//System.out.println("PASSE LA ! "+nouveauMot+"    id = "+currentNode.getId());
		result.add(nouveauMot.substring(0, nouveauMot.length() -1));
		}

		if(!(currentNode.getFils()==null)) result.addAll(navigueListeMots(currentNode.getFils(), nouveauMot));
		if(!(currentNode.getFrere()==null)) result.addAll(navigueListeMots(currentNode.getFrere(), prefixeMot));
		return result;
	}
	//*	
	public void supprimer(String mot){
		long start = System.nanoTime();
		navigueSuppr(racine, mot.toCharArray());
		long duree = System.nanoTime() - start;
		setTempsSuppr(duree);
	}

	private void navigueSuppr(Noeud node, char[] mot){
		Noeud currentNode = node;
		Noeud noeudCle = currentNode, pere = currentNode, pereCle = null;
		int oldfils = -2; // -1 = inf, 0 = eq, 1 = sup, -2 = rien à supprimer
		int i = 0;
		//mot[mot.length] = '*';
		while(i<mot.length){
			System.out.println("deb while");
			if(currentNode == null){
				break;
			}
			else if(mot[i] != currentNode.value){System.out.println("lettre dif-> frere");
			noeudCle = currentNode; oldfils = 1;
			pere = currentNode;
			currentNode = currentNode.getFrere();
			} else { System.out.println("lettre id-> fils");
			//				if(i == mot.length-1)
			//					if(currentNode.value == FinMot)
			//						abc;

			if(currentNode.getFrere() != null){ System.out.println("fils : avec frere");
			noeudCle = currentNode;
			if(oldfils != 2)
				oldfils = 0;
			}

			if(currentNode.getFils().getFrere() != null){
				pere = currentNode;
				oldfils = 2;
			}


			currentNode = currentNode.getFils();
			i++;
			}
		}

		System.out.println("fin while");

		if(pere.equals(this.racine) && pere.equals(noeudCle)){
			System.out.println("Dans -1, noeudCle:"+noeudCle.value+",pere:"+pere.value);
			if(racine.getFrere() == null)
				racine = null;
			else
				racine = racine.getFrere();
			return;
		}
		//if(currentNode == null) // vérifie qu'il n'y a pas de mot plus loin
		switch(oldfils){
		case 0 :
			System.out.println("Dans 0, noeudCle:"+noeudCle.value+",pere:"+pere.value);
			if(noeudCle.getFrere()!=null){
				System.out.println("if ");
				pere.setFrere(noeudCle.getFrere());
			}else {
				System.out.println("else ");
				noeudCle.setFrere(null);
			}
			return;
		case 1 :
			System.out.println("Dans 1, noeudCle:"+noeudCle.value+",pere:"+pere.value);
			/*if(noeudCle.getFrere()!=null){
					System.out.println("if ");
					pere.setFrere(noeudCle.getFrere());
				}else {*/
			System.out.println("else ");
			noeudCle.setFrere(null);
			//}
			return;
		case 2 :
			pere.setFils(noeudCle.getFrere());
		case -2 :
			return;
		}
	}
	//*/

	public TrieHybride conversion(){
		TrieHybride th = new TrieHybride();

		th.setNbMot(convertirUnNoeud(th.getRacine(), racine, 0));
		
		//while(th.pasEquilibre())
			th.equilibrer();
		
		return th;
	}

	private int convertirUnNoeudOld(HybridTrie.Noeud ndH, Noeud ndB, int nb_mot){
		HybridTrie.Noeud eq = new HybridTrie.Noeud(), sup = new HybridTrie.Noeud();

		if(ndB == null) return 0;

		// FinMot.getFrere disparait !

		if(ndB.value != FinMot){
			ndH.setEtiquette(ndB.value);

			if(ndB.getFils() != null && ndB.getFils().value == FinMot){
				ndH.setId(nb_mot++);
			}
			nb_mot = convertirUnNoeud(eq, ndB.getFils(), nb_mot);
			ndH.setEq(eq);
			nb_mot = convertirUnNoeud(sup, ndB.getFrere(), nb_mot);
			ndH.setSup(sup);
		} else {
			nb_mot = convertirUnNoeud(sup, ndB.getFrere(), nb_mot);
			ndH.setSup(sup);
		}
		return nb_mot;
	}
	
	private int convertirUnNoeud(HybridTrie.Noeud ndH, Noeud ndB, int nb_mot){
		HybridTrie.Noeud eq = new HybridTrie.Noeud(), sup = new HybridTrie.Noeud();
		 HybridTrie.Noeud sup2 = new HybridTrie.Noeud();
		if(ndB == null) return 0;
		if(ndB.value != FinMot){
			ndH.setEtiquette(ndB.value);
			if(ndB.getFils() != null){
				if(ndB.getFils().value == FinMot){
					ndH.setId(nb_mot++);
					if(ndB.getFils().getFrere() != null){
						nb_mot = convertirUnNoeud(sup2, ndB.getFils().getFrere(), nb_mot);
						ndH.setEq(sup2);

					}
				} else {
					nb_mot = convertirUnNoeud(eq, ndB.getFils(), nb_mot);
					ndH.setEq(eq);
				}
			}
			if(ndB.getFrere() != null){
				nb_mot = convertirUnNoeud(sup, ndB.getFrere(), nb_mot);
				ndH.setSup(sup);
			}
			return nb_mot;
		} else
			if(ndB.getFrere() != null){ System.out.println("ta mere");
			//nb_mot = convertirUnNoeud(eq, ndB.getFrere(), nb_mot);
			//ndH = eq;
			}
		return nb_mot;
	}

	public int hauteur(){
		return Hauteur(racine);
	}

	public int Hauteur(Noeud Courant){
		if(Courant == null){
			return 0;
		} else
			return 1 + Math.max(Hauteur(Courant.getFrere()),Hauteur(Courant.getFils()));
	}

}
