package jeu;

public class Ecurie extends Reserve {
	// Nombre de chevaux pouvant encore entrer en jeu 
	private int depart;
	// Nombre de chevaux � avoir fini leur tour de plateau
	private int arrivee;
	
	public Ecurie() {
		//Il faudrait mettre le nombre de chevaux en param�tres
		//pour g�rer un jeu � n chevaux
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
			throw new EcurieException("Plus aucun cheval dans l'�curie");
		
		this.depart = depart;
	}

	private void setArrivee(int arrivee) {
		this.arrivee = arrivee;
	}
	

	// Fait revenir un cheval d�gag� par un adversaire � l'�curie de d�part
	public void sortirCheval() throws EcurieException {
		this.setDepart(this.depart + 1);
	}
	

	// Fait revenir un cheval � l'�curie d'arriv�e apr�s qu'il ait fini son tour de plateau
	public void chevalArrivee() {
		this.setArrivee(this.arrivee + 1);
	}

	@Override
	public void sortirPiece(Piece p) throws EcurieException {
		// sortir une pi�ce de l'�curie
		this.setDepart(this.depart - 1);
	}

	@Override
	public void rentrerPiece(Piece p) {
		// rentrer une pi�ce dans l'�curie
		// dans le jeu en fonction de l'exception attrapp�e, on fait la fonction qui lui est li�e
		p.getProprietaire().getPieces().remove(p);
		((CaseNormale) p.getPos()).setOccupant(null);
	}
	
}
