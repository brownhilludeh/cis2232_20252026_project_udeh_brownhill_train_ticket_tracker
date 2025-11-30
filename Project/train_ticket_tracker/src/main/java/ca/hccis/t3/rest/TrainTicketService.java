package ca.hccis.t3.rest;

import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.repositories.TrainTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Optional;

/**
 * REST Service class for Train Ticket operations
 * Provides JSON API for CRUD operations
 *
 * @author Logan
 * @since 2025-11-30
 */
@Path("/tickets")
public class TrainTicketService {

    private static final Logger logger = LoggerFactory.getLogger(TrainTicketService.class);

    @Autowired
    private TrainTicketRepository trainTicketRepository;

    /**
     * GET all train tickets
     * GET http://localhost:8080/api/tickets
     *
     * @return Response with list of all tickets (200 OK or 204 NO CONTENT)
     * @author Brownhill Udeh
     * @since 2025-11-20
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTickets() {
        try {
            logger.info("REST API: Getting all train tickets");

            ArrayList<TrainTicket> tickets = (ArrayList<TrainTicket>) trainTicketRepository.findAll();

            // Console output
            System.out.println("\n========================================");
            System.out.println("REST API - GET ALL TICKETS");
            System.out.println("========================================");
            System.out.println("Total tickets found: " + tickets.size());

            if (!tickets.isEmpty()) {
                System.out.println("\nTicket Details:");
                for (TrainTicket ticket : tickets) {
                    System.out.println("  - ID: " + ticket.getId() +
                            ", Name: " + ticket.getName() +
                            ", Station: " + ticket.getStation() +
                            ", Destination: " + ticket.getDestination());
                }
            }
            System.out.println("========================================\n");

            if (tickets == null || tickets.isEmpty()) {
                logger.info("REST API: No tickets found");
                return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
            } else {
                logger.info("REST API: Found {} tickets", tickets.size());
                return Response
                        .status(HttpURLConnection.HTTP_OK)
                        .entity(tickets)
                        .build();
            }

        } catch (Exception e) {
            logger.error("REST API: Error getting all tickets", e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity(new ErrorResponse("Error retrieving tickets: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * GET one train ticket by ID
     * GET http://localhost:8080/api/tickets/{id}
     *
     * @param id The ticket ID
     * @return Response with ticket (200 OK) or 204 if not found
     * 
     * @author Brownhill Udeh
     * @since 2025-11-20
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketById(@PathParam("id") Integer id) {
        try {
            logger.info("REST API: Getting ticket with ID: {}", id);

            Optional<TrainTicket> ticketOptional = trainTicketRepository.findById(id);

            // Console output
            System.out.println("\n========================================");
            System.out.println("REST API - GET TICKET BY ID");
            System.out.println("========================================");
            System.out.println("Requested ID: " + id);

            if (!ticketOptional.isPresent()) {
                System.out.println("Result: NOT FOUND");
                System.out.println("========================================\n");

                logger.warn("REST API: Ticket not found with ID: {}", id);
                return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
            } else {
                TrainTicket ticket = ticketOptional.get();
                System.out.println("Result: FOUND");
                System.out.println("Name: " + ticket.getName());
                System.out.println("Issue Date: " + ticket.getIssueDate());
                System.out.println("Station: " + ticket.getStation());
                System.out.println("Departure Time: " + ticket.getDepartureTime());
                System.out.println("Destination: " + ticket.getDestination());
                System.out.println("Travel Length: " + ticket.getTravelLength() + " minutes");
                System.out.println("Ticket Price: $" + ticket.getTicketPrice());
                System.out.println("Student: " + ticket.getIsStudent());
                System.out.println("Frequent Traveler: " + ticket.getIsFrequent());
                System.out.println("========================================\n");

                logger.info("REST API: Found ticket: {}", ticket.getName());
                return Response
                        .status(HttpURLConnection.HTTP_OK)
                        .entity(ticket)
                        .build();
            }

        } catch (Exception e) {
            logger.error("REST API: Error getting ticket by ID: {}", id, e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity(new ErrorResponse("Error retrieving ticket: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * POST - Create a new train ticket
     * POST http://localhost:8080/api/tickets
     *
     * @param ticket The ticket to create
     * @return Response with created ticket (200 OK) or 406 if invalid
     * 
     * @author Brownhill Udeh
     * @since 2025-11-20
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTicket(TrainTicket ticket) {
        try {
            logger.info("REST API: Creating new ticket");
            System.out.println("\n========================================");
            System.out.println("REST API - CREATE TICKET");
            System.out.println("========================================");

            // Validation - check for required fields
            if (ticket == null) {
                String error = "Ticket data is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            if (ticket.getName() == null || ticket.getName().trim().isEmpty()) {
                String error = "Name is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            if (ticket.getStation() == null || ticket.getStation().trim().isEmpty()) {
                String error = "Station is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            if (ticket.getDestination() == null || ticket.getDestination().trim().isEmpty()) {
                String error = "Destination is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            if (ticket.getDepartureTime() == null || ticket.getDepartureTime().trim().isEmpty()) {
                String error = "Departure time is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            if (ticket.getTicketPrice() == null) {
                String error = "Ticket price is required";
                logger.warn("REST API: {}", error);
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            // Set ID to null to ensure new record
            if (ticket.getId() == null) {
                ticket.setId(0);
            }

            // Save the ticket
            TrainTicket savedTicket = trainTicketRepository.save(ticket);

            System.out.println("Ticket Created Successfully!");
            System.out.println("Generated ID: " + savedTicket.getId());
            System.out.println("Name: " + savedTicket.getName());
            System.out.println("Station: " + savedTicket.getStation());
            System.out.println("Destination: " + savedTicket.getDestination());
            System.out.println("Price: $" + savedTicket.getTicketPrice());
            System.out.println("========================================\n");

            logger.info("REST API: Ticket created with ID: {}", savedTicket.getId());

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(savedTicket)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();

        } catch (Exception e) {
            logger.error("REST API: Error creating ticket", e);
            System.out.println("Exception: " + e.getMessage());
            System.out.println("========================================\n");
            return Response
                    .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(new ErrorResponse("Error creating ticket: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * DELETE a train ticket by ID
     * DELETE http://localhost:8080/api/tickets/{id}
     *
     * @param id The ticket ID to delete
     * @return Response (200 OK) or 204 if not found
     * 
     * @author Brownhill Udeh
     * @since 2025-11-20
     */
    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") Integer id) {
        try {
            logger.info("REST API: Deleting ticket with ID: {}", id);
            System.out.println("\n========================================");
            System.out.println("REST API - DELETE TICKET");
            System.out.println("========================================");
            System.out.println("Deleting ticket ID: " + id);

            Optional<TrainTicket> ticketOptional = trainTicketRepository.findById(id);

            if (!ticketOptional.isPresent()) {
                System.out.println("Result: Ticket not found");
                System.out.println("========================================\n");

                logger.warn("REST API: Cannot delete - ticket not found with ID: {}", id);
                return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
            } else {
                trainTicketRepository.delete(ticketOptional.get());

                System.out.println("Result: Ticket deleted successfully");
                System.out.println("========================================\n");

                logger.info("REST API: Ticket deleted successfully: {}", id);
                return Response
                        .status(HttpURLConnection.HTTP_OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
            }

        } catch (Exception e) {
            logger.error("REST API: Error deleting ticket: {}", id, e);
            System.out.println("Exception: " + e.getMessage());
            System.out.println("========================================\n");
            return Response
                    .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(new ErrorResponse("Error deleting ticket: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * PUT - Update an existing train ticket
     * PUT http://localhost:8080/api/tickets/{id}
     *
     * @param id     The ticket ID to update
     * @param ticket The updated ticket data
     * @return Response with updated ticket
     * 
     * @author Brownhill Udeh
     * @since 2025-11-20
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTicket(@PathParam("id") Integer id, TrainTicket ticket) {
        try {
            logger.info("REST API: Updating ticket with ID: {}", id);
            System.out.println("\n========================================");
            System.out.println("REST API - UPDATE TICKET");
            System.out.println("========================================");
            System.out.println("Updating ticket ID: " + id);

            // Ensure ID is set
            ticket.setId(id);

            // Validation (same as create)
            if (ticket.getName() == null || ticket.getName().trim().isEmpty()) {
                String error = "Name is required";
                System.out.println("Validation Error: " + error);
                System.out.println("========================================\n");
                return Response
                        .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                        .entity(new ErrorResponse(error))
                        .build();
            }

            TrainTicket updatedTicket = trainTicketRepository.save(ticket);

            System.out.println("Ticket Updated Successfully!");
            System.out.println("ID: " + updatedTicket.getId());
            System.out.println("Name: " + updatedTicket.getName());
            System.out.println("========================================\n");

            logger.info("REST API: Ticket updated: {}", id);

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(updatedTicket)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();

        } catch (Exception e) {
            logger.error("REST API: Error updating ticket: {}", id, e);
            System.out.println("Exception: " + e.getMessage());
            System.out.println("========================================\n");
            return Response
                    .status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(new ErrorResponse("Error updating ticket: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * Error response wrapper class
     */
    public static class ErrorResponse {
        private String error;
        private long timestamp;

        public ErrorResponse(String error) {
            this.error = error;
            this.timestamp = System.currentTimeMillis();
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}