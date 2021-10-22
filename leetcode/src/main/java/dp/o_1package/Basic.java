package dp.o_1package;

import java.util.Arrays;

// 二维dp数组
public class Basic {
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){
        // dp[i][j]数组含义 物品 0-i 放入 容量为j的背包所能取得的最大价值
        int itemNum = weight.length;

        // 行 itemNum个 列 bagSize+1个
        int[][] dp = new int[itemNum][bagSize + 1];
        // 确定递推公式
        // 两种情况 物品i不能放入背包 dp[i][j] = dp[i-1][j]
        // 物品i能放入背包 dp[i][j] = dp[i-1][j-weight[i]] + value[i]


        // 初始化 第一列
        for (int i = 0; i < itemNum; i++) {
            dp[i][0]  = 0;
        }
        // 初始化 第一行
        for (int i = 0; i <= bagSize; i++) {
            if (weight[0] <= i){
                dp[0][i] = value[0];
            }
        }
        // 遍历
        // 先物品 再背包
        for (int i = 1; i < itemNum; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (weight[i] > j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }
        for (int i = 0; i < itemNum; i++) {
            for (int j = 0; j <=bagSize ; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void testWeightBagProblem1(int[] weight, int[] value, int bagWeight){
        int wLen = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[bagWeight + 1];
        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < wLen; i++){
            for (int j = bagWeight; j >= weight[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagWeight; j++){
            System.out.print(dp[j] + " ");
        }
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem(weight, value, bagSize);
    }
}
// leetcode 416 中等 0-1背包应用
class Solution416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        // sum为奇数 直接返回false
        if (sum%2 == 1)return false;
        int length = sum >> 1;

        // dp数组定义 - dp[i] 元素之和为i时,可以凑出的子集和为
        int[] dp = new int[length+1];
        // 递推公式
        // dp[j] = Math.max(dp[j],dp[j-nums[i] + nums[i]])
        // 初始化
        // 如果要输入的参数都是 > 0 ,全部初始化为0 有负数 全部初始化为 负无穷,防止0将答案给覆盖掉
        // 一维dp数组 先遍历货物，再遍历背包
        for (int i = 0; i < nums.length; i++) {
            for (int j = length; j >= nums[i] ; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[i]] + nums[i]);
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
return dp[length] == length?true:false;
    }

    public static void main(String[] args) {
        int[] x = new int[]{1,5,11,5};
        //Arrays.sort(x);
        new Solution416().canPartition(x);
    }
}
// leetcode 1049 中等 01背包
class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;// 石头重量之和
        for (int stone : stones) {
            sum += stone;
        }
        int length = sum >> 1;
        // dp[j] 数组含义 重量为j的背包能装石头的重量之和
        int[] dp = new int[length+1];
        // 递推公式 dp[j] = Math.max(dp[j],dp[j - stones[i]] + stones[i])
        // dp数组初始化 一维dp数组借助滚动数组 不需要考虑 i-1 情况 即不需要初始化第一行
        // 元素没有小于0的数 初始化为0即可
        // 元素中有小于0的数 初始化为 负无穷 ,不能初始化为0防止初始值0将结果覆盖
        for (int i = 0; i < stones.length; i++) {
            for (int j = length; j >= stones[i] ; j--) {
                dp[j] = Math.max(dp[j],dp[j - stones[i]] + stones[i]);
            }
        }
        for (int i = 0; i < length+1; i++) {
            System.out.print(dp[i]+ " ");
        }

        return sum - 2*dp[length];
    }

    public static void main(String[] args) {
        System.out.println("x" + new Solution1049().lastStoneWeightII(new int[]{31, 26, 33, 21, 40}));
    }
}
// leetcode 494 中等 01背包
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }
}
// leetcode 474 中等 01背包
class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 数组的含义 有i个0，j个1的最大子集中的元素个数
        int[][] dp = new int[m+1][n+1];
        // 递推公式
        // dp[i][j]  = Math.max(dp[i][j],dp[i-zeroNum][j-oneNum] + 1);
        // 字符串中只有 0 1 初始化可以全为0
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c=='0'){
                    zeroNum++;
                }else {
                    oneNum++;
                }

            }
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j],dp[i-zeroNum][j-oneNum] + 1);
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
        return dp[m][n];
    }

    public static void main(String[] args) {

    }
}