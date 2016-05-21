package jeu;

public class Plateau
{
	private Case[][] cases;
	
	public Plateau(Case[][] c)
	{
		this.setCases(c);
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
