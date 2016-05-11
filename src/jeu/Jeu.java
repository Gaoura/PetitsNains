package jeu;

public class Jeu {

	private Joueur[] joueur;
	private Plateau plateau;
	private int joueurCourant = 0;
	
	public Jeu(Joueur[] j, Plateau p) {
		this.setJoueur(j);
		this.setPlateau(p);
	}
	
	
}
