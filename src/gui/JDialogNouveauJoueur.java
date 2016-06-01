package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class JDialogNouveauJoueur extends JFrame {
	public int nbJoueur;
	public String[] nomJoueurs;

	private JTextField[] reponses;
	
    public JDialogNouveauJoueur(String msg) {
		nombreJoueur();
		nomJoueurs = new String[this.getNbJoueur()];
		reponses = new JTextField[20];
		nomsJoueurs();
	}
    
    private void nomsJoueurs() {
    	JDialog d1 = new JDialog(this,"Noms de joueurs",true);
    	d1.setSize(400,200);
    	d1.setLayout(new GridLayout(this.getNbJoueur() + 1, 2));
    	for (int i = 1; i <= this.getNbJoueur(); i++) {
    		JPanel p = new JPanel();
    		p.setLayout(new FlowLayout());
    		JLabel question = new JLabel("Quel est le nom du joueur " + i + " ?");
    		reponses[i-1] = new JTextField(10);
    		p.add(question);
    		p.add(reponses[i-1]);
    		d1.add(p);
       	}
    	JButton valider = new JButton("Valider");
    	valider.addActionListener(new EcouteurNoms());
    	d1.add(valider);
    	d1.setVisible(true);
    }
    
    private JTextField[] getReponses() {
    	return this.reponses;
    }
    
    public String[] getNomJoueurs() {
    	return this.nomJoueurs;
    }
    
    private void nombreJoueur() {
        // A perfect constructor, mostly used.
        // A dialog with current frame as parent
        // a given title, and modal
    	JDialog d1 = new JDialog(this,"Nombre de joueurs",true);
        
        // Set size
        d1.setSize(400,150);
        
        // Set some layout
        d1.setLayout(new GridLayout(2,1));
        
        JPanel panel_haut = new JPanel();
		JPanel panel_bas = new JPanel();
		d1.add(panel_haut);
		d1.add(panel_bas);
		
 
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
        
        d1.setVisible(true);
    }
    
    public int getNbJoueur(){
    	return this.nbJoueur;
    } 
    
    public void setNbJoueur(int nb){
    	this.nbJoueur = nb;
    }
    
    class EcouteurJoueur implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object o = e.getSource();
			JButton b = null;
			if (o instanceof JButton) b = (JButton) o;
			setNbJoueur(Integer.parseInt(b.getText()));
			dispose();
		}
	}
    
    class EcouteurNoms implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < getNbJoueur(); i++)
				nomJoueurs[i] = new String(getReponses()[i].getText());
			dispose();
		}
	}
}