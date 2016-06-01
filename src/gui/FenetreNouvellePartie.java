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
	
	private String couleur;
	private String nbJoueur;

	public FenetreNouvellePartie(JFrame princ)
	{
		super(princ, "Nouvelle partie", true);
		this.couleur = "Rouge"; // couleur par defaut
		
		JPanel main_panel = new JPanel();
		this.add(main_panel);
		JPanel panel_haut = new JPanel();
		JPanel panel_bas = new JPanel();
		main_panel.add(panel_haut);		
		main_panel.add(panel_bas);
		
		JLabel question = new JLabel("Combien de joueurs ?");
		question.setBounds(30, 20, 50, 50);
		panel_haut.add(question);
		
		JButton[] bouttonsReponse = new JButton[3];
		bouttonsReponse[0] = new JButton("2");
		bouttonsReponse[1] = new JButton("3");
		bouttonsReponse[2] = new JButton("4");
		
		for (int i = 0; i < bouttonsReponse.length; i++) {
			panel_bas.add(bouttonsReponse[i]);
			bouttonsReponse[i].addActionListener(new EcouteurJoueur());
		}
			
		System.out.println(this.nbJoueur);
		
		
		/*JButton[] boutons = new JButton[4];
		boutons[0] = new JButton("Jaune");
		boutons[1] = new JButton("Vert");
		boutons[2] = new JButton("Bleu");
		boutons[3] = new JButton("Rouge");
		
		JLabel lValeur = new JLabel("Choisissez votre couleur :");
		lValeur.setBounds(30, 20, 50, 50);	// on place et on dimensionne...
		panel_haut.add(lValeur);
		
		for (int i = 0; i < boutons.length; i++)
			panel_bas.add(boutons[i]);
		
		boutons[0].addActionListener(new EcouteurJaune());
		boutons[1].addActionListener(new EcouteurVert());
		boutons[2].addActionListener(new EcouteurBleu());
		boutons[3].addActionListener(new EcouteurRouge());*/
		
		this.setVisible(true);	
	}

	private void setCouleur(String str)
	{
		this.couleur = str;
	}
	
	private void setNbJoueur(String str) {
		this.nbJoueur = str;
	}

	
	class EcouteurJaune implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Jaune");
			dispose();
		}
	}
	
	class EcouteurVert implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Vert");
			dispose();
		}
	}
	
	class EcouteurBleu implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Bleu");
			dispose();
		}
	}
	
	class EcouteurRouge implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setCouleur("Rouge");
			dispose();
		}
	}
	
	class EcouteurJoueur implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object o = e.getSource();
			JButton b = null;
			if (o instanceof JButton) b = (JButton) o;
			setNbJoueur(b.getText());
			dispose();
		}
	}
}
