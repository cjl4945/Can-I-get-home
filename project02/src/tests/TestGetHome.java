package tests; /**
 * DO NOT MODIFY
 *
 * Tester for Heading Home! CS2 Project.
 *
 *
 * Tests the Phase 2 functions
 *
 * This class assumes a working stu.TransportationReader
 *
 * @author scj (scj@cs.rit.edu)
 */

import backtracking.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import stu.Transportation;
import stu.TransportationReader;
import stu.TravelAgency;

public class TestGetHome {

    /**
     * Tests the isThereAPath function
     *
     * Uses the testLarger.txt file
     */
    public static void testIsPath() {
        System.out.println("Testing isThereAPath");
        boolean failed = false;

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        if (!TravelAgency.isThereAPath(m, "Roc", "NYC")) {
            System.out.println("\tisThereAPath Roc -> NYC failed");
            System.out.println("\t\tExpected: true");
            System.out.println("\t\tGot: false");
            failed = true;
        }

        if (TravelAgency.isThereAPath(m, "LA", "Roc")) {
            System.out.println("\tisThereAPath LA -> Roc failed");
            System.out.println("\t\tExpected: false");
            System.out.println("\t\tGot: true");
            failed = true;
        }

        if (!TravelAgency.isThereAPath(m, "RIT", "Sea")) {
            System.out.println("\tisThereAPath RIT -> Sea failed");
            System.out.println("\t\tExpected: true");
            System.out.println("\t\tGot: false");
            failed = true;
        }


        if(!failed){
            System.out.println("\tAll tests passed");
        }

    }

    /**
     * Tests the leastStops function
     *
     * Uses the testLarger.txt file
     */
    public static void testLeastStops(){
        System.out.println("Testing leastStops");
        boolean failed = false;

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        int stops = TravelAgency.leastStops(m, "RIT", "NYC");
        if ( stops != 1) {
            System.out.println("\tleastStops RIT -> NYC failed");
            System.out.println("\t\tExpected: 1");
            System.out.println("\t\tGot: " + stops);
            failed = true;
        }

        stops = TravelAgency.leastStops(m, "LA", "Roc");
        if (stops != -1) {
            System.out.println("\tisThereAPath LA -> Roc failed");
            System.out.println("\t\tExpected: -1");
            System.out.println("\t\tGot: " + stops);
            failed = true;
        }

        stops = TravelAgency.leastStops(m, "RIT", "Sea");
        if (stops != 3) {
            System.out.println("\tisThereAPath RIT -> Sea failed");
            System.out.println("\t\tExpected: 3");
            System.out.println("\t\tGot: " + stops);
            failed = true;
        }


        if(!failed){
            System.out.println("\tAll tests passed");
        }
    }

    /**
     * Tests the canMakeItHomeCost function
     *
     * Uses the testLarger.txt file
     */
    public static void testCanMakeHomeCost(){

        System.out.println("Testing canMakeItHomeCost");
        boolean failed = false;

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        Optional<Configuration> opt =
                TravelAgency.canMakeItHomeCost(m, "RIT", "NYC",10);
        if ( opt.isPresent()) {
            System.out.println("\tleastStops RIT -> NYC for 10 failed");
            System.out.println("\t\tExpected: False");
            System.out.println("\t\tGot: True");
            failed = true;
        }

        opt = TravelAgency.canMakeItHomeCost(m, "RIT", "NYC",1000);
        if ( !opt.isPresent()) {
            System.out.println("\tleastStops RIT -> NYC for 1000 failed");
            System.out.println("\t\tExpected: True");
            System.out.println("\t\tGot: False");
            failed = true;
        }

        opt = TravelAgency.canMakeItHomeCost(m, "Sea", "RIT",10000000);
        if ( opt.isPresent()) {
            System.out.println("\tleastStops Sea -> RIT for 10000000 failed");
            System.out.println("\t\tExpected: False");
            System.out.println("\t\tGot: True");
            failed = true;
        }

        opt = TravelAgency.canMakeItHomeCost(m, "RIT", "Mia",1000);
        if ( !opt.isPresent()) {
            System.out.println("\tleastStops RIT -> Mia for 1000 failed");
            System.out.println("\t\tExpected: True");
            System.out.println("\t\tGot: False");
            failed = true;
        }

        if(!failed){
            System.out.println("\tAll tests passed");
        }

    }

    /**
     * Tests the shortestPath function
     *
     * Uses the testLarger.txt file
     *
     * Does not verify output. Must be done manually
     */
    public static void testShortestPath(){
        System.out.println("Testing shortestPath");

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        List<Transportation> l = TravelAgency.shortestPath(m, "RIT", "Sea");

        for(Transportation t : l){
            System.out.println(t);
            System.out.println("\tstu.stu.Route: " + t.getRoute());
        }
    }

    /**
     * Tests the cheapestPath function
     *
     * Uses the testLarger.txt file
     *
     * Does not verify output. Must be done manually
     */
    public static void testCheapestPath(){
        System.out.println("Testing cheapestPath");

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        List<Transportation> l = TravelAgency.cheapestPath(m, "RIT", "Sea");

        for(Transportation t : l){
            System.out.println(t);
            System.out.println("\tstu.stu.Route: " + t.getRoute());
        }
    }

    /**
     * Tests the quickestPath function
     *
     * Uses the testLarger.txt file
     *
     * Does not verify output. Must be done manually
     */
    public static void testQuickestPath(){
        System.out.println("Testing quickestPath");

        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(
                        "testLarger.txt");

        List<Transportation> l = TravelAgency.quickestPath(m, "RIT", "Sea");

        for(Transportation t : l){
            System.out.println(t);
            System.out.println("\tstu.stu.Route: " + t.getRoute());
        }
    }

    /**
     * Main that runs the tests.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        testIsPath();
        System.out.println();

        testLeastStops();
        System.out.println();

        testCanMakeHomeCost();
        System.out.println();

        testShortestPath();
        System.out.println();

        testCheapestPath();
        System.out.println();

        testQuickestPath();
    }
}
