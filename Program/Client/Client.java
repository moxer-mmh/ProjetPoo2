package Client;

import Administration.Reservations;
import java.util.Map;
import java.util.TreeMap;

public class Client {

    private int clientID;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private Map<Integer, Reservations> reservations = new TreeMap<>();

    public Client() {
    }

    public Client(int clientID, String nom, String prenom, String email, String telephone, String adresse) {
        this.clientID = clientID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Map<Integer, Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Map<Integer, Reservations> reservations) {
        this.reservations = reservations;
    }

    //////////////////////////

    public void demanderReservation(Reservations reservation) {
        reservations.put(reservation.getReservationID(), reservation);
    }


    @Override
    public String toString() {
        return "Client{" + "clientID=" + clientID + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", adresse=" + adresse + ", reservations=" + reservations + '}';
    }


}
