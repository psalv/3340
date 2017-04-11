import java.util.Arrays;



public class SelectKSmallest {

    public static int[] intMergeSort(int[] ar){
        if(ar.length <= 1){
            return ar;
        }
        int[] ar1 = intMergeSort(Arrays.copyOfRange(ar, 0, ar.length/2));
        int[] ar2 = intMergeSort(Arrays.copyOfRange(ar, ar.length/2, ar.length));

        return merge(ar1, ar2);
    }

    public static int[] merge(int[] ar1, int[] ar2){
        int[] ar3 = new int[ar1.length + ar2.length];
        int in1 = 0;
        int in2 = 0;
        int in3 = 0;
        while(true) {
            if(in1 == ar1.length){
                while(in2 < ar2.length){
                    ar3[in3++] = ar2[in2++];
                }
                return ar3;
            }
            if(in2 == ar2.length){
                while(in1 < ar1.length){
                    ar3[in3++] = ar1[in1++];
                }
                return ar3;
            }
            if (ar1[in1] < ar2[in2]) {
                ar3[in3++] = ar1[in1++];
            } else {
                ar3[in3++] = ar2[in2++];
            }
        }
    }

    public static int fiveMedian(int[] S, int pos){
        int a = Math.max(Math.min(S[pos + 0], S[pos + 1]), Math.min(S[pos + 2], S[pos + 3]));
        int b = Math.min(Math.max(S[pos + 0], S[pos + 1]), Math.max(S[pos + 2], S[pos + 3]));

        return Math.max(Math.min(a, b), Math.min(S[pos + 4], Math.max(a, b)));
    }

    public static int medianOfMedians(int[] S){
        if(S.length < 5){
            S = intMergeSort(S);
            return S[S.length/2];
        }

        int numFives = ((Double)Math.floor(S.length/5)).intValue();
        int[] five = new int[numFives];
        int pos = 0;
        for(int i = 0; i < numFives; i++){
            five[i] = fiveMedian(S, pos);
            pos += 5;
        }

        return medianOfMedians(five);
    }

    public static int selectKSmallest(int[] S, int k){
        int med = medianOfMedians(S);
        int numBelow = 0;
        for(int s: S){
            if(s <= med){
                numBelow++;
            }
        }
        int[] s1 = new int[numBelow];
        int[] s2 = new int[S.length - numBelow];
        int s1Pos = 0;
        int s2Pos = 0;
        for(int s: S){
            if(s <= med){
                s1[s1Pos++] = s;
            } else {
                s2[s2Pos++] = s;
            }
        }

        if(numBelow == k){
            return med;
        } else if (numBelow > k){
            return selectKSmallest(s1, k);
        } else {
            return selectKSmallest(s2, k - numBelow);
        }

    }


    //todo why do some numbers not work?
    public static void main(String[] args) {
        int[] input = {32, 45, 1, 3, 21, 321, -32, 45, 231, 433, 8, 77, 543, 2, 23, 3, 9042, -2192, 43, 123, 77};

        System.out.println(Arrays.toString(intMergeSort(input)));
        for(int i = 0; i<input.length; i++){
            System.out.println(selectKSmallest(input, i));
        }

    }
}
