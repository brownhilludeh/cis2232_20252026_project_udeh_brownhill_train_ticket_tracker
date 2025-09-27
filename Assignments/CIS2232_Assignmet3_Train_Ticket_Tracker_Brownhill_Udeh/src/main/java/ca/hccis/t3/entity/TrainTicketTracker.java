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

    // ***************************************************************************
    // Will use a CisUtility object that can be set to use either console or gui.
    // Note if it is transient, it will not be encoded in the json strings.
    // ***************************************************************************
    private transient CisUtility cisUtility = new CisUtility();

    public TrainTicketTracker(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    public TrainTicketTracker() {
        //TODO Auto-generated constructor stub
    }

    /**
     * Prompt the user to enter passenger information
     */
    public void getInformation() {
        this.name = cisUtility.getInputString("Enter passenger's name: ");
        this.dateIssued = cisUtility.getInputString("Enter date issued: ");
        this.station = cisUtility.getInputString("Enter station: ");
        this.departureTime = cisUtility.getInputString("Enter departure time: ");
        this.destination = cisUtility.getInputString("Enter destination: ");
        this.travelLength = cisUtility.getInputInt("Enter travel length: ");
        this.ticketPrice = cisUtility.getInputInt("Enter ticket price: ");
        this.isFrequent = cisUtility.getInputBoolean("Is the passenger a frequent traveler? ");
        this.isStudent = cisUtility.getInputBoolean("Is the passenger a student? ");
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
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

    public int getTravelLength() {
        return travelLength;
    }

    public void setTravelLength(int travelLength) {
        this.travelLength = travelLength;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        this.isStudent = student;
    }

    public boolean isFrequent() {
        return isFrequent;
    }

    public void setFrequent(boolean frequent) {
        this.isFrequent = frequent;
    }

    /**
     * Convert this object to JSON string
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     * Calculate the final ticket price based on discounts.
     * 
     * @return the discounted ticket price
     * @throws IllegalArgumentException if base ticket price is negative
     */
    public int calculateTicketPrice() {
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Ticket price cannot be negative");
        }

        double finalPrice = ticketPrice;

        // Apply student discount (10%)
        if (isStudent) {
            finalPrice -= (finalPrice * 0.10);
        }

        // Apply frequent traveler discount (5%)
        if (isFrequent) {
            finalPrice -= (finalPrice * 0.05);
        }

        return (int) Math.round(finalPrice); // round to nearest whole number since my BA (Ryley) said so.
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
