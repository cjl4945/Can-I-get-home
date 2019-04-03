package stu;

import dijkstras.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Chase Lewis
 */
public class Special extends dijkstras.Graph {
    /**
     * Constructor.
     * *
     *
     * @param graph the graph to search
     */
    public Special(Map<String, Node> graph) {
        super(graph);
    }

    /**
     * Method to compute and display the shortest path in a weighted graph
     * from a start node to a finish node.
     *
     * Precondition: the inputs correspond to nodes in the graph.
     *
     * @param start String name of starting node
     * @param finish String name of finishing node
     * @return a list of nodes from start to finish
     */
    public List<Node> ShortestPath(String start, String finish) {

        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // create a distance map that will hold the shortest path distance
        // to each node from the given startNode.  We will just use the
        // maximum Integer value to represent infinity
        Map<Node, Integer> distance = new HashMap<Node, Integer>();

        // create a predecessor map that will be used to determine
        // the shortest path to each node from the given startNode.
        // If a node is not yet in the map, that is equivalent to the
        // node not having a predecessor, and not being reachable.
        Map<Node, Node> predecessors = new HashMap<Node, Node>();

        dijkstra(startNode, distance, predecessors);

        if (distance.get(finishNode) == Integer.MAX_VALUE) {
            return null;
        } else {
            List<Node> path = new LinkedList<Node>();
            Node n = finishNode;
            while (!n.equals(startNode)) {
                path.add(0, n);
                n = predecessors.get(n);
            }
            path.add(0, startNode);
            return path;

        }
    }
}
