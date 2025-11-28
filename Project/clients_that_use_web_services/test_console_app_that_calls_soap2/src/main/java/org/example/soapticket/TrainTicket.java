package org.example.soapticket;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for trainTicket complex type.
 *
 * <pre>
 * &lt;complexType name="trainTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id"            type="{http://www.w3.org/2001/XMLSchema}int"    minOccurs="0"/>
 *         &lt;element name="name"          type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueDate"     type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="station"       type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departureTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destination"   type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="travelLength"  type="{http://www.w3.org/2001/XMLSchema}int"    minOccurs="0"/>
 *         &lt;element name="ticketPrice"   type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isStudent"     type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isFrequent"    type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trainTicket", propOrder = {
        "id",
        "name",
        "issueDate",
        "station",
        "departureTime",
        "destination",
        "travelLength",
        "ticketPrice",
        "isStudent",
        "isFrequent"
})
public class TrainTicket {

    protected Integer id;
    protected String name;
    protected String issueDate;      // SOAP-side as String, e.g., "2025-11-27"
    protected String station;
    protected String departureTime;
    protected String destination;
    protected Integer travelLength;
    protected BigDecimal ticketPrice;
    protected Boolean isStudent;
    protected Boolean isFrequent;

    // ----- Getters & Setters -----

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String value) {
        this.station = value;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String value) {
        this.departureTime = value;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String value) {
        this.destination = value;
    }

    public Integer getTravelLength() {
        return travelLength;
    }

    public void setTravelLength(Integer value) {
        this.travelLength = value;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal value) {
        this.ticketPrice = value;
    }

    public Boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Boolean value) {
        this.isStudent = value;
    }

    public Boolean getIsFrequent() {
        return isFrequent;
    }

    public void setIsFrequent(Boolean value) {
        this.isFrequent = value;
    }

    @Override
    public String toString() {
        return "TrainTicket\n" +
                "    id           = " + id + ",\n" +
                "    name         = '" + name + "',\n" +
                "    issueDate    = '" + issueDate + "',\n" +
                "    station      = '" + station + "',\n" +
                "    departureTime= '" + departureTime + "',\n" +
                "    destination  = '" + destination + "',\n" +
                "    travelLength = " + travelLength + ",\n" +
                "    ticketPrice  = " + ticketPrice + ",\n" +
                "    isStudent    = " + isStudent + ",\n" +
                "    isFrequent   = " + isFrequent + "\n";
    }
}
