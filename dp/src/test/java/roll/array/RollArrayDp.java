package roll.array;

import javax.imageio.stream.ImageInputStream;

/**
 * @Author tho
 * @Date 2021/12/9/16:17
 * @ProjectName Algorithms4th
 * @ClassName: RollArrayDp
 * @Description: 一维dp数组 滚动数组
 *                 保存各个状态的dp数组情况
 */
public class RollArrayDp {
}
class Solution62 {
    public int uniquePaths(int m, int n) {
        // dp[row][column] 表示到达(row, column) 这点共有 dp[row][column] 条路径
        int[] dp = new int[n];
        // 初始化第一行
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        // 从第二行开始进行 dp 构造
        for (int i = 1; i < m; i++) {
            // 第一列不需要更新

            for (int j = 1; j < n; j++) {
                // 后一列由前一列的状态推导出来
                dp[j] += dp[j - 1];

            }
            /*for (int j = 0; j < n; j++) {
                System.out.print(dp[j]);
            }
            System.out.println();*/
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        new Solution62().uniquePaths(3, 7);
    }
}
