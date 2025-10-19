
import java.util.*;

public class Main {
    static String vehicleType,plateNum;
    static int carCount=0,motorCount=0,truckCount=0;
    static int timeIn, timeOut;
    static int timeInHr, timeInMin, timeOutHr, timeOutMin;
    static double total;

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
                else if(vehicleType.equalsIgnoreCase("truck")|| vehicleType.equalsIgnoreCase("suv"))
                truckCount++;                
                else{
                    System.out.print(border +"\nINVALID INPUT! ONLY (Car, Motorcycle,Truck, SUV) IS ACCEPTED\n"+ border);
                }

            }while(!((vehicleType.equalsIgnoreCase("car")) ||(vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("truck") || (vehicleType.equalsIgnoreCase("suv")))));
            //line 110 validates input
            System.out.print("\nEnter vehicle Plate Number with NO SPACES(eg. abc-1234): ");
            plateNum  = inputVehicle.next();

            System.out.println("\nTIME IN");
            do{
                    
                System.out.print("\nEnter vehicle time IN (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
                timeIn = inputVehicle.nextInt();

                if((timeIn<0 || timeIn>2400) || (timeIn/100>=24 || timeIn%100>=60)){// time/100 = first 2 digits and time%100 = last 2 digits
                    System.out.println("\nInvalid input! time IN must be 0000-2359 ONLY");
                }

            }while((timeIn<0 || timeIn>2400) ||(timeIn/100>=24 || timeIn%100>=60));
            
            System.out.println("\nTIME OUT");
            do{

                System.out.print( "\nEnter vehicle time OUT (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
                timeOut = inputVehicle.nextInt(); 

                if((timeOut<timeIn || timeOut>2400) || (timeOut/100>=24 || timeOut%100>=60)){
                    System.out.println("\nInvalid input! time OUT must be 0000-2359 ONLY and can NOT be LESS THAN  timme IN");
                }
                
            }while(timeOut<timeIn || timeOut>2359 || (timeOut/100>=24 || timeOut%100>=60 ));


            
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
            
            timeInHr = timeIn / 100;
            timeInMin = timeIn % 100;
            timeOutHr = timeOut / 100;
            timeOutMin = timeOut % 100;

            System.out.println(timeInHr + ":" + timeInMin);
            
            System.out.println(ComputeParkngFee());

        }while(status == true);

    }//inputs vehicle info

    static double ComputeParkngFee(){
        int totalMinutesIN = (timeInHr*60) + timeInMin, totalMinutesOUT = (timeOutHr*60) + timeOutMin, timeDiff = (totalMinutesOUT - totalMinutesIN);
        int rate=0;
        double fee=0;

        
        if(vehicleType.equalsIgnoreCase("car"))
            rate = 20;
        else if(vehicleType.equalsIgnoreCase("motorcycle"))
            rate = 10;
        else if(vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")){
            rate = 30;
        }

        int firstHour = rate*2;

        if(totalMinutesIN<=30){
            fee = 0;
        }
        else{
            if((timeDiff/60)%60 != 0){
                timeDiff += 60;
            }
            fee = firstHour + (((timeDiff/60)- 1 )* rate);
        }

        total+=fee;
        return fee;        

    }//computes parking fee

    






    












}//class
