package ca.hccis.t3.client;

import ca.hccis.t3.jpa.entity.TrainTicket;

public interface TrainTicketSOAPInterface {

    TrainTicket getTicketById(Integer id);
    long getTicketCount();

}
