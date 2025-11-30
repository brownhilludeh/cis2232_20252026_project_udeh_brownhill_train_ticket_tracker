package ca.hccis.t3.soap;

import javax.xml.ws.Endpoint;

public class TrainTicketServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
                "http://0.0.0.0:8083/trainticketservice",
                new TrainTicketServiceImpl()
        );

        System.out.println("SOAP TrainTicket service running on:");
        System.out.println(" - http://localhost:8083/trainticketservice?wsdl");
        System.out.println(" - http://" + getLocalIp() + ":8083/trainticketservice?wsdl");
    }

    private static String getLocalIp() {
        try {
            return java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            return "your-local-ip";
        }
    }
}
