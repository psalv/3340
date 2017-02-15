import java.util.HashMap;


class HasKeyCharacter extends HasKey{
    char value;
    public HasKeyCharacter(int key, char c){
        super(key);
        this.value = c;
    }

    public char getValue() {
        return value;
    }
}


public class HuffmanCodes {

    private HashMap<Character, Integer> frequency = new HashMap<>();

    public HuffmanCodes(String filename){
        load(filename);
    }

    public void load(String filename){

    }

    public void save(){

    }

    public void determineHuffman(){
        MinHeap<HasKey> heap = new MinHeap<>();
        for(Character s: frequency.keySet()){
            heap.insert(new HasKeyCharacter(frequency.get(s), s));
        }
    }

}
