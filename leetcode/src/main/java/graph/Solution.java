package graph;


import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // leetcode 542 中等
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;// 行
        int n = mat[0].length;// 列
        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];// 记录该节点是否被访问过了
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0){
                    queue.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int cost = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int i = cur[0];
                int j = cur[1];
                if (mat[i][j] == 1){
                    res[i][j] = cost;
                }
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y>=0 && y < n && !visited[x][y]){
                        queue.offer(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }

            }
            cost++;
        }
        return res;
    }
}
