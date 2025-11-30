package ca.hccis.t3.bo;

import ca.hccis.t3.jpa.entity.TrainTicket;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainTicketValidationBO {

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
