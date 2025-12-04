package ca.hccis.t3;

import ca.hccis.t3.entity.TrainTicketTracker;
import ca.hccis.t3.util.CisUtility;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add passenger's data" + System.lineSeparator()
            + "V) View passenger's information" + System.lineSeparator()
            + EXIT + ") Exit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String PATH = "e:\\CIS2232\\";
    public static final String FILE_NAME = "data_udeh_brownhill.json";

    private static Path dataPath;
    private static FileWriter dataWriter;

    public static void main(String[] args) {

        dataPath = Paths.get(PATH, FILE_NAME);

        try {
            // Ensure parent directories exist
            Files.createDirectories(dataPath.getParent());

            // Create file if it doesn't exist
            if (Files.notExists(dataPath)) {
                Files.createFile(dataPath);
                System.out.println("New File created: " + dataPath.toAbsolutePath());
            } else {
                System.out.println("File already exists: " + dataPath.toAbsolutePath());
            }

            // Open writer (append mode)
            dataWriter = new FileWriter(dataPath.toFile(), true);

        } catch (IOException e) {
            System.out.println("Error setting up file: " + e.getMessage());
            throw new RuntimeException(e);
        }


        String menuOption;
        do {
            menuOption = CisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                case "A":
                    processAdd();
                    break;
                case "V":
                    processShow();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
            }
        } while (!menuOption.equals(EXIT));

        try {
            dataWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add passenger data and save to file
     */
    public static void processAdd() {
        System.out.println("Processing option A");

        TrainTicketTracker passenger = new TrainTicketTracker();
        passenger.getInformation();

        try {
            dataWriter.write(passenger.toJson() + System.lineSeparator());
            dataWriter.flush();
            System.out.println("Passenger data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        }
    }

    /**
     * Read all passenger data from file and display
     */
    public static void processShow() {
        System.out.println("Processing option V");
        Gson gson = new Gson();

        try {
            List<String> lines = Files.readAllLines(dataPath);
            if (lines.isEmpty()) {
                System.out.println("No passenger data found");
            } else {
                System.out.println("Passenger Information:");
                for (String line : lines) {
                    TrainTicketTracker passenger = gson.fromJson(line, TrainTicketTracker.class);
                    System.out.println(passenger.toString());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }
    }
}
