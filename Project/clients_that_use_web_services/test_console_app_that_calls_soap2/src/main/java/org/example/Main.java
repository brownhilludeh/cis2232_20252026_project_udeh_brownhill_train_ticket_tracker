package org.example;

import org.example.soapticket.TrainTicket;
import org.example.soapticket.TrainTicketService;
import org.example.soapticket.TrainTicketServiceImplService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, I'm calling the soap service!");
//        BusPassServiceImplService busPassServiceImplService = new BusPassServiceImplService();
//        BusPassService busPassService = busPassServiceImplService.getBusPassServiceImplPort();
//        System.out.println("count="+busPassService.getCount());
//
//        List<BusPass> theList = busPassService.getBusPasses();
//        for(BusPass current: theList){
//            System.out.println(current);
//        }

               TrainTicketServiceImplService trainTicketServiceImplService = new TrainTicketServiceImplService();
        TrainTicketService trainTicketService = trainTicketServiceImplService.getSkillsAssessmentSquashTechnicalServiceImplPort();

        List<TrainTicket> theList = trainTicketService.getAssessments();
        for(TrainTicket current: theList){
            System.out.println(current);
        }


    }
}