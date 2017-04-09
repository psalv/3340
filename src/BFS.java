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
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
    }
}
