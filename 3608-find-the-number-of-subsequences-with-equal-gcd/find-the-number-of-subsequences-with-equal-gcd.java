class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1000000007;
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }
        
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;
        
        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int i = 0; i <= maxVal; i++) {
                for (int j = 0; j <= maxVal; j++) {
                    nextDp[i][j] = dp[i][j];
                }
            }
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] > 0) {
                        int v = dp[g1][g2];
                        
                        int ng1 = (g1 == 0) ? x : gcd(g1, x);
                        nextDp[ng1][g2] = (nextDp[ng1][g2] + v) % MOD;
                        
                        int ng2 = (g2 == 0) ? x : gcd(g2, x);
                        nextDp[g1][ng2] = (nextDp[g1][ng2] + v) % MOD;
                    }
                }
            }
            dp = nextDp;
        }
        
        int ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        
        return ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
