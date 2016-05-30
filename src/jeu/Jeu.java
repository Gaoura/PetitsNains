package jeu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Jeu implements Tour{

	private Joueur[] joueur; //Liste des joueurs
	private Plateau plateau; //plateau du jeu
	private int joueurCourant = 0; //le joueur en cour
	private Hashtable<Integer, String> ht; //la liste des pivots
	private Hashtable<Integer, String> htInv; //la liste des pivots pour les mouvements de retour
	
	public Jeu(Joueur[] j, Plateau p) {
		this.setJoueur(j);
		this.setPlateau(p);
		this.setHt();
	}
	
	public Hashtable<Integer, String> getHt () {
		return this.ht;
	}
	
	private void setHt() {
		//Lis la liste des pivots stockés dans le fichier "mouvement.txt"
		//et range les données dans une hashtable
		String pos, dir;
		try {
			Scanner sc = new Scanner (new FileReader("mouvement.txt"));
			do {
				sc.findInLine("(\\w+):(\\w+)");
				MatchResult result = sc.match();
				pos = result.group(1);
				dir = result.group(2);
				ht.put(pos.hashCode(), dir);
			} while (sc.nextLine() != null);
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas été trouvé");
			    e.printStackTrace();
		}
		
		try {
			Scanner sc = new Scanner (new FileReader("mouvementInv.txt"));
			do {
				sc.findInLine("(\\w+):(\\w+)");
				MatchResult result = sc.match();
				pos = result.group(1);
				dir = result.group(2);
				ht.put(pos.hashCode(), dir);
			} while (sc.nextLine() != null);
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas été trouvé");
			    e.printStackTrace();
		}
	}
	
	public Joueur[] getJoueur() {
		return this.joueur;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public int getNumJoueurCourant() {
		return this.joueurCourant;
	}
	
	public Joueur getJoueurCourant() {
		return this.getJoueur()[this.getNumJoueurCourant()];
	}

	public void setJoueurCourant(int numJoueur) {
		if (numJoueur <= 0 || numJoueur >= this.getJoueur().length)
			throw new IllegalArgumentException();
		this.joueurCourant = numJoueur;
	}

	public void setJoueur(Joueur[] joueur) {
		this.joueur = joueur;
	}
	
	@Override
	public void terminerTour() {
		//Arrête un tour en cours et donne la main au suivant
		this.setJoueurCourant(((this.joueurCourant++) % this.getJoueur().length));
	}

	@Override
	public int lancerDe() {
		//Lance un dé dont le résultat sera compris entre
		//1 inclus et 7 exclus
		int Min = 1;
		int Max = 7;
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}

	@Override
	public void deplacerPiece(Piece p, int score) {
		// TODO Auto-generated method stub
		int inv = 1;
		for (int i=0; i<score; i++) {
			int abs = 0, ord = 0;
			int hashedKey = p.getPos().getPosition().toString().hashCode();
			
			if (inv == 1) 
				if (ht.containsKey(hashedKey))
					p.setDir(ht.get(hashedKey));
			else //Si on est en déplacement inversé
				if (htInv.containsKey(hashedKey))
					p.setDir(htInv.get(hashedKey));
			
			switch (p.getDir()) {
				case "d" :
					abs = 1;
					break;
				case "g" :
					abs = -1;
					break;
				case "h" :
					ord = 1;
					break;
				case "b" :
					ord = -1;
					break;
			}
			
			Coordonnees caseSuivant = p.getPos().getPosition(); //Les coordonnées courante de la piece 
			Coordonnees temp = new Coordonnees(caseSuivant);
			
			temp.setAbscisse(temp.getAbscisse()+abs);
			temp.setOrdonnee(temp.getOrdonnee()+ord); //Acceder à la case suivante 
			if ((((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(temp)).getOccupant() != null)) {
					if (score - i != 1) {
						inv *= -1; //Si la case suivante a un occupant, on doit repartir dans l'autre sens
						ord *= -1;
						abs *= -1;
						temp.setAbscisse(caseSuivant.getAbscisse()+abs);
						temp.setOrdonnee(caseSuivant.getOrdonnee()+ord); //Acceder à la case suivante 
					} else {
						//sortir la piece (score == 1)
					}
			} else
				caseSuivant = temp;
			
			((CaseOccupable) p.getPos()).setOccupant(null); //La case courante devient vide
			p.setPos(this.getPlateau().getCaseDepuisCoordonnees(caseSuivant));//La pièce change de case
			((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(caseSuivant)).setOccupant(p); //La nouvelle case a son occupant
		}
	}
}