package Administration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Administrateur {
    private Map<Integer, Chambres> chambres;
    private List<Reservations> reservations;

    public Administrateur() {
        this.chambres = new HashMap<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(int roomNumber, TypeChambre roomType) {
        Chambres chambre = new Chambres(roomNumber, roomType);
        chambres.put(roomNumber, chambre);
    }

    public void modifyRoom(int roomNumber, TypeChambre newRoomType) {
        Chambres chambre = chambres.get(roomNumber);
        if (chambre != null) {
            chambre.setType(newRoomType);
            System.out.println("Chambre modifiée avec succès !");
        } else {
            System.out.println("La chambre spécifiée n'existe pas.");
        }
    }

    public void deleteRoom(int roomNumber) {
        Chambres chambre = chambres.get(roomNumber);
        if (chambre != null) {
            if (!chambre.estReservee()) {
                chambres.remove(roomNumber);
                System.out.println("Chambre supprimée avec succès !");
            } else {
                System.out.println("La chambre spécifiée est déjà réservée.");
            }
        } else {
            System.out.println("La chambre spécifiée n'existe pas.");
        }
    }

    public void displayReservationRequests() {
        for (Reservations reservation : reservations) {
            System.out.println("Demande de réservation ID : " + reservation.getId());
            System.out.println("Chambre : " + reservation.getChambre().getNumero());
            System.out.println("Date de début : " + reservation.getDateDebut());
            System.out.println("Date de fin : " + reservation.getDateFin());
            System.out.println("--------------------");
        }
    }

    public void acceptReservation(int reservationId) {
        Reservations reservation = findReservationById(reservationId);
        if (reservation != null) {
            Chambres chambre = reservation.getChambre();
            if (!chambre.estReservee()) {
                chambre.setEstReservee(true);
                System.out.println("Réservation acceptée avec succès !");
            } else {
                System.out.println("La chambre associée à cette réservation est déjà réservée.");
            }
        } else {
            System.out.println("La réservation spécifiée n'existe pas.");
        }
    }

    public void declineReservation(int reservationId) {
        Reservations reservation = findReservationById(reservationId);
        if (reservation != null) {
            reservations.remove(reservation);
            System.out.println("Réservation déclinée avec succès !");
        } else {
            System.out.println("La réservation spécifiée n'existe pas.");
        }
    }

    public void requestReservation(int roomNumber, String startDate, String endDate) {
        Chambres chambre = chambres.get(roomNumber);
        if (chambre != null && !chambre.estReservee()) {
            Reservations reservation = new Reservations(generateReservationId(), chambre, startDate, endDate);
            reservations.add(reservation);
            chambre.setEstReservee(true);
            System.out.println("Demande de réservation effectuée avec succès !");
        } else {
            System.out.println("La chambre spécifiée n'est pas disponible.");
        }
    }

    public void modifyReservation(int reservationId, String newStartDate, String newEndDate) {
        Reservations reservation = findReservationById(reservationId);
        if (reservation != null) {
            if (!reservation.isBeforeStartDate(newStartDate)) {
                reservation.setDateDebut(newStartDate);
                reservation.setDateFin(newEndDate);
                System.out.println("Réservation modifiée avec succès !");
            } else {
                System.out.println("La nouvelle date de début est antérieure à la date actuelle.");
            }
        } else {
            System.out.println("La réservation spécifiée n'existe pas.");
        }
    }

    public void cancelReservation(int reservationId) {
        Reservations reservation = findReservationById(reservationId);
        if (reservation != null) {
            if (!reservation.isBeforeStartDate()) {
                reservations.remove(reservation);
                reservation.getChambre().setEstReservee(false);
                System.out.println("Réservation annulée avec succès !");
            } else {
                System.out.println("La date de début de la réservation est déjà passée.");
            }
        } else {
            System.out.println("La réservation spécifiée n'existe pas.");
        }
    }

    private Reservations findReservationById(int reservationId) {
        for (Reservations reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    private int generateReservationId() {
        // Générer un nouvel ID de réservation unique
        // Vous pouvez utiliser une logique personnalisée pour générer l'ID
        // ou utiliser une bibliothèque externe pour générer des UUID
        return reservations.size() + 1;
    }

    public void displayAvailableRooms() {
        // Afficher les chambres disponibles
        // Cette méthode peut être statique car elle n'a pas besoin d'accéder à des champs d'instance

        for (Map.Entry<Integer, Chambres> entry : chambres.entrySet()) {
            Chambres chambre = entry.getValue();
            if (!chambre.estReservee()) {
                System.out.println("Numéro de chambre : " + chambre.getNumero());
                System.out.println("Type de chambre : " + chambre.getType());
                System.out.println("--------------------");
            }
        }
    }

}
