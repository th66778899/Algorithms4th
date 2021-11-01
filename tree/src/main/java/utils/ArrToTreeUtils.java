package utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author tho
 * @Date 2021/11/1/14:42
 * @ProjectName Algorithms4th
 * @ClassName: utils.ArrToTreeUtils
 * @Description: 按数组顺序构造完全二叉树
 */
public class ArrToTreeUtils {

    public static TreeNode InitTree(Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        Deque<TreeNode> queue = new LinkedList<>();
        int cur = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode r = queue.poll();
            if (arr[cur] == null) {
                r.left = null;
            } else {
                r.left = new TreeNode(arr[cur]);
                queue.offer(r.left);
            }
            if (++cur >= arr.length) {
                break;
            }
            if (arr[cur] == null) {
                r.right = null;
            }else {
                r.right = new TreeNode(arr[cur]);
                queue.offer(r.right);
            }
            if (++cur >= arr.length) {
                break;
            }
        }
        return root;

    }

    public static void main(String[] args) {
        TreeNode treeNode = InitTree(new Integer[]{3, 5, 1, null});
    }
}
