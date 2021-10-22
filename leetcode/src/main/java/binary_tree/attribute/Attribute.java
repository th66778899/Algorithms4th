package binary_tree.attribute;

import java.util.*;

// 二叉树属性
public class Attribute {
}
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
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        // 使用递归
        /*if (root == null) return true;
        return compare(root.left,root.right);*/

        // 使用迭代
        if (root == null) return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root.left);
        queue.offerLast(root.right);
        while (!queue.isEmpty()){
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            // 左右节点均为空 跳过本次循环
            if (left == null && right == null){
                continue;
            }
            if (left ==null  || right == null || left.val != right.val){
                return false;
            }
            // 判断外层和内层
            queue.offerLast(left.left);
            queue.offerLast(right.right);
            // 内层
            queue.offerLast(left.right);
            queue.offerLast(right.left);

        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode31 = new TreeNode(3);
        TreeNode treeNode32 = new TreeNode(3);
        TreeNode treeNode41 = new TreeNode(4);
        TreeNode treeNode42 = new TreeNode(4);
        TreeNode treeNode21 = new TreeNode(2,treeNode31,treeNode41);
        TreeNode treeNode22 = new TreeNode(2,treeNode41,treeNode32);
        TreeNode treeNode = new TreeNode(1, treeNode21, treeNode22);

        new Solution101().isSymmetric(treeNode);
    }

    /**
     * 递归解决二叉树是不是对称的
     * @param left
     * @param right
     * @return
     */
    private boolean compare(TreeNode left,TreeNode right){
        // 先处理递归结束条件,避免左右节点某一个为空带来的问题
        if (left == null && right != null) return false;
        else if (left != null && right == null) return false;
        else if (left == null && right == null) return true;
        // 以上情况排除了left 或 right 其中有空值的情况,接下来需要判断左右节点的值
        else if (left.val != right.val) return false;
        // 还有一种情况 left 和 right 对应值相同
        boolean outside = compare(left.left,right.right);
        boolean inside = compare(left.right,right.left);
        boolean isSame = outside && inside;
        return isSame;
    }
}
class Solution104 {
    int res = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        getDepth(root,1);
        return res;

        // 后序遍历求高度 就是前序遍历求深度
        /*if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int height = Math.max(left,right) + 1;
        return height;*/
    }
    private void getDepth(TreeNode root,int depth){
        res = depth > res ? depth : res;
        if (root.left == null && root.right == null) return;
        if (root.left != null){
            depth++;
            getDepth(root.left,depth);
            depth--;
        }
        if (root.right != null){
            depth++;
            getDepth(root.right,depth);
            depth--;
        }
        return;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
class Solution559 {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;
        for (int i = 0; i < root.children.size(); i++) {
            depth = Math.max(depth,maxDepth(root.children.get(i)));
        }
        return depth + 1;
    }
}
class Solution111 {
    public int minDepth(TreeNode root) {
        // 迭代
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left == null && node.right == null){
                    return depth;
                }
                if (node.left !=null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
        }
        return depth;

