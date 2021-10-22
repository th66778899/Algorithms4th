package leetcode;


import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;
import sun.nio.cs.ext.MacArabic;

import java.lang.reflect.ParameterizedType;
import java.util.*;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class Problem {
}
// LeetCode 2 中等
class Solution2 {
    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建两个引用 一个引用用来指向头结点
        // 一个引用用来 构造链表
        ListNode res = new ListNode(-1);
        ListNode temp = res;
        int carry = 0;// 进位
        while (l1 != null || l2 != null){

            int num1 = (l1 != null) ? l1.val : 0;
            int num2 = (l2 != null) ? l2.val : 0;
            int sum = num1 + num2 + carry;
            ListNode newNode = new ListNode(sum % 10);
            temp.next = newNode;
            carry = sum > 9 ? 1 : 0;// 判断是不是要进位
            temp = temp.next;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2  != null){
                l2 = l2.next;
            }
        }
        if (carry == 1){
            ListNode listNode = new ListNode(1);
            temp.next = listNode;
        }
        return res.next;

    }

    public static void main(String[] args) {
        int[] a = {2,4,3};
        ListNode listNode1 = ArrToListNode.BuildNodeList(a);
        int[] b = {5,6,4};
        ListNode listNode2 = ArrToListNode.BuildNodeList(b);
        ListNode listNode = new Solution2().addTwoNumbers(listNode1, listNode2);
        System.out.println(listNode);




    }

}
// leetcode4 困难
class Solution4{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0.0;
    }



}
// leetcode 234 简单
class Solution234 {
    public boolean isPalindrome(ListNode head) {
        // 如果为空或者仅有一个节点，返回true
        if (head == null && head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (fast != null && fast.next != null){
            pre = slow;  // 记录slow的前一个结点
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;  // 分割两个链表

        // 前半部分
        ListNode cur1 = head;
        // 后半部分。这里使用了反转链表
        ListNode cur2 = reverseList(slow);

        while (cur1 != null){
            if (cur1.val != cur2.val) return false;

            // 注意要移动两个结点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }
    ListNode reverseList(ListNode head){
        // 反转链表
        ListNode tmp = null;
        ListNode pre = null;
        while (head != null){
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    /*public boolean isPalindrome(ListNode head) {
        // 快慢指针,找到链表中间节点
        // 将链表分为前后两部分,将后半部分链表反转
        // 依次比较前后两部分链表的值
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        ListNode pre = new ListNode(0);// 该指针指向slow指针的前一个节点,用来分割链表
        pre.next = head;
        while (slow!=null && fast.next != null){
            count++;
            pre = pre.next;
            slow = slow.next;
            fast = fast.next.next;
        }
        // pre指针指向的是后半部链表头结点的前一个节点,用来反转链表
        ListNode cur = slow;
        ListNode temp = null;
        while (cur != null){
            temp = cur.next;

            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        while (count > 0){

            if (head.val != pre.val){
                return false;
            }
            count--;
            head = head.next;
            pre = pre.next;
        }
        return true;
    }*/
}
// leetcode 338 简单  O(n) 解法  动态规划
class Solution338{
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }



        return res;
    }
}
// leetcode 448 简单
class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                 swap(nums, i, nums[i] - 1);
                /*int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[nums[i] - 1] = temp;*/
            }

        }

        for (int j = 0; j < nums.length; j++) {
            System.out.print(nums[j] + " ");
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j+1){
                res.add(j + 1);
            }
        }

        
        
