package sword_to_offer;

import java.util.*;

public class Problem {
}
// 剑指offer 09
class CQueue09{

    LinkedList<Integer> A, B;
    public CQueue09() {
        A = new LinkedList<Integer>();
        B = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        A.addLast(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()){
            return B.removeLast();
        }
        if (A.isEmpty()){
            return -1;
        }
        while (!A.isEmpty()){
            B.addLast(A.removeLast());
        }
        return B.removeLast();
    }
}
// 剑指offer 03
class Solution03 {
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,1) + 1);
        }
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            if (map.get(integer) >= 2){
                return integer;
            }
        }
        return -1;

    }
}
class Solution04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            int left = 0,right = column - 1;

            while (left < right){
                int mid = (left + right) >> 1;
                if (matrix[i][mid] < target){
                    left = mid + 1;
                }else if (matrix[i][mid] > target){
                    right = mid - 1;
                }else {
                    return true;
                }
            }
        }
        return false;
    }
}
class Solution05 {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c ==  ' '){
                sb.append("%20");
            }else {
                sb.append(c);
            }

        }
        return sb.toString();
    }
}
 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
class Solution06 {
    public int[] reversePrint(ListNode head) {

        Deque<Integer> stack = new LinkedList<Integer>();
        new ArrayDeque<Integer>();
        while (head!=null){
            stack.addFirst(head.val);
            head = head.next;
        }
        int size = stack.size();
        int [] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.removeFirst();
        }
        return result;
    }
}

class Solution10 {
    public int fib(int n) {
        // 动态规划
        // dp[]数组含义 dp[i] 对应的 斐波那契数组值
        // 递推公式 dp[i] = dp[i-1] + dp[i - 2]
        // 初始化 dp[1] = 1 dp[2] = 1;
        if (n == 0) return 0;
        /*int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;*/
        int dp1 = 1;
        int dp2 = 1;
        int dpn = 0;
        for (int i = 2; i < n; i++) {
            //dp[i] = dp[i-1] + dp[i - 2];
            dpn = dp1 + dp2;
            dpn %= 1000000007;
            dp1 = dp2;
            dp2 = dpn;
        }
        /*for (int i = 0; i <= n; i++) {
            System.out.print(dp[i] + " ");
        }*/
        System.out.println(dpn);
        return dpn;

    }

