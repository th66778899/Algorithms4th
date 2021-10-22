package leetcode.tree_problem;

import java.util.*;

public class Tree_Problem_DFS {
}
// 树 前序遍历求深度 后序遍历求高度
class Solution104_DFS {
    int res = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        dfs(root,1);
        return res;
    }
    private void dfs(TreeNode root,int depth){
        res = res < depth ? depth : res;
        if (root.left == null && root.right == null) return;
        // 左子树
        if (root.left != null){
            depth++;
            dfs(root.left,depth);
            depth--;// 回溯
        }
        if (root.right != null){
            depth++;
            dfs(root.right,depth);
            depth--;
        }
        return;
    }




    // dfs 后序遍历

    /*public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);// 左
        int right = maxDepth(root.right);// 右
        int depth = Math.max(left,right) + 1;// 中
        return depth;
    }*/
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
class Solution111_DFS {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left != null && root.right == null){
            // 左子树不为空 右子树为空
            // 最小深度 为 1 + 左子树深度
            return 1 + left;
        }
        if (root.left == null && root.right != null){
            return 1 + right;
        }
        int depth = Math.min(left,right);
        return depth + 1;
    }


}
class Solution222 {
    // 完全二叉树递归
    // 子树里肯定有满二叉树,一直递归,直到遇到满二叉树
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftHeight = 0;
        int rightHeight = 0;
        while (left != null){
            left = left.left;
            leftHeight++;
        }
        while (right != null){
            right = right.right;
            rightHeight++;
        }
        // 如果此树是满二叉树 , 节点数量 = 2 ^ 树高度 - 1
        if (leftHeight == rightHeight){
            return (2 << leftHeight) - 1;
        }
        // 不是满二叉树,一直递归,直到找到满二叉树
        return countNodes(root.left) + countNodes(root.right) + 1;

    }


    // 普通树 递归
    /*public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        res = left + right + 1;
        return res;
    }*/
}
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int dfs = dfs(root);
        if (dfs == -1){
            return false;
        }else {
            return true;
        }
    }
    private int dfs(TreeNode root){
        if (root == null) return 0;
        int leftDepth = 0;
        if (root.left != null){
            leftDepth = dfs(root.left);
        }
        if (leftDepth == -1) return -1;
        int rightDepth = 0;
        if (root.right != null){
            rightDepth = dfs(root.right);
        }
        if (rightDepth == -1){
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) > 1){
            return -1;
        }else {
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }
}
class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Integer> paths = new ArrayList<>();
        dfs(root,paths,res);
        return res;
    }
    private void dfs(TreeNode root,List<Integer> paths,List<String> res){
        // 前序遍历 中左右
        paths.add(root.val);

        if (root.left == null && root.right == null){
            // 到达叶子节点,记录路径
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
        }

        if (root.left != null){
            dfs(root.left,paths,res);
            // 回溯
            paths.remove(paths.size() - 1);
        }
        if (root.right != null){
            dfs(root.right,paths,res);
            paths.remove(paths.size() - 1);
        }

    }
}
class Solution404 {

    public int sumOfLeftLeaves(TreeNode root) {

        return dfs(root);
    }
    private int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftSum = dfs(root.left);// 左
        int rightSum = dfs(root.right);// 右
        int midValue = 0;
        // 判断是不是找到了左叶子节点
        // 中
        if (root.left != null && root.left.left == null && root.left.right == null){
            midValue =  root.left.val;
        }

