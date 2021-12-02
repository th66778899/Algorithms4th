package version2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/12/2/14:02
 * @ProjectName Algorithms4th
 * @ClassName: BackTrackingPart2
 * @Description: 回溯 第二部分
 */
public class BackTrackingPart2 {
}
class Solution90{
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
class Solution491 {
    List<List<Integer>> res;
    List<Integer> path;
    Integer pre;
    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        pre = null;
        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            if (i == 0 || nums[i - 1] <= nums[i]) {
                path.add(nums[i]);

                backTracking(nums, i + 1);
                path.remove(path.size() - 1);
            }

        }
    }

    public static void main(String[] args) {
        new Solution491().findSubsequences(new int[]{4, 6, 7, 7});
    }
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
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
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
            path.add(nums[i]);
            used[i] = true;
            backTracking(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}