    public static void main(String[] args) {
        new Solution10().fib(5);
    }
}
class Solution10_Ⅱ {
    public int numWays(int n) {
        // 01背包问题
        // 物品  1 2 步
        // 背包 n 阶台阶
        int[] step = new int[]{1,2};
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 先遍历背包 再遍历物品 物品可以选取多次 完全背包 遍历物品的内层循环 正序遍历
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                if (i >= step[j]){
                    dp[i] = dp[i] + dp[i - step[j]];
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[n];
    }

    public static void main(String[] args) {
        new Solution10_Ⅱ().numWays(7);
    }
}
// leetcode 154 困难
class Solution11 {
    // 有重复元素的旋转数组寻找最小值
    public int findMinPro(int[] nums) {
        int length = nums.length - 1;
        int right = length;
        // 只有相同元素由中间被切断开,才会导致无法直接使用二分进行查找最小值
        // 消除数组最后的与数组首元素相同的元素
        for (int i = length; i >= 0; i--) {
            if (nums[i] == nums[0]){
                right--;
            }else {
                break;
            }
        }
        // 二分查找
        int left = 0;
        while (left < right){
            int mid = left + (right - left) / 2 + 1;// 防止left < right 条件下进入死循环 left = mid;
            if (nums[mid] >= nums[0]){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return left == left ? nums[0] : nums[left+1];
    }
    // 没有重复元素的旋转数组寻找最小值
    public int findMin(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        while (left < right){
            int mid = left + (right - left) >> 1 + 1;
            if (nums[mid] >= nums[0]){
                left = mid;
            }else {
                right = mid - 1;
            }
        }
        if (left == nums.length - 1){
            return nums[0];
        }else {
            return nums[left + 1];
        }
    }
}
class Solution14_Ⅰ {
    public int cuttingRope(int n) {
        // 动态规划
        // dp[i] i拆分所能获得的最大值
        // j * (i - j) 或 j * dp[i - j]
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            // 要满足这个条件 i = i + (i - j)

            for (int j = 1; j < i-1; j++) {
                dp[i] = Math.max( dp[i] , Math.max( j*(i-j) , j*dp[i-j] ) );
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[n];

        // 数学方法

        /*if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int res = 1;

        while (n > 4){
            res *= 3;
            n -=3;
        }
        res *= n;
        return res;*/
    }
}
class Solution15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0){
            res += n & 1;
            // 右移n位 且自动在左侧补零
            n  = n >>1 ;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution15().hammingWeight(000000000000000111011));
    }
    public int hammingDistance(int x, int y) {
        int res = 0;
        while ( (x | y) != 0){
            int a = x & 1,b = y &1;
            res += a^b;
            x >>= 1;
            y >>= 1;
        }


        /*for (int i = 0; i < 32; i++) {
            int m = (x >> i) & 1;
            int n = (y >> i) & 1;
            // ^ 不同 返回 1
            // & 都为1 返回1
            res += m ^ n;
        }*/
        return res;

    }
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                res += getBit(nums[i],nums[j]);
            }
        }
        return res;
    }
    private int getBit(int x,int y){
        int res = 0;
        while ( (x | y) !=0){
            int a = x & 1;
            int b = y & 1;
            res += a ^ b;
            x >>= 1;
            y >>= 1;
        }
        return res;
    }


}
class Solution16 {
    public double myPow(double x, int n) {
        if (x == 0.00f) return 0.00d;
        double res = 1.0;
        long pow = n;
        if (pow < 0){
            x = 1 / x;
            pow = -pow;
        }
        while (pow > 0){
            if ((pow & 1)== 1){
                res = res * x;
            }
            x = x * x;
            pow >>= 1;

        }
        Math.pow(1,2);
        return res;
    }
}
class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val){
            return head.next;
        }
        ListNode pre = head;
        while (pre!=null){
            if (pre.next.val == val){
                pre.next = pre.next.next;
                return head;
            }
            pre = pre.next;
        }
        return head;
    }
}
class Solution21 {
    public int[] exchange(int[] nums) {
        // 双指针 左指针指向偶数 右指针指向奇数
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            // 左指针移动到偶数
            while ((nums[left] & 1) == 1 && left < right){
                left++;
            }
            // 此时left指向偶数
            // 右指针指向奇数
            while ((nums[right] & 1) == 0 && left < right){
                right--;
            }
            // 将左右指针交换,且各自向中间移动
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;

        }
        return nums;
    }
}
// 反转链表
class Solution24 {
    public ListNode reverseList(ListNode head) {
        // 迭代法
        /*ListNode cur = head;
        ListNode pre = null;
        ListNode temp = null;
        while (cur!=null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;


        }
        return pre;*/

        // 递归
        return reverse(null,head);

    }
    private ListNode reverse(ListNode prev, ListNode cur){
        if (cur == null){
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;
        return reverse(prev,cur);
    }
}
class Solution25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 递归解法
        /*if (l1 == null){
            return l2;
        }else if (l2 == null){
            // 链表l2已经遍历完毕 只需要返回 l1链表即可
            return l1;
        }else if (l1.val < l2.val){
            // 要将l1链表依附到新的链表上去
            // l1已经有一个节点作为新链表的其中一个节点,l1链表的节点会减少一个
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }*/

