package stack;
// next greater number 类型题目 496 739  单调栈

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

// leetcode 496 栈 简单
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
        // stack容器定义
        Deque<Integer> stack = new ArrayDeque<Integer>();
        // 从前往后遍历 效率较低
        /*for (int i = 0; i < nums2.length; i++) {
            // 借助栈进行大小关系的判断
            // 当前元素 与 栈顶元素进行比较
            // 栈里面的元素 是要储存的结果的值 数组元素是比结果要大的第一个值
           while (!stack.isEmpty() && stack.peek() < nums2[i]){
                // 数组元素大于当前栈栈顶的元素
                res.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);// 将该数组元素放入栈中
        }
        while (!stack.isEmpty()){
            res.put(stack.pop(),-1);
        }*/



        // 从后往前遍历
        for (int i = nums2.length - 1; i >= 0; i--) {
            // 数组从后往前遍历 放入栈中，再由栈中取出时，就正好是数组中的顺序
            while (!stack.isEmpty()&& stack.peek() <= nums2[i]){
                // 数组要放入的元素比当前栈的元素要大或等于，栈中的元素就不可能再是潜在答案
                // 数组要放入的元素有可能是潜在的答案
                stack.pop();// 从栈中取出该元素
            }
            res.put(nums2[i],stack.isEmpty() ? -1 : stack.peek());// 栈是空，说明数组中该元素之后没有比该值要大的
                                                         // 栈不是空，该栈的最顶部元素是大于该元素的第一个值
            stack.push(nums2[i]);// 将数组元素放入栈中
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer integer = res.get(nums1[i]);// 最后结果
            ans[i] = integer;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution496 solution496 = new Solution496();
        int[] ints = solution496.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
    }
}
// leetcode 739 中等
class Solution739{
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            // 由后至前遍历元素,按数组的顺序进行出栈
            // 栈中只存放可能的答案，即可能会出现的比之前温度高的情况
            // 栈中存放数组的索引，因为题目要求返回答案是数组的索引
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]){
                // 栈不为空且栈中的元素 <= 数组元素，栈中元素不可能称为答案，因为其比前面的温度还要低
                stack.pop();
            }
            // 退出上面那个循环，栈中的元素要么为空表示不存在比数组该值还要高的温度，答案数组该位置存储0
            // 栈中如果有元素 栈中的元素都会比数组该元素要大 ，栈顶元素即为 大于数组元素的第一个值
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);

        }

        return res;
    }

    public static void main(String[] args) {
        Solution739 solution739 = new Solution739();
        int[] ints = solution739.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}
// leetcode 503 中等  循环数组 利用余数来解决循环数组
class Solution503{
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int length = nums.length;
        // 从后往前遍历数组
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = length*2 - 1; i >= 0; i--) {
            // 遍历数组 stack内存放的值都是比数组的值要大的元素
            while (!stack.isEmpty() && stack.peek() <= nums[i%length]){
                // 元素值小 不需要
                stack.pop();

            }
            res[i%length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i%length]);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution503 solution503 = new Solution503();
        int[] ints = solution503.nextGreaterElements(new int[]{1, 2, 1});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }


    }
}
// 中等
class Solution735{
    public int[] asteroidCollision(int[] asteroids) {


        // 当前数组元素 > 0 且 栈顶元素 < 0 才会出现碰撞情况
        Deque<Integer> stack = new ArrayDeque<Integer>();

        // 正序遍历 使用迭代
        for (int asteroid : asteroids) {
            if (asteroid > 0){
                // 数组元素 > 0 这种情况下考虑栈全部存放 > 0元素
                // 且只有栈顶元素 > 0 时才会出现碰撞的情况
                stack.push(asteroid);
            }else {
                // 数组元素 < 0 由于栈内 存放 > 0元素 有可能发生碰撞
                // 隐含条件 数组元素 < 0
                while (!stack.isEmpty()  && stack.peek() > 0 && -asteroid > stack.peek()){
                    // 这种情况下会发生碰撞
                    // 数组元素 > 栈顶元素
                    stack.pop();
                }
                if (!stack.isEmpty() && -asteroid == stack.peek()){
                    // 两元素相等
                     stack.pop();
                }else if (stack.isEmpty() || stack.peek() < 0){
                    // 当前分支下 隐含条件 数组元素 < 0
                    // 仅有当栈顶元素 < 0 时 才可以入栈
                    stack.push(asteroid);
                }
            }

        }
        // 栈逆序入数组
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >=0; i--) {
            res[i] = stack.pop();
        }
        // 逆序遍历数组 入栈
        // 使用fori 遍历数组每个元素 逆序遍历
        /*for (int i = asteroids.length - 1; i >= 0 ; i--) {
            while (i >=0 &&!stack.isEmpty() && asteroids[i] > 0 && stack.peek() < 0){
                // 这种情况下需要比较数组当前元素和栈内元素的关系
                if (Math.abs(asteroids[i]) > Math.abs(stack.peek())){
                    // 数组元素 绝对值比较 比栈顶元素要大
                    stack.pop();
                }else if (Math.abs(asteroids[i]) == Math.abs(stack.peek())){
                    // 两者相等 栈 pop i-- ;
                    stack.pop();
                    i--;
                }else {
                    i--;
                }
            }
            if (i >= 0){
                stack.push(asteroids[i]);
            }



        }*/
        /*int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
                res[i] = stack.pop();
        }*/




        return res;
    }

    public static void main(String[] args) {


        Solution735 solution735 = new Solution735();
        int[] ints = solution735.asteroidCollision(new int[]{5,10,-5});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}

