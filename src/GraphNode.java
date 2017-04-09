import java.util.HashMap;

public class GraphNode {

    private String key;
    private HashMap<GraphNode, Integer> adjList = new HashMap<>();
    private boolean flag = false;

    public GraphNode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public boolean addEdge(GraphNode n){
        if(adjList.containsKey(n)){
            return false;
        }
        adjList.put(n, 0);
        return true;
    }

    public boolean addEdge(GraphNode n, int w){
        if(adjList.containsKey(n)){
            return false;
        }
        adjList.put(n, w);
        return true;
    }

    public HashMap<GraphNode, Integer> getAdjList() {
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
        for(GraphNode n: adjList.keySet()){
            System.out.println("\t" + n.getKey() + " w: " + adjList.get(n));
        }
    }

    public int hashCode(){
        return key.hashCode();
    }

    public String toString(){
        return "Node: " + this.key;
    }
}
