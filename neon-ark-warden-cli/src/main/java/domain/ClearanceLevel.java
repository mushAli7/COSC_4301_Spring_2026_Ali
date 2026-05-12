package domain;

public class ClearanceLevel {

    public static final String ALPHA = "Alpha";
    public static final String OMEGA = "Omega";
    public static final String ECLIPSE = "Eclipse";

    public static boolean isValid(String level) {

        return level != null && (
                level.equalsIgnoreCase(ALPHA)
                        || level.equalsIgnoreCase(OMEGA)
                        || level.equalsIgnoreCase(ECLIPSE)
        );
    }
}