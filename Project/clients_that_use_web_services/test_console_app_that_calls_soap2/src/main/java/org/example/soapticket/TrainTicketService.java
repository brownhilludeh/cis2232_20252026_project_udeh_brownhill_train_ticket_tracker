package org.example.soapticket;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * SOAP TrainTicketService client interface.
 */
@WebService(
        name = "TrainTicketService",
        targetNamespace = "http://soap.trainticket.hccis.ca/"
)
@XmlSeeAlso({
        ObjectFactory.class
})
public interface TrainTicketService {

    /**
     * Get all train tickets.
     *
     * @return list of TrainTicket
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(
            localName = "getTickets",
            targetNamespace = "http://soap.trainticket.hccis.ca/",
            className = "org.example.soapticket.GetTickets"
    )
    @ResponseWrapper(
            localName = "getTicketsResponse",
            targetNamespace = "http://soap.trainticket.hccis.ca/",
            className = "org.example.soapticket.GetTicketsResponse"
    )
    @Action(
            input = "http://soap.trainticket.hccis.ca/TrainTicketService/getTicketsRequest",
            output = "http://soap.trainticket.hccis.ca/TrainTicketService/getTicketsResponse"
    )
    public List<TrainTicket> getTickets();

}
