package util;

import java.time.LocalDate;

import domain.ClearanceLevel;
import domain.EmploymentStatus;
import domain.IdentifierType;
import domain.Role;

// Handles basic front-end validation checks.
public class InputValidator {

    // Checks that required fields are not blank.
    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Checks that the name contains letters and is at least 2 characters long.
    public static boolean isValidName(String value) {

        return value != null
                && value.trim().length() >= 2
                && value.matches(".*[A-Za-z].*");
    }

    // Checks if the role exists in the system.
    public static boolean isValidRole(String role) {
        return Role.isValid(role);
    }

    // Checks if the email has a basic valid format.
    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // Checks if the employment status exists in the system.
    public static boolean isValidEmploymentStatus(String status) {
        return EmploymentStatus.isValid(status);
    }

    // Checks if the clearance level exists in the system.
    public static boolean isValidClearanceLevel(String level) {
        return ClearanceLevel.isValid(level);
    }

    // Checks if the identifier type exists in the system.
    public static boolean isValidIdentifierType(String type) {
        return IdentifierType.isValid(type);
    }

    // Checks if the date is a real calendar date in YYYY-MM-DD format.
    public static boolean isValidDate(String date) {

        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Checks that the end date is not before the start date.
    public static boolean isValidDateRange(String startDate, String endDate) {

        // Blank end dates are allowed.
        if (endDate == null || endDate.isEmpty()) {
            return true;
        }

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return !end.isBefore(start);
    }
}