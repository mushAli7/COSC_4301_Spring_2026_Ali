package domain;

// Allowed Warden role values.
public class Role {

    public static final String ADMIN = "Admin";
    public static final String FIELD = "Field";
    public static final String RIFT = "Rift";
    public static final String TRAINER = "Trainer";
    public static final String ASTRAL = "Astral";

    // Checks if the role exists in the system.
    public static boolean isValid(String role) {

        return role != null && (
                role.equalsIgnoreCase(ADMIN)
                        || role.equalsIgnoreCase(FIELD)
                        || role.equalsIgnoreCase(RIFT)
                        || role.equalsIgnoreCase(TRAINER)
                        || role.equalsIgnoreCase(ASTRAL)
        );
    }
}