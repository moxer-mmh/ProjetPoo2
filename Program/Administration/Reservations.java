package Administration;


public class Reservations {

    private int id;
    private Chambres chambre;
    private String dateDebut;
    private String dateFin;

    public Reservations(int id, Chambres chambre, String dateDebut, String dateFin) {
        this.id = id;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

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

    public boolean isBeforeStartDate() {
        // Vérifier si la date de début est antérieure à la date actuelle
        // Retourner true si c'est le cas, false sinon
        // Vous pouvez utiliser une logique personnalisée pour comparer les dates
        return false;
    }

    public boolean isBeforeStartDate(String newStartDate) {
        // Vérifier si la nouvelle date de début est antérieure à la date actuelle
        // Retourner true si c'est le cas, false sinon
        // Vous pouvez utiliser une logique personnalisée pour comparer les dates
        return false;
    }

}

