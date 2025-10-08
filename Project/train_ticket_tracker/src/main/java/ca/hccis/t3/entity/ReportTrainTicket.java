package ca.hccis.t3.entity;

import ca.hccis.t3.jpa.entity.TrainTicket;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * Entity class to hold the attributes of the bus pass related reports.
 * 
 * @author bjmaclean
 * @since 20241010
 */
public class ReportTrainTicket {
    private Integer minLength;
    private Integer maxLength;

    private String issuedDate;
    private String station;
    private String departureTime;
    private String destination;
    private int ticketPrice;

    private ArrayList<TrainTicket> tickets;


    
    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public ArrayList<TrainTicket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<TrainTicket> tickets) {
        this.tickets = tickets;
    }
}
