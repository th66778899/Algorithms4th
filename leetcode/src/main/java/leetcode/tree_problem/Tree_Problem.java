package leetcode.tree_problem;

import java.util.*;

public class Tree_Problem {
}
// 中序遍历
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        // 使用指针指向下一个将要遍历节点
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null){
                // 一直指向左子树,直到到达末尾
                stack.push(cur);
                cur = cur.left;
            }else {
                // 左子树为空,取出栈中节点,并指向右子树
                cur = stack.peek();
                stack.poll();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
    // 递归
    /*public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        inOrder(root,res);
        return res;
    }
    private void inOrder(TreeNode root,List<Integer> res){
        if (root == null) return;
        inOrder(root.left,res);
        res.add(root.val);
        inOrder(root.right,res);
    }*/
}
class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null){
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            stack.poll();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }

    // 递归法
    /*public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        preOrder(root,res);
        return res;
    }
    private void preOrder(TreeNode root,List<Integer> res){
        if (root == null){
            return;
        }
        res.add(root.val);
        preOrder(root.left,res);
        preOrder(root.right,res);
    }*/
}
class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null){
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            stack.poll();
            res.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        Collections.reverse(res);
        return res;
    }
    // 递归
    /*public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        postOrder(root,res);
        return res;
    }
    private void postOrder(TreeNode root,List<Integer> res){
        if (root == null) return;
        postOrder(root.left,res);
        postOrder(root.right,res);
        res.add(root.val);
    }*/
}
// 层序遍历  BFS
class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            // 一定要固定size大小 因为queue.size() 是不断变化的
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peek();
                queue.pollFirst();
                level.add(node.val);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peekFirst();
                queue.pollFirst();
                level.add(node.val);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res.add(level);
        }
        Collections.reverse(res);
        return res;
    }
}
class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            // ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peek();
                queue.pollFirst();
                if (i == size - 1){
                    res.add(node.val);
                }
                // level.add(node.val);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            /*if (!level.isEmpty()){
                res.add(level.get(level.size() - 1));
            }*/
        }
        return res;
    }
}
class Solution637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                sum += node.val;
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res.add(sum / size);
        }
        return res;
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
class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.pollFirst();
                level.add(node.val);
                for (Node child : node.children) {
                    if (child != null){
                        queue.offerLast(child);
                    }
                }
            }
            res.add(level);
        }
        return res;
    }
}
class Solution515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return res;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                max = Math.max(max,node.val);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res.add(max);
        }

        return res;
    }
}
class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;
    public Node1 next;

    public Node1() {}

    public Node1(int _val) {
        val = _val;
    }

    public Node1(int _val, Node1 _left, Node1 _right, Node1 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
class Solution116 {
    public Node1 connect(Node1 root) {
        LinkedList<Node1> queue = new LinkedList<>();
        if (root == null) return root;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            // ArrayList<Node1> level = new ArrayList<>();
            Node1 temp = queue.get(0);
            for (int i = 1; i < size; i++) {
                temp.next = queue.get(i);
                temp = queue.get(i);

            }
            for (int i = 0; i < size; i++) {
                Node1 node = queue.pollFirst();
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }

        }
        return root;
    }
}
class Solution117 {
    public Node1 connect(Node1 root) {
        LinkedList<Node1> queue = new LinkedList<>();
        if (root == null) return root;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            // ArrayList<Node1> level = new ArrayList<>();
            Node1 temp = queue.get(0);
            for (int i = 1; i < size; i++) {
                temp.next = queue.get(i);
                temp = queue.get(i);

            }
            for (int i = 0; i < size; i++) {
                Node1 node = queue.pollFirst();
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }

        }
        return root;
    }
}
class Solution104 {
    public int maxDepth(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return 0;
        queue.offerLast(root);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res++;
        }
        return res;
    }
}
class Solution111 {
    public int minDepth(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return 0;
        queue.offerLast(root);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left == null && node.right == null){
                    return res;
                }
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }

        }
        return res;
    }
}
class Solution226 {
    // 迭代 DFS
    public TreeNode invertTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return root;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.poll();
            reverse(node);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);

        }
        return root;
    }



    // 迭代 BFS
    /*public TreeNode invertTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return root;
        queue.offerLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                reverse(node);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
        }
        return root;

    }*/




    // 递归法
    /*public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        // 先序遍历
        reverse(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }*/
    private void reverse(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
class Solution101 {
    // 迭代法
    public boolean isSymmetric(TreeNode root) {
        System.out.println();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return true;
        queue.offerLast(root.left);
        queue.offerLast(root.right);
        while (!queue.isEmpty()){
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            if (left == null && right == null){
                continue;
            }
            if (left != null || right != null || left.val != right.val){
                return false;
            }
            // left,right  都不为空 且 left,right 值不相等
            queue.offerLast(left.left);
            queue.offerLast(right.right);
            queue.offerLast(left.right);
            queue.offerLast(right.left);

        }
        return true;

    }



    // 递归法
    /*public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compare(root.left,root.right);
    }
    public boolean compare(TreeNode left,TreeNode right){
        // 判断左右子树是不是符合要求,有几种情况
        if (left != null && right == null){
            return false;
        }else if (left == null && right != null){
            return false;
        }else if (left == null && right == null){
            return true;
        }else if (left.val != right.val){
            return false;
        }
        // 现在剩下的情况就是左右都不为空 且 左子树的值  等于 右子树的值
        // 递归 进入下一层
        boolean outside = compare(left.left,right.right);
        boolean inside = compare(left.right,right.left);
        boolean res = outside && inside;
        return res;

    }*/
}
class Solution100 {

    // 迭代
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(p);
        queue.offerLast(q);
        while (!queue.isEmpty()){
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            if (left == null && right == null){
                continue;
            }
            if (left == null || right == null || left.val != right.val){
                return false;
            }
            queue.offerLast(left.left);
            queue.offerLast(right.left);
            queue.offerLast(left.right);
            queue.offerLast(right.right);
        }
        return true;

    }
    // 递归
    /*public boolean isSameTree(TreeNode p, TreeNode q) {
        return check(p,q);
    }
    private boolean check(TreeNode left,TreeNode right){
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null || left.val != right.val){
            return false;
        }
        // 左右都不为空 且 左右值 相等
        // 进入递归
        boolean l = check(left.left,right.left);
        boolean r = check(left.right,right.right);
        return l && r;
    }*/
}
class Solution572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root,subRoot);
    }
    private boolean dfs(TreeNode p,TreeNode q){
        if (p == null) return false;
        return check(p, q) || dfs(p.left,q) || dfs(p.right,q);
    }
    private boolean check(TreeNode left,TreeNode right){
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null || left.val != right.val){
            return false;
        }
        boolean l = check(left.left,right.left);
        boolean r = check(left.right,right.right);
        return l && r;
    }
}