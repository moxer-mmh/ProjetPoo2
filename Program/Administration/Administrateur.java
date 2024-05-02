package Administration;

import java.util.*;
import Client.*;

public class Administrateur {
    public static Map<Integer, Chambres> chambres = new TreeMap<>();
    
    public static Map<Integer, Reservations> reservations = new TreeMap<>();
    private static Map<Integer, Client> clients = new TreeMap<>();

    public static void addRoom(int roomNumber, TypeChambre roomType) {
        if (chambres == null || !chambres.containsKey(roomNumber)) {
            chambres.put(roomNumber, new Chambres(roomNumber, roomType));
            System.out.println("Chambre ajoutée avec succès !");
        } else {
            System.out.println("La chambre spécifiée existe déjà.");
        }
    }

    public static void modifyRoom(int roomNumber, TypeChambre newRoomType) {
        if (chambres == null || !chambres.containsKey(roomNumber)) {
            System.out.println("La chambre spécifiée n'existe pas.");
        } else {
            chambres.get(roomNumber).setType(newRoomType);
            System.out.println("Chambre modifiée avec succès !");
        }
    }

    public static void deleteRoom(int roomNumber) {
        if (chambres == null || !chambres.containsKey(roomNumber)) {
            System.out.println("La chambre spécifiée n'existe pas.");
        } else {
            chambres.remove(roomNumber);
            System.out.println("Chambre supprimée avec succès !");
        }
    }

