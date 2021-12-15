package bag;

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

