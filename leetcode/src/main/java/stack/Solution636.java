package stack;

import java.net.Inet4Address;
import java.util.*;

// leetcode 636 中等
public class Solution636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        // 栈存放当前执行的函数
        // 用一个变量存放 时间戳来计算各个程序的执行时间
        Deque<Integer> stack = new ArrayDeque<Integer>();
        String[] split = logs.get(0).split(":");// 数组存放每个字符串的各个内容 进程号 start/end 时间戳
        int process = Integer.parseInt(split[0]);// 将string类型转换为 int类型
        int timeStamp = Integer.parseInt(split[2]);// 存放时间戳
        int[] res = new int[n];// 存放结果的数组
        for (int i = 0; i < logs.size(); i++) {
            // 遍历字符串数组 通过 : 来取出执行的进程和时间戳
            split = logs.get(i).split(":");
            String status = split[1];
            // 根据start end 来计算各程序的执行时间
            // start 入栈 计算
            // end 出栈 计算
            if (status.equals("start")){
                if (!stack.isEmpty()){
                    // 栈内已有执行的进程
                    process = stack.peek();
                    res[process] +=Integer.parseInt(split[2])  - timeStamp;
                }
                stack.push(Integer.parseInt(split[0]));
                timeStamp = Integer.parseInt(split[2]);// 保存上一次的时间戳

            }else {
                // 遇到 end 进行出栈操作
                res[stack.peek()] += Integer.parseInt(split[2]) - timeStamp + 1;
                stack.pop();
                timeStamp = Integer.parseInt(split[2]) + 1;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        Solution636 solution636 = new Solution636();

        int[] ints = solution636.exclusiveTime(1, new ArrayList<String>(Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7")));
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
// leetcode 84 困难 单调栈
class Solution84{
    public int largestRectangleArea(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<Integer>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于或等于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }

        return area;
    }

    public static void main(String[] args) {
        Solution84 solution84 = new Solution84();
        int i = solution84.largestRectangleArea(new int[]{3, 3, 5});
        System.out.println(i);
    }


}

