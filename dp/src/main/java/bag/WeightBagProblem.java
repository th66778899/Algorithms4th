package bag;

/**
 * @Author tho
 * @Date 2021/12/10/13:53
 * @ProjectName Algorithms4th
 * @ClassName: WeightBagProblem
 * @Description: 动态规划 - 背包问题
 */
public class WeightBagProblem {
}
class ZeroOneBag {

    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        // weight数组表示物品信息
        // bagSize 表示背包的容量
        int num = weight.length;
        // dp[i][j] 表示背包容量为j时,背包装入 下标为 [0, i] 的物品的最大重量
        int[][] dp = new int[num][bagSize + 1];
        // 初始化第一行 第一列
        for (int i = 1; i < num; i++) {
            dp[i][0] = 0;
        }
        // 初始化第一行
        for (int i = 1; i <= bagSize; i++) {
            if (weight[0] <= i) {
                dp[0][i] = value[0];
            }
        }
        // 先遍历物品,再遍历背包
        for (int i = 1; i < num; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i]] + value[i]);
                }
            }
        }
        for (int i = 1; i < num; i++) {
            for (int j = 1; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void testWeightBagProblemOneArray(int[] weight, int[] value, int bagSize) {
        // weight数组表示物品信息
        // bagSize 表示背包的容量
        int num = weight.length;
        // dp[i] 表示背包容量为i时,背包装入 物品的最大价值
        int[] dp = new int[bagSize + 1];
        // 初始化第一行 第一列

        // 初始化第一行
        // 物品所有价值都 >= 0 初始化为0即可,初始值不会覆盖之后的物品价值
        for (int i = 0; i <= bagSize; i++) {
            if (weight[0] <= i) {
                dp[i] = value[0];
            }
        }
        // 先遍历物品,再遍历背包
        for (int i = 1; i < num; i++) {
            // 倒序遍历背包,不然会有物品被放入背包多次
            for (int j = bagSize; j >= weight[i] ; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            for (int j = 1; j <= bagSize; j++) {
                System.out.print(dp[j] + " ");
            }
        }

    }
    public static void main(String[] args) {
        testWeightBagProblemOneArray(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
    }
}
