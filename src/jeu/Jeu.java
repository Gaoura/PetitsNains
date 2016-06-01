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
	private Hashtable<Integer, String> ht = new Hashtable<Integer, String>(); //la liste des pivots
	private Hashtable<Integer, String> htInv = new Hashtable<Integer, String>(); //la liste des pivots pour les mouvements de retour
	
	public Jeu(Joueur[] j, Plateau p) {
		this.setJoueur(j);
		this.setPlateau(p);
		this.setHt();
	}
	
	public Hashtable<Integer, String> getHt () {
		return this.ht;
	}
	
	private void setHt() {
		//Lis la liste des pivots stock�s dans le fichier "mouvement.txt"
		//et range les donn�es dans une hashtable
		String pos, posX, posY, dir;
		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/mouvement.txt"));
			while (sc.hasNextLine())
			{
				sc.nextLine();
				sc.findInLine("(\\w+),(\\w+):(\\w+)");
				MatchResult result = sc.match();
				posX = result.group(1);
				posY = result.group(2);
				dir = result.group(3);
				pos = posX + "," + posY;
				this.ht.put(pos.hashCode(), dir);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas �t� trouv�");
			    e.printStackTrace();
		}
		
		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/mouvementInv.txt"));
			while (sc.hasNextLine())
			{
				sc.nextLine();
				sc.findInLine("(\\w+),(\\w+):(\\w+)");
				MatchResult result = sc.match();
				posX = result.group(1);
				posY = result.group(2);
				dir = result.group(3);
				pos = posX + "," + posY;
				this.htInv.put(pos.hashCode(), dir);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas �t� trouv�");
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
		if (numJoueur < 0 || numJoueur >= this.getJoueur().length)
			throw new IllegalArgumentException();
		this.joueurCourant = numJoueur;
	}

	public void setJoueur(Joueur[] joueur) {
		this.joueur = joueur;
	}
	
	@Override
	public void terminerTour() {
		//Arr�te un tour en cours et donne la main au suivant
		this.setJoueurCourant(((this.joueurCourant++) % this.getJoueur().length));
	}

	@Override
	public int lancerDe() {
		//Lance un d� dont le r�sultat sera compris entre
		//1 inclus et 7 exclus
		int min = 1;
		int max = 6;
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	@Override
	public void deplacerPiece(Piece p, int score) {
		int inv = 1;
		for (int i=0; i<score; i++) {
			int abs = 0, ord = 0;
			int hashedKey = p.getPos().getPosition().toString().hashCode();
			
			if (inv == 1) 
				if (ht.containsKey(hashedKey))
					p.setDir(ht.get(hashedKey));
			else //Si on est en d�placement invers�
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
			
			Coordonnees caseSuivant = p.getPos().getPosition(); //Les coordonn�es courante de la piece 
			Coordonnees temp = new Coordonnees(caseSuivant);
			
			temp.setAbscisse(temp.getAbscisse()+abs);
			temp.setOrdonnee(temp.getOrdonnee()+ord); //Acceder � la case suivante 
			if ((((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(temp)).getOccupant() != null)) {
					if (score - i != 1) {
						//Si le score est diff�rent de 1 et que 
						//la case suivante a un occupant, on doit repartir dans l'autre sens
						inv *= -1; 
						ord *= -1;
						abs *= -1;
						temp.setAbscisse(caseSuivant.getAbscisse()+abs);
						temp.setOrdonnee(caseSuivant.getOrdonnee()+ord); //Acceder � la case suivante 
					} else {
						//sortir la piece (score == 1)
						//et prendre sa place
						CaseOccupable nvlEmplacement = ((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(temp));
						try {
							((Ecurie) nvlEmplacement.getOccupant().getProprietaire().getStock()).sortirCheval(); //Le chavel est retourn� dans son �curie
						} catch (EcurieException e) {} //l'exception ne peut pas se produire puisqu'on ajoute un cheval
						this.getJoueurCourant().remove(nvlEmplacement.getOccupant());					
						caseSuivant = nvlEmplacement.getPosition();
					}
			} else
				caseSuivant = temp;
			
			((CaseOccupable) p.getPos()).setOccupant(null); //La case courante devient vide
			p.setPos(this.getPlateau().getCaseDepuisCoordonnees(caseSuivant));//La pi�ce change de case
			((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(caseSuivant)).setOccupant(p); //La nouvelle case a son occupant
		}
	}
	/*//G�n�rer une position de d�part en fonction du joueur en fonction du num�ro de joueur courant
	// 0 = 0,6
	// 1 = 8,0
	// 2 = 14,8
	// 3 = 6,14
	//ensuite regarder dans la hashtable pour savoir sur quel pivot on d�marre
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
		//Le cheval sur la case de d�part retourne � l'�curie
		this.rentrerPiece(occupantDepart);
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
}*/
}