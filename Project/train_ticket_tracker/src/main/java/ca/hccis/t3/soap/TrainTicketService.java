package ca.hccis.t3.soap;

import ca.hccis.t3.jpa.entity.TrainTicket;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface TrainTicketService {
    @WebMethod
    TrainTicket getTicketById(int id);
    @WebMethod
    List<TrainTicket> getTickets();
    @WebMethod
    int getCount();
    }