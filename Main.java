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

import java.util.*;

public class Main {

    //these are global variables that are used throughout the methods.
    static String vehicleType, plateNum; //stores vehicle type and plate number
    static int carCount = 0, motorCount = 0, truckCount = 0; //counts total number of each vehicle
    static int timeIn, timeOut; //stores time in and time out in 24-hour format 
    static int timeInHr, timeInMin, timeOutHr, timeOutMin; //splits the time into hours and minutes
    static double totalFees; //total collected fees
    static boolean isDiscounted, isLostticket; //true if PWD or senior citizen
    static int totalVehicles = 0;
    static int totalParkingMinutes = 0;
    static String username = "user";
    static String password = "1234";

    static String border = "----------------------------------------"; // line divider for display

    public static void main(String[] args) {
        //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String loginUsername;
        String loginPassword;

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

        while (isLogin) {
            System.out.println(" _    _      _                          _ \n"
                    + "| |  | |    | |                        | |\n"
                    + "| |  | | ___| | ___ ___  _ __ ___   ___| |\n"
                    + "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\n"
                    + "\\  /\\  /  __/ | (_| (_) | | | | | |  __/_|\n"
                    + " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)\n"
                    + "                                          \n"
                    + "to the BULSU CICT Parking fee System, " + username + "!");
            System.out.println(border + "\nMAIN MENU\n" + border);

            System.out.println("[1] Add vehicle record");
            System.out.println("[2] View Summary report");
            System.out.println("[3] Change login info");
            System.out.println("[4] About us");
            System.out.println("[5] Exit the program\n" + border);

            System.out.print("Please enter your choice: ");
            menuChoice = input.nextInt();

            switch (menuChoice) {
                case 1:
                    do {

                        InputVehicleInfo();
                        DisplayReceipt();

                        System.out.print("\ntry again? (Y/N): ");
                        confirm = input.next();

                        if (confirm.equalsIgnoreCase("n")) {
                            status = false;
                        } else if (confirm.equalsIgnoreCase("y")) {
                            status = true;
                        } else {
                            System.out.println("Invalid input please try again.\n");
                        }
                    } while (status = true);
                    break;
                case 2:
                    GenerateSummary();
                    break;
                case 3:
                    ChangeLoginInfo();
                    break;
                case 4:

                    System.out.println(border);
                    System.out.println("Group 2 CC 103 Final Project");
                    System.out.println("Parking Fee System\n");

                    System.out.println("Presented By: \n");
                    System.out.println("Leader & Lead Developer: Jared Con Medina\n");
                    System.out.println("Project Manager & Assistant Developer: Thristan Sillano\n");
                    System.out.println("Developer: Justine Ethon Galsim\n");
                    System.out.println("Tester & Documentation & Production & Program Concept Desiger: ");
                    System.out.println("Isabella Quiñon, Abiael Capongga");
                    System.out.println(border);

                    System.out.println("Presented to: ");
                    System.out.println("Engr. Evelyn C. Samson\n" + border);
                    input.nextLine();
                    System.out.print("Press enter to continue...");
                    input.nextLine();
                    break;
                case 5:
                    isLogin = false;
                    break;
                default:
                    System.out.print("\nInvalid input, please try again...\n");

            }//main menu choices when inputted

        } //main menu loops

        System.out.println(border + "\nExiting Program... Thank you for using the BULSU CICT Parking fee System!\n" + border);
    } //main function

    public static void InputVehicleInfo() {
        Scanner inputVehicle = new Scanner(System.in);
        boolean status = true;
        String confirm;

        System.out.println(border + "\nADD VEHICLE RECORD\n" + border);

        do {

            System.out.print("\nEnter vehicle type(Car, Motorcycle,Truck): ");
            vehicleType = inputVehicle.next();

            if (vehicleType.equalsIgnoreCase("car")) {
                carCount++;
            } else if (vehicleType.equalsIgnoreCase("motorcycle")) {
                motorCount++;
            } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
                truckCount++;
            } else {
                System.out.print(border + "\nINVALID INPUT! ONLY (Car, Motorcycle, Truck, SUV) IS ACCEPTED\n" + border);
            }

        } while (!((vehicleType.equalsIgnoreCase("car")) || (vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("truck") || (vehicleType.equalsIgnoreCase("suv")))));
        // validates input
        System.out.print("\nEnter vehicle Plate Number with NO SPACES(eg. abc-1234): ");
        plateNum = inputVehicle.next();

