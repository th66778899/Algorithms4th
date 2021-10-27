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