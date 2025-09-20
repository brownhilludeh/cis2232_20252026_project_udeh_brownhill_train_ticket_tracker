package ca.hccis.t3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Train Ticket Tracker!");
        return "index"; // points to src/main/resources/templates/index.html
    }
}
