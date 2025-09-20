package ca.hccis.t3.service;


import ca.hccis.t3.entity.TrainTicketTracker;
import ca.hccis.t3.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//import ca.hccis.t3.entity.TrainTicketTracker;
//import com.google.gson.Gson;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;

@Service
public class PassengerService {


    private final PassengerRepository repository;

    public PassengerService(PassengerRepository repository) {
        this.repository = repository;
    }

    public void addPassenger(TrainTicketTracker passenger) {
        repository.save(passenger);
    }

    public List<TrainTicketTracker> getAllPassengers() {
        return repository.findAll();
    }

//    private static final String PATH = "e:\\CIS2232\\";
//    private static final String FILE_NAME = "data_udeh_brownhill.json";
//    private final Path dataPath;
//    private final Gson gson = new Gson();
//
//    public PassengerService() {
//        dataPath = Paths.get(PATH + FILE_NAME);
//
//        try {
//            if (!Files.exists(dataPath)) {
//                new File(dataPath.toString()).createNewFile();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error creating file", e);
//        }
//    }
//
//    public void addPassenger(TrainTicketTracker passenger) {
//        try (FileWriter writer = new FileWriter(dataPath.toString(), true)) {
//            writer.write(passenger.toJson() + System.lineSeparator());
//        } catch (IOException e) {
//            throw new RuntimeException("Error writing to file", e);
//        }
//    }
//
//    public List<TrainTicketTracker> getAllPassengers() {
//        List<TrainTicketTracker> passengers = new ArrayList<>();
//        try {
//            List<String> lines = Files.readAllLines(dataPath);
//            for (String line : lines) {
//                passengers.add(gson.fromJson(line, TrainTicketTracker.class));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error reading file", e);
//        }
//        return passengers;
//    }
}
