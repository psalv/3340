import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class TopologicalSort {

    public static boolean doTopologicalSort(GraphNode[] nodes){
        LinkedList<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> seen = new HashSet<>();

        for(GraphNode n: nodes){
            if(n.getInDegree() == 0){
                queue.addLast(n);
                seen.add(n);
            }
        }

        int pos = 0;
        while(!queue.isEmpty()){
            GraphNode cur = queue.removeFirst();
            nodes[pos++] = cur;
            for(GraphNode n: cur.getAdjList().keySet()){
                if(seen.contains(n)){
                    System.out.println("Cycle found, topological sorting requires a directed acyclic graph.");
                    return false;
                }
                if(n.decreaseIn()){
                    queue.addLast(n);
                    seen.add(n);
                }
            }
        }

        if(pos != nodes.length){
            System.out.println("Processing incomplete, topological sorting requires a directed acyclic graph.");
            return false;
        }

        System.out.println(Arrays.toString(nodes));
        return true;
    }


    public static void main(String[] args) {

        // We are building a digraph, if we wanted an undigraph we would add the edge to each adj list.
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        GraphNode g = new GraphNode("g");
        a.addEdge(b);
        a.addEdge(c);
        a.addEdge(d);
        a.addEdge(f);
        b.addEdge(c);
        b.addEdge(f);
        c.addEdge(e);
        c.addEdge(f);
        e.addEdge(f);

        GraphNode[] nodes = {a, b, c, d, e, f, g};

        doTopologicalSort(nodes);
    }
}
