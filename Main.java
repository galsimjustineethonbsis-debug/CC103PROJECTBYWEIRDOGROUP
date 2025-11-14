/*
  HEADER FILE
  Project: Group 2 CC 103 Final Project
  Project Name: Parking Fee System
  Authors: Jared Con Medina (Leader & Lead Developer)
           Thristan Sillano (Project Manager & Assistant Developer)
           Justine Ethon Galsim (Developer)
           Isabella Quiñon (Tester & Documentation)
           Abiael Capongga (Program Concept Designer)
  Timeframe Created: October 9 2025 - November 10, 2025
  Finished: November 10, 2025

  Description:
  The team has formulated a system that can be used in real life situations such as parking fee systems in malls, establishments, and other places that require parking fees.
  The team has used JAVA language to create this program.

    Features:
     - Allows the user to log in with a username and password
     - Add vehicle records with vehicle type, plate number, time in/out, discount status, and lost ticket status
     - Computes parking fees based on vehicle type, duration, discounts, and penalties
     - Applies a 20% discount for PWDs and Senior Citizens and adds a 200 PHP penalty for lost tickets
     - Displays receipt and daily summary report of the day
     - Allows changing of login credentials

    Usage:
     - Runs the program with the default username and password
     - can navigate through the menu to access different features
     - Add vehicle records with vehicle type, plate number, time in/out, discount status, and lost ticket status

    Limits:
     - Time inputs can be only entered as integers with HHMM format and with no colons
     - User is not allowed to input any letters or strings in Time in and Time out or else the program will crash
     - Username and Password are case-sensitive
     - The program does not save data after exiting
     - The program assumes time out is always on the same day as time in and does not support overnight parking
     - no advanced Java Features

    Notes to users:
        - Ensure to input valid data as prompted to avoid errors
        - The program is designed for educational purposes and may require further enhancements for real-world applications

    Notes to Developers:
        - Keep comments updated for clarity and concies to explain the code and its logic to other developers
        - don't forget to test the program thoroughly to ensure all features work as intended
        - dont change variable names as it may cause errors in the program
 */

import java.util.*; //this imports the scanner file for the input of the user

public class Main {

    //these are the default credentials for the user to access the system
    static String user = "user";               //default username (can be modified later in the program)
    static String password = "1234";           //default password (can be modified later in the program)

    //these are the prices for each vehicle and penalties to code efficiency.
    static int MotorcycleRate = 20;
    static int MotorRateSucceeding = MotorcycleRate / 2;
    static int CarRate = 40;
    static int CarRateSucceeding = CarRate / 2;
    static int TruckRate = 60;
    static int TruckRateSucceeding = TruckRate / 2;

    static double LostTicketPenalty = 200; //this is the fixed penalty for lost tickets
    static int DiscountedRate = 20; //this is the percentage for the discount for PWDs and Senior Citizens

    /*this variable serves as the border for the output, put in a variable to 
    prevent the code from being unreadable because of it*/
    static String border = "----------------------------------------";
    static Scanner input = new Scanner(System.in); //this serveds as the input scanner for the user's input

    //these are the global variables that will be used in different methods
    static String vehicleType;                    //"car", "motorcycle", "truck", "suv"
    static String plateNum;                      //plate number of the vehicle
    static int timeIn;                            //time in HHMM format (0000-2359)
    static int timeOut;                           //time out HHMM format (0000-2359), this must be greater than timeIn
    static boolean isDiscounted;                  //true if PWD or Senior Citizen, false otherwise
    static boolean isLostTicket;                  //true if lost ticket, false otherwise

