package sort;
//归并排序
//时间复杂度:O(nlogn)
//空间复杂度:O(n)
//稳定性:稳定
public class MergeSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,-2,6,3};
        //bubbleSort(arr);
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        for (int i : arr) {
            System.out.print(i + "   ");
        }
    }
    public static void mergeSort(int[] arr,int left,int right,int []temp){
        if (left < right){
            //将数组拆分
            int mid = (left + right)/2;
            //将mid左边继续拆分
            mergeSort(arr,0,mid,temp);
            //将mid右边继续拆分
            mergeSort(arr,mid+1,right,temp);

            //合并数组元素
            merge(arr,left,mid,right,temp);

        }
    }
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
         int i = left;
         int j = mid +1;
         //临时temp数组的下标索引
         int t = 0;
         //将两个部分合并
        while (i <= mid && j <=right){
            //左半边数组和右半边数组进行比较，较小的值放入临时数组
            if (arr[i] <= arr[j]){
                temp[t] =  arr[i];
                i++;
                t++;
            }else if (arr[i] >= arr[j]){
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        //上一个while的两个条件，其中之一不满足，便结束while循环，
        //两种情况 1.左半边数组全部放入temp临时数组 或 2.右半边数组全部放入temp临时数组
        //都需要将还有剩余的数组继续合并
        while (i <= mid){
            //左半边数组还有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while ( j <= right){
            //右半边数组继续合并到temp临时数组中
            temp[t] = arr[j];
            t++;
            j++;
        }
        //temp临时数组填充到数组的指定位置
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
