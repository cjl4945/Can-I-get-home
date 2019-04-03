package dijkstras;/*
 * Graph.java
 *
 * Version:
 * $Id: Graph.java,v 1.1 2014/02/07 20:25:20 atd Exp $
 *
 * Revisions:
 * $Log: Graph.java,v $
 * Revision 1.1  2014/02/07 20:25:20  atd
 * initial revision
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Graph class.  Holds representation of a graph as well as functions to 
 * interact with the graph.
 * 
 * @author atd Aaron T Deever
 *
 */
public class Graph {

    /*
     * graph is represented using a map (dictionary).
     */
    protected Map<String, Node> graph;
    
    /**
     * Constructor.
     * * 
     * @param graph the graph to search
     */
    public Graph(Map<String, Node> graph) {
        this.graph = graph;
    }
    
    /**
     * Method to generate a string associated with the graph.  The string
     * comprises one line for each node in the graph. Overrides
     * Object toString method.
     * 
     * @return string associated with the graph.
     */
    public String toString() { 
        String result = "";
        for (String name : graph.keySet()) { 
            result = result + graph.get(name) + "\n";
        }
        return result;
    }
    
    /**
     * Method to check if a given String node is in the graph.
     * @param nodeName string name of a node
     * @return boolean true if the graph contains that key; false otherwise
     */
    public boolean isInGraph(String nodeName) { 
        return graph.containsKey(nodeName);
    }
    
    /**
     * Method to compute and display the shortest path in a weighted graph
     * from a start node to a finish node.
     *
     * Precondition: the inputs correspond to nodes in the graph.   
     * 
     * @param start String name of starting node
     * @param finish String name of finishing node
     * 
     */
    public void displayShortestPath(String start, String finish) { 
        
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
        
        if(distance.get(finishNode) == Integer.MAX_VALUE) { 
            System.out.println("No path from " + start + " to " + finish);
        }
        else { 
            System.out.println("Minimum distance between " + start + " and " + 
                    finish + " is " + String.valueOf(distance.get(finishNode)));
            
            List<Node> path = new LinkedList<Node>();
            Node n = finishNode;
            while (!n.equals(startNode)) { 
                path.add(0, n);
                n = predecessors.get(n);
            }
            path.add(0, startNode);
            
            System.out.print("Shortest path: ");
            for(Node n1 : path) { 
                System.out.print(n1.getName() + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Dijkstra's algorithm.  Given a weighted graph, and a starting node, computes
     * the shortest path to all other nodes (some may be unreachable).
     *
     * Precondition:  assumes weights are non-negative
     * 
     * @param startNode:  the starting node
     * @param distance:  map that holds the minimum distance to any node
     * @param predecessors: map holds predecessor nodes along any shortest path
     */
    protected void dijkstra(Node startNode, Map<Node, Integer> distance,
            Map<Node, Node> predecessors) { 
        
        // initialize distances - we will use Integer.MAX_VALUE to
        // represent infinity
        for(String name : graph.keySet()) { 
            distance.put(graph.get(name), Integer.MAX_VALUE);
        }
        distance.put(startNode,  0);
        
        // initialize predecessors - by not yet including any other nodes,
        // they are unvisited and have no predecessor.  Source node is
        // given predecessor of itself.
        predecessors.put(startNode,  startNode);
        
        // our priority queue will just be a list that we search to extract
        // the minimum from at each step (O(n))
        List<Node> priorityQ = new LinkedList<Node>();
        for (String name : graph.keySet()) { 
            priorityQ.add(graph.get(name));
        }
        
        // main loop
        while (!priorityQ.isEmpty()) { 
            Node U = dequeueMin(priorityQ, distance);
            
            // return if this node still has distance "infinity" - 
            // remaining nodes are inaccessible
            if(distance.get(U) == Integer.MAX_VALUE) { 
                return;
            }
            
            // this loop allows neighbors that have already been finalized
            // to be checked again, but they will never be updated and
            // this doesn't affect overall complexity 
            for(Edge e : U.getEdges()) { 
                Integer w = e.getWeight();
                Node n = e.getToNode();
                // relaxation
                Integer distViaU = distance.get(U) + w;
                if(distance.get(n) > distViaU) { 
                    distance.put(n,  distViaU);
                    predecessors.put(n,  U);
                }    
            }
        }
    }
    

    /*
     * Basic implementation of a priority queue that searches for the minimum.
     */
    private Node dequeueMin(List<Node> priorityQ, Map<Node, Integer> distance) { 

        Node minNode = priorityQ.get(0);  // start off with first one
        for (Node n : priorityQ) { // checks first one again...
            if(distance.get(n) < distance.get(minNode)) { 
                minNode = n;
            }
        }
        return priorityQ.remove(priorityQ.indexOf(minNode));
    }
}