        return res;
    }
    void swap(int[] arr,int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        List<Integer> list = new Solution448().findDisappearedNumbers(new int[]{1, 1, 1});

    }
}
// leetcode 41 困难
class Solution41{
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]){
                swap(nums,i,nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1){
                return i + 1;
            }
        }
        return n + 1;

    }
    void swap(int[] arr,int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
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
class Solution543 {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        // 依次遍历每个节点,求出各个节点的左右子树最大深度
        // 通过全局变量来保存左右子树最大深度之和
        // 递归终止条件
        if (root == null || (root.left == null && root.right == null)){
            return 0;
        }
        maxDepth(root);
        return res;

    }
    private int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        res = Math.max(res,leftDepth + rightDepth);
        return (Math.max(leftDepth,rightDepth)) + 1;
    }
}
// leetcode19 中等
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;// 要删除节点的前驱节点

        ListNode slow = head;// 慢指针
        ListNode fast = head;// 快指针
        while (n > 0){
            fast = fast.next;
            n--;
        }
        // 同时移动快慢指针
        while (fast != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        // pre指向要删除节点的前驱节点,slow指向要删除节点
        pre.next = pre.next.next;

        return dummyNode.next;
    }
}
// leetcode 22 中等
class Solution22 {
    public List<String> generateParenthesis(int n) {
        // 将左右括号匹配转换成 得分情况 添加左括号 +1 田间右括号 -1
        // 分数变化范围确定 [0,n]
        // 括号匹配问题的两个性质
        // 括号总个数为2*n 左括号n个 右括号n个
        // 右括号左边必有左括号 分数变化左边界为 >= 0
        // 当括号个数符合,且分数为0时 该组合为题目要求组合
        ArrayList<String> res = new ArrayList<>();
        dfs(0,2*n,0,n,"",res);
        return res;
    }

    /**
     *
     * @param i 当前dfs递归内的括号个数
     * @param length 所要求的括号总个数
     * @param score 当前所得分值
     * @param maxScore 最大分值
     * @param str 当前递归内的字符串
     * @param res 最终结果
     */
    void dfs(int i,int length,int score,int maxScore,String str,List<String> res){
        // 如果当前括号个数符合 , 且 分数情况符合,记录当前字符串
        if (i == length){
            if (score == 0){
                res.add(str);
            }
        }else {
            // 添加左括号
            if (score + 1 <= maxScore){
                dfs(i + 1,length,score + 1,maxScore,str + "(",res);
            }
            // 添加右括号
            if (score - 1 >= 0){
                dfs(i + 1,length,score - 1,maxScore,str + ")",res);
            }
        }
    }
}
// leetcode 48
class Solution48 {
    public void rotate(int[][] matrix) {
        // 矩阵旋转90° 先上下交换,再对角线交换
        int row = matrix.length;
        int column = matrix[0].length;
        // 上下交换
        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < column; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[row - 1 - i][j];
                matrix[row - 1 - i][j] = temp;
            }
        }
        // 对角线交换
        for (int i = 0; i < row ; i++) {
            for (int j = i+1; j < column; j++) {
                if (i != j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }
}
// leetcode 64 中等
class Solution64 {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];
        // 初始化dp数组
        // 初始化第一行
        dp[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        // 初始化第一列
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] +  grid[i][0];
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        // 遍历数组
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(grid[i][j] + dp[i-1][j] ,grid[i][j] + dp[i][j-1] );
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[row-1][column-1];
    }
}
// leetcode 75 中等
class Solution75 {
    public void sortColors(int[] nums) {
        // n0,n1 表示 0开始位置 1开始位置
        int n0 = 0,n1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            nums[i] = 2;
            if (num < 2){
                nums[n1] = 1;
                n1++;
            }
            if (num < 1){
                nums[n0] = 0;
                n0++;
            }

        }

    }
}

