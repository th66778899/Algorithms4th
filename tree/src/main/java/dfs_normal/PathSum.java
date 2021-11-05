package dfs_normal;

import utils.ArrToTreeUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/1/13:57
 * @ProjectName Algorithms4th
 * @ClassName: PathSum
 * @Description: dfs 普通 路径总和
 */
public class PathSum {

}
class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs(root, targetSum);
    }
    public boolean dfs(TreeNode root, int sum) {

        if (root.left == null && root.right == null && sum == root.val) return true;
        boolean left = false;
        boolean right = false;


        if (root.left != null) {
            sum -= root.val;
            left = dfs(root.left, sum);
            sum += root.val;
        }
        if (root.right != null) {
            sum -= root.val;
            right = dfs(root.right, sum);
            sum += root.val;
        }
        return left || right;
    }

}
class Solution113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return res;
        }


        dfs(root, res, path, targetSum);
        return res;
    }
    private void dfs(TreeNode root, List<List<Integer>> res, List<Integer> path, int sum) {
        // 中
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList(path));
        }
        if (root.left != null) {
            sum -= root.val;
            dfs(root.left, res, path, sum);
            path.remove(path.size() - 1);
            sum += root.val;
        }
        if (root.right != null) {
            sum -= root.val;
            dfs(root.right, res, path, sum);
            path.remove(path.size() - 1);
            sum += root.val;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = ArrToTreeUtils.InitTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        new Solution113().pathSum(treeNode, 22);
    }
}
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreePost(inorder, 0 ,inorder.length, postorder, 0, postorder.length);
    }
    private TreeNode buildTreePost(int[] inOrder, int inLeft, int inRight,
                                   int[] postOrder, int postLeft, int postRight) {
        // 根据后序遍历找根节点,根据中序遍历来构造叶子节点
        // 循环不变量 [left,right)
        // 构造叶子节点
        if (inLeft >= inRight) return null;
        if (inRight - inLeft == 1) {
            return new TreeNode(inOrder[inLeft]);
        }
        // 找到根节点
        TreeNode root = new TreeNode(postOrder[postRight - 1]);
        // 根据根节点切分中序遍历数组
        int index = inLeft;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == root.val) {
                index = i;
            }
        }
        // 切分中序遍历数组为 中序左数组 和 中序右数组
        // 切分后序遍历数组为 后序左数组 和 后序右数组
        // 两个数组的切分 要点 : 两个子树的大小是一致的
        root.left = buildTreePost(inOrder, inLeft, index,
                                    postOrder, postLeft, postLeft + (index - inLeft));
        root.right = buildTreePost(inOrder, index + 1, inRight,
                                    postOrder,postLeft + (index - inLeft) , postRight - 1);

        return root;

    }
}
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreePre(preorder, 0 , preorder.length, inorder, 0 ,inorder.length);
    }
    private TreeNode buildTreePre(int[] preOrder, int preLeft, int preRight,
                                  int[] inOrder, int inLeft, int inRight) {
        if (inLeft >= inRight) {
            return null;
        }
        if (inRight - inLeft == 1) {
            return new TreeNode(inOrder[inLeft]);
        }
        TreeNode root = new TreeNode(preOrder[preLeft]);
        int index = inLeft;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == root.val) {
                index = i;
            }
        }

        root.left = buildTreePre(preOrder, preLeft + 1, index - inLeft + preLeft + 1,
                                    inOrder, inLeft, index);
        root.right = buildTreePre(preOrder, preRight - (inRight - (index + 1)), preRight ,
                                    inOrder, index + 1, inRight);

        return root;
    }
}
class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length);
    }
    private TreeNode buildMaxTree(int[] nums, int left, int right) {
        if (left >= right) return null;
        int index = left;
        int max = nums[left];
        for (int i = left; i < right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        // 循环不变量 [left,right)
        root.left = buildMaxTree(nums, left, index);
        root.right = buildMaxTree(nums, index + 1, right);
        return root;
    }
}
class Solution98 {
    TreeNode pre;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        // 中序遍历
        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) {
                return false;
        }
        pre = root;
        boolean right =  isValidBST(root.right);
        return left && right;
    }

    public static void main(String[] args) {
        TreeNode treeNode = ArrToTreeUtils.InitTree(new Integer[]{5, 1, 4, null, null, 3, 6});
        new Solution98().isValidBST(treeNode);
    }
}
class Solution530 {
    int min;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        dfs(root);
        return min;
    }
    private void dfs(TreeNode root) {
        // 中序遍历
        if (root == null) return;
        dfs(root.left);
        if (pre != null) {
            min = root.val - pre.val  < min ? root.val - pre.val : min;

        }
        pre = root;
        dfs(root.right);
    }
}
class Solution501 {
    int count;
    int max;
    ArrayList<Integer> res;
    TreeNode pre;
    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        count = 1;
        max = 1;
        dfs(root);
        int[] ints = new int[res.size()];
        int index = 0;
        while (index < res.size()) {
            ints[index] = res.get(index);
            index++;
        }
        return ints;
    }
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre != null) {
            if (pre.val == root.val) {
                count++;
                if (count > max) {
                    max = count;
                    res.clear();

                }
            }else {
                count = 1;
            }
        }
        if (count == max) {
            res.add(root.val);
        }
        pre = root;
        dfs(root.right);
    }

    // 在一个有序数组中一次遍历O(1) 空间复杂度 找出众数
    /*public static void main(String[] args) {
        int[] ints = {1, 1, 2, 3, 5, 5, 5, 6};
        int num = ints[0];
        int count = 1;
        int max = 1;
        int pre = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (pre == ints[i]) {
                count++;
                if (count > max) {
                    num = ints[i];
                    max = count;
                }
            }else {
                count = 1;
            }
            pre = ints[i];
        }
        System.out.println(num);
    }*/
}
class Solution236 {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return dfs(root, p, q);
        }
        private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
            if (root == p || root == q || root == null) {
                return root;
            }
            TreeNode left = dfs(root.left, p ,q);
            TreeNode right = dfs(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }else if (root == null && right != null) {
                return right;
            }else {
                return left;
            }
        }
    }
}
class Solution235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            }else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            }else return root;
        }
        return null;
    }
    // 递归
    /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 利用二叉搜索树,将p,q两个节点看做一个区间,p,q 两点的公共祖先肯定在两节点中间
        return dfs(root, p, q);
    }
    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) {
            return root;
        }
        // 中序遍历

        if (root.val < p.val && root.val < q.val) {
            TreeNode right = dfs(root.right, p, q);
             if (right != null) {
                 return right;
             }
        }else if (root.val > p.val && root.val > q.val) {
            TreeNode  left = dfs(root.left, p, q);
             if (left != null) {
                 return left;
             }
        }
        return root;



    }*/
}