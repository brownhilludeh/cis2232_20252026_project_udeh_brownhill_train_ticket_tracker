package ca.hccis.t3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrainTicketTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String name;
    private String dateIssued;
    private String station;
    private String departureTime;
    private String destination;
    private int travelLength;
    private int ticketPrice;
    private boolean isStudent;
    private boolean isFrequent;

    // Empty constructor required by JPA
    public TrainTicketTracker() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}
