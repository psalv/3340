import java.util.ArrayList;

public class MergeSort {

    public static <E extends Comparable> ArrayList<E> mergeSort(ArrayList<E> array){
        int n = array.size();

        // An empty array is sorted, as is an array of length 1.
        if(n <= 1){
            return array;
        }

        ArrayList<E> new1 = MergeSort.mergeSort(new ArrayList<E>(array.subList(0, n/2)));
        ArrayList<E> new2 = MergeSort.mergeSort(new ArrayList<E>(array.subList(n/2, n)));

        return MergeSort.merge(new1, new2);

    }


    public static <E extends Comparable> ArrayList<E> merge(ArrayList<E> array1, ArrayList<E> array2){
        int len1 = array1.size();
        int len2 = array2.size();
        int i = 0;
        int j = 0;
        int num = 0;
        ArrayList<E> newArray = new ArrayList<E>();

        while(num < len1 + len2){

            if(i == len1){
                newArray.add(array2.get(j++));
                num++;
            }
            else if(j == len2){
                newArray.add(array1.get(i++));
                num++;
            }

            else if(array1.get(i).compareTo(array2.get(j)) == -1){
                newArray.add(array1.get(i++));
                num++;
            }
            else {
                newArray.add(array2.get(j++));
                num++;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        ArrayList<Integer> x = new ArrayList<Integer>() {{
            add(2);
            add(1);
            add(213);
            add(-22);
            add(-21);
        }};
        System.out.println(MergeSort.mergeSort(x));

    }
}
