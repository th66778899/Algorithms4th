package binarySearch;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

// 力扣1011 中等 在D天内送达包裹的能力
public class Solution1011 {
    public int shipWithinDays(int[] weights, int days) {
        int min = 0;// 题目所给输入并不是一个有序序列
        int max = 0;// 船只运载量的最大值
        for (int weight : weights) {
            max += weight;
            min = Math.max(min, weight);
        }
        int l = min,r = max;
        while (l < r){
            int mid = l + (r - l) / 2;
            if (check(weights,mid,days)){
                // 满足要求，可以缩减船只载货量,但当前载货量可能是最小值
                r = mid;
            }else {
                // 不满足要求 ，必须增大船只载货量
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     *
     * @param arr 所有货物的信息
     * @param t   船只的载货量
     * @param d   完成目标需要的天数
     * @return
     */
    public boolean check(int[] arr,int t,int d){
        int day = 1;// 默认情况下，至少有一天
        int n = arr.length;// 共 n 个货物需要运送
        int sum = 0;// 每天要运送货物的总和 该值要小于 船只的载货量
        for (int i = 0; i < n;sum = 0){
            // 每进行一次for循环，天数会加1
            while (i < n && (sum + arr[i])<= t){
                sum += arr[i];
                i++;
            }
            day++;
        }
        return day - 1 <= d;
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,4,5,6,7,8,9,10};
        Solution1011 solution1011 = new Solution1011();
        int i = solution1011.shipWithinDays(x, 5);
        System.out.println(i);
    }

}
// 力扣 852 简单
class Solution852{
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r - 1){
            int mid = l + (r - l) / 2;
            // 找到了合适的值
            if (arr[mid] >arr[mid+1] && arr[mid] > arr[mid - 1] ){
                return mid;
            }else if(arr[mid] > arr[mid + 1]){
                r = mid;
            }else {
                l = mid;
            }
        }
        return 1;


    }

    public static void main(String[] args) {
        Solution852 solution852 = new Solution852();
        int[] arr = {24,69,100,99,79,78,67,36,26,19};
        int i = solution852.peakIndexInMountainArray(arr);
        System.out.println(i);

    }
}
class Solution1292{
    // 前缀和 二分  差分
    // 二维前缀和
    public int maxSideLength(int[][] mat, int threshold) {
        return 1;

    }
}
// 前缀和
class Solution303{
    int[] sums; // 保存前缀和数组
    public Solution303(int[] nums) {
        int length = nums.length;
        sums = new int[length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }

    public static void main(String[] args) {
        int[] x = {-2, 0, 3, -5, 2, -1};
        Solution303 solution303 = new Solution303(x);
        int i = solution303.sumRange(0, 2);
        System.out.println(i);
    }
}
// 二维前缀和 中等
class Solution304{
    int [][] preSum;
    public Solution304(int[][] matrix) {
        preSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            // 行
            for (int j = 0; j < matrix[0].length; j++) {
                // 列
                preSum[i+1][j+1] = preSum[i][j+1] + preSum[i+1][j] - preSum[i][j] + matrix[i][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1] - preSum[row2+1][col1] - preSum[row1][col2+1] + preSum[row1][col1];
    }

    public static void main(String[] args) {
        /*int[][] x= {{0,1},{1,3}};
        Solution304 solution304 = new Solution304(x);
        int[][] preSum = solution304.preSum;
        for (int[] ints : preSum) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");

            }
            System.out.println();
        }*/

        System.out.println(7&1);
    }
}
// 力扣 29 中等  二分 快速乘法(倍增思想)
class Solution29{
    public int divide(int dividend, int divisor) {
        long result = 0;
        long x = dividend;// 被除数
        long y = divisor;// 除数
        boolean isNeg = x < 0 && y > 0 || x > 0 && y < 0;// 最后结果为负数
        if (x < 0) x = -x;
        if (y < 0) y = -y;// x,y为负数时转换为正数计算
        // x/y 最后结果肯定在0-x之间
        long left = 0;
        long right = x;
        while (left < right){
            // mid = true 时 移动left mid = l+r+1>>1
            // mid = true 时 移动right mid = l+r >> 1
            long mid = (left + right +1) >> 1;
            if (multiply(mid,y) <= x){
                // 两数相乘 结果小于被除数x，可以加大mid
                left = mid;
            }else {
                right = mid - 1;
            }


        }
        result = isNeg ? -left : left;// 结果为负数 最后结果为 -left
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) result;

    }
    /**
     *  快速乘法模板 倍增思想
     * @param a 被乘数
     * @param b 乘数
     * @return a*b a是被乘数，b是乘数
     */
    public static long multiply(long a,long b){
        long res = 0;
        while (a > 0){
            if ((a&1)==1){
                // 被乘数最低位为1，被乘数为奇数时
                res = res + b;
            }
            a = a >> 1;// a右移一位 相当于 /2
            b = b + b;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        int divide = solution29.divide(-2147483648, -1);
        System.out.println(divide);


        /*long multiply = multiply(5, 6);
        System.out.println(multiply);*//*
        // double x = 5,y = 6;
        double sum = 60.0 / 7;
        double f = 111231.5585;

        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(0);

        double x = ( 60.0 / 11) * 6;
        double z = x + 6;
        // DecimalFormat df = new DecimalFormat("#.00");

        String score = df.format(x);
        String num = "7";
        int i = Integer.parseInt(num);



        System.out.println(format);*/

    }

}
// 力扣 中等 搜索旋转排序数组  leetcode81 类似题目 因为相同元素 2 2 2 从中间进行旋转，所得出数组并不是具有二段性的
// 需要O(n)的时间复杂度恢复二段性
class Solution33{

