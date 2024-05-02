package Design;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Administration.Administrateur;
import Administration.Chambres;
import Administration.EtatChambres;
import Administration.EtatReservation;
import Administration.Reservations;
import Administration.TypeChambre;
import Client.Client;

public class JReservation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
     static  JTable table;
    private int selectedReservationId = -1; // ID de la réservation sélectionnée
    private EtatReservation selectedReservationEtat; // État de la réservation sélectionnée
    

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JReservation frame = new JReservation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JReservation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 905, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Créer un modèle de données pour le JTable
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] { }, // Données initiales
            new String[] { "ID", "Client", "Chambre", "Date Début", "Date Fin", "État"} // Noms des colonnes
        );

        
        Administrateur adm=new Administrateur();
        
        Map<Integer, Chambres> chambres = adm.chambres;
        
        for (Map.Entry<Integer, Reservations> entry : adm.reservations.entrySet()) {
            Reservations reservation = entry.getValue();
            Client client = reservation.getClient();
            String clientName = client.getNom() + " " + client.getPrenom();


            Object[] row = {
                reservation.getId(),
                clientName,
                reservation.getChambre().getNumero(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getEtat(),
            };
            model.addRow(row);
        }

        // Créer le JTable avec le modèle de données
        table = new JTable(model);
        
        
        
    
        // Créer le JScrollPane avec le JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 495); // Ajustez les dimensions et la position selon vos besoins

        // Ajouter le JScrollPane au JPanel principal
        contentPane.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            		int row = table.rowAtPoint(e.getPoint());
            		if (row >= 0) {
            			selectedReservationId = (int) table.getValueAt(row, 0); // Récupérer l'ID de la réservation
            			selectedReservationEtat = (EtatReservation) table.getValueAt(row, 5); // Récupérer l'état de la réservation
            			System.out.println("Réservation sélectionnée : " + selectedReservationId + ", État : " + selectedReservationEtat);
                }
            }
            
        });
        
        
        
        
        JButton retour = new JButton("RETOUR");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAdmin f=new JAdmin();
				f.setVisible(true);
				dispose();
				
			}
		});
		int buttonWidth = 100;
	    int buttonHeight = 30;
	    int buttonX = (1400- buttonWidth) / 2; 
	    int buttonY = 600 - buttonHeight -45;
	    retour.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
	    contentPane.add(retour);
	    
	    JButton btnNewButton = new JButton("ACCEPTER");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedReservationId != -1) {
                    Reservations reservation = adm.reservations.get(selectedReservationId);
                    if (reservation != null) {
                        reservation.setEtat(EtatReservation.CONFIRMEE); // Mettre à jour l'état de la réservation
                        
                        Chambres chambre = reservation.getChambre();
                        chambre.setEtatChambre(EtatChambres.RESERVEE);
						 
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setValueAt(EtatReservation.CONFIRMEE, table.getSelectedRow(), 5); // Mettre à jour la table
                    }
                }
            }
        });
        btnNewButton.setBounds(780, 40, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("DECLINER");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedReservationId != -1) {
                    Reservations reservation = adm.reservations.get(selectedReservationId);
                    if (reservation != null) {
                        reservation.setEtat(EtatReservation.ANNULEE); // Mettre à jour l'état de la réservation
                        
                        Chambres chambre = reservation.getChambre();
                        chambre.setEtatChambre(EtatChambres.LIBRE);
                        
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setValueAt(EtatReservation.ANNULEE, table.getSelectedRow(), 5); // Mettre à jour la table
                    }
                }
            }
        });
        btnNewButton_1.setBounds(780, 74, 89, 23);
        contentPane.add(btnNewButton_1);
    }
}