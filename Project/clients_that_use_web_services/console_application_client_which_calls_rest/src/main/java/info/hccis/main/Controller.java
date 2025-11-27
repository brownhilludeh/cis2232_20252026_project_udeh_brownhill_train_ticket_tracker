package info.hccis.main;

import com.google.gson.Gson;
import ca.hccis.t3.model.jpa.TrainTicket;
import ca.hccis.t3.student.util.UtilityRest;
import org.json.JSONArray;

import java.util.Scanner;

/**
 * Main Controller for TrainTicket REST Client
 * Provides menu-driven interface to interact with REST API
 *
 * @author Your Name
 * @since 2025
 */
public class Controller {

    final public static String MENU = "\nMain Menu \nA) Add\n"
            + "U) Update (FUTURE)\n"
            + "V) View\n"
            + "D) Delete\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8080/t3/api/traintickets";

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            String choice = input.nextLine();
            TrainTicket ticket;
            String url;

            switch (choice.toUpperCase()) {
                case "A":
                    ticket = create();
                    url = URL_STRING;
                    System.out.println("Url=" + url);
                    TrainTicket ticketTemp = (TrainTicket) UtilityRest.addUsingRest(url, ticket);
                    if(ticketTemp != null) {
                        System.out.println("Added new entity:" + ticketTemp.toString());
                    }
                    break;

//                case "U":
//                    // Future implementation for update
//                    System.out.println("Update functionality coming soon");
//                    break;

                case "D":
                    System.out.println("Enter id to delete");
                    Scanner deleteInput = new Scanner(System.in);
                    int id = deleteInput.nextInt();
                    deleteInput.nextLine();  //burn
                    UtilityRest.deleteUsingRest(URL_STRING, id);
                    break;

                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                    //**************************************************************
                    //Based on the json string passed back, loop through each json
                    //object which is a json string in an array of json strings.
                    //*************************************************************
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    //**************************************************************
                    //For each json object in the array, show the ticket details
                    //**************************************************************
                    System.out.println("Here are the rows");
                    Gson gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        TrainTicket current = gson.fromJson(
                                jsonArray.getJSONObject(currentIndex).toString(),
                                TrainTicket.class);
                        System.out.println(current.toString());
                    }
                    break;

                case "X":
                    endProgram = true;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Create a TrainTicket object by asking user for input.
     *
     * @return TrainTicket object
     * @author Your Name
     * @since 2025
     */
    public static TrainTicket create() {
        TrainTicket ticket = new TrainTicket();
        ticket.getInformation();
        return ticket;
    }
}