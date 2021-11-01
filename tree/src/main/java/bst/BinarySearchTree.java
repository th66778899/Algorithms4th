package bst;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import utils.ArrToTreeUtils;
import utils.TreeNode;

/**
 * @Author tho
 * @Date 2021/11/1/16:42
 * @ProjectName Algorithms4th
 * @ClassName: BinarySearchTree
 * @Description: 二叉搜索树
 */
public class BinarySearchTree {
}
class Solution617 {


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    // 自己实现,代码复杂
    /*public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }else if (root2 == null) {
            return root1;
        }else if (root1 == null) {
            return root2;
        }
        return mergeTree(root1, root2);
    }
    private TreeNode mergeTree(TreeNode left, TreeNode right) {
        if (left != null && right != null) {
            left.val += right.val;
        }else if (left == null && right != null) {
            return right;
        }else if (left != null && right == null) {
            return left;
        }
        if (left.left != null || right.left != null) {
            left.left =  mergeTree(left.left, right.left);
        }
        if (left.right != null || right.right != null) {
            left.right =  mergeTree(left.right, right.right);
        }
        return left;
    }*/

    public static void main(String[] args) {
        TreeNode root1 = ArrToTreeUtils.InitTree(new Integer[]{1, 3, 2, 5});
        TreeNode root2 = ArrToTreeUtils.InitTree(new Integer[]{2,1,3,null,4,null,7});
        new Solution617().mergeTrees(root1, root2);
    }
}
class Solution700 {
    // 迭代
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (val > root.val) {
                root = root.right;
            }else if (val < root.val) {
                root = root.left;
            }else {
                return root;
            }
        }

        return null;
    }

    // 递归
    /*public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return root;
        if (val > root.val) {
            return searchBST(root.right, val);
        }else if (val < root.val) {
            return searchBST(root.left, val);
        }else {
            return root;
        }
    }*/
}