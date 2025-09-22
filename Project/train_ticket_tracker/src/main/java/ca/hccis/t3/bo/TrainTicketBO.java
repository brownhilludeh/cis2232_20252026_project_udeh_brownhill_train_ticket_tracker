package ca.hccis.t3.bo;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.jpa.entity.CodeValue;
import ca.hccis.t3.repositories.CodeValueRepository;
import ca.hccis.t3.util.CisUtility;
import ca.hccis.t3.util.CisUtilityFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class TrainTicketBO {

    public static ArrayList<TrainTicket> processDateRangeReport(String start, String end) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets = trainTicketDAO.selectAllByDateRange(start, end);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", tickets);

        return tickets;
    }

    public static ArrayList<TrainTicket> processMinLengthReport(int minLength) throws SQLException {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets = null;

        try {
            tickets = trainTicketDAO.selectAllWithMinLength(minLength);
        } catch (SQLException e) {
            throw e;
        }

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("minLengthReport", tickets);

        return tickets;
    }

    /**
     * Calculate the ticketPrice of the bus pass and set it's value in the bus pass object.
     *
     *         $1 per day for the first 20 days
     *         $0.50 for each day over 20 days
     *
     *         $10 premium if to include rural routes
     *
     *         Adjustments based on type
     *         3-K12 are free
     *         4-Seniors get a 25% discount on their subtotal
     *         5-Students get a 20% discount on their subtotal
     *
     * @param ticket
     * @return the ticketPrice
     * @author BJM
     * @since 20241025
     */
    public static double calculateTicketPrice(TrainTicket ticket) {

        double ticketPrice = 5 * ticket.getTravelLength();

        // Apply student discount: 20%
        if (ticket.isStudentInd()) {
            ticketPrice *= 0.8;
        }

        // Apply frequent rider discount: 15%
        if (ticket.isFrequent()) {
            ticketPrice *= 0.85;
        }

        // Set ticketPrice in the TrainTicket object
        ticket.setTicketPrice(new BigDecimal(Math.round(ticketPrice)));

        return ticketPrice;

    }

    /**
     * Set default values
     *
     * @param ticket
     * @author BJM
     * @since 20241025
     */
    public static void setTicketDefaults(TrainTicket ticket) {
        ticket.setTicketPrice(new BigDecimal(0));
        ticket.setIsStudent(false);
        ticket.setIsFrequent(false);
        ticket.setTravelLength(0);
        ticket.setIssueDate(java.time.LocalDate.now().toString());
    }

    public static void setTicketTypes(CodeValueRepository _cvr, HttpSession session) {
        List<CodeValue> ticketTypes = (List) session.getAttribute("ticketTypes");
        if (ticketTypes == null) {
            ticketTypes = _cvr.findByCodeTypeId(2);
            session.setAttribute("ticketTypes", ticketTypes);
        }


    }

}
