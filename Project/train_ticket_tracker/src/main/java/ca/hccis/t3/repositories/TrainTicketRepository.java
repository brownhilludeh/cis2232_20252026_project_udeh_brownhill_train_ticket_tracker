package ca.hccis.t3.repositories;

<<<<<<< HEAD
import ca.hccis.t3.jpa.entity.TrainTicket;
=======
import ca.hccis.t3.jpa.entity.BusPass;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface TrainTicketRepository extends CrudRepository<TrainTicket, Integer> {
=======
public interface TrainTicketRepository extends CrudRepository<BusPass, Integer> {
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    /**
     * Use Spring Data JPA functionality to find a list of bus passes containing the
     * string passed in as a paramter.
     *
     * @param name The name to find
     * @return The list of items
     * @since 20241031
     * @author BJM
     */
    //https://www.baeldung.com/spring-jpa-like-queries
<<<<<<< HEAD
    List<TrainTicket> findByNameContaining(String name);
=======
    List<BusPass> findByNameContaining(String name);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e

}