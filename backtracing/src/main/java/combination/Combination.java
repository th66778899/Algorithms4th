package combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/11/16:09
 * @ProjectName Algorithms4th
 * @ClassName: Combination
 * @Description: 回溯 - 组合问题
 */
public class Combination {
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
        // k - list.size() 表示还需要的元素个数 i + k - list.size() 表示i至多由这个位置开始
        // i = 3 k = 2 end = 4 要满足 1 2 3 4 四个数找两个 可以由3开始,即为下面的判断条件
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
        backTracking(1, n, k);
        return res;
    }
    private void backTracking(int start, int sum, int k) {
        if (num > sum) return;
        if (list.size() == k && num == sum) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 剪枝
        for (int i = start; i <= 9; i++) {
            list.add(i);
            num += i;
            backTracking(i + 1, sum, k);
            list.remove(list.size() - 1);
            num -= i;
        }

    }
}
class Solution17 {
    ArrayList<String> res;
    StringBuilder list;
    HashMap<String, String> dic;
    // int k;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        list = new StringBuilder();
        if (digits == null) return res;
        dic = new HashMap<String, String>(){{
            put("2","a,b,c");
            put("3","d,e,f");
            put("4","g,h,i");
            put("5","j,k,l");
            put("6","m,n,o");
            put("7","p,q,r,s");
            put("8","t,u,v");
            put("9","w,x,y,z");
        }};
        // k = digits.length();
        backTracking(digits, 0);
        return res;

    }
    private void backTracking(String digits, int index) {
        if (index >= digits.length()) return;
        // index用来记录当前遍历到第几个数了
        char[] chars = digits.toCharArray();

        String s = dic.get(String.valueOf(chars[index]) );
        String[] split = s.split(",");

        for (int i = 0; i < split.length; i++) {
            System.out.print(split[i] + " ");
        }

        if (index == digits.length()) {
            res.add(list.toString());
            return;
        }
        for (int i = 0; i < split.length; i++) {
            list.append(split[i]);
            backTracking(digits, index + 1);
            list.deleteCharAt(list.length() - 1);
        }
    }
}
