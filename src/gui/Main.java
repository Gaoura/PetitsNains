package gui;

import jeu.Ecurie;
import jeu.Jeu;
import jeu.Joueur;
import jeu.Plateau;

public class Main
{
    public static void main(String[] args)
    {        
        //FenetrePrincipale fenetrePrincipale = new FenetrePrincipale("Jeu des petits chevaux");
        //fenetrePrincipale.setVisible(true);
    
        Joueur[] joueurs = new Joueur[4];
        for(int i = 0; i < 4; i++)
            joueurs[i] = new Joueur("Joueur" + i, i, new Ecurie());
        
        //Piece cheval1 = new Piece(joueurs[0], "cheval1", ,)
        /*
        Jeu jeu = new Jeu(null, new Plateau(cases_plateau));*/
        Jeu j = new Jeu(joueurs, null);
        System.out.println("OUI");
        System.out.println(j.getJoueurCourant().getNomJoueur() + "  " + j.getJoueurCourant().getNumeroJoueur() + "  " + j.getNumJoueurCourant());
        System.out.println("NON");
        //System.exit(-1);
        j.terminerTour();
        System.out.println(j.getJoueurCourant().getNomJoueur() + "  " + j.getJoueurCourant().getNumeroJoueur() + "  " + j.getNumJoueurCourant());
        System.out.println("OUI");
        int[] tab = new int[6];
        for (int i = 0; i < 6; i++)
            tab[i] = 0;
        for(int i = 0; i < 10000; i++)
        {
            int k = j.lancerDe();
        
            tab[k-1] = tab[k-1] + 1;
            //if (k < 1 || k > 6)
                //System.out.println("NAN MAIS T'ES SERIEUX" + k);
            
        }
        for (int i = 0; i < 6; i++)
            System.out.println("Fréquence de " + (i+1) + ":" + tab[i]);
        
    }

}