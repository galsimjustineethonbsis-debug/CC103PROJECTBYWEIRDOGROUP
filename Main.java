
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String username = "user", newUsername, loginUsername;
        String password = "1234", oldPassword, newPassword, loginPassword;
        boolean isLogin = false;
        int errCtr = 3;

        System.out.println("\nWelcome to our parking system! Please log in");
        do {
            //login 
            System.out.print("Enter Username: ");
            loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            loginPassword = input.nextLine();

            if (loginUsername.equals(username) && loginPassword.equals(password)) {
                isLogin = true;
            } else {
                errCtr--;
                System.out.print("Incorrect username or password. Please try again\n" + errCtr + " time/s left.\n");
                if (errCtr == 0) {
                    System.out.print("Too many attempts.\nExiting Program...");
                    System.exit(0);
                }
            }

        } while (!isLogin);

        System.out.println("Welcome!");

    } //main function
}//class
