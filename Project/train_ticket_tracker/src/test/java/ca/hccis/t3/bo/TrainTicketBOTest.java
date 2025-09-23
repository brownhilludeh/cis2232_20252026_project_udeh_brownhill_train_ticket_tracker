package ca.hccis.t3.bo;

import ca.hccis.t3.jpa.entity.TrainTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TrainTicketBOTest {

    private TrainTicket ticket;

    @BeforeEach
    void setup() {
        ticket = new TrainTicket();
        ticket.setTravelLength(1); // default travel length
        ticket.setIsStudent(false);
        ticket.setIsFrequent(false);
        ticket.setTicketPrice(BigDecimal.ZERO);
    }

    @Test
    void calculateTicketPrice_regularTicket() {
        ticket.setTravelLength(10);
        ticket.setIsStudent(false);
        ticket.setIsFrequent(false);

        double actual = TrainTicketBO.calculateTicketPrice(ticket);
        double expected = 50; // 10 * 5
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new BigDecimal(50), ticket.getTicketPrice());
    }

    @Test
    void calculateTicketPrice_studentTicket() {
        ticket.setTravelLength(10);
        ticket.setIsStudent(true);

        double actual = TrainTicketBO.calculateTicketPrice(ticket);
        double expected = 40; // 10*5=50, student discount 20%
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new BigDecimal(40), ticket.getTicketPrice());
    }

    @Test
    void calculateTicketPrice_frequentRiderTicket() {
        ticket.setTravelLength(10);
        ticket.setIsFrequent(true);

        double actual = TrainTicketBO.calculateTicketPrice(ticket);
        double expected = 42.5; // 10*5=50, frequent discount 15%
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new BigDecimal(43), ticket.getTicketPrice()); // rounded
    }

    @Test
    void calculateTicketPrice_studentAndFrequent() {
        ticket.setTravelLength(10);
        ticket.setIsStudent(true);
        ticket.setIsFrequent(true);

        double actual = TrainTicketBO.calculateTicketPrice(ticket);
        double expected = 34; // 50 -> 20% student = 40 -> 15% frequent = 34
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new BigDecimal(34), ticket.getTicketPrice());
    }

    @Test
    void calculateTicketPrice_zeroLength() {
        ticket.setTravelLength(0);

        double actual = TrainTicketBO.calculateTicketPrice(ticket);
        double expected = 0;
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new BigDecimal(0), ticket.getTicketPrice());
    }
}
