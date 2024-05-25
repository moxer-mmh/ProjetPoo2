package Controller;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Client;
import Model.EtatReservation;
import Model.Reservation;
import Model.TypeChambre;
import View.JMain;

public class CtrlReservationClient {

	public static int row;
	private static int selectedReservationId = -1;
	

	
public static void actionMakeReservation(JButton btnEnregistrer, JComboBox<String>
	  startdayComboBox, JComboBox<String> startmonthComboBox, JComboBox<String>
	  startyearComboBox, JComboBox<String> enddayComboBox, JComboBox<String>
	  endmonthComboBox, JComboBox<String> endyearComboBox, JComboBox<TypeChambre>
	  etatChambreC, String nom, String prenom) {
	  
	  btnEnregistrer.addActionListener(new ActionListener() {
	  
	  public void actionPerformed(ActionEvent e) {
		  
Client.makeReservation( startdayComboBox.getSelectedItem().toString(),startmonthComboBox.getSelectedItem().toString(),startyearComboBox.getSelectedItem().toString(),
enddayComboBox.getSelectedItem().toString(),endmonthComboBox.getSelectedItem().toString(),endyearComboBox.getSelectedItem().toString(),
etatChambreC.getSelectedItem().toString(), nom,prenom);
		}
		});

	}

	

