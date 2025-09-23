package ca.hccis.t3.soap;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.TrainTicket;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ca.hccis.t3.soap.TrainTicketService")
public class TrainTicketServiceImpl implements TrainTicketService {

    public TrainTicket getTicket(int id) {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        TrainTicket ticket = null;
        for (TrainTicket current : getTickets()) {
            if (current.getId().equals(id)) {
                ticket = current;
            }
        }
        return ticket;

    }

    @Override
    public int getCount() {
        return getTickets().size();
    }

    @Override
    public List<TrainTicket> getTickets() {
        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        return trainTicketDAO.selectAll();
    }
    
    // This is a typo method that should be removed in production
    @Deprecated
    private List<TrainTicket> getTicketes() {
        return getTickets();
    }

}
