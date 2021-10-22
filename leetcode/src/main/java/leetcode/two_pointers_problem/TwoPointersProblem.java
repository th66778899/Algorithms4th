package leetcode.two_pointers_problem;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointersProblem {
}
class Solution27 {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val){

                nums[slow] = nums[fast];
                slow++;
            }

        }
        return slow;
    }
}
class Solution26 {
    public int removeDuplicates(int[] nums) {
        int length = process(nums, 1);
        return length;
    }
    private int process(int[] nums,int k){
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (fast < k || nums[fast] != nums[fast - k]){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
class Solution283 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0){
                nums[index] = num;
                index++;
            }
        }
        while (index < nums.length){
            nums[index++] = 0;

        }
    }
}
class Solution844 {
    public boolean backspaceCompare(String s, String t) {
        int left = s.length() - 1;
        int right = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (true){
            // 自右向左 消除 # 号 , 统计要消除的字符
            while (left >= 0){
                if (s.charAt(left) == '#'){
                    skipS++;
                    left--;
                }else if (skipS > 0){
                    skipS--;
                    left--;
                }else {
                    break;
                }
            }
            while (right >= 0){
                if (t.charAt(right) == '#'){
                    skipT++;
                    right--;
                }else if (skipT > 0){
                    skipT--;
                    right--;
                }else {
                    break;
                }
            }
            // 比较字符串
            if (left < 0 || right < 0){
                break;
            }
            if (s.charAt(left) != t.charAt(right)){
                return false;
            }
            left--;
            right--;
        }
        if (left == -1 && right == -1){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution844().backspaceCompare("ab##", "c#d#"));
    }
}
class Solution977 {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        int index = right;
        while (left < right){
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare >= rightSquare){
                res[index] = leftSquare;
                left++;
            }else {
                res[index] = rightSquare;
                right--;
            }
            index--;
        }
        res[0] = nums[left] * nums[left];
        return res;
    }
}
class Solution344 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
class Solution_Offer_05 {
    public String replaceSpace(String s) {
        String res = s.replaceAll("\\s","%20");
        /*StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' '){
                sb.append("%20");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();*/

        return res;
    }
}
class Solution151 {
    public String reverseWords(String s) {
        // 1.去除首尾空格,中间多余空格
        // 2.翻转字符串
        // 3.翻转单词
        StringBuilder sb = removeSpace(s);
        reverseString(sb,0,sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s){
        int left = 0;
        int right = s.length() - 1;
        while (s.charAt(left) == ' '){
            left++;
        }
        while (s.charAt(right) == ' '){
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right){
            if (s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(s.charAt(left));
            }
            left++;
        }

        /*for (int i = left; i <= right; i++) {
            if (s.charAt(i) != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(s.charAt(i));
            }
        }*/
        return sb;
    }
    // [left,right]
    private void reverseString(StringBuilder sb,int left,int right){
        while (left < right){
            char temp = sb.charAt(left);
            sb.setCharAt(left,sb.charAt(right));
            sb.setCharAt(right,temp);
            left++;
            right--;
        }
    }
    private void reverseEachWord(StringBuilder sb){
        int left = 0;
        int right = 1;
        while (left < sb.length()){
            while (right < sb.length() && sb.charAt(right) != ' '){
                right++;
            }
            // right 此时指向空格字符
            reverseString(sb,left,right - 1);
            left = right + 1;
            right = left + 1;
        }
    }
}

class Solution206 {
    public ListNode reverseList(ListNode head) {
        return reverse(null,head);
    }
    private ListNode reverse(ListNode pre,ListNode cur){
        if (cur == null){
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur,temp);
    }
}
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode fast = pre;
        // ListNode cur = head;

        while (n-- >= 0){
            fast = fast.next;
        }
        while (fast != null){
            pre = pre.next;
            // cur = cur.next;
            fast = fast.next;
        }
        pre.next = pre.next.next;
        return dummyNode.next;

    }
}
class Solution_Interview_02_07 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lengthA = 0;
        int lengthB = 0;
        while (curA != null){
            lengthA++;
            curA = curA.next;
        }
        while (curB != null){
            lengthB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if (lengthA > lengthB){
            while (lengthA != lengthB){
                curA = curA.next;
                lengthA--;

            }
        }else if (lengthA < lengthB){
            while (lengthA != lengthB){
                curB = curB.next;
                lengthB--;

            }
        }
        while (curA != null && curB != null){
            if (curA == curB){
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                // 有环存在
                fast = head;
                while (fast != null){
                    if (slow == fast){
                        return slow;
                    }
                    fast = fast.next;
                    slow = slow.next;

                }
            }
        }

        return null;
    }
}
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 先选择一个数 a
        // 再使用两个指针 slow fast 找出合适的数
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 最小值 > 0 之间返回
            if (nums[0] > 0){
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){

                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0){
                    left++;
                }else if (sum > 0){
                    right--;
                }else {
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
class Solution18 {
    // O(n^3) 时间复杂度
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target){
                        left++;
                    }else if (sum > target){
                        right--;
                    }else {
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        // 处理 左右指针 的 重复情况
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }

            }
        }
        return res;
    }
}