package ca.hccis.t3.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainTicketTrackerTest {

    @Test
    void testNoDiscount() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(100);
        ticket.setStudent(false);
        ticket.setFrequent(false);

        assertEquals(100, ticket.calculateTicketPrice());
    }

    @Test
    void testStudentDiscountOnly() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(100);
        ticket.setStudent(true);
        ticket.setFrequent(false);

        assertEquals(90, ticket.calculateTicketPrice()); // 10% off
    }

    @Test
    void testFrequentDiscountOnly() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(200);
        ticket.setStudent(false);
        ticket.setFrequent(true);

        assertEquals(190, ticket.calculateTicketPrice()); // 5% off
    }

    @Test
    void testBothDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(200);
        ticket.setStudent(true);
        ticket.setFrequent(true);

        // Step 1: 200 - 10% = 180
        // Step 2: 180 - 5% = 171
        assertEquals(171, ticket.calculateTicketPrice());
    }

    @Test
    void testZeroPrice() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(0);
        ticket.setStudent(true);
        ticket.setFrequent(true);

        assertEquals(0, ticket.calculateTicketPrice());
    }

    @Test
    void testNegativePriceThrowsException() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(-50);

        assertThrows(IllegalArgumentException.class, ticket::calculateTicketPrice);
    }

    @Test
    void testLargePriceNoDiscount() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(1_000_000);
        ticket.setStudent(false);
        ticket.setFrequent(false);

        int result = ticket.calculateTicketPrice();
        assertEquals(1_000_000, result);
        assertTrue(result > 0); 
    }

    @Test
    void testLargePriceWithDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(1_000_000);
        ticket.setStudent(true);
        ticket.setFrequent(true);

        assertEquals(855_000, ticket.calculateTicketPrice());
    }

    @Test
    void testRoundingDown() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(101);
        ticket.setStudent(true); // 10% → 90.9
        ticket.setFrequent(false);

        assertEquals(91, ticket.calculateTicketPrice()); // rounds up
    }

    @Test
    void testRoundingWithBothDiscounts() {
        TrainTicketTracker ticket = new TrainTicketTracker();
        ticket.setTicketPrice(105);
        ticket.setStudent(true); // 105 - 10% = 94.5
        ticket.setFrequent(true); // 94.5 - 5% = 89.775 → rounds to 90

        assertEquals(90, ticket.calculateTicketPrice());
    }
}