// leetcode 114 中等
class Solution114 {
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null){
            root = root.right;
        }
        root.right = temp;
    }
}
// leetcode 128 中等
class Solution128 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hash = new HashSet<>();
        for (int num : nums) {
            hash.add(num);
        }
        int res = 0;
        for (Integer integer : hash) {
            // 找到一个序列的头部 条件 [不存在该值 - 1的数字]
            if (!hash.contains(integer - 1)){
                int y = integer;
                // 找到此序列的长度,找到该值之后所有 + 1 的情况
                while (hash.contains(y+1)){
                    y++;

                }
                res = Math.max(res,y-integer + 1);
            }
        }
        return res;
    }
}
// leetcode 142  中等
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        // 环的初始节点有两个节点址线
        ListNode fast = head;
        ListNode slow = head;
        // 第一次相遇,slow 走了 nk k为环长度
        while (true){
            if (fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                break;
            }
        }
        fast = head;
        // 第二次相遇 fast 头节点开始 走a步  与 slow指针相遇
        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
}
// leetcode 146
class LRUCache {
    class Node{
        int key,value;
        Node left,right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }
    int n;// 缓存大小
    Node head,tail;// 两个哨兵
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        n = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        map = new HashMap<>();
        head.right = tail;
        tail.left = head;
    }


    public int get(int key) {
        if (map.containsKey(key)){
            refresh(map.get(key));

            return map.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)){
            node = map.get(key);
            node.value = value;

        }else {
            if (n == map.size()){
                // 缓存已满 淘汰双向链表最后一个节点
                Node deleteNode = tail.left;
                delete(deleteNode);
                map.remove(deleteNode.key);
            }
            node = new Node(key, value);
            map.put(key,node);
        }
        refresh(node);
    }
    // 更新该节点使用情况
    private void refresh(Node node){
        // 将该节点删除,之后添加到双链表头部
        delete(node);
        Node temp = head.right;
        head.right = node;
        node.left = head;
        node.right = temp;
        temp.left = node;

    }
    // 删除该节点
    private void delete(Node node){
        if (node.left != null){
            node.left.right = node.right;
            node.right.left = node.left;
            node.left = null;
            node.right = null;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
    }
}
// leetcode 148
class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next   ;
        while (fast!=null && fast.next != null){
            // 移动快慢指针,找到链表中间节点
            fast = fast.next.next;
            slow = slow.next;
        }
        // 将链表分为两部分
        // [head,slow]
        // (slow,null)
        ListNode rightHead = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return merge(left,right);
    }

    /**
     * 合并两个链表
     * @param l1 链表1
     * @param l2 链表2
     */
    private ListNode merge(ListNode l1,ListNode l2){
        ListNode dummyNode = new ListNode(-1);// 虚拟头结点
        ListNode temp = dummyNode;
        while (l1 !=null && l2 != null){
            if (l1.val <= l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null){
            temp.next = l1;

        }else if (l2 != null){
            temp.next = l2;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode listNode = ArrToListNode.BuildNodeList(new int[]{4, 2, 1, 3});
        new Solution148().sortList(listNode);
    }
}
// leetcode 152 中等
class Solution152 {
    public int maxProduct(int[] nums) {
        // 简单情况 全部是正数 最大值仅需要考虑各个值相乘的情况
        // 有负数,负数的出现使得最大值变为最小值,负数的再次出现使得情况相反
        // 需要记录最小值和最大值,当负数出现时,交换最小值 最大值
        int max = Integer.MIN_VALUE;
        int imax = 1;// 记录的最大值
        int imin = 1;// 记录的最小值
        for (int num : nums) {
            if (num < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * num,num);
            imin = Math.min(imin * num,num);
            max = Math.max(max,imax);
        }
        return max;
    }
}
// leetcode 200 中等
class Solution200 {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    dfs(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }
    // 网格问题dfs模板
    // 二叉树 DFS 两个要素
    // 访问邻节点 判断baseCase(是否超出了遍历范围)
    void dfs(char[][] grid,int r,int c){
        // 判断baseCase
        if (!inArea(grid,r,c)){
            return;
        }
        if (grid[r][c] != '1'){
            return;
        }
        // 访问邻接点

        // 遍历过的陆地要加标记,不要进行第二次遍历
        grid[r][c] = '2';
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
    }
    // 判断是否
    boolean inArea(char[][] grid,int r,int c){
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }
}
// leetcode 463 简单
class Solution463 {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 仅有一个岛屿,只需要一次dfs
                if (grid[i][j] == 1){
                    res = dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    private int dfs(int[][] grid,int r,int c){
        if (!inArea(grid,r,c)){
            // 遍历超出网格范围,该边为网格边界的边
            return 1;
        }
        // 该边为陆地和海洋相接的边.返回1
        if (grid[r][c] == 0){
            return 1;
        }
        // 该方格不是未遍历过的格子,是海洋或者已遍历过的格子,跳过
        if (grid[r][c] != 1){
            return 0;
        }
        // 遍历过的格子 标记为2
        grid[r][c] = 2;
        int length =
                dfs(grid, r, c - 1) +
                dfs(grid, r, c + 1) +
                dfs(grid, r - 1, c) +
                dfs(grid, r + 1, c);
        return length;

    }
    private boolean inArea(int[][] grid,int r,int c){
        return 0 <= r && r < grid.length &&
                0 <= c && c < grid[0].length;
    }
}
// leetcode 695 中等
class Solution695 {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    int dfs = dfs(grid, i, j);
                    max = Math.max(max,dfs);
                }
            }
        }
        return max;
    }
    private int dfs(int[][] grid,int r,int c){
        if (!inArea(grid, r, c)){
            return 0;
        }
        if (grid[r][c] != 1){
            return 0;
        }
        grid[r][c] = 2;
        return 1 +
                dfs(grid, r, c - 1)+
                dfs(grid, r, c + 1)+
                dfs(grid, r - 1, c)+
                dfs(grid, r + 1, c);




    }
    private boolean inArea(int[][] grid,int r,int c){
        return 0 <= r && r < grid.length &&
                0 <= c && c < grid[0].length;
    }
}
// leetcode 827 困难
class Solution827 {
    public int largestIsland(int[][] grid) {
        return -1;
    }
}
// leetcode 207 中等 拓扑排序
class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 保存各节点入度值 以及各节点的前驱节点集合
        HashMap<Integer, Integer> degree = new HashMap<>();
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            degree.put(i,0);
        }
        // 初始化各节点入度值 及 前驱节点
        for (int[] prerequisite : prerequisites) {


            // 前驱课程
            int pre = prerequisite[1];
            // 当前课程
            int cur = prerequisite[0];
            // 初始化入度值
            degree.put(cur,degree.get(cur) + 1);

            // 初始化邻接关系
            if ( !adj.containsKey(pre)){
                adj.put(pre,new ArrayList<>());
            }
            adj.get(pre).add(cur);

        }
        // BFS 将入度为0的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (Integer integer : degree.keySet()) {
            if (degree.get(integer) == 0){
                queue.offer(integer);
            }
        }
        //  遍历邻接表,更新入度情况如果入度为0,加入队列中
        while (!queue.isEmpty()){
            int pre = queue.poll();
            if (!adj.containsKey(pre)){
                continue;
            }
            List<Integer> curLesson = adj.get(pre);
            for (Integer integer : curLesson) {
                degree.put(integer,degree.get(integer) - 1);
                if (degree.get(integer) == 0){
                    queue.offer(integer);
                }
            }
        }
        for (Integer integer : degree.keySet()) {
            if (degree.get(integer) != 0){
                return false;
            }
        }
        return true;
    }
}
// leetcode 208 中等
class Trie {
    class TrieNode{
        boolean end;
        TrieNode[] tns = new TrieNode[26];
    }
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.tns[index] == null){
                temp.tns[index] = new TrieNode();

            }
            temp = temp.tns[index];

        }
        temp.end = true;
    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.tns[index] == null){
                return false;
            }
            temp = temp.tns[index];
        }
        return temp.end;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (temp.tns[index] == null){
                return false;
            }
            temp = temp.tns[index];
        }
        return true;
    }
}
// leetcode 221 中等
class Solution221 {
    public int maximalSquare(char[][] matrix) {
// base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int row = matrix.length;
        int column = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[row + 1][column + 1];
        /*for (int i = 0; i < column; i++) {
            if (matrix[0][i] == '1'){
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == '1'){
                dp[i][0] = 1;
            }
        }*/
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1'){
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1],Math.min(dp[i][j],dp[i + 1][j])) + 1;
                    maxSide = Math.max(maxSide,dp[i + 1][j + 1]);
                }

            }
        }
        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < column + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return maxSide * maxSide;

    }

    public static void main(String[] args) {
        System.out.println(new Solution221().maximalSquare(new char[][]{{'0', '1'}, {'1', '0'}}));
    }
}
// leetcode 238 中等
class Solution238 {
    public int[] productExceptSelf(int[] nums) {
        int prefix = 1;
        int suffix = 1;
        int[] res = new int[nums.length];
        // res[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix *= nums[i];

        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }
        return res;





