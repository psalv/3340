import java.util.ArrayList;
import java.util.Iterator;



public class RadixSort {

    public static <E extends HasKey> void RadixSort(int digits, E data[]) {

        boolean DEBUG = true;

        ArrayList<E> sorted[] = new ArrayList[10];
        ArrayList<E> pointer[];
        boolean first = true;
        for (int i = 0; i < digits; i++) {
            if (first) {
                for (E k : data) {
                    int key = k.getKey() % 10;

                    if (sorted[key] == null) {
                        sorted[key] = new ArrayList<>();
                    }

                    sorted[key].add(k);
                }
                first = false;
            } else {
                pointer = new ArrayList[10];

                for (int j = 0; j < 10; j++) {
                    if (sorted[j] != null) {
                        Iterator<E> iter = sorted[j].iterator();
                        while (iter.hasNext()) {
                            E k = iter.next();
                            int key = (int) (k.getKey() / (Math.pow(10, i))) % 10;

                            if (pointer[key] == null) {
                                pointer[key] = new ArrayList<>();
                            }

                            pointer[key].add(k);
                        }
                    }
                }
                sorted = pointer;
            }
        }

        if (DEBUG) {
            for (int h = 0; h < 10; h++) {
                try {
                    System.out.println(h + " : " + sorted[h].toString());
                } catch (NullPointerException e) {
                }
            }
        }

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

        RadixSort(4, data);
    }
}
