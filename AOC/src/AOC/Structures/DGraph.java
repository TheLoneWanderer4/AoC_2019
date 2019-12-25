package AOC.Structures;

import java.util.*;

/**
 * AOC.Structures.DGraph.java
 * 
 * Represents a directed graph. The nodes are represented as
 * integers ranging from 1 to num_nodes inclusive.
 * The weights are assumed to be >= zero.
 * 
 * Usage instructions:
 * 
 * Construct a AOC.Structures.DGraph
 * AOC.Structures.DGraph graph = new AOC.Structures.DGraph(numNodes);
 * 
 * Add an edge
 * graph.addEdge(v, w, weight);
 * 
 * Other useful methods:
 * graph.getWeight(v,w)
 * graph.getNumNodes()
 * List<Integer> list = graph.getNeighbors(v);
 * 
 */
public class DGraph {
    /*
     * Private Instance Variables
     *
     * @Variable, numNodes, the number of nodes in the graph
     */

    private int numNodes;
    public Set<String> nodes;
    private Set<Edge> edges;

    /*
     * Constructs an instance of the AOC.Structures.DGraph class with # nodes numNodes.
     */
    public DGraph(int numNodes) {
        this.numNodes = numNodes;
        nodes = new HashSet<String>();
        edges = new TreeSet<>();
    }

    public Set<Edge> getEdges(){
        return edges;
    }

    /**
     * Adds the directed edge (v,w) to the graph including updating the node
     * count appropriately.
     * 
     * @param v
     * @param w
     */
    public void addEdge(String v, String w, double distance) {
        int startSize = nodes.size();
        nodes.add(v);
        nodes.add(w);
        int endSize = nodes.size();
        numNodes += endSize - startSize;
        edges.add(new Edge(v, w, distance));
    }

    /*
     * Returns the number of nodes in this graph.
     */
    public int getNumNodes() {
        return numNodes;
    }

    // Returns the weight for the given edge.
    // Returns -1 if there is no edge between the nodes v and w.
    public double getWeight(String v, String w) {
        Edge testEdge = new Edge(v, w, 0.0);
        for(Edge edge : edges){
            if(edge.equals(testEdge)){
                return edge.weight;
            }
        }
        return -1;
    }

    /**
     * For a given node returns a sorted list of all its neighbors.
     * 
     * @param v
     *            Node identifier
     * @return A sorted list of v's neighbors.
     */
    public List<String> getNeighbors(String v) {
        Set<String> ret = new TreeSet<>();
        for(Edge edge : edges){
            if(edge.node1.equals(v)){
                ret.add(edge.node2);
            }
        }
        return new ArrayList<>(ret);
    }

    /* --------------------------------------- */
    /*
     * You should not need to touch anything below this line,
     * except for maybe the name edges in the for each loop just below
     * in the toDotString method if you named your collection of edges
     * differently.
     */
    // Create a dot representation of the graph.
    public String toDotString() {
        String dot_str = "digraph {\n";
        // Iterate over the edges in order.
        for (Edge e : edges) {
            dot_str += e.toDotString() + "\n";
        }
        return dot_str + "}\n";
    }

    /**
     * Immutable undirected edges.
     */
    public class Edge implements Comparable<Edge> {

        // Nodes in edge and weight on edge
        private final String node1;
        private final String node2;
        private final double weight;
        
        /**
         * Stores the given nodes with smaller id first.
         * 
         * @param node1
         * @param node2
         */
        public Edge(String node1, String node2, double weight) {
            assert weight >= 0.0;
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        /**
         * @return an directed edge string in dot format
         */
        public String toDotString() {
            return '"' + node1 +  '"' + " -> " + '"' + node2 + '"' + " [label=\"" + weight
                    + "\"];";
        }

        /**
         * Lexicographical ordering on edges (node1,node2).
         */
        public int compareTo(Edge other) {
            if (this.equals(other)) {
                return 0; // this and other are equal
            } else if ((node1.compareTo(other.node1) < 0)
                    || (node1.equals(other.node1) && node2.compareTo(other.node2) < 0)) {
                return -1; // this is less than other
            } else {
                return 1; // this is greater than other
            }
        }

        /**
         * Lexicographical ordering on edges (node1,node2).
         */
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) {
                return false;
            }
            Edge other = (Edge) o;
            return node1.equals(other.node1) && node2.equals(other.node2);
        }

        /**
         * Know number of nodes when read in input file, so can give each edge a
         * unique hash code.
         */
        public int hashCode() {
            return getNumNodes() * node1.hashCode() + node2.hashCode();
        }
    }

}
