package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Chambre;
import Model.Client;
import Model.EtatChambres;
import Model.EtatReservation;
import Model.Reservation;
import View.JMain;

public class CtrlReservation {
	
private static int selectedReservationId = -1; 
private static EtatReservation selectedReservationEtat; 
	
	 public static void actionInitReservation(DefaultTableModel model) 
	 {
	 for (Map.Entry<Integer, Reservation> entry : Reservation.reservations.entrySet()) {
         Reservation reservation = entry.getValue();
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
		
	
}
	 
	 public static void actionSelectReservation(DefaultTableModel model,JTable table) {
		 
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
	         
	     
	     
	 }
	 
	 public static void actionAcceptReserv(JButton btnAcceptReserv ,DefaultTableModel model,JTable table) {
		 
		 btnAcceptReserv.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (selectedReservationId != -1) {
	                    Reservation reservation = Reservation.reservations.get(selectedReservationId);
	                    if (reservation != null) {
	                        reservation.setEtat(EtatReservation.CONFIRMEE); // Mettre à jour l'état de la réservation
	                        
	                        Chambre chambre = reservation.getChambre();
	                        chambre.setEtatChambre(EtatChambres.RESERVEE);
							 
	                        DefaultTableModel model = (DefaultTableModel) table.getModel();
	                        
	                        int row = getRowFromReservationId(selectedReservationId , model);
			                if (row != -1) {
			                	model.setValueAt(EtatReservation.CONFIRMEE, table.getSelectedRow(), 5); 
			                }
	                        
	                    }
	                }
	            }
	        });
	 }
	 
 public static void actionDeclineReserv(JButton btndeclineReserv ,DefaultTableModel model,JTable table) {
		 
	 btndeclineReserv.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 if (selectedReservationId != -1) {
	                     Reservation reservation = Reservation.reservations.get(selectedReservationId);
	                     if (reservation != null) {
	                         reservation.setEtat(EtatReservation.ANNULEE); // Mettre à jour l'état de la réservation
	                         
	                         Chambre chambre = reservation.getChambre();
	                         chambre.setEtatChambre(EtatChambres.LIBRE);
	                         
	                         DefaultTableModel model = (DefaultTableModel) table.getModel();
	                         int row = getRowFromReservationId(selectedReservationId , model);
				                if (row != -1) {
				                	model.setValueAt(EtatReservation.ANNULEE, table.getSelectedRow(), 5); 
				                }
	                     }
	                 }
	             }
	         });
	            
		 }

	 
	 private static int getRowFromReservationId(int reservationId,DefaultTableModel model) {
	    	
	       
	        for (int row = 0; row < model.getRowCount(); row++) {
	            int id = (int) model.getValueAt(row, 0);
	            if (id == reservationId) {
	                return row;
	            }
	        }
	        return -1; // Aucune ligne trouvée pour cet ID de réservation
	    }
	 
	 public static void actionRetour(JButton retour,JFrame frame) {
			
			retour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JMain windowToBeClosed = new JMain();
					windowToBeClosed.setVisible(true);
				   frame.dispose();
					
				}
			});		
						
					}
				

		 
}