    public static void displayReservationRequests() {
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            Reservations reservation = entry.getValue();
            if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
                System.out.println("---------------------------------------------");
                System.out.println("ID de réservation : " + reservation.getId());
                System.out.println("Numéro de chambre : " + reservation.getChambre().getNumero());
                System.out.println("Date de début : " + reservation.getDateDebut());
                System.out.println("Date de fin : " + reservation.getDateFin());
                System.out.println("Nom du client : " + reservation.getClient().getNom());
                System.out.println("Prénom du client : " + reservation.getClient().getPrenom());
            }
        }
    }

    public static void displayReservationsByClient(Client client) {
        System.out.println("Réservations pour " + client.getNom() + " " + client.getPrenom() + " :");
        for (Map.Entry<Integer, Reservations> entry : reservations.entrySet()) {
            Reservations reservation = entry.getValue();
            if (reservation.getClient().getNom().equals(client.getNom()) && reservation.getClient().getPrenom().equals(client.getPrenom())) {
                System.out.println("---------------------------------------------");
                System.out.println("ID de réservation : " + reservation.getId());
                System.out.println("Numéro de chambre : " + reservation.getChambre().getNumero());
                System.out.println("Date de début : " + reservation.getDateDebut());
                System.out.println("Date de fin : " + reservation.getDateFin());
                System.out.println("Etat : " + reservation.getEtat());
            }
        }
    }

    public static void acceptReservation(int reservationId) {
        if (reservations == null || !reservations.containsKey(reservationId)) {
            System.out.println("La réservation spécifiée n'existe pas.");
        } else {
            Reservations reservation = reservations.get(reservationId);
            if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
                reservation.setEtat(EtatReservation.CONFIRMEE);
                reservation.getChambre().setEtatChambre(EtatChambres.RESERVEE);
                System.out.println("Réservation acceptée avec succès !");
            } else {
                System.out.println("La réservation spécifiée a déjà été traitée.");
            }
        }

    }

    public static void declineReservation(int reservationId) {

        if (reservations == null || !reservations.containsKey(reservationId)) {
            System.out.println("La réservation spécifiée n'existe pas.");
        } else {
            Reservations reservation = reservations.get(reservationId);
            if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
                reservation.setEtat(EtatReservation.ANNULEE);
                reservation.getChambre().setEtatChambre(EtatChambres.LIBRE);
                reservations.remove(reservationId);
                System.out.println("Réservation refusée avec succès !");
            } else {
                System.out.println("La réservation spécifiée a déjà été traitée.");
            }
        }

    }

    public static void requestReservation(TypeChambre TypeChambre, String startDate, String endDate , Client client) {
        if (chambres == null || chambres.isEmpty()) {
            System.out.println("Aucune chambre n'est disponible.");
        } else {
            boolean roomFound = false;
            for (Map.Entry<Integer, Chambres> entry : chambres.entrySet()) {
                Chambres chambre = entry.getValue();
                if (chambre.getType() == TypeChambre && chambre.getEtatChambre() == EtatChambres.LIBRE) {
                    roomFound = true;
                    Reservations reservation = new Reservations(generateReservationId(), chambre, startDate, endDate, client);
                    reservations.put(reservation.getId(), reservation);
                    chambre.setEtatChambre(EtatChambres.RESERVEE);
                    System.out.println("Réservation effectuée avec succès !");
                    break;
                }
            }
            if (!roomFound) {
                System.out.println("Aucune chambre de ce type n'est disponible.");
            }
        }
    }

    public static void modifyReservation(int reservationId, String newStartDate, String newEndDate) {

        if (reservations == null || !reservations.containsKey(reservationId)) {
            System.out.println("La réservation spécifiée n'existe pas.");
        } else {
            Reservations reservation = reservations.get(reservationId);
            if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
                if (!reservation.isBeforeStartDate(newStartDate)) {
                    reservation.setDateDebut(newStartDate);
                    reservation.setDateFin(newEndDate);
                    System.out.println("Réservation modifiée avec succès !");
                } else {
                    System.out.println("La date de début de la réservation est déjà passée.");
                }
            } else {
                System.out.println("La réservation spécifiée a déjà été traitée.");
            }
        }

    }

    public static void cancelReservation(int reservationId) {

        if (reservations == null || !reservations.containsKey(reservationId)) {
            System.out.println("La réservation spécifiée n'existe pas.");
        } else {
            Reservations reservation = reservations.get(reservationId);
            if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
                reservations.remove(reservationId);
                reservation.getChambre().setEtatChambre(EtatChambres.LIBRE);
                System.out.println("Réservation annulée avec succès !");
            } else {
                System.out.println("La réservation spécifiée a déjà été traitée.");
            }
        }

    }

    /*private static Reservations findReservationById(int reservationId) {

        if (reservations == null || !reservations.containsKey(reservationId)) {
            return null;
        } else {
            return reservations.get(reservationId);
        }

    }
*/

    private static int generateReservationId() {

        int reservationId = 1;
        if (reservations != null && !reservations.isEmpty()) {
            reservationId = reservations.size() + 1;
        }
        return reservationId;

    }

    public static void displayAvailableRoomsByType() {
        int simpleRooms = 0;
        int doubleRooms = 0;
        int suiteRooms = 0;
        for (Map.Entry<Integer, Chambres> entry : chambres.entrySet()) {
            Chambres chambre = entry.getValue();
            if (chambre.getEtatChambre() == EtatChambres.LIBRE) {
                switch (chambre.getType()) {
                    case SIMPLE:
                        simpleRooms++;
                        break;
                    case DOUBLE:
                        doubleRooms++;
                        break;
                    case SUITE:
                        suiteRooms++;
                        break;
                }
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("Nombre de chambres simples disponibles : " + simpleRooms);
        System.out.println("Nombre de chambres doubles disponibles : " + doubleRooms);
        System.out.println("Nombre de suites disponibles : " + suiteRooms);

    }

    public static boolean getverifclient(String nom , String prenom){
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            Client client = entry.getValue();
            if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {
                return true;
            }
        }
        return false;
    }

    public static int getclientid(String nom , String prenom){
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            Client client = entry.getValue();
            if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {
                return client.getId();
            }
        }
        return 0;
    }

    public static int getclientsmaxid(){
        int max = 0;
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            Client client = entry.getValue();
            if (client.getId() > max) {
                max = client.getId();
            }
        }
        return max;
    }

    public static void addClient(Client client){
        clients.put(client.getId(), client);
    }

    //getters and setters

    public static Map<Integer, Chambres> getChambres() {
        return chambres;
    }

    public static void setChambres(Map<Integer, Chambres> chambres) {
        Administrateur.chambres = chambres;
    }

    public static Map<Integer, Reservations> getReservations() {
        return reservations;
    }

    public static void setReservations(Map<Integer, Reservations> reservations) {
        Administrateur.reservations = reservations;
    }

    public static Map<Integer, Client> getClients() {
        return clients;
    }

    public static void setClients(Map<Integer, Client> clients) {
        Administrateur.clients = clients;
    }

}
