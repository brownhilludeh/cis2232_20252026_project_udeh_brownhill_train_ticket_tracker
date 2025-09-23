package ca.hccis.t3.soap;

import ca.hccis.t3.dao.TrainTicketDAO;
<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
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
=======
@WebService(endpointInterface = "info.hccis.bus.pass.soap.BusPassService")
public class TrainTicketServiceImpl implements BusPassService {

    public BusPass getBusPass(int id) {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        BusPass busPass = null;
        for(BusPass current: getBusPasses()){
            if(current.getId().equals(id)){
                busPass = current;
            }
        }
        return busPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

    }

    @Override
    public int getCount() {
<<<<<<< HEAD
        return getTicketes().size();
    }

    @Override
    public List<TrainTicket> getTicketes() {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<TrainTicket> ticketes = trainTicketDAO.selectAll();
        return ticketes;
=======
        return getBusPasses().size();
    }

    @Override
    public List<BusPass> getBusPasses() {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<BusPass> busPasses = trainTicketDAO.selectAll();
        return busPasses;

>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

    }

}