	public static void actionMesReservations(JButton reservClient, JPanel pMesReserv, JPanel pReservation,
			JPanel contentPane, JPanel panel_1, String nom,
			String prenom, DefaultTableModel model, JTable tableClient, int selectedReservationId,
			EtatReservation selectedReservationEtat) {

		reservClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				//reaffiche le panel caché
				pMesReserv.setBounds(300, 50, 564, 247);
				contentPane.add(pMesReserv);
				pMesReserv.setVisible(true);
				panel_1.setVisible(true);
				
				Client.mesReservations( nom, prenom, model);
			
			    pReservation.setVisible(false);

		}
	});

}
	

	public static void actionAnnulResrvation(JButton btnAnnuler, String nom, String prenom,
        DefaultTableModel model, JTable tableResrvClient, EtatReservation selectedReservationEtat) {

    btnAnnuler.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
        	/*  selectedReservationId = (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);
        	  String[] Datedebut = tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 3).toString().split("/");        	  
        	  Client.annulReservationOld(nom, prenom, model,selectedReservationId, Datedebut,selectedReservationEtat);
        	  */
        	  tableResrvClient.setModel(model);
     	    int selectedRow = tableResrvClient.getSelectedRow();
        	if (selectedRow != -1) {
        	   selectedReservationId = (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);        	   
			   Client.annulReservation( nom, prenom,selectedReservationId,selectedRow);
			   model.removeRow(selectedRow);
			   JOptionPane.showMessageDialog(null, "Réservation annulée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        	   }
        }
    });
}

	
	public static void actionModifyResrvation(JButton btnModifier, String nom, String prenom,
	        DefaultTableModel model, JTable tableResrvClient, EtatReservation selectedReservationEtat,
	        JButton btnReservation, JPanel pReservation, JPanel panel_1,JPanel pMesReserv,
	        JComboBox<String> startday,JComboBox<String> startmonth,JComboBox<String> startyear,JComboBox<String> endday,JComboBox<String> endmonth,JComboBox<String> endyear 
	        ,JButton btnEnregistrer ,JButton btnModif2,JComboBox<TypeChambre>  etatChambreC ) {

		btnModifier.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	tableResrvClient.setModel(model);
	     	    int selectedRow = tableResrvClient.getSelectedRow();
	        	
	     	    if (selectedRow != -1) {
	        	  	
	        	
	        	pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); // Get insets for border padding
				pReservation.setBounds(300 + insets.left, 20 + insets.top, 800, 700); //
				panel_1.setVisible(false);
				pMesReserv.setVisible(false);
				btnEnregistrer.setVisible(false);
				btnModif2.setVisible(true);
				etatChambreC.setVisible(false);
				
				
				   selectedReservationId = (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);  
		        	   Reservation reservation = Reservation.reservations.get(selectedReservationId);
		        	 
		        	   startday.setSelectedItem(reservation.getDateDebut().toString().substring(0, 2));
		        	   startmonth.setSelectedItem(reservation.getDateDebut().toString().substring(3, 5)); 
		        	   startyear.setSelectedItem(reservation.getDateDebut().toString().substring(6, 10)); 

		        	   endday.setSelectedItem(reservation.getDateFin().toString().substring(0, 2));
		        	   endmonth.setSelectedItem(reservation.getDateFin().toString().substring(3, 5)); 
		        	   endyear.setSelectedItem(reservation.getDateFin().toString().substring(6, 10)); 
		        	   
		        	  
		        	   
					   Client.modifReservation( nom, prenom,selectedReservationId,selectedRow);
					//   model.removeRow(selectedRow);
		        	   }
	        	}
			
 		         
	        
	    });
	}

 

	public static void actionVisiblePanelReserv(JButton btnReservation, JPanel pReservation, JPanel panel_1,JPanel pMesReserv,
			JButton btnModif2,JButton btnEnregistrer,JComboBox<TypeChambre>  etatChambreC ) {

		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); // Get insets for border padding
				pReservation.setBounds(300 + insets.left, 20 + insets.top, 800, 700); //
				panel_1.setVisible(false);
				pMesReserv.setVisible(false);
				btnEnregistrer.setVisible(true);
				btnModif2.setVisible(false);
				etatChambreC.setVisible(true);
			}
		});

	}
	
	public static void actionRetour(JButton retour, JFrame frame) {

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain f = new JMain();
				f.setVisible(true);
				frame.dispose();

			}
		});
	}

	
	public static void actionPopModif(JTextField textField, JComponent[] inputs ) {
        // Show the pop-up window
        int result = JOptionPane.showConfirmDialog(null, inputs, "Enter Name", JOptionPane.PLAIN_MESSAGE);

        // Check if the user clicked OK
        if (result == JOptionPane.OK_OPTION) {
            // Get the text entered by the user
            String name = textField.getText();
            JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
        }
  	 
  	  
  	  
	}
	
	public static void actionModify2Resrvation(JButton btnModif2, String nom, String prenom,
	        DefaultTableModel model, JTable tableResrvClient, EtatReservation selectedReservationEtat,
	        JButton btnReservation, JPanel pReservation, JPanel panel_1,JPanel pMesReserv,
	        JComboBox<String> startday,JComboBox<String> startmonth,JComboBox<String> startyear,JComboBox<String> endday,JComboBox<String> endmonth,JComboBox<String> endyear ) {

		btnModif2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	      
	        	pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); // Get insets for border padding
				pReservation.setBounds(300 + insets.left, 20 + insets.top, 800, 700); //
				panel_1.setVisible(false);
				pMesReserv.setVisible(false);
				
				String startDate = startday.getSelectedItem().toString()+ "/" + startmonth.getSelectedItem().toString() + "/" + startyear.getSelectedItem().toString(); 
				String endDate =  endday.getSelectedItem().toString() + "/" + endmonth.getSelectedItem().toString()+ "/" +  endyear.getSelectedItem().toString();
				  
				
				try { Client.validateDates(startDate, endDate); } catch (InvalidDateException e1) {
					  JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur",
					  JOptionPane.ERROR_MESSAGE); return;
				} 
				
				 Reservation reservation = Reservation.reservations.get(selectedReservationId);
				 
				 reservation.setDateDebut(startday.getSelectedItem().toString()+"/"+startmonth.getSelectedItem().toString()+"/"+startyear.getSelectedItem().toString());
				 reservation.setDateFin(endday.getSelectedItem().toString()+"/"+endmonth.getSelectedItem().toString()+"/"+endyear.getSelectedItem().toString());
	        		
				
			 	   JOptionPane.showMessageDialog(null, "Réservation modifiée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		        	  
	        	
	        	
		        	}  
	       
	        
	    });
	}


	
}


