import java.util.ArrayList;

public class MinHeap<E extends HasKey> {

    private ArrayList<E> heap = new ArrayList<>();

    public MinHeap(){
        // no initialization data.
    }

    // Takes O(nlgn) time.
    public MinHeap(E data[]){
        for(E i: data){
            insert(i);
        }
    }

    // Takes O(lgn) time.
    public void insert(E data){
        heap.add(data);
        buildMinHeapify(heap.size() - 1);
    }

    // Takes O(lgn) time since we can look at each parent until we reach the root.
    private void buildMinHeapify(int i){
        if(i == 0){
            return;
        }

        // Determining the parent.
        int adjust = i % 2 == 0 ? 1 : 0;
        int parent = (int)Math.floor(i/2.0) - adjust;

        // Since we assume that the current min heap maintains the min heap property,
        // we need only look at the parent.
        // If the new node is less than the parent, than it is also less than any of the children's parents.
        // We can therefore swap the parent and the new node.
        if(heap.get(parent).getKey() > heap.get(i).getKey()){
            E temp = heap.get(parent);
            heap.set(parent, heap.get(i));
            heap.set(i, temp);

            // We do this until we reach the root, or until our node is in the correct place (parent is smaller).
            buildMinHeapify(parent);
        }
    }

    // Takes O(lgn) time.
    private void minHeapify(int i){
        int left = 2*i + 1;
        int right = left + 2;
        int size = heap.size() - 1;

        if(left > size){
            return;
        }

        // Need to determine the smallest element between i and it's two children
        int smallest = heap.get(left).getKey() < heap.get(i).getKey() ? left : i;
        smallest = heap.get(left).getKey() < heap.get(smallest).getKey() ? right : smallest;

        // If the children are smaller we need to swap and recurse upwards.
        if(smallest != i){
            E temp = heap.get(smallest);
            heap.set(smallest, heap.get(i));
            heap.set(i, temp);

            minHeapify(smallest);
        }
    }

    // To extract the min it takes O(lgn) time since we need to min-heapify the root.
    public E extractMin(){
        int last = heap.size() - 1;

        // Swap with min with the end, remove the end, and min-heapify the root.
        E min = heap.get(0);
        heap.set(0, heap.get(last));
        heap.set(last, min);
        heap.remove(last);

        minHeapify(0);
        return min;
    }

    // Viewing the min takes O(1).
    public E getMin(){
        return heap.get(0);
    }

    public String toString(){
        return heap.toString();
    }



    public static void main(String[] args) {
        HasKey data[] = {new HasKey(532),
                new HasKey(3),
                new HasKey(12),
                new HasKey(1592),
                new HasKey(531),
                new HasKey(52),
                new HasKey(1),
                new HasKey(902),
                new HasKey(5222),
                new HasKey(67)
        };

        // A few tests.
        MinHeap<HasKey> test = new MinHeap(data);
        System.out.println(test);
        System.out.println(test.extractMin());
        System.out.println(test);
        test.insert(new HasKey(-39));
        System.out.println(test);

    }
}
