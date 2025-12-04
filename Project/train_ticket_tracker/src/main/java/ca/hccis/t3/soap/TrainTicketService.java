package ca.hccis.t3.soap;

import ca.hccis.t3.jpa.entity.TrainTicket;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface TrainTicketService {
    /**
     * Get a ticket by ID.
     *
     * @param id The ID of the ticket to get.
     * @return The ticket with the given ID, or null if not found.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    @WebMethod
    TrainTicket getTicketById(int id);

    /**
     * Returns a list of all tickets.
     *
     * @return A list of all tickets.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    @WebMethod
    List<TrainTicket> getTickets();

    /**
     * Returns the number of tickets.
     *
     * @return The number of tickets.
     * @author Brown_hill Udeh
     * @since 2025-12-04
     */
    @WebMethod
    int getCount();
}