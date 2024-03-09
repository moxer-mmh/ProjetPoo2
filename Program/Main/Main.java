package Main;


import java.util.Scanner;
import Administration.*;
import Client.*;





class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans l'application de gestion d'hôtel !");
        System.out.println("Veuillez vous authentifier :");

        // Vérifier les informations d'authentification
        System.out.println("1. Administrateur");
        System.out.println("2. Client");
        System.out.print("Choix : ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Ignorer le saut de ligne

        switch (roleChoice) {
            case 1:
                // L'utilisateur est un administrateur
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Authentification Administrateur :");
                System.out.print("Nom d'utilisateur : ");
                String username = scanner.nextLine();
                System.out.print("Mot de passe : ");
                String password = scanner.nextLine();
                if (authenticate(username, password)) {
                    System.out.println("Authentification réussie !");
                    System.out.println("-----------------------------------------------------------------");
                    Administration administration = new Administration();
                    administration.start();
                    break;
                }else{
                    System.out.println("Authentification échouée. Veuillez réessayer.");
                    break;
                }

            case 2:
                // L'utilisateur est un client
                System.out.println("-----------------------------------------------------------------");
                Client client = new Client();
                client.start();
                break;
            default:
                System.out.println("Rôle invalide.");
                break;
        }

        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        // Vérifier les informations d'authentification
        // Vous pouvez utiliser une base de données, un service d'authentification externe, etc.
        // Dans cet exemple, nous utilisons des informations d'identification codées en dur
        return username.equals("admin") && password.equals("admin");
    }
}





