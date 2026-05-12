package domain;

public class EmploymentStatus {

    public static final String ACTIVE = "Active";
    public static final String ON_LEAVE = "OnLeave";
    public static final String TERMINATED = "Terminated";

    public static boolean isValid(String status) {

        return status != null && (
                status.equalsIgnoreCase(ACTIVE)
                        || status.equalsIgnoreCase(ON_LEAVE)
                        || status.equalsIgnoreCase(TERMINATED)
        );
    }
}