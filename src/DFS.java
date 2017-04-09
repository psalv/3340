import java.util.HashSet;

public class DFS {


    public static boolean DFS(GraphNode root, String endKey, HashSet<GraphNode> seen){
        if(root.getKey().equals(endKey)){
            return true;
        }
        seen.add(root);
        for(GraphNode n: root.getAdjList().keySet()){
            if(!seen.contains(n)){
                if(DFS(n, endKey, seen)){
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean solveDFS(GraphNode root, String endKey){
        HashSet<GraphNode> seen = new HashSet<>();
        for(GraphNode n: root.getAdjList().keySet()){
            if(!seen.contains(n)){
                if(DFS(n, endKey, seen)){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // We are building a digraph, if we wanted an undigraph we would add the edge to each adj list.
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        a.addEdge(b);
        a.addEdge(c);
        b.addEdge(a);
        b.addEdge(d);
        c.addEdge(f);
        c.addEdge(c);
        f.addEdge(e);
        e.addEdge(a);


        System.out.println(solveDFS(a, "c"));
    }
}
