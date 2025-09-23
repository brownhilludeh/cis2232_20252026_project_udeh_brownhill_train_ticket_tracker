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

<<<<<<< HEAD
    public static ArrayList<TrainTicket> processDateRangeReport(String start, String end) {
=======
    public static ArrayList<BusPass> processDateRangeReport(String start, String end) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
<<<<<<< HEAD
        ArrayList<TrainTicket> tickets = trainTicketDAO.selectAllByDateRange(start, end);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", tickets);

        return tickets;
    }

    public static ArrayList<TrainTicket> processMinLengthReport(int minLength) throws SQLException {
=======
        ArrayList<BusPass> busPasses = trainTicketDAO.selectAllByDateRange(start, end);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", busPasses);

        return busPasses;
    }

    public static ArrayList<BusPass> processMinLengthReport(int minLength) throws SQLException {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
<<<<<<< HEAD
        ArrayList<TrainTicket> tickets = null;

        try {
            tickets = trainTicketDAO.selectAllWithMinLength(minLength);
=======
        ArrayList<BusPass> busPasses = null;

        try {
            busPasses = trainTicketDAO.selectAllWithMinLength(minLength);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        } catch (SQLException e) {
            throw e;
        }

        //Also write the report to a file
<<<<<<< HEAD
        CisUtilityFile.writeReportToFile("minLengthReport", tickets);

        return tickets;
    }

    /**
     * Calculate the ticketPrice of the bus pass and set it's value in the bus pass object.
=======
        CisUtilityFile.writeReportToFile("minLengthReport", busPasses);

        return busPasses;
    }

    /**
     * Calculate the cost of the bus pass and set it's value in the bus pass object.
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
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
<<<<<<< HEAD
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
=======
     * @param busPass
     * @return the cost
     * @author BJM
     * @since 20241025
     */
    public static double calculateBusPassCost(BusPass busPass) {

        double cost = 1 * busPass.getLengthOfPass();

        //adjust cost since days over 20 are 0.5$ less per day
        if (busPass.getLengthOfPass() > 20) {
            cost -= (busPass.getLengthOfPass() - 20) * 0.5;
        }

        if (busPass.getValidForRuralRoute()) {
            cost += 10;
        }

        switch (busPass.getPassType()) {
            case 3: //regular
                break;
            case 4: //k12
                cost = 0;
                break;
            case 5: //student 20% discount
                cost *= (1 - 0.2);
                break;
            case 6: //senior 25% discount
                cost *= (1 - 0.25);
                break;
        }

        busPass.setCost(new BigDecimal(cost));
        return cost;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

    }

    /**
     * Set default values
     *
<<<<<<< HEAD
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
=======
     * @param busPass
     * @author BJM
     * @since 20241025
     */
    public static void setBusPassDefaults(BusPass busPass) {
        busPass.setLengthOfPass(0);
        busPass.setCost(new BigDecimal(0));
        busPass.setPassType(3);
        busPass.setStartDate(CisUtility.getCurrentDate("yyyy-MM-dd"));

    }

    public static void setBusPassTypes(CodeValueRepository _cvr, HttpSession session) {
        List<CodeValue> busPassTypes = (List) session.getAttribute("busPassTypes");
        if (busPassTypes == null) {
            busPassTypes = _cvr.findByCodeTypeId(2);
            session.setAttribute("busPassTypes", busPassTypes);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        }


    }

}
