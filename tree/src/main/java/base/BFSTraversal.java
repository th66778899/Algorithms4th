package base;

import java.util.*;

/**
 * @Author tho
 * @Date 2021/10/25/13:22
 * @ProjectName Algorithms4th
 * @ClassName: BFSTraversal
 * @Description: 二叉树层序遍历 BFS
 */
public class BFSTraversal {
}
class Solution102 {
    /**
    * @Author tho
    * @Date 2021/10/25 13:26
    * @param root
    * @Return List<List<Integer>>
    * @Description: BFS 二叉树层序遍历
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
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
        int x;
        return res;

    }
}