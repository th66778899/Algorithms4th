package base;

import java.util.*;

/**
 * @Author tho
 * @Date 2021/10/25/12:57
 * @ProjectName Algorithms4th
 * @ClassName: IterationTraversal
 * @Description: TODO 迭代遍历
 */
public class IterationTraversal {
}
class Solution144_it {


    /**
     * @Author tho
     * @Date 2021/10/25 11:45
     * @param root
     * @Return List<Integer>
     * @Description: 递归前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            // 先序遍历是中 左 右  用栈模拟递归  就要是 中 右 左
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }

}

class Solution94_it {

    /**
     * @Author tho
     * @Date 2021/10/25 11:48
     * @param root
     * @Return List<Integer>
     * @Description: 迭代中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        // 使用栈来模拟中序遍历
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){

            if (cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                // 遍历中节点
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;

            }
        }
        return res;

    }

}
class Solution145_it {

    /**
     * @Author tho
     * @Date 2021/10/25 11:51
     * @param root
     * @Return List<Integer>
     * @Description: 后序迭代遍历
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
        return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            // 后序遍历是 左 右 中
            // 反转左右顺序 中 左 右 -> 中 右 左 -> 数组逆转就是 左 右 中
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        Collections.reverse(res);
        return res;
    }

}

