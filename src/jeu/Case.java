package jeu;

public abstract class Case
{
	private Coordonnees position;
	private Piece occupant;
	private boolean visible;
	
	public Case(Coordonnees c)
	{
		this.setPosition(c);
		this.setOccupant(null);
		this.setVisible(true);
	}

	public Coordonnees getPosition()
	{
		return this.position;
	}

	public Piece getOccupant()
	{
		return this.occupant;
	}

	public boolean isVisible()
	{
		return this.visible;
	}

	public void setPosition(Coordonnees position)
	{
		if (position == null)
			throw new NullPointerException();
		
		this.position = position;
	}

	public void setOccupant(Piece occupant)
	{
		this.occupant = occupant;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
