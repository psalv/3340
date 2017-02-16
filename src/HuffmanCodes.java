import java.io.*;
import java.util.HashMap;


class HasKeyCharacter extends HasKey{

    private Character value = null;

    private HasKeyCharacter left = null;
    private HasKeyCharacter right = null;

    public HasKeyCharacter(int key, Character c){
        super(key);
        this.value = c;
    }

    public HasKeyCharacter(int key, HasKeyCharacter left, HasKeyCharacter right){
        super(key);
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    public HasKeyCharacter getLeft() {
        return left;
    }

    public HasKeyCharacter getRight() {
        return right;
    }

    public char getValue() {
        return value;
    }

    public String toString(){
        return value == null ? "JOINT" + getKey() : value.toString() + getKey();
    }
}




public class HuffmanCodes {

    private HashMap<Character, Integer> frequency = new HashMap<>();
    private HashMap<Character, String> encode = new HashMap<>();

    public HuffmanCodes(String filename){

        // First we read in the file.
        load(filename);

        // Then we determine the Huffman code.
        determineHuffman();

        // We use our Huffman code to encode the original text.
        encodeText(filename);
    }

    // Loads in the file and determines the frequency of each character in O(n) time.
    private void load(String filename){

        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));

            try {
                String line;
                while((line = in.readLine()) != null){
                    for(int i = 0; i < line.length(); i++){
                        char cur = line.charAt(i);
                        if(!frequency.containsKey(cur)){
                            frequency.put(cur, 1);
                        }
                        else{
                            frequency.put(cur, frequency.get(cur) + 1);
                        }
                    }
                }

            }
            catch(IOException e){
                System.out.println("File cannot be read.");
            }
            in.close();
        }

        catch (FileNotFoundException ee){
            System.out.println("File " + filename + " not found.");
            System.exit(0);
        }

        catch(IOException e){
            System.out.println("Trouble closing file.");
            System.exit(0);
        }
    }


    private void save(){}


    private void determineHuffman(){

        // Builds a min heap from the frequencies of each character.
        MinHeap<HasKeyCharacter> heap = new MinHeap<>();
        for(Character s: frequency.keySet()){
            heap.insert(new HasKeyCharacter(frequency.get(s), s));
        }

        HasKeyCharacter a;
        HasKeyCharacter b;
        HasKeyCharacter z;
        boolean loop = true;

        // We build the heap into a tree.
        while(!heap.isEmpty() && loop){

            a = heap.extractMin();

            try {
                b = heap.extractMin();

                if(heap.isEmpty()){
                    loop = false;
                }

                z = new HasKeyCharacter(a.getKey() + b.getKey(), a, b);
                heap.insert(z);

            }
            catch(IndexOutOfBoundsException e){
                loop = false;
                z = new HasKeyCharacter(a.getKey(), a, null);
                heap.insert(z);
            }
        }

        // We now can do an in order traversal to display the code.
        inOrder(heap.getMin(), "");
    }


    private void inOrder(HasKeyCharacter c, String code){
        if(c.isLeaf()){
            encode.put(c.getValue(), code);
        }
        else{

            // When we go left we add a 0
            inOrder(c.getLeft(), code + "0");

            // When we go right we add a 1
            inOrder(c.getRight(), code + "1");
        }
    }

    private void encodeText(String filename){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            BufferedWriter out = new BufferedWriter(new FileWriter("encodedOut.txt"));

            // We read each line of the file and write it's code to the file.
            try {
                String line;
                while((line = in.readLine()) != null){
                    for(int i = 0; i < line.length(); i++){
                        out.write(encode.get(line.charAt(i)));
                    }
                }

            }
            catch(IOException e){
                System.out.println("File cannot be read.");
            }
            in.close();
            out.close();

            // We need to save the code so the message can be decoded, we save the code to a file.
            BufferedWriter code = new BufferedWriter(new FileWriter("encode.txt"));
            try {
                for(Character c: encode.keySet()){
                    code.write(c + " " + encode.get(c) + "\n");
                }
            }
            catch(IOException e){
                System.out.println("File cannot be read.");
            }
            code.close();
        }

        catch (FileNotFoundException ee){
            System.out.println("File " + filename + " not found.");
            System.exit(0);
        }

        catch(IOException e){
            System.out.println("Trouble closing file.");
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        new HuffmanCodes("words.txt");
    }

}