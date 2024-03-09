package Authentication;

import java.util.Scanner;



/**
 * Authentication
 */


public class Authentication {

    private UserType userType;

    public Authentication() {

    }

    public void authenticate() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your user type (ADMINISTRATOR/CLIENT): ");
            String input = scanner.nextLine();
            if (input.equals("ADMINISTRATOR")) {
                userType = UserType.ADMINISTRATOR;
            } else if (input.equals("CLIENT")) {
                userType = UserType.CLIENT;
            } else {
                System.out.println("Invalid user type");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
