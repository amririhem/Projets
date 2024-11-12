package bibliotheque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biblio_accueil {	
	public Biblio_accueil(){
	
		JFrame frame = new JFrame("Accueil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JButton ajouter_livre = new JButton("Ajouter un Livre ");
        JButton consuler_livre = new JButton("Consulter le Catalogue des livres");
        JButton supprimer_et_en= new JButton("Consulter le Catalogue des Adh�rents");
        JButton statistiques_etud_ens = new JButton("Statistiques sur les Adh�rents les plus assidus");
        JButton rappelle = new JButton("Envoyer des Rappels de Retour");
        JButton Statistiques_livre = new JButton("Statistiques sur les livres les plus emprunt�s");
       

        frame.setLayout(new GridLayout(3,2));
        
        consuler_livre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ConsulterLivresBiblio();
            }
        });
        supprimer_et_en.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ConsulterAdherentsBiblio();
            }
        });
        statistiques_etud_ens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Statistiques_ens_etu();
            }
        });
        Statistiques_livre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Statistiques_livres();
            }
        });
        rappelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Rappelle();
            }
        });
        ajouter_livre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Ajouter_livre();
            }
        });
        frame.add(supprimer_et_en);
        frame.add(consuler_livre);
        frame.add(statistiques_etud_ens);      
        frame.add(Statistiques_livre);
        frame.add(ajouter_livre);
        frame.add(rappelle);               
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);                      
	}
}


