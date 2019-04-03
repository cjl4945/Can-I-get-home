package stu;

/**
 * This class, stu.Route, will represent a path between two locations.
 *
 * @author Chase Lewis
 */
public class Route {

    /**
     *  this will be used to uniquely identify an item of this class.
     */
    private int id;

    /**
     *  this will contain the name of the starting location.
     */
    private String starting;

    /**
     * this will contain the name of the ending location.
     */
    private String ending;

    /**
     * this will be the distance traveled from start to end.
     */

    private int distance;

    /**
     * Constructor that will take in the id, starting position, ending position, and distance and set
        them
     *
     * @param id the unique id of the route
     * @param starting starting location
     * @param ending ending location
     * @param distance distance traveled
     */
    public Route(int id, String starting, String ending, int distance){
        this.id = id;
        this.starting = starting;
        this.ending = ending;
        this.distance = distance;
    }

    /**
     * get unique id of route
     * @return integer of the id
     */
    public int getId() {
        return id;
    }

    /**
     * get starting location of the route
     * @return String of the starting location
     */
    public String getStart() {
        return starting;
    }

    /**
     * get ending location of the route
     * @return String of the ending location
     */
    public String getEnd() {
        return ending;
    }

    /**
     * get distance traveled from start to end
     * @return intger of distance traveled
     */
    public int getDistance() {
        return distance;
    }

    /**
     * this will print out the class
     * @return a string in a specific form
     */
    @Override
    public String toString() {
        return this.getId() + ": " + this.getStart() + " to " + this.getEnd() + ", "
                + this.getDistance() + " distance";
    }
}
