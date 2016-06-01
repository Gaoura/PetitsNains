package jeu;

import java.util.ArrayList;

public class Joueur {
	private String nomJoueur;
	private int numeroJoueur;
	private ArrayList<Piece> pieces;
	private Reserve stock;
	
	public Joueur(String nom, int num, Reserve e) {
		this.setNomJoueur(nom);
		this.setNumeroJoueur(num);
		this.setStock(e);
	}
	
	public Reserve getStock() {
		return this.stock;
	}
	
	private void setStock(Reserve e) {
		this.stock = e;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	private void setNomJoueur(String nomJoueur) {
		if (nomJoueur == null) throw new NullPointerException();
		if (nomJoueur.equals("")) throw new IllegalArgumentException();
		this.nomJoueur = nomJoueur;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	private void setNumeroJoueur(int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
	}

	public ArrayList<Piece> getPieces() {
		return this.pieces;
	}
	
	public void add (Piece p) {
		this.getPieces().add(p);
	}
	
	public Piece get(int index) {
		return this.getPieces().get(index);
	}
	
	public int size() {
		return this.getPieces().size();
	}
	
	public boolean remove(Piece p) {
		return this.getPieces().remove(p);
	}
	
	@Override
	public String toString() {
		return "" + this.getNomJoueur() + "/" + this.getNumeroJoueur();
	}
	
	
}
