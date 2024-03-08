package Administration;

import Client.Client;
import java.util.HashMap;
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

    public Administrateur(Map<Integer, Chambres> chambres, Map<Integer, Reservations> reservations, Map<Integer, Client> clients) {
        this.chambres = chambres;
        this.reservations = reservations;
        this.clients = clients;
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

    public void addChambre(Chambres chambre) {
        chambres.put(chambre.getChambreID(), chambre);
    }

    public void addReservation(Reservations reservation) {
        reservations.put(reservation.getReservationID(), reservation);
    }

    public void addClient(Client client) {
        clients.put(client.getClientID(), client);
    }

    public void removeChambre(int chambreID) {
        chambres.remove(chambreID);
    }

    public void removeReservation(int reservationID) {
        reservations.remove(reservationID);
    }

    public void removeClient(int clientID) {
        clients.remove(clientID);
    }

    public void updateChambre(Chambres chambre) {
        chambres.put(chambre.getChambreID(), chambre);
    }

    public void updateReservation(Reservations reservation) {
        reservations.put(reservation.getReservationID(), reservation);
    }

    public void updateClient(Client client) {
        clients.put(client.getClientID(), client);
    }

    public void afficherChambres() {
        for (Map.Entry<Integer, Chambres> entry : chambres.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void afficherReservations() {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void afficherClients() {
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}