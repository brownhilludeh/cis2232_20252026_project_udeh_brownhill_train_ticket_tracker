package ca.hccis.t3.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket") // matches your table name
public class TrainTicketTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 1st column

    private String name; // 2nd column

    @Column(name = "issueDate")
    private LocalDate issueDate; // 3rd column

    private String station; // 4th column

    private LocalTime departureTime; // 5th column

    private String destination; // 6th column

    private int travelLength; // 7th column

    @Column(name = "ticketPrice")
    private BigDecimal ticketPrice; // 8th column

    @Column(name = "studentIndicator")
    private boolean isStudent; // 9th column

    @Column(name = "frequentRiderIndicator")
    private boolean isFrequent; // 10th column

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.sql.Timestamp createdAt; // 11th column

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.sql.Timestamp updatedAt; // 12th column

    // Empty constructor
    public TrainTicketTracker() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getTravelLength() { return travelLength; }
    public void setTravelLength(int travelLength) { this.travelLength = travelLength; }

    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }

    public boolean isStudent() { return isStudent; }
    public void setStudent(boolean student) { isStudent = student; }

    public boolean isFrequent() { return isFrequent; }
    public void setFrequent(boolean frequent) { isFrequent = frequent; }

    public java.sql.Timestamp getCreatedAt() { return createdAt; }
    public java.sql.Timestamp getUpdatedAt() { return updatedAt; }
}
