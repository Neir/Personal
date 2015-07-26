package HybridTrie;
import java.util.ArrayList;
import java.util.Collections;

import Tools.Parser;
import AffichageGeneral.PanelChoixArbre;
import AffichageGeneral.PanelText;
import BriandaisTree.TreeBriandais;


public class TrieHybride {
	private Noeud racine;
	private int nb_mot;

	public TrieHybride(){
		racine = new Noeud();
		nb_mot = 0;
	}

	public TrieHybride(Noeud racine){
		this.racine = racine;
		nb_mot = 0;
	}

	
	private PanelText console ;
	private PanelChoixArbre Progress ;
	private long temps=0, tempslast ;


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
	public void setChoix(PanelChoixArbre Progress ){
		this.Progress=Progress;
	}
	public void setConsole(PanelText console){
		this.console=console;
	}






	public boolean recherche(String mot){
		return navigue(racine, mot.toCharArray(), 0);
	}

	private boolean navigue(Noeud currentNode, char[] mot, int i) {
		if(currentNode.estNil() || currentNode == null)
			return false;
		else if(mot[i]<currentNode.getEtiquette())
			return navigue(currentNode.getInf(), mot, i);
		else if(mot[i]>currentNode.getEtiquette())
			return navigue(currentNode.getSup(), mot, i);
		else //  mot[i] == currentNode.getEtiquette()
			if(mot.length-1 == i)
				return (currentNode.getId() >= 0)? true : false;
			else
				return navigue(currentNode.getEq(), mot, i+1);
	}

	public void ajoutMot(String mot){
		navigueAjout(racine, mot.toCharArray(), 0);
	}

	private void navigueAjout(Noeud currentNode, char[] mot, int i) {
		if(i != mot.length){
			if(currentNode.estNil()){
				currentNode.setEtiquette(mot[i]);
				//currentNode.setEtiquette(mot[i]);
				if(i == mot.length-1){ 	
					//System.out.println("Etiquette ! "+currentNode.getEtiquette()+"      taille : "+mot.length+"     mot : "+String.valueOf(mot));
					currentNode.setId(nb_mot++);
				} else {
					navigueAjout(currentNode.getEq(), mot, i+1);
				}
			} else if(mot[i]<currentNode.getEtiquette()){
				navigueAjout(currentNode.getInf(), mot, i);
			} else if(mot[i]>currentNode.getEtiquette()){
				navigueAjout(currentNode.getSup(), mot, i);
			} else {//  mot[i] == currentNode.getEtiquette()
				if(i == mot.length-1 && currentNode.getEtiquette() == mot[i] && currentNode.getId() == -1){ 	
					//System.out.println("Etiquette ! "+currentNode.getEtiquette()+"      taille : "+mot.length+"     mot : "+String.valueOf(mot));
					currentNode.setId(nb_mot++);
				}
				navigueAjout(currentNode.getEq(), mot, i+1);
			}
		}
	}

	public void ajoutListeMot(ArrayList<String> listeMot){
		for(String mot : listeMot){
			this.ajoutMot(mot);
		}
	}

	public void ajoutPhrase(String phrase){
		
		ArrayList<String> mots = Parser.parseBufferToWords(phrase);
		int NBMots = mots.size();
		int i=0;
				
		for(String mot : mots){
			long start = System.nanoTime();
			this.ajoutMot(mot);
			i++;
			Progress.SetValueHyb((i*100)/NBMots);
			long duree = System.nanoTime() - start;
			setTemps(temps+duree) ;
			setTempsLast(duree) ;
		}
	}

	public int comptageMots(){
		return nb_mot;
	}

	public ArrayList<String> listeMots(){
		return navigueListeMots(racine, "");
	}

	private ArrayList<String> navigueListeMots(Noeud currentNode, String prefixeMot){
		String nouveauMot = prefixeMot;
		//nouveauMot.concat(String.valueOf(currentNode.getEtiquette()));
		nouveauMot += currentNode.getEtiquette();
		ArrayList<String> result = new ArrayList<String>();

		//System.out.println("Etiquette ! "+currentNode.getEtiquette()+"      prefixe : "+prefixeMot+"      mot : "+nouveauMot);

		if(currentNode.estNil()) return null;

		if(currentNode.getId()>=0){		//System.out.println("PASSE LA ! "+nouveauMot+"    id = "+currentNode.getId());
			result.add(nouveauMot);
		}
		if(!currentNode.getInf().estNil()) result.addAll(navigueListeMots(currentNode.getInf(), prefixeMot));
		if(!currentNode.getEq().estNil()) result.addAll(navigueListeMots(currentNode.getEq(), nouveauMot));
		if(!currentNode.getSup().estNil()) result.addAll(navigueListeMots(currentNode.getSup(), prefixeMot));
		return result;
	}

