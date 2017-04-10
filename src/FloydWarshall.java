import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by paulsalvatore57 on 2017-04-10.
 */
public class FloydWarshall {



    public static Integer[][] floydwarshall(GraphNode[] vertices){
        int num = vertices.length;
        Integer[][] d = new Integer[num][num];

        int i = 0;
        int j;
        for(GraphNode x: vertices){
            j = 0;
            HashMap<GraphNode, Integer> adj = x.getAdjList();
            for(GraphNode y: vertices) {
                if(adj.containsKey(y)){
                    d[i][j] = adj.get(y);
                } else {
                    d[i][j] = Integer.MAX_VALUE / 2 - 1;
                }
                j++;
            }
            i++;
        }

        for(int x = 0; x < num; x++){
            d[x][x] = 0;
        }

        for(int m = 0; m < num; m++) {
            for (int x = 0; x < num; x++) {
                for (int y = 0; y < num; y++) {
                    if(d[x][m] + d[m][y] < d[x][y]){
                        d[x][y] = d[x][m] + d[m][y];
//                        System.out.println('c');
                    }
                }
            }
        }

        return d;
    }



    public static <E> void printTwoDimensionalArray(E[][] array){
        for(E[] row: array){
            System.out.println(Arrays.toString(row));
        }
    }


    public static void main(String[] args) {
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");
        a.addEdge(b, 2);
        a.addEdge(e, 7);
        b.addEdge(c, 1);
        c.addEdge(d, 7);
        c.addEdge(e, 1);
        e.addEdge(b, 2);
        e.addEdge(f, 20);
        f.addEdge(c, 1);
        d.addEdge(f, 1);
        d.addEdge(a, 1);

        GraphNode[] vertices = {a, b, c, d, e, f};

        printTwoDimensionalArray(floydwarshall(vertices));
    }
}
