package leetcode.string_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringProblem {
}
class Solution344 {

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
class Solution541 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = k - 1;
        for (; left < chars.length && right < chars.length;) {
            reverseString(chars,left,right);
            left += k;
            right += k;
        }
        return String.valueOf(chars);
    }
    private void reverseString(char[] arr,int left,int right){
        // 反转 left -> right 之间的字符
        for (int i = 0; i < (right - left) / 2; i++) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
class Solution_Offer_05 {
    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' '){
                builder.append("%20");
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
class Solution151 {
    public String reverseWordsByApi(String s) {
        // 去除首尾空格
        s = s.trim();
        List<String> strings = Arrays.asList(s.split("\\S+"));
        Collections.reverse(strings);
        return String.join(" ",strings);
    }
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);

        reverseString(sb,0,sb.length() - 1);
        reverseEachWords(sb);
        // 三个步骤
        // 1.去除首尾空格以及中间的多余空格
        // 2.翻转整个字符串
        // 3.翻转每个单词
        // " royal never give   up  " ->
        // "royal never give up" ->
        // "pu evig reven layor" ->
        // "up give never royal"

        return sb.toString();
    }

    /**
     * 去除多余空格
     * @param s
     * @return
     */
    private StringBuilder removeSpace(String s){
        int right = s.length() - 1;
        int left = 0;
        StringBuilder sb = new StringBuilder();
        while (s.charAt(left) == ' '){
            left++;
        }
        while (s.charAt(right) == ' '){
            right--;
        }
        // 去除中间重复空格
        while (left <= right){
            // 连续两个字符都是空格 s.charAt(left) == ' ' && sb.charAt(sb.length() - 1) == ' '
            // 跳过
            // 不满足上述条件 将字符加到末尾
            // 取反 s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' '
            if (s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' ' ){
                sb.append(s.charAt(left));
            }
            left++;
        }
        return sb;
    }

    /**
     * [left,right] 区间内翻转字符串
     * @param sb
     * @param left
     * @param right
     */
    private void reverseString(StringBuilder sb,int left,int right){

        while (left < right){
            char temp = sb.charAt(left);
            sb.setCharAt(left,sb.charAt(right));
            sb.setCharAt(right,temp);
            left++;
            right--;
        }
    }

    /**
     * 翻转每个单词
     * @param sb
     */
    private void reverseEachWords(StringBuilder sb){
        int left = 0;
        int right = 1;
        int length = sb.length();
        while (right < length){
            while (right < length && sb.charAt(right) != ' '){
                right++;
            }
            reverseString(sb,left,right - 1);
            left = right + 1;
            right = left + 1;
        }
    }
}
class Solution_Offer_58_Ⅱ {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0,n);
        /*StringBuilder sb = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }*/

    }
}
class Solution28 {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}