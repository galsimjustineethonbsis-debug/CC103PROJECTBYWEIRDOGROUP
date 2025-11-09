
import java.util.*; //this imports the scanner file for the input of the user

public class Main {
//these are the prices for each vehicle and penalties to code efficiency.

    static String user = "user";
    static String password = "1234";
    static int Motorcycle = 20;
    static int MotorSucceeding = 10;
    static int Car = 40;
    static int CarSucceeding = 20;
    static int Truck = 60;
    static int TruckSucceeding = 30;
    static int LostTicketPenalty = 200;
    static int DiscountedRate = 20;

    /*this variable serves as the border for the output, put in a variable to 
    prevent the code from being unreadable because of it*/
    static String border = "----------------------------------------";
    static Scanner input = new Scanner(System.in);

    static String vehicleType;
    static String plateNum;
    static int timeIn;
    static int timeOut;
    static boolean isDiscounted;
    static boolean isLostTicket;

    //this is the main method where it will all start
    // as you can see, some variables are in local while some are considered in global
    public static void main(String[] arg) {

        //these are the varaibles and the data of the user and this can be changed if the user wants to
        int totalVehicles = 0;
        int carctr = 0, motorctr = 0, truckctr = 0;
        int totalParkingMin = 0;
        double totalFees = 0.0;
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("\nWelcome to the BULSU CICT Parking fee System!\nPlease log in.");
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
            while (runProg) {
                String headerArt
                        = " _    _      _                          _ \n"
                        + "| |  | |    | |                        | |\n"
                        + "| |  | | ___| | ___ ___  _ __ ___   ___| |\n"
                        + "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |\n"
                        + "\\  /\\  /  __/ | (_| (_) | | | | | |  __/_|\n"
                        + " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)\n";

                System.out.println();
                System.out.println(headerArt);
                System.out.println("to the BULSU CICT Parking fee System, " + user + "!");
                System.out.println(border);
                System.out.println("             MAIN MENU     ");
                System.out.println(border);

                //below are the printouts of the options of the user.
                System.out.println("      [1] Add vehicle record");
                System.out.println("      [2] View Summary report");
                System.out.println("      [3] Change login info");
                System.out.println("      [4] About us");
                System.out.println("      [5] Exit the program\n" + border);

                int Choice = 0;
                boolean validChoice = false;

                while (!validChoice) {
                    System.out.print("Please enter your choice (1 - 5): ");
                    String choiceStr = input.nextLine();

                    if (choiceStr.equals("1")) {
                        Choice = 1;
                        validChoice = true;
                    } else if (choiceStr.equals("2")) {
                        Choice = 2;
                        validChoice = true;
                    } else if (choiceStr.equals("3")) {
                        Choice = 3;
                        validChoice = true;
                    } else if (choiceStr.equals("4")) {
                        Choice = 4;
                        validChoice = true;
                    } else if (choiceStr.equals("5")) {
                        Choice = 5;
                        validChoice = true;
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 5.");
                    }
                }

                //this is where the program continues and will take different paths...
                //each path differ depending on the user's input earlier of what their choice was.
                switch (Choice) {
                    case 1:
                        // Add vehicle record loop using explicit flag
                        boolean repeatAdd = true;
                        while (repeatAdd) {
                            // gather per-vehicle inputs
                            InputVehicleInfo();

                            // compute fee
                            double fee = ComputeParkingFee();

                            // update totals (local variables)
                            totalVehicles = totalVehicles + 1;

                            if (vehicleType.equalsIgnoreCase("car")) {
                                carctr = carctr + 1;
                            } else if (vehicleType.equalsIgnoreCase("motorcycle") || vehicleType.equalsIgnoreCase("motor")) {
                                motorctr = motorctr + 1;
                            } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
                                truckctr = truckctr + 1;
                            }

                            int inMinutes = (timeIn / 100) * 60 + (timeIn % 100);
                            int outMinutes = (timeOut / 100) * 60 + (timeOut % 100);
                            totalParkingMin = totalParkingMin + (outMinutes - inMinutes);

                            // display receipt (fee passed)
                            DisplayReceipt(fee);

                            // update total fees
                            totalFees = totalFees + fee;

                            // ask whether to add another
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

                    case 2:
                        GenerateSummary(totalVehicles, carctr, motorctr, truckctr, totalParkingMin, totalFees);
                        System.out.print("Press enter to continue...");
                        input.nextLine(); // wait for enter
                        break;

                    case 3:
                        // Change login info within main (no extra methods)
                        ChangeLoginInfo();
                        System.out.println("Please log in again with your new credentials.");
                        runProg = false;
                        break;

                    case 4:
                        // About (kept simple)
                        System.out.println(border);
                        System.out.println("Group 2 CC 103 Final Project");
                        System.out.println("Parking Fee System\n");
                        System.out.println("Presented By:");
                        System.out.println("Leader & Lead Developer: Jared Con Medina");
                        System.out.println("Project Manager & Assistant Developer: Thristan Sillano");
                        System.out.println("Developer: Justine Ethon Galsim");
                        System.out.println("Tester & Documentation & Production & Program Concept Designer: Isabella Quiñon, Abiael Capongga");
                        System.out.println(border);
                        System.out.println("Presented to: ");
                        System.out.println("Engr. Evelyn C. Samson\n" + border);
                        System.out.print("Press enter to continue...");
                        input.nextLine(); // wait for enter
                        break;

                    case 5:
                        runProg = false;
                        exitProgram = true;
                        break;

                    default:
                        System.out.println("\nInvalid input, please try again...");
                } // end switch
            } // end while running
        }

