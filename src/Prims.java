import java.util.HashSet;


public class Prims {

    private static void prims(GraphNode[] vertices){
        MinHeap<GraphNode> heap = new MinHeap<>();

        for(GraphNode n: vertices){
            n.setD(Integer.MAX_VALUE);
        }
        vertices[0].setD(0);

        // Add each element into the heap
        for(GraphNode n: vertices){
            heap.insert(n);
        }

        // A set containing all of the vertices that are apart of the thus far connected MST.
        HashSet<GraphNode> connectedVertices = new HashSet<>();

        while(!heap.isEmpty()){

            // We take the minimum element out, and add it to the connectVertices set.
            GraphNode u = heap.extractMin();
            connectedVertices.add(u);

            // Examine each edge in the adjacency list for u
            for(GraphNode v: u.getAdjList().keySet()){

                // We check if the edge from u to v is minimal, if so we set the pi value of v, and update
                // the heap with this edge weight as the new minimum.
                if(!connectedVertices.contains(v) && u.getAdjList().get(v) < v.getIntKey()){
                    v.setPi(u);
                    v.setD(u.getAdjList().get(v));
                    heap.update();
                }
            }

            // Print edges that are set as vertices are taken out, since these pi values are finalized.
            try {
                System.out.println("\nEdge from " + u + " to " + u.getPi() + "\n   Weight: " + u.getAdjList().get(u.getPi()));
            }
            catch(NullPointerException e){
                // none
            }
        }
    }

    public static void main(String[] args) {
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        a.addUndirectedEdge(b, 7);
        a.addUndirectedEdge(c, 1);
        b.addUndirectedEdge(f, 3);
        f.addUndirectedEdge(c, 1);
        f.addUndirectedEdge(d, 2);
        d.addUndirectedEdge(e, 2);
        c.addUndirectedEdge(e, 9);


        GraphNode[] vertices = {a, b, c, d, e, f};

        prims(vertices);
    }
}
