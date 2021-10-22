package leetcode.tree_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree_Problem_BST {
}
class Solution700 {
    // 迭代
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return root;
        while (root != null){
            if (root.val < val){
                root = root.right;
            }else if (root.val > val){
                root = root.left;
            }else {
                return root;
            }
        }
        return null;

    }


    // 递归
    /*public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        if (root.val < val){
            return searchBST(root.right, val);
        }else if (root.val > val){
            return searchBST(root.left, val);
        }
        return null;
    }*/
}
class Solution98 {
    // 二叉搜索树中序遍历是一个有序递增序列
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        // 中序遍历 左 中 右
        if (!isValidBST(root.left)){
            return false;
        }
        if (pre >= root.val){
            return false;
        }
        pre = root.val;
        // 遍历右子树
        return isValidBST(root.right);
    }
}
class Solution530 {
    int min;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;

        // pre = 0;
        minimum(root);
        return min;
    }
    private void minimum(TreeNode root){
        if (root == null) return;
        minimum(root.left);
        if (pre != null){
            min = Math.min(min,root.val - pre.val);

        }
        pre = root;
        minimum(root.right);

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,3,4,5,6,6,6,7};
        int count = 1;// 统计该数字出现次数
        int max = 1;// 记录出现次数最多的数字
        ArrayList<Integer> res = new ArrayList<>();
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == pre){
                count++;
                if (count == max){
                    res.add(arr[i]);
                }else if (count > max){
                    res.clear();
                    res.add(arr[i]);
                }
                max = Math.max(max,count);
                pre = arr[i];

            }else {
                count = 1;
                pre = arr[i];
            }

        }
        for (Integer re : res) {
            System.out.print(re + " ");
        }

    }
}
class Solution501 {
    int count;// 某数字出现次数
    int max;// 数字次数出现最大值
    TreeNode pre;// 记录前一个值
    ArrayList<Integer> res;
    public int[] findMode(TreeNode root) {


        res = new ArrayList<>();
        max = 0;
        find(root);
        int[] arr = new int[res.size()];
        int index = 0;
        for (Integer re : res) {
            arr[index] = re;
            index++;
        }
        return arr;
    }
    private void find(TreeNode root){
        if (root == null){
            return;
        }
        find(root.left);
        // 计数 , 统计某数字出现次数
        if (pre == null || root.val != pre.val){
            count = 1;
        }else {
            count++;
        }
        // 统计众数
        if (count == max){
            res.add(root.val);
        }else if (count > max){
            res.clear();
            res.add(root.val);
            max = count;
        }
        pre = root;

        find(root.right);
    }
}
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findAncestor(root, p, q);
    }
    private TreeNode findAncestor(TreeNode root, TreeNode p, TreeNode q){
        // 确定递归退出条件
        // 该节点为空 或 该节点为 两节点其中之一
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = findAncestor(root.left, p, q);
        TreeNode right = findAncestor(root.right, p, q);
        if (left != null && right != null){
            return root;
        }else if (left != null && right == null){
            return left;
        }else {
            // left == null right != null
            return right;
        }

    }
}
class Solution235 {
    //迭代
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            if (root.val > p.val && root.val > q.val){
                root = root.left;
            }else if (root.val < p.val && root.val < q.val){
                root = root.right;
            }else {
                return root;
            }
        }
        return root;
    }

    // 递归
    /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 二叉搜索树 中序遍历是一个有序数组
        // 如果 root 节点出现在 [p, q] 之间, root 节点就是公共祖先
        // root 在 [p, q] 左边 需要往右边搜索
        // 反之, 向左边搜索
        if (root == null) return root;
        if (root.val > p.val && root.val > q.val){
            TreeNode left = lowestCommonAncestor(root.left, p ,q);
            if (left != null){
                return left;
            }
        }
        if (root.val < p.val && root.val < q.val){
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (right != null){
                return right;
            }
        }
        return root;

    }*/
}
class Solution701 {
    // 递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val){
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    // 迭代
    /*public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        TreeNode temp = root;
        TreeNode pre = temp;
        while (temp != null){
            pre = temp;
            if (temp.val < val){
                temp = temp.right;
            }else {
                temp = temp.left;
            }
        }
        if (pre.val > val){
            pre.left = new TreeNode(val);
        }else {
            pre.right = new TreeNode(val);
        }
        return root;
    }*/
}