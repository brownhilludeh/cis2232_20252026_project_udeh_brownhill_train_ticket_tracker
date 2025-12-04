package ca.hccis.t3.controllers;

import ca.hccis.t3.repositories.TrainTicketRepository;
import ca.hccis.t3.util.CisUtility;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @Autowired
    private TrainTicketRepository _ttr;

    public BaseController(TrainTicketRepository ttr) {
        _ttr = ttr;
    }

    /**
     * Send the user to the home page.
     *
     * @param session
     *                the session object which will be used to store data
     * @return the view to list all tickets
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("/")
    public String home(HttpSession session) {

        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        return "index";
    }

    /**
     * Redirects the user to the about page.
     *
     * @return the view to list all tickets
     * @author Brownhill Udeh
     * @since 2025-11-03
     */
    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
