package base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/10/25/11:44
 * @ProjectName Algorithms4th
 * @ClassName: RecursionTraversal
 * @Description: 递归遍历树
 */
public class RecursionTraversal {

}

class Solution144 {
    ArrayList<Integer> res;

    /**
    * @Author tho
    * @Date 2021/10/25 11:45
    * @param root
    * @Return List<Integer>
    * @Description: 递归前序遍历
    */
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        preTraversal(root);
        return res;
    }
    // 遍历整个树,不需要返回值
    private void preTraversal(TreeNode root){
        if (root == null) return;
        res.add(root.val);
        preTraversal(root.left);
        preTraversal(root.right);
    }
}

class Solution94 {
    ArrayList<Integer> res;
    /**
    * @Author tho
    * @Date 2021/10/25 11:48
    * @param root
    * @Return List<Integer>
    * @Description: 递归中序遍历
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        inTraversal(root);
        return res;
    }
    private void inTraversal(TreeNode root){
        if (root == null) return;
        inTraversal(root.left);
        res.add(root.val);
        inTraversal(root.right);
    }
}
class Solution145 {
    ArrayList<Integer> res;
    /**
    * @Author tho
    * @Date 2021/10/25 11:51
    * @param root
    * @Return List<Integer>
    * @Description: 后序迭代遍历
    */

    public List<Integer> postorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        postTraversal(root);
        return res;
    }
    private void postTraversal(TreeNode root){
        if (root == null) return;
        postTraversal(root.left);
        postTraversal(root.right);
        res.add(root.val);
    }
}
