package Controller;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Chambre;
import Model.Client;
import Model.EtatChambres;
import Model.EtatReservation;
import Model.Reservation;
import Model.TypeChambre;
import View.JMain;

public class CtrlReservationClient {
	
	
	public static int row;
	private static int selectedReservationId = -1; 
	
	public static void action(JButton btnReservation,JPanel pReservation ) {
	
	  btnReservation.addActionListener(new ActionListener() { public void
	  actionPerformed(ActionEvent e) {
	  
	  pReservation.setVisible(true); 
	  Insets insets = pReservation.getInsets(); //
	  pReservation.setBounds(300+ insets.left, 20 + insets.top, 800, 700); //
	  
	  } });
	  
	}
	  
	  
	  public static void actionRetour(JButton retour,JFrame frame) {
			
		  retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					JMain f = new JMain();
					f.setVisible(true);
					frame.dispose();

				}
			});
	  }
	  
	  
		  public static void actionEnregistrer(JButton btnEnregistrer,String nom,String prenom) {
				
			  btnEnregistrer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//addReservation(nom, prenom);

					}
				});
	 
	}
		  
 public static void actionReservClient( JButton reservClient,JPanel pMesReserv,JPanel pReservation,JPanel contentPane,JPanel panel_1,String nom,
		 String prenom,DefaultTableModel model,JTable tableClient,int selectedReservationId,EtatReservation selectedReservationEtat) {	  
		  
	 reservClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					pMesReserv.setBounds(300, 50, 564, 247);
					contentPane.add(pMesReserv);
					
					 pMesReserv.setVisible(true); panel_1.setVisible(true);
					 remplirTableReservations(Reservation.reservations,nom, prenom, model,tableClient,selectedReservationId,
							 selectedReservationEtat);
					 pReservation.setVisible(false);
					 			
				}
			});

}
 

		 public static void actionAnnulResrv (JButton btnAnnuler,String nom,String prenom,
				 DefaultTableModel model,JTable tableResrvClient,EtatReservation selectedReservationEtat) 
		 {
			 
			 btnAnnuler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
			    	
		
				        for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {
				         
				            Client client =entry.getValue().getClient();
				            selectedReservationId= (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);
				          
				            // Vérifier si la réservation appartient au client et correspond à l'ID sélectionné
				           if (client.getNom().equals(nom) && client.getPrenom().equals(prenom) )
				        		   //&& entry.getValue().getClient().getId() == selectedReservationId) 
				            	 {
				        	   
				        	   Reservation reservation = Reservation.reservations.get(selectedReservationId);
				        	   reservation.setEtat(EtatReservation.ANNULEE);
				        		 entry.getValue().getChambre().setEtatChambre(EtatChambres.LIBRE);
				                System.out.println("Réservation " + selectedReservationId + " annulée avec succès.");

				                // Mettre à jour la ligne correspondante dans la JTable
				                int row = getRowFromReservationId(selectedReservationId ,nom, prenom, model);
				                if (row != -1) {
				                    model.setValueAt( EtatReservation.ANNULEE, row,5);
				                }
				                break;
				            }
				        }
					}
					});
				    
			 }
					
 
		 

		    private static int getRowFromReservationId(int reservationId,String nomClient, String prenomClient,DefaultTableModel model) {
		    	
			       
		        for (int row = 0; row < model.getRowCount(); row++) {
		            int id = (int) model.getValueAt(row, 0);
		            if (id == reservationId) {
		                return row;
		            }
		        }
		        return -1; // Aucune ligne trouvée pour cet ID de réservation
		    }

 
 
 
 public static void remplirTableReservations(Map<Integer, Reservation> reservations,String nom,String prenom,
		 DefaultTableModel model,JTable tableClient,int selectedReservationId,EtatReservation selectedReservationEtat) {
	 
 	model.setRowCount(0);
 	
 	for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
 		
 		
         Reservation reservation = entry.getValue();
         Client client = reservation.getClient();

         if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {
            
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
 	     
     
     
 }
 
 
 public static void makeReservation(JButton btnEnregistrer,String startDate, String endDate,JComboBox<TypeChambre> etatChambreC,String nom,String prenom) {
		

		
	
		btnEnregistrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Client client = new Client(nom, prenom, row);
		if (Chambre.chambres== null ||Chambre.chambres.isEmpty()) {
			System.out.println("Aucune chambre n'est disponible.");
		} else {

			for (Chambre rowList : Chambre.chambres.values()) {

				rowList.getNumero();
				rowList.getType();
				rowList.getEtatChambre();
				++row;
				
			}

	
			boolean roomFound = false;
			for (Map.Entry<Integer, Chambre> entry : Chambre.chambres.entrySet()) {
				Chambre chambre = entry.getValue();
				if (chambre.getType() == etatChambreC.getSelectedItem()
						&& chambre.getEtatChambre() == EtatChambres.LIBRE) {
					roomFound = true;
					
					
					Reservation r = new Reservation(row, chambre, startDate, endDate, client);
					Reservation.reservations.put(row, r);
					Reservation.setReservations(r.reservations);
					
					chambre.setEtatChambre(EtatChambres.EN_ATTENTE);
					System.out.println(row+"Réservation effectuée avec succès !");
					++row;
					break;
				}
			}
			if (!roomFound) {
				System.out.println("Aucune chambre de ce type n'est disponible.");

			}
		}
			}

 });
		}
 
 
 public static void actionNouvelleReserv(JButton btnReservation,JPanel pReservation,JPanel panel_1) {
		
	 btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); // Get insets for border padding
				pReservation.setBounds(300+ insets.left, 20 + insets.top, 800, 700); //
				panel_1.setVisible(false);
			  

			}
		});

}
	
	
}


