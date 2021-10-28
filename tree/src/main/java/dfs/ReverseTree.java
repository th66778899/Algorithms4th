package dfs;


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
        return dfs(root, 1);
    }
    private int dfs(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return depth;
        }
        depth++;
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = dfs(root.left, depth);
        }
        if (root.right != null) {
            right = dfs(root.right, depth);
        }
        return Math.min(left, right);
    }
}