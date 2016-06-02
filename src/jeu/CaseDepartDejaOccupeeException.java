package jeu;

@SuppressWarnings("serial")
public class CaseDepartDejaOccupeeException extends Exception //Impossible de sortir un cheval car la case de départ est occupée
{
	public CaseDepartDejaOccupeeException()
	{
		super();
	}
}
