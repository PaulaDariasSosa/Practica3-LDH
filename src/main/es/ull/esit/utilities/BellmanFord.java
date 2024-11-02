package es.ull.esit.utilities;

import java.util.ArrayList;

/**
 * @class BellmanFord
 * @brief Class to solve the Bellman-Ford algorithm.
 */
public class BellmanFord {

    /**
     * @brief Constant to represent infinity.
     */
    private static final int INFINITY = 999999;
    /**
     * @brief Matrix to store the distances between nodes.
     */
    private final int[][] distanceMatrix;
    /**
     * @brief Lists to store the edges of the graph.
     */
    private ArrayList<Integer> edges1 = null;
    /**
     * @brief Lists to store the edges of the graph.
     */
    private ArrayList<Integer> edges2 = null;
    /**
     * @brief Number of nodes in the graph.
     */
    private final int nodes;
    /**
     * @brief List to store the path between the nodes.
     */
    private final ArrayList<Integer> path;
    /**
     * @brief Array to store the distances between nodes.
     */
    private int[] distances = null;
    /**
     * @brief Value of the path between the nodes.
     */
    private int value;

    /**
     * @brief Constructor of the class.
     * @param distanceMatrix
     * @param nodes
     * @param path
     */
    public BellmanFord(int[][] distanceMatrix, int nodes, ArrayList<Integer> path) {
        this.distanceMatrix = distanceMatrix;
        this.nodes = nodes;
        this.path = path;
        this.calculateEdges();
        this.value = BellmanFord.INFINITY;
    }

    /**
     * @brief Method to calculate the edges of the graph.
     */
    private void calculateEdges() {
        this.edges1 = new ArrayList<>();
        this.edges2 = new ArrayList<>();
        for (int i = 0; i < this.nodes; i++) {
            for (int j = 0; j < this.nodes; j++) {
                if (this.distanceMatrix[i][j] != Integer.MAX_VALUE) {
                    this.edges1.add(i);
                    this.edges2.add(j);
                }
            }
        }
    }

    /**
     * @brief Method to get the distances between nodes.
     * @return Array with the distances between nodes.
     */
    public int[] getDistances() {
        return this.distances;
    }

    /**
     * @brief Method to get the value of the path between the nodes.
     * @return Value of the path between the nodes.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @brief Method to solve the problem.
     */
    public void solve() {
        int numEdges = this.edges1.size();
        int[] predecessor = new int[this.nodes];
        this.distances = new int[this.nodes];
        for (int i = 0; i < this.nodes; i++) {
            this.distances[i] = BellmanFord.INFINITY;
            predecessor[i] = -1;
        }
        this.distances[0] = 0;
        for (int i = 0; i < (this.nodes - 1); i++) {
            for (int j = 0; j < numEdges; j++) {
                int u = this.edges1.get(j);
                int v = this.edges2.get(j);
                if (this.distances[v] > this.distances[u] + this.distanceMatrix[u][v]) {
                    this.distances[v] = this.distances[u] + this.distanceMatrix[u][v];
                    predecessor[v] = u;
                }
            }
        }
        this.path.add(this.nodes - 1);
        int pred = predecessor[this.nodes - 1];
        while (pred != -1) {
            this.path.add(pred);
            pred = predecessor[pred];
        }
        this.value = -this.distances[this.nodes - 1];
    }
}
