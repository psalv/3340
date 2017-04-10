
public class BellmanFord {


    public static boolean relax(GraphNode u, GraphNode v, int w){
        Integer cur = v.getD();
        if(cur == null || cur > u.getD() + w){
            v.setD(u.getD() + w);
            v.setPi(u);
            return true;
        }
        return false;
    }

    public static void bellmanford(GraphNode[] vertices, GraphNode source){
        GraphNode undefined = new GraphNode("UNDEFINED");

        for(GraphNode n: vertices){
            n.setD(Integer.MAX_VALUE);
        }
        source.setD(0);

        for(int i = 0; i < vertices.length; i++){
            for(GraphNode u: vertices){
                for(GraphNode v: u.getAdjList().keySet()){
                    relax(u, v, u.getAdjList().get(v));
                }
            }
        }

        for(GraphNode u: vertices){
            for(GraphNode v: u.getAdjList().keySet()){
                if(v.getD() > v.getD() + u.getAdjList().get(v)){
                    propagateUndefined(v, undefined);
                }
            }
        }

        for(GraphNode n: vertices){
            System.out.println("\n" + n);
            System.out.println("\t D: " + n.getD());
            System.out.println("\tPi: " + n.getPi());
        }

    }

    public static void propagateUndefined(GraphNode n, GraphNode und){
        if(n.getPi() == und){
            return;
        }
        n.setPi(und);
        for(GraphNode v: n.getAdjList().keySet()){
            propagateUndefined(v, und);
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
        a.addEdge(e, 1);
        a.addEdge(f, 2);
        e.addEdge(f, 2);
        b.addEdge(d, 1);
        d.addEdge(c, 1);
        c.addEdge(b, -3);

        GraphNode[] vertices = {a, b, c, d, e, f};

        bellmanford(vertices, a);
    }
}
