
import java.util.*;

public class Main {
    static String vehicleType,plateNum;
    static int carCount=0,motorCount=0,truckCount=0;
    static int timeIn, timeOut;
    static int timeInHr,timeInMin,timeOutHr,timeOutMin;

    static String border = "----------------------------------------";
    
    public static void main(String[] args) {
            //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String username = "user", newUsername, loginUsername;
        String password = "1234", oldPassword, newPassword, loginPassword;

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
                    InputVehicleInfo();
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

        System.out.println(border +"\nExiting Program.. Thank you for using the BULSU CICT Parking fee System!\n"+ border);
    } //main function

    static void InputVehicleInfo(){
        Scanner inputVehicle = new Scanner(System.in);
        boolean status = true;
        String confirm;

        do{

            System.out.println(border + "\nADD VEHICLE RECORD\n" + border);

            do{

                System.out.print("\nEnter vehicle type(Car, Motorcycle,Truck): ");
                vehicleType = inputVehicle.next();
                
                
                if(vehicleType.equalsIgnoreCase("car"))
                carCount++;
                else if(vehicleType.equalsIgnoreCase("motorcycle"))
                motorCount++;
                else if(vehicleType.equalsIgnoreCase("truck"))
                truckCount++;
                else{
                    System.out.print(border +"\nINVALID INPUT! ONLY (Car, Motorcycle,Truck) IS ACCEPTED\n"+ border);
                }

            }while(!((vehicleType.equalsIgnoreCase("car")) ||(vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("truck"))));

            System.out.print("\nEnter vehicle Plate Number(xxx-xxxx): ");
            vehicleType = inputVehicle.next();

      
            

            
    
            do{
                
                System.out.print("\nEnter vehicle time IN  (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
                timeIn = inputVehicle.nextInt();

                if(timeIn<0 || timeIn>2359){
                    System.out.println("\nInvalid input! time IN must be 0000-2359 ONLY");
                }

            }while(timeIn<0 || timeIn>2359);
            System.out.println(border);
            do{

                    System.out.print( "Enter vehicle time OUT (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
                    timeOut = inputVehicle.nextInt(); 

            
            }while(timeOut<timeIn || timeIn < 2359);      
            
            do{
            System.out.print("\ntry again? (Y/N): ");
            confirm = inputVehicle.next();
            if(confirm.equalsIgnoreCase("n"))
                status = false;
            
            else if(confirm.equalsIgnoreCase("y"))
                status = true;
            else{
                System.out.println("Invalid input please try again.\n");
            }
        }while(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n")));
            
            

        }while(status = true);

    }//inputs vehicle info



    












}//class
