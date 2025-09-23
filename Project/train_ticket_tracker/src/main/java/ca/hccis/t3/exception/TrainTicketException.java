package ca.hccis.t3.exception;

public class TrainTicketException extends RuntimeException {

    public TrainTicketException(String message) {
        super(message);
    }

    public TrainTicketException(){
        super("Train Ticket Exception");
    }
}
