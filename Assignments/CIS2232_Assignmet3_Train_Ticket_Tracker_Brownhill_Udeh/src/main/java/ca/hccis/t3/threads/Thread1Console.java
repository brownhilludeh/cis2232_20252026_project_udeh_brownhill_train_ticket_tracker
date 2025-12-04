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

public class Thread1Console extends Thread {

    public static final String EXIT = "X";
    public static final String MENU = "A) Add passenger's data" + System.lineSeparator()
            + "V) View passenger's information" + System.lineSeparator()
            + EXIT + ") Exit" + System.lineSeparator();

    private static final String MESSAGE_ERROR = "Error";
    private static final String MESSAGE_EXIT = "Goodbye";
    private static final String PATH = "e:\\CIS2232\\";
    private static final String FILE_NAME = "data_udeh_brownhill.json";

    private Path dataPath;
    private FileWriter dataWriter;
    private CisUtility cisUtility;

    @Override
    public void run() {
        cisUtility = new CisUtility();
        cisUtility.setIsGUI(false);

        try {
            // Setup file
            dataPath = Paths.get(PATH, FILE_NAME);
            Files.createDirectories(dataPath.getParent());

            if (Files.notExists(dataPath)) {
                Files.createFile(dataPath);
                System.out.println("New File created: " + dataPath.toAbsolutePath());
            } else {
                System.out.println("File already exists: " + dataPath.toAbsolutePath());
            }

            dataWriter = new FileWriter(dataPath.toFile(), true);

        } catch (IOException e) {
            System.out.println("Error setting up file: " + e.getMessage());
            return;
        }

        // Menu loop
        String menuOption;
        do {
            menuOption = cisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {
                case "A":
                    processAdd();
                    break;
                case "V":
                    processShow();
                    break;
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
            }

        } while (!menuOption.equals(EXIT));

        // Close file
        try {
            if (dataWriter != null)
                dataWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processAdd() {
        TrainTicketTracker passenger = new TrainTicketTracker(cisUtility);
        passenger.getInformation();

        try {
            dataWriter.write(passenger.toJson() + System.lineSeparator());
            dataWriter.flush();
            System.out.println("Passenger data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    private void processShow() {
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
        }
    }
}
