package ca.hccis.t3.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity class to represent a train ticket.
 * 
 * @author Brownhill Udeh
 * @since 2025-12-04
 */
@Entity
@Table(name = "Ticket")
public class TrainTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 1, max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date cannot be in the future")
    @Column(name = "issueDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "station", nullable = false, length = 50)
    private String station;

//    @Size(max = 8)
//    @NotNull
    @NotBlank(message = "Departure time is required")
    @Size(max = 8, message = "Departure time must be in the format HH:mm")
    @Column(name = "departureTime", nullable = false, length = 8)
    private String departureTime;

    @NotBlank(message = "Destination is required")
    @Size(min = 3, max = 50, message = "Destination must be between 3 and 50 characters")
    @Column(name = "destination", nullable = false, length = 50)
    private String destination;

    @NotNull(message = "Travel length is required")
    @Min(value = 1, message = "Travel length must be at least 1 km")
    @Column(name = "travelLength", nullable = false)
    private Integer travelLength;

    @NotNull
    @Column(name = "ticketPrice", nullable = false)
    private BigDecimal ticketPrice;

    @NotNull
    @Column(name = "isStudent", nullable = false)
    private Boolean isStudent = false;

    @NotNull
    @Column(name = "isFrequent", nullable = false)
    private Boolean isFrequent = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
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

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Boolean isStudent) {
        this.isStudent = isStudent;
    }

    public Boolean getIsFrequent() {
        return isFrequent;
    }

    public void setIsFrequent(Boolean isFrequent) {
        this.isFrequent = isFrequent;
    }

    public Integer getTravelLength() {
        return travelLength;
    }

    public void setTravelLength(Integer travelLength) {
        this.travelLength = travelLength;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    @Override
    public String toString() {
        return "TrainTicket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", station='" + station + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", destination='" + destination + '\'' +
                ", travelLength=" + travelLength +
                ", ticketPrice=" + ticketPrice +
                ", isStudent=" + isStudent +
                ", isFrequent=" + isFrequent +
                '}';
    }
}