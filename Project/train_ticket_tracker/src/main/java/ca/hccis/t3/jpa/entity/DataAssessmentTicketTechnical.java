package ca.hccis.t3.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DataAssessmentTicketTechnical
 * ----------------------------------------------------
 * Represents a technical or quality assessment for a Train Ticket.
 * Each assessment is linked to a specific TrainTicket record.
 *
 * Author: Brownhill Udeh
 * Date: 2025-10-24
 */
@Entity
@Table(name = "DataAssessmentTicketTechnical")
public class DataAssessmentTicketTechnical {

    @Transient
    private String assessmentCode; // Not stored in DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 1, max = 10)
    @NotNull
    @Column(name = "assessmentDate", nullable = false, length = 10)
    private String assessmentDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "createdDateTime", nullable = false, length = 20)
    private String createdDateTime;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "assessorName", nullable = false, length = 50)
    private String assessorName;

    // Link to TrainTicket — each assessment belongs to one ticket
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TrainTicket trainTicket;

    // Technical metrics for the ticket
    @Column(name = "ticketDataAccuracy")
    private Integer ticketDataAccuracy;

    @Column(name = "ticketValidationSpeed")
    private Integer ticketValidationSpeed;

    @Column(name = "ticketCompleteness")
    private Integer ticketCompleteness;

    @Column(name = "errorCount")
    private Integer errorCount;

    @Column(name = "technicalScore")
    private Integer technicalScore;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public TrainTicket getTrainTicket() {
        return trainTicket;
    }

    public void setTrainTicket(TrainTicket trainTicket) {
        this.trainTicket = trainTicket;
    }

    public Integer getTicketDataAccuracy() {
        return ticketDataAccuracy;
    }

    public void setTicketDataAccuracy(Integer ticketDataAccuracy) {
        this.ticketDataAccuracy = ticketDataAccuracy;
    }

    public Integer getTicketValidationSpeed() {
        return ticketValidationSpeed;
    }

    public void setTicketValidationSpeed(Integer ticketValidationSpeed) {
        this.ticketValidationSpeed = ticketValidationSpeed;
    }

    public Integer getTicketCompleteness() {
        return ticketCompleteness;
    }

    public void setTicketCompleteness(Integer ticketCompleteness) {
        this.ticketCompleteness = ticketCompleteness;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Integer getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(Integer technicalScore) {
        this.technicalScore = technicalScore;
    }

    public String getAssessmentCode() {
        return assessmentCode;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = assessmentCode;
    }

    @Override
    public String toString() {
        return "DataAssessmentTicketTechnical{" +
                "id=" + id +
                ", assessmentDate='" + assessmentDate + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", assessorName='" + assessorName + '\'' +
                ", trainTicketId=" + (trainTicket != null ? trainTicket.getId() : null) +
                ", ticketDataAccuracy=" + ticketDataAccuracy +
                ", ticketValidationSpeed=" + ticketValidationSpeed +
                ", ticketCompleteness=" + ticketCompleteness +
                ", errorCount=" + errorCount +
                ", technicalScore=" + technicalScore +
                '}';
    }
}