        return leftSum + rightSum + midValue;


    }
}
class Solution513 {
    // 迭代 层序遍历 BFS
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (i == 0){
                    res = node.val;
                }
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
        }
        return res;

    }



    // 找到最底层 , 需要借助熟的深度来找到
    // 找到最左边节点 , 可以借助 前序遍历,来优先搜索左侧节点
    // 递归
    /*int depth;
    int res;
    public int findBottomLeftValue(TreeNode root) {
        res = 0;
        depth = 0;
        dfs(root,0);
        return res;
    }
    // 遍历整个树 不需要返回值 找出某条路径,需要返回值
    private void dfs(TreeNode root,int curDepth){
        if (root.left == null && root.right == null){
            // 找到了叶子节点,判断高度关系,是不是可以更新深度
            if (curDepth > depth){
                res = root.val;
                depth = curDepth;
            }
        }
        if (root.left != null){
            curDepth++;
            dfs(root.left,curDepth);
            curDepth--;
        }
        if (root.right != null){
            curDepth++;
            dfs(root.right,curDepth);
            curDepth--;
        }
    }*/
}
class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return dfs(root,targetSum - root.val);

    }
    private boolean dfs(TreeNode root,int target){
        // 到达叶子节点,比较 target 与 当前节点的值
        if (root.left == null && root.right == null && 0 == target){
            return true;
        }
        if (root.left == null && root.right == null && 0 != target){
            return false;
        }
        if (root.left != null){
            target -= root.left.val;
            if (dfs(root.left,target)){
                return true;
            }
            target += root.left.val;
        }
        if (root.right != null){
            target -= root.right.val;
            if (dfs(root.right,target)){
                return true;
            }
            target += root.right.val;
        }
        return false;
    }

}
class Solution113 {
    List<List<Integer>> res;
    ArrayList<Integer> paths;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        paths = new ArrayList<>();
        if (root == null) return res;
        // paths.add(root.val);
        dfs(root,targetSum - root.val);
        return res;
    }
    private void dfs(TreeNode root,int target){
        paths.add(root.val);
        if (root.left == null && root.right == null && root.val == target){
            // 找到了一条合适的路径
            res.add(new ArrayList<>(paths));
        }
        if (root.left != null){
            // paths.add(root.left.val);
            target -= root.val;
            dfs(root.left,target);
            paths.remove(paths.size() - 1);
            target += root.val;
        }
        if (root.right != null){
            // paths.add(root.right.val);
            target -= root.val;
            dfs(root.right,target);
            paths.remove(paths.size() - 1);
            target += root.val;
        }
    }


}
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreePost(inorder,0,inorder.length,postorder,0,postorder.length);
    }
    private TreeNode buildTreePost(int[] inorder,int inLeft,int inRight,
                                   int[] postorder,int postLeft,int postRight){
        // [left,right) 区间 循环不变量
        // 根据中序遍历结果来构造非叶子节点
        // 根据后序遍历结果来构造根节点
        // 中序遍历和后序遍历 中 左右子树的长度是一致的
        if (inRight - inLeft < 1){
            // 没有元素了 , 结束构造
            return null;
        }
        // [inLeft,inRight) [0,1) 只有一个取值 0
        if (inRight - inLeft == 1){
            return new TreeNode(inorder[inLeft]);
        }
        // 找出根节点
        int rootValue = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootValue);
        // 根据根节点值 , 切分中序遍历数组
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootValue){
                rootIndex = i;
            }
        }
        // 构造左右子树 , 根据中序及后序遍历数组来构造
        root.left = buildTreePost(inorder, inLeft, rootIndex, postorder, postLeft, postLeft + (rootIndex - inLeft));
        root.right = buildTreePost(inorder, rootIndex + 1, inRight, postorder, postLeft + (rootIndex - inLeft), postRight - 1);
        return root;

    }
}
class Solution105 {
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTreePre(preOrder,0,preOrder.length,inOrder,0,inOrder.length);
    }
    private TreeNode buildTreePre(int[] preOrder,int preLeft,int preRight,
                                  int[] inOrder,int inLeft,int inRight){
        // 根据中序遍历结果构造非根节点
        if (inRight - inLeft < 1){
            return null;
        }
        if (inRight - inLeft == 1){
            return new TreeNode(inOrder[inLeft]);
        }
        // 根据前序遍历结果找到根节点
        int rootValue = preOrder[preLeft];
        // 找到中序遍历切割点
        int rootIndex = 0;
        TreeNode root = new TreeNode(rootValue);
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == rootValue){
                rootIndex = i;
            }
        }
        // 构造左右子树
        // 前序遍历和中序遍历 左右子树长度一致
        root.left = buildTreePre(preOrder, preLeft + 1, preLeft + (rootIndex - inLeft), inOrder, inLeft, rootIndex);
        root.right = buildTreePre(preOrder, preLeft + (rootIndex - inLeft) + 1, preRight, inOrder, rootIndex + 1, inRight);
        return root;

    }
}
class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums,0,nums.length);
    }
    private TreeNode buildTree(int[] nums,int left,int right){
        if (right - left < 1){
            return null;
        }
        if (right - left == 1){
            return new TreeNode(nums[left]);
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = left; i < right; i++) {
            if (nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = buildTree(nums, left, maxIndex);
        root.right = buildTree(nums, maxIndex + 1, right);
        return root;
    }
}
class Solution617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 两树中有一个树为空 , 直接返回另外一个树的节点
        if (root1 == null) return root2;
        if (root2 == null) return root1;

         root1.val += root2.val;
        // TreeNode root = new TreeNode(rootValue);
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 删除二叉树的节点分为几张情况
        // 左右子树都为空
        // 直接返回null 作为根节点
        // 左右有一侧为空
        // 左右都不为空(找到右子树最左侧节点 , 将左子树root节点加到右子树最左侧节点的left)
        if (root == null) return root;
        if (root.val == key){
            if (root.left == null) {
                return root.right;
            }else if (root.right == null){
                return root.left;
            }else {
                TreeNode cur = root.right;
                while (cur.left != null){
                    cur = cur.left;
                }
                // 此时cur 指向右子树最左侧节点
                cur.left  = root.left;
                // 返回root节点的右子树
                return root.right;
            }
        }
        if (root.val < key){
            root.right = deleteNode(root.right, key);
        }
        if (root.val > key){
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
}