        // 递归
        /*if (root == null) return 0;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null && root.right != null){
            return 1 + rightDepth;
        }
        if (root.left != null && root.right == null){
            return 1 + leftDepth;
        }
        int depth = 0;
        depth = 1 + Math.min(leftDepth,rightDepth);
        return depth;*/
    }
}
class Solution222 {
    public int countNodes(TreeNode root) {
        // 迭代 BFS
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int sum = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                sum++;
                if (node.left!=null) queue.offerLast(node.left);
                if (node.right!=null) queue.offerLast(node.right);
            }

        }
        return sum;

        // 递归 DFS
        /*if (root == null) return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        int sum = left + right;
        return sum + 1;*/
    }
}
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) == -1 ? false : true;
    }
    private int getDepth(TreeNode root){
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1){
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left,right);
    }
}
class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        if (root == null) return res;
        ArrayList<Integer> path = new ArrayList<>();
        preOrder(root,path,res);
        return res;
    }
    private void preOrder(TreeNode root,List<Integer> path,List<String> res){
        // 前序遍历 中 左 右
        path.add(root.val);
        if (root.left!=null){
            preOrder(root.left,path,res);
            path.remove(path.size() - 1);
        }
        if (root.right != null){
            preOrder(root.right,path,res);
            path.remove(path.size() - 1);
        }
        if (root.left == null && root.right == null){
            // 到了叶子节点 记录结果
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                // 最后一个先不添加
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }

    }
}
class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return leftSum(root);
    }
    private int leftSum(TreeNode root){
        if (root == null) return 0;
        int leftRes = leftSum(root.left);
        int rightRes = leftSum(root.right);
        int res = 0;
        if (root.left != null && root.left.left == null && root.left.right == null){
            res += root.left.val;
        }
        return res + leftRes + rightRes;
    }
}
class Solution513 {
    int depth = 0;
    int res = 0;
    public int findBottomLeftValue(TreeNode root) {
        // 迭代
        if (root == null) return 0;
       Deque<TreeNode> queue = new LinkedList<>();
       queue.offerLast(root);
       int res = 0;
       while (!queue.isEmpty()){
           int size = queue.size();
           res = queue.peekFirst().val;
           for (int i = 0; i < size; i++) {
               TreeNode node = queue.pollFirst();

               if (node.left!=null) queue.offerLast(node.left);
               if (node.right!=null) queue.offerLast(node.right);
           }
       }
       return res;

        // 递归
        /*findLeft(root,1);
        return res;*/
    }
    private void findLeft(TreeNode root,int levelDepth){
        if (root.left == null && root.right == null){
            if (levelDepth > depth){
                depth = levelDepth;
                res = root.val;
                return;
            }
        }
        if (root.left != null) {
            levelDepth++;
            findLeft(root.left,levelDepth);
            levelDepth--;

        }
        if (root.right != null){
            levelDepth++;
            findLeft(root.right,levelDepth);
            levelDepth--;
        }
    }
}
// 路径总和
/*再来看返回值，递归函数什么时候需要返回值？什么时候不需要返回值？这里总结如下三点：

如果需要搜索整颗二叉树且不用处理递归返回值，递归函数就不要返回值。（这种情况就是本文下半部分介绍的113.路径总和ii）
如果需要搜索整颗二叉树且需要处理递归返回值，递归函数就需要返回值。 （这种情况我们在236. 二叉树的最近公共祖先中介绍）
如果要搜索其中一条符合条件的路径，那么递归一定需要返回值，因为遇到符合条件的路径了就要及时返回。（本题的情况）*/
class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return pathSum(root,targetSum - root.val);
    }
    public boolean pathSum(TreeNode root,int sum){
        if (root.left == null && root.right == null && sum == 0){
            return true;
        }
        if (root.left == null && root.right == null && sum != 0){
            return false;
        }
        if (root.left != null){
            sum -= root.left.val;
            if (pathSum(root.left,sum)){
                return true;
            }
            sum += root.left.val;// 回溯
        }
        if (root.right != null){
            //sum -= root.right.val;
            if (pathSum(root.right,sum - root.right.val)){// 隐藏了回溯
                return true;
            }
            //sum += root.right.val;
        }
        return false;
    }
}
// 路径总和Ⅱ
class Solution113 {
    ArrayList<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {


        if (root == null) return res;
        path.add(root.val);
        preOrder(root,targetSum - root.val);
        return res;
    }
    private void preOrder(TreeNode root,int sum){
        if (root.left == null && root.right == null){
            // 到了叶子节点
            if (sum == 0){


                res.add(new ArrayList<>(path));
            }

            return;
        }
        if (root.left != null){
            sum -= root.left.val;
            path.add(root.left.val);
            preOrder(root.left,sum);
            // 回溯
            path.remove(path.size() - 1);
            sum += root.left.val;
        }
        if (root.right != null){
            sum -= root.right.val;
            path.add(root.right.val);
            preOrder(root.right,sum);
            // 回溯
            path.remove(path.size() - 1);
            sum += root.right.val;
        }
        return;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(1);
        path.add(2);
        res.add(new ArrayList<>(path));
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                System.out.println(integer);
            }
        }
    }
}
// 中序和后序遍历 构造二叉树
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
       return buildTree23(inorder,0,inorder.length,postorder,0,postorder.length);
    }
    private TreeNode buildTree23(int[] inOrder,int inLeft,int inRight,int[] postOrder,int postLeft,int postRight){
        // 递归退出条件
        if (inRight - inLeft <= 0) return null;

        if (inRight - inLeft > 0){
            // 中序遍历只有一个元素 , 将其返回作为根节点的孩子节点
            return new TreeNode(inOrder[inLeft]);
        }
        TreeNode root = new TreeNode(postOrder[postRight - 1]);// 后序遍历最后一个元素是根节点
        int index = 0;// 记录中序遍历的切割点 以根节点为切割点
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == root.val){
                index = i;
            }
        }
        root.left = buildTree23(inOrder,inLeft,index,postOrder,postLeft,postLeft + (index - inLeft));

        root.right = buildTree23(inOrder,index + 1,inRight,postOrder,index,postRight - 1);
        return root;

    }
}
class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums,0,nums.length);
    }
    private TreeNode buildMaxTree(int[] nums,int left,int right){
        if (right - left <= 0){
            return null;
        }
        if (right - left == 1){
            return new TreeNode(nums[left]);
        }
        int max = Integer.MIN_VALUE;
        int index = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = left; i < right; i++) {
            // map.put(nums[i],i);
            //max = Math.max(max,nums[i]);
            if (nums[i] > max){
                max = nums[i];
                index = i;
            }
        }
        //int index = map.get(max);// 根节点所在位置,根据该位置切割原数组
        TreeNode root = new TreeNode(max);
        root.left = buildMaxTree(nums,left,index);
        root.right = buildMaxTree(nums,index + 1,right);
        return root;
    }
}