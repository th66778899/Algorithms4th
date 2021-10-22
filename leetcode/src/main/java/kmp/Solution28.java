package kmp;


import java.util.HashMap;

// kmp算法
public class Solution28 {
    // KMP算法
    // ss:原串(string) pp:匹配串(pattern)
    public int strStrKmp(String ss, String pp) {
        if (pp.isEmpty()) return 0;
        // 分别读取原串和匹配串的长度
        int m = ss.length();
        int n = pp.length();
        // 原串和匹配串都加空格，使其下标从1开始
        ss = " " + ss;
        pp = " " + pp;
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        // 构建next数组，数组长度为匹配串的长度(next数组是和匹配串相关的)
        int[] next = new int[n + 1];
        // 构造next数组 i = 2; j = 0,i小于等于匹配串长度 时间复杂度 O(n)
        for (int i = 2,j = 0; i <= n ; i++) {
            // 匹配不成功的话，j=next[j]
            while (j > 0 && p[i] !=p[j+1]){
                j = next[j];
            }
            // 匹配成功的话 想让j++
            if (p[i] == p[j+1]) {
                j++;
            }
            // 更新next[j]，结束本次循环 i++
            next[i] = j;
        }
        // 匹配过程 i = 1,j = 0,i小于等于原串长度 (匹配i从1开始)
        for (int i = 1,j = 0; i <= m; i++) {
            // 匹配不成功 j=next[j]
            while (j > 0 && s[i] != p[j+1]){
                j = next[j];
            }
            // 匹配成功的话,先让 j++,结束本次循环后， i++
            if (s[i] == p[j + 1]){
                j++;
            }
            // 一整段匹配成功 ,直接返回下标
            if (j == n){
                return i-n;
            }
        }
        return -1;

    }
    public int strStr(String haystack, String needle) {
        char[] before = haystack.toCharArray();
        char[] after = needle.toCharArray();
        int m = before.length;
        int n = after.length;
        for (int i = 0; i <= m - n; i++) {
            int x = i;
            int y = 0;
            while (y < n && before[x] == after[y]){
                x++;
                y++;
            }
            if (y == n){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = new Solution28().strStrKmp("hello", "ll");
        System.out.println(i);
    }
}
