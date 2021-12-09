package base;

/**
 * @Author tho
 * @Date 2021/12/9/13:30
 * @ProjectName Algorithms4th
 * @ClassName: BaseDp
 * @Description: DP基础问题
 */

/**
 * 对于动态规划问题，我将拆解为如下五步曲，这五步都搞清楚了，才能说把动态规划真的掌握了！
 *
 * 1.确定dp数组（dp table）以及下标的含义
 * 2.确定递推公式
 * 3.dp数组如何初始化
 * 4.确定遍历顺序
 * 5.举例推导dp数组
 *
 * 发出这样的问题之前，其实可以自己先思考这三个问题：
 *
 * 1.这道题目我举例推导状态转移公式了么？
 * 2.我打印dp数组的日志了么？
 * 3.打印出来了dp数组和我想的一样么？
 */
public class BaseDp {
}
class Solution509 {
    // dp
    public int fib(int n) {
        // dp数组含义 下标为i的fb数列之和
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int num = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = num;
        }

        return dp[1];
    }

    public static void main(String[] args) {
        new Solution509().fib(10);
    }
    // 递归
    /*public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }*/
}
class Solution70 {
    public int climbStairs(int n) {
        int[] dp1 = new int[n + 1];
        dp1[1] = 1;
        dp1[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp1[i] = dp1[i - 1] + dp1[i - 2];
        }
        return dp1[n];
    }
}