package tests;

import stu.Route;
import stu.Transportation;


/**
 * DO NOT MODIFY
 *
 * Tester for Heading Home! CS2 Project.
 *
 *
 * Tests the Phase 0 classes
 *     -stu.stu.Route
 *     -stu.stu.Transportation
 *
 * @author scj (scj@cs.rit.edu)
 */

public class TestTransportation {

    /**
     * Tests the stu.stu.Route class
     *
     * Will output a message for any tests failed.
     *
     * Will output "All tests passed" if everything passes.
     */
    public static void testRoute(){
        System.out.println("Testing stu.stu.Route Class");
        boolean failed = false;

        Route r1 = new Route(1, "Syr", "Roc", 200);

        if(r1.getId() != 1){
            System.out.println("\tgetId() failed.");
            System.out.println("\t\tExpected: 1");
            System.out.println("\t\tGot: " + r1.getId());
            failed = true;
        }

        if(!r1.getStart().equals("Syr")){
            System.out.println("\tgetStart() failed.");
            System.out.println("\t\tExpected: Syr");
            System.out.println("\t\tGot: " + r1.getStart());
            failed = true;
        }

        if(!r1.getEnd().equals("Roc")){
            System.out.println("\tgetEnd() failed.");
            System.out.println("\t\tExpected: Syr");
            System.out.println("\t\tGot: " + r1.getEnd());
            failed = true;
        }

        if(r1.getDistance() != 200){
            System.out.println("\tgetDistance() failed.");
            System.out.println("\t\tExpected: 200");
            System.out.println("\t\tGot: " + r1.getDistance());
            failed = true;
        }

        if(!r1.toString().equals("1: Syr to Roc, 200 distance")){
            System.out.println("\ttoString() failed.");
            System.out.println("\t\tExpected: 1: Syr to Roc, 200 distance");
            System.out.println("\t\tGot: " + r1.toString());
            failed = true;
        }

        if(!failed){
            System.out.println("\tAll tests passed.");
        }
    }

    /**
     * Tests the stu.stu.Transportation class
     *
     * Will output a message for any tests failed.
     *
     * Will output "All tests passed" if everything passes.
     */

    public static void testTransportation(){
        System.out.println("Testing stu.stu.Transportation Class");
        boolean failed = false;

        Route r1 = new Route(1, "Syr", "Roc", 200);
        Transportation t1 =
                new Transportation(1, "Bus", 100, 200, r1);

        if(t1.getId() != 1){
            System.out.println("\tgetId() failed.");
            System.out.println("\t\tExpected: 1");
            System.out.println("\t\tGot: " + t1.getId());
            failed = true;
        }

        if(!t1.getType().equals("Bus")){
            System.out.println("\tgetType() failed.");
            System.out.println("\t\tExpected: Bus");
            System.out.println("\t\tGot: " + t1.getType());
            failed = true;
        }

        if(t1.getCost() != 100){
            System.out.println("\tgetCost() failed.");
            System.out.println("\t\tExpected: 100");
            System.out.println("\t\tGot: " + t1.getCost());
            failed = true;
        }

        if(t1.getTime() != 200){
            System.out.println("\tgetTime() failed.");
            System.out.println("\t\tExpected: 200");
            System.out.println("\t\tGot: " + t1.getTime());
            failed = true;
        }

        if(t1.getRoute().getId() != 1){
            System.out.println("\tgetRoute() failed.");
            System.out.println("\t\tExpected: 1");
            System.out.println("\t\tGot: " + t1.getRoute().getId());
            failed = true;
        }

        if(!t1.toString().equals("1: Bus, cost 100, route 1, time 200")){
            System.out.println("\ttoString() failed.");
            System.out.println("\t\t1: Bus, cost 100, route 1, time 200");
            System.out.println("\t\tGot: " + t1.toString());
            failed = true;
        }

        if(!failed){
            System.out.println("\tAll tests passed.");
        }
    }

    /**
     * Main to start the tests.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        testRoute();

        testTransportation();
    }
}
