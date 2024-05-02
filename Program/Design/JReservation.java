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

        // Remplir le modèle avec les données des réservations
     // Créer quelques réservations de test
     
		/*
		 * Chambres chambre1 = new Chambres(101, TypeChambre.SIMPLE); Client client1 =
		 * new Client( "Doe", "John", 1234567890); Reservations reservation1 = new
		 * Reservations(1, chambre1, "2023-05-01", "2023-05-05", client1);
		 * reservations.put(1, reservation1);
		 * 
		 * Chambres chambre2 = new Chambres(201, TypeChambre.DOUBLE); Client client2 =
		 * new Client( "Smith", "Jane", 7654321); Reservations reservation2 = new
		 * Reservations(2, chambre2, "2023-06-10", "2023-06-15", client2);
		 * reservations.put(2, reservation2);
		 */
        
        Administrateur adm=new Administrateur();
        
        for (Map.Entry<Integer, Reservations> entry : adm.reservations.entrySet()) {
            Reservations reservation = entry.getValue();
            Client client = reservation.getClient();
            String clientName = client.getNom() + " " + client.getPrenom();

     /*       // Créer les boutons d'action
            JButton acceptButton = new JButton("✓");
            JButton declineButton = new JButton("✗");

            // Créer un JPanel pour les boutons
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(acceptButton);
            buttonPanel.add(declineButton);

            // Ajouter les ActionListeners aux boutons (si nécessaire)
            acceptButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code à exécuter lorsque l'on clique sur "VRAI"
                    reservation.setEtat(EtatReservation.CONFIRMEE);
                    model.fireTableDataChanged(); // Actualiser le tableau
                }
            });

            declineButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code à exécuter lorsque l'on clique sur "X"
                    reservation.setEtat(EtatReservation.ANNULEE);
                    model.fireTableDataChanged(); // Actualiser le tableau
                }
            });*/

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