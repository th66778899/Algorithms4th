package robbery;

/**
 * @Author tho
 * @Date 2021/12/20/14:42
 * @ProjectName Algorithms4th
 * @ClassName: Robbery
 * @Description: 动态规划 - 打家劫舍
 */
public class Robbery {
}
class Solution198 {
    public int rob(int[] nums) {
        // dp[i] 表示到第i间房,所能得到的最多的钱
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        for (int i = 0; i <= nums.length; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[nums.length];
    }
}
class Solution213 {
    public int rob(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        if (nums.length == 1) {
            return nums[0];
        }
        int left = robCircle(nums, 1,length - 1);
        int right = robCircle(nums, 0, length - 2);
        return Math.max(left, right);
    }
    public int robCircle(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }
}
