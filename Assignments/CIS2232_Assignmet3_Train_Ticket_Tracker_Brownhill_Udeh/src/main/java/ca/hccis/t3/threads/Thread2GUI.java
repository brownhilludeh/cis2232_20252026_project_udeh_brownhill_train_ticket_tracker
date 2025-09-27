package ca.hccis.t3.threads;

import ca.hccis.t3.entity.TrainTicketTracker;
import ca.hccis.t3.util.CisUtility;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Thread2GUI implements Runnable {

    public static final String EXIT = "X";
    public static final String MENU = "A) Add passenger's data\n" +
            "V) View passenger's information\n" +
            EXIT + ") Exit";

    private static final String PATH = "e:\\CIS2232\\";
    private static final String FILE_NAME = "data_udeh_brownhill.json";

    private Path dataPath;
    private FileWriter dataWriter;
    private CisUtility cisUtility;

    @Override
    public void run() {
        cisUtility = new CisUtility();
        cisUtility.setIsGUI(true);

        try {
            // Setup file
            dataPath = Paths.get(PATH, FILE_NAME);
            Files.createDirectories(dataPath.getParent());

            if (Files.notExists(dataPath)) {
                Files.createFile(dataPath);
                cisUtility.display("New File created: " + dataPath.toAbsolutePath());
            } else {
                cisUtility.display("File already exists: " + dataPath.toAbsolutePath());
            }

            dataWriter = new FileWriter(dataPath.toFile(), true);

        } catch (IOException e) {
            cisUtility.display("Error setting up file: " + e.getMessage());
            return;
        }

        // Menu loop
        boolean running = true;
        while (running) {
            String menuOption = cisUtility.getInputString(MENU);
            if (menuOption == null)
                continue; // user closed dialog
            menuOption = menuOption.toUpperCase();

            switch (menuOption) {
                case "A":
                    processAdd();
                    break;
                case "V":
                    processShow();
                    break;
                case EXIT:
                    running = false;
                    cisUtility.display("Goodbye!");
                    break;
                default:
                    cisUtility.display("Invalid choice, try again.");
            }
        }

        // Close file
        try {
            if (dataWriter != null)
                dataWriter.close();
        } catch (IOException e) {
            cisUtility.display("Error closing file: " + e.getMessage());
        }
    }

    private void processAdd() {
        TrainTicketTracker passenger = new TrainTicketTracker(cisUtility);
        passenger.getInformation();

        try {
            dataWriter.write(passenger.toJson() + System.lineSeparator());
            dataWriter.flush();
            cisUtility.display("Passenger data saved successfully!");
        } catch (IOException e) {
            cisUtility.display("Error writing to file: " + e.getMessage());
        }
    }

    private void processShow() {
        Gson gson = new Gson();

        try {
            List<String> lines = Files.readAllLines(dataPath);
            if (lines.isEmpty()) {
                cisUtility.display("No passenger data found");
            } else {
                StringBuilder sb = new StringBuilder("Passenger Information:\n");
                for (String line : lines) {
                    TrainTicketTracker passenger = gson.fromJson(line, TrainTicketTracker.class);
                    sb.append(passenger.toString()).append("\n\n");
                }
                cisUtility.display(sb.toString());
            }
        } catch (IOException e) {
            cisUtility.display("Error reading file: " + e.getMessage());
        }
    }
}
