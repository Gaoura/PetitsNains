package gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jeu.Case;
import jeu.Coordonnees;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame
{
	
	private JTextField tfValeur;
	
	public FenetrePrincipale(String titre)
	{
		super(titre);
		
		ArrayList<String> lignes = new ArrayList<String>();
		
		/*File f = new File("src/jeu/damier.txt");
		System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath());
	    System.out.println("Nom du fichier : " + f.getName());
	    System.out.println("Est-ce qu'il existe ? " + f.exists());
	    System.out.println("Est-ce un répertoire ? " + f.isDirectory());
	    System.out.println("Est-ce un fichier ? " + f.isFile());
		System.exit(-1);*/
		
		try {
			Scanner sc = new Scanner (new FileReader("src/jeu/damier.txt"));
			
			while (sc.hasNextLine())
				lignes.add(sc.nextLine());
			//lignes.add(sc.nextLine());
			
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
		}	// avec ça on a un tableau plus simple a  manipuler
		
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
		
		int x = 0;
		int y = 0;
		boolean fini = false;
		
		// boucle pour trouver les dimensions d'une ecurie dans le damier 
		for (int i = 0; i < hauteur_tab; i++)
		{
			if (fini)
				break;
			
			for(int j = 0; j < hauteur_tab; j++)
				if (ligne[i][j].equals("E"))
				{
					j++;
					
					while(ligne[i][j].equals("V"))
					{
						y++;
						j++;
					}
					
					j--;
					i++;
					
					while(ligne[i][j].equals("V"))
					{
						x++;
						i++;
					}
					// on coupe la boucle puisque la recherche est terminee;
					fini = true;
					break;
				}		
		}	
		// maintenant on a : la taille d'une ecurie, une liste de position du coin superieur gauche de chaque ecurie
		// on va donc pouvoir faire les affichages adÃ©quats
		
		
		
		initFenetrePrincipale();
		initMenu();
		
		JPanel main_panel = new JPanel(new GridLayout(2, 1));
		JPanel panel_jeu = new JPanel(new GridBagLayout());
		
		//------------------------------------------------------------------------------------------------------------------
		// pique sur openclassrooms https://openclassrooms.com/courses/apprenez-a-programmer-en-java/positionner-des-boutons
		// Ã  modifier
		/*
	    //On cree nos differents conteneurs de couleurs differentes
	    JPanel cell1 = new JPanel();
	    cell1.setBackground(Color.YELLOW);

	    JPanel cell2 = new JPanel();
	    cell2.setBackground(Color.red);

	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.green);

	    JPanel cell4 = new JPanel();
	    cell4.setBackground(Color.black);

	    JPanel cell5 = new JPanel();
	    cell5.setBackground(Color.cyan);

	    JPanel cell6 = new JPanel();
	    cell6.setBackground(Color.BLUE);

	    JPanel cell7 = new JPanel();
	    cell7.setBackground(Color.orange);

	    JPanel cell8 = new JPanel();
	    cell8.setBackground(Color.DARK_GRAY);
	    
		 //L'objet servant Ã  positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    //On positionne la case de depart du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    panel_jeu.add(cell1, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    panel_jeu.add(cell2, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;		
	    panel_jeu.add(cell3, gbc);		
	    //---------------------------------------------
	    //Cette instruction informe le layout que c'est une fin de ligne
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 3;	
	    panel_jeu.add(cell4, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    //Celle-ci indique que la cellule se replique de facon verticale
	    gbc.fill = GridBagConstraints.VERTICAL;
	    panel_jeu.add(cell5, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridheight = 1;
	    //Celle-ci indique que la cellule se replique de facon horizontale
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panel_jeu.add(cell6, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridwidth = 2;
	    panel_jeu.add(cell7, gbc);
	    //---------------------------------------------
	    gbc.gridx = 3;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    panel_jeu.add(cell8, gbc);
	    //---------------------------------------------
	    */
	    //------------------------------------------------------------------------------------------------------------------
				
		JPanel panel_haut = new JPanel();
		JPanel panel_bas = new JPanel();
		
		this.add(main_panel);
		main_panel.add(panel_haut);
		main_panel.add(panel_bas);
		
		// code pris du tp4
		/*
		JLabel lValeur = new JLabel("x :");
		lValeur.setBounds(30, 20, 50, 50);	// on place et on dimensionne...
		panel_haut.add(lValeur);
		tfValeur = new JTextField("0", 20);
		tfValeur.setBounds(500, 100, 300, 80);	// on place et on dimensionne...
		panel_haut.add(tfValeur);		
		
		JButton[] boutons = new JButton[3];
		boutons[0] = new JButton("sqrt(x)");
		boutons[1] = new JButton("x^2");
		boutons[2] = new JButton("C");
		
		for (int i = 0; i < boutons.length; i++)
			panel_bas.add(boutons[i]);	
		
		
		boutons[0].addActionListener(new EcouteurRacine());
		boutons[1].addActionListener(new EcouteurCarre());
		boutons[2].addActionListener(new EcouteurRAZ());
		*/
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
	
	// code pris du tp4
	private void initMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Menu Fichier
		JMenu fichier = new JMenu("Fichier");
		menuBar.add(fichier);
		JMenuItem nouveau = new JMenuItem("Nouveau partie");
		fichier.add(nouveau);
		nouveau.addActionListener(new EcouteurNouvellePartie(this));
		
		JMenuItem quitter = new JMenuItem("Quitter");
		fichier.add(quitter);
		quitter.addActionListener(new EcouteurQuitter());
		
		this.setJMenuBar(menuBar);
	}
	
	// code pris du tp4
	class EcouteurRacine implements ActionListener
	{
		@Override
	    public void actionPerformed(ActionEvent e)
			{
				// On convertit le texte contenu dans le champ en double
				double valeur = Double.parseDouble(tfValeur.getText());
				
				// On calcule la sqrt et on place le rÃ©sultat dans le champ de saisie
				if (valeur > 0)
					tfValeur.setText("" + Math.sqrt(valeur));
				else
					tfValeur.setText("Error !");
	    }
	}
	
	// code pris du tp4
	class EcouteurCarre implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// On convertit le texte contenu dans le champ en double
			double valeur = Double.parseDouble(tfValeur.getText());
			
			// On calcule le carrÃ© et on place le rÃ©sultat dans le champ de saisie
			tfValeur.setText("" + valeur*valeur);
		}
	}
	
	// code pris du tp4
	class EcouteurRAZ implements ActionListener
	{
		@Override
	    public void actionPerformed(ActionEvent e)
			{
				// On remet Ã  zÃ©ro le champ de saisie
				tfValeur.setText("0");
	    }
	}
	
	// code pris du tp4
	class EcouteurQuitter implements ActionListener
	{
		@Override
	    public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
	    }
	}
	
	// code pris du tp4
	class EcouteurNouvellePartie implements ActionListener
	{
		private JFrame principale;
		
		public EcouteurNouvellePartie(JFrame princ)
		{
			this.principale = princ;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}
