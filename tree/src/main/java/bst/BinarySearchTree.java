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
class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }else {
            // 找到了目标值
            // 分为几种情况
            if (root.left == null && root.right == null) {
                return null;
            }else if (root.left == null) {
                return root.right;
            }else if (root.right == null) {
                return root.left;
            }else {
                // 左右子树都不为空
                TreeNode temp = root.right;
                while (temp != null && temp.left != null) {
                    temp = temp.left;
                }
                temp.left = root.left;
                return root.right;
            }
        }

        return root;

    }

    public static void main(String[] args) {

        TreeNode treeNode = ArrToTreeUtils.InitTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        TreeNode treeNode1 = new Solution450().deleteNode(treeNode, 3);
        System.out.println(treeNode1.val);
    }
}
class Solution669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val < low) {
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
class Solution108 {
    // 循环不变量 [left, right)
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length);
    }
    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left >= right) return null;
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, left, mid);
        root.right = buildTree(nums, mid + 1, right);
        return root;
    }
}
class Solution538 {
    TreeNode pre;
    public TreeNode convertBST(TreeNode root) {
        // 累加树 二叉搜索树 中序遍历 左中右 递增序列
        // 右 中 左 遍历,该节点的值加上前面一个节点的值
        dfs(root);
        return root;

    }
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (pre != null) {
            root.val += pre.val;
        }
        pre = root;
        dfs(root.left);
    }
}