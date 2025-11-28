package ca.hccis.t3.rest;

import ca.hccis.t3.bo.TrainTicketBO;
import ca.hccis.t3.exception.AllAttributesNeededException;
import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.repositories.TrainTicketRepository;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Train Ticket Service class for accessing using REST.
 *
 * @author 2232
 * @since 20241114
 */
@Path("/ticketService/tickets")
public class TrainTicketService {

    private final TrainTicketRepository _ttr;

    @Autowired
    public TrainTicketService(TrainTicketRepository t3r) {
        this._ttr = t3r;
    }

    /**
     * Method to get all tickets.
     *
     * @author 2250
     * @since 20201116
     * @return tickets
     */
    @GET
    @Produces("application/json")
    public Response getAll() {

        ArrayList<TrainTicket> tickets =
                (ArrayList<TrainTicket>) _ttr.findAll();

        if (tickets == null || tickets.isEmpty()) {
            int responseCode = HttpURLConnection.HTTP_NO_CONTENT;
            return Response.status(responseCode).build();
        } else {
            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(tickets).build();
        }
    }

    /**
     * Method to delete a ticket by id.
     *
     * @author 2250
     * @since 20201116
     * @param id ticket id
     * @return response
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {

        try {
            Optional<TrainTicket> ticketOptional =
                    _ttr.findById(Integer.valueOf(id));

            if (!ticketOptional.isPresent()) {
                return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
            } else {
                _ttr.delete(ticketOptional.get());
            }
        } catch (Exception e) {
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(e.getMessage())
                    .build();
        }

        return Response.status(HttpURLConnection.HTTP_OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }

    /**
     * Method to get ticket by id using REST.
     *
     * @author 2250
     * @since 20220201
     * @param id ticket id
     * @return response
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTicketsById(@PathParam("id") Integer id)
            throws URISyntaxException {

        Optional<TrainTicket> ticket = _ttr.findById(id);

        if (!ticket.isPresent()) {
            return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
        } else {
            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(ticket).build();
        }
    }

    /**
     * Method to create using REST.
     *
     * @author 2250
     * @since 20201116
     * @param inJson json representing a TrainTicket
     * @return response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String inJson) {
        try {
            System.out.println(inJson);
            String temp = save(inJson);
            return Response.status(HttpURLConnection.HTTP_OK)
                    .entity(temp)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        } catch (AllAttributesNeededException aane) {
            System.out.println("AANE Exception happened adding ticket order.");
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(aane.getMessage())
                    .build();
        } catch (Exception e) {
            System.out.println("Exception happened adding ticket order.");
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // If you want a PUT like your instructor's commented example, you can
    // uncomment and adapt this pattern:
    //
    // @PUT
    // @Path("/{id}")
    // @Consumes("application/json")
    // @Produces("application/json")
    // public Response updateTicketOrder(@PathParam("id") int id, String ticketOrderJson)
    //         throws URISyntaxException {
    //
    //     try {
    //         String temp = save(ticketOrderJson);
    //         return Response.status(201)
    //                 .entity(temp)
    //                 .header("Access-Control-Allow-Origin", "*")
    //                 .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    //                 .build();
    //     } catch (AllAttributesNeededException aane) {
    //         return Response.status(400).entity(aane.getMessage()).build();
    //     }
    // }

    /**
     * Method to make sure all required inputs are present and save.
     *
     * @author 2250
     * @since 20220201
     * @param json json representing a TrainTicket
     * @return string json of saved TrainTicket
     */
    public String save(String json) throws AllAttributesNeededException {

        Gson gson = new Gson();
        TrainTicket ticket = gson.fromJson(json, TrainTicket.class);

        if (ticket.getId() == null) {
            ticket.setId(0);
        }

        TrainTicketBO.calculateTicketPrice(ticket);
        ticket = _ttr.save(ticket);

        String temp = gson.toJson(ticket);
        return temp;
    }

    public List<TrainTicket> getTicketsByLengthRange(Integer minLength, Integer maxLength) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTicketsByLengthRange'");
    }
}
