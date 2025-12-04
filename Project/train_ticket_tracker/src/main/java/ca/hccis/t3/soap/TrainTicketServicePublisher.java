package ca.hccis.t3.soap;

import javax.xml.ws.Endpoint;

public class TrainTicketServicePublisher {
    /**
     * The main entry point of the SOAP TrainTicket service publisher.
     * <p>
     * This method publishes the SOAP TrainTicket service at the given URL.
     * It also prints out the URLs at which the service can be accessed.
     *
     * @param args The command line arguments.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public static void main(String[] args) {
        Endpoint.publish(
                "http://0.0.0.0:8083/trainticketservice",
                new TrainTicketServiceImpl());

        System.out.println("SOAP TrainTicket service running on:");
        System.out.println(" - http://localhost:8083/trainticketservice?wsdl");
        System.out.println(" - http://" + getLocalIp() + ":8083/trainticketservice?wsdl");
    }

    /**
     * Get the local IP address.
     *
     * @return The local IP address, or "your-local-ip" if there is an error.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    private static String getLocalIp() {
        try {
            return java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            return "your-local-ip";
        }
    }
}
