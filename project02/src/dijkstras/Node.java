package dijkstras;/*
 * Node.java
 *
 * Version:
 * $Id: Node.java,v 1.1 2014/02/07 20:25:21 atd Exp $
 *
 * Revisions:
 * $Log: Node.java,v $
 * Revision 1.1  2014/02/07 20:25:21  atd
 * initial revision
 *
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a node in a graph.
 * 
 * @author atd:  Aaron T Deever
 *
 */

public class Node {

    /*
     *  Name associated with this node.
     */
    private String name;
    
    /*
     * Neighbors of this node are stored as a list of Edges.  Each edge
     * contains information on its Node endpoints, as well as an integer weight.  
     */
    protected List<Edge> neighbors;
    
    /**
     * Constructor.  Initialized with an empty list of neighbors.
     * 
     * @param name string representing the name associated with the node.
     */
    public Node(String name) { 
        this.name = name;
        this.neighbors = new LinkedList<Edge>();
    }
    
    /**
     * Get the String name associated with this Node.
     * 
     * @return name.
     */
    public String getName() { 
        return name;
    }
    
    /**
     * Add a neighbor to this node.  When no weight is specified, a
     * weight of 0 is assigned to the connecting edge.
     * @param n node to add as neighbor.
     */
    public void addNeighbor(Node n) { 
        addNeighbor(n, 0); // just set weight to 0 if not specified
    }
    
    /**
     * Add a neighbor to this node.  Weight of connecting edge is specified.
     * @param n node to add as neighbor
     * @param weight weight of the edge
     */
    public void addNeighbor(Node n, Integer weight) {
        Edge e = new Edge(this, n, weight);
        neighbors.add(e);
    }
    
    /**
     * Method to return a list for this node containing all 
     * of its neighbor nodes.
     * 
     * @return a list of neighboring nodes
     */
    public List<Node> getNeighbors() { 
        List<Node> l = new LinkedList<Node>();
        for(Edge e: getEdges()) { 
            l.add(e.getToNode());
        }
        return l;
    }
    
    /** 
     * Method to return a list for this node containing all
     * of its outgoing edges.
     * 
     * @return a list of outgoing edges
     */
    public List<Edge> getEdges() { 
        return new LinkedList<Edge>(neighbors);
    }
    
    /**
     * Method to generate a string associated with the node, including the 
     * name of the node followed by the names of its neighbors.  Overrides
     * Object toString method.
     * 
     * @return string associated with the node.
     */
    @Override
    public String toString() { 
        String result;
        result = name + ":  ";
        
        for(Edge e : neighbors) { 
            result = result + "(" + e.getToNode().getName() + ", " + 
                    e.getWeight() + "), ";
        }
        // remove last comma and space, or just spaces in the case of no neighbors
        return (result.substring(0, result.length()-2));
    }
    
    /**
     *  Two Nodes are equal if they have the same name.
     *  @param other The other object to check equality with
     *  @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Node) {
            Node n = (Node) other;
            result = this.name.equals(n.name);
        }
        return result;
    }

    /**
     * The hash code of a Node is just the hash code of the name,
     * since no two nodes can have the same name.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    
}
