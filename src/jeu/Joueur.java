package jeu;

public class Joueur {
	private String nomJoueur;
	private int numeroJoueur;
	private Piece[] pieces;
	
	public Joueur(String nom, int num, Piece[] p) {
		this.setNomJoueur(nom);
		this.setNumeroJoueur(num);
		this.setPieces(p);
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		if (nomJoueur == null) throw new NullPointerException();
		if (nomJoueur.equals("")) throw new IllegalArgumentException();
		this.nomJoueur = nomJoueur;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public void setNumeroJoueur(int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
	}

	public Piece[] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}

}
