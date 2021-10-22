package sort;

public class QuickSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,-2,6,3};
        //bubbleSort(arr);
        quickSort(arr,0,arr.  length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void quickSort(int[] arr,int left,int right){
        if (left >= right){
            //递归退出条件
            return;
        }
        int l = left;
        int r = right;
        while (l<r){
            while (l < r && arr[r] >= arr[left] ) r--;
            while (l < r && arr[l] <= arr[left]) l++;
            if ( r==l){
                //r = l 把最左侧值和要排序的值交换
                int temp = arr[l];
                arr[l] =arr[left];
                arr[left] = temp;
            }else {
                //l != r 把两个下标对应的值进行交换
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;

            }

        }
        //一次快排就结束了，还需要快排左边的元素和右边的元素
        quickSort(arr,left,r-1);
        quickSort(arr,l+1,right);

    }
}
