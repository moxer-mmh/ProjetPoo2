package Client;

import Administration.*;
import java.util.Scanner;


public class Client {

    private Administrateur Administrateur;

    public Client() {
        this.Administrateur = new Administrateur();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Menu Client :");
            System.out.println("1. Demander une réservation");
            System.out.println("2. Modifier une réservation");
            System.out.println("3. Annuler une réservation");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Ignorer le saut de ligne

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    modifyReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 0:
                    System.out.println("Déconnexion du client.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }

        scanner.close();
    }

    private void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numéro de chambre : ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Ignorer le saut de ligne
        System.out.print("Date de début (jj/mm/aaaa) : ");
        String startDate = scanner.nextLine();
        System.out.print("Date de fin (jj/mm/aaaa) : ");
        String endDate = scanner.nextLine();
        Administrateur.requestReservation(roomNumber, startDate, endDate);
        scanner.close();
    }

    private void modifyReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID de réservation à modifier : ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Ignorer le saut de ligne
        System.out.print("Nouvelle date de début (jj/mm/aaaa) : ");
        String newStartDate = scanner.nextLine();
        System.out.print("Nouvelle date de fin (jj/mm/aaaa) : ");
        String newEndDate = scanner.nextLine();
        Administrateur.modifyReservation(reservationId, newStartDate, newEndDate);
        scanner.close();
    }

    private void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID de réservation à annuler : ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Ignorer le saut de ligne
        Administrateur.cancelReservation(reservationId);
        scanner.close();
    }




}




