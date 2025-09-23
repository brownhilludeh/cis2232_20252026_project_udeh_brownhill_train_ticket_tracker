package ca.hccis.t3.bo;

<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainTicketValidationBO {

<<<<<<< HEAD
    public ArrayList<String> validateIssueDate(TrainTicket ticket) {

        ArrayList<String> errors = new ArrayList<>();

        String issueDate = ticket.getIssueDate();
        if (issueDate.length() != 10) {
=======
    public ArrayList<String> validateStartDate(BusPass busPass) {

        ArrayList<String> errors = new ArrayList<>();

        String startDate = busPass.getStartDate();
        if (startDate.length() != 10) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
            errors.add("Start date must be 10 length");
        }

        if (errors.isEmpty()) {
            try {
<<<<<<< HEAD
                LocalDate localDateIssueDate = LocalDate.parse(issueDate);
=======
                LocalDate localDateStartDate = LocalDate.parse(startDate);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

                LocalDate currentDate = LocalDate.now();
                LocalDate oneMonthLater = currentDate.plusMonths(1);

<<<<<<< HEAD
                if (localDateIssueDate.isAfter(oneMonthLater)) {
=======
                if (localDateStartDate.isAfter(oneMonthLater)) {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
                    errors.add("Start date is more than one month after the current date");
                }
            } catch (Exception e) {
                errors.add("Could not parse start date");
            }
        }
<<<<<<< HEAD
=======
        //TODO Add validation to ensure start date is not > 1 month in the future.
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

        return errors;
    }

}
