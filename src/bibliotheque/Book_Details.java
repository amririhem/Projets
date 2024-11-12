package bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book_Details {
	public Book_Details(int user_id,Livre livre) {
		JFrame frame = new JFrame("Afficher les Details d'un Livre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.getContentPane().setBackground(Color.PINK); 

        JLabel label1 = new JLabel("Titre:");
        JLabel label2 = new JLabel("Auteur:");
        JLabel label3 = new JLabel("Genre:");
        JLabel label4 = new JLabel("Disponibilit�:");

        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        
        frame.setLayout(new GridLayout(6,4));
        
        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(label3);
        frame.add(textField3);
        frame.add(label4);
        frame.add(textField4);
        JButton emprunt= new JButton("Emprunter");
        JButton reserver = new JButton("Reserver");
        JButton retour=new JButton("Catalogue");
        JButton annuler=new JButton("Annuler Reservation");
        
        textField1.setEditable(false);
	    textField2.setEditable(false);
	    textField3.setEditable(false);
	    textField4.setEditable(false);
	  
	    textField1.setText(livre.GetTitre());
	    textField2.setText(livre.GetAuteur());
	    textField3.setText(livre.GetGenre());
	    textField4.setText(livre.GetDispo());        
             
		if(livre.GetDispo().equals("disponible")) {
    		frame.add(new JLabel());
    		frame.add(new JLabel());
            frame.add(retour);
			frame.add(emprunt);		
        }else {
        	try {
        		if(Emprunt.emprunterPar(user_id,livre.GetId())) {
            		frame.add(new JLabel());
            		frame.add(new JLabel());
                    frame.add(retour);
            		frame.add(new JLabel("Vous �tes l'emprunteur de ce livre."));
            	}else if(Reservation.reserverPar(user_id,livre.GetId())) {
            		frame.add(new JLabel());
            		frame.add(new JLabel(" Votre demande de reservation est en cours"));
                    frame.add(retour);
            		frame.add(annuler);
            	}else {
            		frame.add(new JLabel());
            		frame.add(new JLabel());
                    frame.add(retour);
            		frame.add(reserver);
            	}	
        	}catch (MyException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
        	}        	       		                   	
        }
		emprunt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Emprunt emp=new Emprunt(user_id,livre.GetId());
                int choix = JOptionPane.showConfirmDialog(frame, "�tes-vous s�r de vouloir emprunter ce livre ?", "Demande de Confirmation d'emprunt de livre", JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) { 
	                try {
	                	emp.emprunterLivre();
	                    JOptionPane.showMessageDialog(frame, "Emprunt r�ussi !", "Confirmation d'emprunt de livre", JOptionPane.INFORMATION_MESSAGE);
	                    frame.dispose();
	                    livre.SetDispo("indisponible");//khater kif livre.dispo=disponible wa9t naawd nabaath el livre yab9a dispo
	                    new Book_Details(user_id,livre);
	                }catch (MyException ex){
	                    JOptionPane.showMessageDialog(frame, ex.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
	                }  
                }    
            }
        });
		reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int confirmation = JOptionPane.showConfirmDialog(frame, "Voulez-vous r�server ce livre ?", "Confirmation de r�servation de livre", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
	                Reservation res=new Reservation(user_id,livre.GetId());
	                
	                try {
	                	 res.reserverLivre();
	                     JOptionPane.showMessageDialog(frame, "R�servation r�ussie !", "Succ�s de la r�servation", JOptionPane.INFORMATION_MESSAGE);
	                     frame.dispose();
	                     new Book_Details(user_id,livre);
	                	
	                }catch (MyException ex) {
	                    JOptionPane.showMessageDialog(frame, ex.getMessage());
	                }    
                }    
            }
        });
		annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(frame, "Voulez-vous annuler cette r�servation ?", "Confirmation d'annulation de r�servation", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
	                Reservation res=new Reservation(user_id,livre.GetId());
	                try {
	                	 res.annulerReservation();
	                        JOptionPane.showMessageDialog(frame, "Annulation de r�servation r�ussie !", "Succ�s de l'annulation", JOptionPane.INFORMATION_MESSAGE);
	                     frame.dispose();
	                     new Book_Details(user_id,livre);
	                	
	                }catch (MyException ex) {
	                    JOptionPane.showMessageDialog(frame, ex.getMessage());
	                }   
                }    
            }
        });
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ConsulterCatalogue(user_id);				
			}
		});     
		
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);    
	}
    
}
