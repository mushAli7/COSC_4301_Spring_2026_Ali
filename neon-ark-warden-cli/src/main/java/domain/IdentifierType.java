package domain;

public class IdentifierType {

    public static final String BADGE = "Badge";
    public static final String PASSPORT = "Passport";
    public static final String VISA = "Visa";

    public static boolean isValid(String type) {

        return type != null && (
                type.equalsIgnoreCase(BADGE)
                        || type.equalsIgnoreCase(PASSPORT)
                        || type.equalsIgnoreCase(VISA)
        );
    }
}