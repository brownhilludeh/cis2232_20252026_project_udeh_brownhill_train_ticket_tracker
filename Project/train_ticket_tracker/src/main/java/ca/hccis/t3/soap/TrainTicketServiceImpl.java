package ca.hccis.t3.soap;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.TrainTicket;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ca.hccis.t3.soap.TrainTicketService")
public class TrainTicketServiceImpl implements TrainTicketService {

    /**
     * Gets a ticket by ID.
     *
     * @param id The ID of the ticket to get.
     * @return The ticket with the given ID, or null if not found.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public TrainTicket getTicketById(int id) {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        TrainTicket ticket = null;
        for (TrainTicket current : getTickets()) {
            if (current.getId().equals(id)) {
                ticket = current;
            }
        }
        return ticket;
    }

    /**
     * Returns the number of tickets.
     *
     * @return The number of tickets.
     * @author Brown_hill Udeh
     * @since 2025-12-04
     */
    @Override
    public int getCount() {
        return getTickets().size();
    }

    /**
     * Returns a list of all tickets.
     *
     * @return A list of all tickets.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    @Override
    public List<TrainTicket> getTickets() {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        return trainTicketDAO.selectAll();
    }
}
