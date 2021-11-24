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
    // StringBuilder sb;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        // sb = new StringBuilder();
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
            } else {
                continue;
            }
            backTracking(str, i + 1);
            list.remove(list.size() - 1);
        }

    }
    private boolean check(String str) {
        char[] chars = str.toCharArray();
        if ("".equals(str)) {
            return false;
        }
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
        System.out.println(new Solution131().partition(s));
    }*/
}
class Solution93 {
    List<String> res;
    StringBuilder sb;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        if (s.length() == 0) {return res;}
        backTracking(s, 0, 0);
        return res;
    }
    private void backTracking(String str, int start, int pointNum) {
        if (pointNum == 3) {
            if (check(str, start, str.length())) {
                sb.append(str.substring(start, str.length()));
                res.add(sb.toString());
            }
            return;
        }

        for (int i = start; i < str.length(); i++) {
            // 该段ip的起始位置不动
            if (check(str, start, i + 1)) {
                // 每次append 的时候要考虑到已经append了一个字符
                sb.append(str.charAt(i));
                pointNum = pointNum + 1;
                sb.append(".");
                backTracking(str, i + 1, pointNum);
                int pointIndex = sb.lastIndexOf(".");
                pointNum --;
                // 删除最后一个逗号
                sb.delete(pointIndex, sb.length());


            } else {
                break;
            }
        }

    }
    private boolean check(String str, int start, int end) {
        if (start >= end) {
            return false;
        }
        // 一段ip如果以0开始 只能是一位,如果该段ip有两位,则非法
        if (str.charAt(start) == '0' && start < end - 1) {
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
         System.out.println(new Solution93().restoreIpAddresses("25525511135"));
//        System.out.println(new Solution93().check("25525511135", 2, 7));
       /* StringBuilder sb = new StringBuilder();

        String s = "2.2.";
        sb.append(s);
        int index = sb.lastIndexOf(".");
        System.out.println(index);
        sb.delete(index, sb.length());
        System.out.println(sb.toString());*/

    }
}