package ca.hccis.t3.repositories;

import ca.hccis.t3.jpa.entity.TrainTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainTicketRepository extends CrudRepository<TrainTicket, Integer> {
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
    List<TrainTicket> findByNameContaining(String name);

    // Find tickets where travelLength is between min and max
    List<TrainTicket> findByTravelLengthBetween(Integer minLength, Integer maxLength);

    // Find tickets where issuedDate is between start and end
    List<TrainTicket> findByIssueDateBetween(String start, String end);
}