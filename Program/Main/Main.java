package Main;


import java.util.Scanner;
import Administration.*;
import Client.*;





class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans l'application de gestion d'hôtel !");

        Administrateur.addRoom( 1, TypeChambre.SIMPLE);
        Administrateur.addRoom( 2, TypeChambre.DOUBLE);
        Administrateur.addRoom( 3, TypeChambre.SUITE);

        int roleChoice = -1;

        try {
                while (roleChoice != 0) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Veuillez vous authentifier :");

                System.out.println("1. Administrateur");
                System.out.println("2. Client");
                System.out.println("0. Quitter");
                System.out.print("Choix : ");

                roleChoice = scanner.nextInt();
                scanner.nextLine();

                switch (roleChoice) {
                    case 1:
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Authentification Administrateur :");

                        System.out.print("Nom d'utilisateur : ");
                        String username = scanner.nextLine();

                        System.out.print("Mot de passe : ");
                        String password = scanner.nextLine();

                        if (authenticate(username, password)) {
                            System.out.println("Authentification réussie !");
                            Administration administration = new Administration();
                            administration.start();
                            break;
                        }else{
                            System.out.println("Authentification échouée. Veuillez réessayer.");
                            break;
                        }

                    case 2:
                        Client client = new Client();
                        client.start();
                        break;
                    case 0:
                        System.out.println("Fermeture de l'application.");
                        break;
                    default:
                        System.out.println("Rôle invalide.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}





