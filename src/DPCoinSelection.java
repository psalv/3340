
// todo: implement with a generic class

public class DPCoinSelection {


    public static boolean solveCoin(int[] coins, int totalVal){
        int[] memo = new int[totalVal + 1];
        memo[0] = 1;
        for(int curVal = 1; curVal <= totalVal; curVal++){
            for(int coin = coins.length - 1; coin >= 0; coin--){
                try {
                    if (memo[curVal - coins[coin]] == 1) {
                        memo[curVal] = 1;
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    // none
                }
            }
        }
        if(memo[totalVal] == 1){
            printCoins(coins, memo, totalVal);
            return true;
        }

        System.out.println("No solution possible.");
        return false;
    }


    public static void printCoins(int[] coins, int[] coinsMemo, int totalVal){
        while(totalVal > 0){
            for(int coin = coins.length - 1; coin >= 0; coin--){
                try {
                    int cur = totalVal - coins[coin];
                    if (cur >= 0 && (coinsMemo[cur] == 1 || cur == 0)) {
                        System.out.println(coins[coin]);
                        totalVal -= coins[coin];
                        break;
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    // none
                }
            }
        }
    }


    public static void main(String[] args) {

        int val = 280;
        int[] coins = {5, 10, 25, 100};

        solveCoin(coins, val);

    }
}