    //this is the main method where it will all start
    // as you can see, some variables are in local while some are considered in global
    //this shows our understanding of variable usage in java
    public static void main(String[] arg) {

        //these are the varaibles that will be used in the main method only
        //these variables serves as the counters and totals for the summary report
        int totalVehicles = 0;
        int carctr = 0, motorctr = 0, truckctr = 0;
        int totalParkingMin = 0;
        double totalFees = 0.0;
        boolean exitProgram = false;
        int ticketctr = 0; //this variable serves as the ticket number counter for each vehicle record added

        //this is the loop of the login system
        while (!exitProgram) {
            System.out.println("\nWelcome to the Azure Parking Daioh Parking fee System!\nPlease log in.");
            boolean isLogin = false; //this variable determines if the user has been logged in the system
            int error = 3; // this is the amount of times that the user has before they get locked out of the system for wrong input of credentials

            do {
                //this is where the user will input their acc details to access the system
                System.out.print("\nEnter Username: ");
                String loginUser = input.nextLine();
                System.out.print("Enter Password: ");
                String loginPass = input.nextLine();

                // and this is the condition that is strictly need to be followed for security purposes
                if (loginUser.equals(user) && loginPass.equals(password)) {
                    isLogin = true; // if the user inputs the correct credentials, this will become true and they will be able to access the system
                    System.out.println("\nLogin successful! Accessing the system...");
                    System.out.println("Logged in as: " + user);
                    System.out.println(border);
                } else { //however... if they dont put the correct credentials:
                    error = error - 1; //their attempts will be subtracted by one
                    System.out.println(border);
                    System.out.println("Incorrect username or password. Please try again");
                    System.out.println(error + " time(s) left.");
                    System.out.println(border);
                    if (error == 0) { // and if that reaches 0
                        System.out.println("\nToo many attempts.\nExiting Program...");
                        input.close();
                        System.exit(0); // the system will terminate
                    }
                }

            } while (!isLogin); // this block of code will repeat itself as long as the user inputs the wrong credentials

            //this variable determines and controls the loop if the system is running or not.
            boolean runProg = true;

            //this is the main loop of the program where the menu and options are located
            while (runProg) {
                //this is the ASCI art header of the program for aesthetics
                //we used ASCI art generator online to create this
                String headerArt
                        = " _    _      _                          _ \n"
                        + "| |  | |    | |                        | |\n"
                        + "| |  | | ___| | ___ ___  _ __ ___   ___| |\n"
                        + "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\n"
                        + "\\  /\\  /  __/ | (_| (_) | | | | | |  __/_|\n"
                        + " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)\n";

                //this is the printout of the main menu header        
                System.out.println();
                System.out.println(headerArt); //since we put the header art in a variable, we can just call it here
                System.out.println("to the Azure Parking Daioh System, " + user + "!");
                System.out.println(border); //since we put the border in a variable, we can just call it here
                System.out.println("             MAIN MENU     ");
                System.out.println(border); //same as above

                //below are the printouts of the options of the user.
                System.out.println("      [1] Add vehicle record");
                System.out.println("      [2] View Summary report");
                System.out.println("      [3] Change login info");
                System.out.println("      [4] About us");
                System.out.println("      [5] Exit the program\n" + border);

                //this is the variable that will store the user's choice
                int Choice = 0;
                boolean validChoice = false; //this variable will determine if the user's input is valid or not

                //this loop will continue until the user inputs a valid choice
                while (!validChoice) {
                    System.out.print("Enter your choice (1-5): ");
                    Choice = input.nextInt();
                    input.nextLine(); //consume newline

                    if (Choice >= 1 && Choice <= 5) {
                        validChoice = true; //valid input
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 5.");
                    }
                }
                //this is where the program continues and will take different paths...
                //each path differ depending on the user's input earlier of what their choice was.
                switch (Choice) {

                    // this case is for adding vehicle records
                    case 1:
                        // this loop allows the user to add multiple vehicle records without going back to the main menu
                        boolean repeatAdd = true;
                        while (repeatAdd) {

                            //PRINT OUT THE MENU OF THE ADD VEHICLE RECORD
                            // gather vehicle inputs
                            InputVehicleInfo(); //this method is on line 294

                            //compute fee and this variable will be used in displaying the receipt too
                            double fee = ComputeParkingFee(); //this method is on line 398

                            //update total vehicles (local variables)
                            totalVehicles = totalVehicles + 1;

                            //updates the number of specific vehicle types
                            if (vehicleType.equalsIgnoreCase("car")) {
                                carctr = carctr + 1; //if the user inputs car, the car counter will increase by one
                            } else if (vehicleType.equalsIgnoreCase("motorcycle") || vehicleType.equalsIgnoreCase("motor")) {
                                motorctr = motorctr + 1; //if the user inputs motorcycle, the motorcycle counter will increase by one
                            } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
                                truckctr = truckctr + 1; //if the user inputs truck, the truck counter will increase by one
                            }

                            //This updates the total parking minutes for summary report   
                            int inMinutes = (timeIn / 100) * 60 + (timeIn % 100);
                            int outMinutes = (timeOut / 100) * 60 + (timeOut % 100);
                            totalParkingMin = totalParkingMin + (outMinutes - inMinutes);

                            ticketctr += 1; //this increases the ticket number counter by one for each vehicle record added

                            //the fee variable is passed here to display the receipt
                            DisplayReceipt(fee, ticketctr); //this method is on line 460

                            //used to update total fees for summary report
                            totalFees = totalFees + fee;

                            //this asks the user if they want to add another vehicle record
                            System.out.print("\nTry again? (Y/N): ");
                            String ans = input.next();
                            input.nextLine();
                            if (ans.equalsIgnoreCase("n")) {
                                repeatAdd = false;
                            } else if (ans.equalsIgnoreCase("y")) {
                                repeatAdd = true;
                            } else {
                                System.out.println("Invalid input. Returning to main menu.");
                                repeatAdd = false;
                            }
                        }
                        break;

                    //this case is for generating the summary report
                    case 2:
                        //this is the method that generates the summary report
                        GenerateSummary(totalVehicles, carctr, motorctr, truckctr, totalParkingMin, totalFees);
                        System.out.print("Press enter to continue...");
                        input.nextLine(); //this waits for the user to press enter before going back to the main menu
                        break;

                    //this case is for changing the login info
                    case 3:
                        //this is the method that allows the user to change their login info, all security measures are implemented here inside the method
                        ChangeLoginInfo(); //this method is on line 534
                        System.out.println("Please log in again with your new credentials.");
                        runProg = false; //this will break the main loop and go back to the login loop
                        break;

                    //this case is for the about us section
                    case 4:
                        System.out.println(border);
                        System.out.println("Group 2 CC 103 Final Project");
                        System.out.println("Parking Fee System\n");
                        System.out.println("Presented By:");

                        System.out.println("*------------------------------|-----------------------|----------------|---------------------------------------|---------------------*");
                        System.out.println("|         NAME                 |        COURSE         |YEAR AND SECTION|           CONTACT INFO                |        ROLE         |");
                        System.out.println("|==============================|=======================|================|=======================================|=====================|");
                        System.out.println("|     MEDINA, JARED CON P.     |                       |                |      medina.jared.bsis@gmail.com      |    Lead Developer   |");
                        System.out.println("*------------------------------|                       |                |---------------------------------------|---------------------*");
                        System.out.println("| SILLANO, ISAIAH THRISTAN W.  |                       |                |     sillano.isaiah@bsis@gmail.com     |   Project Manager   |");
                        System.out.println("*------------------------------|  BACHELOR OF SCIENCE  |                |---------------------------------------|---------------------*");
                        System.out.println("|   GALSIM, JUSTINE ETHON B.   |          IN           |    1B - G2     |     galsimjustineethon@gmail.com      | Assistant Developer |");
                        System.out.println("*------------------------------|  INFORMATION SYSTEMS  |                |---------------------------------------|---------------------*");
                        System.out.println("|     CAPONGGA, ABIGAEL M.     |                       |                |    capongga.abigael.bsis@gmail.com    | Assistant Developer |");
                        System.out.println("*------------------------------|                       |                |---------------------------------------|---------------------*");
                        System.out.println("|QUINON, MA. JESUSA ISABELLA H.|                       |                |    quinon.isabella.bsis@gmail.com     | Assistant Developer |");
                        System.out.println("*------------------------------|-----------------------|----------------|---------------------------------------|---------------------*");
                        System.out.println("Presented to: ");
                        System.out.println("Engr. Evelyn C. Samson\n" + border);
                        System.out.print("Press enter to continue...");
                        input.nextLine(); // wait for enter
                        break;

                    //this case is for exiting the program
                    case 5:
                        runProg = false;
                        exitProgram = true;
                        break;

                    default:
                        //this default case should never be reached due to input validation
                        System.out.println("\nInvalid input, please try again...");
                } // end switch
            } // end while running
        }// end while !exitProgram

