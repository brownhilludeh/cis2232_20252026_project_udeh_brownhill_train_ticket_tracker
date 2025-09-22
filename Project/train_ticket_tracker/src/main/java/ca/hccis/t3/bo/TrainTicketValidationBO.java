package ca.hccis.t3.bo;

import ca.hccis.t3.jpa.entity.TrainTicket;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainTicketValidationBO {

    public ArrayList<String> validateIssueDate(TrainTicket ticket) {

        ArrayList<String> errors = new ArrayList<>();

        String issueDate = ticket.getIssueDate();
        if (issueDate.length() != 10) {
            errors.add("Start date must be 10 length");
        }

        if (errors.isEmpty()) {
            try {
                LocalDate localDateIssueDate = LocalDate.parse(issueDate);

                LocalDate currentDate = LocalDate.now();
                LocalDate oneMonthLater = currentDate.plusMonths(1);

                if (localDateIssueDate.isAfter(oneMonthLater)) {
                    errors.add("Start date is more than one month after the current date");
                }
            } catch (Exception e) {
                errors.add("Could not parse start date");
            }
        }

        return errors;
    }

}