        /*int sum = 1;
        for (int num : nums) {
            sum *= num;
        }
        int prefix[] = new int[nums.length];
        int suffix[] = new int[nums.length];
        Arrays.fill(prefix,1);
        Arrays.fill(suffix,1);
        prefix[0] = nums[0];
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] * nums[i];
        }
        for (int i = nums.length - 2; i >= 0 ; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int pre = 1;
            int cur = 1;
            if (i - 1 >= 0){
                pre = prefix[i-1];
            }
            if (i + 1 < nums.length){
                cur = suffix[i + 1];
            }
            nums[i] = pre * cur;
        }
        return nums;*/

    }

    public static void main(String[] args) {
        new Solution238().productExceptSelf(new int[]{1,2,3,4});
    }
}
// leetcode 287 中等
class Solution287 {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while (slow != fast);
        fast = 0;
        while (slow != fast){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public static void main(String[] args) {
        new Solution287().findDuplicate(new int[]{1,3,4,2,2});
    }
}
// leetcode 438 中等
class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {

        ArrayList<Integer> res = new ArrayList<>();
        int sLength = s.length();
        int pLength = p.length();
        char[] sCnt = new char[26];
        char[] pCnt = new char[26];
        if (sLength < pLength){
            return res;
        }
        for (int i = 0; i < pLength; i++) {
            sCnt[s.charAt(i) - 'a'] += 1;
            pCnt[p.charAt(i) - 'a'] += 1;
        }
        if (Arrays.equals(sCnt,pCnt)){
            res.add(0);
        }
        for (int i = pLength; i < sLength; i++) {
            // 滑动窗口 删除首部元素,尾部新加入元素
            sCnt[s.charAt(i - pLength) - 'a'] -= 1;
            sCnt[s.charAt(i) - 'a'] += 1;
            if (Arrays.equals(sCnt,pCnt)){
                res.add(i - pLength + 1);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        String s = "cbbacc";

        System.out.println(s.subSequence(0, 6) + "length" + s.length());
        ArrayList<Character> res = new ArrayList<>();
        res.add('a');
        res.add('b');
        res.add('c');
        res.contains('a');
        res.remove('a');
    }
}
// leetcode 621 中等
class Solution621 {
    public int leastInterval(char[] tasks, int n) {
        int[] buckets = new int[26];
        for(int i = 0; i < tasks.length; i++){
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        int maxTimes = buckets[25];
        int maxCount = 1;
        for(int i = 25; i >= 1; i--){
            if(buckets[i] == buckets[i - 1])
                maxCount++;
            else
                break;
        }
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);

    }
}
// leetcode 581 中等
class Solution581 {
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int l = 0,r = length - 1;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        while (l <= r && arr[l] == nums[l]){
            l++;
        }

