package binary_tree.traversal;

import java.util.*;

public class Traversal {
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
// 二叉树中序遍历
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;// 借助一个指针来帮助 中序迭代遍历
        while (cur != null || !stack.isEmpty()){
            // 当前指针指向节点不为空 或 当前栈不为空
            // 指针不为空 说明还有左子树存在 栈不为空说明该节点的根节点存在(仍未遍历到,还在stack内)
            if (cur != null){
                // 左子树仍存在 一直遍历二叉树的左子树 直至左子树为空
                stack.addFirst(cur);
                cur = cur.left;
            }else{
                // 左子树为空,取出该数中节点
                TreeNode mid = stack.pollFirst();
                res.add(mid.val);// 该小二叉树的中节点,由于该二叉树的左子树为空,中节点先遍历变成了整个二叉树的中序遍历 左 中 右
                cur = mid.right;

            }

        }
        return res;



        /*List<Integer> res = new ArrayList<>();
        inOrder(root,res);
        return res;*/
    }

    /**
     * 递归实现中序遍历
     * @param root
     * @param res
     */
    private void inOrder(TreeNode root,List<Integer> res){
        if (root == null){
            return;
        }
        // 左子树
        inOrder(root.left,res);
        // 根节点
        res.add(root.val);
        // 右子树
        inOrder(root.right,res);
    }

}
// 二叉树前序遍历
class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 迭代实现前序遍历 借助栈来模拟递归实现
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        // 前序遍历 中 左 右 入栈顺序 中 右 左
        while (!stack.isEmpty()){
            // 根节点
            TreeNode node = stack.pollFirst();
            res.add(node.val);
            // 右子树入栈
            if (node.right!=null){
                stack.addFirst(node.right);
            }
            // 左子树入栈
            if (node.left!=null){
                stack.addFirst(node.left);
            }

        }
        return res;



        // 递归实现前序遍历
        /*ArrayList<Integer> res = new ArrayList<>();
        preOrder(root,res);
        return res;*/

    }


    /**
     * 前序遍历递归实现
     * @param root
     * @param res
     */
    private void preOrder(TreeNode root,List<Integer> res){
        if (root == null) return;
        // 根节点
        res.add(root.val);
        // 左子树
        preOrder(root.left,res);
        // 右子树
        preOrder(root.right,res);
    }
}
// 二叉树后序遍历
class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
       // 二叉树后序遍历 (迭代)
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        // 前序遍历 中 左 右  -> 调换入栈顺序 中 右 左 -> 数组逆序 -> 左 右 中 == 后序遍历 左 右 中
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()){
            TreeNode mid = stack.removeFirst();// 根节点
            res.add(mid.val);
            // 先将左子树入栈,再将右子树入栈 这样出栈顺序就变成 中 右 左
            if (mid.left != null) stack.addFirst(mid.left);
            if (mid.right != null) stack.addFirst(mid.right);
        }
        // 再将结果逆序处理
        Collections.reverse(res);
        return res;

        // 二叉树后序遍历(递归)
        /*ArrayList<Integer> res = new ArrayList<>();
        postOrder(root,res);
        return res;*/
    }

    /**
     * 二叉树后序遍历  递归
     * @param root
     * @param res
     */
    private void postOrder(TreeNode root,List<Integer> res){
        if (root == null) return;
        // 左子树
        postOrder(root.left,res);
        // 右子树
        postOrder(root.right,res);
        // 根节点
        res.add(root.val);
    }
}
// 二叉树层序遍历 BFS
class Solution102 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.offerLast(root);
            while (!queue.isEmpty()){
                // 保存该层遍历结果
                List<Integer> level = new ArrayList<>();
                int size = queue.size();
                while (size > 0){
                    TreeNode node = queue.pollFirst();
                    level.add(node.val);
                    if (node.left != null){
                        queue.offerLast(node.left);
                    }
                    if (node.right != null){
                        queue.offerLast(node.right);
                    }
                    size--;
                }

                res.add(level);
            }
            return res;


        }
}
class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                level.add(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            res.add(level);
        }
        Collections.reverse(res);
        return res;
    }
}
class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();

            res.add(queue.peekFirst().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (node.right!=null) queue.offerLast(node.right);
                if (node.left != null) queue.offerLast(node.left);
            }
        }
        return res;
    }
}
class Solution637 {
    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<Double> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();

            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                sum += node.val;
                if (node.right!=null) queue.offerLast(node.right);
                if (node.left != null) queue.offerLast(node.left);
            }
            res.add(sum/size);
        }
        return res;
    }
}
// 翻转二叉树
class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        // DFS 迭代实现
        if (root == null) return root;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                swapChildren(node);
                if (node.left!=null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }

        }
        return root;




        // 递归实现翻转
        /*if (root == null) return root;
        // 先序遍历
        swapChildren(root);// 中
        swapChildren(root.left);// 左
        swapChildren(root.right);// 右
        return root;*/
    }
    // 交换左右孩子
    private void swapChildren(TreeNode root){
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
    }
}
class Solution617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left,root2.left);
        root1.right = mergeTrees(root1.right,root2.right);
        return root1;
    }
}