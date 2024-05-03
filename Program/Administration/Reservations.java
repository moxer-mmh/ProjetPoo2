package Administration;

import Client.Client;

public class Reservations {

    private int id;
    private Client client;
    private Chambres chambre;
    private EtatReservation etat;
    private String dateDebut;
    private String dateFin;

    public Reservations(int id, Chambres chambre, String dateDebut, String dateFin, Client client) {
        this.id = id;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.etat = EtatReservation.EN_ATTENTE;
    }

    /// Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chambres getChambre() {
        return chambre;
    }

    public void setChambre(Chambres chambre) {
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

}
