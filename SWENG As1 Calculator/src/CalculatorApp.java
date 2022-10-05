import java.util.Scanner;
import java.lang.Math;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the Calculator App\nPlease enter the string to be computed: ");

        String userCalculations = userInput.nextLine();

        // The tokens that make up the input
        String[] tokens = userCalculations.split("");

        String[] operators = userCalculations.split("[0-9]");
        String[] operands = userCalculations.split("[*+-]");

        int aggregate = 0;
        String operator = "";

        for (int i = 0; i <= tokens.length -1; i++) {

            switch (tokens[1].charAt(0)) {

                // case to add two numbers
                case '*':

                    aggregate *= (Integer.parseInt(tokens[0])) * (Integer.parseInt(tokens[2]));

                    break;

                // case to subtract two numbers
                case '-':
                    aggregate -= Integer.parseInt(tokens[0])
                            - Integer.parseInt(tokens[2]);

                    break;

                // case to multiply two numbers
                case '+':
                    aggregate += Integer.parseInt(tokens[0])
                            + Integer.parseInt(tokens[2]);

                    break;

                default:

                    System.out.println("You enter wrong input");

                    break;
            }
        }


            System.out.println("The final result:");

            System.out.println();

            // print the final result
            System.out.print(aggregate);

    }
}