        // 迭代解法
        // 遍历 l1,l2链表 将两链表中较小值 连接到新链表末尾
        // 虚拟头结点
        ListNode dummyNode = new ListNode(-1);
        ListNode left = l1;
        ListNode right = l2;
        ListNode cur = dummyNode;
        while (left!=null && right!= null){
            if (left.val < right.val){
                cur.next = left;
                cur = cur.next;
            }else {
                cur.next = right;
                cur = cur.next;
            }
        }
        if (left == null){
            cur.next = right;
        }else if (right == null){
            cur.next = left;
        }
        return dummyNode.next;
    }
}
class Solution29 {
    public int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0){
            return new int[0];
        }
        int column = matrix[0].length;
        int[] res = new int[row * column];
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for (int i = 0, x = 0, y = 0, d = 0; i < row * column; i++) {
            res[i] = matrix[x][y];
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

    public static void main(String[] args) {
        new Solution29().spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
    /*public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // if(matrix.length == 0) return new int[0];
        circle(matrix,0,0,matrix.length - 1,matrix[0].length-1,res);
        //int[] arr = new int[matrix.length * matrix[0].length];
        *//*int i = 0;
        for (Integer re : res) {
            arr[i] = re;
            i++;
        }*//*
        return res;
    }
    private void circle(int[][] matrix,int x1,int y1,int x2,int y2,List<Integer> res){
        // 递归退出条件
        if (x2 < x1 || y2 < y1) return;
        // 只有一行需要遍历
        if (x1 == x2){
            for (int i = y1; i <= y2; i++) {
                res.add(matrix[x1][i]);
            }
            return;
        }
        // 只有一列需要遍历
        if (y1 == y2){
            for (int i = x1; i <= x2; i++) {
                res.add(matrix[i][y1]);
            }
            return;
        }
        // 遍历当前圈
        // 上方 遍历区间 都是 左闭右开 [)
        for (int i = y1; i < y2; i++) {
            res.add(matrix[x1][i]);
        }
        // 右方
        for (int i = x1; i < x2; i++) {
            res.add(matrix[i][y2]);
        }
        // 下方
        for (int i = y2; i > y1; i--) {
            res.add(matrix[x2][i]);
        }
        // 左方
        for (int i = x2; i > x1; i--) {
            res.add(matrix[i][y1]);
        }
        // 往里收圈 继续遍历
        circle(matrix, x1+1, y1+1, x2 - 1, y2 - 1, res);
    }*/
}
// 剑指offer 30题
class MinStack30 {

    /** initialize your data structure here. */
    Deque<Integer> stack;
    Deque<Integer> minStack;
    public MinStack30() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.addFirst(x);
        if (minStack.isEmpty() || x <= minStack.peek()){
            minStack.addFirst(x);
        }
    }

    public void pop() {
        // 将stack中元素 出栈
        Integer pop = stack.removeFirst();
        // 如果stack元素要出栈的是最小元素 保存最小元素的栈也要相应出栈
        if (pop.equals(minStack.peek()) ){
            minStack.removeFirst();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        // 按入栈顺序将元素入栈
        // 不断比较栈顶元素与出栈序列 两者相同 栈弹出,不同 继续按入栈元素顺序入栈
        // 如果满足入栈 出栈顺序 栈内最终为空
        for (int i1 : pushed) {
            stack.addFirst(i1);
            while (!stack.isEmpty() && stack.peek() == popped[i]){
                stack.removeFirst();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  // 二叉树 BFS 层序遍历 需要借助队列实现
class Solution32_Ⅰ {

    public int[] levelOrder(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>() {{
            add(root);
        }};
        ArrayList<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.removeFirst();
            res.add(node.val);
            if (node.left!=null) queue.offerLast(node.left);
            if (node.right!=null) queue.offerLast(node.right);
        }
        int[] arr = new int[res.size()];
        int i = 0;
        for (Integer re : res) {
            arr[i] = re;
            i++;
        }
        return arr;
    }

}
class Solution32_Ⅱ {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>() {{
            add(root);
        }};
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            while (size > 0){
                TreeNode node = queue.removeFirst();
                level.add(node.val);
                if (node.left!=null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);

                size--;
            }
            res.add(level);
        }

        return res;
    }
}
class Solution32_Ⅲ {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        boolean leftToRight = true;// 由左至右遍历
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>() {{
            add(root);
        }};
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<Integer>();
            while (size > 0){
                // true 由左至遍历 false 由右至左遍历
                TreeNode node = queue.removeFirst();
                if (leftToRight){
                    level.addLast(node.val);
                }else {
                    level.addFirst(node.val);
                }
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);


                size--;
            }
            leftToRight = !leftToRight;
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addFirst(2);
        queue.addFirst(3);
        for (Integer integer : queue) {
            System.out.print(integer + " ");
        }
    }

}
class Solution33 {
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder,0,postorder.length-1);
    }
    private boolean verify(int[] postOrder,int left,int right){
        // 后序遍历 二叉搜索树 的序列会以 根节点 为分界,将 序列分为小于根节点的左子树 和 大于根节点的右子树
        // 左子树和右子树又可以以相同的方式被划分为更小的左右子树
        // 递归结束条件
        // 后序数组遍历完毕,说明该数组符合后序遍历的要求
        if (left >= right) return true;
        int index = left;// index来划分左右子树
        while (postOrder[index] < postOrder[right]){
            index++;
        }
        // 循环不变量  [)  [left,index) [index,right)
        for (int i = index; i < right; i++) {
            if (postOrder[i] < postOrder[right]){
                return false;
            }
        }
        return verify(postOrder,left,index-1) && verify(postOrder,index,right-1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution33().verifyPostorder(new int[]{1, 2, 5, 10, 6, 9, 4, 3}));
    }
}
class Solution38 {
    ArrayList<String> res = new ArrayList<>();
    boolean[] isUsed = null;
    StringBuilder sb = new StringBuilder();
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        isUsed = new boolean[chars.length];
        Arrays.fill(isUsed,false);
        backTracking(chars,isUsed);
        String[] x = new String[res.size()];
        int i = 0;
        for (String re : res) {
            x[i] = re;
            i++;
        }
        return x;
    }
    private void backTracking(char[] chars,boolean[] isUsed){
        //
        if (sb.length() == chars.length){
            res.add(sb.toString());
            return;
        }
        // for循环横向遍历数组  递归 纵向遍历树支
        for (int i = 0; i < chars.length; i++) {
            // 防止重复的元素出现
            if ( i > 0 && chars[i] == chars[i-1] && isUsed[i - 1] == false){
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (isUsed[i] == false){
                sb.append(chars[i]);
                isUsed[i] = true; //标记同⼀树⽀nums[i]使⽤过，防止同一树支重复使用
                backTracking(chars,isUsed);
                // 回溯
                isUsed[i] = false;
                sb.deleteCharAt(sb.length()-1); //回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
            }
        }

    }

    public static void main(String[] args) {
        char[] s = new char[3];
        s[0] = 'd';
        s[1] = 'a';
        s[2] = 'f';
        for (char c : s) {
            System.out.print(c + " ");
        }
        Arrays.sort(s);
        for (char c : s) {
            System.out.print(c + " ");
        }

    }
}
class Solution39 {
    public int majorityElement(int[] nums) {
        // hashMap统计各数出现次数
        /*HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        for (Integer integer : map.keySet()) {
           if ( map.get(integer) > (nums.length /2) ){
               return integer;
           }
        }
        return -1;*/

        // 摩尔投票法摩尔投票法思路
        // 候选人(cand_num)初始化为nums[0]，票数count初始化为1。
        // 当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
        // 当票数count为0时，更换候选人，并将票数count重置为1。
        // 遍历完数组后，cand_num即为最终答案。
        //
        // 为何这行得通呢？
        // 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
        // 且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
        // 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
        // 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
        //
        // 无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
        int target = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == target){
                count++;
            }else {
                count--;
            }
            if (count == -1){
                target = nums[i];
                count = 1;
            }
        }
        return target;
    }
}
class Solution42 {
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        int res = 0;
        for (int i = 1; i < length  ; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i],dp[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
class Solution46 {
    // 动态规划
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i-2) != '0' && Integer.parseInt(s.substring(i-2,i)) < 26){
                dp[i] = dp[i-1] + dp[ i - 2];

            }else{
                dp[i] = dp[i-1];
            }
        }

        return dp[s.length()];
    }


    // 记忆化递归
    // 在递归的过程中，我们发现求f(i)的时候需要先求解f(i + 1)和f(i + 2)，显然，
    // 这两者的求解过程中间存在许多重复解，这样会产生许多冗余的计算。
    // 我们把这些重复子问题的解，即中间解，记录下来，以避免冗余计算。因此记忆化递归便很自然地写出来了。
   /* String s = null;
    int[] dp = null;// 使用dp数组保存中间过程
    public int translateNum(int num) {
        s = String.valueOf(num);
        dp = new int[s.length()];
        return f(0);
    }
    private int f(int i){
        if (i >= s.length() - 1) return 1;
        if (dp[i] > 0) return dp[i];
        int res = f(i + 1);
        if (s.charAt(i) != '0' && Integer.parseInt(s.substring(i,i+2)) < 26){
            res += f(i+2);
        }
        dp[i] = res;
        return res;
    }*/

    // 普通递归
    /*String s = null;
    public int translateNum(int num) {
        s = String.valueOf(num);
        return f(0);
    }
    private int f(int i){
        if (i >= s.length() -1) return 1;
        int res = f(i + 1);
        if (s.charAt(i) != '0' && Integer.parseInt(s.substring(i,i+2)) < 26){
            res = res + f(i + 2);
        }
        return res;
    }*/
}
// 动态规划
class Solution47 {
    public int maxValue(int[][] grid) {
        // 二维dp数组
        /*int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];
        // dp 数组含义 以i-1 j-1 为下标的矩形所能获得的最大价值
        // 递推公式 dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i-1][j-1];
        // 要么向右移最大值 要么向下移获取最大值
        // dp数组初始化
        // 初始化第一行
        dp[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        // 初始化第一列
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[row-1][column-1];*/
        int[] dp = new int[grid[0].length];// 滚动数组
        // 递推公式 dp[i] = Math.max(dp[i-1] + grid[row][i],dp[i] + grid[row][i]);
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for (int row = 1; row < grid.length; row++) {
            // 列
            dp[0] += grid[row][0];
            for (int i = 1; i < grid[0].length; i++) {

                dp[i] = Math.max(dp[i-1] + grid[row][i],dp[i] + grid[row][i]);
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[grid[0].length - 1];
    }

    public static void main(String[] args) {
        new Solution47().maxValue(new int[][]{{1,4,8,6,2,2,1,7},{4,7,3,1,4,5,5,1},{8,8,2,1,1,8,0,1},{8,9,2,9,8,0,8,9},{5,7,5,7,1,8,5,5},{7,0,9,4,5,6,5,6},{4,9,9,7,9,1,9,0}});
    }
}
class Solution48 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int start = 0,end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)){
                start = Math.max(start,map.get(c));
            }
            res = Math.max(res,end - start + 1);
            map.put(c,end + 1);
        }
        return res;

    }
}
class Solution {
    public int nthUglyNumber(int n) {
        // dp[i] 表示第 i-1个丑数
        // 丑数的递推性质： 丑数只包含因子 2, 3, 52,3,5 ，
        // 因此有 “丑数 == 某较小丑数 × 某因子” （例如：10=5×2）。
        // a b c 丑数的各个因子对应的积 2 : x*dp[a]  3 : x*dp[b] 5 : x*dp[c] x下一个丑数是其中的最小值
        // 最小值 ==  这三个(2 : x*dp[a]  3 : x*dp[b] 5 : x*dp[c]) 其中一个 ,该丑数的位置就已经确定 对应的指针递增即可
        int[] dp = new int[n];
        int a = 0,b = 0, c = 0;
        dp[0] = 1;// 1也是丑数
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min( dp[a]*2 , Math.min( dp[b]*3 , dp[c]*5 ));
            if (dp[i] == dp[a] * 2) {a++;}
            if (dp[i] == dp[b] * 3) {b++;}
            if (dp[i] == dp[c] * 5) {c++;}

        }
        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[n-1];

    }
}
class Solution50 {
        public char firstUniqChar(String s) {
            // 数组取代hashMap
            int[] asc = new int[128];
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                asc[aChar] ++;
            }
            for (char aChar : chars) {
                if (asc[aChar] == 1){
                    return aChar;
                }
            }
            return ' ';

