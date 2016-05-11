package jeu;

public class CaseEcurie extends Case
{

	private int depart;
	private int arrivee;
	
	public CaseEcurie(Coordonnees c)
	{
		super(c);
		this.depart = 4;
		this.arrivee = 4;
	}

	public int getDepart()
	{
		return this.depart;
	}

	public int getArrivee()
	{
		return this.arrivee;
	}

	private void setDepart(int depart) throws EcurieException
	{
		if (depart < 0)
			throw new EcurieException("Plus aucun cheval dans l'écurie");
		
		this.depart = depart;
	}

	private void setArrivee(int arrivee)
	{
		this.arrivee = arrivee;
	}
	
	public void sortirCheval() throws EcurieException
	{
		this.setDepart(this.depart - 1);
	}
	
	public void rentrerCheval()
	{
		this.setArrivee(this.arrivee + 1);
	}

}
