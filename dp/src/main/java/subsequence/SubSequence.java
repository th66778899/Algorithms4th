package subsequence;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author tho
 * @Date 2021/12/23/13:59
 * @ProjectName Algorithms4th
 * @ClassName: SubSequence
 * @Description: 动规 - 子序列问题
 */
public class SubSequence {
}
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        // dp[i] 表示到 i 的最长递增子序列
        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > res) {
                res = dp[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution300().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
}
class Solution674 {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int length = nums.length;
        int[] dp = new int[length];

        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i- 1] + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution674().findLengthOfLCIS(new int[]{1,3,5,4,7});
    }
}
class Solution718 {
    public int findLength(int[] nums1, int[] nums2) {
        int res = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;
        // dp[i][j] 含义
        // nums1 0 到 i - 1
        // nums2 0 到 j - 1 最长相同子数组长度
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}