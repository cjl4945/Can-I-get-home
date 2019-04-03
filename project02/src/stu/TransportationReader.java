package stu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Chase Lewis
 */
public class TransportationReader {

    /**
     * A static function called readTransportations(String fName). It will read in the
     * Transportations from the file with the provided name and return them as a map.
     *  The integer is the id of the Transportation
     *
     * @param fName
     * @return
     */
    public static Map<Integer, Transportation> readTransportations(String fName){
        Map<Integer, Transportation> trans = new TreeMap<>();
        Map<Integer, Route> routes = new TreeMap<>();
        try  {
            FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (!(line = br.readLine()).equals("Transportations")){

                String grid[] = line.split(", ");

                if (grid.length > 1) {
                    Route temp = new Route(Integer.parseInt(grid[0]), grid[1], grid[2], Integer.parseInt(grid[3]));
                    routes.put(Integer.parseInt(grid[0]), temp);
                }
                }
            while ((line = br.readLine()) != null){
                String grid[] = line.split(", ");


                if (grid.length > 1) {
                    Transportation temp = new Transportation(Integer.parseInt(grid[0]), grid[1], Integer.parseInt(grid[2]),
                            Integer.parseInt(grid[4]), routes.get(Integer.parseInt(grid[3])));
                    trans.put(Integer.parseInt(grid[0]), temp);
                }
            }
        } catch (IOException e) {
            Map<Integer, Transportation> empty = new TreeMap<>();
            return empty;
        }

        return trans;
    }

    public static void main(String[] args) {
        String fname = args[0];
        readTransportations(fname);
    }
}
