package org.example.soapticket;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Factory for objects in the org.example.soapticket package.
 */
@XmlRegistry
public class ObjectFactory {

    // Must match package-info.java and your WSDL targetNamespace
    private static final String NAMESPACE = "http://soap.trainticket.hccis.ca/";

    private static final QName _GetTickets_QNAME =
            new QName(NAMESPACE, "getTickets");
    private static final QName _GetTicketsResponse_QNAME =
            new QName(NAMESPACE, "getTicketsResponse");

    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTickets}
     */
    public GetTickets createGetTickets() {
        return new GetTickets();
    }

    /**
     * Create an instance of {@link GetTicketsResponse}
     */
    public GetTicketsResponse createGetTicketsResponse() {
        return new GetTicketsResponse();
    }

    /**
     * Create an instance of {@link TrainTicket}
     */
    public TrainTicket createTrainTicket() {
        return new TrainTicket();
    }

    /**
     * Create a JAXBElement for getTickets.
     */
    @XmlElementDecl(namespace = NAMESPACE, name = "getTickets")
    public JAXBElement<GetTickets> createGetTickets(GetTickets value) {
        return new JAXBElement<>(_GetTickets_QNAME, GetTickets.class, null, value);
    }

    /**
     * Create a JAXBElement for getTicketsResponse.
     */
    @XmlElementDecl(namespace = NAMESPACE, name = "getTicketsResponse")
    public JAXBElement<GetTicketsResponse> createGetTicketsResponse(GetTicketsResponse value) {
        return new JAXBElement<>(_GetTicketsResponse_QNAME, GetTicketsResponse.class, null, value);
    }
}
