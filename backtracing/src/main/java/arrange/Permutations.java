package arrange;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/24/15:32
 * @ProjectName Algorithms4th
 * @ClassName: Permutations
 * @Description: 回溯 - 全排列问题
 */
public class Permutations {
}
class Solution46 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        return res;
    }
    private void backTracking(int[] nums, int start) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);

        }

    }
}


