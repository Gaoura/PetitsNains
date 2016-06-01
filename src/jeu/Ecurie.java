package jeu;

public class Ecurie extends Reserve {
	// Nombre de chevaux pouvant encore entrer en jeu 
	private int depart;
	// Nombre de chevaux à avoir fini leur tour de plateau
	private int arrivee;
	
	public Ecurie() {
		//Il faudrait mettre le nombre de chevaux en paramètres
		//pour gérer un jeu à n chevaux
		this.depart = 4;
		this.arrivee = 4;
	}

	public int getDepart() {
		return this.depart;
	}

	public int getArrivee() {
		return this.arrivee;
	}

	private void setDepart(int depart) throws EcurieException {
		if (depart < 0)
			throw new EcurieException("Plus aucun cheval dans l'écurie");
		
		this.depart = depart;
	}

	private void setArrivee(int arrivee) {
		this.arrivee = arrivee;
	}
	

	// Fait revenir un cheval dégagé par un adversaire à l'écurie de départ
	public void sortirCheval() throws EcurieException {
		this.setDepart(this.depart + 1);
	}
	

	// Fait revenir un cheval à l'écurie d'arrivée après qu'il ait fini son tour de plateau
	public void chevalArrivee() {
		this.setArrivee(this.arrivee + 1);
	}

	@Override
	public void sortirPiece(Piece p) throws EcurieException {
		// sortir une pièce de l'écurie
		this.setDepart(this.depart - 1);
	}

	@Override
	public void rentrerPiece(Piece p) {
		// rentrer une pièce dans l'écurie
		// dans le jeu en fonction de l'exception attrappée, on fait la fonction qui lui est liée
		p.getProprietaire().getPieces().remove(p);
		((CaseNormale) p.getPos()).setOccupant(null);
	}
	
}
