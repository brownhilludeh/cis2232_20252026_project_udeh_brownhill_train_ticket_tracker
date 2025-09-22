package ca.hccis.t3.soap;

import ca.hccis.t3.dao.TrainTicketDAO;
import ca.hccis.t3.jpa.entity.BusPass;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public int getCount() {
        return getBusPasses().size();
    }

    @Override
    public List<BusPass> getBusPasses() {

        TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
        ArrayList<BusPass> busPasses = trainTicketDAO.selectAll();
        return busPasses;


    }

}
