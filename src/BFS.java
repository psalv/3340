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

            for(GraphEdge e: cur.getAdjList()){
                GraphNode temp = e.getOpposite(cur);
                if(!seen.contains(temp)){
                    queue.addLast(temp);
                    seen.add(temp);
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
        GraphEdge e1 = new GraphEdge(a, b);
        a.addEdge(e1);
        GraphEdge e2 = new GraphEdge(a, c);
        a.addEdge(e2);
        GraphEdge e3 = new GraphEdge(b, a);
        b.addEdge(e3);
        GraphEdge e8 = new GraphEdge(b, d);
        b.addEdge(e8);
        GraphEdge e4 = new GraphEdge(c, f);
        c.addEdge(e4);
        GraphEdge e5 = new GraphEdge(c, c);
        c.addEdge(e5);
        GraphEdge e6 = new GraphEdge(f, e);
        f.addEdge(e6);
        GraphEdge e7 = new GraphEdge(e, a);
        e.addEdge(e7);

        System.out.println(solveBFS(a, "c"));
    }
}
