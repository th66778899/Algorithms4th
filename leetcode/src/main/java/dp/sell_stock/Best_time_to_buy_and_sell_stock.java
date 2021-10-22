package dp.sell_stock;

import org.junit.jupiter.api.Test;

public class Best_time_to_buy_and_sell_stock {
}
// 简单 动态规划 - 股票问题
class Solution121 {
    public int maxProfit(int[] prices) {
        // dp[i][0] 表示第i天持有股票所获得的金钱
        // dp[i][1] 表示第i天不持有股票所获得的金钱
        // 递推公式
        // dp[i][0] 前一天已经持有股票 dp[i][0] = dp[i-1][0]
        // dp[i][0] 前一天没有持有股票 dp[i][0] = -prices[i] 今天购入股票

        // dp[i][1] 前一天持有股票 今天卖出 dp[i][1] = dp[i-1][1] + prices[i]
        // dp[i][1] 前一天未持有股票        dp[i][1] = dp[i-1][1];
        // 初始化 dp[i][0] 需要初始化 dp[0][0]
        //        dp[i][1] 需要初始化 dp[0][1]
        int[][] dp = new int[2][2];
        // 滚动数组 类似一个环
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i <prices.length ; i++) {
            dp[i % 2][0] = Math.max(dp[ (i - 1) % 2 ][0],-prices[i]);
            dp[i%2][1] = Math.max(dp[ (i - 1 ) % 2 ][1],dp[ (i - 1) % 2][0] + prices[i]);
        }
        /*for (int i = 0; i <2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }*/
        return dp[(prices.length-1) % 2][1];

    }
    @Test
    void test1(){
        new Solution121().maxProfit(new int[]{7,1,5,3,6,4});

    }
}
// leetcode 中等 动态规划-股票问题 可以买卖k次
class Solution122 {
    public int maxProfit(int[] prices) {
        // 动态规划
        // dp数组含义与上一道题没有区别
        // 主要区别在于递推公式
        // dp[i][0] 情况1.前一天未持有股票 因为可以交易多次,之前可能已经有了股票的利润 dp[i][0] = dp[i-1][1] - prices[i]
        //           情况2.前一天持有股票 dp[i][0] = dp[i-1][0]
        // dp[i][1] 情况1.前一天未持有股票 dp[i][1] = dp[i-1][1]
        //           情况2.前一天持有股票 dp[i][1] = dp[i-1][0] + prices[i]
        int length = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i%2][0] = Math.max(dp[ ( i - 1 ) % 2 ][0],dp[( i - 1 ) % 2][1] - prices[i]);
            dp[i%2][1] = Math.max(dp[( i - 1 ) % 2][1],dp[( i - 1 ) % 2][0] + prices[i]);
        }
        /*for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }*/

        // 贪心思路
        /*int sum = 0;
        int profit = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = prices[i] - buy;
            if (profit > 0) {
                sum += profit;
            }
            buy = prices[i];
        }
        return sum;*/
        return dp[(length- 1)%2][1];
    }
    @Test
    void test2(){
        new Solution122().maxProfit(new int[]{7,1,5,3,6,4});
    }
}
// leetcode 困难 动态规划-股票问题 只能买入2次
class Solution123 {
    public int maxProfit(int[] prices) {
        // 对每一天 共有五种状态
        // 1.不操作 2.第一次持有 3.第一次卖出 4.第二次持有 5.第二次卖出
        int length = prices.length;
        int[][] dp = new int[length][5];
        // dp[i][1] 第一次持有 该第一次是对于这种状态下来说的 而不是全局情况下的第一次持有
        // dp[i][1] = dp[i-1][0] - prices[i] 第一次买入
        // dp[i][1] = dp[i-1][1]    持有股票但没有操作

        // dp[i][2] 第i天 未持有股票 可能已经卖出 dp[i][2] = dp[i-1][1] + prices[i]
        // dp[i][2] 未持有股票 可能还没入买入股票 dp[i][2] = dp[i-1][2]
        // dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2] - prices[i])
        // dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3] + prices[i])
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i][1]  + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4],dp[i][3] + prices[i]);
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[length-1][4];
    }

    public static void main(String[] args) {
        new Solution123().maxProfit(new int[]{1,2,3,4,5});
    }
}
// leetcode 188 动态规划-股票问题Ⅳ 可以买卖k次
class Solution188 {
    public int maxProfit(int k, int[] prices) {
        // dp[i][j]
        // k次买卖 共有 2 + 2*k 次状态 1.不操作  + (2.买 3.卖)*k
        if (prices.length == 1 || prices.length == 0){
            return 0;
        }
        int[][] dp = new int[prices.length][2*k+1];
        // 递推公式
        // dp[i][j] j为奇数 买入的情况 dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1] - prices[i])
        // dp[i][j] j为偶数 卖出的情况 dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1] + prices[i])
        // 初始化 dp[0][0] = 0; dp[0][j] = -prices[0] j为奇数
        for (int i = 1; i < 2*k+1; i+=2) {
            dp[0][i] = -prices[0];
        }
        dp[0][0] = 0;
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < 2*k-1; j+=2) {
                dp[i][j+1] = Math.max(dp[i-1][j+1],dp[i-1][j] - prices[i]);
                dp[i][j+2] = Math.max(dp[i-1][j+2],dp[i-1][j+1] + prices[i]);
            }
        }
        /*for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2*k+1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/
        return dp[length-1][2*k];

    }

    public static void main(String[] args) {
        new Solution188().maxProfit(2,new int[]{1,2,3,4,5});
    }
}
// leetcode 中等 动态规划-股票问题 有冷冻期
class Solution309 {
    public int maxProfit(int[] prices) {
        // 共分为4个状态
        // 1.买 2.卖(2.1很早就卖了 度过了冷冻期 保持卖的状态 2.2今天卖) 4.冷冻期
        int length = prices.length;
        if (length == 0 || length == 1){
            return 0;
        }
        int[][] dp = new int[length][4];
        // 递推公式                  -之前已经买入(状态1)  -还没买入,过了冷冻期(状态2)    -还没买入,前一天是冷冻期(状态4)
        // 0.状态1 买入股票 dp[i][0]   dp[i-1][0]          dp[i-1][1] - prices[i]        dp[i-1][3] - prices[i]
        //                          -已经卖出,前一天是冷冻期(状态4)
        // 1.状态2 已经卖出股票,且过了冷冻期 dp[i-1][3] dp[i-1][1]
        // 2.状态3 今天卖出股票 dp[i-1][0] + prices[i]
        // 3.状态4 今天是冷冻期,前一天卖出了股票 dp[i-1][2]
        // 初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i-1][0],Math.max(dp[i-1][1] - prices[i] , dp[i-1][3] - prices[i]));
            dp[i][1] = Math.max(dp[i-1][3] ,dp[i-1][1]);
            dp[i][2] = dp[i-1][0] + prices[i];
            dp[i][3] = dp[i-1][2];
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }
        return Math.max(dp[length-1][1],Math.max(dp[length-1][2],dp[length-1][3]));
    }

    public static void main(String[] args) {
        new Solution309().maxProfit(new int[]{1,2,3,0,2});
    }
}

// leetcode 714 动态规划-股票问题 含手续费
class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int length = prices.length;
        int[][] dp = new int[length][2];// 两种状态 买入股票,卖出股票
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1 ; i < length; i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] -prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] + prices[i] - fee);
        }
        /*for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }*/
        return dp[length-1][1];



        // 贪心思路
        /*int buy = prices[0] + fee;
        int sum = 0;
        for (int p : prices) {
            if (p + fee < buy) {
                buy = p + fee;
            } else if (p > buy){
                sum += p - buy;
                buy = p;
            }
        }
        return sum;*/
    }

    public static void main(String[] args) {
        new Solution714().maxProfit(new int[]{1, 3, 2, 8, 4, 9},2);
    }
}