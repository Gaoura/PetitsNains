package jeu;

public interface Tour {
	public void terminerTour();
	public int lancerDe();
	public void deplacerPiece(Piece p, int score);
	public void rentrerCheval(Piece p) throws ChevalException, EcurieException, CaseDepartDejaOccupeeException;
	public void sortirCheval(Piece p) throws EcurieException;
}
