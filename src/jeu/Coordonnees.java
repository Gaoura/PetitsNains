package jeu;

public class Coordonnees
{
	private int abscisse;
	private int ordonnee;
	
	public Coordonnees(int x, int y)
	{
		this.setAbscisse(x);
		this.setOrdonnee(y);
	}

	public Coordonnees (Coordonnees c) {
		this.setAbscisse(c.getAbscisse());
		this.setOrdonnee(c.getOrdonnee());
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
		if (abscisse < 0)
			throw new IllegalArgumentException();
		
		this.abscisse = abscisse;
	}

	public void setOrdonnee(int ordonnee)
	{
		if (ordonnee < 0)
			throw new IllegalArgumentException();
		
		this.ordonnee = ordonnee;
	}
	
	public String toString() {
		return "" + this.getAbscisse() + "," + this.getOrdonnee();
	}
}
