package ca.hccis.t3.soap;

import javax.xml.ws.Endpoint;

public class TrainTicketServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
          "http://localhost:8083/trainticketservice",
           new TrainTicketServiceImpl());

    }
}