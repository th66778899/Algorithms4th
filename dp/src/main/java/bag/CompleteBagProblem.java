package bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/12/15/14:46
 * @ProjectName Algorithms4th
 * @ClassName: CompleteBagProblem
 * @Description: 动态规划 - 完全背包问题
 */
public class CompleteBagProblem {
    public static void testCompleteBagProblem(int[] weights, int[] values, int bagSize) {
        // 完全背包问题 每个物品可以被放入多次
        int num = weights.length;
        int[][] dp = new int[num][bagSize + 1];
        // 初始化第一行
        for (int i = 1; i <= bagSize; i++) {
            if (bagSize >= weights[0]) {
                dp[0][i] += dp[0][i - weights[0]] + values[0];
            }
        }

        for (int i = 1; i < num; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }


    }
    public static void testCompleteBagProblemOneArray(int[] weights, int[] values, int bagSize) {
        // dp[i] 表示容量为i的背包其能装入的物品最大价值为dp[i]
        int[] dp = new int[bagSize + 1];
        // dp 数组 全部初始化为0即可
        for (int i = 0; i < weights.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j >= weights[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        testCompleteBagProblemOneArray(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
    }
}

class Solution518 {
    public int change(int amount, int[] coins) {
        int length = coins.length;
        // dp[i] 表示硬币总额为i 的方法数
        int[] dp = new int[amount + 1];
        // 初始化 全部初始化为0
        dp[0] = 1;



        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
            for (int j = 0; j <= amount; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }

        return dp[amount];
    }
    public static void main(String[] args) {
        new Solution518().change(5, new int[]{1, 2, 5});
    }
}
class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        int length = nums.length;
        // dp[i] 表示装满i背包的 方法数目
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < length; j++) {
                if (i >= nums[j]) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                }
            }
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();

        }
        return dp[target];
    }

    public static void main(String[] args) {
        new Solution377().combinationSum4(new int[]{1, 2, 3}, 4);
    }
}
class Solution70 {
    public int climbStairs(int n) {
        // dp[i] 上到i阶阶梯 共有dp[i]种方法
        // 物品 1 2
        int step = 2;
        int[] dp = new int[n + 1];
        // 排列问题
        dp[0] = 1;
        int[] steps = new int[]{1, 2};
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= step; j++) {
                if (i >= j) {
                    dp[i] = dp[i] + dp[i - j];
                }
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        new Solution70().climbStairs(3);
    }
}
class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        // dp[i] 含义 凑出金额为i所需要的硬币的最少个数为 dp[i]
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 初始化 金额为0需要的最小硬币个数为0
        dp[0] = 0;
        // 求组合问题,且硬币个数无限,完全背包问题,背包容量遍历正序即可
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
            for (int j = 0; j <= amount; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[amount];

    }

    public static void main(String[] args) {
        new Solution322().coinChange(new int[] {1, 2, 5}, 11);
    }
}
class Solution279 {
    public int numSquares(int n) {
        int x = (int) Math.sqrt((double) n);
        // dp[i] 表示凑成i所需要完全平方数的最小数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] values = new int[x + 1];
        for (int i = 0; i <= x; i++) {
            values[i] = i * i;
        }

        // 要求的是数量,排列还是组合都一样
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= values[i]) {
                    dp[j] = Math.min(dp[j], dp[j - values[i]] + 1);
                }
            }
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[n];
    }
}
class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // wordDict 就是物品
        // s 大小就是背包容量 让 dp[i] = i 就是true,物品可以填满背包
        int bagSize = s.length();
        boolean[] dp = new boolean[bagSize + 1];
        // dp[0] = true 为了递推公式使用 , 其他全部为false
        // 如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）。
        // 所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
        for (int i = 1; i <= bagSize; i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j] == true) {
                    dp[i] = true;
                }
            }
        }
        return dp[bagSize];
    }


    public static void main(String[] args) {
        List<String> values = new ArrayList<>();
        values.add("leet");
        values.add("code");
        new Solution139().wordBreak("leetcode", values);
    }
}