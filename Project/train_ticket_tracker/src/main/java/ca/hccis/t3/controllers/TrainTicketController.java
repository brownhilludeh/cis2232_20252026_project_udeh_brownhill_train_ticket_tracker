package ca.hccis.t3.controllers;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.bo.TrainTicketValidationBO;
<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
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
 * Controller to administer bus passes. Note that the code was taken from
 * Fred Campos' project from 2021 which also had modifications from Ferhad in
 * 2022.
 *
 * @author BJM
 * @since 20241021
 */
@Controller
<<<<<<< HEAD
@RequestMapping("/ticket")
=======
@RequestMapping("/buspass")
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
public class TrainTicketController {

    private final TrainTicketRepository _bpr;
    private final CodeValueRepository _cvr;

    @Autowired
    public TrainTicketController(TrainTicketRepository bpr, CodeValueRepository cvr) {
        _bpr = bpr;
        _cvr = cvr;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(TrainTicketController.class);

    @RequestMapping("")
    public String home(Model model, HttpSession session) {

<<<<<<< HEAD
        Iterable<TrainTicket> tickets = _bpr.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("ticket", new TrainTicket());
        return "ticket/list";
=======
        Iterable<BusPass> busPasses = _bpr.findAll();
        model.addAttribute("busPasses", busPasses);
        model.addAttribute("busPass", new BusPass());
        return "buspass/list";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Page to delete a bus pass
     *
     * @param id ID
     * @return redirect to the list page
     * @author BJM
     * @since 20241021
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        _bpr.deleteById(id);
<<<<<<< HEAD
        return "redirect:/ticket";
=======
        return "redirect:/buspass";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Page to add new entity. Taken from tutor app from 2022 (which was
     * also derived from class samples)
     *
     * @param model
     * @return add
     * @author BJM
     * @since 2024-10-24
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

<<<<<<< HEAD
        TrainTicketBO.setTicketTypes(_cvr, session);
        TrainTicketBO.setTicketTypes(_cvr, session);
        TrainTicket ticket = new TrainTicket();
        TrainTicketBO.setTicketDefaults(ticket);
        model.addAttribute("ticket", ticket);
        return "ticket/add";
=======
        TrainTicketBO.setBusPassTypes(_cvr, session);
        TrainTicketBO.setBusPassTypes(_cvr, session);
        BusPass busPass = new BusPass();
        TrainTicketBO.setBusPassDefaults(busPass);
        model.addAttribute("busPass", busPass);
        return "buspass/add";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author BJM
     * @since 20241025
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

<<<<<<< HEAD
        TrainTicketBO.setTicketTypes(_cvr, session);

        Optional ticket = _bpr.findById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "ticket/add";
=======
        TrainTicketBO.setBusPassTypes(_cvr, session);

        Optional busPass = _bpr.findById(id);
        if (busPass.isPresent()) {
            model.addAttribute("busPass", busPass.get());
            return "buspass/add";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        }

        //todo How can we communcicate this to the view.
        model.addAttribute("message", "Could not load the bus pass");
<<<<<<< HEAD
        return "redirect:/ticket";
=======
        return "redirect:/buspass";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }


    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param request
<<<<<<< HEAD
     * @param ticket       what is being added or modified
     * @param bindingResult Result of SQL
     * @return add with errors or ticket
=======
     * @param busPass       what is being added or modified
     * @param bindingResult Result of SQL
     * @return add with errors or busPass
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
     * @author CIS2232
     * @since 20241024
     */
    @RequestMapping("/submit")
<<<<<<< HEAD
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("ticket") TrainTicket ticket, BindingResult bindingResult) {
        boolean valid = true;

        TrainTicketValidationBO bpvbo = new TrainTicketValidationBO();
        ArrayList<String> validationErrorsStartDate = bpvbo.validateIssueDate(ticket);
=======
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("busPass") BusPass busPass, BindingResult bindingResult) {
        boolean valid = true;

        TrainTicketValidationBO bpvbo = new TrainTicketValidationBO();
        ArrayList<String> validationErrorsStartDate = bpvbo.validateStartDate(busPass);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        if(validationErrorsStartDate.size() > 0) {
            valid = false;
        }

        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - BJM");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");
<<<<<<< HEAD
            ticket.setTicketPrice(new BigDecimal("0"));
            model.addAttribute("ticket", ticket);
            model.addAttribute("businessValidationErrorsStartDate", validationErrorsStartDate);
            return "ticket/add";
        }

        TrainTicketBO.calculateTicketPrice(ticket);
        _bpr.save(ticket);
        return "redirect:/ticket";
=======
            busPass.setCost(new BigDecimal(0));
            model.addAttribute("busPass", busPass);
            model.addAttribute("businessValidationErrorsStartDate", validationErrorsStartDate);
            return "buspass/add";
        }

        TrainTicketBO.calculateBusPassCost(busPass);
        _bpr.save(busPass);
        return "redirect:/buspass";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Search for a customer name
     *
     * @param model
<<<<<<< HEAD
     * @param ticket
=======
     * @param busPass
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
     * @return view for list
     * @author BJM
     * @since 2024-10-31
     */
    @RequestMapping("/search")
<<<<<<< HEAD
    public String search(Model model, @ModelAttribute("ticket") TrainTicket ticket) {
=======
    public String search(Model model, @ModelAttribute("busPass") BusPass busPass) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        //**********************************************************************
        //Use repository method created to find any entities which contain
        //the name entered on the list page.
        //**********************************************************************
<<<<<<< HEAD
        model.addAttribute("tickets", _bpr.findByNameContaining(ticket.getName()));
        logger.debug("searched for ticket name:" + ticket.getName());
        return "ticket/list";
=======
        model.addAttribute("busPasses", _bpr.findByNameContaining(busPass.getName()));
        logger.debug("searched for busPass name:" + busPass.getName());
        return "buspass/list";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }
}
