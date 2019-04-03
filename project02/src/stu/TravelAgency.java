package stu;

import backtracking.Backtracker;
import backtracking.Configuration;
import dfs_bfs.*;

import java.util.*;

/**
 * @author Chase Lewis
 */
public class TravelAgency  {


    /**
     * This function will use DFS to determine if there is a path home. It will return true if a
     * path exists; false otherwise. You are welcome to use any of the lecture code to do this, or
     * come up with your own as long as it is doing a depth first search.
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return a boolean if there is a path between the two locations
     */
    public static boolean isThereAPath(Map<Integer,Transportation> m, String start, String end){
        dfs_bfs.Graph graph = routes(m);
        boolean can;
        can = dfs_bfs.GraphSearching.canReachDFS(graph,start,end);
        return can;
    }

    /**
     * helper function that makes a graph returns it with nodes and their
     * neighbors
     * @param m an instance of the map created in phase 1
     * @return a graph with nodes and their neighbors
     */
    public static dfs_bfs.Graph routes(Map<Integer,Transportation> m){
        dfs_bfs.Graph routes = new LinkedGraph();
        ArrayList<Route> routelist = new ArrayList<>();
        for (Transportation value : m.values()) {
            Route route = value.getRoute();
            if (!(routelist.contains(route))){
                routelist.add(route);
            }
        }
        for (Route r: routelist){
            if (routes.hasNode(r.getStart())) {
                if (routes.hasNode(r.getEnd())){
                    routes.addNeighbor(routes.getNode(r.getStart()), routes.getNode(r.getEnd()));
                }
                else {
                    Node temp = routes.makeNode(r.getEnd());
                    routes.addNeighbor(routes.getNode(r.getStart()), temp);
                }
            }
            else{
                Node temp =routes.makeNode(r.getStart());
                if (routes.hasNode(r.getEnd())){
                    routes.addNeighbor(temp, routes.getNode(r.getEnd()));
                }
                else {
                    Node temp1 = routes.makeNode(r.getEnd());
                    routes.addNeighbor(temp, temp1);
                }
            }
        }
        return routes;
    }

    /**
     * This function will use BFS to determine the least number of stops to get home. Time,
     * cost, and distance are not a factor in this. It there is not path it will return -1.
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return a int of the least amount of stops between the starting and ending location
     */
    public static int leastStops(Map<Integer,Transportation> m, String start, String end) {
        dfs_bfs.Graph graph = routes(m);
        Iterable<Node> path = dfs_bfs.GraphSearching.buildPathBFS(graph, start, end);
        int count = -1;
        if (path == null) {
            return -1;
        }

        else {
            Iterator<Node> iterator = path.iterator();
            while(iterator.hasNext()) {
                count ++;
                iterator.next();
            }

        }
        return count;
    }

    /**
     * This function will use Backtracking to determine if a path exists between the two locations
     * for under the maximum cost provided. You must use backtracking as discussed in lecture.
     * This will require you to write a configuration. This configuration must implement the
     * Configuration interface from lecture and have a toString.
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @param maxCost the maximum cost of the trip.
     * @return It will return the Configuration inside of the Optional if successful; otherwise it will return
     * an empty Optional.
     */
    public static Optional<Configuration> canMakeItHomeCost(Map<Integer,Transportation> m, String start,
                                                            String end, int maxCost){
        Config c = new Config(m,start,end,maxCost);
        Backtracker b = new Backtracker(false);

        return b.solve(c);
    }

    /**
     * This function will use Dijkstra’s to determine the shortest distance path between the two
     * locations. It will return a List of the Transportations in the order they are used to get
     * between the paths; or an empty List if no such path exists.
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return It will return a List of the Transportations in the order they are used to get
     * between the paths
     */
    public static List<Transportation> shortestPath(Map<Integer,Transportation> m, String start,
                                                    String end){
        List<dijkstras.Node> path = helperpath(m,start,end);
        List<Transportation> uraqt = new LinkedList<>();
        List<Transportation> trans = new LinkedList<>();
        if (path == null){
            List<Transportation> empty = new LinkedList<>();

            return empty;
        }
        int count = 1;
        for (dijkstras.Node node: path){
            ArrayList<Transportation> temp = new ArrayList<>();
            for (Transportation t : m.values()){
                Route r = t.getRoute();
                if (count >= path.size()){
                    break;
                }

                if (path.get(count-1).getNeighbors() == null){
                    System.out.println(node.toString());
                    break;
                }
                if (t.getRoute().getStart().equals(node.getName()) &&
                        t.getRoute().getEnd().equals(path.get(count).getName())){
                    temp.add(t);


                }

            }
            count++;
            int minVal = Integer.MAX_VALUE;
            Transportation minTrans = null;
            for (Transportation tran: temp){
                if (tran.getRoute().getDistance() < minVal){
                    minTrans = tran;
                    minVal = tran.getRoute().getDistance();
                }
            }
            if (minTrans == null){
                break;
            }
            trans.add(minTrans);
            }
        return trans;
    }

