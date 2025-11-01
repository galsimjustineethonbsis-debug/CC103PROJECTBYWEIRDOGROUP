
import java.util.*; //this imports the scanner file for the input of the user

public class Main {

    //these are global variables that are used throughout the methods.
    static String vehicleType, plateNum; //stores vehicle type and plate number
    static int carCount = 0, motorCount = 0, truckCount = 0; //counts total number of each vehicle
    static int timeIn, timeOut; //stores time in and time out in 24-hour format 
    static int timeInHr, timeInMin, timeOutHr, timeOutMin; //splits the time into hours and minutes
    static double totalFees; //total collected fees
    static boolean isDiscounted; //true if PWD or senior citizen
    static int totalVehicles = 0;
    static int totalParkingMinutes = 0;
    static String username = "user";
    static String password = "1234";

    static String border = "----------------------------------------"; // line divider for display

    public static void main(String[] args) {
        //initialize the variables and scanner
        Scanner input = new Scanner(System.in);

        String newUsername, loginUsername;
        String newPassword, loginPassword;

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
                    } while (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n")));
                    break;
                case 2:
                    GenerateSummary();
                    break;
                case 3:
                    ChangeLoginInfo();
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

                        if (confirm.equalsIgnoreCase("n")) {
                            status = false;
                        } else if (confirm.equalsIgnoreCase("y")) {
                            status = true;
                        } else {
                            System.out.println("Invalid input please try again.\n");
                        }
                    } while (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n")));
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
                System.out.print(border + "\nINVALID INPUT! ONLY (Car, Motorcycle,Truck, SUV) IS ACCEPTED\n" + border);
            }

        } while (!((vehicleType.equalsIgnoreCase("car")) || (vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("truck") || (vehicleType.equalsIgnoreCase("suv")))));
        // validates input
        System.out.print("\nEnter vehicle Plate Number with NO SPACES(eg. abc-1234): ");
        plateNum = inputVehicle.next();

        do {
            System.out.println("\nLost ticket (Y/N): ");
            confirm = inputVehicle.next();

            if (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n"))) {
                System.out.print("Invalid input (Y/N) ONLY");
            }
        } while (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n")));
        confirm = "";
        do {
            System.out.println("\nPWD or Senior Citizen (Y/N): ");
            confirm = inputVehicle.next();

            if (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("n"))) {
                System.out.print("Invalid input (Y/N) ONLY");
            } else {
                isDiscounted = true;
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

        int totalMinutesIN = (timeInHr * 60) + timeInMin, totalMinutesOUT = (timeOutHr * 60) + timeOutMin, timeDiff = (totalMinutesOUT - totalMinutesIN);
        int rate = 0;
        double fee = 0;

        if (vehicleType.equalsIgnoreCase("car")) {
            rate = 20;
        } else if (vehicleType.equalsIgnoreCase("motorcycle")) {
            rate = 10;
        } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
            rate = 30;
        }

        int firstHour = rate * 2;

        if (totalMinutesIN <= 30) {
            fee = 0;
        } else {
            if ((timeDiff / 60) % 60 != 0) {
                timeDiff += 60;
            }
            fee = firstHour + (((timeDiff / 60) - 1) * rate);
        }

        if (isDiscounted == true) {
            fee -= (fee * 0.2);
        }

        totalFees += fee;
        return fee;

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
        System.out.println("Vehicle: " + vehicleType);
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
        } else if (timeOutMin < 10 && timeInHr > 10) {
            System.out.println("Time Out: " + timeOutHr + ":" + "0" + timeOutMin);
        } else if (timeOutMin > 10 && timeInHr < 10) {
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

        System.out.println("Enter current password to continue: ");
        oldPassword = input.nextLine();

        if (!oldPassword.equals(password)) {
            System.out.println("Incorrect Password! Returning to mainmenu...");
            return;
        }

        System.out.println("\nOption: ");
        System.out.println("     [1] Username only");
        System.out.println("     [2] Password only");
        System.out.println("     [3] Both Username and Password");
        System.out.println("Enter Choice: ");
        choice = input.nextInt();
        input.nextLine();

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
