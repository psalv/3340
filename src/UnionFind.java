import java.lang.reflect.Array;
import java.util.Arrays;

class UnionFindNode<E>{

    private UnionFindNode<E> head;
    private E value;

    public UnionFindNode(E value) {
        this.value = value;
        this.head = this;
    }

    public E getValue() {
        return value;
    }

    public UnionFindNode<E> getHead() {
        return head;
    }

    public void setHead(UnionFindNode<E> head) {
        this.head = head;
    }

    public String toString(){
        return "\nNode:  " + value.toString() + "\nClass: " + head.getValue().toString();
    }
}


public class UnionFind <E>{

    private E sets[];

    private boolean finalized = false;



    public void uandf(int n){
        sets = (E[]) new Object[n];
        System.out.println(Arrays.toString(sets));
    }

    public void make_set(E i){
        if(!finalized){

        }
    }

    public void union_sets(E i[], E j[]){
        if(!finalized){

        }
    }

    public E[] find_set(E i){
        return null;
    }

    public int final_sets(){
        finalized = true;


        return 0;
    }

    public static void main(String[] args) {
        UnionFind<Integer> test = new UnionFind<>();
        test.uandf(4);
    }
}
