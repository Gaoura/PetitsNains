package jeu;

public class Piece {
	private Joueur proprietaire;
	private String nom;
	private Case pos;
	
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
		
	}
	
	
}
