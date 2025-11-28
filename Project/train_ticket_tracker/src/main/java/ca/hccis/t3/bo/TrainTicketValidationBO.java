package ca.hccis.t3.bo;

import ca.hccis.t3.jpa.entity.TrainTicket;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Business validation for TrainTicket
 * Following instructor's pattern
 */
public class TrainTicketValidationBO {

    /**
     * Validate issue date
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateIssueDate(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        LocalDate issueDate = trainTicket.getIssueDate();

        // Check if date exists
        if (issueDate == null) {
            errors.add("Issue date is required");
            return errors;
        }

        LocalDate currentDate = LocalDate.now();
        int compareValue = issueDate.compareTo(currentDate);

        // Issue date cannot be in the future
        if (compareValue > 0) {
            errors.add("Issue date cannot be in the future");
        }

        // Optional: Check if issue date is too far in the past (e.g., more than 1 year)
        LocalDate oneYearAgo = currentDate.minusYears(1);
        if (issueDate.isBefore(oneYearAgo)) {
            errors.add("Issue date cannot be more than 1 year in the past");
        }

        return errors;
    }

    /**
     * Validate name
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateName(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        String name = trainTicket.getName();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Passenger name is required");
        } else if (name.length() < 1) {
            errors.add("Passenger name must be at least 1 character");
        } else if (name.length() > 20) {
            errors.add("Passenger name cannot exceed 20 characters");
        }

        return errors;
    }

    /**
     * Validate station
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateStation(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        String station = trainTicket.getStation();

        if (station == null || station.trim().isEmpty()) {
            errors.add("Station is required");
        } else if (station.length() < 1) {
            errors.add("Station must be at least 1 character");
        } else if (station.length() > 50) {
            errors.add("Station cannot exceed 50 characters");
        }

        return errors;
    }

    /**
     * Validate destination
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateDestination(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        String destination = trainTicket.getDestination();

        if (destination == null || destination.trim().isEmpty()) {
            errors.add("Destination is required");
        } else if (destination.length() < 1) {
            errors.add("Destination must be at least 1 character");
        } else if (destination.length() > 50) {
            errors.add("Destination cannot exceed 50 characters");
        }

        // Check if station and destination are the same
        String station = trainTicket.getStation();
        if (station != null && destination != null &&
                station.trim().equalsIgnoreCase(destination.trim())) {
            errors.add("Destination must be different from station");
        }

        return errors;
    }

    /**
     * Validate departure time
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateDepartureTime(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        String departureTime = trainTicket.getDepartureTime();

        if (departureTime == null || departureTime.trim().isEmpty()) {
            errors.add("Departure time is required");
        } else if (departureTime.length() > 8) {
            errors.add("Departure time cannot exceed 8 characters");
        }

        // Optional: Validate time format (HH:MM:SS or HH:MM)
        if (departureTime != null && !departureTime.trim().isEmpty()) {
            if (!departureTime.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$")) {
                errors.add("Departure time must be in format HH:MM or HH:MM:SS");
            }
        }

        return errors;
    }

    /**
     * Validate travel length
     * @param trainTicket the train ticket to validate
     * @return ArrayList of error messages
     */
    public static ArrayList<String> validateTravelLength(TrainTicket trainTicket) {
        ArrayList<String> errors = new ArrayList<>();

        Integer travelLength = trainTicket.getTravelLength();

        if (travelLength == null) {
            errors.add("Travel length is required");
        } else if (travelLength <= 0) {
            errors.add("Travel length must be greater than 0");
        } else if (travelLength > 1440) { // 24 hours in minutes
            errors.add("Travel length cannot exceed 1440 minutes (24 hours)");
        }

        return errors;
    }

    /**
     * Validate all fields at once
     * @param trainTicket the train ticket to validate
     * @return ArrayList of all error messages
     */
    public static ArrayList<String> validateAll(TrainTicket trainTicket) {
        ArrayList<String> allErrors = new ArrayList<>();

        allErrors.addAll(validateName(trainTicket));
        allErrors.addAll(validateIssueDate(trainTicket));
        allErrors.addAll(validateStation(trainTicket));
        allErrors.addAll(validateDestination(trainTicket));
        allErrors.addAll(validateDepartureTime(trainTicket));
        allErrors.addAll(validateTravelLength(trainTicket));

        return allErrors;
    }
}