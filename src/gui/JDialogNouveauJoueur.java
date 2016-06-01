package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class JDialogNouveauJoueur extends JFrame {
	JDialog d1;
	String nbJoueur;

    public JDialogNouveauJoueur() {
        initDialogue();
    }
    
    private void initDialogue() {
        // A perfect constructor, mostly used.
        // A dialog with current frame as parent
        // a given title, and modal
        d1 = new JDialog(this,"Nombre de joueurs",true);
        
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
    
    public String getNbJoueur(){
    	return this.nbJoueur;
    } 
    
    public void setNbJoueur(String str){
    	this.nbJoueur = str;
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