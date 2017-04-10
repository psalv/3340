import java.util.HashMap;

public class GraphNode extends HasKey {

    private String key;
    private HashMap<GraphNode, Integer> adjList = new HashMap<>();
    private boolean flag = false;
    private int inDegree = 0;
    private Integer d = null;
    private GraphNode pi = null;

    public GraphNode(String key) {
        super(null);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void incrementIn(){
        this.inDegree++;
    }

    public boolean decreaseIn(){
        this.inDegree--;
        return this.inDegree == 0;
    }

    public boolean addEdge(GraphNode n){
        if(adjList.containsKey(n)){
            return false;
        }
        n.incrementIn();
        adjList.put(n, 0);
        return true;
    }


    public boolean addEdge(GraphNode n, int w){
        if(adjList.containsKey(n)){
            return false;
        }
        n.incrementIn();
        adjList.put(n, w);
        return true;
    }

    public boolean addUndirectedEdge(GraphNode n){
        if(adjList.containsKey(n)){
            return false;
        }
        n.incrementIn();
        adjList.put(n, 0);
        n.addEdge(this);
        return true;
    }

    public boolean addUndirectedEdge(GraphNode n, int w){
        if(adjList.containsKey(n)){
            return false;
        }
        n.incrementIn();
        adjList.put(n, w);
        n.addEdge(this, w);
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

    public int getInDegree() {
        return inDegree;
    }

    public void printAdj(){
        System.out.println("Adjacency list for: " + this.key);
        for(GraphNode n: adjList.keySet()){
            System.out.println("\t" + n.getKey() + " w: " + adjList.get(n));
        }
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.setIntKey(d);
        this.d = d;
    }

    public GraphNode getPi() {
        return pi;
    }

    public void setPi(GraphNode pi) {
        this.pi = pi;
    }

    public int hashCode(){
        return key.hashCode();
    }

    public String toString(){
        return "Node: " + this.key;
    }
}
