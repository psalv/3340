
class HasKey{
    private Integer key;

    public HasKey(int key) {
        this.key = key;
    }

    public int getKey(){
        return this.key;
    }

    public String toString(){
        return key.toString();
    }
}