package jeu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Jeu implements Tour
{

  // Liste des joueurs
	private Joueur[] joueur;
  // Plateau du jeu
	private Plateau plateau;
  // Joueur dont c'est le tour de jeu
	private int joueurCourant;
  // Liste des pivots pour le deplacement
	private Hashtable<Integer, String> ht;
  // Liste des pivots pour les mouvements de retour en arriere
	private Hashtable<Integer, String> htInv;

	public Jeu(Joueur[] j, Plateau p)
  {
    this.joueurCourant = 0;
    this.ht = new Hashtable<Integer, String>();
    this.htInv = new Hashtable<Integer, String>()
		this.setJoueur(j);
		this.setPlateau(p);
		this.setHt();
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

	public Hashtable<Integer, String> getHt ()
  {
		return this.ht;
	}

	private void setHt() {
		// Lis la liste des pivots stockes dans le fichier "mouvement.txt"
		//et range les donnees dans une hashtable
		String pos, posX, posY, dir;
		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/mouvement.txt"));
			while (sc.hasNextLine())
			{
				sc.nextLine();
				sc.findInLine("(\\w+),(\\w+):(\\w+)");
				MatchResult result = sc.match();
				posX = result.group(1);
				posY = result.group(2);
				dir = result.group(3);
				pos = posX + "," + posY;
				this.ht.put(pos.hashCode(), dir);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas ete trouve");
			    e.printStackTrace();
		}

		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/mouvementInv.txt"));
			while (sc.hasNextLine())
			{
				sc.nextLine();
				sc.findInLine("(\\w+),(\\w+):(\\w+)");
				MatchResult result = sc.match();
				posX = result.group(1);
				posY = result.group(2);
				dir = result.group(3);
				pos = posX + "," + posY;
				this.htInv.put(pos.hashCode(), dir);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas ete trouve");
			    e.printStackTrace();
		}
	}

	public Joueur[] getJoueur() {
		return this.joueur;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public int getNumJoueurCourant() {
		return this.joueurCourant;
	}

	public Joueur getJoueurCourant() {
		return this.getJoueur()[this.getNumJoueurCourant()];
	}

	public void setJoueurCourant(int numJoueur) {
		if (numJoueur < 0 || numJoueur >= this.getJoueur().length)
			throw new IllegalArgumentException();
		this.joueurCourant = numJoueur;
	}

	public void setJoueur(Joueur[] joueur) {
		this.joueur = joueur;
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

	@Override
	public void terminerTour() {
		//Arr�te un tour en cours et donne la main au suivant
		this.setJoueurCourant(((this.joueurCourant++) % this.getJoueur().length));
	}

	@Override
	public int lancerDe() {
		//Lance un d� dont le r�sultat sera compris entre
		//1 inclus et 7 exclus
		int min = 1;
		int max = 6;
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	@Override
	public void deplacerPiece(Piece p, int score) {
		int inv = 1;
		for (int i=0; i<score; i++) {
			int abs = 0, ord = 0;
			int hashedKey = p.getPos().getPosition().toString().hashCode();

			if (inv == 1)
				if (ht.containsKey(hashedKey))
					p.setDir(ht.get(hashedKey));
			else //Si on est en d�placement invers�
				if (htInv.containsKey(hashedKey))
					p.setDir(htInv.get(hashedKey));

			switch (p.getDir()) {
				case "d" :
					abs = 1;
					break;
				case "g" :
					abs = -1;
					break;
				case "h" :
					ord = 1;
					break;
				case "b" :
					ord = -1;
					break;
			}

      // Les coordonnees courantes de la piece
			Coordonnees caseSuivant = p.getPos().getPosition();
			Coordonnees temp = new Coordonnees(caseSuivant);

			temp.setAbscisse(temp.getAbscisse()+abs);
			temp.setOrdonnee(temp.getOrdonnee()+ord); //Acceder a la case suivante
			if ((((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(temp)).getOccupant() != null))
      {
					if (score - i != 1)
          {
						// Si le score est different de 1 et que
						// la case suivante a un occupant, on doit repartir dans l'autre sens
						inv *= -1;
						ord *= -1;
						abs *= -1;
						temp.setAbscisse(caseSuivant.getAbscisse()+abs);
						temp.setOrdonnee(caseSuivant.getOrdonnee()+ord); //Acceder a la case suivante
					}
          else
          {
						// sortir la piece (score == 1)
						// et prendre sa place
						CaseOccupable nvlEmplacement = ((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(temp));
						try
            {
              // Le cheval est retourne dans son ecurie
							((Ecurie) nvlEmplacement.getOccupant().getProprietaire().getStock()).sortirCheval();
						}
            // l'exception ne peut pas se produire puisqu'on ajoute un cheval
            catch (EcurieException e) {}

						this.getJoueurCourant().remove(nvlEmplacement.getOccupant());
						caseSuivant = nvlEmplacement.getPosition();
					}
			}
      else
				caseSuivant = temp;

      // La case courante devient vide
			((CaseOccupable) p.getPos()).setOccupant(null);
      // La piece change de case
			p.setPos(this.getPlateau().getCaseDepuisCoordonnees(caseSuivant));
      // La nouvelle case a son occupant
			((CaseOccupable) this.getPlateau().getCaseDepuisCoordonnees(caseSuivant)).setOccupant(p);
		}
	}

  public void sortirCheval()
  {
    // La position de depart est fonction du numero du joueur courant
  	// 0 = 0,6
  	// 1 = 8,0
  	// 2 = 14,8
  	// 3 = 6,14
  	Coordonnees c = null;
  	switch (this.getNumJoueurCourant())
    {
  		case 0 :
  			c = new Coordonnees(0,6);
  			break;
  		case 1 :
  			c = new Coordonnees(8,0);
  			break;
  		case 2 :
  			c = new Coordonnees(14,8);
  			break;
  		case 3 :
  			c = new Coordonnees(6,14);
  			break;
  	}

    // on cree la piece que l'on va sortir de l'ecurie, on sait que :
    // - son proprietaire est le joueur courant
    // - son nom est "cheval" + un numero fonction du nombre de cheval deja dans la liste du joueur
    // - sa case sera, si la pîece est ajoutee, la case de depart du joueur
    //    (sinon elle sera detruite de toute facon)
    // - les cases de depart des joueurs se trouvant sur des pivots, on recupere la direction
    //    de la piece directement dans la hashtable
    Piece p = new Piece(this.getJoueurCourant(),
                        "cheval" + (this.getJoueurCourant().size() + 1),
                        this.getPlateau().getCaseDepuisCoordonnees(c),
                        this.ht.get(c.toString().hashCode());

    // on regarde s'il y a un occupant sur la case de depart du joueur
  	Piece occupantDepart = ((CaseNormale) this.getPlateau().getCaseDepuisCoordonnees(c)).getOccupant();

    // s'il n'y en a pas
  	if (occupantDepart == null)
    {
      // on peut ajouter la piece au jeu
  		this.ajout(p);
    }
    // sinon s'il y a deja une piece
    else
      // et que le proprietaire de cette piece est different
      // du joueur voulant sortir sa piece de l'ecurie
      if (occupantDepart.getProprietaire() != this.getJoueurCourant())
      {
    		// Le cheval sur la case de depart retourne a son ecurie
    		this.rentrerPiece(occupantDepart);
        // puis on peut ajouter la piece du joueur courant au jeu
    		ajout(p);
  	  }
      // sinon si le proprietaire et le joueur courant sont identiques, il y a Exception
      else
  		  throw new CaseDepartDejaOccupeeException();
  }

  // Ajoute une piece au jeu :
  // - ajoute la piece a liste de pieces du joueur
  // - ajoute la piece a la case
  // - modifie l'ecurie du joueur
  public void ajout(Piece p) throws ChevalException, EcurieException
  {
    this.getJoueurCourant().add(p);
    p.getPos().

    /*
  	Piece piece = null;
  	for (Piece iterateurPiece : this.getJoueurCourant().getPieces()) {
  		if (iterateurPiece.getNom().equals(p.getNom())) {
  			piece = iterateurPiece;
  			break;
  		}
  	}

  	if (p == null) throw new ChevalException("Cheval introuvable");
  	this.getJoueurCourant().getStock().sortirPiece();
  	String nomCheval = "cheval" + (4-((Ecurie) this.getJoueurCourant().getStock()).getDepart());
  	String newDir = ht.get(p.getPos().getPosition().toString().hashCode());
  	this.getJoueurCourant().add(new Piece(this.getJoueurCourant(), nomCheval, piece.getPos(), newDir));
    */
  }
}
