package ca.hccis.t3.bo;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.util.CisUtilityFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainTicketBO {

    /**
     * Process the report for the given date range.
     *
     * @param start The start of the date range in YYYY-MM-DD format.
     * @param end   The end of the date range in YYYY-MM-DD format.
     * @return A list of tickets that fall within the given date range.
     * @author Brownhill Udeh
     * @since 2025-10-10
     */
    public static ArrayList<TrainTicket> processDateRangeReport(String start, String end) {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets = trainTicketDAO.selectAllByDateRange(start, end);

        // Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", tickets);

        return tickets;
    }

    /**
     * Process the report for the given travel length range.
     *
     * @param minLength The minimum travel length.
     * @param maxLength The maximum travel length.
     * @return A list of tickets that fall within the given travel length range.
     * @author Brownhill Udeh
     * @since 2025-10-10
     * @throws SQLException If there is an error accessing the database
     */
    public static ArrayList<TrainTicket> processTravelLengthReport(int minLength, int maxLength) throws SQLException {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets;

        try {
            tickets = trainTicketDAO.selectAllWithTravelLength(minLength, maxLength);
        } catch (SQLException e) {
            throw e;
        }

        // Also write the report to a file
        CisUtilityFile.writeReportToFile("travelRangeReport", tickets);

        return tickets;
    }

    /**
     * Calculates the ticket price for a given train ticket.
     *
     * The ticket price is calculated as follows:
     * - The base price is 5 dollars per travel length.
     * - If the ticket is for a student, then the price is discounted by 20%.
     * - If the ticket is for a frequent traveler, then the price is discounted by
     * 15%.
     *
     * @param ticket The train ticket to calculate the price for.
     * @return The calculated ticket price.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public static double calculateTicketPrice(TrainTicket ticket) {
        double ticketPrice = 5 * ticket.getTravelLength();

        if (ticket.getIsStudent()) {
            ticketPrice *= 0.8;
        }

        if (ticket.getIsFrequent()) {
            ticketPrice *= 0.85;
        }

        ticket.setTicketPrice(new BigDecimal(Math.round(ticketPrice)));
        return ticketPrice;
    }

    /**
     * Sets the default values for a new train ticket.
     *
     * @param ticket The train ticket to set the default values for.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public static void setTicketDefaults(TrainTicket ticket) {
        ticket.setTicketPrice(new BigDecimal(0));
        ticket.setIsStudent(false);
        ticket.setIsFrequent(false);
        ticket.setTravelLength(0);
        ticket.setIssueDate(java.time.LocalDate.now());
    }
}