            /*char[] chars = s.toCharArray();
            if (chars.length == 0) {
                return ' ';
            }
            HashMap<Character, Integer> map = new LinkedHashMap<>();
            for (char aChar : chars) {
                map.put(aChar,map.getOrDefault(aChar,0) + 1);
            }
            Set<Character> characters = map.keySet();
            for (Character character : characters) {
                if (map.get(character) == 1){
                    return character;
                }
            }


            return ' ';*/
        }
}
class Solution52 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 统计两链表长度 找出差值 让长的先走完差值
        // 两指针同时向后移动 比较当前节点
        ListNode left = headA;
        ListNode right = headB;
        int x = 0,y = 0;
        while ( left != null){
            left = left.next;
            x++;
        }
        while ( right != null){
            right  = right.next;
            y++;
        }
        left = headA;
        right = headB;
        int abs = Math.abs(x - y);
        while (abs > 0){
            if (x > y){
                left = left.next;
                abs--;
            }else{
                right = right.next;
                abs--;
            }
        }
        while (left != null){
            if (left == right){
                return left;
            }
            left = left.next;
            right = right.next;
        }
        return null;
    }
}
class Solution53_Ⅰ {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) return 0;
        int right = length - 1;
        int left = 0;

        // 二分找出 左右边界
        // 找出左边界
        while (left < right){
            int mid = left + right >> 1;
            if (nums[mid] >= target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) return 0;
        int x1 = left;
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
        if (nums[left] != target) return 0;
        int x2 = left;

        return x2 - x1 + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution53_Ⅰ().search(new int[]{5, 7, 8, 8, 10}, 8));
    }
}
class Solution53_Ⅱ {
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + right >> 1;
            if (nums[mid] > mid){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
class Solution54 {
    int result = 0;
    int k;
    public int kthLargest(TreeNode root, int k) {
        /*PriorityQueue<Integer> queue = new PriorityQueue<>();
        ArrayList<Integer> res = new ArrayList<>();
        traversal(root,res);
        for (Integer re : res) {
            while (queue.size() < k){
                queue.offer(re);
            }
            queue.offer(re);
            queue.poll();
        }
        for (Integer re : res) {
            System.out.print(re + " ");
        }
        return queue.peek();*/
        this.k = k;
        traversal(root);
        return result;

    }
    private void traversal(TreeNode root){
        // 左 中 右 中序遍历 遍历结果 升序遍历
        // 右 中 左 中序遍历 遍历结果 降序遍历
        // 通过k来找出倒数k个值 也就是倒数第k最大的值
        if (root == null){
            return;
        }
        // 右子树
        traversal(root.right);
        if (this.k > 0){
            result = root.val;
            this.k--;
        }
        // 左子树
        traversal(root.left);





        /*if (root == null){
            return;
        }
        traversal(root.left,res);
        res.add(root.val);
        traversal(root.right,res);*/
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode3.left = treeNode1;
        treeNode3.right = treeNode4;
        treeNode1.right = treeNode2;
        System.out.println(new Solution54().kthLargest(treeNode3, 1));
    }
}
class Solution55_Ⅱ {
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) == -1 ? false : true;
    }
    private int getDepth(TreeNode root){
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1){
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1){
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left,right);
    }
}

