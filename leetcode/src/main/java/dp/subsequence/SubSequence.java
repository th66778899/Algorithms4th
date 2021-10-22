package dp.subsequence;

import sun.nio.cs.ext.MacArabic;

public class SubSequence {
}
// leetcode 300 中等 子序列(不连续)
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        // dp[i] [0-i]个元素中最长子序列
        // 递推公式  if(nums[i] > nums[j])
        //          dp[i] = Math.max(dp[i],dp[j]+1)
        // 初始化 dp[] 数组所有全部初始化为1
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        /*for (int i = 0; i < length; i++) {
            System.out.print(dp[i] + " ");
        }*/
        int res = 0;
        for (int i = 0; i < length; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution300().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6});
    }
}
// leetcode 1143 动态规划-最长公共子序列
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] 0-i 的序列x 与 0-j的序列y 最大的公共子序列
        // 递推公式 str[i] == str[j] dp[i][j] = dp[i-1][j-1] + 1;
        //         str[i] != str[j]  dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])
        // 初始化 dp[i][0] = 0 dp[0][j] = 0;
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // 先对dp数组做初始化操作
        for (int i = 1; i <= text1.length(); i++) {
            char str1 = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char str2 = text2.charAt(j - 1);
                if (str1 == str2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        new Solution1143().longestCommonSubsequence("abcde","ace");

        String x = "ace";
        System.out.println(x.length());
    }
}
// leetcode 中等 动态规划-子序列(不连续)
class Solution1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <=nums2.length ; j++) {
                if (nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }

            }
        }
        /*for (int i = 0; i <= nums1.length; i++) {
            for (int j = 0; j <= nums2.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/
        return dp[nums1.length][nums2.length];
    }

    public static void main(String[] args) {
        new Solution1035().maxUncrossedLines(new int[]{1,4,2},new int[]{1,2,4});
    }
}
/*-----------子序列问题(连续)------------*/
// leetcode 简单 动态规划-子序列问题(连续)
class Solution674 {
    public int findLengthOfLCIS(int[] nums) {
        // dp[]数组含义 dp[i] 表示 [0-i] 最长子序列
        // 递推公式 if (nums[i] > nums[i-1]) dp[i] = dp[i-1] + 1;
        // 初始化 全部初始化为1
        int[] dp = new int[nums.length+1];
        for (int i = 0; i <= nums.length; i++) {
            dp[i] = 1;
        }
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]){
                dp[i] = dp[i-1]+1;
            }
            res = Math.max(res,dp[i]);

        }
        /*for (int i = 0; i < nums.length+1; i++) {
            System.out.print(dp[i] + " ");
        }*/
        return res;
    }

    public static void main(String[] args) {
        new Solution674().findLengthOfLCIS(new int[]{1,3,5,4,7});
    }
}
// leetcode 718 动态规划-子序列(连续)
class Solution718 {
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j] 以i-1结尾的A 以 j-1 结尾的B 最长公共子串
        // 递推公式 if(nums1[i-1] == nums[j-1]) dp[i][j] = dp[i-1][j-1] + 1
        // 初始化 全部初始化为0
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length ; j++) {
                if (nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                res = Math.max(res,dp[i][j]);
            }
        }
        for (int i = 0; i <= nums1.length; i++) {
            for (int j = 0; j <=nums2.length ; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution718().findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7});
    }
}
// leetcode 简单 动态规划-子序列(连续)
class Solution53 {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            res = Math.max(res,dp[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(dp[i] + " ");
        }
        return res;

        // 贪心算法
        /*if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return sum;*/
    }

    public static void main(String[] args) {
        new Solution53().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
}
/*-----------------------编辑距离----------------------*/
class Solution392 {
    public boolean isSubsequence(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        int res = 0;
        for (int i = 1; i <= s.length(); i++) {
            char str1 = s.charAt(i-1);
            for (int j = 1; j <= t.length(); j++) {
                char str2 = t.charAt(j-1);
                if (str1 == str2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                res = Math.max(res,dp[i][j]);

            }
        }
        for (int i = 0; i < s.length()+1; i++) {
            for (int j = 0; j < t.length()+1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        if (res == s.length()){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        new Solution392().isSubsequence("axc","ahbgdc");
    }
}
// leetcode 115 动态规划-编辑距离
class Solution115 {
    public int numDistinct(String s, String t) {
        // dp[i][j] 表示  i-1结尾的s字符串 j-1结尾的t字符串 t在s中出现的次数
        // dp[i][j] 递推公式 s[i-1] == t[j-1] dp[i][j] = dp[i-1][j-1]  + dp[i-1][j]
        //                  s[i-1] != t[j-1] dp[i][j] =  dp[i-1][j]
        // 初始化 dp[i][0] = 1 表示t空字符串在s中可以出现的次数 只有一次
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            char str1 = s.charAt(i - 1);
            for (int j = 1; j <= t.length(); j++) {
                char str2 = t.charAt(j - 1);
                if (str1 == str2){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[s.length()][t.length()];

    }

    public static void main(String[] args) {
        new Solution115().numDistinct("baegg","bag");
    }
}
// leetcode 中等 动态规划-编辑距离
class Solution583 {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i <= word1.length(); i++) {
            char str1 = word1.charAt(i - 1);
            for (int j = 1; j <= word2.length(); j++) {
                char str2 = word2.charAt(j - 1);
                if (str1 == str2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return word1.length() + word2.length() - dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        new Solution583().minDistance("sea","eat");
    }

}
// leetcode 困难动态规划-编辑距离
class Solution72 {
    public int minDistance(String word1, String word2) {
        // 1. 确定dp数组（dp table）以及下标的含义
        // dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= length2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                }
            }
        }
        for (int i = 0; i <= length1; i++) {
            for (int j = 0; j <= length2; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        new Solution72().minDistance("horse","ros");
    }
}
/*-----------------------回文串----------------------*/
// leetcode 647 中等 动态规划-回文串
class Solution647 {
    public int countSubstrings(String s) {

        // 双指针 以某个点为中心 向左右两侧扩散 只用考虑中心有1个元素 与中心有两个元素的情况
        /*int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += extend(s,i,i);
            res += extend(s,i,i+1);
        }
        return res;*/



        // 动态规划
        //  布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int sum = 0;
        for (int i = length-1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    if (j - i <= 1){
                        sum++;
                        dp[i][j] = true;
                    }else if (dp[i+1][j-1]){
                        sum++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return sum;
    }
    private int extend(String s,int left,int right){
        int res = 0;
        int length = s.length();
        while (left >=0 && right < length && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
            res++;
        }
        return res;


    }
    public static void main(String[] args) {
        new Solution647().countSubstrings("aaa");
    }
}

// leetcode 中等 动态规划-回文子序列
class Solution516 {
    public int longestPalindromeSubseq(String s) {
        // dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length ;i++) {
            dp[i][i] = 1;
        }
        for (int i = length-1; i >= 0 ; i--) {
            for (int j = i+1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][length-1];

    }

    public static void main(String[] args) {
        new Solution516().longestPalindromeSubseq("cbbd");
    }
}