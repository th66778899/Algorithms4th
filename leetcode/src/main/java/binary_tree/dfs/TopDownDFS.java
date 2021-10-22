package binary_tree.dfs;

// 自顶向下的深度优先搜索

public class TopDownDFS {
    // leetcode 129 中等
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if  (root == null){
            return 0;
        }
        dfs(root,0);
        return sum;
    }
    private void dfs(TreeNode root,int num){
        num = num * 10 + root.val;
        if (root.left == null && root.right == null){
            sum += num;
            return;
        }
        if (root.left!=null){
            dfs(root.left,num);
        }
        if (root.right != null){
            dfs(root.right,num);
        }
    }
}
// leetcode 98 中等 验证二叉搜索树
class Solution98 {
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

}
// leetcode 450 中等
class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if  (root.val < key){
            // key 大于当前节点的值， 向右子树寻找
            root.right = deleteNode(root.right,key);

        }
        else if  (root.val > key){
            // key小于当前节点的值 ， 向左子树寻找
            root.left = deleteNode(root.left,key);
        }else {
            // key 等于 当前节点
            if (root.left == null){
                return root.right;
            }
            if (root.right == null){
                return root.left;
            }
            // 将该节点的左子树头结点 移动到右子树最左侧最下面节点的左子树
            TreeNode temp = root.right;
            while (temp.left!=null){
                temp = temp.left;
            }
            temp.left = root.left;
            root = root.right;
        }

        return root;
    }
}
