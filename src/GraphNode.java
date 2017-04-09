import java.util.ArrayList;

public class GraphNode {

    private String key;
    private ArrayList<GraphEdge> adjList = new ArrayList<>();
    private boolean flag = false;

    public GraphNode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void addEdge(GraphEdge e){
        adjList.add(e);
    }

    public ArrayList<GraphEdge> getAdjList() {
        return adjList;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void printAdj(){
        System.out.println("Adjacency list for: " + this.key);
        for(GraphEdge e: adjList){
            System.out.println("\t" + e.getOpposite(this));
        }
    }

    public String toString(){
        return "Node: " + this.key;
    }
}
