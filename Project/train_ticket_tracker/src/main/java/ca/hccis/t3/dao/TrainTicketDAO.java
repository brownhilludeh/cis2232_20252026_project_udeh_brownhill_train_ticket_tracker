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
     * Select all
     *
     * @since 20210924
     * @author BJM
     */
    public ArrayList<TrainTicket> selectAll() {
        ArrayList<TrainTicket> passes = null;
        Statement stmt = null;

        // ******************************************************************
        // Use the DriverManager to get a connection to our MySql database. Note
        // that in the dependencies, we added the Java connector to MySql which
        // will allow us to connect to a MySql database.
        // ******************************************************************
        // ******************************************************************
        // Create a statement object using our connection to the database. This
        // statement object will allow us to run sql commands against the database.
        // ******************************************************************
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from ticket;");

            // ******************************************************************
            // Loop through the result set using the next method.
            // ******************************************************************
            passes = new ArrayList();

            while (rs.next()) {

                TrainTicket ticket = new TrainTicket();
                ticket.setId(rs.getInt(1));
                ticket.setName(rs.getString("name"));
                ticket.setIssueDate(rs.getString("issueDate"));
                ticket.setStation(rs.getString("station"));
                ticket.setDepartureTime(rs.getString("departureTime"));
                ticket.setDestination(rs.getString("destination"));
                ticket.setTravelLength(rs.getInt("travelLength"));

                passes.add(ticket);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return passes;
    }

    /**
     * Select all by date range
     *
     * @since 20251017
     * @author Brownhill Udeh
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
                ticket.setIssueDate(rs.getString("issueDate"));
                ticket.setStation(rs.getString("station"));
                ticket.setDepartureTime(rs.getString("departureTime"));
                ticket.setDestination(rs.getString("destination"));
                ticket.setTravelLength(rs.getInt("travelLength"));
                passes.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }

        return passes;
    }


    /**
     * Select all for min length report
     *
     * @since 20241011
     * @author BJM
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
                    ticket.setIssueDate(rs.getString("issueDate"));
                    ticket.setStation(rs.getString("station"));
                    ticket.setDepartureTime(rs.getString("departureTime"));
                    ticket.setDestination(rs.getString("destination"));
                    ticket.setTravelLength(rs.getInt("travelLength"));
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
