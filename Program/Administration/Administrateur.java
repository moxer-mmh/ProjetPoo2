package Administration;

import Client.Client;
import java.util.Map;
import java.util.TreeMap;
/**
 * Administrateur
 */
public class Administrateur {


    private Map<Integer, Chambres> chambres = new TreeMap<>();
    private Map<Integer, Reservations> reservations = new TreeMap<>();
    private Map<Integer, Client> clients = new TreeMap<>();

    public Administrateur() {
    }

    public Map<Integer, Chambres> getChambres() {
        return chambres;
    }

    public void setChambres(Map<Integer, Chambres> chambres) {
        this.chambres = chambres;
    }

    public Map<Integer, Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Map<Integer, Reservations> reservations) {
        this.reservations = reservations;
    }

    public Map<Integer, Client> getClients() {
        return clients;
    }

    public void setClients(Map<Integer, Client> clients) {
        this.clients = clients;
    }


    //////////////////////////
}