package jeu;

public abstract class Case
{
	// Coordonnees de la case sur le plateau 
	private Coordonnees position;
		
	public Case(Coordonnees c)
	{
		this.setPosition(c);
	}

	public Coordonnees getPosition()
	{
		return this.position;
	}

	private void setPosition(Coordonnees position)
	{
		if (position == null)
			throw new NullPointerException();
		
		this.position = position;
	}

}
