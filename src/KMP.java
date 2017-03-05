import java.util.Arrays;

public class KMP {

    private int nextTable[];
    private String t;
    private String p;

    public KMP(String t, String p){
        this.t = t;
        this.p = p;
        nextTable = new int[p.length()];
        generateTable();
        System.out.println(Arrays.toString(nextTable));
    }

    private Integer next(int q){
        return nextTable[q - 1];
    }

    private void generateTable(){
        nextTable[0] = 0;
        int q = 0;
        for(int i = 1; i < p.length(); i++){
            while(q > 0 && p.charAt(i) != p.charAt(q)){
                q = next(q);
            }
            if(p.charAt(i) == p.charAt(q)){
                q++;
            }
            nextTable[i] = q;
        }

    }

    public static void main(String[] args) {
        new KMP("test", "ananas");
    }

}
