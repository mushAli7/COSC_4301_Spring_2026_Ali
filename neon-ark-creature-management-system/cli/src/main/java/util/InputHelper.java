package util;

import java.util.Scanner;

/*
 * Centralized helper methods to validate CLI input.
 */
public class InputHelper {

    /*
     * Prevents blank required text input.
     */
    public static String readRequiredText(
            Scanner scanner,
            String prompt) {

        String value = "";

        while (value.isBlank()) {

            System.out.print(prompt);
            value = scanner.nextLine().trim();

            if (value.isBlank()) {
                System.out.println("This field is required.");
            }
        }

        return value;
    }

    /*
     * Allows optional text input.
     */
    public static String readOptionalText(
            Scanner scanner,
            String prompt) {

        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /*
     * Restricts input to allowed values only.
     */
    public static String readMenuChoice(
            Scanner scanner,
            String prompt,
            String... allowedValues) {

        while (true) {

            System.out.print(prompt);

            String value =
                    scanner.nextLine().trim().toUpperCase();

            for (String allowed : allowedValues) {

                if (value.equalsIgnoreCase(allowed)) {
                    return value;
                }
            }

            System.out.println("Invalid selection. Allowed values:");

            for (String allowed : allowedValues) {
                System.out.println("- " + allowed);
            }
        }
    }

    /*
     * Requires at least two letters and rejects values that are only numbers.
     */
    public static String readNameText(
            Scanner scanner,
            String prompt) {

        while (true) {

            System.out.print(prompt);

            String value = scanner.nextLine().trim();

            if (value.isBlank()) {

                System.out.println("This field is required.");
            }
            else if (!value.matches("[A-Za-z ]+")) {

                System.out.println(
                        "Please enter alphabetic characters only."
                );
            }
            else if (value.length() < 2) {

                System.out.println(
                        "Please enter at least two characters."
                );
            }
            else {

                return value;
            }
        }
    }

    /*
     * Ensures a positive numeric ID value.
     */
    public static Long readPositiveLong(
            Scanner scanner,
            String prompt) {

        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {

                Long value = Long.parseLong(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Value must be greater than zero.");
            }
            catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    /*
     * Ensures use of HH:MM format.
     */
    public static String readTimeValue(Scanner scanner, String prompt) {

        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (value.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                return value;
            }

            System.out.println("Time must use HH:MM format.");
        }
    }
}