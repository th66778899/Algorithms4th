package tree;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
public class Solution {
}

// leetcode 根据中序和后序遍历构造二叉树
class Solution106 {
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        return buildTree1(inOrder,0,inOrder.length,postOrder,0,postOrder.length);
    }
    private TreeNode buildTree1(int[] inOrder,int inLeft,int inRight,
                                int[] postOrder,int postLeft,int postRight){
        // 中序遍历数组全部遍历完意味着树中每个节点都已经完成了构造
        // 整个递归对于节点都是左闭右开 [) 循环不变量
        // 中序数组已经遍历完毕
        if (inRight - inLeft < 1){
            return null;
        }
        // [0,1) 中序左区间仅有一个节点 将其作为树的左子树节点即可
        if (inRight - inLeft == 1){
            return new TreeNode(inOrder[inLeft]);
        }
        // 找出树的root节点 即 后序遍历最后一个节点
        int val = postOrder[postRight - 1];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == val){
                index = i;
                break;
            }
        }
        root.left = buildTree1(inOrder,inLeft,index,postOrder,postLeft,(index - inLeft) + postLeft);
        root.right = buildTree1(inOrder,index + 1,inRight,postOrder,(index - inLeft) + postLeft,postRight - 1);
        return root;

    }
}
// leetcode 前序和中序遍历恢复二叉树
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree1(preorder,0,preorder.length,inorder,0,inorder.length);
    }
    private TreeNode buildTree1(int[] preOrder,int preLeft,int preRight,
                                int[] inOrder,int inLeft,int inRight){
        if (inRight - inLeft < 1){
            return null;
        }
        if (inRight - inLeft == 1){
            return new TreeNode(inOrder[inLeft]);
        }
        int val = preOrder[preLeft];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == val){
                index = i;
                break;
            }
        }
        root.left = buildTree1(preOrder,preLeft + 1,preLeft + (index - inLeft) + 1,
                inOrder,inLeft,index);
        root.right = buildTree1(preOrder,preLeft + (index - inLeft) + 1,preRight,
                inOrder,index + 1,inRight);
        return root;
    }
}