    /**
     * a helper function for the shortest path to return a list of dijkstras nodes and edges
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return a list of node and edges
     */
    public static List<dijkstras.Node> helperpath(Map<Integer,Transportation> m, String start,
                                                       String end) {
        Map<String, dijkstras.Node> ma = new TreeMap<>();
        Special sp = new Special(ma);
        ArrayList<Route> temp = new ArrayList<>();
        for (Transportation val : m.values()) {
            Route r = val.getRoute();
            if (!(temp.contains(r))) {
                dijkstras.Node tempor = new dijkstras.Node(r.getStart());
                if (ma.containsKey(r.getStart())) {
                    if (ma.containsKey(r.getEnd())) {
                        ma.get(r.getStart()).addNeighbor(ma.get(r.getEnd()), r.getDistance());
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        ma.get(r.getStart()).addNeighbor(tem, r.getDistance());
                        ma.put(tem.getName(), tem);
                    }
                } else {
                    dijkstras.Node t = new dijkstras.Node(r.getStart());
                    if (ma.containsKey(r.getEnd())) {
                        t.addNeighbor(ma.get(r.getEnd()), r.getDistance());
                        ma.put(t.getName(), t);
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        t.addNeighbor(tem, r.getDistance());
                        ma.put(t.getName(), t);
                        ma.put(tem.getName(), tem);
                    }
                }
            }
            temp.add(r);
        }

        return sp.ShortestPath(start, end);
    }


    /**
     * This function will use Dijkstra’s to determine the cheapest path between the two locations.
     * It will return a List of the Transportations in the order they are used to get between the
     * paths; or an empty List if no such path exists.
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return It will return a List of the Transportations in the order they are used to get
     * between the paths
     */
    public static List<Transportation> cheapestPath(Map<Integer,Transportation> m,
                                                    String start,
                                                    String end){
        List<dijkstras.Node> path = helpercheapest(m,start,end);
        List<Transportation> trans = new LinkedList<>();
        if (path == null){
            List<Transportation> empty = new LinkedList<>();

            return empty;
        }
        int count = 1;
        for (dijkstras.Node node: path){
            ArrayList<Transportation> temp = new ArrayList<>();
            for (Transportation t : m.values()){
                Route r = t.getRoute();
                if (count >= path.size()){
                    break;
                }

                if (path.get(count-1).getNeighbors() == null){
                    System.out.println(node.toString());
                    break;
                }
                if (t.getRoute().getStart().equals(node.getName()) &&
                        t.getRoute().getEnd().equals(path.get(count).getName())){
                    temp.add(t);
                }
                }
            count++;
            int minVal = Integer.MAX_VALUE;
            Transportation minTrans = null;
            for (Transportation tran: temp){
                if (tran.getCost() < minVal){
                    minTrans = tran;
                     minVal = tran.getCost();
                }
            }
            if (minTrans == null){
                break;
            }
            trans.add(minTrans);
        }
        return trans;
    }

