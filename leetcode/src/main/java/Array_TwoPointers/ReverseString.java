package Array_TwoPointers;

import java.util.Stack;

public class ReverseString {

}
//leetcode 344
//简单
class Solution344 {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length;
        while (i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }

    }
}
//leetcode 26
//简单
class Solution26 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length){
            if (i==0 || nums[i-1] != nums[j]){
                nums[i] = nums[j];
                i++;
                j++;
            }else {
                j++;
            }
        }
        return i;

    }
}
//leetcode 80
//中等
class Solution80 {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length){
            if (slow < 2){
                slow++;
                fast++;
            }
            else if (nums[slow-2] == nums[fast]){
                fast++;
            }else if (nums[slow-2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }

        }
        return slow;

    }
    //时间为100%
    /*public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length){
            if (slow < 2 ||nums[slow-2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
            else{
                fast++;
            }

        }
        return slow;
    }*/
}
//leetcode11
//中等
class Solution11 {
    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length -1;
        int area = 0;
        while (left < right){
            if (height[left] < height[right]){
                area = height[left] * (right - left);
                if (max < area){
                    max = area;
                }
                left++;

            }else {
                area = height[right] * (right-left);
                if (max < area){
                    max = area;
                }
                right--;
            }
        }
        return max;

    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int i = maxArea(height);
        System.out.println(i);

    }
}
//leetcode283
//简单
class Solution283 {
    public static void moveZeroes(int[] nums) {
        int left = 0;
        int right = left+1;
        while (right < nums.length){
            if (nums[left] ==0 && nums[right] !=0){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
                right++;
            }else if (nums[left] ==0 && nums[right]==0){
                right++;
            }else {
                left++;
                right++;
            }
        }

    }

    public static void main(String[] args) {
        /*int[] arr = {0,1,0,3,12};
        moveZeroes(arr);
        for (int i : arr) {
            System.out.println(i);
        }*/
        String str = "abcde";
        String substring = str.substring( 2);
        String str1 = str + substring;
        char[] chars = str.toCharArray();
        char aChar = chars[0];
        System.out.println(substring);


    }
}
//letcode1047
//简单
class Solution1047 {
    public static String removeDuplicates(String S) {
        //数组模拟栈解决
        char[] chars = S.toCharArray();
        char[] temp = new char[S.length()];
        int i = -1;
        for (char aChar : chars) {
            if (0<=i && temp[i]==aChar){
                i--;
            }else {
                i++;
                temp[i] = aChar;
            }
        }
        //用stringBuffer将char数组转换为String类型

        /*StringBuilder sb = new StringBuilder();
        while (0<=i){
            sb.append(temp[i]);
            i--;
        }
        sb.reverse();
        String s = String.valueOf(sb);*/

        //用String的重载构造器将char数组转换为String类型
        return new String(temp,0,i+1);

        //栈解决
        /*char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        //int i = chars.length;
        //int j = 0;
        for (char aChar : chars) {
            if (!stack.isEmpty()&&stack.peek() == aChar){
                stack.pop();

            }else {
                stack.push(aChar);
            }
        }




        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());

        }
        sb.reverse();
        return sb.toString();*/
    }

    public static void main(String[] args) {
        String s = "aababaab";
        String s1 = removeDuplicates(s);
        System.out.println(s1);
    }
}

