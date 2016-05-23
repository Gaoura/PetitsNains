package jeu;

public class CaseEcurie extends Case
{

	// Nombre de chevaux pouvant encore entrer en jeu 
	private int depart;
	// Nombre de chevaux à avoir fini leur tour de plateau
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
	
	// Fait entrer un cheval en jeu
	public void sortirCheval() throws EcurieException
	{
		this.setDepart(this.depart - 1);
	}
	
	// Fait revenir un cheval dégagé par un adversaire à l'écurie de départ
	public void rentrerCheval() throws EcurieException
	{
		this.setDepart(this.depart + 1);
	}
	

	// Fait revenir un cheval à l'écurie d'arrivée après qu'il ait fini son tour de plateau
	public void chevalArrivee()
	{
		this.setArrivee(this.arrivee + 1);
	}
}
