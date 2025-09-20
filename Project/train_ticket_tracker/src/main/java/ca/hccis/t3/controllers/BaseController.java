package ca.hccis.t3.controllers;

import ca.hccis.t3.entity.TrainTicketTracker;
import ca.hccis.t3.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BaseController {

    private final PassengerService passengerService;

    public BaseController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // menu page
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("passenger", new TrainTicketTracker());
        return "add"; // form page
    }

    @PostMapping("/add")
    public String addPassenger(TrainTicketTracker passenger) {
        passengerService.addPassenger(passenger);
        return "redirect:/show"; // will go to the list of all passengers
    }


    @GetMapping("/show")
    public String showPassengers(Model model) {
        model.addAttribute("passengers", passengerService.getAllPassengers());
        return "show"; // display page
    }
}
