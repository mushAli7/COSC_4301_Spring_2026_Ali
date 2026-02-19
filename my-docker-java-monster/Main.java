// Main class serves as the entry point for the application.
public class Main {

    // Main method that executes the program logic
    public static void main(String[] args) {

        // Create a new Monster object
        Monster monster = new Monster("Emberclaw", "Fire");

        // Print the monster description to the terminal
        System.out.println("Your monster has been created. Description: "
                + monster.getDescription());
    }
}
