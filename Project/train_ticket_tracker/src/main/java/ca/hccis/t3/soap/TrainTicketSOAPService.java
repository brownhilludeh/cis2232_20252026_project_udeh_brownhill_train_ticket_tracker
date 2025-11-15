package ca.hccis.t3.soap;

import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.repositories.TrainTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Optional;

@WebService(serviceName = "TrainTicketSOAPService")
@Component
public class TrainTicketSOAPService {

    @Autowired
    private TrainTicketRepository trainTicketRepository;

    @WebMethod(operationName = "getTicketById")
    public TrainTicket getTicketById(@WebParam(name = "id") Integer id) {
        if (id == null) {
            return null;
        }

        Optional<TrainTicket> ticket = trainTicketRepository.findById(id);
        return ticket.orElse(null);
    }

    @WebMethod(operationName = "getTicketCount")
    public long getTicketCount() {
        return trainTicketRepository.count();
    }
}