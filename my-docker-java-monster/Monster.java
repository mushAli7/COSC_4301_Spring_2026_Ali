// Monster class, a simple class in the Neon Ark program.
public class Monster {

    // Instance variables
    private String name;
    private String type;

    // Constructor to initialize monster attributes
    public Monster(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // A method to return clear description of the monster
    public String getDescription() {
        return name + " is a " + type + "-type monster from the Neon Ark training program.";
    }
}