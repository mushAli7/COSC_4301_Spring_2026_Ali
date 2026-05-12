package menu;

import model.Warden;
import service.WardenService;

import java.util.List;
import java.util.Scanner;

// Handles the Neon Ark console menus and user interaction.
public class WardenMenu {

    private final WardenService service;
    private final Scanner scanner;

    public WardenMenu() {
        service = new WardenService();
        scanner = new Scanner(System.in);
    }

    // Starts the main menu loop.
    public void start() {

        boolean running = true;

        while (running) {

            printMainMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addNewWarden();
                    break;

                case "2":
                    viewWardensMenu();
                    break;

                case "3":
                    updateWardenMenu();
                    break;

                case "4":
                    certificationsMenu();
                    break;

                case "5":
                    employmentStatusMenu();
                    break;

                case "6":
                    running = false;
                    System.out.println("Exiting Neon Ark Console...");
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    // Prints the main menu options
    private void printMainMenu() {

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("NEON ARK — ADMIN WARDEN ONBOARDING CONSOLE");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("[ MAIN MENU ]");
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add New Warden");
        System.out.println("2. View Wardens");
        System.out.println("3. Update Warden");
        System.out.println("4. Manage Certifications");
        System.out.println("5. Deactivate / Terminate Warden");
        System.out.println("6. Exit");
        System.out.println();
        System.out.print("Select an option: ");
    }

    // Displays the Warden viewing submenu.
    private void viewWardensMenu() {

        boolean viewing = true;

        while (viewing) {

            System.out.println();
            System.out.println("[ VIEW WARDENS ]");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. View All Wardens");
            System.out.println("2. View Warden by ID");
            System.out.println("3. View Wardens by Employment Status");
            System.out.println("4. View Wardens by Role");
            System.out.println("5. Return to Main Menu");
            System.out.println();

            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    viewAllWardens();
                    break;

                case "2":
                    printViewByIdSimulation();
                    break;

                case "3":
                    printViewByStatusSimulation();
                    break;

                case "4":
                    printViewByRoleSimulation();
                    break;

                case "5":
                    viewing = false;
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    // Displays all wardens from the CSV snapshot.
    private void viewAllWardens() {

        List<Warden> wardens = service.getAllWardens();

        System.out.println();
        System.out.println("ALL WARDENS");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        System.out.printf(
                "%-5s %-12s %-12s %-30s %-12s %-10s %-12s %-15s %-12s %-12s %-12s%n",
                "warden_id",
                "first_name",
                "last_name",
                "email",
                "identifier_type",
                "identifier_value",
                "role",
                "employment_status",
                "clearance_level",
                "start_date",
                "end_date"
        );

        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for (Warden warden : wardens) {

            System.out.printf(
                    "%-5d %-12s %-12s %-30s %-12s %-10s %-12s %-15s %-12s %-12s %-12s%n",
                    warden.getWardenId(),
                    warden.getFirstName(),
                    warden.getLastName(),
                    warden.getEmail(),
                    warden.getIdentifierType(),
                    warden.getIdentifierValue(),
                    warden.getRole(),
                    warden.getEmploymentStatus(),
                    warden.getClearanceLevel(),
                    warden.getStartDate(),
                    warden.getEndDate()
            );
        }
        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Forces a non-empty user response.
    // Repeatedly prompts the user and displays an error until a valid value is entered.
    private String promptRequired(String label) {

        String value;

        do {
            System.out.print(label + ": ");
            value = scanner.nextLine().trim();

            if (value.isEmpty()) {

                System.out.println(label + " is required. Please enter a value.");

            } else if ((label.equalsIgnoreCase("First Name")
                    || label.equalsIgnoreCase("Last Name"))
                    && !util.InputValidator.isValidName(value)) {

                System.out.println("Please enter a valid " + label.toLowerCase() + ".");
            }

        } while (
                value.isEmpty()
                        || (
                        (label.equalsIgnoreCase("First Name")
                                || label.equalsIgnoreCase("Last Name"))
                                && !util.InputValidator.isValidName(value)
                )
        );

        return value;
    }

    // Prompts until a valid identifier type is entered.
    private String promptIdentifierType() {

        String type;

        do {
            System.out.print("Identifier Type: ");
            type = scanner.nextLine().trim();

            if (type.isEmpty()) {
                System.out.println("Identifier Type is required. Please enter a value.");
            } else if (!util.InputValidator.isValidIdentifierType(type)) {
                System.out.println(
                        "Invalid identifier type. Use Badge, Passport, or Visa."
                );
            }

        } while (!util.InputValidator.isValidIdentifierType(type));

        return type;
    }

    // Prompts until a valid email is entered and enforces email is required.
    private String promptEmail() {

        String email;

        do {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("Email is required. Please enter a value.");
            } else if (!util.InputValidator.isValidEmail(email)) {
                System.out.println("Invalid email format. " +
                        "Please enter a valid email address (e.g., user@example.com)");
            }

        } while (email.isEmpty() || !util.InputValidator.isValidEmail(email));

        return email;
    }

    // Prompts until a valid role is entered and enforces role is required
    private String promptRole() {

        String role;

        do {
            System.out.print("Role: ");
            role = scanner.nextLine().trim();

            if (role.isEmpty()) {
                System.out.println("Role is required. Please enter a value.");
            } else if (!util.InputValidator.isValidRole(role)) {
                System.out.println("Invalid role. Use Admin, Field, Rift, Trainer, or Astral.");
            }

        } while (!util.InputValidator.isValidRole(role));

        return role;
    }

    // Prompts until a valid employment status is entered and enforces status is required
    private String promptEmploymentStatus() {

        String status;

        do {
            System.out.print("Employment Status: ");
            status = scanner.nextLine().trim();

            if (status.isEmpty()) {
                System.out.println("Employment Status is required. Please enter a value.");
            } else if (!util.InputValidator.isValidEmploymentStatus(status)) {
                System.out.println("Invalid employment status. Use Active, OnLeave, or Terminated.");
            }

        } while (!util.InputValidator.isValidEmploymentStatus(status));

        return status;
    }

    // Prompts until a valid clearance level is entered and enforces clearance level is required
    private String promptClearanceLevel() {

        String level;

        do {
            System.out.print("Clearance Level: ");
            level = scanner.nextLine().trim();

            if (level.isEmpty()) {
                System.out.println("Clearance Level is required. Please enter a value.");
            } else if (!util.InputValidator.isValidClearanceLevel(level)) {
                System.out.println("Invalid clearance level. Use Alpha, Omega, or Eclipse.");
            }

        } while (!util.InputValidator.isValidClearanceLevel(level));

        return level;
    }

    // Prompts until a valid date format is entered.
    private String promptDate(String label, boolean required) {

        String date;

        do {

            System.out.print(label + ": ");
            date = scanner.nextLine().trim();

            // Allow blank optional dates.
            if (!required && date.isEmpty()) {
                return "";
            }

            if (!util.InputValidator.isValidDate(date)) {

                System.out.println(
                        "Invalid date format. Use YYYY-MM-DD."
                );
            }

        } while (!util.InputValidator.isValidDate(date));

        return date;
    }

    // Displays the Warden certifications submenu.
    private void certificationsMenu() {

        boolean managing = true;

        while (managing) {

            System.out.println();
            System.out.println("[ MANAGE CERTIFICATIONS ]");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Add Certification");
            System.out.println("2. View Certifications");
            System.out.println("3. Mark Certification Expired");
            System.out.println("4. Remove Certification");
            System.out.println("5. Return to Main Menu");
            System.out.println();

            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                case "2":
                case "3":
                case "4":
                    printCertificationSimulation();
                    break;

                case "5":
                    managing = false;
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    // Displays the employment status management submenu.
    private void employmentStatusMenu() {

        boolean managing = true;

        while (managing) {

            System.out.println();
            System.out.println("[ EMPLOYMENT STATUS MANAGEMENT ]");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Place Warden On Leave");
            System.out.println("2. Reactivate Warden");
            System.out.println("3. Terminate Warden");
            System.out.println("4. Return to Main Menu");
            System.out.println();

            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                case "2":
                case "3":
                    printDeactivateTerminateSimulation();
                    break;

                case "4":
                    managing = false;
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    // Allows optional fields to be left blank.
    private String promptOptional(String label) {

        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    // Collects and validates new Warden information.
    private void addNewWarden() {

        System.out.println();
        System.out.println("ADD NEW WARDEN");
        System.out.println("---------------------------------------------------------");

        String firstName = promptRequired("First Name");

        String lastName = promptOptional("Last Name");

        String email = promptEmail();

        String identifierType = promptIdentifierType();

        String identifierValue;

        do {
            identifierValue = promptRequired("Identifier Value");

            if (service.identifierExists(identifierType, identifierValue)) {
                System.out.println("That identifier already exists. Please enter a unique value.");
            }

        } while (service.identifierExists(identifierType, identifierValue));

        String role = promptRole();

        String employmentStatus = promptEmploymentStatus();

        String clearanceLevel = promptClearanceLevel();

        String startDate = promptDate("Start Date (YYYY-MM-DD)", true);

        String endDate = promptDate("End Date (YYYY-MM-DD)", false);

        while (!util.InputValidator.isValidDateRange(startDate, endDate)) {

            System.out.println(
                    "End Date cannot be before Start Date."
            );

            endDate = promptDate("End Date (YYYY-MM-DD)", false);
        }

        printAddWardenRequest(
                firstName,
                lastName,
                email,
                identifierType,
                identifierValue,
                role,
                employmentStatus,
                clearanceLevel,
                startDate,
                endDate
        );
    }

    // Prints the simulated request that would be sent to the backend.
    private void printAddWardenRequest(String firstName,
                                       String lastName,
                                       String email,
                                       String identifierType,
                                       String identifierValue,
                                       String role,
                                       String employmentStatus,
                                       String clearanceLevel,
                                       String startDate,
                                       String endDate) {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("HTTP Method: POST");
        System.out.println("Endpoint: /api/wardens");
        System.out.println("Description: Create a new Warden onboarding record");
        System.out.println();
        System.out.println("Payload:");
        System.out.println("{");
        System.out.println("  \"firstName\": \"" + firstName + "\",");
        System.out.println("  \"lastName\": \"" + lastName + "\",");
        System.out.println("  \"email\": \"" + email + "\",");
        System.out.println("  \"identifierType\": \"" + identifierType + "\",");
        System.out.println("  \"identifierValue\": \"" + identifierValue + "\",");
        System.out.println("  \"role\": \"" + role + "\",");
        System.out.println("  \"employmentStatus\": \"" + employmentStatus + "\",");
        System.out.println("  \"clearanceLevel\": \"" + clearanceLevel + "\",");
        System.out.println("  \"startDate\": \"" + startDate + "\",");
        System.out.println("  \"endDate\": \"" + endDate + "\"");
        System.out.println("}");
        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");
        System.out.println("No data was permanently saved.");
    }

    // Shows the request preview for updating a Warden.
    private void printUpdateWardenSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: Update Warden");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- wardenId");
        System.out.println("- field to update");
        System.out.println("- new value");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Warden ID must exist");
        System.out.println("- Updated values cannot be blank");
        System.out.println("- Role, status, clearance, and dates must match system rules");

        System.out.println();
        System.out.println("HTTP Method: PUT");
        System.out.println("Endpoint: /api/wardens/{id}");

        System.out.println();
        System.out.println("Payload:");
        System.out.println("{");
        System.out.println("  \"wardenId\": 1,");
        System.out.println("  \"field\": \"role\",");
        System.out.println("  \"newValue\": \"Trainer\"");
        System.out.println("}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Shows the request preview for managing Warden certifications.
    private void printCertificationSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: Manage Certifications");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- wardenId");
        System.out.println("- certification name");
        System.out.println("- date earned");
        System.out.println("- optional expiration date");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Warden ID must exist");
        System.out.println("- Certification name cannot be blank");
        System.out.println("- Dates must use YYYY-MM-DD format");
        System.out.println("- Expiration date cannot be before date earned");

        System.out.println();
        System.out.println("HTTP Method: POST");
        System.out.println("Endpoint: /api/wardens/{id}/certifications");

        System.out.println();
        System.out.println("Payload:");
        System.out.println("{");
        System.out.println("  \"wardenId\": 1,");
        System.out.println("  \"certificationName\": \"Containment Safety\",");
        System.out.println("  \"dateEarned\": \"2025-01-20\",");
        System.out.println("  \"expirationDate\": \"2027-01-20\"");
        System.out.println("}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Shows the request preview for viewing a Warden by ID.
    private void printViewByIdSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: View Warden by ID");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- wardenId");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Warden ID must exist");

        System.out.println();
        System.out.println("HTTP Method: GET");
        System.out.println("Endpoint: /api/wardens/{id}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Shows the request preview for viewing Wardens by employment status.
    private void printViewByStatusSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: View Wardens by Employment Status");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- employment status");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Status must match Neon Ark system rules");

        System.out.println();
        System.out.println("HTTP Method: GET");
        System.out.println("Endpoint: /api/wardens/status/{status}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Shows the request preview for viewing Wardens by role.
    private void printViewByRoleSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: View Wardens by Role");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- role");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Role must match Neon Ark system rules");

        System.out.println();
        System.out.println("HTTP Method: GET");
        System.out.println("Endpoint: /api/wardens/role/{role}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    // Displays the Update Warden submenu.
    private void updateWardenMenu() {

        boolean updating = true;

        while (updating) {

            System.out.println();
            System.out.println("[ UPDATE WARDEN ]");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Update Role");
            System.out.println("2. Update Clearance Level");
            System.out.println("3. Update Employment Status");
            System.out.println("4. Update Start Date");
            System.out.println("5. Update End Date");
            System.out.println("6. Update Email");
            System.out.println("7. Return to Main Menu");
            System.out.println();

            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                    printUpdateWardenSimulation();
                    break;

                case "7":
                    updating = false;
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    // Shows the request preview for deactivating or terminating a Warden.
    private void printDeactivateTerminateSimulation() {

        System.out.println();
        System.out.println("SIMULATED REQUEST");
        System.out.println("---------------------------------------------------------");
        System.out.println("Action: Deactivate / Terminate Warden");
        System.out.println();

        System.out.println("Inputs Required:");
        System.out.println("- wardenId");
        System.out.println("- updated employment status");
        System.out.println("- end date");

        System.out.println();
        System.out.println("Validation:");
        System.out.println("- Warden ID must exist");
        System.out.println("- Status must match Neon Ark system rules");
        System.out.println("- End date must use YYYY-MM-DD format");
        System.out.println("- End date cannot be before the start date");

        System.out.println();
        System.out.println("HTTP Method: PUT");
        System.out.println("Endpoint: /api/wardens/{id}/status");

        System.out.println();
        System.out.println("Payload:");
        System.out.println("{");
        System.out.println("  \"wardenId\": 6,");
        System.out.println("  \"employmentStatus\": \"Terminated\",");
        System.out.println("  \"endDate\": \"2025-06-30\"");
        System.out.println("}");

        System.out.println();
        System.out.println("Result: SUCCESS (simulated)");

        System.out.println();
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }
}