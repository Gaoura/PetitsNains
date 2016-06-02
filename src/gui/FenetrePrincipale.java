package gui;

import jeu.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	
	private int nbJoueur;
	private String[] nomsJoueurs;
	private Jeu jeu = new Jeu();
	private JPanel panelJeu;
	private JPanel panelDiscussion;
	private JLabel lResultatTour;
	private JPanel panelChoixChevaux;
	private JPanel panelChoix;
	private JLabel[] lEcurie;
	private JLabel res;
	private int score;
	
	public FenetrePrincipale(String titre) {
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
		String[][] ligne = new String[longueur_tab][hauteur_tab];
		Case[][] cases_plateau = new Case[longueur_tab][hauteur_tab];	
		
		for(int i = 0; i < hauteur_tab; i++) {
			String[] temp = lignes.get(i).split(" ");
			for(int j = 0; j < temp.length; j++) ligne[i][j] = temp[j];
		}
		
		// creation du jeu en fonction de ce qu'on obtient grace au fichier damier.txt
		
		this.panelJeu = new JPanel(new GridBagLayout()); //Le pannel qui contiendra le plateau de jeu
		GridBagConstraints c = new GridBagConstraints(); //Position de la case dans le GridBagLayout
		
		for(int i = 0; i < hauteur_tab; i++) {
			for(int j = 0; j < longueur_tab; j++) {				
				if (ligne[i][j].equals("V")) {
					cases_plateau[i][j] = null;
				} else {
					//on récupère le nom, on ajoute.png et on ouvre le fichier
					//cas spécial : si le nom commence par "marche"
					//extraire le numéro de la marche 
					c.gridx = j;
					c.gridy = i;
					c.gridheight = 1;
					c.gridwidth = 1;
					String nomCase = ligne[i][j];
					
					if(nomCase.contains("marche")) { //Une marche a été trouvée
						int num = Integer.parseInt(nomCase.substring(nomCase.length()-1)); //extraction du numéro de la marche (c'est toujours le dernier caractère)
						cases_plateau[i][j] = new CaseMarche(new Coordonnees(i, j), num);
					} else if (nomCase.contains("ecurie")) { //Une écurie se trouve ici
						c.gridheight = 6;
						c.gridwidth = 6;
					} else { //C'est une case normale
						cases_plateau[i][j] = new CaseNormale(new Coordonnees(i, j));
					}
					
					Plateau p = new Plateau(cases_plateau);
					this.jeu.setPlateau(p);
					
					nomCase += ".png";
					Icon img = new ImageIcon("img/" + nomCase);
					JLabel picLabel = new JLabel(img);
					
					this.panelJeu.add(picLabel, c);
				}
			}
		}
		initFenetrePrincipale();
		initMenu();
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		
		this.panelDiscussion = new JPanel(); //Le panel d'interaction avec le joueur
		
		mainPanel.add(this.panelJeu);
		mainPanel.add(this.panelDiscussion);
		
		this.add(mainPanel);
		
		this.setVisible(true);
	}
	
	public void setResultatTour(String msg) {
		this.lResultatTour.setText(msg);
	}
	
	public void lancerJeu() {
		Ecurie[] e = new Ecurie[getNbJoueur()];
		this.lEcurie = new JLabel[getNbJoueur()];
		Joueur[] joueurs = new Joueur[getNbJoueur()];
		for (int i = 0; i < getNbJoueur(); i++) {
			try {
				e[i] = new Ecurie(4);
			} catch (EcurieException e1) { 
				e1.printStackTrace();
			}
			joueurs[i] = new Joueur(getNomsJoueurs()[i], i, e[i]);
			this.lEcurie[i] = new JLabel("Chevaux au départ : " + e[i].getDepart() + " / Chevaux à l'arrivé : " + e[i].getArrivee());
		}
		this.jeu.setJoueur(joueurs);
		
		initPanelDiscussion();
		
	}
	
	public void initPanelDiscussion() {
		this.panelDiscussion.setLayout(new GridLayout(3,1));
		JPanel panelBouton = new JPanel();
		this.panelDiscussion.add(panelBouton);
		panelBouton.setLayout(new FlowLayout());
		
		JPanel panelEcurie = new JPanel();
		this.panelDiscussion.add(panelEcurie);
		panelEcurie.setLayout(new GridLayout(getNbJoueur(), 1));
		for (int i = 0; i<getNbJoueur(); i++) panelEcurie.add(this.lEcurie[i]);
		
		JButton lancer = new JButton("Lancer le dé");
		lancer.addActionListener(new lancerDe());
		panelBouton.add(lancer);
		
		this.res = new JLabel("Résultat =");
		panelBouton.add(res);
		
		this.panelChoix = new JPanel();
		this.panelChoix.setLayout(new GridLayout(2,1));
		this.panelDiscussion.add(this.panelChoix);
		
		this.panelChoixChevaux = new JPanel();
		this.panelChoixChevaux.setLayout(new FlowLayout());
		this.panelChoix.add(this.panelChoixChevaux);
		
		JPanel panelResultatTour = new JPanel();
		this.lResultatTour = new JLabel();
		panelResultatTour.add(this.lResultatTour);
		panelResultatTour.setLayout(new FlowLayout());
		this.panelChoix.add(panelResultatTour);
		this.lResultatTour.setText("Veuillez lancer le dé");
		
		this.panelDiscussion.validate();
	}
	
	public String getLEcurie(int i) {
		return lEcurie[i].getText();
	}

	public void setLEcurie(int i, String str) {
		this.lEcurie[i].setText(str);
	}

	public String getResDe() {
		return res.getText();
	}

	public void setResDe(String str) {
		this.res.setText(str);
	}
	
	public String getResTour() {
		return lResultatTour.getText();
	}

	public void setResTour(String str) {
		this.lResultatTour.setText(str);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
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

	private void setNomsJoueurs(String[] nomsJoueurs) {
		this.nomsJoueurs = nomsJoueurs;
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
	
	private void initMenu() {
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
	
	public void demanderDeplacement() {
		
	}
	
	class EcouteurQuitter implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent e) {
			System.exit(0);
	    }
	}
	
	class EcouteurNouvellePartie implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Dialogue de récupération des informations
			JDialogNouveauJoueur d1 = new JDialogNouveauJoueur("nbJoueur");
			setNbJoueur(d1.getNbJoueur()); //Récupèration du nombre de joueurs
			initNomsJoueurs(getNbJoueur()); //Instanciation du tableau de noms de joueurs
			setNomsJoueurs(d1.getNomJoueurs()); //Remplissage du tableau de noms de joueurs 
			
			lancerJeu();
		}
	}
	
	class lancerDe implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setScore(jeu.lancerDe());
			setResDe("Résultat = " + getScore());
			setResultatTour("C'est au tour de " + getJeu().getJoueurCourant().getNomJoueur() + " de jouer.");

			if (getScore() == 6) {
				try {
					getJeu().sortirPiece();
					setResTour("Le cheval " + (getJeu().getJoueurCourant().size()) + " est placé à la case de départ");
					setLEcurie(getJeu().getNumJoueurCourant(), 
							"Chevaux au départ : " + ((Ecurie) getJeu().getJoueurCourant().getStock()).getDepart() + 
							" / Chevaux à l'arrivé : " + ((Ecurie) getJeu().getJoueurCourant().getStock()).getArrivee());
				} catch (CaseDepartDejaOccupeeException e1) {
					// Impossible de sortir une pièce, le joueur a déjà un cheval sur la case de sortie
					setResTour("Impossible de sortir le cheval : la case de départ est déjà occupée");
				} catch (ChevalException e1) {
					//Impossible de trouver le cheval
					demanderDeplacement();
					e1.printStackTrace();
				} catch (EcurieException e1) {
					//Plus de cheval au départ
					System.out.println("Ecurie vide");
				}
			} else {
				if (getJeu().getJoueurCourant().size() == 0) {
					getJeu().terminerTour(); //le joueur n'a pas de pièce sur le terrain
				} else { //le joueur a au moins une piece sur le terrain
					//déplacement
					getJeu().terminerTour();
				}
			}
		}
	}
}
