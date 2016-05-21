package jeu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Piece {
	private Joueur proprietaire;
	private String nom;
	private Case pos;
	private int direction;
	
	public Piece (Joueur j, String nom, Case pos) {
		this.setProprietaire(j);
		this.setNom(nom);
		this.setPos(pos);
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	public Case getPos() {
		return pos;
	}

	public void setPos(Case pos) {
		this.pos = pos;
	}
	
	public void seDeplacer(int score) {
		//Lis le fichier mouvement.txt pour obtenir les pivots
		//Puis si la coordonnées courante à chaque déplacement correspond à une entrée
		//on est sur un pivot et on doit changer de direction
		String pos;
		String dir;
		
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
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
}
