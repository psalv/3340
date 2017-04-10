
class HasKey{
    private Integer intKey;

    public HasKey(Integer intKey) {
        this.intKey = intKey;
    }

    public int getIntKey(){
        return this.intKey;
    }

    public void setIntKey(Integer intKey) {
        this.intKey = intKey;
    }

    public String toString(){
        return intKey.toString();
    }
}