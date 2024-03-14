package Client;

import Administration.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private String nom;
    private String prenom;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        try {
            while (choice != 0) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Menu client :");
                System.out.println("1. Faire une réservation");
                System.out.println("2. Modifier une réservation");
                System.out.println("3. Annuler une réservation");
                System.out.println("0. Retour au menu principal");
                System.out.print("Choix : ");


              //chofelna hna yacine kayen exception mahabatch troh
                choice = scanner.nextInt();

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
                        System.out.println("Retour au menu principal.");
                        return;
                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            }
        } catch (NoSuchElementException e) {
          System.out.println("Invalid input. Please enter a valid choice.");
        }

        scanner.close();
    }

    private void makeReservation() {
      Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("---------------------------------");
            System.out.println("Chambres disponibles :");
            Administrateur.displayAvailableRooms();

            System.out.println("---------------------------------");
            System.out.println("Veuillez entrer les informations de réservation :");

            System.out.print("Nom : ");
            this.nom = scanner.nextLine();

            System.out.print("Prénom : ");
            this.prenom = scanner.nextLine();

            System.out.print("Numéro de chambre : ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Date de début (jj/mm/aaaa) : ");
            String startDate = scanner.nextLine();

            System.out.print("Date de fin (jj/mm/aaaa) : ");
            String endDate = scanner.nextLine();

            Administrateur.requestReservation(roomNumber, startDate, endDate, this.nom, this.prenom);
            return;
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        

      scanner.close();

    }

    private void modifyReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------------");
        System.out.print("ID de réservation à modifier : ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nouvelle date de début (jj/mm/aaaa) : ");
        String newStartDate = scanner.nextLine();

        System.out.print("Nouvelle date de fin (jj/mm/aaaa) : ");
        String newEndDate = scanner.nextLine();

        Administrateur.modifyReservation(reservationId, newStartDate, newEndDate);
        scanner.close();
    }

    private void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------------");
        System.out.print("ID de réservation à annuler : ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        Administrateur.cancelReservation(reservationId);
        scanner.close();
    }

    /// Getters & Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}




