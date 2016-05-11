package jeu;

public class CaseOccupable extends Case
{
	private Piece occupant;
	
	public CaseOccupable(Coordonnees c)
	{
		super(c);
		this.setOccupant(null);
	}
	
	public Piece getOccupant()
	{
		return this.occupant;
	}
	
	public void setOccupant(Piece occupant)
	{
		this.occupant = occupant;
	}

}
