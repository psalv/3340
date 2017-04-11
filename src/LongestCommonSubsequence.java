
public class LongestCommonSubsequence {

    public static void printLCS(Integer[][] memo, String st1, String st2){
        int x = st1.length();
        int y = st2.length();
        int index = memo[y - 1][x - 1];
        char[] build = new char[index + 1];
        while(x > 1 && y > 1){
            if(st1.charAt(x - 1) == st2.charAt(y - 1)){
                build[index] = st1.charAt(x-1);
                x--;
                y--;
                index--;
            } else if (memo[x-1][y] > memo[x][y-1]){
                x--;
            } else{
                y--;
            }
        }

        String lcs = "";
        for(char c: build){
            lcs += c;
        }
        System.out.println("\t" + lcs);

    }

    public static void LCS(String x, String y){

        // Initializing memo
        Integer[][] memo = new Integer[x.length() + 1][y.length() + 1];
        for(int i = 0; i <= x.length(); i++){
            memo[i][0] = 0;
        }
        for(int j = 0; j <= y.length(); j++){
            memo[0][j] = 0;
        }

        for(int i = 1; i <= x.length(); i++){

            for(int j = 1; j <= y.length(); j++){

                if(x.charAt(i - 1) == y.charAt(j - 1)){
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }

        System.out.println(String.format("LCS is %d characters long.", memo[memo.length - 1][memo[0].length - 1]));

        printLCS(memo, x, y);

    }

    public static void main(String[] args) {
        LCS("skullandbones", "lullabybabies");
    }

}