        while (l <= r && arr[r] == nums[r]){
            r--;
        }
        return r - l + 1;
    }
}
// leetcode 704 简单
class Solution704 {
    public int search(int[] nums, int target) {
        int left = 0;
        int length = nums.length;
        int right = length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        if (nums[left] == target){
            return left;
        }else {
            return -1;
        }
    }
}
class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // [left,right]
        while (left <= right){
            int mid = left + ((right - left) / 2);
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return left;
    }
}
class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int [] res = new int[]{-1,-1};
        int length = nums.length;
        if(length == 0) return res;
        int right = length - 1;
        int left = 0;
        int index = -1;
        // 寻找左边界
        while (left < right){
            int mid = left + ((right - left) / 2);
            if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        if (nums[right] != target){
            return res;
        }
        res[0] = left;
        left = 0;
        right = length - 1;
        // 寻找右边界
        while (left < right){
            int mid = left + ((right - left) / 2) + 1;
            if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid;
            }
        }
        res[1] = left;





        // 二分找出 左右边界
        // 找出左边界
        /*while (left < right){
            int mid = left + right >> 1;
            if (nums[mid] >= target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) return res;
        res[0] = left;
        left = 0;
        right = length - 1;
        // 找出右边界
        while (left < right){
            int mid = left + right + 1 >> 1;// 防止死循环
            if (nums[mid] <= target){
                left = mid;
            }else {
                right = mid - 1;
            }

        }
        if (nums[left] != target) return res;
        res[1] = left;
        return res;
*/
        return res;
    }
}
class Solution69 {
    public int mySqrt(int x) {
        long left = 1;
        long right = x;
        while (left < right){
            long mid = left + ((right - left) / 2) + 1;
            System.out.println(mid);
            if (mid * mid < x){
                left = mid;
            }else if (mid * mid == x) {
                return (int) mid;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }
}
class Solution367 {
    public boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;
        while (left <= right){
            long mid = left + ( (right - left) / 2);
            // System.out.println(mid + " " + left + " " + right);

            if (mid * mid <= num ){
                left = mid + 1;
            }else if (mid * mid > num){
                right = mid - 1;
            }
        }
        if (right * right == num ){
            return true;
        }else {
            return false;
        }


    }
}
class Solution27 {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int length = nums.length;
        int right = length - 1;
        for (int num : nums) {
            if (num != val){
                nums[left] = num;
                left++;
            }
        }
        return left;

