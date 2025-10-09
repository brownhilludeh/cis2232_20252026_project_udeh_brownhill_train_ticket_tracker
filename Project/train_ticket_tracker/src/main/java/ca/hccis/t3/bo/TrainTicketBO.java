package ca.hccis.t3.bo;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.TrainTicket;
//import ca.hccis.t3.jpa.entity.CodeValue;
import ca.hccis.t3.repositories.CodeValueRepository;
import ca.hccis.t3.util.CisUtilityFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class TrainTicketBO {

    public static ArrayList<TrainTicket> processDateRangeReport(String start, String end) {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets = trainTicketDAO.selectAllByDateRange(start, end);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", tickets);

        return tickets;
    }

    public static ArrayList<TrainTicket> processTravelLengthReport(int minLength, Integer integer) throws SQLException {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> tickets = null;

        try {
            tickets = trainTicketDAO.selectAllWithTravelLength(minLength);
        } catch (SQLException e) {
            throw e;
        }

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("minLengthReport", tickets);

        return tickets;
    }

    /**
     * Calculate the ticket price of the train ticket.
     *
     * $5 per unit of travel length
     * - 20% discount if student
     * - 15% discount if frequent rider
     *
     * @param ticket
     * @return ticket price
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
     * Set default values for a new train ticket
     */
    public static void setTicketDefaults(TrainTicket ticket) {
        ticket.setTicketPrice(new BigDecimal(0));
        ticket.setIsStudent(false);
        ticket.setIsFrequent(false);
        ticket.setTravelLength(0);
        ticket.setIssueDate(java.time.LocalDate.now().toString());
    }

//    public static void setTicketTypes(CodeValueRepository _cvr, HttpSession session) {
//        List<CodeValue> ticketTypes = (List) session.getAttribute("ticketTypes");
//        if (ticketTypes == null) {
//            ticketTypes = _cvr.findByCodeTypeId(2);
//            session.setAttribute("ticketTypes", ticketTypes);
//        }
//    }

}
