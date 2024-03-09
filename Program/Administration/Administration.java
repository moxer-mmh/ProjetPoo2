package Administration;

import java.util.Scanner;

public class Administration {

    private Administrateur Administrateur;

    public Administration() {
        this.Administrateur = new Administrateur();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Menu Administrateur :");
            System.out.println("1. Gérer les chambres");
            System.out.println("2. Gérer les réservations");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageRooms();
                    break;
                case 2:
                    manageReservations();
                    break;
                case 0:
                    System.out.println("Déconnexion de l'administrateur.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }

        scanner.close();
    }

    private void manageRooms() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Gestion des chambres :");
            System.out.println("1. Ajouter une chambre");
            System.out.println("2. Modifier une chambre");
            System.out.println("3. Supprimer une chambre");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Numéro de chambre : ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();

                    TypeChambre.displayTypeChabmre();
                    System.out.print("Type de chambre : ");
                    TypeChambre roomType = TypeChambre.valueOf(scanner.nextLine());

                    Administrateur.addRoom(roomNumber, roomType);
                    System.out.println("Chambre ajoutée avec succès !");
                    break;
                case 2:
                    System.out.print("Numéro de chambre à modifier : ");
                    int roomNumberToModify = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nouveau type de chambre : ");
                    TypeChambre newRoomType = TypeChambre.valueOf(scanner.nextLine());

                    Administrateur.modifyRoom(roomNumberToModify, newRoomType);
                    System.out.println("Chambre modifiée avec succès !");
                    break;
                case 3:
                    System.out.print("Numéro de chambre à supprimer : ");
                    int roomNumberToDelete = scanner.nextInt();
                    scanner.nextLine();

                    Administrateur.deleteRoom(roomNumberToDelete);
                    System.out.println("Chambre supprimée avec succès !");
                    break;
                case 0:
                    System.out.println("Retour au menu administrateur.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }

        scanner.close();
    }

    private void manageReservations() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Gestion des réservations :");
            System.out.println("1. Afficher les demandes de réservation");
            System.out.println("2. Accepter une réservation");
            System.out.println("3. Décliner une réservation");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Administrateur.displayReservationRequests();
                    break;
                case 2:
                    System.out.print("ID de réservation à accepter : ");
                    int reservationIdToAccept = scanner.nextInt();
                    scanner.nextLine();

                    Administrateur.acceptReservation(reservationIdToAccept);
                    break;
                case 3:
                    System.out.print("ID de réservation à décliner : ");
                    int reservationIdToDecline = scanner.nextInt();
                    scanner.nextLine();

                    Administrateur.declineReservation(reservationIdToDecline);
                    break;
                case 0:
                    System.out.println("Retour au menu administrateur.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }

        scanner.close();
    }

}