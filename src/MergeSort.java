import java.util.ArrayList;

public class MergeSort {

    public static <E extends Comparable> ArrayList<E> mergeSort(ArrayList<E> array){
        int arraySize = array.size();

        // An empty array is sorted, as is an array of length 1.
        if(arraySize <= 1){
            return array;
        }

        ArrayList<E> sortedArray1 = MergeSort.mergeSort(new ArrayList<E>(array.subList(0, arraySize/2)));
        ArrayList<E> sortedArray2 = MergeSort.mergeSort(new ArrayList<E>(array.subList(arraySize/2, arraySize)));

        return MergeSort.merge(sortedArray1, sortedArray2);

    }


    // Merge simply merges to already sorted lists.
    public static <E extends Comparable> ArrayList<E> merge(ArrayList<E> sortedArray1, ArrayList<E> sortedArray2){

        // We know we are finished an array when our position matches it's size (have examined every element in the array)
        int lenArray1 = sortedArray1.size();
        int lenArray2 = sortedArray2.size();

        // Keep track of our positions within each input array.
        int i = 0;
        int j = 0;
        int num = 0;
        ArrayList<E> mergedArray = new ArrayList<>();

        while(num < lenArray1 + lenArray2){

            // If we've looked at every element in sortedArray1, add an element from sortedArray2
            if(i == lenArray1){
                mergedArray.add(sortedArray2.get(j++));
                num++;
            }

            // If we've looked at every element in sortedArray2, add an element from sortedArray1
            else if(j == lenArray2){
                mergedArray.add(sortedArray1.get(i++));
                num++;
            }

            // If the next position in sortedArray1 is less than the next position in array 2, add the next position in sortedArray1
            else if(sortedArray1.get(i).compareTo(sortedArray2.get(j)) == -1){
                mergedArray.add(sortedArray1.get(i++));
                num++;
            }

            // Otherwise add the next position from sortedArray2
            else {
                mergedArray.add(sortedArray2.get(j++));
                num++;
            }
        }
        return mergedArray;
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
