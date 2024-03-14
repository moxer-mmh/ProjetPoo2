package Administration;


public class Reservations {

    private int id;
    private String nomClient;
    private String prenomClient;
    private Chambres chambre;
    private EtatReservation etat;
    private String dateDebut;
    private String dateFin;

    public Reservations(int id, Chambres chambre, String dateDebut, String dateFin, String nomClient, String prenomClient) {
        this.id = id;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
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

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }



}

