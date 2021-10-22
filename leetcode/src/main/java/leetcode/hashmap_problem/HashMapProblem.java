package leetcode.hashmap_problem;

import java.util.*;

public class HashMapProblem {
}
// 使用数组解决字符匹配
class Solution242 {
    public boolean isAnagram(String s, String t) {
        char[] sCnt = new char[26];
        char[] tCnt = new char[26];
        for (char c : t.toCharArray()) {
            tCnt[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            sCnt[c - 'a']++;
        }
        // 另外一种判断
        for (int i = 0; i < 26; i++) {
            if (sCnt[i] != tCnt[i]){
                return false;
            }
        }
        /*if (Arrays.equals(sCnt,tCnt)){
            return true;
        }*/
        return true;
    }
}
class Solution383 {
    public boolean canConstruct(String s1, String s2) {
        // s1 字符个数 <= s2 字符个数
        char[] s1Cnt = new char[26];
        char[] s2Cnt = new char[26];
        for (char c : s1.toCharArray()) {
            s1Cnt[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            s2Cnt[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (s1Cnt[i] != 0){
                if (s1Cnt[i] > s2Cnt[i]){
                    return false;
                }
            }
        }
        return true;
    }
}
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)){
                map.put(key,new ArrayList<String>());
            }
            List<String> strings = map.get(key);
            strings.add(str);


        }
        for (String s : map.keySet()) {
            System.out.println(s);
        }
        /*for (List<String> value : map.values()) {
            for (String s : value) {
                System.out.print(s + " ");
            }
            System.out.println();
        }*/
        return new ArrayList<>(map.values());
    }
}
class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        char[] sCnt = new char[26];
        char[] pCnt = new char[26];
        ArrayList<Integer> res = new ArrayList<>();
        int sLength = s.length();
        int pLength = p.length();
        if (sLength < pLength){
            return res;
        }
        for (int i = 0; i < pLength; i++) {
            sCnt[s.charAt(i) - 'a']++;
            pCnt[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCnt,pCnt)){
            res.add(0);
        }
        for (int i = pLength; i < s.length(); i++) {

            sCnt[s.charAt(i - pLength) - 'a']--;
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt,pCnt)){
                res.add(i - pLength + 1);
            }
        }
        return res;
    }
}
class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();
        // ArrayList<Integer> res = new ArrayList<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)){
                res.add(i);
            }
        }
        int[] res1 = new int[res.size()];
        int index = 0;
        for (Integer re : res) {
            res1[index] = re;
            index++;
        }
        return res1;

    }
}
class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] res = new int[Math.min(length1,length2)];
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        while (index1 < length1 && index2 < length2){
            if (nums1[index1] > nums2[index2]){
                index2++;
            }else if (nums1[index1] < nums2[index2]){
                index1++;
            }else {
                res[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(res,0,index);
    }
}
class Solution202 {
    public boolean isHappy(int n) {
        HashSet<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)){
            record.add(n);
            n = process(n);
        }
        return n == 1;
    }
    private int process(int n){
        int sum = 0;
        while (n != 0){
            int x = n % 10;
            sum += x * x;
            n /= 10;
        }
        return sum;
    }
}
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }
}
class Solution454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        // 四个数相加转换为2两个数相加
        for (int i : nums1) {
            for (int i1 : nums2) {
                    int temp = i + i1;
                    map.put(temp,map.getOrDefault(temp,0) + 1);

            }
        }
        for (int i : nums3) {
            for (int i1 : nums4) {
                int temp = i + i1;
                if (map.containsKey(0 - temp)){
                    res += map.get(0 - temp);
                }
            }
        }
        return res;

    }


}
// 双指针 去重
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) break;// 最小值 > 0 直接退出循环,整个数组元素之和不可能等于0
            // 去重,计算三数之和 时就要去重 ,防止出现相同的组合,三数之和的组合 只需要一个数不同即可
            if (i > 0 && nums[i] == nums[i - 1]){
                // i > 0 防止数组下标越界
                continue;
                // 将这个数剔除选择之中
            }
            // 自i个数 之后 的数组区间,设置两个指针,自区间首尾向 中间寻找
            // 寻找过程 O(n) 为 n^2
            int left = i + 1;
            int right = length - 1;

            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // 三数之和为0 情况下 左右指针可能到达的数可能和之前组合相同,需要去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }else if (sum < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }
}
// 双指针 去重
class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return res;
        }
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = length - 1;
                while (left < right){
                    int temp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (temp == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }else if (temp < target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}