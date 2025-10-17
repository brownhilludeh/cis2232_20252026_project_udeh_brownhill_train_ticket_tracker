package ca.hccis.t3.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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

    @Size(max = 10)
    @NotNull
    @Column(name = "issueDate", nullable = false, length = 10)
    private String issueDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "station", nullable = false, length = 50)
    private String station;

    @Size(max = 8)
    @NotNull
    @Column(name = "departureTime", nullable = false, length = 8)
    private String departureTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "destination", nullable = false, length = 50)
    private String destination;

    @NotNull
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

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
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