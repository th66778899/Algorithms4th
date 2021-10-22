package datastructure;


import sun.reflect.generics.tree.Tree;

import java.lang.reflect.ParameterizedType;
import java.util.*;

// 实现二叉树 BST binary search tree
public class MyBinaryTree {
    static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
    private TreeNode root;// 树的根部

    // 寻找树中节点
    public TreeNode get(int key){
        TreeNode cur = root;
        while (cur!=null && cur.val !=key){
            if (key < cur.val){
                // 向左子树遍历
                cur = cur.left;
            }else {
                // 向右子树遍历
                cur = cur.right;
            }
        }
        return cur == null ? null : cur;// 找到了 cur 不是null 返回cur
                                        // 没有找到 cur == null 返回null
    }
    // 插入元素到二叉树中
    public void insert(int key){
        if (root == null){
            root = new TreeNode(key);
            return;
        }
        TreeNode cur = root;// 插入元素 应该在的位置
        TreeNode parent = null;// 插入元素应在位置的 父节点
        while (true){
            parent = cur;
            // 遍历整个二叉树 寻找应该插入的位置
            if (key < parent.val){
                // 向左子树遍历
                cur = parent.left;
                if (cur == null){
                    // 遍历到了要插入的位置
                    parent.left = new TreeNode(key);
                    return;
                }
            }else if (parent.val < key){
                // 向右子树遍历
                cur = parent.right;
                if (cur == null){
                    parent.right = new TreeNode(key);
                    return;
                }

            }else {
                return;// 节点已经插入 BST 不允许有节点值相同
            }

        }
    }
    // 删除二叉树节点
    public boolean delete(int key){
        // 遍历二叉树 找出该值是不是存在于二叉树中
        TreeNode parent = root;// 删除节点的父节点
        TreeNode cur = root;// 要删除的节点
        boolean isLeftChild = false;// 判断是不是左子树
        while (cur !=null && cur.val!=key){
            parent = cur;
            if (key < parent.val){
                // 遍历左子树
                cur = cur.left;// 确定要删除的节点是左子树还是右子树
                isLeftChild = true;
            }else {
                // 遍历右子树
                cur = cur.right;
                isLeftChild = false;
            }
        }
        if (cur == null){
            return false;// key不存在与该树中
        }
        // 删除节点的 情况有三种
        // 1.叶子节点 最简单
        // 2.有一个子节点
        // 3.有两个子节点

        // 1.叶子节点
        if (cur.left==null && cur.right == null){
            // 要删除节点是叶子节点
            if (cur == root){
                // 要删除节点是根节点 且根节点没有子节点
                root = null;
            }
            else if (isLeftChild){
                // 要删除节点是左节点
                parent.left = null;
            }else {
                // 要删除节点是右节点
                parent.right = null;
            }

        }
        // 2.要删除的节点有 一个子节点
        else if (cur.left == null && cur.right != null){
            // 有一个右节点
            // 要删除的节点是一个左节点
            if (cur == root){
                // 如果要删除的是根节点
                root = cur.right;
            }else if (isLeftChild){
                // 要删除节点是左节点
                parent.left = cur.right;
            }else {
                // 要删除节点是右节点
                parent.right = cur.right;
            }
        }else if (cur.left != null && cur.right == null){
            // 有一个左节点
            if (cur == root){
                root = cur.left;
            }else if (isLeftChild){
                parent.left = cur.left;
            }else {
                parent.right = cur.left;
            }
        }
        // 3.有两个子节点
        else {
            TreeNode successor = getSuccessor(cur);
            if (cur == root){
                root = successor;
            }else if (isLeftChild){
                cur.left = successor;
            }else{
                cur.right = successor;
            }
            // 将左子树附着在successor 左边
            successor.left = cur.left;

        }
        return true;

    }
    // 删除有两个子节点的 节点 需要一个辅助函数
    // 提上来左子树最大值 或 右子树最小值 都是符合的 都需要一样的复杂度
    // 假设要提上来的是 右子树的最小值
    private TreeNode getSuccessor(TreeNode node){
        // 找出要替换删除节点的 新结点
        TreeNode successor = null;
        TreeNode successorParent = null;
        TreeNode cur = node.right;
        // 找右子树的最小值 一直遍历 左子树就行
        while (cur!=null){
            successorParent = successor;
            successor = cur;
            cur = cur.left;

        }
        // 将要删除节点 及其右子树处理完
        if (successor!=node.right){
            // 将要提上来的节点的右节点 附在 其父节点的左边
            successor.left = successor.right;
            // 将要提上来的节点 提到要删除节点的
            successor.right = node.right;
        }
        return successor;

    }
    // 树的遍历 先序遍历 中序遍历 后序遍历 递归实现
    public static void preOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        System.out.println(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    // 先序遍历 迭代实现
    public static List<Integer> preOrderTraversalIteration(TreeNode root){
        // 递归实现底层使用栈来实现递归
        ArrayList<Integer> res = new ArrayList<Integer>();// 保存迭代遍历二叉树的结果
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();// 借助栈模拟实现递归
        if (root == null){
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode tempNode = stack.pop();
            if (tempNode!=null){
                res.add(tempNode.val);
                // 先序遍历是 中左右
                // 入栈顺序变为 右 - 左
                stack.push(tempNode.right);
                stack.push(tempNode.left);
            }
        }
        return res;
    }
    // 中序遍历 递归实现
    public static void inOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }

        preOrderTraversal(root.left);
        System.out.println(root.val);
        preOrderTraversal(root.right);
    }
    // 中序遍历 迭代实现
    public static List<Integer> inOrderTraversalIteration(TreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;// 使用一个指针来指向访问的节点
        while (cur!=null || !stack.isEmpty()){
            if (cur!=null){
                stack.push(cur);
                cur = cur.left;// 一直遍历左子树，直到遍历到叶子节点
            }else {
                // 如果遍历到了叶子节点，将最后的叶子节点出栈 数值加入数组中
                TreeNode tempNode = stack.pop();
                res.add(tempNode.val);

                cur = tempNode.right;
            }
        }

        return res;
    }

    // 后序遍历 递归实现
    public static void postOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }

        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
        System.out.println(root.val);
    }
    // 后序遍历 迭代实现
    public static List<Integer> postOrderTraversalIteration(TreeNode root){
        // 使用栈来模拟迭代遍历二叉树
        // 后序遍历 是 左 右 中 先序遍历时 中 左 右 先序遍历将左右子树的遍历顺序改变 - 中 右 左 再将结果数组逆序 就是后序遍历迭代实现
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        if (root == null){
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode tempNode = stack.pop();
            if (tempNode!=null){
                res.add(tempNode.val);
                // 先将左子树入栈 再将右子树入栈
                stack.push(tempNode.left);
                stack.push(tempNode.right);
            }
        }
        // 将数组逆序
        // 数组 双指针翻转数组
        Collections.reverse(res);

        return res;
    }
}
