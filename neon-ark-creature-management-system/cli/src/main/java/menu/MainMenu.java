package menu;

import api.CreatureApi;
import model.CreatureResponse;
import model.CreateCreatureRequest;
import util.InputHelper;
import util.TablePrinter;
import model.RenameCreatureRequest;
import model.RenameCreatureResponse;
import model.MessageResponse;
import model.CreatureObservationResponse;
import model.FeedingLookupResponse;
import model.AdminUserResponse;
import api.ApiException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
 * Handles the interactive menu for the Neon Ark CLI.
 */
public class MainMenu {

    private final CreatureApi creatureApi;
    private final Scanner scanner;

    public MainMenu(CreatureApi creatureApi) {
        this.creatureApi = creatureApi;
        this.scanner = new Scanner(System.in);
    }

    public void run() {

        boolean running = true;

        while (running) {
            printMainMenu();

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                listAllCreatures();
            }
            else if (choice.equals("2")) {
                viewCreatureById();
            }
            else if (choice.equals("3")) {
                registerCreature();
            }
            else if (choice.equals("4")) {
                renameCreature();
            }
            else if (choice.equals("5")) {
                removeCreature();
            }
            else if (choice.equals("6")) {
                viewCreatureObservations();
            }
            else if (choice.equals("7")) {
                findFeedingsByTime();
            }
            else if (choice.equals("8")) {
                viewAllSystemUsers();
            }
            else if (choice.equals("0")) {
                running = !confirmExit();
            }
            else {
                System.out.println("Invalid option. Please select a menu number shown above.");
            }
        }

        System.out.println("Neon Ark CLI has been closed.");
    }

    private void printMainMenu() {

        System.out.println();
        System.out.println("=====================================");
        System.out.println("       NEON ARK CLI SYSTEM");
        System.out.println("=====================================");
        System.out.println();
        System.out.println("1. List all creatures");
        System.out.println("2. View creature by ID");
        System.out.println("3. Register new creature");
        System.out.println("4. Rename creature");
        System.out.println("5. Remove creature");
        System.out.println("6. View creature observations/notes");
        System.out.println("7. Find creatures by feeding time");
        System.out.println();
        System.out.println("--- Admin Only ---");
        System.out.println("8. View all system users");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
        System.out.print("Select an option: ");
    }

    private void listAllCreatures() {

        try {
            List<CreatureResponse> creatures = creatureApi.listAllCreatures();
            TablePrinter.printCreatureTable(creatures);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to retrieve creatures. Please verify the backend is running.");
        }

        pause();
    }

    private void viewCreatureById() {

        System.out.print("Enter creature ID: ");
        String input = scanner.nextLine().trim();

        try {
            Long creatureId = Long.parseLong(input);

            CreatureResponse creature = creatureApi.getCreatureById(creatureId);
            TablePrinter.printCreatureDetail(creature);
        }
        catch (NumberFormatException exception) {
            System.out.println("Invalid ID. Please enter a whole number.");
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private void registerCreature() {

        CreateCreatureRequest request = new CreateCreatureRequest();

        System.out.println();
        System.out.println("=== REGISTER NEW CREATURE ===");

        request.name = InputHelper.readNameText(
                scanner,
                "Name: "
        );

        request.species = InputHelper.readNameText(
                scanner,
                "Species: "
        );

        request.dangerLevel = InputHelper.readMenuChoice(
                scanner,
                "Danger Level (LOW/MEDIUM/HIGH): ",
                "LOW",
                "MEDIUM",
                "HIGH"
        );

        request.status = InputHelper.readMenuChoice(
                scanner,
                "Status (ACTIVE/QUARANTINED/CRITICAL/REMOVED): ",
                "ACTIVE",
                "QUARANTINED",
                "CRITICAL",
                "REMOVED"
        );

        request.notes = InputHelper.readOptionalText(
                scanner,
                "Notes: "
        );

        request.habitatId = InputHelper.readPositiveLong(
                scanner,
                "Habitat ID: "
        );

        try {

            CreatureResponse creature =
                    creatureApi.createCreature(request);

            System.out.println();
            System.out.println("Creature registered successfully.");

            TablePrinter.printCreatureDetail(creature);

        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private void renameCreature() {

        System.out.println();
        System.out.println("=== RENAME CREATURE ===");

        Long creatureId = InputHelper.readPositiveLong(
                scanner,
                "Creature ID: "
        );

        String newName = InputHelper.readNameText(
                scanner,
                "New creature name: "
        );

        System.out.print("Confirm rename? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Rename cancelled.");
            pause();
            return;
        }

        RenameCreatureRequest request = new RenameCreatureRequest();
        request.newName = newName;

        try {
            RenameCreatureResponse response =
                    creatureApi.renameCreature(creatureId, request);

            System.out.println();
            System.out.println(response.message);
            System.out.println("Creature ID: " + response.creatureId);
            System.out.println("Old Name:    " + response.oldName);
            System.out.println("New Name:    " + response.newName);
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private void removeCreature() {

        System.out.println();
        System.out.println("=== REMOVE CREATURE ===");

        Long creatureId = InputHelper.readPositiveLong(
                scanner,
                "Creature ID: "
        );

        System.out.print("Confirm removal? This will mark the creature as REMOVED. (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Removal cancelled.");
            pause();
            return;
        }

        try {
            MessageResponse response =
                    creatureApi.removeCreature(creatureId);

            System.out.println(response.message);
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {

            System.out.println(
                    "Unable to connect to the backend service."
            );
        }

        pause();
    }

    private void viewCreatureObservations() {

        System.out.println();
        System.out.println("=== VIEW CREATURE OBSERVATIONS ===");

        Long creatureId = InputHelper.readPositiveLong(
                scanner,
                "Creature ID: "
        );

        try {
            CreatureObservationResponse response =
                    creatureApi.getCreatureObservations(creatureId);

            TablePrinter.printCreatureObservations(response);
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private void findFeedingsByTime() {

        System.out.println();
        System.out.println("=== FIND CREATURES BY FEEDING TIME ===");

        String time = InputHelper.readTimeValue(
                scanner,
                "Feeding time (HH:MM): "
        );

        try {
            FeedingLookupResponse response =
                    creatureApi.findFeedingsByTime(time);

            TablePrinter.printFeedingResults(response);
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private void viewAllSystemUsers() {

        System.out.println();
        System.out.println("=== VIEW ALL SYSTEM USERS ===");

        try {
            List<AdminUserResponse> users =
                    creatureApi.getAllUsers();

            TablePrinter.printAdminUsers(users);
        }
        catch (ApiException exception) {
            printApiError(exception);
        }
        catch (IOException | InterruptedException exception) {
            System.out.println("Unable to connect to the backend service.");
        }

        pause();
    }

    private boolean confirmExit() {

        System.out.print("Are you sure you want to exit? (Y/N): ");
        String answer = scanner.nextLine().trim();

        return answer.equalsIgnoreCase("Y");
    }

    private void pause() {

        System.out.println();
        System.out.print("Press Enter to return to the main menu...");
        scanner.nextLine();
    }

    private void printApiError(ApiException exception) {

        try {
            MessageResponse response =
                    creatureApi.getObjectMapper().readValue(
                            exception.getMessage(),
                            MessageResponse.class
                    );

            System.out.println(response.message);
        }
        catch (Exception parsingException) {
            System.out.println(exception.getMessage());
        }
    }
}