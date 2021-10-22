package sliding_window;

import java.util.HashMap;

/*class Solution:
    def problemName(self, s: str) -> int:
        # Step 1: 定义需要维护的变量们 (对于滑动窗口类题目，这些变量通常是最小长度，最大长度，或者哈希表)
        x, y = ..., ...

        # Step 2: 定义窗口的首尾端 (start, end)， 然后滑动窗口
        start = 0
        for end in range(len(s)):
            # Step 3: 更新需要维护的变量, 有的变量需要一个if语句来维护 (比如最大最小长度)
            x = new_x
            if condition:
                y = new_y

            '''
            ------------- 下面是两种情况，读者请根据题意二选1 -------------
            '''
            # Step 4 - 情况1
            # 如果题目的窗口长度固定：用一个if语句判断一下当前窗口长度是否超过限定长度
            # 如果超过了，窗口左指针前移一个单位保证窗口长度固定, 在那之前, 先更新Step 1定义的(部分或所有)维护变量
            if 窗口长度大于限定值:
                # 更新 (部分或所有) 维护变量
                # 窗口左指针前移一个单位保证窗口长度固定

            # Step 4 - 情况2
            # 如果题目的窗口长度可变: 这个时候一般涉及到窗口是否合法的问题
            # 如果当前窗口不合法时, 用一个while去不断移动窗口左指针, 从而剔除非法元素直到窗口再次合法
            # 在左指针移动之前更新Step 1定义的(部分或所有)维护变量
            while 不合法:
                # 更新 (部分或所有) 维护变量
                # 不断移动窗口左指针直到窗口再次合法

        # Step 5: 返回答案
        return ...
*/
public class Basic {
}
class Solution643 {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;// 滑动窗口内元素之和
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        // sum保存了 [0,k) 共k个大小的窗口
        int max = sum;// 保存最大值
        // 窗口右移 左右指针都向右移
        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(max,sum);
        }
        return 1.0 * max / k;
    }
}
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int start = 0,end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)){
                start = Math.max(start,map.get(c));
            }
            res = Math.max(res,end - start + 1);
            map.put(c,end + 1);
        }
        return res;

    }
    public boolean isUgly(int n) {
        if (n < 0) return false;
        while( n % 2 == 0) { n /= 2;}
        while (n % 3 == 0) { n /= 3;}
        while (n % 5 == 0) {n /= 5;}
        return n == 1;
    }


}

