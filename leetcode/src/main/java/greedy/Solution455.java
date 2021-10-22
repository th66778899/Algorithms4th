package greedy;

import java.util.Arrays;
import java.util.stream.IntStream;

// leetcode 455 简单 贪心
public class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        // 贪心算法
        // 小饼干 先给 需求小的人
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int sum = 0;
        for (int i = 0; i < s.length && index < g.length; i++) {
            if (s[i] >= g[index]){
                index++;
                sum++;
            }

        }
        return sum;
    }
}
// leetcode 1005 简单
class Solution1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //从前向后遍历，遇到负数将其变为正数，同时K--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if (k % 2 == 1) nums[len - 1] = -nums[len - 1];
        int result = 0;
        for (int a : nums) {
            result += a;
        }
        return result;
    }
}
// leetcode 860 简单 贪心
class Solution860 {
    public boolean lemonadeChange(int[] bills) {
        // 纸币只有三种情况 5 10 20
        // 5 10 两种情况是固定的 5不需要找零
        // 10 只需要一张5元
        // 20 有两种情况 10 + 5 或者 5*3
        // 20找零 优先  10 + 5 5元纸币能找零的情况更多
        int bill_5 = 0;
        int bill_10 = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5){
                bill_5++;
            }else if (bills[i] == 10){
                bill_5--;
                bill_10++;
            }else {
                // 20元纸币
                if ( bill_10 > 0){
                    bill_5--;
                    bill_10--;
                }else {
                    bill_5-=3;
                }
            }
            if (bill_5 < 0 || bill_10 < 0){
                return false;
            }
        }

        return true;
    }
}
// leetcode 376 摆动序列 贪心
class Solution376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }
}