// 异或 ^
//
class Solution56_Ⅰ {
        public int[] singleNumbers(int[] nums) {
            int bit = 1;
            int res = 0;
            for (int num : nums) {
                res = res ^ num;
            }
            // 最后 res 为 两个不相同的数的异或结果
            // 找出res中位为1 的 位置 (两数不同,肯定会存在异或结果为1) 由此将两数分为两组
            // 两组分别进行异或 各组最终结果就是所要求的结果
            while ((res & bit) == 0){
                bit = bit << 1;
            }
            int a = 0;
            int b = 0;
            // bit就是两组值的分界点
            for (int num : nums) {
                // 该位为1 分为一组
                if ((num & bit) == 0){
                    a = a ^ num;
                }else {
                    b = b ^ num;
                }
            }
            return new int[]{a,b};

        }

    public static void main(String[] args) {
        int[] ints = new Solution56_Ⅰ().singleNumbers(new int[]{1, 2, 5, 2});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
//

class Solution56_Ⅱ {
    public int singleNumber(int[] nums) {
        // 统计所有数字在每一位上的1的个数 0-31位
        // 对每一位上1的个数对3求余  余数就是所求数的各位的情况
        // 将求出的各个位数还原成所求值
        int [] bit = new int[32];
        int bitCheck = 1;// 来求出各数的所有 位 的情况
        for (int num : nums) {
            int i = 31;
            while (num != 1){
                if ((num & bitCheck) == 1){
                    bit[i]++;

                }
                num = num >> 1;
                i--;
            }
            bit[i]++;

        }

        int res = 0;
        for (int i = 0; i < 32; i++) {

            res = res + bit[i] % 3;
            res = res << 1;
        }
        res >>= 1;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution56_Ⅱ().singleNumber(new int[]{3, 5, 3, 3}));
    }
}
class Solution57 {
    public int[] twoSum(int[] nums, int target) {
        // 两个指针寻找目标值
        int left = 0,right = nums.length - 1;
        while (true){
            if (nums[left] + nums[right] < target){
                left++;
            }else if (nums[left] + nums[right] > target){
                right--;
            }else {
                return new int[]{nums[left],nums[right]};
            }
        }
    }
}
class Solution57_Ⅱ {
    public int[][] findContinuousSequence(int target) {
        // 滑动窗口 重要性质  左右指针都只向右移动
        List<int[]> res = new ArrayList<>();
        int left = 1,right = 2,sum = 1;
        while (left < right){
            if (sum == target && right - left > 1){
                int[] arr = new int[right - left];
                for (int i = 0; i < right - left; i++) {
                    arr[i] = left + i;
                }
                res.add(arr);
            }
            if (sum >= target){
                sum -= left;
                left++;
            }else {
                sum += right;
                right++;
            }
        }


        return res.toArray(new int[0][]);
    }
}
class Solution58_Ⅰ {
    public String reverseWords(String s) {
        String[] s1 = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        ArrayList<String> res = new ArrayList<>();
        for (int i = s1.length - 1 ;i >= 0 ; i--) {
            if (!s1[i].equals(" ")){
                sb.append(s1[i] + " ");
                res.add(s1[i]);
            }


        }
        //sb.deleteCharAt(sb.length()-1);
        for (String re : res) {
            System.out.println(re + " " + res.size());
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        //System.out.println(new Solution58_Ⅰ().reverseWords("  hello world!  "));
        String s = " a  b";
        String[] s1 = s.trim().split("\\s+");
        for (int i = 0; i < s1.length; i++) {
            System.out.println(s1[i].equals("") + "|"+ i+s1[i]);
        }


    }
}
class Solution59_Ⅰ {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        if (length == 0){
            return new int[0];
        }
        int[] res = new int[length - k + 1];
        // 双向队列保存窗口内最大值
        // 遍历数组 每次遍历要检查队列内的元素是不是还在窗口中
        // i >= k - 1 此时
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            // 保证队列首元素  是窗口内的最大值0
            while (!queue.isEmpty() && nums[queue.peekLast()]  <= nums[i]){
                queue.pollLast();
            }
            // 将新的最大值 或者窗口内不是最大值加入队列
            queue.offerLast(i);
            // 判断队列首元素是不是在窗口内,不在 移除队首元素
            if ( queue.peekFirst() < i - k + 1 ){
                queue.pollFirst();
            }
            // 判断窗口是不是达到要求 取得各窗口最大值
            if ( i >= k - 1){
                res[i - k + 1] = nums[queue.peekFirst()];
            }


        }
        return res;
    }

