package jeu;

public class Jeu {

	private Joueur[] joueur;
	private Plateau plateau;
	private int joueurCourant = 0;
	
	public Jeu(Joueur[] j, Plateau p) {
		this.setJoueur(j);
		this.setPlateau(p);
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

	public int getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(int joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void setJoueur(Joueur[] joueur) {
		this.joueur = joueur;
	}
	
	
}
