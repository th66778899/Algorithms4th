package binarySearch;

import org.junit.jupiter.api.Test;

// 简单的二分查找 查找某个值
public class SimpleBinarySearch {
    /**
     * 有序数组里寻找某个值
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearch(int[] arr,int k){
        int l = 0, r = arr.length - 1;
        while ( l <= r){
            // int mid = (l + r) / 2;// l+r 有可能会超过int_max 导致 变成一个负数
            int mid = l + (r - l) / 2;
            if (arr[mid] == k){
                return mid;
            }else if (arr[mid] > k){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     *  测试 binarySearch
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,6,7,9};
        int i = binarySearch(arr, 3);
        if (i==-1){
            System.out.println("未找到");
        }else {
            System.out.println("找到了"+i);
        }
    }


    /*
    * 场景变换 找最接近 3 的数
    *         找比 3 大的最小数字
    */

}

/**
 * 第一次出现2的位置   寻找一个模糊值
 * 循环条件：l<r 只有两个元素的时候，前一个元素符合要求，l<=r l，r都在前一个元素上，r=mid，会进入死循环
 * 削减搜索空间 l = mid, r=mid-1 或 l=mid+1,r=mid
 */
class FirstOccuranceOf2{
    /**
     * 找 模糊值 2第一次出现的位置
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchFindVague(int[] arr,int k){
        int l = 0;
        int r = arr.length - 1;

        while (l < r){
            int mid = l + (r - l) / 2;
            if (arr[mid] < k){
                l = mid + 1;
            }else{
                r = mid;
            }

            /*if (arr[mid] == k){
                r = mid;
            }else if(arr[mid] < k){
                l = mid + 1;
            }else {
                r = mid-1;
            }*/
        }
        return l;
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,3,5,6,7};
        int a = binarySearchFindVague(x,2);
        System.out.println(a);
    }


    /**
     * 找 模糊值 2 最后一次出现的位置
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchFindVague1(int[] arr,int k){
        int l = 0;
        int r = arr.length - 1;

        while (l < r){
            int mid = l + (r - l + 1) / 2;// 两个元素 2 3 不 + 1  会导致进入死循环
            /*if (arr[mid] < k){
                l = mid + 1;
            }else if (arr[mid] == k){
                l = mid;
            }else if(arr[mid] > k){
                r = mid - 1;
            }*/
            // 上面的代码简化版本
            if (arr[mid] > k){
                r = mid - 1;
            }else {
                l = mid;
            }
        }
        return l;
    }
    @Test
    void testLastOccurance1(){
        int[] x = {1,2,3,3,5,6,7};
        int a = binarySearchFindVague1(x,3);
        System.out.println(a);
    }
    /**
     * 找 模糊值  最接近k的值
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchFindVague2(int[] arr,int k){
        int l = 0;
        int r = arr.length - 1;

        while (l < r - 1){
            // 退出条件 当 l r 相邻时
            int mid = l + (r - l) / 2;
            /*if (arr[mid] < k){
                l = mid + 1;
            }else if (arr[mid] == k){
                l = mid;
            }else if(arr[mid] > k){
                r = mid - 1;
            }*/
            // 上面的代码简化版本
            if (arr[mid] > k){
                r = mid;
            }else {
                l = mid;
            }

        }
        if (arr[r] < k){
            return r;
        }else if (arr[l] > k){
            return l;
        }else {
            return k - arr[l] > arr[r] - k ? r : l;
        }

    }
    @Test
    void testLastOccurance(){
        int[] x = {1,2,2,2,5,6,7};
        int a = binarySearchFindVague2(x,4);
        System.out.println(a);
    }

}
