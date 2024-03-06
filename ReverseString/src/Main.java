/*
    REVERSE STRING
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declare variables
        Scanner input = new Scanner(System.in);
        String a, b = "";

        // Prompt user
        System.out.println("Please type text you wish to invert.");
        a = input.nextLine();

        // Iterate and reverse string
        for (int i = a.length() - 1; i >= 0; i--) {
            b += a.charAt(i);
        }

        // Print result
        System.out.println(b);
    }
}