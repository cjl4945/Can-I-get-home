package tests;

/**
 * DO NOT MODIFY
 *
 * Tester for Heading Home! CS2 Project.
 *
 *
 * Tests the Phase 1 stu.TransportationReader
 *
 * @author scj (scj@cs.rit.edu)
 */

import stu.Transportation;
import stu.TransportationReader;

import java.util.Map;

public class TestReader {

    /**
     * Tests the map for a particular entry.
     *
     * Assumes if entry is there and toString
     * matches the test has been passed
     * @param m the map to check for the entry
     * @param id the id (key) for the entry.
     * @param expected the expected toString value
     * @return true if the test passes; false otherwise
     */
    public static boolean testMapEntry(Map<Integer, Transportation> m,
                                       int id, String expected){
        if(!m.containsKey(id)){
            System.out.println("\tFailed to add stu.stu.Transportation " + id);
            return false;
        }
        else{
            String t = m.get(id).toString();
            if(!t.equals(expected)){
                System.out.println("\tFailed to correctly add stu.stu.Transportation " + id);
                System.out.println("\t\tExpected: " + expected);
                System.out.println("\t\tGot: " + t);
                return false;
            }
        }

        return true;
    }

    public static void testReader(){
        System.out.println("Testing readTransportations");
        String fName = "testReaderSample.txt";
        boolean failed = false;
        Map<Integer, Transportation> m =
                TransportationReader.readTransportations(fName);

        failed = failed ||
                !testMapEntry(m,1,"1: Bicycle, cost 25, route 1, time 1000");
        failed = failed ||
                !testMapEntry(m,2,"2: Bus, cost 100, route 3, time 200");
        failed = failed ||
                !testMapEntry(m,3,"3: Train, cost 200, route 1, time 300");

        m = TransportationReader.readTransportations("noSuchFile.txt");
        if(!m.isEmpty()){
            System.out.println("\tFailed FileNotFound Test");
            failed = true;
        }

        if(!failed){
            System.out.println("\tAll tests passed.");
        }
    }

    public static void main(String[] args) {
        testReader();
    }
}
