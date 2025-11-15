package ca.hccis.t3.rest;

import ca.hccis.t3.dao.ExternalStationDAO;
import ca.hccis.t3.dao.ExternalTripDAO;
import ca.hccis.t3.service.ExternalTrainAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * REST endpoints for accessing external Deutsche Bahn Train API.
 * Provides real-time train data integration.
 *
 * @author Your Name
 * @since 20251115
 */
@Path("/ExternalTrainAPIService/v1")
public class ExternalTrainAPIRestService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalTrainAPIRestService.class);
    private final ExternalTrainAPIService externalTrainAPIService;

    @Autowired
    public ExternalTrainAPIRestService(ExternalTrainAPIService externalTrainAPIService) {
        this.externalTrainAPIService = externalTrainAPIService;
    }

    /**
     * Search for train stations by name.
     *
     * Example: GET /api/ExternalTrainAPIService/v1/stations/search?q=Berlin
     *
     * @param query Station name or partial name
     * @return List of matching stations
     */
    @GET
    @Path("/stations/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchStations(@QueryParam("q") String query) {
        try {
            if (query == null || query.trim().isEmpty()) {
                logger.warn("Search stations called with empty query");
                return Response
                        .status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity("{\"error\": \"Query parameter 'q' is required\"}")
                        .build();
            }

            List<ExternalStationDAO> stations = externalTrainAPIService.searchStations(query);

            if (stations.isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_NO_CONTENT)
                        .build();
            }

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(stations)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            logger.error("Error searching stations: {}", e.getMessage(), e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Get detailed information about a specific station.
     *
     * Example: GET /api/ExternalTrainAPIService/v1/stations/8011160
     *
     * @param stationId Station ID
     * @return Station details
     */
    @GET
    @Path("/stations/{stationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStationDetails(@PathParam("stationId") String stationId) {
        try {
            ExternalStationDAO station = externalTrainAPIService.getStationDetails(stationId);

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(station)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            logger.error("Error getting station details: {}", e.getMessage(), e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Find train journeys between two stations.
     *
     * Example: GET /api/ExternalTrainAPIService/v1/journeys?from=8011160&to=8000105&departure=2025-11-14T14:00
     *
     * @param fromStationId Origin station ID
     * @param toStationId Destination station ID
     * @param departureTime Departure time (ISO format: yyyy-MM-ddTHH:mm)
     * @return List of available journeys
     */
    @GET
    @Path("/journeys")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJourneys(
            @QueryParam("from") String fromStationId,
            @QueryParam("to") String toStationId,
            @QueryParam("departure") String departureTime) {

        try {
            // Validate parameters
            if (fromStationId == null || fromStationId.trim().isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity("{\"error\": \"Parameter 'from' (origin station ID) is required\"}")
                        .build();
            }
            if (toStationId == null || toStationId.trim().isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity("{\"error\": \"Parameter 'to' (destination station ID) is required\"}")
                        .build();
            }
            if (departureTime == null || departureTime.trim().isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity("{\"error\": \"Parameter 'departure' (ISO format: yyyy-MM-ddTHH:mm) is required\"}")
                        .build();
            }

            List<ExternalTripDAO> journeys = externalTrainAPIService.findJourneys(
                    fromStationId, toStationId, departureTime);

            if (journeys.isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_NO_CONTENT)
                        .build();
            }

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(journeys)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            logger.error("Error finding journeys: {}", e.getMessage(), e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Get real-time departures from a station.
     *
     * Example: GET /api/ExternalTrainAPIService/v1/departures/8011160?duration=120
     *
     * @param stationId Station ID
     * @param duration Minutes ahead to check (default 120)
     * @return List of upcoming departures
     */
    @GET
    @Path("/departures/{stationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartures(
            @PathParam("stationId") String stationId,
            @DefaultValue("120") @QueryParam("duration") int duration) {

        try {
            List<ExternalTripDAO> departures = externalTrainAPIService.getDepartures(stationId, duration);

            if (departures.isEmpty()) {
                return Response
                        .status(HttpURLConnection.HTTP_NO_CONTENT)
                        .build();
            }

            return Response
                    .status(HttpURLConnection.HTTP_OK)
                    .entity(departures)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            logger.error("Error getting departures: {}", e.getMessage(), e);
            return Response
                    .status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}