    /**
     * a helper function for the cheapest path that returns the path in a list of dikstras nodes
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return a list of nodes in order from start to end
     */
    public static List<dijkstras.Node> helpercheapest(Map<Integer,Transportation> m, String start,
                                                  String end) {
        Map<String, dijkstras.Node> ma = new TreeMap<>();
        Special sp = new Special(ma);
        ArrayList<Route> temp = new ArrayList<>();
        for (Transportation val : m.values()) {
            Route r = val.getRoute();
            if (!(temp.contains(r))) {
                dijkstras.Node tempor = new dijkstras.Node(r.getStart());
                if (ma.containsKey(r.getStart())) {
                    if (ma.containsKey(r.getEnd())) {
                        ma.get(r.getStart()).addNeighbor(ma.get(r.getEnd()), val.getCost());
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        ma.get(r.getStart()).addNeighbor(tem, val.getCost());
                        ma.put(tem.getName(), tem);
                    }
                } else {
                    dijkstras.Node t = new dijkstras.Node(r.getStart());
                    if (ma.containsKey(r.getEnd())) {
                        t.addNeighbor(ma.get(r.getEnd()), val.getCost());
                        ma.put(t.getName(), t);
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        t.addNeighbor(tem, val.getCost());
                        ma.put(t.getName(), t);
                        ma.put(tem.getName(), tem);
                    }
                }
            }
            temp.add(r);
        }

        return sp.ShortestPath(start, end);
    }

    /**
     * This function will use Dijkstra’s to determine the quickest path between the two locations.
     * It will return a List of the Transportations in the order they are used to get between the
     * paths; or an empty List if no such path exists.
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return It will return a List of the Transportations in the order they are used to get
     * between the paths
     */
    public static List<Transportation> quickestPath(Map<Integer,Transportation> m,
                                                    String start,
                                                    String end){
        List<dijkstras.Node> path = helpertime(m,start,end);
        List<Transportation> trans = new LinkedList<>();
        if (path == null){
            List<Transportation> empty = new LinkedList<>();

            return empty;
        }
        int count = 1;
        for (dijkstras.Node node: path){
            ArrayList<Transportation> temp = new ArrayList<>();
            for (Transportation t : m.values()){
                Route r = t.getRoute();
                if (count >= path.size()){
                    break;
                }

                if (path.get(count-1).getNeighbors() == null){
                    System.out.println(node.toString());
                    break;
                }
                if (t.getRoute().getStart().equals(node.getName()) &&
                        t.getRoute().getEnd().equals(path.get(count).getName())){
                    temp.add(t);
                }
            }
            count++;
            int minVal = Integer.MAX_VALUE;
            Transportation minTrans = null;
            for (Transportation tran: temp){
                if (tran.getCost() < minVal){
                    minTrans = tran;
                    minVal = tran.getCost();
                }
            }
            if (minTrans == null){
                break;
            }
            trans.add(minTrans);
        }
        return trans;
    }

    /**
     * a helper function for the quickest path that returns the path in a list of dikstras nodes
     *
     * @param m an instance of the map created in Phase 1.
     * @param start  the name of the starting location.
     * @param end the name of the ending location.
     * @return a list of nodes in order from start to end
     */
    public static List<dijkstras.Node> helpertime(Map<Integer,Transportation> m, String start,
                                                  String end) {
        Map<String, dijkstras.Node> ma = new TreeMap<>();
        Special sp = new Special(ma);
        ArrayList<Route> temp = new ArrayList<>();
        for (Transportation val : m.values()) {
            Route r = val.getRoute();
            if (!(temp.contains(r))) {
                dijkstras.Node tempor = new dijkstras.Node(r.getStart());
                if (ma.containsKey(r.getStart())) {
                    if (ma.containsKey(r.getEnd())) {
                        ma.get(r.getStart()).addNeighbor(ma.get(r.getEnd()), val.getCost());
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        ma.get(r.getStart()).addNeighbor(tem, val.getCost());
                        ma.put(tem.getName(), tem);
                    }
                } else {
                    dijkstras.Node t = new dijkstras.Node(r.getStart());
                    if (ma.containsKey(r.getEnd())) {
                        t.addNeighbor(ma.get(r.getEnd()), val.getCost());
                        ma.put(t.getName(), t);
                    } else {
                        dijkstras.Node tem = new dijkstras.Node(r.getEnd());
                        t.addNeighbor(tem, val.getCost());
                        ma.put(t.getName(), t);
                        ma.put(tem.getName(), tem);
                    }
                }
            }
            temp.add(r);
        }

        return sp.ShortestPath(start, end);
    }



    public static void main(String[] args) {
        Map<Integer,Transportation> map = TransportationReader.readTransportations(args[0]);
        System.out.println(shortestPath(map,"RIT","Sea"));
        int done = leastStops(map,"Roc","NYC");
    }
}
