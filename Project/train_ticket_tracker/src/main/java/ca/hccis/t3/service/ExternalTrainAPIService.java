package ca.hccis.t3.service;

import ca.hccis.t3.dao.ExternalStationDAO;
import ca.hccis.t3.dao.ExternalTripDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for integrating with external Deutsche Bahn (DB) Train API.
 * Provides real-time train schedules, station information, and journey planning.
 *
 * API Documentation: https://v6.db.transport.rest/api.html
 *
 * @author Your Name
 * @since 20251115
 */
@Service
public class ExternalTrainAPIService {

    private static final String BASE_URL = "https://v6.db.transport.rest";
    private static final Logger logger = LoggerFactory.getLogger(ExternalTrainAPIService.class);
    private final Gson gson;

    public ExternalTrainAPIService() {
        this.gson = new Gson();
    }

    /**
     * Search for train stations by name.
     *
     * Example: searchStations("Berlin") returns all Berlin stations
     *
     * @param query Station name or partial name
     * @return List of matching stations
     * @throws Exception if API call fails
     */
    public List<ExternalStationDAO> searchStations(String query) throws Exception {
        logger.info("Searching stations for query: {}", query);

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        String endpoint = BASE_URL + "/locations?query=" + encodedQuery + "&results=10";

        String jsonResponse = makeGetRequest(endpoint);
        JsonArray stationsArray = gson.fromJson(jsonResponse, JsonArray.class);

        List<ExternalStationDAO> stations = new ArrayList<>();
        for (JsonElement element : stationsArray) {
            JsonObject station = element.getAsJsonObject();

            // Only include stations (not addresses or POIs)
            if (station.has("type") && "station".equals(station.get("type").getAsString())) {
                ExternalStationDAO dao = new ExternalStationDAO();
                dao.setId(station.get("id").getAsString());
                dao.setName(station.get("name").getAsString());

                // Get location if available
                if (station.has("location")) {
                    JsonObject location = station.getAsJsonObject("location");
                    if (location.has("latitude")) {
                        dao.setLatitude(location.get("latitude").getAsDouble());
                    }
                    if (location.has("longitude")) {
                        dao.setLongitude(location.get("longitude").getAsDouble());
                    }
                }

                stations.add(dao);
            }
        }

        logger.info("Found {} stations", stations.size());
        return stations;
    }

    /**
     * Find train journeys between two stations.
     *
     * Example: findJourneys("8011160", "8000105", "2025-11-14T14:00")
     * Finds trains from Berlin to Frankfurt on Nov 14 at 2pm
     *
     * @param fromStationId Origin station ID (get from searchStations)
     * @param toStationId Destination station ID
     * @param departureTime Departure time in ISO format (yyyy-MM-ddTHH:mm)
     * @return List of available journeys
     * @throws Exception if API call fails
     */
    public List<ExternalTripDAO> findJourneys(String fromStationId, String toStationId, String departureTime)
            throws Exception {

        logger.info("Finding journeys from {} to {} at {}", fromStationId, toStationId, departureTime);

        String encodedFrom = URLEncoder.encode(fromStationId, StandardCharsets.UTF_8.toString());
        String encodedTo = URLEncoder.encode(toStationId, StandardCharsets.UTF_8.toString());
        String encodedTime = URLEncoder.encode(departureTime, StandardCharsets.UTF_8.toString());

        String endpoint = BASE_URL + "/journeys?from=" + encodedFrom +
                "&to=" + encodedTo +
                "&departure=" + encodedTime +
                "&results=5";

        String jsonResponse = makeGetRequest(endpoint);
        JsonObject response = gson.fromJson(jsonResponse, JsonObject.class);

        List<ExternalTripDAO> trips = new ArrayList<>();

        if (response.has("journeys")) {
            JsonArray journeys = response.getAsJsonArray("journeys");

            for (JsonElement element : journeys) {
                JsonObject journey = element.getAsJsonObject();
                ExternalTripDAO trip = new ExternalTripDAO();

                // Get departure info
                if (journey.has("legs") && journey.getAsJsonArray("legs").size() > 0) {
                    JsonObject firstLeg = journey.getAsJsonArray("legs").get(0).getAsJsonObject();
                    JsonObject lastLeg = journey.getAsJsonArray("legs")
                            .get(journey.getAsJsonArray("legs").size() - 1).getAsJsonObject();

                    // Origin
                    if (firstLeg.has("origin")) {
                        JsonObject origin = firstLeg.getAsJsonObject("origin");
                        trip.setOrigin(origin.get("name").getAsString());
                        if (origin.has("departure")) {
                            trip.setDepartureTime(origin.get("departure").getAsString());
                        }
                    }

                    // Destination
                    if (lastLeg.has("destination")) {
                        JsonObject destination = lastLeg.getAsJsonObject("destination");
                        trip.setDestination(destination.get("name").getAsString());
                        if (destination.has("arrival")) {
                            trip.setArrivalTime(destination.get("arrival").getAsString());
                        }
                    }

                    // Train line/number
                    if (firstLeg.has("line") && firstLeg.getAsJsonObject("line").has("name")) {
                        trip.setTrainLine(firstLeg.getAsJsonObject("line").get("name").getAsString());
                    }

                    // Duration
                    if (journey.has("legs")) {
                        trip.setNumberOfLegs(journey.getAsJsonArray("legs").size());
                    }
                }

                // Price (if available - DB API doesn't always include prices)
                if (journey.has("price")) {
                    JsonObject price = journey.getAsJsonObject("price");
                    if (price.has("amount")) {
                        trip.setPrice(price.get("amount").getAsDouble());
                    }
                }

                trips.add(trip);
            }
        }

        logger.info("Found {} journeys", trips.size());
        return trips;
    }

