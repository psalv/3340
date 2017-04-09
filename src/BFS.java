import java.util.HashSet;
import java.util.LinkedList;

public class BFS {


    public static boolean solveBFS(GraphNode root, String endKey){
        LinkedList<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> seen = new HashSet<>();
        queue.add(root);
        seen.add(root);
        while(!queue.isEmpty()){
            GraphNode cur = queue.removeFirst();
            if(cur.getKey().equals(endKey)){
                return true;
            }

            cur.getAdjList().keySet().stream().filter(n -> !seen.contains(n)).forEach(n -> {
                queue.addLast(n);
                seen.add(n);
            });
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

        System.out.println(solveBFS(a, "c"));
    }
}
