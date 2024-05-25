package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Client;
import View.JAdmin;
import View.JResrvationClient;



public class CtrlClient {
	
public static int row;
	
public static void actionInitClient(DefaultTableModel model) 
	 {
	
	 for (Map.Entry<Integer, Client> entry : Client.clients.entrySet()) {
         Client client = entry.getValue();
        System.out.println( client.getNom());

         Object[] row = {
             client.getId(),
             client.getNom(),
             client.getPrenom()
         };
         model.addRow(row);
     }
		
	
}
	
 
 public static void actionRetour(JButton retour,JFrame frame) {
			
			retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JAdmin windowToBeClosed = new JAdmin();
					windowToBeClosed.setVisible(true);
				   frame.dispose();
					
				}
			});		
}
	
 public static void actionAddClient(JButton btnNewButton, JFrame frame, JTextField nomClient, JTextField prenomClient) {
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nom = nomClient.getText().trim();
	            String prenom = prenomClient.getText().trim();

	            // Vérifier si les champs sont vides
	            if (nom.isEmpty() || prenom.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Veuillez saisir votre nom et prénom.", "Erreur", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Vérifier si les champs contiennent uniquement des caractères alphabétiques
	            if (!nom.matches("[a-zA-Z]+") || !prenom.matches("[a-zA-Z]+")) {
	                JOptionPane.showMessageDialog(null, "Le nom et le prénom ne doivent contenir que des caractères alphabétiques.", "Erreur", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Vérifier si le client existe déjà dans la map
	            boolean clientExists = false;
	            for (Client c : Client.clients.values()) {
	                if (c.getNom().equals(nom) && c.getPrenom().equals(prenom)) {
	                    System.out.println("Le client existe déjà.");
	                    clientExists = true;
	                    JResrvationClient f = new JResrvationClient(nom, prenom);
	                    f.setVisible(true);
	                    frame.dispose();
	                    break; // Sortir de la boucle après avoir ouvert la fenêtre JMenuClient
	                }
	            }

	            // Si le client n'existe pas, créer un nouveau client
	            if (!clientExists) {
	                ++row;
	                Client client = new Client(nom, prenom, row);
	                Client.clients.put(row, client);
	                System.out.println("Nouveau client créé avec l'ID : " + row);
	                JResrvationClient f = new JResrvationClient(nom, prenom);
	                f.setVisible(true);
	                frame.dispose();
	            }
	        }
	    });
	}
	public static void listeClient() {
		for (@SuppressWarnings("unused") Client rowList : Client.clients.values()) {

		}
	}

		 
}


