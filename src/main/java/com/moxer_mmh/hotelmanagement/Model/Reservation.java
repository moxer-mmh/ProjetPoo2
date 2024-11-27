package Model;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Reservation {

    private int id;
    private Client client;
    private Chambre chambre;
    private EtatReservation etat;
    private String dateDebut;
    private String dateFin;

    public static Map<Integer, Reservation> reservations = new TreeMap<>();
    
    public static int row;

    public Reservation(int  id, Chambre chambre, String dateDebut, String dateFin, Client client2) {
    	this.id=id;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client2;
        this.etat = EtatReservation.EN_ATTENTE;
        
    }

    /// Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isBeforeStartDate(String newStartDate) {

        if (newStartDate.compareTo(this.dateDebut) < 0) {
            return true;
        } else {
            return false;

        }

    }

    public EtatReservation getEtat() {
        return etat;
    }

    public void setEtat(EtatReservation etat) {
        this.etat = etat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static Map<Integer, Reservation> getReservations() {
        return reservations;
    }

    public static void setReservations(Map<Integer, Reservation> newValue) {
        // Add validation logic here if needed
        reservations = newValue;
    }
    

    public static void acceptReservation(int selectedReservationId ,DefaultTableModel model) {
		 
	                if (selectedReservationId != -1) {
	                    Reservation reservation = Reservation.reservations.get(selectedReservationId);
	                    if (reservation != null) {
	                    	if  (reservation.getChambre().getEtatChambre()==EtatChambres.RESERVEE) {
	                    		
	                    		 reservation.setEtat(EtatReservation.ANNULEE);
	                    		
	                    		//JOptionPane.showMessageDialog(table, e);
	                    	}
	                    	else {
	                        reservation.setEtat(EtatReservation.CONFIRMEE); // Mettre à jour l'état de la réservation
	                        
	                        Chambre chambre = reservation.getChambre();
	                        chambre.setEtatChambre(EtatChambres.RESERVEE);
							 
	                      
	                    	}
	                        
	                    }
	                }
	          
	 }
    
    
    public static void declineReservation(int selectedReservationId ,DefaultTableModel model,JTable table) {

   	            	 if (selectedReservationId != -1) {
   	                     Reservation reservation = Reservation.reservations.get(selectedReservationId);
   	                     if (reservation != null) {
   	                         reservation.setEtat(EtatReservation.ANNULEE); // Mettre à jour l'état de la réservation
   	                         
   	                         Chambre chambre = reservation.getChambre();
   	                         chambre.setEtatChambre(EtatChambres.LIBRE);
   				                }
   	                     }
   	                 }

    

}