	public int comptageNil(){
		return navigueNil(racine);
	}

	private int navigueNil(Noeud currentNode) {
		if(currentNode.estNil()){
			return 1;
		} else
			//if(mot[i]<currentNode.getEtiquette()){
			return navigueNil(currentNode.getInf()) +
					navigueNil(currentNode.getEq()) +
					navigueNil(currentNode.getSup());
	}

	public int hauteur(){
		return navigueHauteur(racine);
	}

	private int navigueHauteur(Noeud currentNode) {
		if(currentNode == null){
			return 0;
		} else
			return 1 + Math.max(Math.max(navigueHauteur(currentNode.getInf()),
					navigueHauteur(currentNode.getEq())),
					navigueHauteur(currentNode.getSup()));
	}

	public int profondeurMoyenne(){
		int somme = 0;
		ArrayList<Integer> profondeurs = new ArrayList<Integer>();

		navigueProfondeur(racine.getInf(), profondeurs, 1);
		navigueProfondeur(racine.getEq(), profondeurs, 1); 
		navigueProfondeur(racine.getSup(), profondeurs, 1); 

		for(Integer prof : profondeurs){
			//System.out.println("    Profondeur : "+prof);
			somme += (int) prof;
		}
		return somme/profondeurs.size();
	}

	private void navigueProfondeur(Noeud currentNode, ArrayList<Integer> listProfs, int currentp){
		if(currentNode.estNil()){
			return;
		} else if(currentNode.estFeuille()){
			listProfs.add(currentp);
		} else {
			navigueProfondeur(currentNode.getInf(), listProfs, currentp+1);

			navigueProfondeur(currentNode.getEq(), listProfs, currentp+1);

			navigueProfondeur(currentNode.getSup(), listProfs, currentp+1);
		}
	}

	public int prefixe(String mot){
		return naviguePrefixe(racine, mot.toCharArray(), 0);
	}

	private int naviguePrefixe(Noeud currentNode, char[] mot, int i) {
		if(currentNode.estNil() || currentNode == null)
			return 0;

		if(mot.length > i){
			if(mot[i]<currentNode.getEtiquette())
				return naviguePrefixe(currentNode.getInf(), mot, i);
			else if(mot[i]>currentNode.getEtiquette())
				return naviguePrefixe(currentNode.getSup(), mot, i);
			else {//  mot[i] == currentNode.getEtiquette()
				if(mot.length-1 == i && currentNode.getId() != -1) // le prefixe est un mot
					return 1 + naviguePrefixe(currentNode.getEq(), mot, i+1);
				return naviguePrefixe(currentNode.getEq(), mot, i+1);
			}
		} else { // On a trouvé le prefixe
			if(currentNode.getId() != -1) // le noeud contient un mot
				return 1 + naviguePrefixe(currentNode.getInf(), mot, i) 
						+ naviguePrefixe(currentNode.getEq(), mot, i)
						+ naviguePrefixe(currentNode.getSup(), mot, i);
			else
				return naviguePrefixe(currentNode.getInf(), mot, i) 
						+ naviguePrefixe(currentNode.getEq(), mot, i)
						+ naviguePrefixe(currentNode.getSup(), mot, i);
		}
	}

	public void suppression(String mot){
		if(recherche(mot)){
			navigueSuppr(racine, mot.toCharArray());
		}
	}

	private void navigueSuppr(Noeud node, char[] mot){
		Noeud currentNode = node;
		Noeud noeudCle = currentNode; //, pere = null, pereCle = null; 
		int oldfils = -2; // -1 = inf, 0 = eq, 1 = sup, -2 = rien à supprimer
		int i = 0;
		while(i<mot.length){
			
			if(currentNode.estNil()){
				return;
			}
			else if(mot[i]<currentNode.getEtiquette()){
				noeudCle = currentNode; oldfils = -1;
				//pere = currentNode;
				currentNode = currentNode.getInf();
			} else if (mot[i]>currentNode.getEtiquette()){
				noeudCle = currentNode; oldfils = 1;
				//pere = currentNode;
				currentNode = currentNode.getSup();
			} else {
				if(i == mot.length-1){
					currentNode.setId(-1);
				}

				if(!currentNode.getInf().estNil() || !currentNode.getSup().estNil()){
					noeudCle = currentNode;
					//pereCle = pere;
					oldfils = 0;
				}
				//pere = currentNode;
				currentNode = currentNode.getEq();
				i++;
			}
		}
		if(currentNode.estNil()) // vérifie qu'il n'y a pas de mot plus loin
		switch(oldfils){
			case -1 :
				noeudCle.getInf().supprimerRec();
				return;
			case 0 :
				noeudCle.getEq().supprimerRec();
				//replaceAuCentre(pereCle);

				return;
			case 1 :
				noeudCle.getSup().supprimerRec();
				return;
			case -2 :
				noeudCle.supprimerRec();
				return;
			}
	}
	
