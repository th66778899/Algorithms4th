package recursion;

import netscape.security.UserTarget;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

// 递归
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
public class Recursion {
    // 计算阶乘
    public static int getFactorial(int num){
        if (num == 0) {return 1;}
        int x = num * getFactorial(num - 1);
        return x;
    }

    /*public static void main(String[] args) {
        // 测试阶乘
        *//*int factorial = getFactorial(3);
        System.out.println(factorial);*//*
        // 测试阶乘
        int[] arr = {6,9,-1,8,6};
        mergeSort(arr,0,4);
        for (int i : arr) {
            System.out.print(i+ " ");
        }


    }*/
    // 分治 - 走楼梯
    public static int ladderResolve(int ladder){
        if (ladder == 1) return 1;
        if (ladder == 2) return 2;
        return ladderResolve(ladder - 1) + ladderResolve(ladder - 2);
    }
    // 归并排序
    static void mergeSort(int []arr,int lo,int hi){
        if (hi <= lo) return;
        int mid = (lo + hi) >> 1;// mid = (lo + hi) / 2
        mergeSort(arr,lo,mid);// 左半边数组排序
        mergeSort(arr,mid + 1,hi);// 右半边数组排序
        mergeArray(arr,lo,mid,hi);// 合并两数组

    }
    // 合并数组
    static void mergeArray(int[] arr,int lo,int mid,int hi){
        int[] temp = new int[hi - lo +1];// 临时数组来存储要合并的数组
        int i = lo;// 左半边数组的指针
        int j = mid + 1;// 右半边数组的指针
        int k = 0;// temp数组的指针
        while (i <=mid && j <=hi){
            if (arr[i] <=arr[j]){
                // 升序排序 稳定的排序
                temp[k] = arr[i];
                k++;
                i++;// 移动数组指针
            }else {
                temp[k] = arr[j];
                k++;
                j++;
            }
        }
        // 左半边数组全部存到了临时数组
        while (j <= hi){
            temp[k] = arr[j];
            k++;
            j++;
        }
        // 右半边数组全部存到了临时数组
        while (i<=mid){
            temp[k] = arr[i];
            k++;
            i++;
        }
        // 将临时数组内容复制到原数组中
        for (i = lo,k = 0; i <= hi;k++, i++) {
            // i指针指向原数组
            // k指针指向temp数组
            arr[i] = temp[k];

        }
    }
    // 递归 二叉树的遍历
    // 求二叉树的高度
    static int getTreeHeight(TreeNode root){
        if (root == null) return 0;
        int left = getTreeHeight(root.left);
        int right = getTreeHeight(root.right);
        int height = Math.max(left,right) + 1;
        return height;
    }
    @Test
    void testGetTreeHeight(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        System.out.println(getTreeHeight(treeNode1));

    }
    // 求二叉树某节点所在层数
    static int layer = 0;
    // 1.设置一个全局标志位 找到了该节点 直接返回 减少遍历次数
    static boolean flag = false;
    static void getLayer(TreeNode root,int x){
        if (root == null) return ;
        if (flag) return;
        layer++;
        if (root.val == x){
            flag = true;
            System.out.println(layer);
        }
        getLayer(root.left,x);
        getLayer(root.right,x);
        layer--;// 该二叉树遍历完 需要向上回退 需要将layer层数 --
    }
    @Test
    void testGetTreeLayer(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        getLayer(treeNode1,2);

    }
    // 求二叉树某节点所在层数

    // 2.将节点所在层数作为参数传递

    static void getLayerByP(TreeNode root,int layer,int x){
        if (root == null) return ;
        if (flag) return;
        //layer++;
        if (root.val == x){
            flag = true;
            System.out.println(layer);
        }
        getLayerByP(root.left,layer + 1,x);
        getLayerByP(root.right,layer + 1,x);
        //layer--;// 该二叉树遍历完 需要向上回退 需要将layer层数 --
    }
    @Test
    void testGetTreeLayerByP(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        getLayerByP(treeNode1,2,1);

    }
    // 求二叉树某节点到根节点的路径

    static Deque<Integer> stack = new ArrayDeque<>();// 存储路径信息
    static void getRoad(TreeNode root,int x){
        if (root == null) return ;
        if (flag) return;
        stack.push(root.val);
        if (root.val == x){
            flag = true;
            for (Integer integer : stack) {
                System.out.println(integer);
            }

        }
        getRoad(root.left,x);
        getRoad(root.right,x);
        stack.pop();
    }
    @Test
    void testgetRoad(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
       getRoad(treeNode1,2);


    }
    // 全排列问题
    static final int MAXN = 10;
    static boolean isUsed[] = new boolean[MAXN];
    static Deque<Integer> stack1 = new ArrayDeque<>();// 排列信息
    static int N;
    static void DFS(int index){
        if (index >= N){
            // 输出栈中结果
            for (Integer integer : stack1) {
                System.out.print(integer + " ");
            }
            System.out.println();

        }


        for (int i = 1; i <= N; i++) {
            if (isUsed[i]) continue;// 该数字已经被使用过
            stack1.push(i);
            isUsed[i] = true;
            DFS(index + 1);
            stack1.pop();
            isUsed[i] = false;
        }
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        recursion.N = 3;
        recursion.DFS(0);
    }
}
