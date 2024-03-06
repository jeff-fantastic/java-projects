/*
    SUM A + B
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declare variables
        Scanner input = new Scanner(System.in);
        int a, b, result;

        // Prompt user for input
        System.out.println("Please type the first number...");
        a = input.nextInt();
        System.out.println("Please type the next number...");
        b = input.nextInt();

        // Calculate and print
        result = a + b;
        System.out.println("The sum of " + a + " + " + b + " equals " + result);
    }
}