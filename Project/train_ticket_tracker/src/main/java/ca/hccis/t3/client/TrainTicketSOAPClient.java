package ca.hccis.t3.client;

import ca.hccis.t3.jpa.entity.TrainTicket;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class TrainTicketSOAPClient {

    public static void main(String[] args) {
        try {
            // WSDL URL
            URL wsdlURL = new URL("http://localhost:8080/ws/TrainTicketSOAPService?wsdl");

            // QName for the service
            QName qname = new QName("http://soap.t3.hccis.ca/", "TrainTicketSOAPService");

            // Create service
            Service service = Service.create(wsdlURL, qname);

            // Get port
            TrainTicketSOAPInterface port = service.getPort(TrainTicketSOAPInterface.class);

            // Test: Get ticket by ID
            System.out.println("=== Testing SOAP Service ===");
            System.out.println("\nTest 1: Get Ticket by ID = 1");

            TrainTicket ticket = port.getTicketById(1);

            if (ticket != null) {
                System.out.println("SUCCESS! Found ticket:");
                System.out.println("  ID: " + ticket.getId());
                System.out.println("  Name: " + ticket.getName());
                System.out.println("  Station: " + ticket.getStation());
                System.out.println("  Destination: " + ticket.getDestination());
                System.out.println("  Price: $" + ticket.getTicketPrice());
            } else {
                System.out.println("No ticket found with ID = 1");
            }

            // Test: Get total count
            System.out.println("\nTest 2: Get Total Ticket Count");
            long count = port.getTicketCount();
            System.out.println("Total tickets in database: " + count);

            System.out.println("\n=== SOAP Client Test Complete ===");

        } catch (Exception e) {
            System.err.println("Error calling SOAP service:");
            e.printStackTrace();
        }
    }
}
