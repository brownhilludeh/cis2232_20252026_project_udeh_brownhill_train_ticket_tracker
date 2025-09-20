package ca.hccis.t3;

import ca.hccis.t3.entity.TrainTicketTracker;
import ca.hccis.t3.service.PassengerService;
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

    private final PassengerService passengerService;

    // Inject the service via constructor
    public TrainTicketController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/all")
    public String showPassengers(Model model) {
        model.addAttribute("passengers", passengerService.getAllPassengers());
        return "passengers";
    }

    @GetMapping("/add")
    public String addPassengerForm(Model model) {
        model.addAttribute("passenger", new TrainTicketTracker());
        return "addPassenger";
    }

    @PostMapping("/add")
    public String addPassengerSubmit(@ModelAttribute TrainTicketTracker passenger) {
        passengerService.addPassenger(passenger); // save to DB
        return "redirect:/tickets/all";
    }
}
