package jeu;

public class Plateau
{
	private Case[][] cases;
	
	public Plateau(Case[][] c)
	{
		this.setCases(c);
	}
	
	public Plateau(Coordonnees c)
	{
		Case[][] plateau = new Case[c.getAbscisse()][c.getOrdonnee()];
		this.setCases(plateau);
	}

	public Case[][] getCases()
	{
		return this.cases;
	}

	public void setCases(Case[][] cases)
	{
		if (cases == null)
			throw new NullPointerException();
		
		this.cases = cases;
	}
}
