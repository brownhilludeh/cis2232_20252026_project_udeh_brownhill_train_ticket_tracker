package ca.hccis.t3.bo;

<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrainTicketBOTest {

    @Test
    void calculateBusPassCost_1length_nonRural_regularType() {

<<<<<<< HEAD
        TrainTicket busPass = new TrainTicket();
=======
        BusPass busPass = new BusPass();
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        busPass.setLengthOfPass(1);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = TrainTicketBO.calculateBusPassCost(busPass);
        double expected = 1.0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateBusPassCost_21_length_nonRural_regularType() {

<<<<<<< HEAD
        TrainTicket busPass = new TrainTicket();
=======
        BusPass busPass = new BusPass();
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        busPass.setLengthOfPass(21);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = TrainTicketBO.calculateBusPassCost(busPass);
        double expected = 20.50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateBusPassCost_20_length_nonRural_regularType() {

<<<<<<< HEAD
        TrainTicket busPass = new TrainTicket();
=======
        BusPass busPass = new BusPass();
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
        busPass.setLengthOfPass(20);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = TrainTicketBO.calculateBusPassCost(busPass);
        double expected = 20;
        Assertions.assertEquals(expected, actual);
    }

}