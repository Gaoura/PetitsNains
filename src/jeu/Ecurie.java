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

	@Override
	public void rentrerPiece(Piece p) {
		// rentrer une pi�ce dans l'�curie
		// dans le jeu en fonction de l'exception attrapp�e, on fait la fonction qui lui est li�e
		p.getProprietaire().getPieces().remove(p);
		((CaseNormale) p.getPos()).setOccupant(null);
	}
	
}
