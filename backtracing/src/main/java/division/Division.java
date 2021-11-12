package division;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/12/17:19
 * @ProjectName Algorithms4th
 * @ClassName: Division
 * @Description: 回溯-切割问题
 */
public class Division {
}
class Solution131 {
    List<List<String>> res;
    ArrayList<String> list;
    StringBuilder sb;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        sb = new StringBuilder();
        backTracking(s, 0);
        return res;
    }
    private void backTracking(String str, int start) {
        if (start >= str.length()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < str.length(); i++) {
            if (check(str.substring(start, i + 1))) {
                list.add(str.substring(start, i + 1));
            }else {
                continue;
            }
            backTracking(str, i + 1);
            list.remove(list.size() - 1);
        }
    }
    private boolean check(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


    /*public static void main(String[] args) {
        //
        String s = "aab";
        System.out.println(new Solution131().check(s.substring(0, 0)));
    }*/
}
