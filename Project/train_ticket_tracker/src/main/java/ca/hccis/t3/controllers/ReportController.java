package ca.hccis.t3.controllers;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.entity.ReportTrainTicket;
import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.service.TrainTicketService;
import ca.hccis.t3.util.CisUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final TrainTicketService service;

    public ReportController(TrainTicketService service) {
        this.service = service;
    }

    /**
     * This method is the entry point for the reports controller. It will
     * display all available reports.
     *
     * @param model
     *                the model object which will be used to pass data to the view
     * @param session
     *                the session object which will be used to store data
     * @return the view to show all available reports
     * @author Brownhill Udeh
     * @since 2025-10-10
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * This method is the entry point for the travel length range report. It will
     * display the form for the travel length range report.
     *
     * @param model
     *                the model object which will be used to pass data to the view
     * @param session
     *                the session object which will be used to store data
     * @return the view to show the form for the travel length range report
     * @author Brownhill Udeh
     * @since 2025-10-10
     */
    @RequestMapping("/ticket/travel-range")
    public String reportTicketTravelLength(Model model, HttpSession session) {
        model.addAttribute("reportInput", new ReportTrainTicket());
        model.addAttribute("tickets", null);
        model.addAttribute("message", "");
        return "report/reportTravelRangeIndex";
    }

    /**
     * Process the travel length range report form submission
     *
     * @param reportInput Object containing inputs for the report
     * @param model
     * @return view to show report of travel length range
     * @author Brownhill U
     * @since 2025-10-10
     */
    @PostMapping("/ticket/travel-range")
    public String reportTravelRangeSubmit(@ModelAttribute("reportInput") ReportTrainTicket reportInput, Model model) {
        int minLength = reportInput.getMinLength();
        int maxLength = reportInput.getMaxLength();

        ArrayList<TrainTicket> tickets;
        try {
            tickets = TrainTicketBO.processTravelLengthReport(minLength, maxLength);
        } catch (SQLException e) {
            model.addAttribute("message", "Error generating report: " + e.getMessage());
            reportInput.setTickets(new ArrayList<>());
            model.addAttribute("reportInput", reportInput);
            return "report/reportTravelRangeView";
        }

        reportInput.setTickets(tickets);
        model.addAttribute("reportInput", reportInput);
        model.addAttribute("message", tickets.isEmpty() ? "No tickets found in this range"
                : "Report writing to file was successful with " + tickets.size() + " tickets.");

        return "report/reportTravelRangeView";
    }

    /**
     * This method is the entry point for the date range report. It will
     * display the form for the date range report.
     *
     * @param model
     *                the model object which will be used to pass data to the view
     * @param session
     *                the session object which will be used to store data
     * @return the view to show the form for the date range report
     * @author Brownhill Udeh
     * @since 2025-10-10
     */
    @GetMapping("/ticket/date-range")
    public String showDateRangeForm(Model model) {
        model.addAttribute("reportInput", new ReportTrainTicket());
        model.addAttribute("tickets", null);
        model.addAttribute("message", "");
        return "report/reportDateRangeIndex";
    }

    /**
     * Process the date range report form submission
     *
     * @param reportInput Object containing inputs for the report
     * @param model
     * @return view to show report of date range
     * @author Brownhill Udeh
     * @since 2025-10-10
     */
    @PostMapping("/ticket/date-range")
    public String processDateRangeReport(@ModelAttribute("reportInput") ReportTrainTicket reportInput, Model model) {
        ArrayList<TrainTicket> tickets = TrainTicketBO.processDateRangeReport(
                reportInput.getStartDate(), reportInput.getEndDate());

        reportInput.setTickets(tickets);

        model.addAttribute("reportInput", reportInput);
        model.addAttribute("message", "Report writing to file was successful with " + tickets.size() + " tickets.");

        return "report/reportDateRangeView";
    }

}
