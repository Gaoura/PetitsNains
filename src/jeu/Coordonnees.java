package jeu;

public class Coordonnees
{
	private int abscisse;
	private int ordonnee;
	
	public Coordonnees(int x, int y)
	{
		this.abscisse = x;
		this.ordonnee = y;
	}

	public int getAbscisse()
	{
		return this.abscisse;
	}

	public int getOrdonnee()
	{
		return this.ordonnee;
	}

	public void setAbscisse(int abscisse)
	{
		this.abscisse = abscisse;
	}

	public void setOrdonnee(int ordonnee)
	{
		this.ordonnee = ordonnee;
	}
}
