package ca.hccis.t3.service;

import ca.hccis.t3.jpa.entity.TrainTicket;
import ca.hccis.t3.repositories.TrainTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainTicketService {

    private final TrainTicketRepository repository;

    public TrainTicketService(TrainTicketRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all tickets in a given travel length range.
     * 
     * @param minLength Minimum travel length.
     * @param maxLength Maximum travel length.
     * @return List of tickets in the range.
     * @since 2025-10-10
     * @author Brownhill Udeh
     */
    public List<TrainTicket> getTicketsByLengthRange(int minLength, int maxLength) {
        return repository.findByTravelLengthBetween(minLength, maxLength);
    }

    public List<TrainTicket> getTicketsByDateRange(String startDate, String endDate) {
        return repository.findByIssueDateBetween(startDate, endDate);
    }
}
