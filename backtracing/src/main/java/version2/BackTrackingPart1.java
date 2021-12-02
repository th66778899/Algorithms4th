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
public class BackTrackingPart1 {
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
class Solution131 {
    List<List<String>> res;
    List<String> path;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        backTracking(s, 0);
        return res;
    }
    private void backTracking(String str, int start) {
        if (start >= str.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < str.length(); i++) {
            String substring = str.substring(start, i + 1);
            if (check(substring)) {

                path.add(substring);
                backTracking(str, i + 1);
                path.remove(path.size() - 1);

            }
        }
    }
    private boolean check(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i -1]) {
                return false;
            }
        }
        return true;
    }
}
class Solution93 {
    List<String> res;
    StringBuilder sb;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        backTracking(s, 0, 0);
        return res;
    }
    private void backTracking(String str, int start, int num) {
        if (num == 3) {
            if (check(str, start, str.length())) {
                sb.append(str.substring(start, str.length()));
                res.add(sb.toString());
            }
            return;
        }
        if (num > 3) {
            return;
        }
        for (int i = start; i < str.length(); i++) {
            String substring = str.substring(i, i + 1);
            if (check(str, start, i + 1)) {
                sb.append(substring);
                sb.append(".");
                num = num + 1;
                backTracking(str, i + 1, num);
                num = num - 1;
                int index = sb.lastIndexOf(".");
                sb.delete(index, sb.length());
            } else {
                break;
            }
        }
    }
    private boolean check(String str, int start, int end) {
        if (start >= end) {
            return false;
        }
        if (str.charAt(start) == '0' && start < end) {
            return false;
        }
        int sum = 0;
        for (int i = start; i < end; i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return false;
            }


            sum *= 10;
            sum += str.charAt(i) - '0';
            if (sum > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
         new Solution93().restoreIpAddresses("25525511135");
//        System.out.println(new Solution93().check("25525511135", 0, 6));
    }
}
class Solution78 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}