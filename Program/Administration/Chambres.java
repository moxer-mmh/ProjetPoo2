package Administration;
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

}


