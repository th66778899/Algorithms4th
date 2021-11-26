package checkerboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/25/16:04
 * @ProjectName Algorithms4th
 * @ClassName: CheckBoard
 * @Description: 回溯 - 棋盘问题
 */
public class CheckBoard {
}
class Solution51 {
    List<List<String>> res;
    // List<String> path;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        // path = new ArrayList<>();
        String[][] chessBoard = new String[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], ".");
        }
        backTracking(0, n, chessBoard);
        return res;
    }
    private void backTracking(int row, int n, String [][] chessBoard) {
        if (n == row) {
            res.add(arrayToString(chessBoard));
            return;
        }
        for (int column = 0; column < n; column++) {

            if (check(row, column, chessBoard, n)) {
                chessBoard[row][column] = "Q";
                // path.add(arrayToString(chessBoard[row]));
                backTracking(row + 1, n, chessBoard);
                // path.remove(path.size() - 1);
                chessBoard[row][column] = ".";
            }
        }
    }
    private List<String> arrayToString(String[][] strs) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String[] str : strs) {
            sb.delete(0, sb.length());
            for (String s : str) {
                sb.append(s);
            }
            list.add(sb.toString());
        }
        return list;
    }
    // 检查当前 [row, column] 能不能放皇后棋子
    private boolean check(int row, int column, String[][] chessBoard, int n) {
        // 行 不用检查,每次递归都是选择某一行放入皇后棋子,不会出现一行放两个棋子的情况
        // 检查列
        for (int i = 0; i < n; i++) {
            if (chessBoard[i][column].equals("Q")) {
                return false;
            }
        }
        // 检查   [row, column] 点的 左上角斜线是不是存在皇后
        // 每次递归,行数会增加,每次仅需要检查当前行之前是不是满足条件即可
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0 ; i--, j--) {
            if (chessBoard[i][j].equals("Q")) {
                return false;
            }
        }
        // 检查   [row, column] 点的 右上角斜线是不是存在皇后
        for (int i = row - 1, j = column + 1; i >= 0 && j < n ;i--, j++ ) {
            if (chessBoard[i][j].equals("Q")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new Solution51().solveNQueens(4);
    }
}
class Solution37 {
    public void solveSudoku(char[][] board) {
        backTracking(board);
    }
    private boolean backTracking(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {continue;}
                for (char k = '1'; k <= '9'; k++) {
                    if (check(i, j, board, k)) {
                        board[i][j] = k;
                        if (backTracking(board)) return true;
                        board[i][j] = '.';
                    }
                }
                // for循环结束,该位置1 - 9 没有一个数字是可以填入的
                // 直接return false 该数独无解
                // 回溯的条件,有一个位置可能一个可行的解都没有
                return false;

            }
        }
        return true;
    }
    private boolean check(int row, int column, char[][] board, char chess) {
        // 检查同行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == chess) {
                return false;
            }
        }
        // 检查同列
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == chess) {
                return false;
            }
        }
        // 检查九宫格
        int startRow = (row / 3) * 3;
        int startColumn = (column / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startColumn; j < startColumn + 3; j++) {
                if (board[i][j] == chess) {
                    return false;
                }
            }
        }
        return true;
    }
}
