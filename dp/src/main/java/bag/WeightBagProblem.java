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
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        /*left + right = sum
        left - right = target
        left = (sum + target) / 2
        分出来的集合必须是整数,
        */
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        int bagSize = (sum + target) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }

        return dp[bagSize];
    }
}
class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp数组定义
        // dp[i][j] 表示 i个0,j个1的字符串的最大个数
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                if (aChar == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            // 一个字符串一个字符串地遍历
            // dp数组全部初始化为0即可
            // 遍历背包,两个维度 0 的数量和 1的数量
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        new Solution474().findMaxForm(new String[]{"10", "0001", "111001", "1","0"}, 5, 3);
    }
}