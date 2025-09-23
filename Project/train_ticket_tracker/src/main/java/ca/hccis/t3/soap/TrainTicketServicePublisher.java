package ca.hccis.t3.soap;

import javax.xml.ws.Endpoint;

public class TrainTicketServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
<<<<<<< HEAD
          "http://localhost:8083/trainticketservice",
=======
          "http://localhost:8083/buspassservice",
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
           new TrainTicketServiceImpl());

    }
}