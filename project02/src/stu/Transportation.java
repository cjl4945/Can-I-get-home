package stu;

/**
 * This class, stu.Transportation, will represent the various forms of transportation in the
 * system
 *
 * @author Chase Lewis
 */
public class Transportation {
    /**
     * this will be use to uniquely identify an item of this class.
     */
    private int id;

    /**
     *  this will be used to store the type. Examples of type are ”rental
     *  car”, ”boat”, etc.
     */
    private String type;

    /**
     *  this will be the cost to use this transportation
     */
    private int cost;

    /**
     *  the time it takes to travel from the start to end.
     */
    private int time;

    /**
     *  the stu.Route object this class uses.
     */
    private Route route;

    /**
     * constructor that takes in the private states
     *
     * @param id unique id for the item
     * @param type type of of transportation
     * @param cost cost of this transportation
     * @param time  time spent traveling by this transportation
     * @param route route object of this transporation
     */
    public Transportation(int id, String type, int cost, int time, Route route) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.time = time;
        this.route = route;
    }

    /**
     *  get id of the transporation
     * @return integer of the id
     */
    public int getId() {
        return id;
    }

    /**
     * get type of transportation
     * @return String of the type of transportation
     */
    public String getType() {
        return type;
    }

    /**
     * get cost of transporation
     * @return integer of cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * get time to travel by this type of transportation
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     * get the route  of this transporation
     * @return the route object
     */
    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return this.getId() + ": " + this.getType() + ", " + "cost " + this.getCost() + ", route " + this.route.getId()
                + ", time " + this.getTime();
    }
}