    // 使用二分查找旋转数组的节点 复杂度O(logN)
    public int search(int[] nums, int target) {
        int loc = 0;
        int length = nums.length;
        // 使用二分的前提条件 最少有两个元素
        if (length == 0) return -1;
        if (length == 1) return nums[0] == target ? 0 : -1;

        // 遍历数组 loc为前半段有序区间的最后一个元素
        int l = 0,r = length -1;
        while ( l < r){
            int mid = (l + r + 1) >> 1;
            if (nums[mid] < nums[0]){
                r = mid - 1;
            }else {
                l = mid;
            }

        }
        loc = l;


        int res = find(nums,target,0,loc);
        if(res != -1) return res;// 前半段找到了
        if ( loc + 1 < length){
            res = find(nums,target,loc + 1,length -1);
        }
        return res;

    }

    // 遍历一遍数组找到旋转数组的节点 复杂度O(n)
    /*public boolean search(int[] nums, int target) {
        int loc = 0;
        int length = nums.length;
        // 遍历数组 loc为前半段有序区间的最后一个元素
        for(int i = 0;i < nums.length-1;i++){
            if(nums[i] > nums[i+1]){
                loc = i;
                // break 推出循环，否则数组下标会越界
                break;
            }
        }
        int res = find(nums,target,0,loc);
        if(res != -1) return true;// 前半段找到了
        if ( loc + 1 < length){
            res = find(nums,target,loc + 1,length -1);
        }
        return res !=-1 ? true : false;

    }*/
    public int find(int[] arr,int target,int left,int right){
        // 返回目标值的下标
        int l = left;
        int r = right;
        while(l < r){
            // 移动r
            int mid =l + (r - l) / 2 ;
            if (arr[mid] > target){
                // 中间值 小于 target
                r = mid;
            }else if(arr[mid] < target){
                l = mid + 1;
            }else{
                return mid;
            }

        }
        return arr[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        int [] nums = {4,5,6,7,0,1,2};
        int search = solution33.search(nums, 0);
        System.out.println(search);
    }
}
// 因为重复元素 导致失去二段性，需要恢复二段性
class Solution81{




    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        int [] nums = {4,5,6,7,0,1,2};
        int search = solution33.search(nums, 0);
        System.out.println(search);
    }
}
// leetCode 153 中等
class Solution153{
    public int findMin(int[] nums) {
        // 暴力算法 遍历数组 找出最小值 时间复杂度 O(N)
        // 二分查找，找到数组中不满足 a[n] < a[n+1] a[n+1] 就是最小值
        // 二分查找没有找到，说明数组已经是有序的，a[0]就是最小值
        int l = 0;
        int length = nums.length;
        int r = length - 1;

        while (l < r){
            int mid = (l + r + 1) >> 1;

            if (nums[mid] >= nums[0]){
                // 此段为数组的前半段
                l = mid;
            }else {
                r = mid - 1;
            }


        }

        return r + 1 < length ? nums[r + 1] : nums[0];
    }

