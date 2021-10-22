package stack;

import java.util.*;

// LeetCode 20 简单 栈
public class Solution20 {
    public boolean isValid(String s) {

        Deque<Character> stack = new LinkedList<Character>();
        // 建立哈希表 来记录左右括号的对应情况
        HashMap<Character, Character> map = new HashMap<Character, Character>() {{
            put(']', '[');
            put(')', '(');
            put('}', '{');
        }};
        // 如果输入是奇数 ， 必定返回false
        if (s.length()%2 == 1){
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            // 左括号入栈 遇到右括号判断栈顶元素是不是匹配左右括号
             if (!map.containsKey(x)){
                 // 遍历的元素 如果是左括号 直接入栈
                 stack.push(x);
             }else {
                 // 遇到右括号 判断与栈顶元素是不是匹配
                 if (!stack.isEmpty() && map.get(x).equals(stack.peek())){
                     stack.pop();
                 }else {
                     return false;
                 }
             }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        boolean valid = solution20.isValid("(){]");
        System.out.println(valid);
    }
}
// leetcode 394 中等
class Solution394{
    public String decodeString(String s) {
        char[] arr = s.toCharArray();
        Deque<StringBuilder> chars = new LinkedList<StringBuilder>();
        LinkedList<Integer> nums = new LinkedList<Integer>();
        int count = 0; // 统计数字
// 放一个空的StringBuilder处理这种情况的开头： abc4[d]
        chars.push(new StringBuilder());



        for (char c : arr) {
            // 遍历字符串每一个元素
            // 分为4种情况
            // 1. [ 2. ] 3.数字 4.字符
            if (c >= '0' && c <= '9'){
                // 情况3
                count = count * 10 + c - '0';
            }else {
                if (c == '['){
                    // 情况 1 左括号可以作为数字的终结位置 将数字存入要保存数字的栈中
                    if (count > 0){
                        nums.push(count);
                        count = 0;// 将数字置零 每一次新的数字要重新处理
                    }
                    chars.push(new StringBuilder());// 每一个数字存入要对应一个StringBuilder 来处理字符
                }else if (c == ']'){
                    // 情况2 这种情况需要计算当前字符栈要保存的内容
                    int x = nums.isEmpty() ? 1 :nums.pop(); // 当 [ 之前没有数字时，要进行处理
                    StringBuilder str = chars.pop();
                    while (x > 0){                       // 默认这种情况下，至少会有一个元素
                        chars.peek().append(str);
                        x --;
                    }



                }else {
                    // 情况4 字符是字母 要保存到字符栈中

                    chars.peek().append(c);

                }

            }

        }




        return chars.peek().toString();
    }

    public static void main(String[] args) {
        /*Solution394 solution394 = new Solution394();
        String s = solution394.decodeString("abc4[d]");
        System.out.println(s);*/
        Deque<StringBuilder> stack = new ArrayDeque<StringBuilder>();
        int i = 3;
        while (i-- > 0){
            // i = i - 1 > 0 先判断 后运算
            stack.push(new StringBuilder().append(i));
        }
        i = 3;
        while (i-- > 0){
            StringBuilder pop = stack.pop();
            System.out.println(pop);
        }


    }
}
