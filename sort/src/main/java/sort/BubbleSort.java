package sort;
//冒泡排序
//时间复杂度:O(n^2)
//空间复杂度:O(1)
//稳定性:稳定

/*
* 对经典的冒泡排序优化可以分为两部分

1、外层循环优化：记录交换标志，如果未发生交换，则跳出循环，排序结束
2、内层循环优化：记录上次排序位置，下次循环对比到该位置
*
对于类似{2,3,4,5,6,1}数组，可通过正向和反向双向冒泡来优化
* */
public class BubbleSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,2,3,5,6,7,-2,-6,9};
        //bubbleSort(arr);
        bubbleSortPP(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    //如果在某一次冒泡排序过程中，没有交换元素，说明该数组已经有序
    public static void bubbleSort(int[] arr){
        //共需要n-1趟的遍历
        int temp=0;
        for (int i = 0; i < arr.length-1; i++) {
            //第n趟遍历需要进行 length-1-n 次比较
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    //如果前一个元素大于后一个元素，两者交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    //冒泡排序优化， 如果数组已经有序，就不再需要进行排序
    //冒泡排序结束前，数组提前有序，可以进行优化
    public static void bubbleSortP(int[] arr){
        //共需要n-1趟的遍历

        int temp=0;
        for (int i = 0; i < arr.length-1; i++) {

            //第n趟遍历需要进行 length-1-n 次比较
            boolean flag = true;
            //每一趟冒泡排序都要假设该数组已经有序了
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){

                    //只要数组不是有序的，一趟冒泡排序必定会进入此方法
                    flag = false;
                    //如果前一个元素大于后一个元素，两者交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            //一趟冒泡排序执行完后，flag还是true，说明该数组已经是有序的，不需要在进行排序
            //退出外层循环
            if (flag){
                break;
            }
        }
    }
    //冒泡排序 优化 ++
    //如果数组序列尾部已经局部有序，可以记录最后一次交换的位置，减少比较次数
    public static void bubbleSortPP(int[] arr){
        //共需要n-1趟的遍历
        int num = 0;
        int temp=0;
        for (int i = 0; i < arr.length-1; i++) {
            //记录最后一次比较的索引
            //数组完全有序时，一轮扫描就结束整个冒泡循环
            int sortedIndex = arr.length-1;
            //第n趟遍历需要进行 length-1-n 次比较

            //num为某一趟冒泡排序需要比较的次数
            num = arr.length - i - 1;
            for (int j = 0; j < num; j++) {
                if (arr[j] > arr[j+1]){


                    //如果前一个元素大于后一个元素，两者交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    //记录最后一次交换的索引下标
                    sortedIndex =j;
                }
            }
            num =  sortedIndex;


        }
    }
}
