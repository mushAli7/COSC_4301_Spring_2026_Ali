package util;

import model.CreatureResponse;
import model.CreatureObservationResponse;
import model.ObservationResponse;
import model.FeedingLookupResponse;
import model.FeedingResponse;
import model.AdminUserResponse;

import java.util.List;

/*
 * Handles formatted console table output for CLI screens.
 */
public class TablePrinter {

    /*
     * Displays all creatures in a formatted table layout.
     */
    public static void printCreatureTable(List<CreatureResponse> creatures) {

        System.out.println();
        System.out.println("==================================================================================================================");
        System.out.println("CREATURE MANAGEMENT LIST");
        System.out.println("==================================================================================================================");

        System.out.printf(
                "%-5s %-15s %-20s %-10s %-15s %-20s%n",
                "ID",
                "NAME",
                "SPECIES",
                "DANGER",
                "STATUS",
                "HABITAT"
        );

        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (CreatureResponse creature : creatures) {

            System.out.printf(
                    "%-5d %-15s %-20s %-10s %-15s %-20s%n",
                    creature.creatureId,
                    creature.name,
                    creature.species,
                    creature.dangerLevel,
                    creature.status,
                    creature.habitatName
            );
        }

        System.out.println("==================================================================================================================");
    }
    /*
     * Displays one creature record in a readable detail view.
     */
    public static void printCreatureDetail(CreatureResponse creature) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("CREATURE DETAIL");
        System.out.println("==================================================");
        System.out.println("ID:          " + creature.creatureId);
        System.out.println("Name:        " + creature.name);
        System.out.println("Species:     " + creature.species);
        System.out.println("Danger:      " + creature.dangerLevel);
        System.out.println("Status:      " + creature.status);
        System.out.println("Habitat:     " + creature.habitatName);
        System.out.println("Notes:       " + creature.notes);
        System.out.println("Created At:  " + creature.createdAt);
        System.out.println("==================================================");
    }

    /*
     * Displays creature observations in a readable format.
     */
    public static void printCreatureObservations(
            CreatureObservationResponse response) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("CREATURE OBSERVATIONS");
        System.out.println("==================================================");

        System.out.println("Creature: " + response.creature.name);
        System.out.println("Species:  " + response.creature.species);
        System.out.println("Status:   " + response.creature.status);

        System.out.println("==================================================");

        if (response.observations == null
                || response.observations.isEmpty()) {

            System.out.println("No observations found.");
            System.out.println("==================================================");
            return;
        }

        for (ObservationResponse observation : response.observations) {

            System.out.println();
            System.out.println("Observation ID: " + observation.observationId);
            System.out.println("Author:         " + observation.authorName
                    + " (" + observation.authorUsername + ")");
            System.out.println("Observed At:    " + observation.observedAt);
            System.out.println("Notes:");
            System.out.println(observation.observationText);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("==================================================");
    }

    /*
     * Displays feeding schedule lookup results.
     */
    public static void printFeedingResults(
            FeedingLookupResponse response) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("FEEDING SCHEDULE RESULTS");
        System.out.println("==================================================");

        System.out.println(response.message);

        if (response.feedings == null
                || response.feedings.isEmpty()) {

            System.out.println("==================================================");
            return;
        }

        for (FeedingResponse feeding : response.feedings) {

            System.out.println();
            System.out.println("Creature ID:  " + feeding.creatureId);
            System.out.println("Creature:     " + feeding.creatureName);
            System.out.println("Habitat:      " + feeding.habitatName);
            System.out.println("Feeding Time: " + feeding.feedingTime);
            System.out.println("Food Type:    " + feeding.foodType);
            System.out.println("Notes:        " + feeding.notes);

            System.out.println("--------------------------------------------------");
        }

        System.out.println("==================================================");
    }

    /*
     * Displays all system users in table format.
     */
    public static void printAdminUsers(
            List<AdminUserResponse> users) {

        System.out.println();
        System.out.println("========================================================================================");
        System.out.println("SYSTEM USERS");
        System.out.println("========================================================================================");

        System.out.printf(
                "%-5s %-20s %-20s %-15s %-15s %-12s%n",
                "ID",
                "FULL NAME",
                "EMAIL",
                "PHONE",
                "USERNAME",
                "ROLE"
        );

        System.out.println("----------------------------------------------------------------------------------------");

        for (AdminUserResponse user : users) {

            System.out.printf(
                    "%-5d %-20s %-20s %-15s %-15s %-12s%n",
                    user.userId,
                    user.fullName,
                    user.email,
                    user.phone,
                    user.username,
                    user.role
            );
        }

        System.out.println("========================================================================================");
    }
}