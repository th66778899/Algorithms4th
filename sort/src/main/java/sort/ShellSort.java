package sort;
//希尔排序
//时间复杂度:最坏情况O(nlogn^2)
//          平均情况O(nlogn)
//空间复杂度:O(1)
//稳定性:不稳定
public class ShellSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,-2,6,3};
        //bubbleSort(arr);
        shellSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    /*
    * public static void insertSort(int [] arr){
        int insertIndex = 0;
        int insertValue = 0;
        for (int i = 1; i < arr.length; i++) {
        * //步长为1变为步长为gap的插入排序
            insertIndex = i;
            insertValue = arr[i];
            while (insertIndex -1>= 0 && insertValue < arr[insertIndex-1]){
                //要插入的值小于其 下标前面的值，且前面的值的下标不能为0
                //将其前面的值进行右移，覆盖后面的值，要插入的值被覆盖也没事，
                //insertValue已经存储了要插入的值
                arr[insertIndex] =arr[insertIndex-1];
                insertIndex--;
            }
            //循环结束后，把要插入的值放到合适的位置上
            arr[insertIndex] = insertValue;
        }

    }
    * */
    public static void shellSort(int[] arr){
        //步长 gap
        for (int gap = arr.length/2; gap > 0  ; gap /= 2) {
            for (int i = gap ; i < arr.length; i++) {
                //步长为1变为步长为gap的插入排序
                //插入式，间隔为gap的插入排序
                int insertIndex = i;
                int insertValue = arr[i];
                while (insertIndex - gap >=0 && insertValue < arr[insertIndex-gap]){
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }

                    arr[insertIndex] = insertValue;
                //冒泡式  效率没有插入式高，低一点点


            }
        }
    }

}
