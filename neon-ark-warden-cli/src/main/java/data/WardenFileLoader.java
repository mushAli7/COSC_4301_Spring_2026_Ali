// CSV snapshot is treated as read-only seeded data.
// No records are added, removed, or modified.

package data;

import model.Warden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Reads Warden data from the CSV file.
public class WardenFileLoader {

    // CSV snapshot is read-only seeded data.
    // This program does not add, remove, or modify CSV records.
    public List<Warden> loadWardens() {
        List<Warden> wardens = new ArrayList<>();

        // Reads the CSV file from the resource's folder.
        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("wardens.csv");

            if (inputStream == null) {
                System.out.println("Could not find wardens.csv");
                return wardens;
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream));

            String line;

            // Skip header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                // Keeps empty values like blank last names.
                String[] data = line.split(",", -1);

                if (data.length != 11) {
                    System.out.println("Skipping malformed CSV row.");
                    continue;
                }

                Warden warden = new Warden(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8],
                        data[9],
                        data[10]
                );

                wardens.add(warden);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading wardens file.");
        }

        return wardens;
    }
}