        do {
            System.out.print("\nLost ticket (Y/N): ");
            confirm = inputVehicle.next();

            if (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n"))) {
                
            } else if (confirm.equalsIgnoreCase("y")) {
                isLostticket = true;
            } else {
                isLostticket = false;
            }

        } while (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n")));
        confirm = "";

        do {
            System.out.print("\nPWD or Senior Citizen (Y/N): ");
            confirm = inputVehicle.next();

            if (confirm.equalsIgnoreCase("y")) {
                isDiscounted = true;
            } else if (confirm.equalsIgnoreCase("n")) {
                isDiscounted = false;
            } else {
                System.out.println("Invalid input (Y/N) ONLY");
            }
        } while (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n")));

        System.out.println("\nTIME IN");

        do {

            System.out.print("\nEnter vehicle time IN (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
            timeIn = inputVehicle.nextInt();

            if ((timeIn < 0 || timeIn > 2400) || (timeIn / 100 >= 24 || timeIn % 100 >= 60)) {// time/100 = first 2 digits and time%100 = last 2 digits
                System.out.println("\nInvalid input! time IN must be 0000-2359 ONLY");
            }

        } while ((timeIn < 0 || timeIn > 2400) || (timeIn / 100 >= 24 || timeIn % 100 >= 60));

        System.out.println("\nTIME OUT");
        do {

            System.out.print("\nEnter vehicle time OUT (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
            timeOut = inputVehicle.nextInt();

            if ((timeOut < timeIn || timeOut > 2400) || (timeOut / 100 >= 24 || timeOut % 100 >= 60)) {
                System.out.println("\nInvalid input! time OUT must be 0000-2359 ONLY and can NOT be LESS THAN  timme IN");
            }

        } while (timeOut < timeIn || timeOut > 2359 || (timeOut / 100 >= 24 || timeOut % 100 >= 60));

        totalVehicles++;

        int inMinutes = (timeIn / 100) * 60 + (timeIn % 100);
        int outMinutes = (timeOut / 100) * 60 + (timeOut % 100);
        totalParkingMinutes += (outMinutes - inMinutes);

    }//inputs vehicle info

    public static double ComputeParkingFee() {
        timeInHr = timeIn / 100;
        timeInMin = timeIn % 100;
        timeOutHr = timeOut / 100;
        timeOutMin = timeOut % 100;

        int rate = 0;
        double fee = 0;

        int totalMinutesIN = (timeInHr * 60) + timeInMin;
        int totalMinutesOUT = (timeOutHr * 60) + timeOutMin;
        int timeDiff = totalMinutesOUT - totalMinutesIN;

        int totalMinutes = timeDiff;
        int totalHours = timeDiff / 60;
        int remainMin = timeDiff % 60;

        if (remainMin > 0) {
            totalHours++;
        }

        if (totalMinutes <= 30) {
            return 0;
        }

        double totalFee = 0;
        int firstHourRate = 0;
        int succeedingRate = 0;

        if (vehicleType.equalsIgnoreCase("car")) {
            firstHourRate = 20;
            succeedingRate = 10;
        } else if (vehicleType.equalsIgnoreCase("motorcycle")) {
            firstHourRate = 40;
            succeedingRate = 20;
        } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
            firstHourRate = 60;
            succeedingRate = 30;
        } else {
            System.out.println("Invalid vehicle type.");
            return 0;
        }

        if (totalHours <= 1) {
            totalFee = firstHourRate;
        } else {
            totalFee = firstHourRate + ((totalHours - 1) * succeedingRate);
        }

        

        if (isLostticket == true) {
            totalFee += 200;
        }

        if (isDiscounted) {
            totalFee = totalFee - (totalFee * 0.2);
        }

        int firstHour = rate * 2;

        if (timeDiff <= 30) {
            fee = 0;
        } else {

            //ts rounds it up
            if (remainMin > 0) {
                totalHours++;
            }
            //1st hour is rate * 2

        }
        return totalFee;

    }//computes parking fee

    public static void DisplayReceipt() {
        timeInHr = timeIn / 100;
        timeInMin = timeIn % 100;
        timeOutHr = timeOut / 100;
        timeOutMin = timeOut % 100;

        int totalDuration = (((((timeOutHr * 60) - timeOutMin)) - (timeInHr * 60) + timeInMin) / 60);
        if (((timeInHr * 60) + timeInMin) - ((timeOutHr * 60) - timeOutMin) % 60 != 0) {
            totalDuration++;
        }
        System.out.println(border + "\nSUMMARY RECEIPT\n" + border);
        System.out.println("Vehicle:      " + vehicleType);
        System.out.println("Plate Number: " + plateNum);
        if (timeInMin < 10 && timeInHr < 10) {
            System.out.println("Time In: " + "0" + timeInHr + ":" + "0" + timeInMin);
        } else if (timeInMin < 10 && timeInHr > 10) {
            System.out.println("Time In: " + timeInHr + ":" + "0" + timeInMin);
        } else if (timeInMin > 10 && timeInHr < 10) {
            System.out.println("Time In: " + "0" + timeInHr + ":" + timeInMin);
        } else {
            System.out.println("Time In: " + timeInHr + ":" + timeInMin);
        }

        if (timeOutMin < 10 && timeOutHr < 10) {
            System.out.println("Time Out: " + "0" + timeOutHr + ":" + "0" + timeOutMin);
        } else if (timeOutMin < 10 && timeOutHr > 10) {
            System.out.println("Time Out: " + timeOutHr + ":" + "0" + timeOutMin);
        } else if (timeOutMin > 10 && timeOutHr < 10) {
            System.out.println("Time Out: " + "0" + timeOutHr + ":" + timeOutMin);
        } else {
            System.out.println("Time Out: " + timeOutHr + ":" + timeOutMin);
        }
        System.out.println("Total Duration: " + totalDuration);
        System.out.println("fee: " + ComputeParkingFee());
    }

    public static void GenerateSummary() {
        System.out.println(border);
        System.out.println("DAILY SUMMARY REPORT");
        System.out.println(border);

        System.out.println("Total Vehicles Served: " + totalVehicles);
        System.out.println("   Cars: " + carCount);
        System.out.println("   Motorcycles: " + motorCount);
        System.out.println("   Trucks: " + truckCount);

        System.out.println("\nTotal Fees Collected: P" + totalFees);

        if (totalVehicles > 0) {
            int avgMinutes = totalParkingMinutes / totalVehicles;
            int avgHours = avgMinutes / 60;
            int avgRemainMins = avgMinutes % 60;

            System.out.println("Average Parking Duration: " + avgHours + " hour(s) and " + avgRemainMins + " minute(s)");
        } else {
            System.out.println("No record yet");
        }

        System.out.println(border);

    }

    public static void ChangeLoginInfo() {
        Scanner input = new Scanner(System.in);
        String oldPassword, newUsername, newPassword;
        int choice;

        System.out.println(border + "\nCHANGE LOGIN INFO\n" + border);

        System.out.print("Enter current password to continue: ");
        oldPassword = input.next();

        if (!oldPassword.equals(password)) {
            System.out.print("Incorrect Password! Returning to Main menu...\n");
            return;
        }

        System.out.println("\nOption: ");
        System.out.println("[1] Username only");
        System.out.println("[2] Password only");
        System.out.println("[3] Both Username and Password");
        System.out.println("Enter Choice: ");
        choice = input.nextInt();
       

        switch (choice) {
            case 1:
                System.out.print("Enter new Username: ");
                newUsername = input.nextLine();
                username = newUsername;
                System.out.println("Username successfully changed!");
                break;
            case 2:
                System.out.print("Enter new Password: ");
                newPassword = input.nextLine();
                password = newPassword;
                System.out.println("Password successfully changed!");

                break;
            case 3:
                System.out.print("Enter new Username: ");
                newUsername = input.nextLine();
                username = newUsername;

                System.out.print("Enter new Password: ");
                newPassword = input.nextLine();
                password = newPassword;

                System.out.println("Username & Password successfully changed!");
                break;
            default:
                System.out.println("Invalid choice! Returning to menu...");
                break;
        }
        System.out.println(border);
    }
}//class
