package ca.hccis.t3.exception;

/**
 * Exception class for train ticket related errors.
 * @author Brownhill Udeh
 * @since 2025-12-04
 */
public class TrainTicketException extends RuntimeException {

    public TrainTicketException(String message) {
        super(message);
    }

    public TrainTicketException(){
        super("Train Ticket Exception");
    }
}