        System.out.println(border + "\nExiting Program... Thank you for using the BULSU CICT Parking fee System!\n" + border);
        input.close();
    }//main

    //this is the InputVehicleInfo() method where the program asks for the user's input on what type of vehicle they need to input
    public static void InputVehicleInfo() {
        System.out.println(border);
        System.out.println("     ADD VEHICLE RECORD     ");
        System.out.println(border);

        // VEHICLE TYPE
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

        // consume newline before using nextLine()
        input.nextLine();

        // PLATE NUMBER
        boolean validPlate = false;
        while (!validPlate) {
            System.out.print("Enter vehicle Plate Number (e.g. ABC-1234): ");
            plateNum = input.nextLine();

            if (plateNum.equals("") || plateNum.equals(" ")) {
                System.out.println("Invalid plate number. Cannot be empty or only a space.");
            } else {
                break; // accept it, even if user typed spaces inside
            }
        }

        // DISCOUNT (Y/N)
        boolean validDisc = false;
        while (!validDisc) {
            System.out.print("PWD or Senior Citizen (Y/N): ");
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

        // LOST TICKET (Y/N)
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

        do {

            System.out.print("\nEnter vehicle time IN (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
            timeIn = input.nextInt();

            if ((timeIn < 0 || timeIn > 2400) || (timeIn / 100 >= 24 || timeIn % 100 >= 60)) {// time/100 = first 2 digits and time%100 = last 2 digits
                System.out.println("\nInvalid input! time IN must be 0000-2359 ONLY");
            }

        } while ((timeIn < 0 || timeIn > 2400) || (timeIn / 100 >= 24 || timeIn % 100 >= 60));

        do {

            System.out.print("\nEnter vehicle time OUT (0000(12:00am)-2359(11:59pm) with NO colons \":\"): ");
            timeOut = input.nextInt();

            if ((timeOut < timeIn || timeOut > 2400) || (timeOut / 100 >= 24 || timeOut % 100 >= 60)) {
                System.out.println("\nInvalid input! time OUT must be 0000-2359 ONLY and can NOT be LESS THAN  timme IN");
            }

        } while (timeOut < timeIn || timeOut > 2359 || (timeOut / 100 >= 24 || timeOut % 100 >= 60));

        input.nextLine(); // clear newline before going back to main
    }

    //end of vehicle info
    public static double ComputeParkingFee() {
        int TimeinHour = timeIn / 100;
        int TimeinMin = timeIn % 100;
        int TimeoutHour = timeOut / 100;
        int TimeoutMin = timeOut % 100;

        int TotalMinutesIn = TimeinHour * 60 + TimeinMin;
        int TotalMinutesOut = TimeoutHour * 60 + TimeoutMin;
        int diffMinutes = TotalMinutesOut - TotalMinutesIn;

        int totalHours = diffMinutes / 60;
        if (diffMinutes % 60 > 0) {
            totalHours = totalHours + 1;
        }

        int FirstHourRate = 0;
        int SucceedingRate = 0;

        if (vehicleType.equalsIgnoreCase("motorcycle") || vehicleType.equalsIgnoreCase("motor")) {
            FirstHourRate = Motorcycle;
            SucceedingRate = MotorSucceeding;
        } else if (vehicleType.equalsIgnoreCase("car")) {
            FirstHourRate = Car;
            SucceedingRate = CarSucceeding;
        } else if (vehicleType.equalsIgnoreCase("truck") || vehicleType.equalsIgnoreCase("suv")) {
            FirstHourRate = Truck;
            SucceedingRate = TruckSucceeding;
        } else {
            System.out.println("Invalid vehicle type.");
            return 0.0;
        }

        double parkingFee = 0.0;
        if (diffMinutes > 30) {
            if (totalHours <= 1) {
                parkingFee = FirstHourRate;
            } else {
                parkingFee = FirstHourRate + (totalHours - 1) * SucceedingRate;
            }
        }
        if (isDiscounted) {
            parkingFee = parkingFee - (parkingFee * DiscountedRate / 100.0);
        }

        // add lost ticket penalty
        if (isLostTicket) {
            parkingFee = parkingFee + LostTicketPenalty;
        }

        return parkingFee;
    }

    public static void DisplayReceipt(double fee) {
        int TimeinHour = timeIn / 100;
        int TimeinMin = timeIn % 100;
        int TimeoutHour = timeOut / 100;
        int TimeoutMin = timeOut % 100;

        int TotalMinutesIn = TimeinHour * 60 + TimeinMin;
        int TotalMinutesOut = TimeoutHour * 60 + TimeoutMin;
        int diffMinutes = TotalMinutesOut - TotalMinutesIn;
        int durHours = diffMinutes / 60;
        int durMins = diffMinutes % 60;

        System.out.println(border + "\nSUMMARY RECEIPT\n" + border);
        System.out.println("Vehicle: " + vehicleType);
        System.out.println("Plate Number: " + plateNum);

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

        System.out.println("Time In: " + inHrStr + ":" + inMnStr);
        System.out.println("Time Out: " + outHrStr + ":" + outMnStr);
        System.out.println("Total Duration: " + durHours + " hour(s) and " + durMins + " minute(s)");
        System.out.println("fee: " + ComputeParkingFee());
    } // end DisplayReceipt

    // -------------------------
    // GenerateSummary - prints daily totals (parameters from main)
    // -------------------------
    public static void GenerateSummary(int totalVehicles, int carctr, int motorcyclectr, int truckctr,
            int totalParkingMin, double totalFees) {
        System.out.println(border);
        System.out.println("DAILY SUMMARY REPORT");
        System.out.println(border);

        System.out.println("Total Vehicles Served: " + totalVehicles);
        System.out.println("   Cars: " + carctr);
        System.out.println("   Motorcycles: " + motorcyclectr);
        System.out.println("   Trucks: " + truckctr);

        System.out.println("\nTotal Fees Collected: P" + totalFees);

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

    public static void ChangeLoginInfo() {
        String oldPassword, newUsername, newPassword;
        int choice;
        int attempts = 3; // maximum attempts for entering current password

        System.out.println(border + "\nCHANGE LOGIN INFO\n" + border);

        while (attempts > 0) {
            System.out.print("Enter current password to continue: ");
            oldPassword = input.next();

            if (oldPassword.equals(password)) {
                // Correct password → continue to change info
                System.out.println("\nOption: ");
                System.out.println("[1] Username only");
                System.out.println("[2] Password only");
                System.out.println("[3] Both Username and Password");
                System.out.print("Enter Choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new Username: ");
                        newUsername = input.nextLine();
                        user = newUsername;
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
                        user = newUsername;

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
                return; // exit method after successful change
            } else {
                attempts--;
                System.out.println(border);
                System.out.println("Incorrect Password. Please try again.");
                System.out.println("Attempts left: " + attempts);
                System.out.println(border);

                if (attempts == 0) {
                    System.out.println("Too many attempts. Exiting program...");
                    input.close();
                    System.exit(0);
                }
            }
        }
    }
}
