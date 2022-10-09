package assignment;

import java.util.Scanner;
import java.util.Stack;

public class CalculatorApp {


    public static int evaluate(String userInputCalculation) {
        char[] tokens = userInputCalculation.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> operators = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack of numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer strbuf = new StringBuffer();

                // For multi-digit numbers in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    strbuf.append(tokens[i++]);
                    values.push(Integer.parseInt(strbuf.toString()));

                // the i points to
                // the character next to the digit,
                // since the for loop also increases
                // the i, skip one
                //  token position; we need to
                // decrease the value of i by 1 to
                // correct the offset.
                i--;
            }

            // Current token is an opening brace,
            // push it to 'ops'
            else if (tokens[i] == '(')
                operators.push(tokens[i]);

                // Closing brace encountered,
                // solve entire brace
            else if (tokens[i] == ')') {
                while (operators.peek() != '(')
                    values.push(applyOp(operators.pop(), values.pop(), values.pop()));
                operators.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' )
            {
                // While top of 'ops' has same
                // or greater precedence to current
                // token, which is an operator.
                // Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek()))
                    values.push(applyOp(operators.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                operators.push(tokens[i]);
            }
        }

        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!operators.empty()) values.push(applyOp(operators.pop(), values.pop(), values.pop()));

        // Top of 'values' contains
        // result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {

        if ((op1 == '*' ) && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a)
    {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the Calculator App\nPlease enter the string to be computed: ");

        String userCalculations = userInput.nextLine();

        //error checking with inputted string
        int countErrors = 0;
        for(int i = 0; i < userCalculations.length(); i++)
        {
            if(Character.isWhitespace(userCalculations.charAt(i)) ||
                    Character.isLetter(userCalculations.charAt(i)) ||
                    userCalculations.contains("/"))
            {
                countErrors++;
            }
        }

        if (countErrors == 0)
        {
            System.out.println(CalculatorApp.evaluate(userCalculations));
        }

        else
        {
            System.out.print("This is an invalid string.\nPlease only enter valid" +
                    " strings that contain integers and operands +, - or *.\n" +
                    "For example, 432+89*3-56");
        }
    }
}

