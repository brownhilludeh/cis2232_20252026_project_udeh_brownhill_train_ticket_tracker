package ca.hccis.t3.entity;

import ca.hccis.t3.util.CisUtility;
import com.google.gson.Gson;

public class TrainTicketTracker {

    // Passenger information
    private String name;
    private String dateIssued;
    private String station;
    private String departureTime;
    private String destination;
    private int travelLength;
    private int ticketPrice;
    private boolean isStudent;
    private boolean isFrequent;

    /**
     * Prompt the user to enter passenger information
     */
    public void getInformation() {
        this.name = CisUtility.getInputString("Enter passenger's name: ");
        this.dateIssued = CisUtility.getInputString("Enter date issued: ");
        this.station = CisUtility.getInputString("Enter station: ");
        this.departureTime = CisUtility.getInputString("Enter departure time: ");
        this.destination = CisUtility.getInputString("Enter destination: ");
        this.travelLength = CisUtility.getInputInt("Enter travel length: ");
        this.ticketPrice = CisUtility.getInputInt("Enter ticket price: ");
        this.isFrequent = CisUtility.getInputBoolean("Is the passenger a frequent traveler? ");
        this.isStudent = CisUtility.getInputBoolean("Is the passenger a student? ");
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDateIssued() { return dateIssued; }
    public void setDateIssued(String dateIssued) { this.dateIssued = dateIssued; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getTravelLength() { return travelLength; }
    public void setTravelLength(int travelLength) { this.travelLength = travelLength; }

    public int getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(int ticketPrice) { this.ticketPrice = ticketPrice; }

    public boolean isStudent() { return isStudent; }
    public void setStudent(boolean student) { this.isStudent = student; }

    public boolean isFrequent() { return isFrequent; }
    public void setFrequent(boolean frequent) { this.isFrequent = frequent; }

    /**
     * Convert this object to JSON string
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     * Override toString to display passenger information
     */
    @Override
    public String toString() {
        return "Passenger: " + name
                + "\nDate Issued: " + dateIssued
                + "\nStation: " + station
                + "\nDeparture Time: " + departureTime
                + "\nDestination: " + destination
                + "\nTravel Length: " + travelLength
                + "\nTicket Price: " + ticketPrice
                + "\nStudent: " + isStudent
                + "\nFrequent Traveler: " + isFrequent;
    }

    /**
     * Static helper to convert any object to JSON
     */
    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
