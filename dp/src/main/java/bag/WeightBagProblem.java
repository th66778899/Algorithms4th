package bag;

import java.util.Arrays;

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
        // 初始化 第一列
        // 背包重量为0 , 可以放入的物品为0
        for (int i = 0; i < num; i++) {
            dp[i][0] = 0;
        }
        // 初始化 第一行
        for (int i = 1; i <= bagSize; i++) {
            if (i >= weight[0]) {
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
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        testWeightBagProblemOneArray(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
    }
}
class Solution416 {
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        // dp[i] 表示背包容量为i,所能放入的数字之和最大值
        int[] dp = new int[sum / 2 + 1];
        // 物品就是数字,重量就是数字大小,价值也是数字大小
        // 初始化
        for (int i = 0; i <= sum / 2; i++) {
            if (i >= nums[0]) {
                dp[i] = nums[0];
            } else {
                dp[i] = 0;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = sum / 2; j >= nums[i] ; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        if (dp[sum / 2] == sum / 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution416().canPartition(new int[]{1, 2, 5}));
    }

}
class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int bagSize = sum / 2;
        // dp[i] 表示容量为i的背包所能装下的最大重量的石头
        int[] dp = new int[bagSize + 1];
        // dp数组初始化 全部初始化为0,因为所有石头的重量都大于0
        for (int i = 0; i < stones.length; i++) {
            for (int j = bagSize; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        // 石头分为两堆  dp[bagSize] 和 sum - dp[bagSize]
        // sum / 2 是向下取整
        // sum - dp[bagSize] 一定 >= dp[bagSize]
        return sum - dp[bagSize] - dp[bagSize];
    }
}