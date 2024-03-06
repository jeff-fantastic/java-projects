/*
    CALCULATOR APP
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Declare variables
    public static final String HELP_STR = "\n" +
            "+ [NUMBER] - Adds number to current\n" +
            "- [NUMBER] - Subtracts number from current\n" +
            "* (x) [NUMBER] - Multiplies number to current\n" +
            "/ [NUMBER] - Divides number into current\n" +
            "% [NUMBER] - Performs modulo operation on current\n" +
            "clr (c, clear) - Resets current to 0\n" +
            "stop (s) - Stops program\n" +
            "help (h) - Brings up this message\n";
    public static final String ERR_STR = "\n" +
            "INVALID OPERATION! Please try again!";
    public static Scanner input = new Scanner(System.in);
    public static float current = 0;
    public static String operation = "";
    public static boolean is_running = true;

    // Main loop
    public static void main(String[] args) {
        while (is_running) {
            loop();
        }
    }

    // Checks if a string is a float
    public static boolean is_float(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            float result = Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void loop() {
        // Prompt user
        System.out.printf("Number is: %.2f" + "\n", current);
        System.out.println("Please insert your next operation. (type 'help' for options)");
        operation = input.nextLine();

        // Split line and decode
        String[] split = operation.split("\\s+");

        // Check for generic codes
        switch (split[0]) {
            case "help", "h":
                System.out.println(HELP_STR);
                return;
            case "clr", "clear", "c":
                current = 0.0f;
                return;
            case "stop", "s":
                is_running = false;
                return;
        }

        // Verify numeric data
        if (Arrays.stream(split).count() <= 1) {
            System.out.println(ERR_STR);
            return;
        }
        if (!is_float(split[1])) {
            System.out.println(ERR_STR);
            return;
        }

        // Convert string to float
        float num = Float.parseFloat(split[1]);

        // First character should be an operation
        switch (split[0]) {
            case "+":
                current += num;
                break;
            case "-":
                current -= num;
                break;
            case "*", "x":
                current *= num;
                break;
            case "/":
                current /= num;
                break;
            case "%":
                current %= num;
                break;
            default:
                System.out.println(ERR_STR);
                break;
        }
    }
}