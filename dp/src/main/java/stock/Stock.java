package stock;

/**
 * @Author tho
 * @Date 2021/12/22/10:26
 * @ProjectName Algorithms4th
 * @ClassName: Stock
 * @Description: dp - 股票问题
 */
public class Stock {
}
class Solution121 {
    // 动规解法
     public int maxProfit(int[] prices) {
        int length = prices.length;
        // dp[i][0] 表示第 i + 1 天卖出股票的利润
        // dp[i][1] 表示第 i + 1 天持有股票的利润
         int[][] dp = new int[length][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            // 卖出股票
            dp[i][0] = Math.max(prices[i] + dp[i - 1][1], dp[i - 1][0]);
            // 买入股票
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        if (dp[length - 1][0] < 0) {
            return 0;
        }
        return dp[length - 1][0];
    }
    // 贪心解法
    /*public int maxProfit(int[] prices) {
        int length = prices.length;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < length; i++) {
            min = Math.min(min, prices[i]);
            res = Math.max(prices[i] - min, res);
        }
        return res;
    }*/


    public static void main(String[] args) {
        System.out.println(new Solution121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
class Solution122 {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        // dp[i][0] 表示第 i + 1 天不持有股票的利润
        // dp[i][1] 表示第 i + 1 天持有股票的利润
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            // 第 i 天 不持有股票的情况下 1. 今天卖了股票 2.之前就不持有股票
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            // 第 i 天 持有股票 1.今天买入股票 2.之前就有股票
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][0];
    }
}
class Solution123 {
    public int maxProfit(int[] prices) {
        // 每天的操作有5个状态
        // 0.没有操作
        // 1.第一次买入
        // 2.第一次卖出
        // 3.第二次买入
        // 4.第二次卖出
        int length = prices.length;
        int[][] dp = new int[length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp[i][0] = dp[i - 1][0];
            // 第一次买入 1.今天买入股票 2.之前已经买入了股票
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            // 第一次卖出 1.今天卖出  2.已经卖出了股票
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[length - 1][4];
    }
}
class Solution188 {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2 * k + 1];
        dp[0][0] = 0;
        for (int i = 1; i < 2 * k; i += 2) {
            dp[0][i] = -prices[0];
        }
        // k为奇数 表示买入
        // k为偶数 表示卖出
        for (int i = 1; i < length; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j <= 2 * k; j++) {
                if (j % 2 == 1) {
                    // 1.今天买入 2.之前已经买入
                    dp[i][j] = Math.max(dp[i - 1][j - 1] - prices[i], dp[i - 1][j]);
                } else {
                    // 卖出股票
                    // 1.今天卖出 2.之前已经卖出
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + prices[i], dp[i - 1][j]);
                }

            }
        }
        return dp[length - 1][2 * k];
    }

    public static void main(String[] args) {
        System.out.println(new Solution188().maxProfit(2, new int[]{2, 4, 1}));
    }
}