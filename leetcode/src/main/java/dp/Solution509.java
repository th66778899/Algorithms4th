package dp;

public class Solution509 {
    public int fib(int n) {
        // 1. dp数组以及下标的含义
        // 2. 递推公式
        // 3. dp数组以及如何初始化
        // 4. 遍历顺序
        // 5. 打印dp数组
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
// leetcode70 简单
class Solution70 {
    public int climbStairs(int n) {
        if (n <=2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(dp[i]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        new Solution70().climbStairs(4);
    }
}
// leetcode 746 简单 动态规划
class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        // dp数组含义 : 登上第i阶台阶需要花费的体力
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] , dp[i - 2]) + cost[i];
        }
        int res = Math.min(dp[cost.length-1],dp[cost.length-2]);

        // 一种可以不用dp数组的写法
        /*int dp0 = cost[0];
        int dp1 = cost[1];
        int dpi;
        for (int i =2; i < cost.length; i++) {
            dpi = Math.min(dp0,dp1) + cost[i];
            dp0 = dp1;
            dp1 = dpi;
        }
        return Math.min(dp0,dp1);*/


        return res;

    }
}
// leetcode 62 中等
class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // dp数组含义 (0,0) 到 (i,j) 共有几种走法
        //int[][] dp = new int[m][n];
        // 数组初始化
        // 第一行初始化
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        // 初始化 行
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
                break;
            }
            dp[i][0] = 1;
        }
        // 初始化列
        for (int i = 0; i < column; i++) {
            if (obstacleGrid[0][i] == 1){
                dp[0][i] = 0;
                break;
            }
            dp[0][i] = 1;
        }
        // 递推公式
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j] == 1){
                    continue;
                }
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        for (int i = 0; i <row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[row-1][column-1];
    }

    public static void main(String[] args) {
        new Solution63().uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}});
    }
}
// leetcode 62 中等
class Solution62 {
    public int uniquePaths(int m, int n) {
        // dp数组含义 (0,0) 到 (i,j) 共有几种走法
        int[][] dp = new int[m][n];
        // 数组初始化
        // 第一行初始化
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 列初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 递推公式
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j-1];
            }
        }
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        new Solution62().uniquePaths(3,7);
    }
}
class Solution343 {
    public int integerBreak(int n) {

        // dp[i] 数组 表示i这个数所能拆出来的最大值
        int[] dp = new int[n+1];
        // 一个数的拆分最大值 j + (i - j) = i
        // 1.j*(i-j) (i-j)已不能拆分
        // 2.j*dp[i-j] 或 j*(i-j) (i-j) 还可以拆分
        // j 从1到 i - 1遍历,j已经完成了拆分 (i-j)要保证可以拆分
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            // 遍历j 前一个数进行拆分
            // 要保证i-j可以拆分 i - j > 1 --- i-j最小值为2 这样i-j才可以拆分
            int max = 0;
            for (int j = 1; j < i - 1; j++) {
                // 一共三个最大值
                // 最内层循环 一次循环取得 j*(i-j) 与 j*(dp[i-j]) 之间最大值
                // 同时要保证dp[i] 为所有拆分的最大值
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));

            }
        }
        /*for (int i = 0; i <=n ; i++) {
            System.out.println(dp[i]);
        }*/
        return dp[n];
    }

    public static void main(String[] args) {
        new Solution343().integerBreak(10);
    }
}
