package roll.array;

/**
 * @Author tho
 * @Date 2021/12/16/9:25
 * @ProjectName Algorithms4th
 * @ClassName: ZeroOneBag
 * @Description: 零一背包练习
 */
public class ZeroOneBag {
    public static void zeroOneBagTest(int[] weights, int[] values, int bagSize) {
        int length = weights.length;
        // dp[i][j] 表示[0, i - 1] 物品,容量为j的背包,所能装入的物品的最大价值
        int[][] dp = new int[length][bagSize + 1];
        // 初始化dp数组
        // 只需要初始化第一行,第一列背包容量为0,dp[i][0] 全部都为0
        for (int i = 1; i <= bagSize; i++) {
            if (i >= weights[0]) {
                dp[0][i] = values[0];
            }
        }
        // 先遍历物品,再遍历背包

        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                // 可以放物品
                if (j >= weights[i]) {
                    // 可以放物品,但是可以选择放 或则 不放
                    // 不放,就是相当于没有放新的物品
                    // 放了物品,可能会把前面放的物品挤出去,导致得到的价值降低了,因此要取两者最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                } else {
                    // 不能放物品
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // 先遍历背包,再遍历物品
        /*for (int i = 1; i <= bagSize; i++) {
            for (int j = 1; j < length; j++) {
                if (i >= weights[j]) {
                    dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - weights[j]] + values[j]);
                } else {
                    dp[j][i] = dp[j - 1][i];
                }
            }
        }*/
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void zeroOneBagRollArrayTest(int[] weights, int[] values, int bagSize) {
        int length = weights.length;
        // dp[i] 表示容量为i的背包,所能装入的物品的最大价值
        // 01背包dp[i][j] 仅需要该行上一行的数据即可,可用滚动数组代替二维数组
        int[] dp = new int[bagSize + 1];
        // 初始化dp
        for (int i = 1; i <= bagSize; i++) {
            if (i >= weights[0]) {
                dp[i] = values[0];
            }
        }
        for (int i = 0; i <= bagSize; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        // 滚动数组不能记录 物品的
        /// 先遍历背包,再遍历物品
        for (int j = bagSize; j > 0; j--) {
            for (int i = 1; i < length; i++) {
                if (j >= weights[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
            for (int x = 0; x <= bagSize; x++) {
                System.out.print(dp[x] + " ");
            }
            System.out.println();
        }

        /// 先遍历物品,再遍历背包
        /*for (int i = 1; i < length; i++) {
            for (int j = bagSize; j >= weights[i]; j--) {
                // 倒叙遍历,防止物品被多次使用
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
            for (int x = 0; x <= bagSize; x++) {
                System.out.print(dp[x] + " ");
            }
            System.out.println();

        }*/
    }
    public static void completeBagRollArrayTest(int[] weights, int[] values, int bagSize) {
        /// 完全背包 物品可以无限次使用
        int length = weights.length;
        // dp[i] 表示容量为i的背包,所能装入的物品的最大价值
        // 01背包dp[i][j] 仅需要该行上一行的数据即可,可用滚动数组代替二维数组
        int[] dp = new int[bagSize + 1];
        // 初始化dp
        for (int i = 1; i <= bagSize; i++) {
            if (i >= weights[0]) {
                dp[i] = values[0];
            }
        }
        for (int i = 0; i <= bagSize; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        // 滚动数组不能记录 物品的
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                // 倒叙遍历,防止物品被多次使用
                if (j >= weights[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
            for (int x = 0; x <= bagSize; x++) {
                System.out.print(dp[x] + " ");
            }
            System.out.println();

        }
    }
    public static void completeBagTest(int[] weights, int[] values, int bagSize) {
        /// 完全背包 物品可以无限次使用
        int length = weights.length;
        // dp[i][j] 表示可选物品[0, i - 1] , 容量为j的背包,所能装入的物品的最大价值
        int[][] dp = new int[length][bagSize + 1];
        // 初始化dp
        for (int i = 1; i <= bagSize; i++) {
            if (i >= weights[0]) {
                dp[0][i] = Math.max(dp[0][i], dp[0][i - weights[0]] + values[0]);
            }
        }

        // 滚动数组不能记录 物品的
        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= bagSize; j++) {

                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[] weights = {1, 1, 3, 4};
        int[] values = {15, 15, 20, 30};
        int bagSize = 4;
        completeBagTest(weights, values, bagSize);
    }

}