	private BriandaisTree.Noeud navigueConversion(Noeud currentNode){
		BriandaisTree.Noeud noeudBriandais = new BriandaisTree.Noeud();
		BriandaisTree.Noeud noeudFinMot = new BriandaisTree.Noeud();
		noeudFinMot.value = '*';

		if(currentNode.estNil()) return null;

		if(!currentNode.getInf().estNil())
			noeudBriandais = navigueConversion(currentNode.getInf());
		else {
			noeudBriandais.value = currentNode.getEtiquette();
			if(currentNode.getId() != -1){
				noeudBriandais.setFils(noeudFinMot);
				noeudBriandais.setFrere(navigueConversion(currentNode.getSup()));
			} else {
				noeudBriandais.setFils(navigueConversion(currentNode.getEq()));
				noeudBriandais.setFrere(navigueConversion(currentNode.getSup()));
			}
		}
		return noeudBriandais;
	}

	public TreeBriandais conversion() {
		TreeBriandais tb;
		BriandaisTree.Noeud noeudFinMot = new BriandaisTree.Noeud();
		noeudFinMot.value = '*';
		
		if (this.racine.estNil())
			return new TreeBriandais();
		else {
			tb = new TreeBriandais();
			tb.racine = new BriandaisTree.Noeud();
			tb.racine.value = this.racine.getEtiquette();
			tb.racine.setFils(null);
			tb.racine.setFrere((this.racine.getSup() != null) ? 
					(new TrieHybride(this.racine.getSup()).conversion()).racine : null);
			if(this.racine.getId() != -1) {
				BriandaisTree.Noeud nd = new BriandaisTree.Noeud();
				nd.value = '*';
				nd.setFils(null);
				nd.setFrere((this.racine.getEq() != null) ? 
						(new TrieHybride(this.racine.getEq()).conversion()).racine : null);
				tb.racine.setFils(nd);
			} else {
				tb.racine.setFils((new TrieHybride(this.racine.getEq())).conversion().racine);
			}
			if (this.racine.getInf() != null){
				tb.fusion((new TrieHybride(this.racine.getInf())).conversion());
				return tb;
			}
			else
				return tb;
		}
	}
	
	public void equilibrer(){
		ArrayList<String> listMotsMelanges = this.listeMots();
		reset();
		Collections.shuffle(listMotsMelanges);
		this.ajoutListeMot(listMotsMelanges);
		
		//navigueEquilibre(racine);
	}
	
	public void reset(){
		racine = new Noeud();
		nb_mot = 0;
	}
	
	private void navigueEquilibre(Noeud ndCourrant){
		Noeud ndTmp;
		
		if(ndCourrant.estNil()) return;
		
		if(new TrieHybride(ndCourrant.getInf()).comptageMots() > new TrieHybride(ndCourrant.getEq()).comptageMots()){
			//swap(ndCourrant, ndCourrant.getInf());
			ndTmp = ndCourrant;
			ndCourrant = ndCourrant.getInf();
			new TrieHybride(ndCourrant).ajoutListeMot(new TrieHybride(ndTmp.getEq()).listeMots());
			new TrieHybride(ndCourrant).ajoutListeMot(new TrieHybride(ndTmp.getSup()).listeMots());
			// on prend tous les mots : prendre que ceux eq et sup (attention à la racine pour eq)
			// problème au niveau des id aussi !
			
		}
		/*else*/ if(new TrieHybride(ndCourrant.getSup()).comptageMots() > new TrieHybride(ndCourrant.getEq()).comptageMots()){
			//swap(ndCourrant, ndCourrant.getSup());
		}
		navigueEquilibre(ndCourrant.getInf());
		navigueEquilibre(ndCourrant.getEq());
		navigueEquilibre(ndCourrant.getSup());
	}
	
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	public void setNbMot(int nb_mot){
		this.nb_mot = nb_mot;
	}
	
}