package sort;

//选择排序
//时间复杂度:O(n^2)
//空间复杂度:O(1)
//稳定性:不稳定
public class SelectSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,-2,6,3};
        //bubbleSort(arr);
        selectSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void selectSort(int[] arr){
        int min = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length-1; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                // arr[j] < min 减少执行时间
                // arr[j] <= min 增加选择排序稳定性
                //有时候也是不稳定的  10 2 5 3 2 进行选择排序 也是不稳定的
                if (arr[j] < min){
                    //如果min不是数组中的最小值，给min和minIndex赋新的值
                    min = arr[j];
                    minIndex = j;
                }
            }
            //一趟选择排序完成后，判断minIndex有没有发生变化
            //没有发生变化，minIndex就是最小值下标
            //发生变化，需要将arr[minIndex]与arr[i]进行交换
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
