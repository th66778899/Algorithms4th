package leetcode.stack_queue_problem;

import java.util.*;

public class Stack_Queue_Problem {
}
class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;
    public MyQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void push(int x) {
        inStack.addFirst(x);
    }

    public int pop() {
        dumpInStack();
        return outStack.removeFirst();
    }

    public int peek() {
        dumpInStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
    private void dumpInStack(){
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.addFirst(inStack.pollFirst());
            }
        }
    }
}
class MyStack {
    Deque<Integer> stack;
    public MyStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.addFirst(x);
    }

    public int pop() {
        return stack.removeFirst();
    }

    public int top() {
        return stack.peekFirst();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
class Solution20 {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>(){{
            put(')','(');
            put(']','[');
            put('}','{');
        }};
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)){
                stack.addFirst(c);
            }else {
                if (stack.peek() == map.get(c)){
                    stack.removeFirst();
                }else {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }
}
class Solution1047 {
    public String removeDuplicates(String s) {
        String res = "";
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c){
                stack.pop();
            }else {
                stack.push(c);
            }

        }
        while (!stack.isEmpty()){
            res += stack.removeLast();
        }
        return res;
    }
}
class Solution150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if ("+".equals(token)){
                int num1 = stack.pop();
                int num2 =  stack.pop();
                int res = num1 + num2;
                stack.push(res);
            }else if ("-".equals(token)){
                int num1 = stack.pop();
                int num2 =  stack.pop();
                int res = num2 - num1;
                stack.push(res);
            }else if ("*".equals(token)){
                int num1 = stack.pop();
                int num2 =  stack.pop();
                int res = num1 * num2;
                stack.push(res);
            }else if ("/".equals(token)){
                int num1 = stack.pop();
                int num2 =  stack.pop();
                int res = num2 / num1;
                stack.push(res);
            }else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.peek();
    }
}
class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // 窗口定义 [i - k + 1,i]
            // 双端队列 queue 来存储窗口中的最大值
            // 要保证队列首元素是当前窗口最大值
            // 且 队首元素的下标要符合题目要求
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1){
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);

            if (i >= k - 1){
                res[index] = nums[queue.peekFirst()];
                index++;
            }
        }

        return res;
    }
}
class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (queue.size() == k){
                // 优先级队列已满
                if (queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }else {
                queue.offer(new int[]{num,count});
            }
        }


        // res[] 存储的是出现的次数
        int index = 0;
        while (index < k){
            res[index] = queue.poll()[0];
            index++;
        }
        return res;
    }
}