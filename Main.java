
import java.util.*; //this imports the scanner file for the input of the user

public class Main {
//these are the prices for each vehicle and penalties to code efficiency.

    static int Motorcycle = 20;
    static int MotorSucceeding = 10;
    static int Car = 20;
    static int CarSucceeding = 20;
    static int Truck = 20;
    static int TruckSucceeding = 20;
    static int LostTicketPenalty = 20;
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
        String user = "user";
        String password = "1234";

        int totalVehicles = 0;
        int carctr = 0, motorctr = 0, truckctr = 0;
        int totalParkingMin = 0;
        double totalFees = 0.0;

        System.out.println("\nWelcome to the BULSU CICT Parking fee System!\nPlease log in.");
        boolean isLogin = false; //this variable determines if the user has been logged in the system
        int error = 3; // this is the amount of times that the user has before they get locked out of the system for wrong input of credentials

        do {
            //this is where the user will input their acc details to access the system
            System.out.print("\nEnter Username: ");
            String loginUser = input.next();
            System.out.print("Enter Password: ");
            String loginPass = input.next();

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
            System.out.println(); //below is an ASCII art that we used a generator to make
            System.out.println(" _    _      _                          _ ");
            System.out.println("| |  | |    | |                        | |");
            System.out.println("| |  | | ___| | ___ ___  _ __ ___   ___| |");
            System.out.println("| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |");
            System.out.println("\\  /\\  /  __/ | (_| (_) | | | | | |  __/_|");
            System.out.println(" \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)");
            System.out.println("                                          ");
            System.out.println("to the BULSU CICT Parking fee System, " + user + "!");
            System.out.println(border);
            System.out.println("      MAIN MENU     ");
            System.out.println(border);

            //below are the printouts of the options of the user.
            System.out.println("[1] Add vehicle record");
            System.out.println("[2] View Summary report");
            System.out.println("[3] Change login info");
            System.out.println("[4] About us");
            System.out.println("[5] Exit the program\n" + border);

            System.out.print("Please enter your choice");
            int Choice = input.nextInt(); //this variable will be used in switch case 

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
                    break;

                case 3:
                    // Change login info within main (no extra methods)
                    System.out.print("Enter current password to continue: ");
                    String oldPass = input.next();
                    if (!oldPass.equals(password)) {
                        System.out.println("Incorrect Password! Returning to Main menu...");
                    } else {
                        System.out.println("\nOption: ");
                        System.out.println("     [1] Username only");
                        System.out.println("     [2] Password only");
                        System.out.println("     [3] Both Username and Password");
                        System.out.print("Enter Choice: ");
                        int choice = input.nextInt();
                        if (choice == 1) {
                            System.out.print("Enter new Username: ");
                            String newUser = input.next();
                            user = newUser;
                            System.out.println("Username successfully changed!");
                        } else if (choice == 2) {
                            System.out.print("Enter new Password: ");
                            String newPass = input.next();
                            password = newPass;
                            System.out.println("Password successfully changed!");
                        } else if (choice == 3) {
                            System.out.print("Enter new Username: ");
                            String newUser = input.next();
                            user = newUser;
                            System.out.print("Enter new Password: ");
                            String newPass = input.next();
                            password = newPass;
                            System.out.println("Username & Password successfully changed!");
                        } else {
                            System.out.println("Invalid choice! Returning to menu...");
                        }
                    }

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
                    input.nextLine(); // consume newline
                    input.nextLine(); // wait for enter
                    break;

                case 5:
                    runProg = false;
                    break;

                default:
                    System.out.println("\nInvalid input, please try again...");
            } // end switch
        } // end while running

        System.out.println(border + "\nExiting Program... Thank you for using the BULSU CICT Parking fee System!\n" + border);
        input.close();
    }//main

    //this is the InputVehicleInfo() method where the program asks for the user's input on what type of vehicle they need to input
    public static void InputVehicleInfo() {
        System.out.println(border);
        System.out.println("     ADD VEHICLE RECORD     ");
        System.out.println(border);

        do {
            System.out.println("Enter vehicle type (Car, Motorcycle, Truck");
            vehicleType = input.next();
            if (vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("Motorcycle")
                    || vehicleType.equalsIgnoreCase("motor") || vehicleType.equalsIgnoreCase("truck")
                    || vehicleType.equalsIgnoreCase("suv")) {
                break;
            } else {
                System.out.println("INVALID INPUT! ONLY (Car, Motorcycle, Truck, SUV) IS ACCEPTED");
            }
        } while (!((vehicleType.equalsIgnoreCase("car")) || (vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("truck") || (vehicleType.equalsIgnoreCase("suv")))));

        System.out.print("\nEnter vehicle Plate Number with NO SPACES (eg. abc-1234): ");
        plateNum = input.next();

        String lostValid = "no";
        while (lostValid.equals("no")) {
            System.out.print("\nLost ticket (Y/N): ");
            String confirm = input.next();
            if (confirm.equalsIgnoreCase("y")) {
                isLostTicket = true;
                lostValid = "yes";
            } else if (confirm.equalsIgnoreCase("n")) {
                isLostTicket = false;
                lostValid = "yes";
            } else {
                System.out.println("Invalid input (Y/N) ONLY");
            }
        }

        String discValid = "no";
        while (discValid.equals("no")) {
            System.out.print("\nPWD or Senior Citizen (Y/N): ");
            String confirm = input.next();
            if (confirm.equalsIgnoreCase("y")) {
                isDiscounted = true;
                discValid = "yes";
            } else if (confirm.equalsIgnoreCase("n")) {
                isDiscounted = false;
                discValid = "yes";
            } else {
                System.out.println("Invalid input (Y/N) ONLY");
            }
        }

        boolean validIn = false;
        while (!validIn) {
            System.out.print("\nEnter vehicle time IN (0000 - 2359) with NO colons (e.g. 0830): ");
            timeIn = input.nextInt();
            int hr = timeIn / 100;
            int mn = timeIn % 100;
            if (hr < 0 || hr >= 24 || mn < 0 || mn >= 60) {
                System.out.println("Invalid input! time IN must be 0000-2359 ONLY");
            } else {
                validIn = true;
            }
        }
        boolean validOut = false;
        while (!validOut) {
            System.out.print("\nEnter vehicle time OUT (0000 - 2359) with NO colons (e.g. 0930): ");
            timeOut = input.nextInt();
            int hr = timeOut / 100;
            int mn = timeOut % 100;
            if (hr < 0 || hr >= 24 || mn < 0 || mn >= 60) {
                System.out.println("Invalid input! time OUT must be 0000-2359 ONLY");
            } else {
                int inMinutes = (timeIn / 100) * 60 + (timeIn % 100);
                int outMinutes = (timeOut / 100) * 60 + (timeOut % 100);
                if (outMinutes < inMinutes) {
                    System.out.println("Invalid input! time OUT cannot be less than time IN");
                } else {
                    validOut = true;
                }
            }
        }
    }//end of vehicle info

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

        if (diffMinutes <= 30) {
            return 0.0;
        }

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
        if (totalHours <= 1) {
            parkingFee = FirstHourRate;
        } else {
            parkingFee = FirstHourRate + (totalHours - 1) * SucceedingRate;
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

        System.out.printf("\nTotal Fees Collected: P%.2f\n", totalFees);

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
}//class
