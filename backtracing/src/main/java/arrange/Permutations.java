package arrange;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[nums.length];
        backTracking(nums);
        return res;
    }
    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);
            backTracking(nums);
            path.remove(path.size() - 1);
            used[i] = false;

        }

    }


}
class Solution47 {
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums);
        return res;
    }
    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            if (used[i] == true) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backTracking(nums);
            path.remove(path.size() - 1);
            used[i] = false;

        }

    }
}


