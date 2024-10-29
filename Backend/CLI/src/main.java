package src;


import java.util.Scanner;


public class main {
    // To get inputs
    Scanner scanner = new Scanner(System.in);

    // Get integer user inputs
    private int getUserInputInt(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect Integer");
            return -1;
        }
    }

    // Get float user inputs
    private double getUserInputDouble(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextDouble();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect Float");
            return -1;
        }
    }

    // Get string user inputs
    private String getUserInputString(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect String");
            return null;
        }
    }

    // Main menu
    private int mainMenu() {
        System.out.println("""
        ===== Real-Time Event Ticketing System =====
        1. Configure System Parameters
        2. Start Ticket Handling
        3. Stop Ticket Handling
        4. View Real-Time Ticket Status
        5. View Sales Log
        6. Exit
        ============================================
        """);
        return getUserInputInt("Please select an option (1-6):> ");
    }

    // Main method
    public static void main(String[] args) {
        main main = new main();

        while (true) {
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Configure System Parameters
                    break;

                case 2:     // 2. Start Ticket Handling
                    break;

                case 3:     // 3. Stop Ticket Handling
                    break;

                case 4:     // 4. View Real-Time Ticket Status
                    break;

                case 5:     // Inject language or reference
                    break;

                case 6:     // 6. Exit
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            System.out.println();
        }
    }
}
