package info.hccis.main;

import info.hccis.student.util.CisUtility;
import info.hccis.student.util.UtilityRest;

public class Controller {

    public static void main(String[] args) {

        // Ask the user for endpoint
        String endpoint = CisUtility.getInputString(
                "Enter the endpoint URL (blank = TrainTicket POST)");
        if (endpoint == null || endpoint.isEmpty()) {
            endpoint = "http://localhost:8080/ticketService/tickets";
        }

        // Ask for the JSON parameter body
        String parameter = CisUtility.getInputString(
                "Enter JSON body (blank = sample TrainTicket)");
        if (parameter == null || parameter.isEmpty()) {
            parameter = "{\n" +
                    "  \"name\": \"Brownhill Udeh\",\n" +
                    "  \"issueDate\": \"2025-11-27\",\n" +
                    "  \"station\": \"Charlottetown\",\n" +
                    "  \"departureTime\": \"12:30\",\n" +
                    "  \"destination\": \"Summerside\",\n" +
                    "  \"travelLength\": 45,\n" +
                    "  \"isStudent\": false,\n" +
                    "  \"isFrequent\": true\n" +
                    "}";
        }

        // Send POST request
        Object responseObject = UtilityRest.postUsingRest(endpoint, parameter);
        String result = String.valueOf(responseObject);   // <- no cast error

        System.out.println("\n=== SERVER RESPONSE ===");
        System.out.println(result);
    }
}
