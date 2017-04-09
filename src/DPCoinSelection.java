import java.util.ArrayList;

public class DPCoinSelection {


    public static boolean solveCoin(ArrayList<Integer> coins, int totalVal){
        for(int curVal = 0; curVal <= totalVal; curVal++){
            for(int coin = coins.size() - 1; coin > 0; coin--){
                if(coins.contains(curVal - coins.get(coin))){
                    coins.add(curVal);
                    break;
                }
            }
        }
        return coins.contains(totalVal);
    }


    public static void printCoins(ArrayList<Integer> coins, ArrayList<Integer> coinsMemo, int totalVal){
        while(totalVal > 0){
            for(int coin = coins.size() - 1; coin >= 0; coin--){
                int cur = totalVal - coins.get(coin);
                if(cur >= 0 && (coinsMemo.contains(cur) || cur == 0)){
                    System.out.println(coins.get(coin));
                    totalVal -= coins.get(coin);
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> coins = new ArrayList<>();
        coins.add(5);
        coins.add(10);
        coins.add(25);
        coins.add(100);

        int val = 280;

        ArrayList<Integer> coinsCopy = (ArrayList)coins.clone();

        if(solveCoin(coinsCopy, val)){
            printCoins(coins, coinsCopy, val);
        }

    }
}
