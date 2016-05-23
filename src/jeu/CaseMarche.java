package jeu;

public class CaseMarche extends CaseOccupable
{
	// Valeur de la marche (1, ..., 6)
	private int valeur;
	
	public CaseMarche(Coordonnees c, int v)
	{
		super(c);
		this.setValeur(v);
	}

	public int getValeur()
	{
		return this.valeur;
	}

	private void setValeur(int valeur)
	{
		if (valeur < 0 || valeur > 6)
			throw new IllegalArgumentException();
		
		this.valeur = valeur;
	}

}
