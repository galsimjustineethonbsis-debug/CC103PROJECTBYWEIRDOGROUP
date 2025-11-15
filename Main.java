
import java.util.*;

public class Main {
    static String vehicleType,plateNum;
    static int carCount=0,motorCount=0,truckCount=0;
    static int timeIn, timeOut;
    static int timeInHr, timeInMin, timeOutHr, timeOutMin;
    static double total;
    static boolean isPWDorSS;
    static int totalVehicles = 0;
    static int totalParkedMinutes = 0;

    static String border = "----------------------------------------";
    
    public static void main(String[] args) {
            //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String username = "user", newUsername, loginUsername;
        String password = "1234", oldPassword, newPassword, loginPassword;

        int menuChoice;

        String confirm;
        boolean isLogin = false, status;
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
                    do{

                        InputVehicleInfo();
                        DisplayReceipt();
                        
                        System.out.print("\ntry again? (Y/N): ");
                        confirm = input.next();

                        if(confirm.equalsIgnoreCase("n"))
                            status = false;                    
                        
                        else if(confirm.equalsIgnoreCase("y"))
                            status = true;
                        else{
                            System.out.println("Invalid input please try again.\n");
                    }
                    }while(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n")));
                    break;
                case 2:
                    GenerateSummary();
                    break;               
                case 3:
                    //function3
                    break;
                case 4:
                    do {
                    System.out.println(border);
                    System.out.println("Group 2 CC 103 Final Project");
                    System.out.println("Parking Fee System!");
                    
                    System.out.println("Presented By: ");
                    System.out.println("Leader & Lead Developer: Jared Con Medina");
                    System.out.println("Project Manager & Assistant Developer: Thristan Sillano");
                    System.out.println("Developer: Justine Ethon Galsim");
                    System.out.println("Tester & Documentation & Production & Program Concept Desiger: ");
                    System.out.println("Isabella Quiñon, Abiael Capongga");
                    System.out.println(border);

                    System.out.println("Presented to: ");
                    System.out.println("Engr. Evelyn C. Samson");

                    System.out.println("Do you want to try again?");
                    System.out.print("\ntry again? (Y/N): ");
                        confirm = input.next();

                        if(confirm.equalsIgnoreCase("n"))
                            status = false;                    
                        
                        else if(confirm.equalsIgnoreCase("y"))
                            status = true;
                        else{
                            System.out.println("Invalid input please try again.\n");
                    }
                    }while(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n")));
                    break;
                case 5:
                    isLogin = false;
                    break;
                default:
                    System.out.print("\nInvalid input, please try again...\n");

            }//main menu choices when inputted

        } //main menu loops

        System.out.println(border +"\nExiting Program... Thank you for using the BULSU CICT Parking fee System!\n"+ border);
    } //main function

    static void InputVehicleInfo(){ 
        Scanner inputVehicle = new Scanner(System.in);
        boolean status = true;
        String confirm;

        

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
            // validates input
            System.out.print("\nEnter vehicle Plate Number with NO SPACES(eg. abc-1234): ");
            plateNum  = inputVehicle.next();

            do{
                System.out.println("\nLost ticket (Y/N): ");
                confirm = inputVehicle.next();
                
                if(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n"))){
                    System.out.print("Invalid input (Y/N) ONLY");
                }   
            }while(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n")));
            confirm = "";
            do{
                System.out.println("\nPWD or Senior Citizen (Y/N): ");
                confirm = inputVehicle.next();
                
                if(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n"))){
                    System.out.print("Invalid input (Y/N) ONLY");
                }
                else{
                    isPWDorSS = true;
                }
            }while(!(confirm.equalsIgnoreCase("y")|| confirm.equalsIgnoreCase("n")));            

            

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


    }//inputs vehicle info

    static double ComputeParkngFee(){
        timeInHr = timeIn / 100;
        timeInMin = timeIn % 100;
        timeOutHr = timeOut / 100;
        timeOutMin = timeOut % 100;

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
        totalParkedMinutes += timeDiff;
        totalVehicles++;

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

        if(isPWDorSS == true){
            fee-=(fee*0.2);
        }


        total+=fee;
        return fee;        

    }//computes parking fee

    static void DisplayReceipt(){
        timeInHr = timeIn / 100;
        timeInMin = timeIn % 100;
        timeOutHr = timeOut / 100;
        timeOutMin = timeOut % 100;
        
        int totalDuration = (((((timeOutHr*60)-timeOutMin))-(timeInHr*60)+timeInMin)/60);
        if(((timeInHr*60)+timeInMin)-((timeOutHr*60)-timeOutMin)%60!=0){
            totalDuration++;
        }
        System.out.println(border + "\nSUMMARY RECEIPT\n" + border);
        System.out.println("Vehicle: " + vehicleType);
        System.out.println("Plate Number: " + plateNum);
        if (timeInMin < 10 && timeInHr < 10){
            System.out.println("Time In: " + "0" + timeInHr + ":" + "0" + timeInMin);
        } else if (timeInMin < 10 && timeInHr > 10){
            System.out.println("Time In: " + timeInHr + ":" + "0" + timeInMin);
        } else if (timeInMin > 10 && timeInHr < 10){
            System.out.println("Time In: " + "0" + timeInHr + ":" + timeInMin);
        }  else {
            System.out.println("Time In: " + timeInHr + ":" + timeInMin);
        }
       
        if (timeOutMin < 10 && timeOutHr < 10){
            System.out.println("Time Out: " + "0" + timeOutHr + ":" + "0" + timeOutMin);
        } else if (timeOutMin < 10 && timeInHr > 10){
            System.out.println("Time Out: " + timeOutHr + ":" + "0" + timeOutMin);
        } else if (timeOutMin > 10 && timeInHr < 10){
            System.out.println("Time Out: " + "0" + timeOutHr + ":" + timeOutMin);
        }  else {
            System.out.println("Time Out: " + timeOutHr + ":" + timeOutMin);
        }
        System.out.println("Total Duration: " + totalDuration );
        System.out.println("fee: " + ComputeParkngFee());
    }
    static void GenerateSummary(){
        System.out.println(border);
        System.out.println("Total Vehicle Parked Today: ");
        System.out.println("Total Fees Collected: " +  total);

        if (totalVehicles > 0){
            int avgMins = totalParkedMinutes / totalVehicles;
            int avgHrs = avgMins / 60;
            int avgMinutes = avgMins % 60;
            System.out.println("Average Time Parked: " + avgHrs + "hours");
        } else {
            System.out.println("Average Time Parked: N/A (no vehicle recorded.)");
        }
    }

}//class
