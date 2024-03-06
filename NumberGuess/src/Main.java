/*
    NUMBER GUESSING GAME
*/

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declare variables
        Scanner input = new Scanner(System.in);
        Random rng = new Random();
        int rand, guess;
        boolean is_running = true;

        // Randomize
        rand = rng.nextInt() % 20;

        // Program loop
        while (is_running) {
            // Prompt user for random guess
            System.out.println("Please guess a random number between 1 and 20");
            guess = input.nextByte();

            // Check the guess
            if (guess > rand) {
                System.out.println("You guessed too high, try again!");
            } else if (guess < rand) {
                System.out.println("You guessed too low, try again!");
            } else {
                System.out.println("You guessed correctly, thanks for playing!");
                is_running = false;
            }
        }
    }
}