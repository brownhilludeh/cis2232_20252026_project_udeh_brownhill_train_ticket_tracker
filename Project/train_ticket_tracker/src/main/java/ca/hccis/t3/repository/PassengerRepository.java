package ca.hccis.t3.repository;

import ca.hccis.t3.entity.TrainTicketTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<TrainTicketTracker, Long> {
}