        /*while ( left < length && right >= 0){
            if (nums[right] == val){
                length--;
                right--;
            }
            else if (nums[left] == val){
                swap(nums,left,right);
                left++;
                right--;
                length--;
            }else {
                left++;
            }
        }
        return length;*/
    }
    void swap(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
class Solution26 {
    public int removeDuplicates(int[] nums) {

        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]){
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }
}
class Solution80 {
    public int removeDuplicates(int[] nums) {
        return process(nums,2);
    }
    private int process(int[] nums,int k){
        int index = 0;
        for (int num : nums) {
            if (index < k || nums[index - k] != num){
               nums[index] = num;
               index++;
            }
        }
        return index;
    }
}
class Solution283 {
    public void moveZeroes(int[] nums) {
        int zero = 0;
        int noneZero = 0;
        while (noneZero < nums.length){
            if (nums[noneZero] != 0){
                swap(nums,zero,noneZero);
                noneZero++;
            }
            zero++;
        }

    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        new Solution283().moveZeroes(new int[]{0,1,0,3,12});
    }
}
class Solution844 {
    public boolean backspaceCompare(String s, String t) {
        int left = s.length() - 1;
        int right = t.length() - 1;
        int skipSNum = 0;
        int skipTNum = 0;
        while (true){
            // 消除s后面 '#' 号
            while (left >= 0){
                if (s.charAt(left) == '#'){
                    skipSNum++;
                    left--;
                }else if (skipSNum > 0){
                    // # 号还没有抵消字符
                    skipSNum--;
                    left--;
                }else {
                    break;
                }
            }
            // left指向s字符串第一个没有被消除的字符
            // 消除t字符串的 # 号
            while (right >= 0){
                if (t.charAt(right) == '#'){
                    skipTNum++;
                    right--;
                }else if (skipTNum > 0){
                    skipTNum--;
                    right--;
                }else {
                    break;
                }
            }
            // right 指向 t 字符串第一个没有被消除的字符
            // 比较两字符串的字符
            // 如果两字符串有的以及遍历完了,需要先判断
            if (left < 0 || right < 0){
                break;
            }
            if (s.charAt(left) != t.charAt(right)){
                return false;
            }
            left--;
            right--;
        }
        if (left == -1 && right == -1){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        //new Solution844().backspaceCompare("xywrrmp","xywrrmu#p");
        String s = "xywrrmp";
        String t = "xywrrmu#p";
        System.out.println(s.charAt(6));
        System.out.println(t.charAt(8));
        System.out.println("s -> " + s.length() + "t -> " + t.length());
    }
}
class Solution977 {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int index = j;
        while (i <= j){
            if (nums[i] * nums[i] <= nums[j] * nums[j]){
                res[index] = nums[j] * nums[j];
                j--;
            }else {
                res[index] = nums[i] * nums[i];
                i++;
            }
            index--;
        }

