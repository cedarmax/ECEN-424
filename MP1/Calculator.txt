import java.util.Scanner;

public class Calculator {

    // Private field to store the name
    private String name;

    // Method for addition
    public Float addition(Float A, Float B) {
        return A + B;
    }

    // Method for subtraction
    public Float subtraction(Float A, Float B) {
        return A - B;
    }

    // Method for multiplication
    public Float multiplication(Float A, Float B) {
        return A * B;
    }

    // Setter for the name field
    public void setname(String N) {
        this.name = N;
    }

    // Getter for the name field
    public String getname() {
        return this.name;
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Create a Calculator object
        Calculator mycalc = new Calculator();

        // Set the name of the calculator (group number)
        mycalc.setname("Group 19");

        // Create a scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Print the welcome message
        System.out.println("Welcome to the Calculator designed by \"" + mycalc.getname() + "\".");
        System.out.println("Enter A to Add, S to Subtract, M to Multiply, and Q to quit.");

        // While loop to keep the calculator running until 'Q' is entered
        while (true) {
            System.out.print("Enter your operation: ");
            String operation = scanner.nextLine().toUpperCase();  // Read input and convert to uppercase

            // Check for quit condition
            if (operation.equals("Q")) {
                System.out.println("Quitting the calculator. Goodbye!");
                break;
            }

            // If the input is not valid, show the welcome message again
            if (!(operation.equals("A") || operation.equals("S") || operation.equals("M"))) {
                System.out.println("Invalid input. Please enter a valid operation.");
                System.out.println("Welcome to the Calculator designed by \"" + mycalc.getname() + "\".");
                System.out.println("Enter A to Add, S to Subtract, M to Multiply, and Q to quit.");
                continue;
            }

            // Prompt user to enter arguments
            System.out.print("Enter argument 1: ");
            String arg1Str = scanner.nextLine();  // Input parsed as a string
            Float arg1 = null;
            try {
                arg1 = Float.parseFloat(arg1Str);  // Convert String to Float
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for argument 1. Please try again.");
                continue;
            }

            System.out.print("Enter argument 2: ");
            String arg2Str = scanner.nextLine();  // Input parsed as a string
            Float arg2 = null;
            try {
                arg2 = Float.parseFloat(arg2Str);  // Convert String to Float
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for argument 2. Please try again.");
                continue;
            }

            // Perform the operation based on user input
            Float result = null;
            String resultType = "";

            switch (operation) {
                case "A":
                    result = mycalc.addition(arg1, arg2);
                    resultType = "The sum";
                    break;
                case "S":
                    result = mycalc.subtraction(arg1, arg2);
                    resultType = "The difference";
                    break;
                case "M":
                    result = mycalc.multiplication(arg1, arg2);
                    resultType = "The product";
                    break;
            }

            // Print the result
            System.out.println(resultType + " of " + arg1 + " and " + arg2 + " is " + result + ".");
        }

        // Close the scanner
        scanner.close();
    }
}