    public static void main(String[] args) {
        Solution153 solution153 = new Solution153();
        int min = solution153.findMin(new int[]{3,4,0,1,2});
        System.out.println(min);
    }
}
class Solution10_03{
    public int search(int[] arr, int target) {
        // 重复元素出现在 首部 尾部 需要进行处理
        // 重复元素出现在中间不影响分段
        int length = arr.length;
        if (length == 0) return -1;
        if (length == 1) return arr[0] == target ? 0 : -1;
        int k = length;
        // 消除数组末尾与数组首部的相同元素
        while (k - 1 >= 0){
            if  (arr[0] == arr[length-1]){
                length--;
            }
            k--;
        }
        // 二分查找 寻找旋转点 分割
        int l = 0;
        int r = length - 1;
        int loc = 0;// 分割点
        while ( l < r){
            int mid = (l + r + 1) >> 1;
            if(arr[mid] >= arr[0]){
                // 数组的左半部分 移动 l 防止死循环 ，mid 需要 + 1
                l = mid;// 不能排除潜在答案
            }else{
                r = mid - 1;
            }
        }
        loc = l;// loc 就是旋转点
        // 查找数组左半边
        int res = 0;
        int i = find(arr, target, 0, loc);
        res =  i!= -1 ? i : -1;// i!=-1 左半边找到了 返回该值的下标 没找到 i == -1 直接返回-1
        int i1 = find(arr, target, loc + 1, length - 1);
        if (res!=-1){
            return res;
        }else {
            return  i1!=-1 ?i1 : -1;
        }

    }
    public int find(int[] arr,int target,int left,int right){
        int l = left;
        int r = right;

        while (l < r){
            int mid = (l + r) >> 1;
            if (arr[mid] < target){
                l = mid + 1;
            }else {
                // target <= arr[mid] 向左移
                r = mid;
            }
        }
        return arr[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        Solution10_03 solution10_03 = new Solution10_03();
        int search = solution10_03.search(new int[]{15, 16, 19, 20, 25, 1, 3, 4,5 ,5,5, 7, 10, 14}, 5);
        System.out.println(search);
    }
}
// leetcode 1482 中等
class Solution1482{
    public int minDays(int[] bloomDay, int m, int k) {
        int max = bloomDay[0];
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] > max) max = bloomDay[i];
        }
        int length = bloomDay.length;
        if (length < m * k){
            return -1;
        }
        // 最少需要1天 最多需要数组中的最大值的天数
        int l = 0;
        int r = max;
        while (l < r){
            int mid = l + r >> 1;
            if (check(bloomDay,mid,m * k)){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
    public boolean check(int[] arr,int num,int sum){
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num){
                x++;
            }
        }
        if (x >= sum){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution1482 solution1482 = new Solution1482();
        int i = solution1482.minDays(new int[]{1,10,3,10,2}, 3, 2);
        System.out.println(i);
    }
}
class Solution34{
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};

        int l = 0;
        int length= nums.length;
        if(length == 0) return res;

        int r = length - 1;
        int i = 1;
        while (l < r){
            int mid = (l + r) >> 1;
            // 寻找最后一个目标值
            if (nums[mid] >= target){
                r = mid;
            }else {
                l = mid + 1;
            }

        }
        if (nums[l] != target){
            // 整个数组没有目标值
            return res;
        }else {
            // 找到了目标值的第一个元素
            res[0] = l;
            l = 0;
            r = length - 1;
            while (l < r){
                int mid = (l + r + 1) >> 1;
                if (nums[mid] <= target){
                    // 向→移动
                    l = mid;
                }else {
                    r = mid - 1;
                }
            }
            // 数组中找到了第一次出现的目标值，数组中已经确定有目标值存在
            // 第二次寻找不需要判断能不能找到目标值，必定会存在目标值
            // 数组中只出现了一次目标值 结果数组的两个值全部都是该目标值的位置
            res[1] = l;
        }




        return res;
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        int[] ints = solution34.searchRange(new int[]{5,7,7,8,8,10}, 6);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
// leetcode154 困难
class Solution154 {
    public int findMin(int[] nums) {
        int length = nums.length;

        // 需要预处理 去除尾部相同元素
        while (length>0 && nums[length -1] == nums[0]){
            length --;
        }
        // 如果只有一个元素不需要二分，直接返回数组第一个元素
        if (nums.length==1) return nums[0];
        // 二分查找旋转点

        int l = 0;
        int r = length - 1;
        while (l < r){
            int mid = (l + r + 1) >> 1;
            if (nums[mid] >= nums[0]){
                l = mid;
            }else {
                r = mid - 1;
            }
        }

        return l + 1 < length ? nums[l+1] : nums[0];
    }
    /*public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r && nums[0] == nums[r]) r--;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r + 1 < n ? nums[r + 1] : nums[0];
    }*/


    public static void main(String[] args) {
        Solution154 solution154 = new Solution154();
        int min = solution154.findMin(new int[]{2,2,2,0,1});
        System.out.println(min);
    }
}
