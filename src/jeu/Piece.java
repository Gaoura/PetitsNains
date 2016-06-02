package jeu;

public class Piece {
	private Joueur proprietaire;
	private String nom;
	private Case pos;
	private String dir;
	
	public Piece (Joueur j, String nom, Case pos, String dir) {
		this.setProprietaire(j);
		this.setNom(nom);
		this.setPos(pos);
		this.setDir(dir);
	}
	
	public String getDir() {
		return this.dir;
	}
	
	public void setDir(String dir) {
		if (!dir.equals("d") && !dir.equals("g") && !dir.equals("h") && !dir.equals("b"))
			throw new IllegalArgumentException("Direction incorrecte");
		this.dir = dir;
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
}
