package service;

import data.WardenFileLoader;
import model.Warden;

import java.util.List;

// Handles Warden-related business operations.
public class WardenService {

    private List<Warden> wardens;

    // Loads the starting Warden snapshot.
    public WardenService() {

        // Load the read-only starting data when the service starts.
        WardenFileLoader loader = new WardenFileLoader();
        wardens = loader.loadWardens();
    }

    // Returns all wardens from the read-only data file.
    public List<Warden> getAllWardens() {
        return wardens;
    }

    // Checks if the identifier type and value already exist together.
    public boolean identifierExists(String identifierType, String identifierValue) {

        for (Warden warden : wardens) {

            boolean sameType =
                    warden.getIdentifierType().equalsIgnoreCase(identifierType);

            boolean sameValue =
                    warden.getIdentifierValue().equalsIgnoreCase(identifierValue);

            if (sameType && sameValue) {
                return true;
            }
        }

        return false;
    }
}