    public static void main(String[] args) {
        new Solution59_Ⅰ().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
    }
}
class MaxQueue59_Ⅱ {
    ArrayDeque<Integer> queue;
    ArrayDeque<Integer> maxQueue;
    public MaxQueue59_Ⅱ() {
        queue = new ArrayDeque<Integer>();
        maxQueue = new ArrayDeque<Integer>();

    }

    public int max_value() {
        if (!maxQueue.isEmpty()){
            return maxQueue.peekFirst();
        }
        return -1;
    }

    public void push_back(int value) {
        queue.offerLast(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() <  value){
            maxQueue.pollLast();

        }
        maxQueue.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()){
            return -1;
        }
        if (queue.peekFirst() == maxQueue.peekFirst()){
            maxQueue.pollFirst();
        }

        return queue.pollFirst();
    }

    public static void main(String[] args) {
        Integer x = 1;
        Integer y = 1;
        System.out.println(x.equals(y));
    }
}
class Solution63 {
    public int maxProfit(int[] prices) {
        // 贪心 最优解 局部最优以达到全局最优,只买卖一次,找最左最小值 与 最右最大值
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            min = Math.min(min,price);
            res = Math.max(res,price - min);
        }
        return res;




        // 动态规划 不是最优解
        // dp[][] 数组定义 dp[i][0] 第i天持有股票获得的最大金钱
        //                dp[i][1] 第i天不持有股票获得的最大金钱
        /*int[][] dp = new int[prices.length][2];
        // 递推公式
        // dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
        // dp[i][1] = Math.max(dp[i-1][0] + prices[i],dp[i-1][1]);
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            dp[i][1] = Math.max(dp[i-1][0] + prices[i],dp[i-1][1]);
        }
        return dp[prices.length-1][1];*/
    }
}

