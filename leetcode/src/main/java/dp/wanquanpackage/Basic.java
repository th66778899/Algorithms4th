package dp.wanquanpackage;

import java.util.Arrays;
import java.util.List;

// 完全背包问题
// 物品可以无限次放入背包
public class Basic {
    //先遍历物品，再遍历背包
    private static void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++){
            for (int j = 1; j <= bagWeight; j++){
                if (j - weight[i] >= 0){
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        for (int maxValue : dp){
            System.out.print(maxValue + " ");
        }
    }

    //先遍历背包，再遍历物品
    private static void testCompletePackAnotherWay(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 1; i <= bagWeight; i++){
            for (int j = 0; j < weight.length; j++){
                if (i - weight[j] >= 0){
                    dp[i] = Math.max(dp[i], dp[i - weight[j]] + value[j]);
                }
            }
        }
        for (int maxValue : dp){
            System.out.print(maxValue + "  ");
        }
    }

    public static void main(String[] args) {
        testCompletePackAnotherWay();
    }
}
// leetcode 中等 完全背包
class Solution518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        // dp数组含义:dp[j] 表示凑出总额为j的硬币组合有几种
        // 递推公式  dp[j] = dp[j] + dp[j-amount[i]] + 1;
        // 初始化 硬币金额 全部 >= 0 初始化为0即可
        dp[0] = 1;
        // 排列
        for (int j = 0; j <= amount; j++) { // 遍历背包容量
            for (int i = 0; i < coins.length; i++) { // 遍历物品
                if (j - coins[i] >= 0) dp[j] += dp[j - coins[i]];
            }
        }
        // 组合
        /*for (int i = 0; i < coins.length; i++){
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }*/
        for (int i = 0; i <= amount; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        new Solution518().change(4,new int[]{1,2,3});
    }
}
// leetcode 322 中等 完全背包
class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        // dp数组含义 dp[i] 表示 硬币凑出i 共需要的硬币数
        // 递推公式 dp[j] = Math.max(dp[j],dp[j-coins[i]] + 1);
        // 初始化

        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        // 组合问题 先遍历物品,再遍历背包
        for (int i = coins.length-1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j],dp[j-coins[i]] + 1);
                }
            }
        }
        /*for (int i = 0; i < amount+1; i++) {
            System.out.print(dp[i]+ " ");
        }*/
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        new Solution322().coinChange(new int[]{2},3);
    }
}
// leetcode 279 中等 完全背包
class Solution279 {
    public int numSquares(int n) {


        //int length = (int)Math.sqrt(n);
        // 物品数组
        /*int[] num = new int[length+1];
        for (int i = 0; i <=length; i++) {
            num[i] = i;
        }*/
        // dp数组定义 dp[j] j所需要的平方数的最小个数
        // 递推公式 dp[j] = Math.min(dp[j],dp[j - num[i]] + 1);
        // 初始化 dp[0] = 0,其它 最大值 防止之后的结果被初始值覆盖
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; i++) {
            dp[i] = max;
        }
        dp[0] = 0;
        // 先遍历物品 再遍历 背包 组合

        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <=n ; j++) {
                if (dp[j - i * i] !=max){
                    dp[j] = Math.min(dp[j],dp[j - i * i] + 1);
                    /*if (dp[j] < dp[j - i * i] + 1){
                        dp[j] = dp[j];
                    }else {
                        dp[j] = dp[j - i * i] + 1;
                    }*/
                   // dp[j] = dp[j] < dp[j - i * i] + 1 ? dp[j] : dp[j - i * i] + 1;

                }
            }
        }
        /*for (int i = 0; i <=n; i++) {
            System.out.print(dp[i] + " ");
        }*/
        return dp[n];
    }

    public static void main(String[] args) {
        new Solution279().numSquares(13);
    }
}
// leetcode 139 中等 完全背包
class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] valid = new boolean[s.length() + 1];
        valid[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j,i)) && valid[j]) {
                    valid[i] = true;
                }
            }
        }

        return valid[s.length()];
    }
}