import java.util.Arrays;
import java.util.LinkedList;

public class TopologicalSort {

    public static void doTopologicalSort(GraphNode[] nodes){
        LinkedList<GraphNode> queue = new LinkedList<>();
        for(GraphNode n: nodes){
            if(n.getInDegree() == 0){
                queue.addLast(n);
            }
        }

        int pos = 0;
        while(!queue.isEmpty()){
            GraphNode cur = queue.removeFirst();
            nodes[pos++] = cur;
            for(GraphNode n: cur.getAdjList().keySet()){
                if(n.decreaseIn()){
                    queue.addLast(n);
                }
            }
        }

        System.out.println(Arrays.toString(nodes));



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
        b.addEdge(c);
        b.addEdge(f);
        c.addEdge(e);
        c.addEdge(f);
        e.addEdge(f);

        GraphNode[] nodes = {a, b, c, d, e, f, g};

        doTopologicalSort(nodes);
    }
}
