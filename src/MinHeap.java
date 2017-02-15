import java.util.ArrayList;

class HasKey_{
    private Integer key;

    public HasKey_(int key) {
        this.key = key;
    }

    public int getKey(){
        return this.key;
    }

    public String toString(){
        return key.toString();
    }
}

public class MinHeap {

    private ArrayList<HasKey_> heap = new ArrayList<>();

    public MinHeap(){
        // no initialization data.
    }

    public MinHeap(HasKey_ data[]){
        for(HasKey_ i: data){
            insert(i);
        }
    }

    // Takes O(nlgn) time.
    private void insert(HasKey_ data){
        heap.add(data);
        buildMinHeapify(heap.size() - 1);
    }

     // Takes O(lgn) time since we can look at each parent until we reach the root.
     private void buildMinHeapify(int i){
        if(i == 0){
            return;
        }
        int adjust = i % 2 == 0 ? 1 : 0;
        int parent = (int)Math.floor(i/2.0) - adjust;
        if(heap.get(parent).getKey() > heap.get(i).getKey()){
            HasKey_ temp = heap.get(parent);
            heap.set(parent, heap.get(i));
            heap.set(i, temp);
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

        int smallest = heap.get(left).getKey() < heap.get(i).getKey() ? left : i;
        smallest = heap.get(left).getKey() < heap.get(smallest).getKey() ? right : smallest;

        if(smallest != i){
            HasKey_ temp = heap.get(smallest);
            heap.set(smallest, heap.get(i));
            heap.set(i, temp);

            minHeapify(smallest);
        }
    }

    // To extract the min it takes O(lgn) time since we need to minheapify the root.
    private HasKey_ extractMin(){
        int last = heap.size() - 1;

        HasKey_ min = heap.get(0);
        heap.set(0, heap.get(last));
        heap.set(last, min);
        heap.remove(last);

        minHeapify(0);
        return min;
    }

    // Viewing the min takes O(1).
    private HasKey_ getMin(){
        return heap.get(0);
    }

    public String toString(){
        return heap.toString();
    }



    public static void main(String[] args) {
        HasKey_ data[] = {new HasKey_(532),
                new HasKey_(3),
                new HasKey_(12),
                new HasKey_(1592),
                new HasKey_(531),
                new HasKey_(52),
                new HasKey_(1),
                new HasKey_(902),
                new HasKey_(5222),
                new HasKey_(67)
        };

        // A few tests.
        MinHeap test = new MinHeap(data);
        System.out.println(test);
        System.out.println(test.extractMin());
        System.out.println(test);
        test.insert(new HasKey_(-39));
        System.out.println(test);

    }
}
