package dp.打家劫舍;
// 动态规划 打家劫舍系列
public class House_robber {
}
// leetcode 198 中等 动态规划-打家劫舍1
class Solution198 {
    public int rob(int[] nums) {
        // dp[i] 含义 前i间房子能得到的钱最大值
        int[] dp = new int[nums.length];
        // 递推公式
        // 第i间房 偷 dp[i] = dp[i-2] + nums[i];
        // 第i间房 不偷 dp[i] = dp[i-1]
        // dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i <nums.length ; i++) {
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        new Solution198().rob(new int[]{2,7,9,3,1});
    }
}
// leetcode 213 中等 动态规划-打家劫舍Ⅱ
class Solution213 {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0],nums[1]);
        int left = robRange(nums,0,nums.length-2);
        int right = robRange(nums,1,nums.length-1);
        return Math.max(left,right);
    }
    private int robRange(int[] nums,int start,int end){
        int[] dp = new int[end+1];
        dp[start] = nums[start];
        dp[start+1] = Math.max(nums[start],nums[start+1]);
        for (int i = start+2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
        }
        return dp[end];

    }

    public static void main(String[] args) {
        System.out.println(new Solution213().rob(new int[]{2,3,2}));
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
// leetcode 中等 动态规划-打家劫舍Ⅲ 树形DP
class Solution337 {
    // 3.状态标记递归
    // 执行用时：0 ms , 在所有 Java 提交中击败了 100% 的用户
    // 不偷：Max(左孩子不偷，左孩子偷) + Max(又孩子不偷，右孩子偷)
    // root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) +
    // Math.max(rob(root.right)[0], rob(root.right)[1])
    // 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
    // root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
    public int rob3(TreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]);
    }

    int[] robAction1(TreeNode root) {
        int res[] = new int[2];
        if (root == null)
            return res;

        int[] left = robAction1(root.left);
        int[] right = robAction1(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
