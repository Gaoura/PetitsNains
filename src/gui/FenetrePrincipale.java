package gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jeu.Case;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame
{
	
	private JTextField tfValeur;
	private int nbJoueur;
	private JPanel main_panel;
	private String[] nomsJoueurs;
	
	public FenetrePrincipale(String titre)
	{
		super(titre);
		
		ArrayList<String> lignes = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/damier.txt"));
			
			while (sc.hasNextLine())
				lignes.add(sc.nextLine());
			
			sc.close();
		} catch (FileNotFoundException e) {
			    System.out.println ("Le fichier n'a pas été trouvé");
			    e.printStackTrace();
		}
		
		int longueur_tab = lignes.get(0).split(" ").length;
		int hauteur_tab = lignes.size();
	
		// on fait un tableau de dimensions :
		// - le nombre de caracteres sur la ligne 0
		// - le nombre de String dans la liste
		String[][] ligne = new String[15][15];
		Case[][] cases_plateau = new Case[longueur_tab][hauteur_tab];		
		
		for(int i = 0; i < hauteur_tab; i++)
		{
			String[] temp = lignes.get(i).split(" ");
			for(int j = 0; j < temp.length; j++) ligne[i][j] = temp[j];
		}
		
		// creation du jeu en fonction de ce qu'on obtient grace au fichier damier.txt
		// E est la premiere case d'une ecurie et V toutes ses autres cases
		// sauf une case V au centre du damier qui est une case inutile
		// N est une case normale
		
		for(int i = 0; i < hauteur_tab; i++)
			for(int j = 0; j < longueur_tab; j++)
			{				
				
				if (ligne[i][j].equals("V")) {
					cases_plateau[i][j] = null;
				} else {
					//on récupère le nom, on ajoute.png et on ouvre le fichier
					//cas spécial : si le nom commence par "marche"
					//extraire le numéro de la marche 
				}
			}
						/*// partie sale, on se fie aux coordonnees de la case pour savoir son numero de marche
						int num = 0;
						if (i == 7)
							if (j < 7)
								num = j;
							else
								num = hauteur_tab - j - 1;
						else
							if (i < 7)
								num = i;
							else
								num = hauteur_tab - i - 1;
						
						cases_plateau[i][j] = new CaseMarche(new Coordonnees(i, j), num);*/
		
		initFenetrePrincipale();
		initMenu();
		
		main_panel = new JPanel(new GridLayout(2, 1));
		JPanel panel_jeu = new JPanel(new GridBagLayout());
		
		JPanel panel_haut = new JPanel();
		JPanel panel_bas = new JPanel();
		
		//getMainPanel().add(new JLabel("Je suis une carotte"));
		
		this.add(this.getMainPanel());
		/*main_panel.add(panel_haut);
		main_panel.add(panel_bas);*/
		this.setVisible(true);
	}

	private void setNbJoueur(int nb) {
		this.nbJoueur = nb;
	}
	
	private int getNbJoueur() {
		return this.nbJoueur;
	}

	public void initNomsJoueurs (int r) {
		this.nomsJoueurs = new String[r];
	}
	
	public String[] getNomsJoueurs() {
		return nomsJoueurs;
	}

	public void setNomsJoueurs(String[] nomsJoueurs) {
		this.nomsJoueurs = nomsJoueurs;
	}

	private JPanel getMainPanel() {
		return this.main_panel;
	}
	
	private void initFenetrePrincipale() {
		Toolkit tk = Toolkit.getDefaultToolkit();    
		Dimension d = tk.getScreenSize();
		int hauteurEcran, largeurEcran, hauteurFenetre,	largeurFenetre,	xFenetre,	yFenetre;    
		hauteurEcran = d.height;	// on recupere la hauteur de l'ecran
		largeurEcran = d.width;		// on recupere la largeur de l'ecran
		hauteurFenetre = hauteurEcran/2;
		largeurFenetre = largeurEcran/2;	// la fenetre prend 1/4 de l'ecran
		xFenetre    =    largeurEcran/3;
		yFenetre    =    hauteurEcran/3;	// elle est placee a  1/3 du coin haut gauche
		this.setLocation(xFenetre,yFenetre); 
		this.setSize(largeurFenetre, hauteurFenetre);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Menu Fichier
		JMenu fichier = new JMenu("Fichier");
		menuBar.add(fichier);
		JMenuItem nouveau = new JMenuItem("Nouveau partie");
		fichier.add(nouveau);
		nouveau.addActionListener(new EcouteurNouvellePartie());
		
		JMenuItem quitter = new JMenuItem("Quitter");
		fichier.add(quitter);
		quitter.addActionListener(new EcouteurQuitter());
		
		this.setJMenuBar(menuBar);
	}
	
	class EcouteurQuitter implements ActionListener
	{
		@Override
	    public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
	    }
	}
	
	class EcouteurNouvellePartie implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Demande le nombre de joueurs
			JDialogNouveauJoueur d1 = new JDialogNouveauJoueur("nbJoueur");
			setNbJoueur(d1.getNbJoueur()); //Récupèration du nombre de joueurs
			initNomsJoueurs(getNbJoueur()); //Instanciation du tableau de noms de joueurs
			setNomsJoueurs(d1.getNomJoueurs()); //Remplissage du tableau de noms de joueurs
			
			//lancerJeu();
		}
	}
}
