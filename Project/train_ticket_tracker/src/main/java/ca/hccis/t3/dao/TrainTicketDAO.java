package ca.hccis.t3.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ca.hccis.t3.jpa.entity.TrainTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO class to access ticket orders.
 *
 * @author bjmaclean
 * @since 20220621
 */
public class TrainTicketDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(TrainTicketDAO.class);

    public TrainTicketDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }

    }

    /**
     * Select all tickets from the database.
     * 
     * @return A list of all tickets in the database.
     * @throws SQLException If there is an error accessing the database
     * 
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public ArrayList<TrainTicket> selectAll() {
        ArrayList<TrainTicket> passes = null;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from ticket;");

            passes = new ArrayList<>();

            while (rs.next()) {
                TrainTicket ticket = new TrainTicket();
                ticket.setId(rs.getInt("id"));
                ticket.setName(rs.getString("name"));
                ticket.setIssueDate(rs.getObject("issueDate", java.time.LocalDate.class));
                ticket.setStation(rs.getString("station"));
                ticket.setDepartureTime(rs.getString("departureTime"));
                ticket.setDestination(rs.getString("destination"));
                ticket.setTravelLength(rs.getInt("travelLength"));

                ticket.setIsStudent(rs.getBoolean("isStudent"));
                ticket.setIsFrequent(rs.getBoolean("isFrequent"));

                passes.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return passes;
    }

    /**
     * Select all tickets from the database where the issue date is between the
     * given start and end dates.
     * 
     * @param start The start of the date range in YYYY-MM-DD format.
     * @param end   The end of the date range in YYYY-MM-DD format.
     * @return A list of tickets that fall within the given date range.
     * @author Brownhill Udeh
     * @since 2025-10-10
     * @throws SQLException If there is an error accessing the database
     */
    public ArrayList<TrainTicket> selectAllByDateRange(String start, String end) {
        ArrayList<TrainTicket> passes = new ArrayList<>();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM ticket " +
                    "WHERE issueDate >= '" + start +
                    "' AND issueDate <= '" + end + "';";

            rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                TrainTicket ticket = new TrainTicket();
                ticket.setId(rs.getInt("id"));
                ticket.setName(rs.getString("name"));
                ticket.setIssueDate(rs.getObject("issueDate", java.time.LocalDate.class));
                ticket.setStation(rs.getString("station"));
                ticket.setDepartureTime(rs.getString("departureTime"));
                ticket.setDestination(rs.getString("destination"));
                ticket.setTravelLength(rs.getInt("travelLength"));

                ticket.setIsStudent(rs.getBoolean("isStudent"));
                ticket.setIsFrequent(rs.getBoolean("isFrequent"));

                passes.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }

        return passes;
    }

    /**
     * Select all tickets with a travel length between the given minimum and maximum
     * travel lengths.
     * 
     * @param minLength The minimum travel length.
     * @param maxLength The maximum travel length.
     * @return A list of tickets with travel lengths between the given minimum and
     *         maximum travel lengths.
     * @throws SQLException If there is an error accessing the database
     * @since 20251017
     * @author Brownhill Udeh
     */
    public ArrayList<TrainTicket> selectAllWithTravelLength(int minLength, int maxLength) throws SQLException {
        ArrayList<TrainTicket> passes = new ArrayList<>();
        String sql = "SELECT * FROM ticket WHERE travelLength BETWEEN ? AND ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, minLength);
            stmt.setInt(2, maxLength);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TrainTicket ticket = new TrainTicket();
                    ticket.setId(rs.getInt("id"));
                    ticket.setName(rs.getString("name"));
                    ticket.setIssueDate(rs.getObject("issueDate", java.time.LocalDate.class));
                    ticket.setStation(rs.getString("station"));
                    ticket.setDepartureTime(rs.getString("departureTime"));
                    ticket.setDestination(rs.getString("destination"));
                    ticket.setTravelLength(rs.getInt("travelLength"));

                    ticket.setIsStudent(rs.getBoolean("isStudent"));
                    ticket.setIsFrequent(rs.getBoolean("isFrequent"));

                    passes.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return passes;
    }

}
