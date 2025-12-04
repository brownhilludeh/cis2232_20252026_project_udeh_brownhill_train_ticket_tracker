package ca.hccis.t3;

import ca.hccis.t3.rest.TrainTicketService;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Jersey REST API configuration class.
 * 
 * @author Brownhill Udeh
 * @since 2025-12-04
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    /**
     * Initializes the Jersey REST API configuration.
     * Registers the TrainTicketService class and prints a success message to the
     * console.
     * 
     * @PostConstruct
     * @author Brownhill Udeh
     * @since 2025-12-04
     */
    @PostConstruct
    private void init() {
        registerClasses(TrainTicketService.class);
        System.out.println("========================================");
        System.out.println("Jersey REST API Configured Successfully!");
        System.out.println("REST endpoints available at: /api/train-departures");
        System.out.println("========================================");
    }
}
