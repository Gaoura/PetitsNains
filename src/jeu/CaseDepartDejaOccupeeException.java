package jeu;

@SuppressWarnings("serial")
public class CaseDepartDejaOccupeeException extends Exception //Impossible de sortir un cheval car la case de d�part est occup�e
{
	public CaseDepartDejaOccupeeException()
	{
		super();
	}
}
