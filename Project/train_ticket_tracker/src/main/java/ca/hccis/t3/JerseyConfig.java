/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hccis.t3;

<<<<<<< HEAD
import ca.hccis.t3.rest.TrainTicketService;
=======
import ca.hccis.t3.rest.BusPassService;
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author Logan
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
<<<<<<< HEAD
        registerClasses(TrainTicketService.class);
=======
        registerClasses(BusPassService.class);
>>>>>>> 2605daf5da24552d95991c268ac8b80512ead69e
    }
}
