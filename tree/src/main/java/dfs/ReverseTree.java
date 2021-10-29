package dfs;


import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * @Author tho
 * @Date 2021/10/27/15:57
 * @ProjectName Algorithms4th
 * @ClassName: ReverseTree
 * @Description: 翻转二叉树
 */
public class ReverseTree {

}
class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }


        }
        return root;

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
class Solution590 {
    public List<Integer> postorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            List<Node> children = node.children;
            for (Node child : children) {
                if (child != null) {
                    stack.push(child);
                }
            }
            res.add(node.val);
        }
        Collections.reverse(res);
        return res;
    }

}
class Solution111 {
    public boolean isSymmetric(TreeNode root) {
        // 判断一个树是不是对称二叉树,需要对当前root节点进行判断,
        // 只有当 已root 节点为根节点的二叉树是对称二叉树时,才需要进行递归,判断左右子树是不是对称的
        if (root == null) return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.right);
        queue.offer(root.left);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }else if (left == null && right != null) {
                return false;
            }else if (left != null && right == null) {
                return false;
            }else if (left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }


        return true;

    }
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }else if (left != null && right == null) {
            return false;
        }else if (left == null && right != null) {
            return false;
        }else if (left.val != right.val) {
            return false;
        }
        // 需要递归判断
        boolean out = check(left.left, right.right);
        boolean in = check(left.right, right.left);
        return out && in;
    }
}
class Solution104 {

    int res = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 1);

        return res;
    }
    private void dfs(TreeNode root,int depth) {
        // 前序遍历求深度,后序遍历求高度
        // 使用前序遍历来求深度
        res = depth > res ? depth : res;
        if (root.left == null && root.right == null) return;
        if (root.left != null) {
            depth++;
            dfs(root.left, depth);
            depth--;
        }
        if (root.right != null) {
            depth++;
            dfs(root.right, depth);
            depth--;
        }

    }


}
// N叉树最大深度
class Solution559 {
    public int maxDepth(Node root) {
        return dfs(root);
    }
    private int dfs(Node root) {
        if (root == null) return 0;
        int maxDepth = 0;
        for (Node child : root.children) {
            if (child != null) {
                maxDepth = Math.max(maxDepth, dfs(child)) + 1;
            }
        }
        return maxDepth;
    }
}
class Solution111_dfs{

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        if (root.left == null && root.right != null) {
            return 1 + rightDepth;
        }
        if (root.left != null && root.right == null) {
            return 1+ leftDepth;
        }
        // 已经排除了 左右有一侧树为空的情况
        // 还剩两种情况,左右都为空 左右都不是空,这两种情况可以合并处理
        // 都是左右子树深度最小值 + 1
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
class Solution222 {

    public int countNodes(TreeNode root) {

        if (root == null) return 0;

        return dfs(root);
    }
    private int dfs(TreeNode root) {
        // 利用完全二叉树的性质
        // 完全二叉树节点个数 : 2^深度 - 1
        // 通过判断左右子树的深度来判断是不是一个完全二叉树
        // 一直向左子树方向移动指针,求左子树深度
        // 一直向右子树移动指针,求右子树深度
        // 左右子树深度一致,说明这是一个完全二叉树,可以求节点数
        // 不一致,递归左右子树,找到一个完全二叉树
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0;
        int rightDepth = 0;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (leftDepth << 1) - 1;
        }
        return dfs(root.left) + dfs(root.right);

    }
}
class Solution210 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0;
        int rightDepth = 0;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
}
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        // 二叉树高度 该节点到叶子节点路径长度  可以使用后序遍历
        // 二叉树深度 该节点到根节点路径长度    可以使用前序遍历
        if (dfs(root) == -1) {
            return false;
        }else {
            return true;
        }

    }
    private int dfs(TreeNode root) {
        // 根据二叉树高度来判断是不是一个平衡二叉树
        // 如果二叉树不满足平衡二叉树,返回 -1 , 否则返回当前树的高度
        if (root == null) return 0;
        int leftHeight = dfs(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfs(root.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }else {
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }
}
class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        if (root == null) return res;
        ArrayList<Integer> path = new ArrayList<>();
        dfs(root, res, path);

        return res;
    }
    private void dfs(TreeNode root, List<String> res, List<Integer> path){


        if (root.left == null && root.right == null) {
            // 到达叶子节点,记录结果
            StringBuilder sb = new StringBuilder();
            int length = path.size();
            for (int i = 0; i < length - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(length - 1));
            res.add(sb.toString());

        }
        // 前序遍历 中左右
        // 中

        if (root.left != null) {
            // 遍历左子树
            path.add(root.val);
            dfs(root.left, res, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            // 遍历右子树
            path.add(root.val);
            dfs(root.right, res, path);
            path.remove(path.size() - 1);
        }
    }
}
class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }else if (p == null) {
            return false;
        }else if (q == null) {
            return false;
        }else if (p.val != q.val) {
            return false;
        }
        // 还剩一种情况,左右子树相等情况
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (check(root, subRoot)) {
            return true;
        }
        boolean left = check(root.left, subRoot);
        boolean right = check(root.right, subRoot);
        return left || right;
    }
    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }else if (p == null) {
            return false;
        }else if (q == null) {
            return false;
        }else if (p.val != q.val) {
            return false;
        }
        boolean left = check(p.left, q.left);
        boolean right = check(p.right, q.right);
        return left && right;
    }
}
