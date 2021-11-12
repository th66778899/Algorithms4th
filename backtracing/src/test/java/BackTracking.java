import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/12/9:22
 * @ProjectName Algorithms4th
 * @ClassName: BackTracking
 * @Description: TODO 回溯 - 二刷
 */
public class BackTracking {
}
class Solution77 {
    List<List<Integer>> res;
    ArrayList<Integer> list;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        backTracking(1, n, k);
        return res;
    }

    private void backTracking(int start, int end, int k) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i + (k - list.size()) <= end + 1; i++) {
            list.add(i);
            backTracking(i + 1, end, k);
            list.remove(list.size() - 1);
        }
    }

}
class Solution216 {
    List<List<Integer>> res;
    ArrayList<Integer> list;
    int num;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        num = 0;
        backTracking(n, k, 0, 1);
        return res;
    }
    /**
    * @Author tho
    * @Date 2021/11/12 9:38
    * @param target 目标和
    * @param k      目标元素个数
    * @param sum    当前元素之和
    * @Return void
    * @Description: todo lc216
    */
    private void backTracking(int target, int k, int sum, int start) {
        // 剪枝
        if (sum > target) {
            return;
        }
        if (list.size() == k && sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 剪枝
        for (int i = start ; i + (k - list.size()) <= 9 + 1; i++) {
            list.add(i);
            backTracking(target, k, sum + i, i + 1);
            list.remove(list.size() - 1);
        }

    }
}
class Solution17 {
    String[] dic;
    StringBuilder sb;
    ArrayList<String> res;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits.length() == 0) return res;
        dic = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        sb = new StringBuilder();

        backTracking(digits, 0);
        return res;
    }
    private void backTracking(String digits, int index) {
        char[] chars = digits.toCharArray();
        if (index == chars.length) {
            res.add(sb.toString());
            return;
        }
        if (index > digits.length()) return;


        String button = dic[chars[index] - '0'];
        char[] buttons = button.toCharArray();
        for (int i = 0; i < buttons.length; i++) {
            sb.append(buttons[i]);
            backTracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}