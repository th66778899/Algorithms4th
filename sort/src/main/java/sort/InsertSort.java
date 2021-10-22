package sort;

//时间复杂度:O(n^2)
//空间复杂度:O(1)
//稳定性:稳定
public class InsertSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,-2,6,3};
        //bubbleSort(arr);
        insertSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void insertSort(int [] arr) {
        int insertIndex = 0;
        int insertValue = 0;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i;
            insertValue = arr[i];
            while (insertIndex - 1 >= 0 && insertValue < arr[insertIndex - 1]) {
                //要插入的值小于其 下标前面的值，且前面的值的下标不能为0
                //将其前面的值进行右移，覆盖后面的值，要插入的值被覆盖也没事，
                //insertValue已经存储了要插入的值
                arr[insertIndex] = arr[insertIndex - 1];
                insertIndex--;
            }
            //循环结束后，把要插入的值放到合适的位置上
            if (insertIndex !=i){
                arr[insertIndex] = insertValue;
            }

        }
    }

}
