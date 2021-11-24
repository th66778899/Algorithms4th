package subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tho
 * @Date 2021/11/24/14:30
 * @ProjectName Algorithms4th
 * @ClassName: Subset
 * @Description: 回溯 - 子集问题
 */
public class Subset {
}
class Solution78 {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        if (start >= nums.length) {return;}

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        new Solution78().subsets(arr);
    }
}
class Solution90 {
    boolean[] used;
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        used = new boolean[nums.length];
        res = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(nums, 0);
        return res;
    }
    private void backTracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        if (start >= nums.length) {return;}
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
               continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backTracking(nums, i + 1);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
