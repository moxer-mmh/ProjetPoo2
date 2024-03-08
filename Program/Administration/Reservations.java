package Administration;

import Client.Client;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Reservations {

    private int reservationID;
    private Client client;
    private String dateDebut;
    private String dateFin;
    private int nombrePersonnes;
    private Map<Integer, Chambres> chambres = new TreeMap<>();

    public Reservations() {
    }

    public Reservations(int reservationID, Client client, String dateDebut, String dateFin, int nombrePersonnes) {
        this.reservationID = reservationID;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePersonnes = nombrePersonnes;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public Map<Integer, Chambres> getChambres() {
        return chambres;
    }

    public void setChambres(Map<Integer, Chambres> chambres) {
        this.chambres = chambres;
    }

    @Override
    public String toString() {
        return "Reservations{" + "reservationID=" + reservationID + ", client=" + client + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nombrePersonnes=" + nombrePersonnes + ", chambres=" + chambres + '}';
    }


}

