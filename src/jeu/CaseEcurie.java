package jeu;

public class CaseEcurie extends Case
{

	// Nombre de chevaux pouvant encore entrer en jeu 
	private int depart;
	// Nombre de chevaux � avoir fini leur tour de plateau
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
			throw new EcurieException("Plus aucun cheval dans l'�curie");
		
		this.depart = depart;
	}

	private void setArrivee(int arrivee)
	{
		this.arrivee = arrivee;
	}
	
	// Fait entrer un cheval en jeu
	public void sortirCheval() throws EcurieException
	{
		this.setDepart(this.depart - 1);
	}
	
	// Fait revenir un cheval d�gag� par un adversaire � l'�curie de d�part
	public void rentrerCheval() throws EcurieException
	{
		this.setDepart(this.depart + 1);
	}
	

	// Fait revenir un cheval � l'�curie d'arriv�e apr�s qu'il ait fini son tour de plateau
	public void chevalArrivee()
	{
		this.setArrivee(this.arrivee + 1);
	}
}
