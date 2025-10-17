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
import java.util.List;

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

    private final TrainTicketService service;

    public ReportController(TrainTicketService service) {
        this.service = service;
    }

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
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /*
     * Process the report
     *
     * @param model
     * 
     * @param reportTrainTicket Object containing inputs for the report
     * 
     * @return view to show form for travel length report
     * 
     * @author Brownhill U
     * 
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

    // @RequestMapping("/ticket/date-range")
    // public String reportTicketDateRange(Model model) {
    // model.addAttribute("reportInput", new ReportTrainTicket());
    // return "report/reportDateRangeIndex";
    // }

    // @RequestMapping("/ticket/date-range")
    // public String reportTicketDateRange(Model model) {
    // ArrayList<TrainTicket> tickets =
    // TrainTicketBO.processDateRangeReport("2025-10-01", "2025-10-17");
    // model.addAttribute("tickets", tickets); // Display
    // model.addAttribute("reportInput", new ReportTrainTicket());
    // model.addAttribute("message", "Report generated successfully with " +
    // tickets.size() + " tickets.");
    // return "report/reportDateRangeIndex";
    // }
    //
    //
    // // Handle form submission
    // @PostMapping("/ticket/date-range")
    // public String reportTicketDateRangeSubmit(@ModelAttribute("reportInput")
    // ReportTrainTicket reportInput, Model model) {
    //
    // String startDate = reportInput.getStartDate();
    // String endDate = reportInput.getEndDate();
    //
    // List<TrainTicket> tickets = service.getTicketsByDateRange(startDate,
    // endDate);
    //
    // if (tickets.isEmpty()) {
    // model.addAttribute("message", "No tickets found in this range");
    // } else {
    // reportInput.setTickets(tickets);
    // model.addAttribute("reportInput", reportInput);
    // }
    //
    // return "report/reportDateRangeView";
    // }

    // GET: show form
    @GetMapping("/ticket/date-range")
    public String showDateRangeForm(Model model) {
        model.addAttribute("reportInput", new ReportTrainTicket());
        model.addAttribute("tickets", null);
        model.addAttribute("message", "");
        return "report/reportDateRangeIndex";
    }

    // POST: process submitted dates
    @PostMapping("/ticket/date-range")
    public String processDateRangeReport(@ModelAttribute("reportInput") ReportTrainTicket reportInput, Model model) {
        ArrayList<TrainTicket> tickets = TrainTicketBO.processDateRangeReport(
                reportInput.getStartDate(), reportInput.getEndDate());

        // Set tickets inside reportInput for Thymeleaf
        reportInput.setTickets(tickets);

        model.addAttribute("reportInput", reportInput);
        model.addAttribute("message", "Report writing to file was successful with " + tickets.size() + " tickets.");

        return "report/reportDateRangeView";
    }

}