class Solution64 {

    public int sumNums(int n) {
        boolean x = n > 1 && ( (n = n + sumNums(n - 1)) > 0);

        return n;

    }
}
// 不用 + - * / 实现四则运算 位运算
class Solution65 {
    public int add(int a, int b) {
        // 进位和 非进位和
        while (b != 0){
            int temp = (a & b) << 1;// 进位
            a = a ^ b;// 非进位
            // 之后要把进位和和 非进位和 加起来,直至进位和为零
            b = temp;
        }
        return a;
    }
}
class Solution66 {
    public int[] constructArr(int[] a) {
        int length = a.length;
        if (length == 0) return new int[0];
        // 计算该元素 左边所有乘积 O(n) 右边所有元素乘积O(n)
        // 对应左右成绩相乘就是 该元素要求的乘积
        int[] resLeft = new int[length];//该元素左边所有乘积
        int[] resRight = new int[length];// 该元素右边所有乘积
        resLeft[0] = 1;
        resRight[length - 1] = 1;
        // 构造左边元素所有乘积
        for (int i = 1; i < length; i++) {
            resLeft[i] = resLeft[i - 1] * a[i - 1];
        }
        // 构造右边元素所有乘积
        for (int i = length - 2; i >= 0 ; i--) {
            resRight[i] = resRight[i+1] * a[i+1];
        }
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = resLeft[i] * resRight[i];
        }

