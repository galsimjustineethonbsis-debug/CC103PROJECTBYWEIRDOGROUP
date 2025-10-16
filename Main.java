
import java.util.*;

public class Main {
    static String vehicleType,plateNum;
    static int carCount=0,motorCount=0,TruckCount=0;
    static String timeIn, timeOut;
    static int timeInHr,timeInMin,timeOutHr,timeOutMin;
    

    public static void main(String[] args) {
            //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String username = "user", newUsername, loginUsername;
        String password = "1234", oldPassword, newPassword, loginPassword;
        String border = "----------------------------------------";

        int menuChoice;

        boolean isLogin = false;
        int errCtr = 3;

        System.out.println("\nWelcome to the BULSU CICT Parking fee System!\nPlease log in.");
        do {
            //login 
            System.out.print("\nEnter Username: ");
            loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            loginPassword = input.nextLine();

            if (loginUsername.equals(username) && loginPassword.equals(password)) {
                isLogin = true;
            } else {
                errCtr--;
                System.out.print(border + "\nIncorrect username or password. Please try again\n" + errCtr + " time(s) left.\n" + border);
                if (errCtr == 0) {
                    System.out.print("\nToo many attempts.\nExiting Program...");
                    System.exit(0);
                }
            }

        } while (!isLogin);
        
        while(isLogin){
            System.out.println("\nWelcome to the BULSU CICT Parking fee System, " + username +"!");
            System.out.println(border + "\nMAIN MENU\n" + border);            
            
            System.out.println("[1] Add vehicle record");
            System.out.println("[2] View Summary report");
            System.out.println("[3] Change login info");
            System.out.println("[4] About us");
            System.out.println("[5] Exit the program\n" + border);  

            System.out.print("Please enter your choice: ");
            menuChoice= input.nextInt();

            switch(menuChoice){
                case 1:
                    //function1
                    break;
                case 2:
                    //function2
                    break;               
                case 3:
                    //function3
                    break;
                case 4:
                    //outputs blablabla basta nasa rubrics
                    break;
                case 5:
                    isLogin = false;
                    break;
                default:
                    System.out.print("\nInvalid input, please try again...\n");

            }//main menu choices when inputted

        } //main menu loops


    } //main function

    static 












}//class
