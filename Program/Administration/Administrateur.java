package Administration;

import java.util.Map;
import java.util.TreeMap;

public class Administrateur {
    private static Map<Integer, Chambres> chambres = new TreeMap<>();
    private static Map<Integer, Reservations> reservations = new TreeMap<>();

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
                System.out.println("ID de réservation : " + reservation.getId());
                System.out.println("Numéro de chambre : " + reservation.getChambre().getNumero());
                System.out.println("Date de début : " + reservation.getDateDebut());
                System.out.println("Date de fin : " + reservation.getDateFin());
                System.out.println("--------------------");
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
                reservation.getChambre().setEstReservee(true);
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
                System.out.println("Réservation refusée avec succès !");
            } else {
                System.out.println("La réservation spécifiée a déjà été traitée.");
            }
        }

    }

    public static void requestReservation(int roomNumber, String startDate, String endDate , String nom, String prenom) {

        if (chambres == null || !chambres.containsKey(roomNumber)) {
            System.out.println("La chambre spécifiée n'existe pas.");
        } else {
            Chambres chambre = chambres.get(roomNumber);
            if (!chambre.estReservee()) {
                int reservationId = generateReservationId();
                reservations.put(reservationId, new Reservations(reservationId, chambre, startDate, endDate, nom, prenom));
                System.out.println("Réservation demandée avec succès !");
            } else {
                System.out.println("La chambre spécifiée est déjà réservée.");
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

    public static void displayAvailableRooms() {
        for (Map.Entry<Integer, Chambres> entry : chambres.entrySet()) {
            Chambres chambre = entry.getValue();
            if (!chambre.estReservee()) {
                System.out.println("Numéro de chambre : " + chambre.getNumero());
                System.out.println("Type de chambre : " + chambre.getType());
            }
        }
    }

}
