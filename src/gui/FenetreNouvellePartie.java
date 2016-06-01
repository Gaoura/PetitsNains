package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreNouvellePartie extends JDialog
{
	
	String couleur;
	
	public FenetreNouvellePartie(JFrame princ)
	{
		super(princ, "Nouvelle partie", true);
		this.couleur = "Rouge"; // couleur par défaut
		
		initFenetreNouvellePartie();
		
		JPanel main_panel = new JPanel();
		this.add(main_panel);
		JPanel panel_haut = new JPanel();
		JPanel panel_bas = new JPanel();
		main_panel.add(panel_haut);		
		main_panel.add(panel_bas);
		
		JLabel lValeur = new JLabel("Choisissez votre couleur :");
		lValeur.setBounds(30, 20, 50, 50);	// on place et on dimensionne...
		panel_haut.add(lValeur);
		
		
		JButton[] boutons = new JButton[4];
		boutons[0] = new JButton("Jaune");
		boutons[1] = new JButton("Vert");
		boutons[2] = new JButton("Bleu");
		boutons[3] = new JButton("Rouge");
		
		for (int i = 0; i < boutons.length; i++)
			panel_bas.add(boutons[i]);
		
		boutons[0].addActionListener(new EcouteurJaune());
		boutons[1].addActionListener(new EcouteurVert());
		boutons[2].addActionListener(new EcouteurBleu());
		boutons[3].addActionListener(new EcouteurRouge());
		
		this.setVisible(true);	
	}
	
	private void initFenetreNouvellePartie()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();    
		Dimension d = tk.getScreenSize();
		int hauteurEcran, largeurEcran,	xFenetre,	yFenetre;    
		hauteurEcran = d.height;	// on récupère la hauteur de l'écran
		largeurEcran = d.width;		// on récupère la largeur de l'écran
		xFenetre = largeurEcran/3;
		yFenetre = hauteurEcran/3;	// elle est placée à 1/3 du coin haut gauche
		this.setLocation(xFenetre,yFenetre);
		this.setSize(450, 150);  
	}

	private void setCouleur(String string)
	{
		this.couleur = string;
	}

	
	class EcouteurJaune implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Jaune");
		}
	}
	
	class EcouteurVert implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Vert");
		}
	}
	
	class EcouteurBleu implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Bleu");
		}
	}
	
	class EcouteurRouge implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Rouge");
		}
	}
}
