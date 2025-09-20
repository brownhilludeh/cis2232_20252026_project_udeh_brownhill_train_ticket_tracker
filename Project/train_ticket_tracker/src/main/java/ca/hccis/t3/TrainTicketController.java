package ca.hccis.t3;

import ca.hccis.t3.entity.TrainTicketTracker;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TrainTicketController {

    private static final String PATH = "e:\\CIS2232\\";
    private static final String FILE_NAME = "data_udeh_brownhill.json";
    private static final Path dataPath = Paths.get(PATH + FILE_NAME);
    private static final Gson gson = new Gson();

    static {
        // Ensure the file exists
        try {
            File file = new File(dataPath.toString());
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error creating data file", e);
        }
    }

    // Home page
    @GetMapping("/")
    public String home() {
        return "index"; // src/main/resources/templates/index.html
    }

    // Show all passengers
    @GetMapping("/all")
    public String showPassengers(Model model) {
        List<TrainTicketTracker> passengers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                passengers.add(gson.fromJson(line, TrainTicketTracker.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("passengers", passengers);
        return "passengers"; // templates/passengers.html
    }

    // Form to add passenger
    @GetMapping("/add")
    public String addPassengerForm(Model model) {
        model.addAttribute("passenger", new TrainTicketTracker());
        return "addPassenger"; // templates/addPassenger.html
    }

    // Handle form submission
    @PostMapping("/add")
    public String addPassengerSubmit(@ModelAttribute TrainTicketTracker passenger, Model model) {
        try (FileWriter writer = new FileWriter(dataPath.toString(), true)) {
            writer.write(passenger.toJson() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/tickets/all";
    }
}
