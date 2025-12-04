package ca.hccis.t3.bo;

import ca.hccis.t3.jpa.entity.TrainTicket;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainTicketValidationBO {

    /**
     * Validate the issue date of a train ticket.
     *
     * @param ticket The train ticket to validate the issue date for.
     * @return A list of errors if the issue date is invalid, otherwise an empty
     *         list.
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    public ArrayList<String> validateIssueDate(TrainTicket ticket) {
        ArrayList<String> errors = new ArrayList<>();

        LocalDate issueDate = ticket.getIssueDate();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneDayLater = currentDate.plusDays(1);

        if (issueDate.isAfter(oneDayLater)) {
            errors.add("Start date cannot be more than 1 day after today");
        }

        return errors;
    }
}
