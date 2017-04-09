
import java.lang.Math;

class HasKeyItem extends HasKey{

    private int weight;
    private int val;
    private String itemKey;

    public HasKeyItem(String itemKey,  int val, int weight){
        super(-1);
        this.itemKey = itemKey;
        this.weight = weight;
        this.val = val;
    }

    public HasKeyItem(String itemKey,  int val, int weight, int key){
        super(key);
        this.itemKey = itemKey;
        this.weight = weight;
        this.val = val;
    }

    public String getItemKey(){
        return this.itemKey;
    }

    public int getWeight() {
        return weight;
    }

    public int getVal() {
        return val;
    }

    public String toString(){
        return this.itemKey;
    }
}

public class DPKnapsack {

    public static <E extends HasKeyItem> int solveKnapsack(E[] items, int W){
        int[][] memo = new int[items.length][W];

        // Work from left to right, up to down, with each row representing taking an item
        for(int i = 1; i < items.length; i++){
            for(int w = 1; w < W; w++){

                // If we have less weight we can take the item
                if(items[i].getWeight() > w){
                    memo[i][w] = memo[i - 1][w];
                }

                // Otherwise we look if taking this item, and then taking the items leftover with the weight left (will have already been computed),
                // is greater than if we just don't take it and do the same as the row above.
                else {
                    memo[i][w] = Math.max(items[i].getVal() + memo[i - 1][w - items[i].getWeight()], memo[i - 1][w]);
                }
            }
        }

        // Since we propagate our numbers to the bottom left, we can just take he last value as the max.
        return memo[items.length - 1][W - 1];

    }


    public static void main(String[] args) {
        HasKeyItem a = new HasKeyItem("a", 1, 3);
        HasKeyItem b = new HasKeyItem("b", 2, 6);
        HasKeyItem c = new HasKeyItem("c", 8, 1);
        HasKeyItem d = new HasKeyItem("d", 7, 20);

        HasKeyItem[] itemList = {a, b, c, d};

        System.out.println(solveKnapsack(itemList, 9));

    }
}
