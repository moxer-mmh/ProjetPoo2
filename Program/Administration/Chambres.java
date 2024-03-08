package Administration;
import Client.Client;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

enum TypeChambre {
    SIMPLE, DOUBLE, SUITE
}

public class Chambres {


    private int chambreID;
    private TypeChambre typeChambre;
    private boolean disponible;
    private Map<Integer, Reservations> reservations = new TreeMap<>();

    public Chambres() {
    }

    public Chambres(int chambreID, TypeChambre typeChambre, boolean disponible) {
        this.chambreID = chambreID;
        this.typeChambre = typeChambre;
        this.disponible = disponible;
    }

    public int getChambreID() {
        return chambreID;
    }

    public void setChambreID(int chambreID) {
        this.chambreID = chambreID;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Map<Integer, Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Map<Integer, Reservations> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Chambres{" + "chambreID=" + chambreID + ", typeChambre=" + typeChambre + ", disponible=" + disponible + ", reservations=" + reservations + '}';
    }

    public void addReservation(Reservations reservation) {
        reservations.put(reservation.getReservationID(), reservation);
    }

    public void removeReservation(int reservationID) {
        reservations.remove(reservationID);
    }

    public void displayReservations() {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void displayReservationsByClient(int clientID) {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            if (entry.getValue().getClient().getClientID() == clientID) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void displayReservationsByDate(String date) {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            if (entry.getValue().getDateDebut().equals(date) || entry.getValue().getDateFin().equals(date)) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void displayReservationsByType(TypeChambre type) {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            if (entry.getValue().getChambres().get(entry.getValue().getChambres().size() - 1).getTypeChambre() == type) {
                System.out.println(entry.getValue());
            }
        }
    }

}


