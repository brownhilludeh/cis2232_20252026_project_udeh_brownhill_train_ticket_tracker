package ca.hccis.t3.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainTicketTrackerTest {

    @Test
    void testNoDiscount() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(20); // base = 20 * 5 = 100
        ticket.setStudent(false);
        ticket.setFrequent(false);

        assertEquals(100, ticket.calculateTicketPrice());
    }

    @Test
    void testStudentDiscountOnly() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(20); // base = 100
        ticket.setStudent(true);
        ticket.setFrequent(false);

        assertEquals(80, ticket.calculateTicketPrice()); // 20% off
    }

    @Test
    void testFrequentDiscountOnly() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(40); // base = 200
        ticket.setStudent(false);
        ticket.setFrequent(true);

        assertEquals(170, ticket.calculateTicketPrice()); // 15% off
    }

    @Test
    void testBothDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(40); // base = 200
        ticket.setStudent(true);
        ticket.setFrequent(true);

        // Step 1: 200 - 20% = 160
        // Step 2: 160 - 15% = 136
        assertEquals(136, ticket.calculateTicketPrice());
    }

    @Test
    void testZeroTravelLength() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(0); // base = 0
        ticket.setStudent(true);
        ticket.setFrequent(true);

        assertEquals(0, ticket.calculateTicketPrice());
    }

    @Test
    void testNegativeTicketPriceThrowsException() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(-50); // direct override
        assertThrows(IllegalArgumentException.class, ticket::calculateTicketPrice);
    }

    @Test
    void testLargeTravelLengthNoDiscount() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(200_000); // base = 1_000_000
        ticket.setStudent(false);
        ticket.setFrequent(false);

        int result = ticket.calculateTicketPrice();
        assertEquals(1_000_000, result);
        assertTrue(result > 0);
    }

    @Test
    void testLargeTravelLengthWithDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(200_000); // base = 1_000_000
        ticket.setStudent(true);
        ticket.setFrequent(true);

        assertEquals(680_000, ticket.calculateTicketPrice()); // 20% then 15% off
    }

    @Test
    void testRoundingDown() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(21); // base = 105
        ticket.setStudent(true); // 105 - 20% = 84
        ticket.setFrequent(false);

        assertEquals(84, ticket.calculateTicketPrice());
    }

    @Test
    void testRoundingWithBothDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTravelLength(21); // base = 105
        ticket.setStudent(true); // 105 - 20% = 84
        ticket.setFrequent(true); // 84 - 15% = 71.4 → rounds to 71

        assertEquals(71, ticket.calculateTicketPrice());
    }
}
