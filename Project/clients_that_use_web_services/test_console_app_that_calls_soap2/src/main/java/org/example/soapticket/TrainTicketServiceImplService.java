package org.example.soapticket;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * SOAP client for TrainTicketService.
 */
@WebServiceClient(
        name = "TrainTicketServiceImplService",
        targetNamespace = "http://soap.trainticket.hccis.ca/",
        wsdlLocation = "http://10.4.33.51:8083/trainticketservice?wsdl"   // <-- change if needed
)
public class TrainTicketServiceImplService extends Service {

    private static final URL TRAINTICKETSERVICEIMPLSERVICE_WSDL_LOCATION;
    private static final WebServiceException TRAINTICKETSERVICEIMPLSERVICE_EXCEPTION;

    private static final QName TRAINTICKETSERVICEIMPLSERVICE_QNAME =
            new QName("http://soap.trainticket.hccis.ca/", "TrainTicketServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.4.33.51:8083/trainticketservice?wsdl"); // <-- same URL as above
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRAINTICKETSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        TRAINTICKETSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public TrainTicketServiceImplService() {
        super(__getWsdlLocation(), TRAINTICKETSERVICEIMPLSERVICE_QNAME);
    }

    public TrainTicketServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRAINTICKETSERVICEIMPLSERVICE_QNAME, features);
    }

    public TrainTicketServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, TRAINTICKETSERVICEIMPLSERVICE_QNAME);
    }

    public TrainTicketServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRAINTICKETSERVICEIMPLSERVICE_QNAME, features);
    }

    public TrainTicketServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TrainTicketServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * Get the TrainTicketService port.
     */
    @WebEndpoint(name = "TrainTicketServiceImplPort")
    public TrainTicketService getTrainTicketServiceImplPort() {
        return super.getPort(
                new QName("http://soap.trainticket.hccis.ca/", "TrainTicketServiceImplPort"),
                TrainTicketService.class
        );
    }

    /**
     * Get the TrainTicketService port with features.
     */
    @WebEndpoint(name = "TrainTicketServiceImplPort")
    public TrainTicketService getTrainTicketServiceImplPort(WebServiceFeature... features) {
        return super.getPort(
                new QName("http://soap.trainticket.hccis.ca/", "TrainTicketServiceImplPort"),
                TrainTicketService.class,
                features
        );
    }

    private static URL __getWsdlLocation() {
        if (TRAINTICKETSERVICEIMPLSERVICE_EXCEPTION != null) {
            throw TRAINTICKETSERVICEIMPLSERVICE_EXCEPTION;
        }
        return TRAINTICKETSERVICEIMPLSERVICE_WSDL_LOCATION;
    }
}
