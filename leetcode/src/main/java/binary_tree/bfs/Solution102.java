package binary_tree.bfs;


import java.util.*;

// leetcode 102
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Solution102 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 使用队列来实现BFS
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();// 保存每一层的结果
            int size = queue.size();
            // 队列里存储了每一层的节点信息
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);// 出队列
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }

            }
            res.add(level);
        }
        return res;
    }
}
// leetcode104 简单
class Solution104{
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
                if (cur.right!=null) queue.offer(cur.right);
            }
            res++;
        }


        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode20 = new TreeNode(20);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode20.left = treeNode15;
        treeNode20.right = treeNode7;
        treeNode3.left = treeNode9;
        treeNode3.right = treeNode20;
        Solution104 solution104 = new Solution104();
        int i = solution104.maxDepth(treeNode3);
        System.out.println(i);
    }

}
// leetcode 199 中等
class Solution199{
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.offer(root);
        while (!queue.isEmpty()){

            int size = queue.size();
            res.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode3.right = treeNode4;
        treeNode1.right = treeNode3;
        treeNode2.right = treeNode5;
        treeNode1.left = treeNode2;
        List<Integer> integers = new Solution199().rightSideView(treeNode1);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }


    }
}
// leetcode 101
class Solution101{
    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null||(root.left == null&&root.right == null)) return true;
        queue.offer(root.left);
        queue.offer(root.right);
        int num = 0;
        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();
                // 两个节点都为空 继续循环
                if (left == null && right == null){
                    continue;
                }
                if (left == null || right == null){
                    return false;
                }
                if (left.val != right.val){
                    return false;
                }
                // 将左孩子的左节点 右孩子的右节点
                queue.offer(left.left);
                queue.offer(right.right);
                // 左孩子的右节点 右孩子的左节点
                queue.offer(left.right);
                queue.offer(right.left);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode21 = new TreeNode(2);
        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode31 = new TreeNode(3);
        TreeNode treeNode32 = new TreeNode(3);
        TreeNode treeNode41 = new TreeNode(4);
        TreeNode treeNode42 = new TreeNode(4);
        treeNode21.left = treeNode31;
        treeNode21.right = treeNode41;
        treeNode22.left = treeNode42;
        treeNode22.right = treeNode32;
        treeNode1.left = treeNode21;
        treeNode1.right = treeNode22;
        Solution101 solution101 = new Solution101();
        System.out.println(solution101.isSymmetric(treeNode1));
    }
}
// leetcode103 中等
class Solution103{

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                level.add(cur.val);
                // 从左往右遍历
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);
                /*if (!leftToRight){
                    // 从左往右遍历
                    if (cur.left!=null) queue.offer(cur.left);
                    if (cur.right!=null) queue.offer(cur.right);
                }else {
                    // 从右往左遍历
                    if (cur.right!=null) queue.offer(cur.right);
                    if (cur.left!=null) queue.offer(cur.left);
                }*/

            }
            if (!leftToRight){
                Collections.reverse(level);
            }
            leftToRight = !leftToRight;

            res.add(level);

        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode2.left = treeNode4;
        treeNode3.right = treeNode5;
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        List<List<Integer>> lists = new Solution103().zigzagLevelOrder(treeNode1);
        for (List<Integer> list : lists) {
            System.out.println();
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
        }
    }
}
// leetcode 111 简单
class Solution111{
    public int minDepth(TreeNode root) {
        int res = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        if (root==null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                res++;
                TreeNode cur = queue.pop();
                if (cur.left==null&&cur.right==null){
                    // 当前节点为叶子节点
                    return res;
                }
                if (cur.left!=null) queue.offer(cur.left);
                if (cur.right!=null) queue.offer(cur.right);

            }

        }

        return res;
    }
}
// leetcode515 中等
class Solution515{
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null)return res;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int max = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                if (cur.val > max) max = cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(max);

        }
        return res;
    }
}
class TreeNodeCom {
    public int val;
    public List<TreeNodeCom> children;

    public TreeNodeCom() {}

    public TreeNodeCom(int _val) {
        val = _val;
    }

    public TreeNodeCom(int _val, List<TreeNodeCom> _children) {
        val = _val;
        children = _children;
    }
}
// leetcode 429 中等
class Solution429{
    public List<List<Integer>> levelOrder(TreeNodeCom root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNodeCom> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNodeCom cur = queue.poll();
                level.add(cur.val);
                for (TreeNodeCom child : cur.children) {
                    if (child!=null){
                        queue.offer(child);
                    }
                }
            }
            level.add(null);
            res.add(level);
        }
        return res;
    }
}

