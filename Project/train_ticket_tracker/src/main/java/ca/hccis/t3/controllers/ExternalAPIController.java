package ca.hccis.t3.controllers;

import ca.hccis.t3.dao.ExternalStationDAO;
import ca.hccis.t3.dao.ExternalTripDAO;
import ca.hccis.t3.service.ExternalTrainAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for External Train API integration pages.
 * Handles Deutsche Bahn API interactions through web interface.
 *
 * @author Udeh Brownhill
 * @since 20251115
 * @version 4 API
 */
@Controller
public class ExternalAPIController {

    private static final Logger logger = LoggerFactory.getLogger(ExternalAPIController.class);
    private final ExternalTrainAPIService externalTrainAPIService;

    @Autowired
    public ExternalAPIController(ExternalTrainAPIService externalTrainAPIService) {
        this.externalTrainAPIService = externalTrainAPIService;
    }

    /**
     * Display the main external API search page.
     *
     * @param model Spring Model
     * @return View name
     *
     * @author Udeh Brownhill
     * @since 20251115
     * @version 4 API
     */
    @GetMapping("/external-api")
    public String showExternalAPI(Model model) {
        logger.info("Displaying external API page");
        return "externalAPI";
    }

    /**
     * Search for stations by name.
     *
     * @param query Station name to search
     * @param model Spring Model
     * @return View name
     *
     * @author Udeh Brownhill
     * @since 20251115
     * @version 4 API
     */
    @PostMapping("/external-api/search-stations")
    public String searchStations(@RequestParam("query") String query, Model model) {
        logger.info("Searching for stations with query: {}", query);

        try {
            List<ExternalStationDAO> stations = externalTrainAPIService.searchStations(query);
            model.addAttribute("stations", stations);
            model.addAttribute("searchQuery", query);

            if (stations.isEmpty()) {
                model.addAttribute("message", "No stations found for: " + query);
                logger.info("No stations found for query: {}", query);
            } else {
                logger.info("Found {} stations for query: {}", stations.size(), query);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error searching stations: " + e.getMessage());
            logger.error("Error searching stations: {}", e.getMessage(), e);
        }

        return "externalAPI";
    }

    /**
     * Find journeys between two stations.
     *
     * @param fromStationId Origin station ID
     * @param toStationId Destination station ID
     * @param departureTime Departure time
     * @param model Spring Model
     * @return View
     *
     * @author Udeh Brownhill
     * @since 20251115
     * @version 4 API
     */
    @PostMapping("/external-api/find-journeys")
    public String findJourneys(
            @RequestParam("fromStationId") String fromStationId,
            @RequestParam("toStationId") String toStationId,
            @RequestParam("departureTime") String departureTime,
            Model model) {

        logger.info("Finding journeys from {} to {} at {}", fromStationId, toStationId, departureTime);

        try {
            // Format the departure time for the API (ISO format)
            // Input format from datetime-local: 2025-11-14T14:00
            String formattedTime = departureTime; // Already in correct format

            List<ExternalTripDAO> journeys = externalTrainAPIService.findJourneys(
                    fromStationId, toStationId, formattedTime);

            model.addAttribute("journeys", journeys);
            model.addAttribute("fromStationId", fromStationId);
            model.addAttribute("toStationId", toStationId);
            model.addAttribute("departureTime", departureTime);

            if (journeys.isEmpty()) {
                model.addAttribute("message", "No journeys found for the selected criteria.");
                logger.info("No journeys found");
            } else {
                logger.info("Found {} journeys", journeys.size());
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error finding journeys: " + e.getMessage());
            logger.error("Error finding journeys: {}", e.getMessage(), e);
        }

        return "externalAPI";
    }

    /**
     * Get real-time departures from a station.
     *
     * @param stationId Station ID
     * @param duration Duration in minutes
     * @param model Spring Model
     * @return View
     *
     * @author Udeh Brownhill
     * @since 20251115
     * @version 4 API
     */
    @PostMapping("/external-api/departures")
    public String getDepartures(
            @RequestParam("stationId") String stationId,
            @RequestParam(value = "duration", defaultValue = "120") int duration,
            Model model) {

        logger.info("Getting departures for station {} with duration {}", stationId, duration);

        try {
            List<ExternalTripDAO> departures = externalTrainAPIService.getDepartures(stationId, duration);

            model.addAttribute("departures", departures);
            model.addAttribute("stationId", stationId);
            model.addAttribute("duration", duration);

            if (departures.isEmpty()) {
                model.addAttribute("message", "No departures found for this station.");
                logger.info("No departures found for station {}", stationId);
            } else {
                logger.info("Found {} departures for station {}", departures.size(), stationId);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error getting departures: " + e.getMessage());
            logger.error("Error getting departures: {}", e.getMessage(), e);
        }

        return "externalAPI";
    }
//
//    @GetMapping("/external-api/test-service")
//    @ResponseBody
//    public String testService() {
//        try {
//            List<ExternalStationDAO> stations = externalTrainAPIService.searchStations("Berlin");
//            return "Success! Found " + stations.size() + " stations";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @PostMapping("/external-api/search-stations-mock")
//    public String searchStationsMock(@RequestParam("query") String query, Model model) {
//        List<ExternalStationDAO> stations = new ArrayList<>();
//
//        ExternalStationDAO berlin = new ExternalStationDAO();
//        berlin.setId("8011160");
//        berlin.setName("Berlin Hauptbahnhof");
//        berlin.setLatitude(52.525589);
//        berlin.setLongitude(13.369548);
//        stations.add(berlin);
//
//        model.addAttribute("stations", stations);
//        model.addAttribute("searchQuery", query);
//        return "externalAPI";
//    }
}