package ca.hccis.t3.soap;

import javax.xml.ws.Endpoint;

public class TrainTicketServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
          "http://0.0.0.0:8083/trainticketservice",
           new TrainTicketServiceImpl());
//        "http://10.4.33.51:8083/trainticketservice",
//                "http://0.0.0.0:8083/trainticketservice",
//                new TrainTicketServiceImpl());
    }
}