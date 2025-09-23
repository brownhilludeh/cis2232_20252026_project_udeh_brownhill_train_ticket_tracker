package ca.hccis.t3.controllers;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.entity.ReportTrainTicket;
<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
import ca.hccis.t3.util.CisUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller to administer reports of the project.
 *
 * @author BJM
 * @since 20220616
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @author BJM
     * @since 20220624
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * Method to send user to the date range report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2024-10-10
     */
<<<<<<< HEAD
    @RequestMapping("/ticket/daterange")
    public String reportticketDateRange(Model model, HttpSession session) {
=======
    @RequestMapping("/buspass/daterange")
    public String reportBusPassDateRange(Model model, HttpSession session) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        //Ensure that the current date is set in the session
        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        String start = CisUtility.getCurrentDate(-30, "yyyy-MM-dd");
        String end = CisUtility.getCurrentDate(+30, "yyyy-MM-dd");

        //**********************************************************************
        // Put the report object in the model and send the user
        // to the report input view.
        //**********************************************************************
        ReportTrainTicket reportTrainTicket = new ReportTrainTicket();
        //Set the default start/end dates for the report
<<<<<<< HEAD
        reportTrainTicket.setIssuedDate(start);
        reportTrainTicket.setDepartureTime(end);

        model.addAttribute("reportInput", reportTrainTicket);

        return "report/reportTrainTicketValidRange";
=======
        reportTrainTicket.setDateStart(start);
        reportTrainTicket.setDateEnd(end);

        model.addAttribute("reportInput", reportTrainTicket);

        return "report/reportBusPassDateRange";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Process the report
     *
     * @param model
     * @param reportTrainTicket Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2024-10-10
     */
<<<<<<< HEAD
    @RequestMapping("/ticket/validRange/submit")
    public String reportTicketDateRangeSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

        //Call BO method to process the report
        ArrayList<TrainTicket> tickets = TrainTicketBO.processDateRangeReport(reportTrainTicket.getIssuedDate(), reportTrainTicket.getDepartureTime());

        //Put the list in the Java object
        reportTrainTicket.setTicketPasses(tickets);

        //Add a message in case the report does not contain any data
        if (tickets != null && tickets.isEmpty()) {
=======
    @RequestMapping("/buspass/daterange/submit")
    public String reportBusPassDateRangeSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

        //Call BO method to process the report
        ArrayList<BusPass> busPasses = TrainTicketBO.processDateRangeReport(reportTrainTicket.getDateStart(), reportTrainTicket.getDateEnd());

        //Put the list in the Java object
        reportTrainTicket.setBusPasses(busPasses);

        //Add a message in case the report does not contain any data
        if (busPasses != null && busPasses.isEmpty()) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
            model.addAttribute("message", "No data found");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
       model.addAttribute("reportInput", reportTrainTicket);

<<<<<<< HEAD
        return "report/reportticketDateRange";
=======
        return "report/reportBusPassDateRange";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Method to send user to the min length report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2024-10-10
     */
<<<<<<< HEAD
    @RequestMapping("/ticket/minlength")
    public String reportticketMinLength(Model model) {
=======
    @RequestMapping("/buspass/minlength")
    public String reportBusPassMinLength(Model model) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        //**********************************************************************
        // Put the report object in the model and send the user
        // to the report input view.
        //**********************************************************************
        model.addAttribute("reportInput", new ReportTrainTicket());

<<<<<<< HEAD
        return "report/reportticketMinLength";
=======
        return "report/reportBusPassMinLength";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

    /**
     * Process the report
     *
     * @param model
     * @param reportTrainTicket Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2024-10-11
     */
<<<<<<< HEAD
    @RequestMapping("/ticket/minlength/submit")
    public String reportTicketMinLengthSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

        ArrayList<TrainTicket> tickets;
        try {
            tickets = TrainTicketBO.processMinLengthReport(reportTrainTicket.getMinLength());
        } catch (SQLException e) {
            model.addAttribute("message", "Exception accessing database");
            tickets = null;
        }

        reportTrainTicket.setTickets(tickets);

        if (tickets != null && tickets.isEmpty()) {
=======
    @RequestMapping("/buspass/minlength/submit")
    public String reportBusPassMinLengthSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

        ArrayList<BusPass> busPasses;
        try {
            busPasses = TrainTicketBO.processMinLengthReport(reportTrainTicket.getMinLength());
        } catch (SQLException e) {
            model.addAttribute("message", "Exception accessing database");
            busPasses = null;
        }

        reportTrainTicket.setBusPasses(busPasses);

        if (busPasses != null && busPasses.isEmpty()) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
            model.addAttribute("message", "No data found");
            System.out.println("BJM - no data found");
        }

        model.addAttribute("reportInput", reportTrainTicket);

<<<<<<< HEAD
        return "report/reportTicketMinLength";
=======
        return "report/reportBusPassMinLength";
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }

}
