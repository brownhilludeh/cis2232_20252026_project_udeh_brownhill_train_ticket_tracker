package ca.hccis.t3.model.jpa;

import java.util.Objects;
import java.util.Scanner;

/**
 * TrainTicket Entity - matches instructor's pattern
 */
public class TrainTicket {

    private Integer id = 0;
    private String origin;
    private String destination;
    private String departureDate;
    private Double price;
    private String passengerName;
    private String createdDateTime;

    public void getInformation() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter origin: ");
        origin = input.nextLine();

        System.out.print("Enter destination: ");
        destination = input.nextLine();

        System.out.print("Enter departure date (YYYY-MM-DD): ");
        departureDate = input.nextLine();

        System.out.print("Enter price: ");
        price = Double.parseDouble(input.nextLine());

        System.out.print("Enter passenger name: ");
        passengerName = input.nextLine();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "TrainTicket\n" +
                "    id                = " + id + ",\n" +
                "    origin            = '" + origin + "',\n" +
                "    destination       = '" + destination + "',\n" +
                "    departureDate     = '" + departureDate + "',\n" +
                "    price             = " + price + ",\n" +
                "    passengerName     = '" + passengerName + "',\n" +
                "    createdDateTime   = '" + createdDateTime + "'\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrainTicket)) return false;
        TrainTicket that = (TrainTicket) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}