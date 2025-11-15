package ca.hccis.t3.controllers;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.bo.TrainTicketValidationBO;
import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.repositories.TrainTicketRepository;
import ca.hccis.t3.repositories.CodeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller to administer train tickets. Note that the code was taken from
 *
 * @author Brownhill Udeh
 * @since 2025-10-31
 */
@Controller
@RequestMapping("/ticket")
public class TrainTicketController {

    private final TrainTicketRepository _ttr;
    private final CodeValueRepository _cvr;

    @Autowired
    public TrainTicketController(TrainTicketRepository bpr, CodeValueRepository cvr) {
        _ttr = bpr;
        _cvr = cvr;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(TrainTicketController.class);

    /**
     * Returns the list of tickets view.
     *
     * @param model
     *            the model object which will be used to pass data to the view
     * @param session
     *            the session object which will be used to store data
     * @return the view to list all tickets
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        Iterable<TrainTicket> tickets = _ttr.findAll();

        model.addAttribute("tickets", tickets);
        model.addAttribute("ticket", new TrainTicket());
        return "ticket/list";
    }


    /**
     * Deletes a ticket with a given id.
     *
     * @param id the id of the ticket to be deleted
     * @return a redirect to the ticket list page
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        _ttr.deleteById(id);
        return "redirect:/ticket";
    }


    /**
     * Add a new ticket. Set default values for the ticket.
     *
     * @param model
     * @param session
     * @return the view to add a ticket
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

//        TrainTicketBO.setTicketTypes(_cvr, session);
//        TrainTicketBO.setTicketTypes(_cvr, session);
        TrainTicket ticket = new TrainTicket();
        TrainTicketBO.setTicketDefaults(ticket);
        model.addAttribute("ticket", ticket);
        return "ticket/add";
    }

    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

//        TrainTicketBO.setTicketTypes(_cvr, session);

        Optional ticket = _ttr.findById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "ticket/add";
        }

        //todo How can we communcicate this to the view.
        model.addAttribute("message", "Could not load the train ticket");
        return "redirect:/ticket";
    }


    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param @request
     * @param ticket       what is being added or modified
     * @param bindingResult Result of SQL
     * @return add with errors or ticket
     * @author Brownhill Udeh
     * @since 2025-10-31
     */
    @RequestMapping("/submit")
    public String submit(
            Model model,
            @Valid @ModelAttribute("ticket") TrainTicket ticket,
            BindingResult bindingResult
    ) {
        // Set default ticketPrice before validation
        if (ticket.getTicketPrice() == null) {
            ticket.setTicketPrice(BigDecimal.ZERO);
        }

        // Custom validation (issue date)
        TrainTicketValidationBO ttvbo = new TrainTicketValidationBO();
        ArrayList<String> validationErrorsStartDate = ttvbo.validateIssueDate(ticket);
        for (String err : validationErrorsStartDate) {
            bindingResult.rejectValue("issueDate", "error.ticket", err);
        }

        // Check for any validation errors
        if (bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - Brownhill Udeh");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");

            model.addAttribute("businessValidationErrorsStartDate", validationErrorsStartDate);
            return "ticket/list";
        }

        // Calculate the actual ticket price after validation passes
        TrainTicketBO.calculateTicketPrice(ticket);

        // Save ticket
        _ttr.save(ticket);

        return "redirect:/ticket";
    }


    /**
     * Search for a customer name
     *
     * @param model
     * @param ticket
     * @return view for list
     * @author Brownhill Udeh
     * @since 2025-11-02
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("ticket") TrainTicket ticket) {

        //**********************************************************************
        //Use repository method created to find any entities which contain
        //the name entered on the list page.
        //**********************************************************************
        model.addAttribute("tickets", _ttr.findByNameContaining(ticket.getName()));
        logger.debug("searched for ticket name:" + ticket.getName());
        return "ticket/list";
    }
}
