package ca.hccis.t3.controllers;

import ca.hccis.t3.entity.StationOption;
import ca.hccis.t3.entity.TrainAPI;
import ca.hccis.t3.util.CisUtilityNetwork;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/train-api")
public class TrainAPIController {

    private static final Logger logger = LoggerFactory.getLogger(TrainAPIController.class);

    private static final String APP_ID = "f22773a6";
    private static final String APP_KEY = "267376bda73ac440e0d8d7973e93c180";

    /**
     * Returns the list of available station options for the dropdown
     */
    private List<StationOption> getStationOptions() {
        List<StationOption> stations = new ArrayList<>();

        stations.add(new StationOption("HBD", "Hebden Bridge"));
        stations.add(new StationOption("BTN", "Brighton"));
        stations.add(new StationOption("WAT", "London Waterloo"));
        stations.add(new StationOption("EUS", "London Euston"));
        stations.add(new StationOption("KGX", "London Kings Cross"));
        stations.add(new StationOption("PAD", "London Paddington"));
        stations.add(new StationOption("VIC", "London Victoria"));
        stations.add(new StationOption("CST", "London Cannon Street"));
        stations.add(new StationOption("CHX", "London Charing Cross"));
        stations.add(new StationOption("LBG", "London Bridge"));
        stations.add(new StationOption("MAN", "Manchester Piccadilly"));
        stations.add(new StationOption("EDB", "Edinburgh Waverley"));
        stations.add(new StationOption("GLC", "Glasgow Central"));
        stations.add(new StationOption("BHM", "Birmingham New Street"));

        return stations;
    }

    @GetMapping("/timetable")
    public String getStationTimetable(
            @RequestParam(name = "station", defaultValue = "HBD") String stationCode,
            Model model,
            HttpSession session) {

        // CRITICAL FIX: Always add station options to model for dropdown
        model.addAttribute("stationOptions", getStationOptions());
        model.addAttribute("stationCode", stationCode);

        // Set default values in case of error
        model.addAttribute("stationName", stationCode);
        model.addAttribute("date", "N/A");
        model.addAttribute("timeOfDay", "N/A");

        try {
            String url = String.format(
                    "https://transportapi.com/v3/uk/train/station_timetables/%s.json?app_id=%s&app_key=%s&train_status=passenger",
                    stationCode, APP_ID, APP_KEY
            );

            logger.info("Fetching timetable for station: {}", stationCode);

            String result = CisUtilityNetwork.connectToApi(url);

            // Check if result is null or empty
            if (result == null || result.trim().isEmpty()) {
                logger.warn("Null or empty response from TransportAPI for station {}", stationCode);
                model.addAttribute("apiError", "No data received from the train API.");
                model.addAttribute("departuresList", new ArrayList<>());
                return "train-api/timetable";
            }

            logger.debug("TransportAPI response for station {}: {}",
                    stationCode,
                    result.substring(0, Math.min(200, result.length())));

            // Parse JSON response
            JSONObject root = new JSONObject(result);

            // Extract station information
            String stationName = root.optString("station_name", stationCode);
            String date = root.optString("date", "N/A");
            String timeOfDay = root.optString("time_of_day", "N/A");

            model.addAttribute("stationName", stationName);
            model.addAttribute("date", date);
            model.addAttribute("timeOfDay", timeOfDay);

            // Extract departures
            List<TrainAPI> departuresList = new ArrayList<>();

            if (root.has("departures")) {
                JSONObject departuresObj = root.getJSONObject("departures");

                if (departuresObj.has("all")) {
                    JSONArray all = departuresObj.getJSONArray("all");

                    for (int i = 0; i < all.length(); i++) {
                        JSONObject dep = all.getJSONObject(i);

                        TrainAPI td = new TrainAPI();
                        td.setAimedDepartureTime(dep.optString("aimed_departure_time", "N/A"));
                        td.setOriginName(dep.optString("origin_name", "N/A"));
                        td.setDestinationName(dep.optString("destination_name", "N/A"));
                        td.setPlatform(dep.optString("platform", "TBA"));
                        td.setTrainUid(dep.optString("train_uid", "N/A"));

                        departuresList.add(td);
                    }
                }
            }

            model.addAttribute("departuresList", departuresList);

            // ============================================================
            // CONSOLE OUTPUT - Display all attributes (Required for grading)
            // ============================================================
            System.out.println("\n========================================");
            System.out.println("TRAIN TIMETABLE - WEB SERVICE RESULTS");
            System.out.println("========================================");
            System.out.println("Station Code: " + stationCode);
            System.out.println("Station Name: " + stationName);
            System.out.println("Date: " + date);
            System.out.println("Time of Day: " + timeOfDay);
            System.out.println("Total Departures: " + departuresList.size());
            System.out.println("----------------------------------------");

            if (departuresList.isEmpty()) {
                System.out.println("No departures found for this station.");
                logger.info("No departures found for station {}", stationCode);
            } else {
                System.out.println("\nDEPARTURES:");
                System.out.println("----------------------------------------");

                for (int i = 0; i < departuresList.size(); i++) {
                    TrainAPI train = departuresList.get(i);
                    System.out.println("\nTrain #" + (i + 1) + ":");
                    System.out.println("  Departure Time: " + train.getAimedDepartureTime());
                    System.out.println("  Origin: " + train.getOriginName());
                    System.out.println("  Destination: " + train.getDestinationName());
                    System.out.println("  Platform: " + train.getPlatform());
                    System.out.println("  Train UID: " + train.getTrainUid());
                }

                logger.info("Found {} departures for station {}", departuresList.size(), stationCode);
            }

            System.out.println("========================================\n");

        } catch (JSONException je) {
            logger.error("JSON parsing error for station {}: {}", stationCode, je.getMessage(), je);
            model.addAttribute("departuresList", new ArrayList<>());
            model.addAttribute("apiError", "Error parsing timetable data. The API response may be invalid.");
        } catch (Exception ex) {
            logger.error("Error fetching timetable for station {}", stationCode, ex);
            model.addAttribute("departuresList", new ArrayList<>());
            model.addAttribute("apiError", "Error loading timetable data: " + ex.getMessage());
        }

        return "train-api/timetable";
    }
}