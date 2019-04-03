package stu;

import backtracking.Configuration;

import java.util.*;

/**
 * @author Chase Lewis
 */
public class Config implements Configuration {

    /**
     * keeps track of the current cost
     */
    private int currCost;

    /**
     * holds the maxcost
     */
    private int MaxCost;

    /**
     * current city
     */
    private String currCity;

    /**
     * city to start at
     */
    private String startCity;

    /**
     * city to end at
     */
    private String endCity;


    /**
     * map of transportations
     */
    private Map<Integer, Transportation> map = new HashMap<>();

    /**
     * predecesssors maps
     */
    private HashMap<Integer,Transportation> p;


    /**
     * constructor to take it parameters and intitialize them
     * @param m an instance of the map created in Phase 1.
     * @param startCity  the name of the starting location.
     * @param endCity the name of the ending location.
     * @param MaxCost the maximum cost of the trip.
     */
    public Config(Map<Integer, Transportation> m, String startCity, String endCity, int MaxCost){
        this.currCost = 0;
        this.MaxCost = MaxCost;
        this.currCity = startCity;
        this.startCity = startCity;
        this.endCity = endCity;
        for (Transportation t: m.values()){
            this.map.put(t.getId(), t);
        }
    }

    /**
     * copy constructor
     * @param config the original config constructor
     */
    public Config(Config config){
        this.currCost = config.currCost;
        this.MaxCost = config.MaxCost;
        this.currCity = config.currCity;
        this.startCity = config.startCity;
        this.endCity = config.endCity;
        for (Transportation t: config.map.values()){
            this.map.put(t.getId(), t);
        }
    }

    /**
     * Get the collection of successors from the current one.
     *
     * @return All successors, valid and invalid
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        Collection<Configuration> help = new LinkedList<>();

        for (Transportation tran : this.map.values()){
            if (tran.getRoute().getStart().equals(this.currCity) && !(this.map.containsKey(tran.getId()))){
                Config c = new Config(this);
                help.add(c);
                currCost = currCost + tran.getCost();
            }

        }
        return help;
    }

    /**
     * Is the current configuration valid or not?
     *
     * @return true if valid; false otherwise
     */
    @Override
    public boolean isValid() {
        return currCost <= MaxCost;
    }

    /**
     * Is the current configuration a goal?
     * @return true if goal; false otherwise
     */
    @Override
    public boolean isGoal() {
        for (Transportation tran : this.map.values()){
            if (tran.getRoute().getEnd().equals(endCity) && isValid()){
                return true;

            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "hey Scott";
    }
}