    /**
     * Get real-time departures from a specific station.
     *
     * @param stationId Station ID
     * @param duration How many minutes ahead to check (default 120)
     * @return List of upcoming departures
     * @throws Exception if API call fails
     */
    public List<ExternalTripDAO> getDepartures(String stationId, int duration) throws Exception {
        logger.info("Getting departures for station {} with duration {}", stationId, duration);

        String endpoint = BASE_URL + "/stops/" + stationId + "/departures?duration=" + duration;

        String jsonResponse = makeGetRequest(endpoint);
        JsonObject response = gson.fromJson(jsonResponse, JsonObject.class);

        List<ExternalTripDAO> departures = new ArrayList<>();

        if (response.has("departures")) {
            JsonArray departuresArray = response.getAsJsonArray("departures");

            for (JsonElement element : departuresArray) {
                JsonObject departure = element.getAsJsonObject();
                ExternalTripDAO trip = new ExternalTripDAO();

                // Station name
                if (departure.has("stop")) {
                    JsonObject stop = departure.getAsJsonObject("stop");
                    if (stop.has("name")) {
                        trip.setOrigin(stop.get("name").getAsString());
                    }
                }

                // Departure time
                if (departure.has("when")) {
                    trip.setDepartureTime(departure.get("when").getAsString());
                }

                // Train line
                if (departure.has("line") && departure.getAsJsonObject("line").has("name")) {
                    trip.setTrainLine(departure.getAsJsonObject("line").get("name").getAsString());
                }

                // Direction/Destination
                if (departure.has("direction")) {
                    trip.setDestination(departure.get("direction").getAsString());
                }

                // Platform
                if (departure.has("platform")) {
                    trip.setPlatform(departure.get("platform").getAsString());
                }

                // Delay (if any)
                if (departure.has("delay")) {
                    trip.setDelayMinutes(departure.get("delay").getAsInt());
                }

                departures.add(trip);
            }
        }

        logger.info("Found {} departures", departures.size());
        return departures;
    }

    /**
     * Get detailed information about a specific station.
     *
     * @param stationId Station ID
     * @return Station details
     * @throws Exception if API call fails
     */
    public ExternalStationDAO getStationDetails(String stationId) throws Exception {
        logger.info("Getting details for station {}", stationId);

        String endpoint = BASE_URL + "/stops/" + stationId;

        String jsonResponse = makeGetRequest(endpoint);
        JsonObject station = gson.fromJson(jsonResponse, JsonObject.class);

        ExternalStationDAO dao = new ExternalStationDAO();

        if (station.has("id")) {
            dao.setId(station.get("id").getAsString());
        }
        if (station.has("name")) {
            dao.setName(station.get("name").getAsString());
        }
        if (station.has("location")) {
            JsonObject location = station.getAsJsonObject("location");
            if (location.has("latitude")) {
                dao.setLatitude(location.get("latitude").getAsDouble());
            }
            if (location.has("longitude")) {
                dao.setLongitude(location.get("longitude").getAsDouble());
            }
        }

        return dao;
    }

    /**
     * Helper method to make GET requests to the API.
     *
     * @param urlString The complete URL to call
     * @return JSON response as string
     * @throws Exception if request fails
     */
    private String makeGetRequest(String urlString) throws Exception {
        logger.debug("Making GET request to: {}", urlString);

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            logger.error("API request failed with code: {}", responseCode);
            throw new Exception("API request failed with code: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        logger.debug("API request successful");
        return response.toString();
    }
}