        //this is the exit message of the program when the user chooses to exit
        System.out.println(border + "\nExiting Program... Thank you for using the Azure Parking Daioh Parking fee System!\n" + border);
        input.close();

    }//main

//-------------------------METHODS-------------------------//
    //this is the InputVehicleInfo() method where the program asks for the user's input on what type of vehicle they need to input
    //vehicleType, plateNum, timeIn, timeOut, isDiscounted, isLostTicket are all global variables used in this method and will all be updaed
    //TAKENOTE: 
    //TimeIn and TimeOut are in HHMM format (0000-2359) without colons and are expected as integers ONLY
    //timeOut must be greater than timeIn
    public static void InputVehicleInfo() {
        System.out.println(border);
        System.out.println("     ADD VEHICLE RECORD     ");
        System.out.println(border);

        System.out.println(border);
        System.out.println("         VEHICLE RATES MENU");
        System.out.println(border);
        System.out.println("Motorcycle   - First Hour: P" + MotorcycleRate
                + " | Succeeding Hour: P" + MotorRateSucceeding);
        System.out.println("Car          - First Hour: P" + CarRate
                + " | Succeeding Hour: P" + CarRateSucceeding);
        System.out.println("Truck / SUV  - First Hour: P" + TruckRate
                + " | Succeeding Hour: P" + TruckRateSucceeding);
        System.out.println(border);
        System.out.println("Additional Information:");
        System.out.println(" - 30 minutes grace period (no charge)");
        System.out.println(" - Lost ticket penalty: P" + LostTicketPenalty);
        System.out.println(" - 20% discount for PWDs and Senior Citizens");
        System.out.println(border);

        //VEHICLE TYPE
        //this loop will continue until the user inputs a valid vehicle type
        boolean validType = false;
        while (!validType) {
            System.out.print("Enter vehicle type (Car, Motorcycle, Truck, SUV): ");
            vehicleType = input.next();
            if (vehicleType.equalsIgnoreCase("car")
                    || vehicleType.equalsIgnoreCase("motorcycle")
                    || vehicleType.equalsIgnoreCase("motor")
                    || vehicleType.equalsIgnoreCase("truck")
                    || vehicleType.equalsIgnoreCase("suv")) {
                validType = true;
            } else {
                System.out.println("Invalid input! Please enter only Car, Motorcycle, Truck, or SUV.");
            }
        }

        //consume newline before using nextLine()
        input.nextLine();

        //PLATE NUMBER
        //this loop will continue until the user inputs a valid plate number
        boolean validPlate = false;
        while (!validPlate) {
            System.out.print("Enter vehicle Plate Number (e.g. ABC-1234): ");
            plateNum = input.nextLine();

            if (plateNum.equals("") || plateNum.equals(" ")) {
                System.out.println("Invalid plate number. Cannot be empty or only a space.");
            } else {
                validPlate = true; // accept it, even if user typed spaces inside
            }
        }

        //TIME IN and TIME OUT
        //this do-while loop will continue until the user inputs a valid time in
        //TIME IN and TIME OUT
//this loop will continue until the user inputs valid time in and time out
        boolean validTime = false;
        do {
            System.out.print("\nEnter vehicle time IN (0000-2359, no colons): ");
            timeIn = input.nextInt();
            System.out.print("\nEnter vehicle time OUT (0000-2359, no colons): ");
            timeOut = input.nextInt();

            //check if times are within 00:00-23:59
            boolean timeInValid = timeIn >= 0 && timeIn <= 2359 && (timeIn / 100 < 24) && (timeIn % 100 < 60);
            boolean timeOutValid = timeOut >= 0 && timeOut <= 2359 && (timeOut / 100 < 24) && (timeOut % 100 < 60);

            if (!timeInValid || !timeOutValid) {
                System.out.println("\nInvalid input! Time IN and OUT must be between 0000-2359.");
            } else if (timeOut < timeIn) {
                System.out.println("\nInvalid input! Time OUT must be greater than Time IN.");
            } else if (timeOut == timeIn) {
                System.out.println("\nInvalid input! Time IN and Time OUT cannot be the same.");
            } else {
                validTime = true; //all checks passed
            }
        } while (!validTime);

        //DISCOUNT (Y/N)
        //discount applies to PWDs and Senior Citizens and will loop until valid input is given
        boolean validDisc = false;
        while (!validDisc) {
            System.out.print("\nPWD or Senior Citizen (Y/N): ");
            String confirm = input.next();
            if (confirm.equalsIgnoreCase("y")) {
                isDiscounted = true;
                validDisc = true;
            } else if (confirm.equalsIgnoreCase("n")) {
                isDiscounted = false;
                validDisc = true;
            } else {
                System.out.println("Invalid input! Please enter only Y or N.");
            }
        }

        //LOST TICKET (Y/N)
        //this loop will continue until the user inputs a valid input for lost ticket
        boolean validLost = false;
        while (!validLost) {
            System.out.print("Lost ticket (Y/N): ");
            String confirm = input.next();
            if (confirm.equalsIgnoreCase("y")) {
                isLostTicket = true;
                validLost = true;
            } else if (confirm.equalsIgnoreCase("n")) {
                isLostTicket = false;
                validLost = true;
            } else {
                System.out.println("Invalid input! Please enter only Y or N.");
            }
        }

        input.nextLine(); // clear newline before going back to main
    } //end InputVehicleInfo

    //ComputeParkingFee - computes parking fee based on global variables
    //Global variables used: vehicleType, timeIn, timeOut, isDiscounted, isLostTicket
    //returns computed parking fee as double
    //How the parking fee is computed:
    //Converts timeIn and timeOut from HHMM to total minutes then put in a variable called diffMinutes
    //then if diffMinutes is less than or equal to 30 minutes, parking fee is 0
    //discount and lost ticket penalty are applied after the parking fee is computed
    public static double ComputeParkingFee() {
        int TimeinHour = timeIn / 100;
        int TimeinMin = timeIn % 100;
        int TimeoutHour = timeOut / 100;
        int TimeoutMin = timeOut % 100;

        int TotalMinutesIn = TimeinHour * 60 + TimeinMin;
        int TotalMinutesOut = TimeoutHour * 60 + TimeoutMin;
        int diffMinutes = TotalMinutesOut - TotalMinutesIn;  //total parking duration in minutes

        //this rounds up the total hours (61 minutes = 2 hours)
        int totalHours = diffMinutes / 60;
        if (diffMinutes % 60 > 0) {
            totalHours = totalHours + 1;
        }

        //this is the placeholder for the rates depending on vehicle type
        int FirstHourRate = 0;
        int SucceedingRate = 0;

        //determine rates based on vehicle type
        if (vehicleType.equalsIgnoreCase("motorcycle") || vehicleType.equalsIgnoreCase("motor")) {
            FirstHourRate = MotorcycleRate;
            SucceedingRate = MotorRateSucceeding;
        } else if (vehicleType.equalsIgnoreCase("car")) {
            FirstHourRate = CarRate;
            SucceedingRate = CarRateSucceeding;
        } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
            FirstHourRate = TruckRate;
            SucceedingRate = TruckRateSucceeding;
        } else {
            //this should never be reached due to input validation
            System.out.println("Invalid vehicle type.");
            return 0.0;
        }

        //compute parking fee
        double parkingFee = 0.0;
        if (diffMinutes > 30) { //this condition applies 30-minute grace period
            if (totalHours <= 1) {
                parkingFee = FirstHourRate;
            } else {
                parkingFee = FirstHourRate + (totalHours - 1) * SucceedingRate;
            }
        }

        //apply discount (Penalty is not part of discount)
        return parkingFee;
    }//end ComputeParkingFee

    //DisplayReceipt - displays receipt based on global variables and fee parameter
    //Global variables used: vehicleType, plateNum, timeIn, timeOut
    //this uses the fee variable earlier at the main method when ComputeParkingFee() is called
    public static void DisplayReceipt(double fee, int ticketctr) {
        String zeronum = "000";
        if (ticketctr < 10) {
            zeronum = "000";
        } else if (ticketctr < 100) {
            zeronum = "00";
        } else if (ticketctr < 1000) {
            zeronum = "0";
        } else {
            zeronum = "";
        }

        //this block of code converts timeIn and timeOut from HHMM to hours and minutes for display
        int TimeinHour = timeIn / 100;
        int TimeinMin = timeIn % 100;
        int TimeoutHour = timeOut / 100;
        int TimeoutMin = timeOut % 100;

        //this block of code computes the total duration in hours and minutes for display
        int TotalMinutesIn = TimeinHour * 60 + TimeinMin;
        int TotalMinutesOut = TimeoutHour * 60 + TimeoutMin;
        int diffMinutes = TotalMinutesOut - TotalMinutesIn;
        int durHours = diffMinutes / 60;
        int durMins = diffMinutes % 60;

        // this is the printout of the receipt
        System.out.println(border + "\nSUMMARY RECEIPT\n" + border);
        System.out.println("Vehicle:      " + vehicleType);
        System.out.println("Plate Number: " + plateNum);
        System.out.println("Ticket number: " + zeronum + ticketctr);
        if (isDiscounted) {
            System.out.println("Discount:     PWD/Senior Citizen");
        } else {
            System.out.println("Discount:     None");
        }
        if (isLostTicket) {
            System.out.println("Lost Ticket:  Yes");
        } else {
            System.out.println("Lost Ticket:  No");
        }

        //format time in and time out with leading zeros if needed  
        String inHrStr = "" + TimeinHour;
        if (TimeinHour < 10) {
            inHrStr = "0" + TimeinHour;
        }
        String inMnStr = "" + TimeinMin;
        if (TimeinMin < 10) {
            inMnStr = "0" + TimeinMin;
        }
        String outHrStr = "" + TimeoutHour;
        if (TimeoutHour < 10) {
            outHrStr = "0" + TimeoutHour;
        }
        String outMnStr = "" + TimeoutMin;
        if (TimeoutMin < 10) {
            outMnStr = "0" + TimeoutMin;
        }

        System.out.println("\nTime In:  " + inHrStr + ":" + inMnStr);
        System.out.println("Time Out: " + outHrStr + ":" + outMnStr);
        System.out.println("Total Duration: " + durHours + " hour(s) and " + durMins + " minute(s)");

        System.out.println("\nSubtotal: " + fee);

        if (isDiscounted) {

            System.out.println("Discount Applied (" + DiscountedRate + "%): -P" + (fee * (DiscountedRate / 100.0)));
            fee = fee - (fee * (DiscountedRate / 100.0));
            System.out.println("Amount after discount:   P" + fee);
        }

        //this will add the lost ticket penalty after the discount is applied
        if (isLostTicket) {
            fee += LostTicketPenalty;
            System.out.println("Lost Ticket Penalty:    +P" + LostTicketPenalty);
        }
        System.out.println("\nTotal Amount Due:        P" + fee);
        System.out.println(border);

    } //end DisplayReceipt

    //GenerateSummary - generates daily summary report based on parameters
    //parameters: totalVehicles, carctr, motorcyclectr, truckctr, totalParkingMin, totalFees
    public static void GenerateSummary(int totalVehicles, int carctr, int motorcyclectr, int truckctr,
            int totalParkingMin, double totalFees) {
        System.out.println(border);
        System.out.println("DAILY SUMMARY REPORT");
        System.out.println(border);

        System.out.println("Total Vehicles Served: " + totalVehicles);
        System.out.println("   Cars: " + carctr);
        System.out.println("   Motorcycles: " + motorcyclectr);
        System.out.println("   Trucks or SUV: " + truckctr);

        System.out.println("\nTotal Amount Due: P" + totalFees);

        //compute average parking duration
        if (totalVehicles > 0) {
            int avgMinutes = totalParkingMin / totalVehicles;
            int avgHours = avgMinutes / 60;
            int avgRemainMins = avgMinutes % 60;
            System.out.println("Average Parking Duration: " + avgHours + " hour(s) and " + avgRemainMins + " minute(s)");
        } else {
            System.out.println("No record yet");
        }

        System.out.println(border);
    } // end GenerateSummary

    //ChangeLoginInfo - allows user to change username and/or password
    //prompts for current password for security for only 3 attempts
    //After any change of any credentials, user is logged out and must log in again
    public static void ChangeLoginInfo() {
        String oldPassword, newUsername, newPassword; //temporary variables for changing credentials, like a placeholder
        int choice;
        int attempts = 3; //maximum attempts for entering current password

        System.out.println(border + "\nCHANGE LOGIN INFO\n" + border);

        while (attempts > 0) {
            System.out.print("Enter current password to continue: ");
            oldPassword = input.next();

            if (oldPassword.equals(password)) {
                //if password is correct, allow changes
                //this prints out the options for changing login info
                System.out.println("\nOption: ");
                System.out.println("[1] Username only");
                System.out.println("[2] Password only");
                System.out.println("[3] Both Username and Password");
                System.out.print("Enter Choice: ");
                choice = input.nextInt();
                input.nextLine();

                //this switch-case handles the user's choice for changing credentials
                switch (choice) {
                    //this case is for changing username only
                    case 1:
                        //placeholder here so data is not randomly lost
                        System.out.print("Enter new Username: ");
                        newUsername = input.nextLine();
                        user = newUsername;
                        System.out.println("Username successfully changed!");
                        break;

                    //this case is for changing password only
                    case 2:
                        System.out.print("Enter new Password: ");
                        newPassword = input.nextLine();
                        password = newPassword;
                        System.out.println("Password successfully changed!");
                        break;

                    //this case is for changing both username and password 
                    case 3:
                        System.out.print("Enter new Username: ");
                        newUsername = input.nextLine();
                        user = newUsername;

                        System.out.print("Enter new Password: ");
                        newPassword = input.nextLine();
                        password = newPassword;

                        System.out.println("Username & Password successfully changed!");
                        break;

                    //this default case should never be reached due to input validation
                    default:
                        System.out.println("Invalid choice! Returning to menu...");
                        break;
                }
                System.out.println(border);
                return; // exit method after successful change

                //if password is incorrect
            } else {
                attempts--; //decrease attempts by 1
                System.out.println(border);
                System.out.println("Incorrect Password. Please try again.");
                System.out.println("Attempts left: " + attempts);
                System.out.println(border);

                //if attempts reach 0, exit program
                if (attempts == 0) {
                    System.out.println("Too many attempts. Exiting program...");
                    input.close();
                    System.exit(0);
                }
            }
        }//end while
    }//end ChangeLoginInfo
}//end class Main

