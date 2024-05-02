package Design;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import Administration.Administrateur;
import Administration.Chambres;
import Administration.EtatChambres;
import Administration.EtatReservation;
import Administration.Reservations;
import Administration.TypeChambre;
import Client.Client;
import javax.swing.JLayeredPane;
import javax.swing.ListSelectionModel;

public class JMenuClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField prenomC;
	private JTextField dateFin;
	private static JTextField nomC;
	private JTextField dateDebut;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	static JComboBox<TypeChambre> etatChambreC = new JComboBox<TypeChambre>();
	static int row = 1;
	static int selectedRow;
	
	static Administrateur r = new Administrateur();
	
	JPanel pReservation = new JPanel();
	
	JTable tableClient;
 	static DefaultTableModel model;
	private int selectedReservationId = -1; // ID de la réservation sélectionnée
	private EtatReservation selectedReservationEtat; // État de la réservation sélectionnée
	 private boolean isReservationListPanelVisible = false;

		    
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuClient frame = new JMenuClient(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JMenuClient(String nom, String prenom) {
		
		 model = new DefaultTableModel(
                 new Object[][] {}, 
                 new String[] {"ID", "Chambre", "Type", "Date Début", "Date Fin", "État"}
             );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 11, 228, 447);
		panel.setLayout(null);
		panel.setBackground(new Color(64, 128, 128));
		contentPane.add(panel);
		
		
		
		JButton fermer = new JButton("Retour");
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain f = new JMain();
				f.setVisible(true);
				dispose();

			}
		});
		fermer.setFont(new Font("Arial", Font.BOLD, 14));
		fermer.setBounds(45, 382, 142, 42);
		panel.add(fermer);

		
		pReservation.setBounds(300, 11, 564, 189);
		contentPane.add(pReservation);

		pReservation.setVisible(false);
		JButton btnReservation = new JButton("+ RESERVATION");
		btnReservation.setFont(new Font("Arial", Font.BOLD, 14));
		btnReservation.setBounds(20, 22, 197, 42);
		panel.add(btnReservation);
		pReservation.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(194, 11, 150, 120);
		panel_1_1.setLayout(null);
		pReservation.add(panel_1_1);

		prenomC = new JTextField();
		prenomC.setText(prenom);
		prenomC.setColumns(10);
		prenomC.setBounds(10, 20, 105, 25);
		panel_1_1.add(prenomC);
		
		
		dateFin = new JTextField();
		dateFin.setColumns(10);
		dateFin.setBounds(10, 49, 105, 25);
		panel_1_1.add(dateFin);

		etatChambreC.setModel(new DefaultComboBoxModel(TypeChambre.values()));
		etatChambreC.setBounds(10, 85, 105, 25);
		panel_1_1.add(etatChambreC);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(46, 11, 150, 120);
		panel_3.setLayout(null);
		pReservation.add(panel_3);

		nomC = new JTextField();
		nomC.setText(nom);
		nomC.setColumns(10);
		nomC.setBounds(10, 20, 108, 24);
		panel_3.add(nomC);

		dateDebut = new JTextField();
		dateDebut.setColumns(10);
		dateDebut.setBounds(10, 51, 108, 24);
		panel_3.add(dateDebut);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 86, 108, 24);
		panel_3.add(textField_4);

		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.setBounds(151, 131, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReservation(nom, prenom);

			}
		});
		pReservation.add(btnNewButton);
		
		JPanel pMesReserv = new JPanel();
		pMesReserv.setBounds(300, 211, 564, 247);
		contentPane.add(pMesReserv);
		pMesReserv.setLayout(null);
		 JButton btnModifier, btnAnnuler;
		 
		 pMesReserv.setVisible(false);
        
       
           
            // Créer le JTable avec le modèle de données
            tableClient = new JTable(model);
            tableClient.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            	}
            });
            tableClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           // pMesReserv.add(tableClient);)
            tableClient.setVisible(true);
	        getContentPane().setLayout(null);
	        
	        // Créer le JScrollPane avec le JTable
	        JScrollPane scrollPane = new JScrollPane(tableClient);
			/*
			 * scrollPane.addMouseListener(new MouseAdapter() {
			 * 
			 * @Override public void mouseClicked(MouseEvent e) { if (e.getClickCount() ==
			 * 2) { // Check for double click
			 * 
			 * selectedRow = tableClient.getSelectedRow(); // Assuming you want the value
			 * from the second column
			 * 
			 * // DefaultTableModel model = (DefaultTableModel) table.getModel(); String
			 * value1 = (String) ((TableModel) tableClient).getValueAt(selectedRow, 5);
			 * Object value2 = ((TableModel) tableClient).getValueAt(selectedRow, 6); Object
			 * value3 = ((TableModel) tableClient).getValueAt(selectedRow, 7);
			 * 
			 * 
			 * dateDebut.removeAll(); dateFin.removeAll(); if (selectedRow != -1) {
			 * dateDebut.setText((String) value2);
			 * 
			 * dateFin.setToolTipText((String) value3); // typeChambre.setModel(new
			 * DefaultComboBoxModel(TypeChambre.values()));
			 * 
			 * } else { dateDebut.setText(""); // Or display an error message } }
			 * 
			 * }
			 * 
			 * 
			 * } } });
			 */
	        scrollPane.setBounds(10, 11, 452, 225);
	        
	        pMesReserv.add(scrollPane);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBounds(465, 11, 92, 62);
	        pMesReserv.add(panel_1);
	        panel_1.setLayout(null);
	        btnAnnuler = new JButton("Annuler");
	        btnAnnuler.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		annulerReservation(nomC.getText(),prenomC.getText());}
	        });
	        btnAnnuler.setBounds(6, 34, 83, 23);
	        panel_1.add(btnAnnuler);
	       
	        btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnModifier = new JButton("Modifier");
	        btnModifier.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnModifier.setBounds(6, 4, 83, 23);
	        panel_1.add(btnModifier);	        
	        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
	        
	        
	        JButton reservClient = new JButton("Mes RESERVATION");
			reservClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					pMesReserv.setBounds(300, 50, 564, 247);
					contentPane.add(pMesReserv);
					
					 pMesReserv.setVisible(true); panel_1.setVisible(true);
					 remplirTableReservations(r.reservations,nom, prenom);
					 pReservation.setVisible(false);
					 			
				}
			});
			
			btnReservation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					pReservation.setVisible(true);
					Insets insets = pReservation.getInsets(); // Get insets for border padding
					pReservation.setBounds(300+ insets.left, 20 + insets.top, 800, 700); //
					panel_1.setVisible(false);
				  
				}
			});

			reservClient.setFont(new Font("Arial", Font.BOLD, 14));
			reservClient.setBounds(20, 195, 197, 42);
			panel.add(reservClient);
			
	}

	
	
	public void addReservation(String startDate, String endDate) {
		startDate= dateDebut.getText();
		endDate= dateFin.getText();

		JChambre c1 = new JChambre();
		Client client = new Client(nomC.getText(), prenomC.getText(), row);
	
	

		if (r.chambres == null ||r.chambres.isEmpty()) {
			System.out.println("Aucune chambre n'est disponible.");
		} else {

			for (Chambres rowList : r.chambres.values()) {

				rowList.getNumero();
				rowList.getType();
				rowList.getEtatChambre();

				row++;
			}

		
			boolean roomFound = false;
			for (Map.Entry<Integer, Chambres> entry : r.chambres.entrySet()) {
				Chambres chambre = entry.getValue();
				if (chambre.getType() == etatChambreC.getSelectedItem()
						&& chambre.getEtatChambre() == EtatChambres.LIBRE) {
					roomFound = true;
					Reservations reservation = new Reservations(row, chambre, startDate, endDate, client);
					r.reservations.put(row, reservation);
					chambre.setEtatChambre(EtatChambres.EN_ATTENTE);
					System.out.println("Réservation effectuée avec succès !");
					++row;
					break;
				}
			}
			if (!roomFound) {
				System.out.println("Aucune chambre de ce type n'est disponible.");

			}
		}
	}
	
	
	 
	    private int getRowFromReservationId(int reservationId,String nomClient, String prenomClient) {
	    	
		       
	        for (int row = 0; row < model.getRowCount(); row++) {
	            int id = (int) model.getValueAt(row, 0);
	            if (id == reservationId) {
	                return row;
	            }
	        }
	        return -1; // Aucune ligne trouvée pour cet ID de réservation
	    }

	    private void modifierReservation() {
	        // Code pour modifier la réservation sélectionnée
	        // ...
	    }

	    private void annulerReservation(String nomClient, String prenomClient) {
	    	
	    	//JMenuClient m=new JMenuClient(nomClient,prenomClient);
	        // Code pour annuler la réservation sélectionnée
	        Administrateur r = new Administrateur();

	        for (Map.Entry<Integer, Reservations> entry : r.reservations.entrySet()) {
	            Reservations reservation = entry.getValue();
	            Client client = reservation.getClient();

	            // Vérifier si la réservation appartient au client et correspond à l'ID sélectionné
	            if (client.getNom().equals(nomClient) && client.getPrenom().equals(prenomClient) && reservation.getId() == selectedReservationId) {
	                // Annuler la réservation en modifiant son état
	                reservation.setEtat(EtatReservation.ANNULEE);
	                reservation.getChambre().setEtatChambre(EtatChambres.LIBRE);
	                System.out.println("Réservation " + selectedReservationId + " annulée avec succès.");

	                // Mettre à jour la ligne correspondante dans la JTable
	                int row = getRowFromReservationId(selectedReservationId,nomClient, prenomClient);
	                if (row != -1) {
	                    model.setValueAt(reservation.getEtat(), row,5);
	                }
	                break;
	            }
	        }
	    }
	    
	    
	    
 private void remplirTableReservations(Map<Integer, Reservations> reservations, String nomClient, String prenomClient) {
	  
	  	  
	    	model.setRowCount(0);
	    	
	    	for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
	    		
	    		
	            Reservations reservation = entry.getValue();
	            Client client = reservation.getClient();

	            if (client.getNom().equals(nomClient) && client.getPrenom().equals(prenomClient)) {
	               
	                Object[] rowData = {
	                		reservation.getId(),
	                		reservation.getChambre().getNumero(),
	                		reservation.getChambre().getType(),
	                		reservation.getDateDebut(),
	                		reservation.getDateFin(),
	                		reservation.getEtat()
	                };
	                model.addRow(rowData);
	           
	            }
	           
	           
	        }
	    	        tableClient.addMouseListener(new MouseAdapter() {
	        	            @Override
	        	            public void mouseClicked(MouseEvent e) {
	        	                int row = tableClient.rowAtPoint(e.getPoint());
	        	                if (row >= 0) {
	        	                    selectedReservationId = (int) tableClient.getValueAt(row, 0); // Récupérer l'ID de la réservation
	        	                    selectedReservationEtat = (EtatReservation) tableClient.getValueAt(row, 5); // Récupérer l'état de la réservation
	        	                    System.out.println("Réservation sélectionnée : " + selectedReservationId + ", État : " + selectedReservationEtat);
	        	                    
	        	               
	        	                }
	        	            }
	        	        
	             });
	        
	        
	    }
}


