/**
 * Can be weighted or unweighted.
 *
 * If the graph is directed it will be in the direction of u -> v
 */
public class GraphEdge {

    private GraphNode u;
    private GraphNode v;
    private int w;

    public GraphEdge(GraphNode u, GraphNode v) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public GraphEdge(GraphNode u, GraphNode v, int w) {
        this.u = u;
        this.v = v;
    }

    public GraphNode getU() {
        return u;
    }

    public GraphNode getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public GraphNode getOpposite(GraphNode n){
        return u == n ? v : u;
    }

    public String toString() {
        return "Edge: " + u.getIntKey() + " " + v.getIntKey();
    }
}
