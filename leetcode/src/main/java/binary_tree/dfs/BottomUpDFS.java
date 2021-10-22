package binary_tree.dfs;

import java.util.*;

// 自底向上的DFS
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
// leetcode 104 简单
public class BottomUpDFS {
    public int maxDepth(TreeNode root) {
        if  (root == null) {
            return 0;

        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int max_length = Math.max(left,right) + 1;
        return max_length;
    }

    public static void main(String[] args) {
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode20 = new TreeNode(20);
        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode3.left = treeNode9;
        treeNode3.right = treeNode20;
        treeNode20.left = treeNode15;
        treeNode20.right = treeNode7;
        new BottomUpDFS().maxDepth(treeNode3);
    }
}
// leetcode 124 困难

class Solution124{
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    private int dfs(TreeNode node){
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        max = Math.max(max,left + right + node.val);
        return Math.max(left + node.val,right + node.val);
    }
}
// leetcode 110 简单
class Solution110{
    public boolean isBalanced(TreeNode root) {
        if (depth(root) == -1) return false;
        else {
            return true;
        }
    }
    private int depth(TreeNode root){
        if (root == null) {return 0;}
        int left = depth(root.left);
        if (left == -1) {return -1;}
        int right = depth(root.right);
        if (right == -1){return  -1;}
        if (Math.abs(right - left) > 1){
            return -1;
        }else {
            return Math.max(left,right) + 1;
        }

    }
}
// leetcode 112 简单
class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return traversal(root,targetSum - root.val);
    }
    private boolean traversal(TreeNode node,int targetSum){
        if (node.left == null && node.right == null && targetSum == 0){return true;}
        if (node.left == null && node.right == null) {return false;}

        if (node.left!=null){
            targetSum -= node.left.val;
            if (traversal(node.left,targetSum)){return true;}
            targetSum += node.left.val;// 回溯
        }
        if (node.right!=null){
            targetSum -= node.right.val;
            if (traversal(node.right,targetSum)){return true;}
            targetSum += node.right.val;
        }
        return false;
    }
}
// leetcode 113 中等
class Solution113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res; // 非空判断

        List<Integer> path = new LinkedList<>();
        preorderDFS(root, targetSum, res, path);
        return res;
    }

    public void preorderDFS(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path) {
        path.add(root.val);
        // 遇到了叶子节点
        if (root.left == null && root.right == null) {
            // 找到了和为 targetSum 的路径
            if (targetSum - root.val == 0) {
                res.add(new ArrayList<>(path));
            }
            return; // 如果和不为 targetSum，返回
        }

        if (root.left != null) {
            preorderDFS(root.left, targetSum - root.val, res, path);
            path.remove(path.size() - 1); // 回溯
        }
        if (root.right != null) {
            preorderDFS(root.right, targetSum - root.val, res, path);
            path.remove(path.size() - 1); // 回溯
        }
    }


    public static void main(String[] args) {
        Solution113 solution113 = new Solution113();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        System.out.println(solution113.pathSum(treeNode1, 3));
    }

}
// leetcode 236 中等
class Solution236{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return   lowestCommonAncestor1(root,p,q);
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left != null && right != null){
            return root;
        }
        if (left == null){
            // left 是空  两结点全部在右子树 返回右子树根节点
            return right;
        }
        return left;
    }
}
// leetcode 508 中等
class Solution508{
    // 使用hashMap存储结果
    HashMap<Integer, Integer> map = new HashMap<>();
    int max = 0;// 某数出现次数
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null){
            return new int[]{};
        }
        Sum(root);
        ArrayList<Integer> sum = new ArrayList<>();
        // 遍历hashmap 找出出现次数最多的元素
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == max){
                sum.add(integer);
            }
        }
        int[] res = new int[sum.size()];
        int i = 0;
        for (Integer integer : sum) {
            res[i] = integer;
            i++;
        }

        return res;


    }
    private int Sum(TreeNode node){
        if(node == null) return 0;
        int left = Sum(node.left);
        int right = Sum(node.right);
        int sum = left + node.val + right;
        map.put(sum,map.getOrDefault(sum,0) + 1);
        max = Math.max(max,map.get(sum));
        return sum;

    }

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(-3);
        treeNode5.left = treeNode3;
        treeNode5.right = treeNode2;
        int[] frequentTreeSum = new Solution508().findFrequentTreeSum(treeNode5);
        for (int i : frequentTreeSum) {
            System.out.println(i);
        }


    }

}
