/*
    PERSON DATABASE

    GUI application that allows for input of user information
 */

import java.util.Vector;

public class Main {
    // Declare variables
    public static Vector<Person> gDatabase = new Vector<>();

    public static void main(String[] args) {
        // Create main window
        DatabaseView window = new DatabaseView();
    }
}