package ca.hccis.t3.entity;

<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the bus pass related reports.
<<<<<<< HEAD
 * 
=======
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
 * @author bjmaclean
 * @since 20241010
 */
public class ReportTrainTicket {
<<<<<<< HEAD
    private String issuedDate;
    private String station;
    private String departureTime;
    private String destination;
    private int ticketPrice;

    private ArrayList<TrainTicket> tickets;

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
=======
    private String dateStart;
    private String dateEnd;
    private int minLength;
    private ArrayList<BusPass> busPasses;

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ArrayList<BusPass> getBusPasses() {
        return busPasses;
    }

    public void setBusPasses(ArrayList<BusPass> busPasses) {
        this.busPasses = busPasses;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }
}
