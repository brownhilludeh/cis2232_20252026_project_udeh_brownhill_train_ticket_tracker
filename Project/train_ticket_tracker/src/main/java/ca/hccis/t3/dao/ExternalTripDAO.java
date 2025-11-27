package ca.hccis.t3.dao;

/**
 * DAO class for external train trip/journey information from Deutsche Bahn API.
 *
 * @author Udeh Brownhill
 * @since 20251115
 */
public class ExternalTripDAO {

    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String trainLine;
    private Integer numberOfLegs;
    private Double price;
    private String platform;
    private Integer delayMinutes;

    public ExternalTripDAO() {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTrainLine() {
        return trainLine;
    }

    public void setTrainLine(String trainLine) {
        this.trainLine = trainLine;
    }

    public Integer getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(Integer numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    @Override
    public String toString() {
        return "ExternalTripDAO{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", trainLine='" + trainLine + '\'' +
                ", numberOfLegs=" + numberOfLegs +
                ", price=" + price +
                ", platform='" + platform + '\'' +
                ", delayMinutes=" + delayMinutes +
                '}';
    }
}