/*

===========================================================
                 PROJECT CONTRIBUTION REPORT
===========================================================

1. JARED CON MEDINA – Leader & Lead Developer
   Programming:
     - Designed initial system structure and main logic.
     - Implemented user login, menu navigation, and fee computation.
     - Declared global constants (rates, penalties).
     - Fixed early bugs in program flow.
   Leadership:
     - Assigned tasks and coordinated code integration.
     - Guided the assistant developers and the formulation of the code, presentation, and flowchart design.

2. THRISTAN SILLANO – Project Manager & Assistant Developer
   Programming:
     - Debugged major logical issues (exit logic, summary, averages).
     - Improved user input validation and error-handling.
     - Made variables local
     - Ensured the program followed rubric requirements.
   Project Management:
     - Coordinated team workflow and updates.
   Presentation:
     - Created and prepared slides and system walkthrough.

3. JUSTINE ETHON GALSIM – Developer
   Programming:
     - Assisted in vehicle counting logic and fee updates.
     - Helped refine certain compute and summary functions.
     - was the one who developed the GenerateSummary method
   Documentation:
     - Contributed to technical descriptions.

4. ISABELLA QUIÑON – Tester, Documentation, Concept Designer
   Testing:
     - Identified bugs, tested edge cases, and validated program behavior.
     - Helped in GenerateSummary with Abigael Caponnga

   Documentation & Concept:
     - Helped finalize system theme and project background.
     - collaborated with Isabella Quiñon in the flowchart design


5. Abigael Capongga– Tester, Documentation, Concept Designer
   Testing:
     - Identified bugs, tested edge cases, and validated program behavior.
     - Helped in GenerateSummary with Isabella Quiñon 
   Documentation & Concept:
     - Helped finalize system theme and project background.
     - collaborated with Isabella Quiñon in the flowchart design
===========================================================
 */
