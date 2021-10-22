package backtracking;

import java.util.*;

// leetcode 77 中等 回溯算法
public class Solution77 {

    public List<List<Integer>> combine(int n, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        backTracking(n,k,1,queue,res);
        return res;
    }
    private void backTracking(int n, int k,int startIndex,Deque<Integer> queue,List<List<Integer>> res) {
        // 确定递归结束条件

        if (queue.size() == k){
            res.add(new ArrayList<>(queue));// 当存储组合数组的数组长度为所需要的个数时，将组合添加到结果中
            return;
        }
        // 处理单层递归逻辑
        // for循环 剪枝 i<=size
        for (int i = startIndex; i <= n-(k - queue.size()) + 1; i++) {
            queue.offer(i);
            backTracking(n,k,i+1,queue,res);
            queue.removeLast();

        }

    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new Solution77().combine(3, 2);
        for (List<Integer> integers : combine) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
// leetcode 39 中等
class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        Deque<Integer> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        combineSumT(candidates,target,0,0,queue,res);
        return res;
    }
    private void combineSumT(int[] candidates, int target,int sum,int startIndex,Deque<Integer> queue,List<List<Integer>> res){
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(queue));
            return;
        }
        // 单层循环逻辑
        for (int i = startIndex; i < candidates.length; i++) {
            // 剪枝操作 将数组排序 判断总和加当前数组之和是不是小于target
            // 如果某个值 + 总和已经大于target 该值之后的元素就不再需要进行后续的比较了
            if (sum + candidates[i] <= target){
                queue.offer(candidates[i]);
                sum += candidates[i];
                // 下层递归
                combineSumT(candidates, target, sum, i, queue, res);
                // 回溯操作
                sum -= candidates[i];
                queue.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution39().combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}

// leetcode 40 中等
class Solution40{
    int sum = 0;
    Deque<Integer> queue = new ArrayDeque<>();
    List<List<Integer>> res= new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] isUsed = new boolean[candidates.length];
        backTracking(candidates,target,0,isUsed);
        return res;
    }
    private void backTracking(int[] candidates,int target,int index,boolean[] isUsed){
        // 元素不能重复使用，
        // 结果集不能重复 , 同一层元素不能重复使用
        if (sum == target){
            res.add(new ArrayList<>(queue));
            return;
        }
        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            //出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && isUsed[i - 1] == false){
                continue;
            }
            queue.add(candidates[i]);
            isUsed[i] = true;
            sum += candidates[i];
            backTracking(candidates, target, i + 1, isUsed);
            // 回溯
            sum -= candidates[i];
            queue.removeLast();
            isUsed[i] = false;
        }
    }
}
// leetcode 216 组合总和Ⅲ
class Solution216{
    List<List<Integer>> result = new ArrayList<>();
    Deque<Integer> queue = new ArrayDeque<>();
    public List<List<Integer>> combinationSum3(int k, int targetSum) {
        backTracking(k,targetSum,1,0);
        return result;

    }
    private void backTracking(int k,int targetSum,int index,int sum){
        // 剪枝
        if (sum > targetSum) return;
        // 递归结束条件 元素个数符合 元素总和符合
        if (queue.size() == k){
            if (sum == targetSum){
                result.add(new ArrayList<>(queue));
            }
            return;
        }
        // for循环宽度为集合内可能出现的元素 1 - 9
        for (int i = index; i <= 9 - (k - queue.size()) + 1; i++) {
            queue.add(i);
            sum += i;
            backTracking(k, targetSum, i + 1, sum);
            // 回溯
            sum -= i;
            queue.removeLast();
        }
    }

}
// leetcode 17 中等 回溯 组合问题
class Solution17{
    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
/*-----------下面是切割问题--------------*/
// leetcode 131 中等
class Solution131{
    Deque<String> queue = new ArrayDeque<>();
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backTracking(s,0);
        return res;
    }
    private void backTracking(String s,int startIndex){
        // 如果是回文字串 进行记录
        if (startIndex >=s.length()){
            res.add(new ArrayList<>(queue));
            return;
        }
        // 单层递归逻辑
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s,startIndex,i)){
                String substring = s.substring(startIndex, i + 1);
                queue.offer(substring);
            }else {
                continue;
            }
            // 起始位置后移，保证不重复
            backTracking(s,i+1);
            queue.removeLast();
        }
    }
    private boolean isPalindrome(String s,int startIndex,int end){
        // 利用两个指针遍历字符串 如果遍历到了不相等 就返回false
        for (int i = startIndex,j = end; i < j; i++,j--) {
            if (s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
// leetcode 93 中等 回溯-切割问题
class Solution93{
    List<String> result = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return result;
        backTracking(s,0,0);
        return result;
    }
    private void backTracking(String s,int start,int pointNum){
        // 递归退出条件 字符串中逗号数量 为 3
        // 判断最后一段字符是不是符合要求
        if (pointNum == 3){
            if (isValid(s,start,s.length() - 1)){
                result.add(s);
            }
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isValid(s,start,i)){
                // s.substring(i,j)  [i,j) 左闭右开
                s = s.substring(0,i + 1) + "."+ s.substring(i + 1);
                pointNum++;
                backTracking(s, i + 2, pointNum);
                // 回溯
                pointNum--;
                // 删除"."
                s = s.substring(0,i+1) + s.substring(i+2);

            }else {
                // 剪枝操作 如果某一个字段不符合操作，该字段后续的遍历就不再进行了
                break;
            }
        }

    }
    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
    private boolean isValid(String s,int start,int end){
        if (start > end){
            return false;
        }
        if (s.charAt(start) == '0' && start != end){
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0'){
                return false;
            }

            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255){
                return false;
            }

        }
        return true;
    }
}
/*------------下面是子集问题---------------*/
// leetcode 78 中等  回溯 - 子集问题
class Solution78 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        backTracking(nums,0);
        return result;

    }
    private void backTracking(int[] nums,int startIndex){
        result.add(new ArrayList<>(path));
        // 确定终止条件
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}
// leetcode 90 中等 回溯 - 子集Ⅱ
class Solution90{
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] isUsed;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        isUsed = new boolean[nums.length];// 保存元素是否已被同层访问
        if (nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        backTracking(nums,0);
        return result;


    }
    private void backTracking(int[] nums,int startIndex){
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 结果去重
            if ( i > 0 && nums[i] == nums[i - 1] && isUsed[i-1] == false){
                continue;
            }
            path.add(nums[i]);
            isUsed[i] = true;
            backTracking(nums, i + 1);
            isUsed[i] = false;
            path.removeLast();

        }

    }
}
/*--------------排列问题-------------------*/
class Solution46 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    Deque<Integer> path = new ArrayDeque<>();// 用来存放符合条件结果
    boolean[] isUsed;
    public List<List<Integer>> permute(int[] nums) {
        isUsed = new boolean[nums.length];
        backTracking(nums);
        return result;
    }
    private void backTracking(int[] nums){
        // 递归结束条件 path队列内元素等于数组元素
        if (path.size() >= nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isUsed[i] == false){
                path.offer(nums[i]);
                isUsed[i] = true;
                backTracking(nums);
                // 回溯
                isUsed[i] = false;
                path.removeLast();

            }
        }
    }
}
// leetcode 47 中等
class Solution47 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    Deque<Integer> path = new ArrayDeque<>();// 用来存放符合条件结果
    boolean[] isUsed;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        isUsed = new boolean[nums.length];
        backTracking(nums);
        return result;
    }
    private void backTracking(int[] nums){
        if (path.size() >= nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && isUsed[i - 1] == false){
                continue;
            }
            if (!isUsed[i]){
                path.add(nums[i]);
                isUsed[i] = true;
                backTracking(nums);
                isUsed[i] = false;
                path.removeLast();
            }
        }
    }
}
/*---------------棋盘问题-------------*/
// leetcode 51 N皇后问题 困难 回溯-棋盘问题
class Solution51 {
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (char[] chars : chessBoard) {
            // 填充char数组
            Arrays.fill(chars,'.');
        }
        backTracking(n,0,chessBoard);
        return res;


    }
    private void backTracking(int n,int row,char[][] chessBoard){
        // 递归退出条件 每一行都遍历到了
        if (row == n){
            res.add(arrayToList(chessBoard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row,col,n,chessBoard)){
                chessBoard[row][col] = 'Q';
                backTracking(n,row + 1,chessBoard);
                // 回溯
                chessBoard[row][col] = '.';
            }
        }


    }
    // 将二维char 数组转换为 List<String>
    private List<String> arrayToList(char[][] chessboard){
        List<String> result = new ArrayList<>();
        for (char[] chars : chessboard) {
            result.add(String.copyValueOf(chars));
        }
        return result;
    }
    // 判断该位置插入皇后是否满足题目要求
    private boolean isValid(int row,int col,int n,char[][] chessBoard){
        // 判断同一列是不是存在皇后
        for (int i = 0; i < n; i++) {
            if (chessBoard[i][col] == 'Q'){
                return false;
            }
        }
        // 判断45°角有没有皇后
        // 剪枝 只需要判断右上方斜45° 可以根据 row 来判断该皇后遍历到了第几行
        // 其下面的行不需要去进行45°的判断
        for (int i = row - 1,j = col + 1; i >=0 && j <= n - 1 ; i--,j++) {
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        // 行不需要判断 每进入下一层递归 不会再选择前面已经选择过的行
        // 判断135°有没有皇后
        for (int i = row - 1,j = col - 1; i >=0 && j >=0 ; i--,j--) {
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }
}
// leetcode37 解数独 困难
class Solution37 {
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < 9; i++){ // 遍历行
            for (int j = 0; j < 9; j++){ // 遍历列
                if (board[i][j] != '.'){ // 跳过原始数字
                    continue;
                }
                for (char k = '1'; k <= '9'; k++){ // (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if (solveSudokuHelper(board)){ // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
/*-------------回溯-其它问题-----------*/
// leetcode 491 中等 递增子序列
class Solution491 {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums,0);
        return res;
    }

    private void backtracking (int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }

        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) ||
                    (used[nums[i] + 100] == 1)) continue;
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
// leetcode 332 中等 重新安排行程
class Solution332 {
    private Deque<String> res;
    private Map<String, Map<String, Integer>> map;

    private boolean backTracking(int ticketNum){
        if(res.size() == ticketNum + 1){
            return true;
        }
        String last = res.getLast();
        if(map.containsKey(last)){//防止出现null
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    res.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking(ticketNum)) return true;
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序Map
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);

        }
        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }
}