package ca.hccis.t3.entity;

import ca.hccis.t3.jpa.entity.TrainTicket;
import java.util.List;

/**
 * Description: ReportTrainTicket
 * 
 * @author Brownhill Udeh
 * @since 2025-10-31
 */
public class ReportTrainTicket {

    private Integer minLength;
    private Integer maxLength;
    private String startDate;
    private String endDate;

    // Add this
    private List<TrainTicket> tickets;

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    // Getter & Setter for tickets
    public List<TrainTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<TrainTicket> tickets) {
        this.tickets = tickets;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