        return res;
    }
}
// 把字符串转换为整数
// 类似C/C++ atoi函数
// 对给出的样例提示进行充分的考虑
class Solution67 {
    public int strToInt(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        int index = 0;// char数组的起始下标 用来排除开头的空格
        int sign = 1;// 表示正负号
        // 去除前导空格
        while (index < length && chars[index] == ' '){
            index++;
        }
        // 如果输入参数 str全部都是空格 直接返回0
        if (index == length){
            return 0;
        }
        // 正负符号只记录一次
        if (chars[index] == '+'){
            index++;
        }else if (chars[index] == '-'){
            index++;
            sign = -1;
        }
        int res = 0;
        while (index < length){
            // 判断是不是合法数字
            if (chars[index] > '9' || chars[index] < '0'){
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && (chars[index] - '0')  > Integer.MAX_VALUE % 10){
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || res == Integer.MIN_VALUE / 10 && (chars[index] - '0')  > -(Integer.MIN_VALUE % 10)){
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (chars[index] - '0');

            index++;


        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println( -Integer.MIN_VALUE);
    }
}
class Solution68_Ⅰ {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            if (root.val > p.val && root.val > q.val){
                root = root.left;
            }else if (root.val < p.val && root.val < q.val){
                root = root.right;
            }else {
                return root;
            }
        }
        return null;
    }
}
class Solution68_Ⅱ {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return getAncestor(root,p,q);
    }
    private TreeNode getAncestor(TreeNode root,TreeNode p,TreeNode q){
        if (root == null || root == p || root == q){
            return root;
        }
        // 后序遍历 自底向上
        TreeNode left = getAncestor(root.left,p,q);
        TreeNode right = getAncestor(root.right,p,q);
        if (left != null && right != null){
            return root;
        }
        if (left == null){
            return right;
        }else if (right == null){
            return left;
        }else {
            return null;
        }
    }
}