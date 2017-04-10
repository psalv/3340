import java.util.HashSet;

public class Dijkstra {


    public static boolean relax(GraphNode u, GraphNode v, int w){
        Integer cur = v.getD();
        if(cur == null || cur > u.getD() + w){
            v.setD(u.getD() + w);
            v.setPi(u);
            return true;
        }
        return false;
    }

    /**
     * Note: inefficient heap update method, currently rebuilds the entire heap taking O(nlgn) time.
     * Therefore this algorithm will take O( (|V| + |E|) * |V|*lg(|V|)) time as is.
     *
     * With an efficient implementation this will only take O( (|V| + |E|) * lg(|V|) ) time.
     */
    public static void dijkstra(GraphNode[] vertices, GraphNode source){
        for(GraphNode n: vertices){
            n.setD(Integer.MAX_VALUE);
        }
        source.setD(0);

        MinHeap<GraphNode> heap = new MinHeap<>();
        for(GraphNode n: vertices){
            heap.insert(n);
        }
        HashSet<GraphNode> seen = new HashSet<>();

        while(!heap.isEmpty()){
            GraphNode cur = heap.extractMin();
            seen.add(cur);
            for(GraphNode n: cur.getAdjList().keySet()){
                if(!seen.contains(n)){
                    if(relax(cur, n, cur.getAdjList().get(n))){
                        heap.update();
                    }
                }
            }
        }

        for(GraphNode n: vertices){
            System.out.println("\n" + n);
            System.out.println("\t D: " + n.getD());
            System.out.println("\tPi: " + n.getPi());
        }

    }


    public static void main(String[] args) {
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        a.addEdge(b, 1);
        a.addEdge(c, 1);
        b.addEdge(a, 1);
        b.addEdge(f, 1);
        c.addEdge(f, 8);
        c.addEdge(c, 1);
        f.addEdge(e, 1);
        e.addEdge(a, 1);

        GraphNode[] vertices = {a, b, c, d, e, f};

        dijkstra(vertices, a);
    }
}
