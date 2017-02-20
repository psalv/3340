
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class UnionFindException extends Exception{
    public UnionFindException(String s){
        super(s);
    }
}

// A class to store information necessary for UnionFind to function.
class UnionFindNode<E extends Comparable<E>> implements Comparable<UnionFindNode<E>>{

    // A head which points to the path to a representative for a set.
    // A class with a head pointer to itself indicates that the representative for this set is it's own value.
    private UnionFindNode head;
    private E value;
    private Integer rank = 1;
    private boolean representative = false;

    public UnionFindNode(E value) {
        this.value = value;
        this.head = this;
    }

    public UnionFindNode(E value, int rank) {
        this.value = value;
        this.head = this;
        this.representative = true;
        this.rank = rank;
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

    public int hashCode(){
        return value.hashCode();
    }



    public int compareTo(UnionFindNode o) {
        return this.rank > o.getRank() ? 1 : this.rank < o.getRank() ? -1 : 0;
    }




    public String toString(){
        if(representative){
            return "Class: " + head.getValue().toString() + "\nRank: " + this.rank;
        }
        return "\nNode:  " + value.toString() + "\nClass: " + head.getValue().toString() + "\nRank: " + this.rank;
    }
}


public class UnionFind <E extends Comparable>{

    private UnionFindNode sets[];

    // Retrieve indices that were assigned to generic type objects.
    private HashMap<E, Integer> indices = new HashMap<>();

    // We need to be able to tell how many elements have already been added to our UnionFind instance.
    private Integer counter = 0;
    private Integer numElements = null;

    private boolean finalized = false;
    private UnionFindNode<Integer> finalHeads[];

    public UnionFind(){
        // none
    }

    // Specifies the maximum size of the elements contained within UnionFind.
    public void uandf(int n){
        sets = new UnionFindNode[n];
        numElements = n;
    }

    public void make_set(E i) throws UnionFindException{
        if(!finalized){

            // We can only add as many elements as was specified in uandf; therefore, we check both that we still have
            // space to add an element, and also that uandf has already been called.
            if(numElements == null || counter >= numElements){
                throw new UnionFindException("Invalid operation: check initialization function uandf and number of existing sets.");
            }
            else{
                indices.put(i, counter);
                sets[counter++] = new UnionFindNode<>(i);
            }
        }
    }

    public void union_sets(E i, E j) throws UnionFindException {
        if(!finalized){

            // First need to check that each of these elements have been added to the UnionFind structure.
            Integer indexI = indices.get(i);
            Integer indexJ = indices.get(j);
            if(indexI == null || indexJ == null){
                throw new UnionFindException("One or both of the elements was not contained in the collection.");
            }

            // Retrieve the representatives from each of the sets contained i and j
            UnionFindNode iHead = getVeryHead(sets[indexI]);
            UnionFindNode jHead = getVeryHead(sets[indexJ]);

            // If they are already in the same set, don't union.
            if(iHead == jHead){
                return;
            }

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

    private UnionFindNode getVeryHead(UnionFindNode n){

        // Keep track of the path up to the head so we can do compression later.
        Stack<UnionFindNode> path = new Stack<>();

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

    public UnionFindNode find_set(E i) throws UnionFindException{

        // Need to ensure that i has been added to our UnionFind structure.
        Integer index = indices.get(i);
        if(index == null){
            throw new UnionFindException("Element was not contained in the collection.");
        }
        return getVeryHead(sets[index]);
    }

    public int final_sets(){

        // We finalize the union-find such that no more changes can occur.
        finalized = true;
        HashMap<UnionFindNode, ArrayList<UnionFindNode>> foundSets = new HashMap<>();

        // Determining how manny different sets there are
        for (UnionFindNode e : sets) {

            // Sets does not necessarily need to be full, this avoids a NullPointerException.
            if (e == null) {
                break;
            }

            // We use the representative from each node, inspecting if it has already been seen,
            // if so we add our element to the representatives list of children, otherwise we have found
            // a new representative and create a new list for it's children.
            UnionFindNode head = getVeryHead(e);
            if (!foundSets.containsKey(head)) {
                foundSets.put(head, new ArrayList<>());
            }
            foundSets.get(head).add(e);
        }
        int numSets = foundSets.size();

        // We need to go through each set and reset their representatives to be integers.
        int i = numSets;
        finalHeads = new UnionFindNode[numSets];
        for (UnionFindNode key : foundSets.keySet()) {
            UnionFindNode<Integer> newRepresentative = new UnionFindNode<>(i--, key.getRank());

            // We keep track of all representatives such that we can retrieve them later.
            finalHeads[i] = newRepresentative;
            for (UnionFindNode val : foundSets.get(key)) {
                val.setHead(newRepresentative);
            }
        }

        // Finally return the number of unique sets that was found earlier.
        return numSets;
    }

    public UnionFindNode<Integer>[] getFinalHeads() {
        return finalHeads;
    }

    public String toString(){
        String toReturn = "";
        for(UnionFindNode e: sets){
            if(e == null){
                break;
            }
            toReturn += e.toString() + "\n";
        }

        return toReturn;
    }


    public static void main(String[] args) {
        UnionFind<Integer> test = new UnionFind<>();
        try{
            test.uandf(6);
            test.make_set(1);
            test.make_set(2);
            test.make_set(3);
            test.make_set(25);
            test.make_set(26);

            test.union_sets(25, 26);
            test.union_sets(1, 2);
            test.union_sets(3, 25);
            test.final_sets();
            test.final_sets();
            System.out.println(test);

            System.out.println(test.find_set(25));
        } catch (UnionFindException e){
            e.printStackTrace();
        }
    }
}
