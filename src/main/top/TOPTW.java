package top;

import java.util.ArrayList;
import java.util.Arrays;

import es.ull.esit.utilities.ExpositoUtilities;

/**
 * @brief Class to represent the TOPTW problem.
 */
public class TOPTW {
    private int nodes;
    private double[] x;
    private double[] y;
    private double[] score;
    private double[] readyTime;
    private double[] dueTime;
    private double[] serviceTime;
    private int vehicles;
    private int depots;
    private double maxTimePerRoute;
    private double maxRoutes;
    private double[][] distanceMatrix;

    /**
     * @brief Constructor of the class.
     * @param nodes
     * @param routes
     */
    public TOPTW(int nodes, int routes) {
        this.nodes = nodes;
        this.depots = 0;
        this.x = new double[this.nodes + 1];
        this.y = new double[this.nodes + 1];
        this.score = new double[this.nodes + 1];
        this.readyTime = new double[this.nodes + 1];
        this.dueTime = new double[this.nodes + 1];
        this.serviceTime = new double[this.nodes + 1];
        this.distanceMatrix = new double[this.nodes + 1][this.nodes + 1];
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                this.distanceMatrix[i][j] = 0.0;
            }
        }
        this.maxRoutes = routes;
        this.vehicles = routes;
    }

    /**
     * @brief Method to know if a node is a depot.
     * @param a
     * @return
     */
    public boolean isDepot(int a) {
        if(a > this.nodes) {
            return true;
        }
        return false;
    }

    /**
     * @brief Method to get the distance of a route.
     * @param route
     * @return
     */
    public double getDistance(int[] route) {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            int node1 = route[i];
            int node2 = route[i + 1];
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * @brief Method to get the distance of a route.
     * @param route
     * @return
     */
    public double getDistance(ArrayList<Integer> route) {
        double distance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            int node1 = route.get(i);
            int node2 = route.get(i + 1);
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * @brief Method to get the distance of a route.
     * @param routes
     * @return
     */
    public double getDistance(ArrayList<Integer>[] routes) {
        double distance = 0.0;
        for (ArrayList<Integer> route : routes) {
            distance += this.getDistance(route);
        }
        return distance;
    }

    /**
     * @brief Method to calculate the distance matrix.
     */
    public void calculateDistanceMatrix() {
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                if (i != j) {
                    double diffXs = this.x[i] - this.x[j];
                    double diffYs = this.y[i] - this.y[j];
                    this.distanceMatrix[i][j] = Math.sqrt(diffXs * diffXs + diffYs * diffYs);
                    this.distanceMatrix[j][i] = this.distanceMatrix[i][j];
                } else {
                    this.distanceMatrix[i][j] = 0.0;
                }
            }
        }
    }

    /**
     * @brief Method to get the maximum time per route.
     */
    public double getMaxTimePerRoute() {
        return maxTimePerRoute;
    }

    /**
     * @brief Method to set the maximum time per route.
     * @param maxTimePerRoute
     */
    public void setMaxTimePerRoute(double maxTimePerRoute) {
        this.maxTimePerRoute = maxTimePerRoute;
    }

    /**
     * @brief Method to get the maximum number of routes.
     */
    public double getMaxRoutes() {
        return maxRoutes;
    }

    /**
     * @brief Method to set the maximum number of routes.
     * @param maxRoutes
     */
    public void setMaxRoutes(double maxRoutes) {
        this.maxRoutes = maxRoutes;
    }

    /**
     * @brief Method to get the POIs.
     * @return
     */
    public int getPOIs() {
        return this.nodes;
    }

    /**
     * @brief Method to get the distance between two nodes.
     * @param i
     * @param j
     * @return double value.
     */
    public double getDistance(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * @brief Method to get the time between two nodes.
     * @param i
     * @param j
     * @return double value.
     */
    public double getTime(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * @brief Method to get the nodes.
     * @return int value.
     */
    public int getNodes() {
        return this.nodes;
    }

    /**
     * @brief Method to set the nodes.
     * @param nodes
     */
    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    /**
     * @brief Method to get the x coordinate of a node.
     * @param index
     * @return double value.
     */
    public double getX(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.x[index];
    }

    /**
     * @brief Method to set the x coordinate of a node.
     * @param index
     * @param x
     */
    public void setX(int index, double x) {
        this.x[index] = x;
    }

    /**
     * @brief Method to get the y coordinate of a node.
     * @param index
     * @return double value.
     */
    public double getY(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.y[index];
    }

    /**
     * @brief Method to set the y coordinate of a node.
     * @param index
     * @param y
     */
    public void setY(int index, double y) {
        this.y[index] = y;
    }

    /**
     * @brief Method to get the score of a node.
     * @param index
     * @return double value.
     */
    public double getScore(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.score[index];
    }

    /**
     * @brief Method to get the score of a node.
     */
    public double[] getScore() {
        return this.score;
    }

    /**
     * @brief Method to set the score of a node.
     * @param index
     * @param score
     */
    public void setScore(int index, double score) {
        this.score[index] = score;
    }

    /**
     * @brief Method to get the ready time of a node.
     * @param index
     * @return double value.
     */
    public double getReadyTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.readyTime[index];
    }

    /**
     * @brief Method to set the ready time of a node.
     * @param index
     * @param readyTime
     */
    public void setReadyTime(int index, double readyTime) {
        this.readyTime[index] = readyTime;
    }

    /**
     * @brief Method to get the due time of a node.
     * @param index
     * @return double value.
     */
    public double getDueTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.dueTime[index];
    }

    /**
     * @brief Method to set the due time of a node.
     * @param index
     * @param dueTime
     */
    public void setDueTime(int index, double dueTime) {
        this.dueTime[index] = dueTime;
    }

    /**
     * @brief Method to get the service time of a node.
     * @param index
     * @return double value.
     */
    public double getServiceTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.serviceTime[index];
    }

    /**
     * @brief Method to set the service time of a node.
     * @param index
     * @param serviceTime
     */
    public void setServiceTime(int index, double serviceTime) {
        this.serviceTime[index] = serviceTime;
    }

    /**
     * @brief Method to get the vehicles.
     * @return int value.
     */
    public int getVehicles() {
        return this.vehicles;
    }

    /**
     * @brief Method to convert the TOPTW object to a string.
     */
    @Override
    public String toString() {
        final int COLUMN_WIDTH = 15;
        String text = "Nodes: " + this.nodes + "\n";
        String[] strings = new String[]{"CUST NO.", "XCOORD.", "YCOORD.", "SCORE", "READY TIME", "DUE DATE", "SERVICE TIME"};
        int[] width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        for (int i = 0; i < this.nodes; i++) {
            strings = new String[strings.length];
            int index = 0;
            //strings[index++] = Integer.toString("" + i);
            strings[index++] = Integer.toString(i);
            strings[index++] = "" + this.x[i];
            strings[index++] = "" + this.y[i];
            strings[index++] = "" + this.score[i];
            strings[index++] = "" + this.readyTime[i];
            strings[index++] = "" + this.dueTime[i];
            strings[index++] = "" + this.serviceTime[i];
            text += ExpositoUtilities.getFormat(strings, width);
            text += "\n";
        }
        text += "Vehicles: " + this.vehicles + "\n";
        strings = new String[]{"VEHICLE", "CAPACITY"};
        width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        return text;
    }

    /**
     * @brief Method add a node.
     * @return int value.
     */
    public int addNode() {
        this.nodes++;
        return this.nodes;
    }

    /**
     * @brief Method to add a node to the depot.
     * @return int value.
     */
    public int addNodeDepot() {
        this.depots++;
        return this.depots;
    }
}
