package top;

/**
 * @brief Class that represents a route in the TOPTW problem.
 */
public class TOPTWRoute {
    int predecessor;
    int succesor;
    int id;

    /**
     * @brief Default constructor.
     */
    TOPTWRoute() {
        
    }

    /**
     * @brief Constructor.
     * @param pre Predecessor.
     * @param succ Successor.
     * @param id Route id.
     */
    TOPTWRoute(int pre, int succ, int id) {
        this.predecessor = pre;
        this.succesor = succ;
        this.id = id;
    }

    /**
     * @brief Get the predecessor of the route.
     * @return Predecessor.
     */
    public int getPredeccesor() {
        return this.predecessor;
    }

    /**
     * @brief Get the successor of the route.
     * @return Successor.
     */
    public int getSuccesor() {
        return this.succesor;
    }

    /**
     * @brief Get the id of the route.
     * @return Id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief Set the predecessor of the route.
     * @param pre Predecessor.
     */
    public void setPredeccesor(int pre) {
        this.predecessor = pre;
    }

    /**
     * @brief Set the successor of the route.
     * @param suc Successor.
     */
    public void setSuccesor(int suc) {
        this.succesor = suc;
    }

    /**
     * @brief Set the id of the route.
     * @param id Id.
     */
    public void setId(int id) {
        this.id = id;
    }
}
