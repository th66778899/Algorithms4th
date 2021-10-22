package binary_tree.bst;

import java.util.ArrayList;

// 二叉搜索树
public class BinarySearchTree {
}
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
class Solution700 {
    public TreeNode searchBST(TreeNode root, int val) {
        // 迭代
        while (root!=null){
            if (root.val < val){
                root = root.right;
            }else if (root.val > val){
                root = root.left;
            }else return root;
        }
        return null;

        // 递归法
        /*if (root == null) return null;
        if (root.val == val) return root;
        // 二分查找
        if (root.val < val){
           return searchBST(root.right,val);
        }
        if (root.val > val){
           return searchBST(root.left,val);
        }
        return null;*/

    }
}
class Solution98 {
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;// 空树也是二叉搜索树
        boolean left = isValidBST(root.left);
        if (pre!=null && pre.val >= root.val) return false;
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }
}
class Solution530 {
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;// 记录前一个节点 用来比较前一个节点和当前节点差值
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        getMinimum(root);
        return min;
    }
    private void getMinimum(TreeNode root){
        if (root == null) return;
        // 中序遍历 左 中 右
        getMinimum(root.left);
        if (pre != null){
            min = Math.min(min,root.val - pre.val);
        }
        pre = root;
        getMinimum(root.right);
    }
}
class Solution501 {
    int count;// 某数出现次数
    int maxCount;// 树中数字出现最大的次数
    TreeNode pre;// 指向前一个节点,为了计算前后元素是不是相等
    ArrayList<Integer> res;// 记录最终结果
    public int[] findMode(TreeNode root) {
        count = 0;
        maxCount = 0;

        res = new ArrayList<>();
        getMost(root);
        int[] ress = new int[res.size()];
        int i = 0;
        for (Integer re : res) {
            ress[i] = re;
            i++;
        }

        return ress;
    }
    private void getMost(TreeNode root){
        if (root == null) return;
        getMost(root.left);// 遍历左节点
        if (pre == null){
            // 第一个节点 次数 + 1
            count = 1;
        }else if (pre.val == root.val){
            count++;
        }else {
            // 前后元素不相等
            count = 1;// 出现次数置为1
        }
        pre = root;
        if (count == maxCount){
            res.add(root.val);
        }
        if (count > maxCount){
            maxCount = count;
            res.clear();
            res.add(root.val);
        }
        getMost(root.right);
    }
}
class Solution538 {
    TreeNode pre;// 指向之前遍历的节点

    public TreeNode convertBST(TreeNode root) {
        sumTree(root);
        return root;
    }
    private void sumTree(TreeNode root){
        if (root == null) return;
        // 遍历顺序 右 中 左
        // 1 3 5 数组求右往左的累加 9 8 5
        sumTree(root.right);

        if (pre != null){
            root.val += pre.val;
        }
        pre = root;
        sumTree(root.left);
    }
}
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return getAncestor(root,p,q);
    }
    private TreeNode getAncestor(TreeNode root,TreeNode p,TreeNode q){
        if (root == null || root == p || root == q) return root;
        TreeNode left = getAncestor(root.left,p,q);
        TreeNode right = getAncestor(root.right,p,q);
        if (left != null && right != null){
            return root;
        }
        if (left == null && right != null){
            return right;
        }else if (left != null && right == null){
            return left;
        }else{
            // left right 都不为null
            return null;
        }
    }
}
class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 迭代
        while (root != null){
            if (root.val > p.val && root.val > q.val){
                root = root.left;
            }else if (root.val < p.val && root.val < q.val){
                root = root.right;
            }else return root;
        }
        return null;


        // 递归
        //return bstGetAncestor(root, p, q);
    }
    private TreeNode bstGetAncestor(TreeNode root,TreeNode p,TreeNode q){
        if (root == null) return root;
        // 根据bst的特性 由上往下搜索, 根据 p,q 确定一个区间[p.val,q.val] (不知道p,q大小关系 假设 这样)
        // 有三种情况 root.val < p.val 向右子树遍历 在区间内 返回root 在区间右侧 向左子树遍历
        if (root.val > p.val && root.val > q.val){
            TreeNode left = bstGetAncestor(root.left,p,q);
            if (left != null) return left;
        }
        if (root.val < p.val && root.val < q.val){
            TreeNode right = bstGetAncestor(root.right,p,q);
            if (right != null) return right;
        }
        // root在区间内
        return root;

    }
}
class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 递归法
        if (root == null){
            TreeNode kid = new TreeNode(val);
            return kid;
        }
        if (root.val > val){
            root.left = insertIntoBST(root.left,val);
        }
        if (root.val < val){
            root.right = insertIntoBST(root.right,val);
        }
        return root;



        // 迭代法
        /*if (root == null){
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode parent = root;
        while (cur != null){
            parent = cur;
            if (cur.val < val){
                cur = cur.right;
            }else if (cur.val > val){
                cur = cur.left;
            }
        }
        // parent 指向空节点的前一个节点
        if (parent.val > val){
            TreeNode left = new TreeNode(val);
            parent.left = left;
        }else {
            TreeNode right = new TreeNode(val);
            parent.right = right;
        }
        return root;*/

    }
}
class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val == key){
            if (root.left == null && root.right == null){
                // 该节点为叶子节点 返回null
                return null;
            }else if (root.left == null){
                // 只有右孩子 有孩子替换该节点
                return root.right;
            }else if (root.right == null){
                return root.left;
            }else {
                // 有左右 两个孩子
                // 需要将该节点的左孩子节点 加到 右子树最左侧的节点 左边
                TreeNode cur = root.right;
                while (cur.left != null){
                    cur = cur.left;
                }
                // 此时cur指向右子树最左侧的节点,之后需要将cur 的左子树 由null 变为 root 节点的左孩子
                cur.left = root.left;
                // 需要将root 指向 root.right
                root = root.right;
                return root;
            }


        }
        if (root.val < key){
            root.right = deleteNode(root.right,key);
        }
        if (root.val > key){
            root.left = deleteNode(root.left,key);
        }
        return root;
    }
}
class Solution669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val < low){
            // 当前节点值 小于 low
            // 需要将该节点删除,其左子树必定也小于low ,不需要考虑左子树 只需要考虑右子树
            TreeNode right = trimBST(root.right,low,high);
            return right;
        }
        if (root.val > high){
            TreeNode left = trimBST(root.left,low,high);
            return left;
        }
        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);
        return root;
    }
}
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBst(nums,0,nums.length - 1);
    }
    private TreeNode buildBst(int[] nums,int left,int right){
        // 循环不变量 [] 左闭右闭区间
        if (left > right){
            return null;
        }
        int mid = left + right - left >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBst(nums,left,mid - 1);
        root.right = buildBst(nums,mid + 1,right);
        return root;
    }
}
