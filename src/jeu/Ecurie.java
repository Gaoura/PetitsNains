package jeu;

public class Ecurie extends Reserve
{
	// Nombre de chevaux pouvant encore entrer en jeu
	private int depart;
	// Nombre de chevaux a avoir fini leur tour de plateau
	private int arrivee;

	public Ecurie(int n)
  {
		this.setDepart(n);
		this.setArrivee(0);
	}

  /*/////////////////////////////////////////////////////////////////////////////////////////
              #####                               #     #####
             #     # ###### #####  ####          #     #     # ###### #####  ####
             #       #        #   #             #      #       #        #   #
             #  #### #####    #    ####        #        #####  #####    #    ####
             #     # #        #        #      #              # #        #        #
             #     # #        #   #    #     #         #     # #        #   #    #
              #####  ######   #    ####     #           #####  ######   #    ####
  *//////////////////////////////////////////////////////////////////////////////////////////

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
			throw new EcurieException("Plus aucun cheval dans l'ecurie");

		this.depart = depart;
	}

	private void setArrivee(int arrivee)
  {
		this.arrivee = arrivee;
	}

  /*/////////////////////////////////////////////////////////////////////////////////////////
                     #     #
                     ##   ## ###### ##### #    #  ####  #####  ######  ####
                     # # # # #        #   #    # #    # #    # #      #
                     #  #  # #####    #   ###### #    # #    # #####   ####
                     #     # #        #   #    # #    # #    # #           #
                     #     # #        #   #    # #    # #    # #      #    #
                     #     # ######   #   #    #  ####  #####  ######  ####
  *//////////////////////////////////////////////////////////////////////////////////////////

	// Fait revenir un cheval degage par un adversaire a l'ecurie de depart
	public void sortirCheval() throws EcurieException
	{
		this.setDepart(this.depart + 1);
	}

	// Fait revenir un cheval a l'ecurie d'arrivee apres qu'il ait fini son tour de plateau
	public void chevalArrivee()
	{
		this.setArrivee(this.arrivee + 1);
	}

	// Sortir une piece de l'ecurie
	@Override
	public void sortirPiece(Piece p) throws EcurieException
	{

		this.setDepart(this.depart - 1);
	}

	@Override
	public void rentrerPiece(Piece p)
  {
		// rentrer une piece dans l'ecurie
		// dans le jeu en fonction de l'exception attrapee, on fait la fonction qui lui est liee
		p.getProprietaire().getPieces().remove(p);
		((CaseNormale) p.getPos()).setOccupant(null);
	}

}