        return res;
    }

}
class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int res = Integer.MAX_VALUE;
        int windowLength = 0;// 窗口大小
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target){
                // 窗口满足要求,尝试改变窗口内容
                windowLength = right - left + 1;
                res = windowLength < res ? windowLength : res;
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
class Solution643 {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;

        int sum = 0;
        double res = Integer.MIN_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (right - left + 1 == k){
                res = Math.max(res,sum / k);
                sum -= nums[left];
                left++;
            }

        }
        return res;
    }
}
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        // 窗口内字符串 全部不相同
        // 每次遇到重复字符,将该字符对于值设为其后面一位的值,窗口开始设为该字符对应值
        for (int start = 0,end = 0; end < s.length(); end++) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)){
                // 需要将窗口维护为 没有重复字符的窗口
                start = Math.max(start,map.get(cur));
            }
            res = Math.max(res,end - start + 1);
            // 窗口下次开始时为 该字符对应下标的下一位
            map.put(cur,end + 1);

        }
        return res;
    }
}
class Solution1695 {
    public int maximumUniqueSubarray(int[] nums) {
        // 去重 key 表示某个数字 value 表示该数字重复出现时窗口 左指针的值
        HashSet<Integer> set = new HashSet<>();

        int sum = 0;
        int res = Integer.MIN_VALUE;// 保存最大值
        for (int right = 0,left = 0; right < nums.length; right++) {
            int num = nums[right];

            if (!set.contains(num)){
                set.add(num);
                sum += num;
                res = Math.max(res,sum);
            }else {
                // 有重复数字出现
                while (num != nums[left]){
                    sum -= nums[left];
                    set.remove(nums[left]);
                    left++;
                }
                // 保存
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434};
        System.out.println(new Solution1695().maximumUniqueSubarray(arr));
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        char[] s1Cnt = new char[26];
        char[] s2Cnt = new char[26];
        if (s1Length > s2Length){
            return false;
        }
        for (int i = 0; i < s1Length; i++) {
            s1Cnt[s1.charAt(i) - 'a']++;
            s2Cnt[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(s1Cnt,s2Cnt)){
            return true;
        }
        for (int i = s1Length; i < s2Length; i++) {
            s2Cnt[s2.charAt(i - s1Length) - 'a']--;
            s2Cnt[s2.charAt(i) - 'a']++;
            if (Arrays.equals(s1Cnt,s2Cnt)){
                return true;
            }
        }
        return false;
    }
}
class Solution1004 {
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int zeroSum = 0;
        // 将题目转换为 窗口内0 的个数 个数 <= k 是合法的

        for (int right = 0, left = 0; right < nums.length; right++) {
            if (nums[right] == 0){
                zeroSum++;
            }
            while (zeroSum > k){
                // 0的个数超过了题目规定,移动左指针,减少0的个数
                if (nums[left] == 0){

                    zeroSum--;
                }
                left++;
            }
            res = Math.max(res,right - left + 1);
        }
        return res;
    }
}
class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        // int sum = 0;
        for (int right = 0, left = 0; right < nums.length; right++) {
            if (nums[right] != 0){
                res = Math.max(res,right - left + 1);
            }else {
                left = right + 1;
            }
        }
        return res;
    }
}
class Solution1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int length = s.length();
        int[] costs = new int[length];
        int sum = 0;
        int res = 0;
        for (int i = 0; i < length; i++) {
            costs[i] = Math.abs(t.charAt(i) - s.charAt(i));
        }
        for (int right = 0, left = 0; right < length; right++) {
            sum += costs[right];
            while (sum > maxCost){
                sum -= costs[left];
                left++;
            }

            res = Math.max(res,right - left + 1);
        }
        return res;

    }

    public static void main(String[] args) {
        new Solution1208().equalSubstring("krrgw","zjxss", 19);
    }
}
class Solution1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int res = 0;
        int sum = 0;
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0){
                sum += customers[i];
                customers[i] = 0;
            }
        }
        for (int right = 0, left = 0; right < grumpy.length; right++) {
            sum += customers[right];
            if (right - left + 1 > minutes){
                // 移动窗口
                sum -= customers[left];
                left++;
            }
            res = Math.max(res,sum);

        }
        return res;
    }
}
class Solution1423 {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        int cur = 0;
        int length = cardPoints.length;
        int minLength = cardPoints.length - k;
        for (int i = 0; i < length; i++) {
            sum += cardPoints[i];
        }
        for (int i = 0; i < minLength; i++) {
            cur += cardPoints[i];
        }
        int min = cur;
        for (int i = minLength; i < length; i++) {
            cur = cur - cardPoints[i - minLength] + cardPoints[i];
            min = Math.min(min,cur);
        }
        return sum - min;
    }
}
class Solution76 {
    public String minWindow(String s, String t) {
        // 使用hashmap保存要匹配的字符串 也就是t中的字符串,s中有相同的字符串,hashmap -- 判断hashmap中的值是不是全部为0
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int strLeft = 0;// 记录最小值的窗口左边界
        int windowLength = Integer.MAX_VALUE;// 窗口大小
        for (char c : t.toCharArray()) {
            map.put(c,map.getOrDefault(c,0) + 1);
        }
        while (right < s.length()){
            // 遍历s中字符 遇到已存在map中的 ,将map中的值 --;
            char curChar = s.charAt(right);
            if (map.containsKey(curChar)){
                map.put(curChar,map.get(curChar) - 1);
            }
            right++;
            // 判断当前map是否值全部 <= 0(已满足窗口要求,记录窗口大小及左指针)
            while (check(map)){
                if (right - left < windowLength){
                    windowLength = right - left;
                    strLeft = left;
                }
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)){
                    map.put(leftChar,map.get(leftChar) + 1);

                }
                left++;
            }
        }
        String res = windowLength == Integer.MAX_VALUE ? "" : s.substring(strLeft,strLeft + windowLength);
        return res;
    }
    private boolean check(HashMap<Character,Integer> map){
        for (Integer value : map.values()) {
            if (value > 0){
                return false;
            }
        }
        return true;
    }
}
class Solution59 {
    // 按照方向进行模拟
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        // 四个方向 上 右 下 左
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        for (int i = 1, x = 0, y = 0, d = 0; i <= n * n; i++) {
            // d 来判断下一步要走的方向 d 有4个值 0 1 2 3 分别对应 ↑ → ↓ ←
            res[x][y] = i;
            // 下一步可能的x
            int nx = x + dirs[d][0];
            // 下一步可能的y
            int ny = y + dirs[d][1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[nx][ny] != 0){
                // 如果下一个可能的步骤会越界 或者 对应格子已经赋值,变换方向
                d = (d + 1) % 4;
                nx = x + dirs[d][0];
                ny = y + dirs[d][1];
            }
            x = nx;
            y = ny;
        }
        return res;

    }

    public static void main(String[] args) {
        new Solution59().generateMatrix(3);
    }
    // 按照形状进行模拟
    /*public int[][] generateMatrix(int n) {
        int[][] res  = new int[n][n];
        int startx = 0,starty = 0;// 遍历起始坐标
        int loop = n / 2;
        int count = 1;// 每一列遍历个数
        int mid = n / 2;// 奇数时要单独处理 中间元素
        int num = 1;// 每格要填充的数
        int i,j;
        while (loop -- > 0){
            
            i = startx;
            j = starty;
            // 上列 左至右
            for (j = starty;j < starty + n - count;j++){
                res[startx][j] = num;
                num++;
            }
            // 右列 上至下
            for (i = startx; i < startx + n - count; i++) {
                res[i][j] = num;
                num++;
            }
            // 下列 右至左
            for (; j > starty ; j--) {
                res[i][j] = num;
                num++;
            }
            // 左列 下至上
            for (; i > startx ; i--) {
                res[i][j] = num;
                num++;
            }
            if (n % 2 == 1){
                res[mid][mid] = num;
            }
            // 下一圈循环要从 (1,1) 开始
            startx++;
            starty++;
            // 每次循环 各列遍历元素 -2
            count += 2;
        }
        return res;

    }*/
}
class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int row = matrix.length;
        int column = matrix[0].length;
        // 四个方向 右 下 左 上
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for (int i = 0, x = 0, y = 0, d = 0 ;i < row * column; i++) {
            res.add(matrix[x][y]);
            matrix[x][y] = -101;
            int nx = x + dirs[d][0];
            int ny = y + dirs[d][1];
            if (nx < 0 || nx >= row || ny < 0 || ny >= column || matrix[nx][ny] == -101){
                d = (d + 1) % 4;
                nx = x + dirs[d][0];
                ny = y + dirs[d][1];

            }
            x = nx;
            y = ny;
        }
        return res;

    }

}