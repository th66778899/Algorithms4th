package version2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/12/2/14:02
 * @ProjectName Algorithms4th
 * @ClassName: BackTrackingPart2
 * @Description: 回溯 第二部分
 */
public class BackTrackingPart2 {
}
class Solution90{
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
class Solution491 {
    List<List<Integer>> res;
    List<Integer> path;

    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        // 使用一个数组记录本次循环内的相同数据是否被访问过
        // 每次新的递归,数组全部是默认值,表示每个数字都是第一次使用
        boolean[] used = new boolean[201];
        for (int i = start; i < nums.length; i++) {
            if (path.size() > 0 && path.get(path.size() - 1) > nums[i] ||
                    used[nums[i] + 100] == true) {
                continue;
            }
            path.add(nums[i]);
            used[nums[i] + 100] = true;
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        new Solution491().findSubsequences(new int[]{4, 6, 7, 7});
    }
}
class Solution46 {
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[nums.length];
        backTracking(nums);
        return res;
    }
    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backTracking(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
class Solution47 {
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums);
        return res;
    }
    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            if (used[i] == true) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backTracking(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
class Solution51 {
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], '.');
        }
        backTracking(chessBoard, 0);
        return res;
    }
    private void backTracking(char[][] chessBoard, int row) {
        if (row == chessBoard.length) {
            res.add(new ArrayList<>(arrayToList(chessBoard)));
        }
        for (int i = 0; i < chessBoard.length; i++) {
            if (check(chessBoard, row, i)) {
                chessBoard[row][i] = 'Q';
                backTracking(chessBoard, row + 1);
                chessBoard[row][i] = '.';
            }
        }

    }
    private boolean check(char[][] chessBoard, int row, int column) {
        // 检查列
        for (int i = 0; i < chessBoard.length; i++) {
            if (chessBoard[i][column] == 'Q') {
                return false;
            }
        }
        // 随着递归的深入,仅需要检查当前元素上面的行即可
        // 检查左上角 45°
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0 ; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查右上角45°
        for (int i = row - 1, j = column + 1; i >= 0 && j < chessBoard.length ; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    private List<String> arrayToList(char[][] chessBoard) {

        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                sb.append(chessBoard[i][j]);
            }
            res.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return res;
    }
}
class Solution37 {
    public void solveSudoku(char[][] board) {
        backTracking(board);
    }
    private boolean backTracking(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (char k = '1'; k <= '9'; k++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    if (check(board, i, j, k)) {
                        board[i][j] = k;
                        if (backTracking(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 没有找到合适的数字填入,需要return false
                // 该位置的下一位进行1-9数字的尝试,return false
                // 该位置需要重置为. 尝试换该数字下一个数字
                return false;
            }
        }

        return true;
    }
    private boolean check(char[][] board, int row, int column, char chess) {
        // 检查行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == chess) {
                return false;
            }
        }
        // 检查列
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == chess) {
                return false;
            }
        }
        // 检查九宫格
        int newRow = (row / 3) * 3;
        int newColumn = (column / 3) * 3;
        for (int i = newRow; i < newRow + 3; i++) {
            for (int j = newColumn; j < newColumn + 3; j++) {
                if (board[i][j] == chess) {
                    return false;
                }
            }
        }
        return true;
    }
}