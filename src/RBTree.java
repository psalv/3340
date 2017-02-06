
class RBNode{
    public static enum COLOR {RED, BLACK}

    private COLOR color = COLOR.BLACK;
    private Integer value;
    private  RBNode leftChild;
    private  RBNode rightChild;
    private RBNode parent;

    public RBNode(){
        // none
    }

    public RBNode(RBNode parent){
        this.parent = parent;
    }

    public RBNode(COLOR color, Integer value, RBNode leftChild, RBNode rightChild, RBNode parent) {
        this.color = color;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public RBNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBNode leftChild) {
        this.leftChild = leftChild;
    }

    public RBNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBNode rightChild) {
        this.rightChild = rightChild;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public String toString(){
        return value == null ? "NULL" : value.toString();
    }
}


public class RBTree {

    private RBNode root = new RBNode();

    public RBNode find(Integer val){
        if(root.getValue() == null || root.getValue().equals(val)){
            return root;
        }
        else {
            return root.getValue() > val ? find(val, root.getLeftChild()) : find(val, root.getRightChild());
        }
    }

    public RBNode find(Integer val, RBNode cur){
        if(cur.getValue() == null || cur.getValue().equals(val)){
            return cur;
        }
        else {
            return cur.getValue() > val ? find(val, cur.getLeftChild()) : find(val, cur.getRightChild());
        }
    }

    public int BH(RBNode cur){
        if(cur.getParent() == null){
            return cur.getColor() == RBNode.COLOR.RED ? 0 : 1;
        }
        else{
            return cur.getColor() == RBNode.COLOR.RED ? BH(cur.getParent()) : 1 + BH(cur.getParent());
        }
    }

    public boolean isLeft(RBNode n){
        return n.getParent().getLeftChild() == n;
    }

    public RBNode opp(RBNode n){
        return n.getParent() == null ? null : isLeft(n) ? n.getParent().getRightChild() : n.getParent().getLeftChild();
    }

    public void redPropogation(RBNode cur){
        if (cur == null || cur.getParent() == null || cur.getParent().getColor() == RBNode.COLOR.BLACK){
            return;
        }
        cur.setColor(RBNode.COLOR.RED);
        cur.getParent().setColor(RBNode.COLOR.BLACK);
        opp(cur.getParent()).setColor(RBNode.COLOR.BLACK);
        cur.getParent().getParent().setColor(RBNode.COLOR.RED);

        redPropogation(cur.getParent().getParent());
    }

    public void insert(Integer val){
        RBNode pos = find(val);

        // If we are just starting the tree, we add at the root.
        if (pos.getParent() == null){
            root = new RBNode(RBNode.COLOR.BLACK, val, new RBNode(), new RBNode(), null);
            root.setLeftChild(new RBNode(root));
            root.setRightChild(new RBNode(root));
        }
        else{

            // Our find method returns a null node, we therefore need to set the value
            pos.setValue(val);
            pos.setLeftChild(new RBNode(pos));
            pos.setRightChild(new RBNode(pos));

            // First we check to see if the parent is red, if not then we can make the new node red, therein not changing the BH
            if(pos.getParent().getColor() != RBNode.COLOR.RED){
                pos.setColor(RBNode.COLOR.RED);
            }
            else{

                RBNode parentsSibling = opp(pos.getParent());

                if (parentsSibling.getColor() == RBNode.COLOR.RED){
                    redPropogation(pos);
                }
            }
        }
    }

    public void inOrder(){
        print(root.getLeftChild());
        System.out.println("\nVal: " + root + "\nBH: " + BH(root) + "\nColor: " + root.getColor());
        print(root.getRightChild());
    }

    public void print(RBNode cur){
        if(cur.getValue() == null){
            return;
        }

        print(cur.getLeftChild());
        System.out.println("\nVal: " + cur + "\nBH: " + BH(cur) + "\nColor: " + cur.getColor());
        print(cur.getRightChild());
    }



    public static void main(String[] args) {
        RBTree test = new RBTree();
        test.insert(8);
        test.insert(6);
        test.insert(9);
        test.insert(4);
        test.insert(7);
//        test.insert(3);
        test.inOrder();
    }
}
