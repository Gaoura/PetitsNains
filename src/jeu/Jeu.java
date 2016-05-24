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
		int hashedKey = p.getPos().getPosition().toString().hashCode();
		if (ht.containsKey(hashedKey))
			p.setDir(ht.get(hashedKey));
		
		if (p.getDir().equals("d")) {
			//abscisse ++
		} else if (p.getDir().equals("g")) {
			//abscisse --
		} else if (p.getDir().equals("h")) {
			//ordonnée ++
		} else if (p.getDir().equals("b")) {
			//ordonnée ++
		}
		
	}
	
	public void rentrerCheval(Piece p) throws ChevalException, EcurieException, CaseDepartDejaOccupeeException {
		//Générer une position de départ en fonction du joueur en fonction du numéro de joueur courant
		// 0 = 0,6
		// 1 = 8,0
		// 2 = 14,8
		// 3 = 6,14
		//ensuite regarder dans la hashtable pour savoir sur quel pivot on démarre
		Coordonnees c = null;
		switch (this.getNumJoueurCourant()) {
			case 0:
				c = new Coordonnees(0,6);
				break;
			case 1 :
				c = new Coordonnees(8,0);
				break;
			case 2 :
				c = new Coordonnees(14,8);
				break;
			case 3 :
				c = new Coordonnees(6,14);
				break;
		}
		Piece occupantDepart = ((CaseNormale) this.getPlateau().getCaseDepuisCoordonnees(c)).getOccupant();
		if (occupantDepart == null) {
			this.ajout (p);
		} else if (occupantDepart.getProprietaire() != this.getJoueurCourant()) {
			//Le cheval sur la case de départ retourne à l'écurie
			this.sortirCheval(occupantDepart);
			ajout(p);
		} else if (occupantDepart.getProprietaire() == this.getJoueurCourant()) 
			throw new CaseDepartDejaOccupeeException();
	}
	
	public void ajout (Piece p) throws ChevalException, EcurieException {
		Piece piece = null;
		for (Piece iterateurPiece : this.getJoueurCourant().getPieces()) {
			if (iterateurPiece.getNom().equals(p.getNom())) {
				piece = iterateurPiece;
				break;
			}
		}
		
		if (p == null) throw new ChevalException("Cheval introuvable");
		this.getJoueurCourant().getStock().sortirPiece();
		String nomCheval = "cheval" + (4-((Ecurie) this.getJoueurCourant().getStock()).getDepart());
		String newDir = ht.get(p.getPos().getPosition().toString().hashCode());
		this.getJoueurCourant().add(new Piece(this.getJoueurCourant(), nomCheval, piece.getPos(), newDir));
	}

	@Override
	public void sortirCheval(Piece p) throws EcurieException {
		//sortir un cheval du plateau
		p.getProprietaire().getPieces().remove(p);
		p.getProprietaire().getStock().sortirPiece();
		((CaseNormale) p.getPos()).setOccupant(null);
	}
}
