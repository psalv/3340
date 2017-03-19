
public class LongestCommonSubsequence {

    public static String LCS(String x, String y){

        int longestSequence = 0;
        int longestIndexInX = -1;

        // Initializing memo
        int[][] memo = new int[x.length() + 1][y.length() + 1];
        for(int i = 0; i < x.length(); i++){
            memo[i][0] = 0;
        }
        for(int j = 0; j < y.length(); j++){
            memo[0][j] = 0;
        }

        for(int i = 1; i < x.length() + 1; i++){

            for(int j = 1; j < y.length(); j++){

                if(x.charAt(i - 1) == y.charAt(j - 1)){


                    int temp = 1 + memo[i - 1][j - 1];
                    if(temp > longestSequence){
                        longestSequence = temp;
                        longestIndexInX = i;
                    }

                    memo[i][j] = temp;
                }
            }
        }

        if(longestIndexInX == -1){
            System.out.println("No subsequence match found.");
            return null;
        }
        else{
            System.out.println("Longest subsequence of size: " + longestSequence);
        }

        String buildLongest = "";
        for(int c = 0; c < longestSequence; c++){
            buildLongest += x.charAt(longestIndexInX - longestSequence + c);
        }

        System.out.println("Longest subsequence: " + buildLongest);

        return buildLongest;
    }

    public static void main(String[] args) {
        LCS("skullandbones", "lullabybabies");
    }
}
