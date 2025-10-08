package ca.hccis.t3.controllers;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.entity.ReportTrainTicket;
import ca.hccis.t3.jpa.entity.TrainTicket;
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
       logger.info("Running the reports controller base method");
       return "report/list";
   }

   /*
    * Process the report
    *
    * @param model
    * @param reportTrainTicket Object containing inputs for the report
    * @return view to show form for travel length report
    * @author Brownhill U
    * @since 2025-10-10
    */
    @RequestMapping("/ticket/travel-length")
    public String reportTicketTravelLength(Model model, HttpSession session) {
    //    ReportTrainTicket reportTrainTicket = new ReportTrainTicket();
        // reportTrainTicket.setTravelLength(0);
        return "report/reportTravelRangeIndex";
    }

   /**
    * Method to send user to the date range report.
    *
    * @param model
    * @return view for list
    * @author BJM
    * @since 2024-10-10
    */
   @RequestMapping("/ticket/date-range")
   public String reportTicketDateRange(Model model, HttpSession session) {

       String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
       session.setAttribute("currentDate", currentDate);

       String start = CisUtility.getCurrentDate(-30, "yyyy-MM-dd");
       String end = CisUtility.getCurrentDate(+30, "yyyy-MM-dd");

       ReportTrainTicket reportTrainTicket = new ReportTrainTicket();
       //Set the default start/end dates for the report
       reportTrainTicket.setIssuedDate(start);
       reportTrainTicket.setIssuedDate(end);

       model.addAttribute("reportInput", reportTrainTicket);

    //    return "report/reportTrainTicketValidRange";
       return "report/reportTicketDateRange";
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
   @RequestMapping("/ticket/validRange/submit")
   public String reportTicketDateRangeSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

       //Call BO method to process the report
       ArrayList<TrainTicket> tickets = TrainTicketBO.processDateRangeReport(reportTrainTicket.getIssuedDate(), reportTrainTicket.getDepartureTime());

       //Put the list in the Java object
       reportTrainTicket.setTickets(tickets);

       //Add a message in case the report does not contain any data
       if (tickets != null && tickets.isEmpty()) {
           model.addAttribute("message", "No data found");
           System.out.println("Brownhill - no data found");
       }

       //Put object in model so it can be used on the view (html)
      model.addAttribute("reportInput", reportTrainTicket);

       return "report/reportTrainTicketDateRange";
   }

//    /**
//     * Method to send user to the min length report.
//     *
//     * @param model
//     * @return view for list
//     * @author BJM
//     * @since 2024-10-10
//     */
//    @RequestMapping("/ticket/minlength")
//    public String reportTicketMinLength(Model model) {

//        //**********************************************************************
//        // Put the report object in the model and send the user
//        // to the report input view.
//        //**********************************************************************
//        model.addAttribute("reportInput", new ReportTrainTicket());

//        return "report/reportticketMinLength";
//    }

//    /**
//     * Process the report
//     *
//     * @param model
//     * @param reportTrainTicket Object containing inputs for the report
//     * @return view to show report
//     * @author BJM
//     * @since 2024-10-11
//     */
//    @RequestMapping("/ticket/minlength/submit")
//    public String reportTicketMinLengthSubmit(Model model, @ModelAttribute("reportInput") ReportTrainTicket reportTrainTicket) {

//        ArrayList<TrainTicket> tickets;
//        try {
//            tickets = TrainTicketBO.processMinLengthReport(reportTrainTicket.getTravelLength());
//        } catch (SQLException e) {
//            model.addAttribute("message", "Exception accessing database");
//            tickets = null;
//        }

//        reportTrainTicket.setTickets(tickets);

//        if (tickets != null && tickets.isEmpty()) {
//            model.addAttribute("message", "No data found");
//            System.out.println("BJM - no data found");
//        }

//        model.addAttribute("reportInput", reportTrainTicket);

//        return "report/reportTicketMinLength";
//    }

}
