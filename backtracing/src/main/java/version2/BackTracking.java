package version2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/12/1/15:21
 * @ProjectName Algorithms4th
 * @ClassName: BackTracking
 * @Description: 回溯
 */
public class BackTracking {
}
class Solution77 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(n, k, 1);
        return res;
    }
    private void backTracking(int n, int k, int start) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n - k + path.size() + 1; i++) {
            path.add(i);
            backTracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }

    }

}
class Solution216 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(k, n, 0, 1);
        return res;
    }
    private void backTracking(int k, int n, int sum, int start){
        if (path.size() == k && sum == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < 10; i++) {
            path.add(i);
            backTracking(k, n, sum + i, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
class Solution17 {
    List<String> res;
    String[] dic;
    StringBuilder sb;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        dic = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, 0);
        return res;
    }
    private void backTracking(String digits, int index) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char[] chars = digits.toCharArray();
        String buttons = dic[chars[index] - '0'];
        char[] buttonArray = buttons.toCharArray();
        for (int i = 0; i < buttonArray.length; i++) {
            sb.append(buttonArray[i]);
            backTracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
class Solution39 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(candidates, target, 0, 0);
        return res;
    }
    private void backTracking(int[] candidates, int target, int sum, int startIndex) {
        if (target == sum) {
            res.add(new ArrayList<>(path));
            return ;
        }
        if (sum > target) {
            return;
        }
        /**
         * 注意点 可以重复选取,下次递归由i开始,但是不能选取i之前的元素
         */
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            backTracking(candidates, target, sum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        new Solution39().combinationSum(new int[]{2, 3, 6, 7}, 7);
    }
}
class Solution40 {
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[candidates.length];
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);

        return res;
    }
    private void backTracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        /**
         * && sum + candidates[i] <= target 剪枝,减少了一次递归
         */
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            /**
             * 不使用boolean数组 直接使用 startIndex来进行去重
             * startIndex 同一层递归都是一样的,同一层不能使用相同的元素
             */
            /*if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }*/

            if (i > 0 && candidates[i] == candidates[i - 1] && used[i-1] == false) {
                continue;
            }
            path.add(candidates[i]);
            used[i] = true;
            backTracking(candidates, target, sum + candidates[i], i + 1);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}