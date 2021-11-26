package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/26/17:01
 * @ProjectName Algorithms4th
 * @ClassName: Solution
 * @Description: 回溯 - 其他问题
 */
public class Solution {
}
class Solution491 {
    List<List<Integer>> res;
    List<Integer> path;
    Integer pre = null;
    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(0, nums);
        return res;
    }
    private void backTracking(int start, int[] nums) {
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        // 特解 数字范围是 [-100, 100]
        boolean[] used = new boolean[201];
        for (int i = start; i < nums.length; i++) {

            if (pre == null || pre <= nums[i] ) {
                if (used[nums[i] + 100]) {
                    continue;
                }
                path.add(nums[i]);
                used[nums[i] + 100] = true;
                backTracking(i + 1, nums);
                used[nums[i] + 100] = false;
                path.remove(path.size() - 1);
            }
            pre = nums[i];

        }
    }
}
