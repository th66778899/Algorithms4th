package dfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author tho
 * @Date 2021/10/29/15:31
 * @ProjectName Algorithms4th
 * @ClassName: LeftLeaveSum
 * @Description: 左叶子之和 需要在根节点判断是不是左叶子
 */
public class LeftLeaveSum {
}
class Solution404 {
    // 迭代
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                res += node.left.val;
            }
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;

    }
    // 递归
    /*int res;
    public int sumOfLeftLeaves(TreeNode root) {
        res = 0;
        if (root == null) return res;
        dfs(root);
        return res;
    }
    private void dfs(TreeNode root) {
        // 判断
        if (root == null) return;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        dfs(root.left);
        dfs(root.right);
    }*/
}
class Solution513 {
    int maxHeight;
    int res;
    public int findBottomLeftValue(TreeNode root) {
        // 树左下角的值
        // 最底层  借助高度来找到
        // 左下角,只记录
        maxHeight = 0;
        res = root.val;
        dfs(root, 1);
        return res;
    }
    private void dfs(TreeNode root, int height) {
        if (root == null) return;
        // 高度变大了
        // 只有第一次高度变化才会更新maxHeight值
        // 更新时该节点的值就是最底层,最左侧的节点的值
        if ( height > maxHeight) {
            res = root.left.val;
            maxHeight = height;
        }
        // 左
        height++;
        dfs(root.left, height);
        dfs(root.right, height);
    }
}