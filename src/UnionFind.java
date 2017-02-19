
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class UnionFindNode<E>{

    private UnionFindNode head;
    private E value;
    private int rank = 1;
    private int rep;

    public UnionFindNode(E value) {
        this.value = value;
        this.head = this;
    }

    public E getValue() {
        return value;
    }

    public UnionFindNode getHead() {
        return head;
    }

    public void setHead(UnionFindNode head) {
        this.head = head;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int hashCode(){
        return value.hashCode();
    }

    public String toString(){
        return "\nNode:  " + value.toString() + "\nClass: " + head.getValue().toString() + "\nRank: " + this.rank;
    }
}


public class UnionFind <E>{

    private UnionFindNode<E> sets[];

    // Retrieve indices that were assigned to generic type objects.
    private HashMap<E, Integer> indices = new HashMap<>();

    // We need to be able to tell how many elements have already been added to our UnionFind instance.
    private Integer counter = 0;
    private Integer numElements = null;

    private boolean finalized = false;

    // Specifies the maximum size of the elements contained within UnionFind.
    public void uandf(int n){
        sets = new UnionFindNode[n];
        numElements = n;
    }

    public void make_set(E i){
        if(!finalized){

            // We can only add as many elements as was specified in uandf; therefore, we check both that we still have
            // space to add an element, and also that uandf has already been called.
            if(numElements == null || counter >= numElements){
                System.out.println("Invalid operation: check initialization function uandf and number of existing sets.");
            }
            else{
                indices.put(i, counter);
                sets[counter++] = new UnionFindNode<>(i);
            }
        }
    }

    public void union_sets(E i, E j){
        if(!finalized){

            // First need to check that each of these elements have been added to the UnionFind structure.
            Integer indexI = indices.get(i);
            Integer indexJ = indices.get(j);
            if(indexI == null || indexJ == null){
                System.out.println("One or both of the elements was not contained in the collection.");
                return;
            }

            // Retrieve the representatives from each of the sets contained i and j
            UnionFindNode<E> iHead = getVeryHead(sets[indexI]);
            UnionFindNode<E> jHead = getVeryHead(sets[indexJ]);

            // We always make the smaller set point to the larger to reduce the size of the tree.
            // We adjust the rank in order to tell the relative tree sizes for future operations.
            if(iHead.getRank() > jHead.getRank()){
                jHead.setHead(iHead);
                iHead.setRank(iHead.getRank() + jHead.getRank());
            }
            else{
                iHead.setHead(jHead);
                jHead.setRank(iHead.getRank() + jHead.getRank());
            }

        }
    }

    private UnionFindNode<E> getVeryHead(UnionFindNode<E> n){

        // Keep track of the path up to the head so we can do compression later.
        Stack<UnionFindNode<E>> path = new Stack<>();

        // We follow head pointers until we find a node pointing to itself, indicating that this is the representative.
        while(n.getHead() != n){
            path.push(n);
            n = n.getHead();
        }

        // Path compression.
        while(!path.isEmpty()){
            path.pop().setHead(n);
        }
        return n;
    }

    public UnionFindNode find_set(E i){

        // Need to ensure that i has been added to our UnionFind structure.
        Integer index = indices.get(i);
        if(index == null){
            System.out.println("Element was not contained in the collection.");
            return null;
        }
        return getVeryHead(sets[index]);
    }

//    private

    public int final_sets(){

        // We finalize the union-find such that no more changes can occur.
        finalized = true;
        HashMap<UnionFindNode<E>, ArrayList<UnionFindNode<E>>> foundSets = new HashMap<>();

        // Determining how manny different sets there are
        for(UnionFindNode<E> e: sets){

            // Sets does not necessarily need to be full, this avoids a NullPointerException.
            if(e == null){
                break;
            }

            // We use the representative from each node, inspecting if it has already been seen,
            // if so we add our element to the representatives list of children, otherwise we have found
            // a new representative and create a new list for it's children.
            UnionFindNode<E> head = getVeryHead(e);
            if(!foundSets.containsKey(head)){
                foundSets.put(head, new ArrayList<>());
            }
            foundSets.get(head).add(e);
        }
        int numSets = foundSets.size();

        // We need to go through each set and reset their representatives to be integers.
        int i = numSets;
        for(UnionFindNode<E> key: foundSets.keySet()){
            UnionFindNode<Integer> newRepresentative = new UnionFindNode<>(i--);
            for(UnionFindNode<E> val: foundSets.get(key)){
                val.setHead(newRepresentative);
            }
        }

        // Finally return the number of unique sets that was found earlier.
        return numSets;
    }

    public String toString(){
        String toReturn = "";
        for(UnionFindNode<E> e: sets){
            if(e == null){
                break;
            }
            toReturn += e.toString() + "\n";
        }

        return toReturn;
    }

    public static void main(String[] args) {
        UnionFind<Integer> test = new UnionFind<>();
        test.uandf(6);
        test.make_set(1);
        test.make_set(2);
        test.make_set(3);
//        test.make_set(4);
        test.make_set(25);
        test.make_set(26);

        test.union_sets(25, 26);
        test.union_sets(1, 2);
//        test.union_sets(4, 2);
        test.union_sets(3, 25);
//        System.out.println(test);
        test.final_sets();
        System.out.println(test);
    }
}
